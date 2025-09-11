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
import jp.ken.interiorShop.presentation.formmodel.UserInfoFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;

/*
 * 作成 : 西村
 * 会員情報確認画面
 */
@Controller
@SessionAttributes("UserLoginForm")
public class UserInfoController {

	
	
	public UserInfoController() {
		
	}
	
	// セッションオブジェクトの生成
//	@ModelAttribute("UserLoginForm")
//	public UserLoginFormModel setupUserLoginForm() {
//		return new UserLoginFormModel();
//	}
	
	/**
	 * 会員情報確認画面 GET通信用メソッド
	 * @param model
	 * @return ログイン画面, 会員情報確認画面
	 */
	@GetMapping(value = "/user/info")
	public String toInfo(SessionStatus status, Model model) {
		UserLoginFormModel loginForm = (UserLoginFormModel) model.getAttribute("UserLoginForm");
		// ログイン情報がない場合、会員ログイン画面にリダイレクト
		if(loginForm.getUserId() == null || loginForm.getUserId().isEmpty()) {
			return "redirect:/user/login";
		}
		// ログイン情報を確認画面表示用のFormModelにセット
		UserInfoFormModel infoForm = new UserInfoFormModel();
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
		/*
		 * [退会ボタン押下時]
		 * 確認ダイアログを表示
		 * 		はい → 現在ログイン中の会員情報をDBから削除(セッションの情報を参照する)
		 *			成功 → セッション破棄して会員メインメニュー画面へ
		 *			失敗 → ロールバック、エラーメッセージを追加
		 *		いいえ → 本画面を再表示 (「退会処理をキャンセルしました」的なメッセージを入れてもいいかも)
		 */
		// バリデーションチェック
		if(result.hasErrors()) {
			return "userInfo";
		}
		
		/*
		 * [保存]ボタン押下時
		 * バリデーションチェック(上記)
		 * UserInfoFormModelにセッションのログイン情報からID,性別,生年月日を入れてDB更新
		 *			成功 → 適当なメッセージを表示してセッション情報・画面更新
		 *			失敗 → ロールバック、エラーメッセージを追加
		 */
		
		
		// 確認用として一旦メインメニューに飛ばす
		return "redirect:/user/main";
	}
	
	/* 入力値変更確認メソッド
	 * true : 変更なし | false : 変更あり
	 * 同一メールアドレスはバリデーションチェック時点で弾かれており、
	 * バリデーションチェックでエラーなし ＝ 最低でもメールアドレスは変更されている
	 * となるため、不要な処理。念のためコメントアウトで残す
	 */ 
//	public boolean checkChangeData(UserInfoFormModel infoForm, UserLoginFormModel loginForm) {
//		if(infoForm.getUserName() == loginForm.getUserName()
//				&& infoForm.getUserKana() == loginForm.getUserKana()
//				&& infoForm.getUserPost() == loginForm.getUserPost()
//				&& infoForm.getUserAddress() == loginForm.getUserAddress()
//				&& infoForm.getUserPhone() == loginForm.getUserPhone()
//				&& infoForm.getUserMail() == loginForm.getUserMail()
//				&& infoForm.getUserPass() == loginForm.getUserPass()) {
//			return true;
//		}
//		return false;
//	}
}
