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
public class UserLoginFormModel {

	// 会員ID
	private String loginId;
	
	// 会員名
	private String loginName;
	
	// フリガナ
	private String loginKana;
	
	// 性別
	private String loginGender;
	
	// 生年月日
	private String loginBirth;
	
	// 郵便番号
	private String loginPost;
	
	// 住所
	private String loginAddress;
	
	// 電話番号
	private String loginPhone;

	// メールアドレス
	@NotEmpty(message = "メールアドレスは必須です", groups = ValidGroup1.class)
	@Email(message = "メールアドレスの形式が無効です", groups = ValidGroup2.class)
	private String loginMail;
	
	// パスワード
	@NotEmpty(message = "パスワードは必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "パスワードは英数字8桁を入力してください"
		, groups = ValidGroup2.class)
	private String loginPass;
}
