package jp.ken.interiorShop.presentation.formmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import lombok.Data;

/*
 * 作成 : 西村
 */
@Data
public class UserAddFormModel {
	
	// 名前
	private String addName;
	
	// フリガナ
	private String addKana;
	
	// 性別
	private String addGender;
	
	// 生年月日
	private String addBirth;
	
	// 郵便番号
	private String addPost;
	
	// 住所
	private String addAddress;
	
	// 電話番号
	private String addPhone;

	// メールアドレス
	@NotEmpty(message = "メールアドレスは必須です", groups = ValidGroup1.class)
	@Email(message = "メールアドレスの形式が無効です", groups = ValidGroup2.class)
	private String addMail;
	
	// パスワード
	@NotEmpty(message = "パスワードは必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "パスワードは英数字8桁を入力してください"
		, groups = ValidGroup2.class)
	private String addPass;

}
