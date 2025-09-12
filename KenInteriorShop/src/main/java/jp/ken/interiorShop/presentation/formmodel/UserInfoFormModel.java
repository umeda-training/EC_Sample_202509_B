package jp.ken.interiorShop.presentation.formmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import lombok.Data;

/*
 * 作成 : 西村
 * 会員情報確認・変更
 */
@Data
public class UserInfoFormModel {

	// 会員ID
	private String userId;
	
	// 会員名
	@NotEmpty(message = "お名前は必須です", groups = ValidGroup1.class)
	private String userName;
	
	// フリガナ
	@NotEmpty(message = "フリガナは必須です", groups = ValidGroup1.class)
	private String userKana;
	
	// 性別
	private String userGender;
	
	// 生年月日
	private String userBirth;
	
	// 郵便番号
	@NotEmpty(message = "郵便番号は必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[0-9]{7}$", message = "ハイフンなし、半角数字7桁で入力してください"
	, groups = ValidGroup2.class)
	private String userPost;
	
	// 住所
	@NotEmpty(message = "住所は必須です", groups = ValidGroup1.class)
	private String userAddress;
	
	// 電話番号
	@NotEmpty(message = "電話番号は必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[0-9]{10,11}$", message = "ハイフンなし、半角数字10～11桁で入力してください"
	, groups = ValidGroup2.class)
	private String userPhone;

	// メールアドレス
	@NotEmpty(message = "メールアドレスは必須です", groups = ValidGroup1.class)
	@Email(message = "メールアドレスの形式が無効です", groups = ValidGroup2.class)
//	@UniqueEmail(message = "すでに登録済みのメールアドレスです", groups = ValidGroup2.class)
	private String userMail;
	
	// パスワード
	@NotEmpty(message = "パスワードは必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "パスワードは半角英数字8桁で入力してください"
		, groups = ValidGroup2.class)
	private String userPass;

}
