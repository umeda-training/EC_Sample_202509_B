package jp.ken.interiorShop.presentation.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import jp.ken.interiorShop.domain.repository.UserSearchRepository;
import jp.ken.interiorShop.presentation.formmodel.UserInfoFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;
import jp.ken.interiorShop.service.UserInfoService;

/*
 * 作成 : 西村
 * 会員情報確認画面
 */
@Controller
@SessionAttributes({"UserLoginForm", "userInfoFormModel"})
public class UserInfoController {
	
	private UserInfoService userInfoService;
	private UserSearchRepository userSearchRepository;
	
	public UserInfoController(
			UserInfoService userInfoService,
			UserSearchRepository userSearchRepository) {
		this.userInfoService = userInfoService;
		this.userSearchRepository = userSearchRepository;
	}
	
	// セッションオブジェクトの生成
	@ModelAttribute("userInfoFormModel")
	public UserInfoFormModel setupUserInfoForm() {
		return new UserInfoFormModel();
	}
	
	/**
	 * 会員情報確認画面 GET通信用メソッド
	 * @param model
	 * @return ログイン画面, 会員情報確認画面
	 */
	@GetMapping(value = "/user/info")
	public String toInfo(SessionStatus status, Model model) {
		UserLoginFormModel loginForm = (UserLoginFormModel) model.getAttribute("UserLoginForm");
		// ログイン情報がない場合、会員ログイン画面にリダイレクト
		if(loginForm == null ||
				loginForm.getUserId() == null || loginForm.getUserId().isEmpty()) {
			return "redirect:/user/login";
		}
		// ログイン情報を確認画面表示用セッション情報にセット
		UserInfoFormModel infoForm = new UserInfoFormModel();
		infoForm.setUserId(loginForm.getUserId());				//ID
		infoForm.setUserName(loginForm.getUserName());			//名前
		infoForm.setUserKana(loginForm.getUserKana());			//フリガナ
		infoForm.setUserGender(loginForm.getUserGender());		//性別
		infoForm.setUserBirth(loginForm.getUserBirth());		//生年月日
		infoForm.setUserPost(loginForm.getUserPost());			//郵便番号
		infoForm.setUserAddress(loginForm.getUserAddress());	//住所
		infoForm.setUserPhone(loginForm.getUserPhone());		//電話番号
		infoForm.setUserMail(loginForm.getUserMail());			//メールアドレス
		infoForm.setUserPass(loginForm.getUserPass());			//パスワード
		
		model.addAttribute("userInfoFormModel", infoForm);
		model.addAttribute("infoMsg", "お客様の登録情報を表示しています。");
		return "userInfo";
	}
	
	/**
	 * 会員情報確認画面 POST通信用メソッド
	 * @param loginForm
	 * @param result
	 * @param model
	 * @return 
	 * @throws Exception
	 */
	@PostMapping(value = "/user/info")
	public String infoUser(@Validated(ValidGroupOrder.class) @ModelAttribute UserInfoFormModel infoForm,
			BindingResult result, Model model) throws Exception {
		
		// バリデーションチェック
		if(result.hasErrors()) {
			model.addAttribute("error", "入力エラーがあります。");
			return "userInfo";
		}
		// 変更された値が1つでもあるかチェック
		UserLoginFormModel loginForm = (UserLoginFormModel) model.getAttribute("UserLoginForm");
		if(!checkChangeData(infoForm, loginForm)) {
			model.addAttribute("infoMsg", "変更された項目はありません。");
			return "userInfo";
		}
		/*
		 * メールアドレスのチェック
		 * ① セッションのログイン情報(UserLoginForm)のメールアドレスと同一かチェック(同一なら②はスキップ)
		 * ② 入力メールアドレスを引数として会員テーブル検索、検索結果が0件かチェック
		 *   どちらも満たさない場合、 エラーメッセージを追加
		 */
		String infoMail = infoForm.getUserMail();
		if(!infoMail.equals(loginForm.getUserMail()) &&
				userSearchRepository.chkUserByMail(infoMail) != 0) {
			model.addAttribute("error", "入力エラーがあります。");
			model.addAttribute("mailError", "他のお客様によってすでに登録済みのメールアドレスです");
			return "userInfo";
		}
		// DB更新処理
		int numberOfRow = userInfoService.updateUser(infoForm);
		
		if(numberOfRow == 0) {
			model.addAttribute("error", "変更に失敗しました");
			return "userInfo";
		}
		// セッション情報(UserLoginForm)を更新
		loginForm.setUserName(infoForm.getUserName());			//名前
		loginForm.setUserKana(infoForm.getUserKana());			//フリガナ
		loginForm.setUserPost(infoForm.getUserPost());			//郵便番号
		loginForm.setUserAddress(infoForm.getUserAddress());	//住所
		loginForm.setUserPhone(infoForm.getUserPhone());		//電話番号
		loginForm.setUserMail(infoForm.getUserMail());			//メールアドレス
		loginForm.setUserPass(infoForm.getUserPass());			//パスワード
		
		model.addAttribute("UserLoginForm", loginForm);
		// 変更完了メッセージ
		model.addAttribute("infoMsg", "お客様の登録情報を変更しました。");
		
		return "userInfo";
	}
	
	/**
	 * 退会 POST通信用メソッド
	 * @param loginForm
	 * @param result
	 * @param model
	 * @return 
	 * @throws Exception
	 */
	@PostMapping(value = "/user/info/delete")
	public String deleteUser(SessionStatus status, Model model) throws Exception {

		// ポップアップウィンドウ表示
		System.setProperty("java.awt.headless", "false");
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);		//前面に表示させるための処理
		int result = JOptionPane.showConfirmDialog(frame, "KEN interior Shopを退会します。\n"
						+ "本当によろしいですか？\n" + "", "退会確認",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		frame.setAlwaysOnTop(false);
		if(result != JOptionPane.YES_OPTION) {
			// OKを押下しなかった場合
			model.addAttribute("infoMsg", "退会をキャンセルしました。");
			return "userInfo";
		}
		// 退会処理を行う
		// SQL-DELETE文を実行
//		UserLoginFormModel loginForm = (UserLoginFormModel) model.getAttribute("UserLoginForm");
//		int numberOfRow = userInfoService.updateUser(loginForm);
		
//		if(numberOfRow == 0) {
//			model.addAttribute("error", "退会処理中にエラーが発生しました");
//			return "userInfo";
//		}
		System.out.println("退会処理 (ダミーメッセージ)");
		// ポップアップウィンドウ表示
		System.setProperty("java.awt.headless", "false");
		frame.setAlwaysOnTop(true);		//前面に表示させるための処理
		JOptionPane.showMessageDialog(frame, "退会処理が完了しました。");
		frame.setAlwaysOnTop(false);
		
		status.setComplete();
		return "redirect:/user/login";
	}
	
	/* 入力値変更確認メソッド
	 * true : 変更あり | false : 変更なし
	 * 1つでも変更があれば、true
	 */ 
	public boolean checkChangeData(UserInfoFormModel infoForm, UserLoginFormModel loginForm) {
		if(infoForm.getUserName().equals(loginForm.getUserName())
				&& infoForm.getUserKana().equals(loginForm.getUserKana())
				&& infoForm.getUserPost().equals(loginForm.getUserPost())
				&& infoForm.getUserAddress().equals(loginForm.getUserAddress())
				&& infoForm.getUserPhone().equals(loginForm.getUserPhone())
				&& infoForm.getUserMail().equals(loginForm.getUserMail())
				&& infoForm.getUserPass().equals(loginForm.getUserPass())) {
			return false;
		}
		return true;
	}
}
