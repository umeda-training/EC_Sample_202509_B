package jp.ken.interiorShop.presentation.formmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import lombok.Data;

/*
 * 作成 : 西村
 * ログイン会員情報
 */
@Data
public class UserLoginFormModel {

	// 会員ID
	private String userId;
	
	// 会員名
	private String userName;
	
	// フリガナ
	private String userKana;
	
	// 性別
	private String userGender;
	
	// 生年月日
	private String userBirth;
	
	// 郵便番号
	private String userPost;
	
	// 住所
	private String userAddress;
	
	// 電話番号
	private String userPhone;

	// メールアドレス
	@NotEmpty(message = "メールアドレスは必須です", groups = ValidGroup1.class)
	@Email(message = "メールアドレスの形式が無効です", groups = ValidGroup2.class)
	private String userMail;
	
	// パスワード
	@NotEmpty(message = "パスワードは必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "パスワードは英数字8桁を入力してください"
		, groups = ValidGroup2.class)
	private String userPass;
}
