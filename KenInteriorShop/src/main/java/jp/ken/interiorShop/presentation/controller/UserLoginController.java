package jp.ken.interiorShop.presentation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;
import jp.ken.interiorShop.service.UserLoginService;

/*
 * 作成 : nishimura
 */
@Controller
@SessionAttributes("UserLoginForm")
public class UserLoginController {

	private UserLoginService userLoginService;
	
	public UserLoginController(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}
	
	// セッションオブジェクトの生成
	@ModelAttribute("UserLoginForm")
	public UserLoginFormModel setupUserLoginForm() {
		return new UserLoginFormModel();
	}
	
	/**
	 * ログイン画面 GET通信用メソッド
	 * @param model
	 * @return ログイン画面
	 */
	@GetMapping(value = "/user/login")
	public String toLogin(SessionStatus status, Model model) {
		UserLoginFormModel userLoginForm = new UserLoginFormModel();
		model.addAttribute("userLoginForm", userLoginForm);
		return "userLogin";
	}
	
	/**
	 * ログイン画面 POST通信用メソッド
	 * @param loginForm
	 * @param result
	 * @param model
	 * @return ログイン画面, 従業員検索画面
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	public String loginMembers(@Validated @ModelAttribute UserLoginFormModel userLoginForm,
			BindingResult result, Model model) throws Exception {
		String btn = (String) model.getAttribute("btn");
		// 新規会員登録 ボタン押下時
		if(btn != null) {
			return "userAdd";
		}

		// バリデーションチェック(未入力チェック)
		if(result.hasErrors()) {
			return "userLogin";
		} else {
			List<UserLoginFormModel> formList = userLoginService.getLogin(userLoginForm);
			
			if(formList == null || formList.isEmpty()) {
				model.addAttribute("errors", "メールアドレスまたはパスワードが違います");
				return "userLogin";
			}
			//セッションオブジェクトに格納
			userLoginForm.setLoginId(formList.getFirst().getLoginId());
			userLoginForm.setLoginName(formList.getFirst().getLoginName());
			userLoginForm.setLoginKana(formList.getFirst().getLoginKana());
			userLoginForm.setLoginGender(formList.getFirst().getLoginGender());
			userLoginForm.setLoginBirth(formList.getFirst().getLoginBirth());
			userLoginForm.setLoginPost(formList.getFirst().getLoginPost());
			userLoginForm.setLoginAddress(formList.getFirst().getLoginAddress());
			userLoginForm.setLoginPhone(formList.getFirst().getLoginPhone());
			userLoginForm.setLoginMail(formList.getFirst().getLoginMail());
			userLoginForm.setLoginPass(formList.getFirst().getLoginPass());
			model.addAttribute("UserLoginForm", userLoginForm);
		}
		
		// ログイン成功時、遷移元の画面に遷移
		String backAddress = (String) model.getAttribute("backAddress");
		//model.addAttribute("headline", "トップページ");
		return backAddress;
	}
}
