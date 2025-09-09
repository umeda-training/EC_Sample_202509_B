package jp.ken.interiorShop.presentation.formmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.annotation.UniqueEmail;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import jp.ken.interiorShop.common.validator.groups.ValidGroup3;
import lombok.Data;

/*
 * 作成 : 西村
 */
@Data
public class UserAddFormModel {
	
	// 名前
	@NotEmpty(message = "必須入力です", groups = ValidGroup1.class)
	private String addName;
	
	// フリガナ
	@NotEmpty(message = "必須入力です", groups = ValidGroup1.class)
	private String addKana;
	
	// 性別
	@NotEmpty(message = "性別を選択してください", groups = ValidGroup1.class)
	private String addGender;
	
	// 生年月日
	@NotEmpty(message = "生年月日を選択してください", groups = ValidGroup1.class)
	private String addBirth;
	
	// 郵便番号
	@NotEmpty(message = "必須入力です", groups = ValidGroup1.class)
	private String addPost;
	
	// 住所
	@NotEmpty(message = "必須入力です", groups = ValidGroup1.class)
	private String addAddress;
	
	// 電話番号
	@NotEmpty(message = "必須入力です", groups = ValidGroup1.class)
	private String addPhone;

	// メールアドレス
	@NotEmpty(message = "メールアドレスは必須です", groups = ValidGroup1.class)
	@Email(message = "メールアドレスの形式が無効です", groups = ValidGroup2.class)
	@UniqueEmail(message = "すでに登録済みのメールアドレスです", groups = ValidGroup3.class)
	private String addMail;
	
	// パスワード
	@NotEmpty(message = "パスワードは必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "パスワードは英数字8桁を入力してください"
		, groups = ValidGroup2.class)
	private String addPass;

}
