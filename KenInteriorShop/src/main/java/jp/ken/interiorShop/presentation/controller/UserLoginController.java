package jp.ken.interiorShop.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.ken.interiorShop.common.validator.groups.ValidGroupOrder;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;
import jp.ken.interiorShop.service.UserSearchService;

/*
 * 作成 : 西村
 * 会員ログイン画面
 */
@Controller
@SessionAttributes("UserLoginForm")
public class UserLoginController {

	private UserSearchService userSearchService;
	
	public UserLoginController(UserSearchService userSearchService) {
		this.userSearchService = userSearchService;
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
		UserLoginFormModel form = new UserLoginFormModel();
		model.addAttribute("userLoginFormModel", form);
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
	@PostMapping(value = "/user/login")
	public String loginMembers(@Validated(ValidGroupOrder.class) @ModelAttribute UserLoginFormModel loginForm,
			BindingResult result, Model model) throws Exception {
		// バリデーションチェック
		if(result.hasErrors()) {
			return "userLogin";
		} else {
//			List<UserLoginFormModel> formList = userSearchService.getLogin(userLoginForm);
			UserLoginFormModel form = userSearchService.getLogin(loginForm);
			String tmpMail = form.getUserMail();
			if(tmpMail == null || tmpMail.isEmpty()) {
				model.addAttribute("errors", "メールアドレスまたはパスワードが違います");
				return "userLogin";
			}
			//セッションオブジェクトに格納
			UserLoginFormModel userLoginForm = new UserLoginFormModel();
			userLoginForm.setUserId(form.getUserId());
			userLoginForm.setUserName(form.getUserName());
			userLoginForm.setUserKana(form.getUserKana());
			userLoginForm.setUserGender(form.getUserGender());
			userLoginForm.setUserBirth(form.getUserBirth());
			userLoginForm.setUserPost(form.getUserPost());
			userLoginForm.setUserAddress(form.getUserAddress());
			userLoginForm.setUserPhone(form.getUserPhone());
			userLoginForm.setUserMail(form.getUserMail());
			userLoginForm.setUserPass(form.getUserPass());
			model.addAttribute("UserLoginForm", userLoginForm);
/*
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
*/
		}
		
		// ログイン成功時、遷移元の画面に遷移
		// 一旦メインメニュー固定とする
//		String backAddress = (String) model.getAttribute("backAddress");
//		return backAddress;
		return "redirect:/user/main";
	}
}
