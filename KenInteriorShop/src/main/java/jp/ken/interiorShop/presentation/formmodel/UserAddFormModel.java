package jp.ken.interiorShop.presentation.formmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.annotation.UniqueEmail;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import lombok.Data;

/*
 * 作成 : 西村
 * 会員登録情報
 */
@Data
public class UserAddFormModel {
	
	// 名前
	@NotEmpty(message = "お名前は必須です", groups = ValidGroup1.class)
	private String userAddName;
	
	// フリガナ
	@NotEmpty(message = "フリガナは必須です", groups = ValidGroup1.class)
	private String userAddKana;
	
	// 性別
	@NotEmpty(message = "性別を選択してください", groups = ValidGroup1.class)
	private String userAddGender;
	
	// 生年月日
	private String userAddBirthYear;
	private String userAddBirthMonth;
	private String userAddBirthDay;
	
	// 郵便番号
	@NotEmpty(message = "郵便番号は必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[0-9]{7}$", message = "ハイフンなし、半角数字7桁で入力してください"
	, groups = ValidGroup2.class)
	private String userAddPost;
	
	// 住所
	@NotEmpty(message = "住所は必須です", groups = ValidGroup1.class)
	private String userAddAddress;
	
	// 電話番号
	@NotEmpty(message = "電話番号は必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[0-9]{10,11}$", message = "ハイフンなし、半角数字10～11桁で入力してください"
	, groups = ValidGroup2.class)
	private String userAddPhone;

	// メールアドレス
	@NotEmpty(message = "メールアドレスは必須です", groups = ValidGroup1.class)
	@Email(message = "メールアドレスの形式が無効です", groups = ValidGroup2.class)
	@UniqueEmail(message = "すでに登録済みのメールアドレスです", groups = ValidGroup2.class)
	private String userAddMail;
	
	// パスワード
	@NotEmpty(message = "パスワードは必須です", groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "パスワードは半角英数字8桁で入力してください"
		, groups = ValidGroup2.class)
	private String userAddPass;

}
