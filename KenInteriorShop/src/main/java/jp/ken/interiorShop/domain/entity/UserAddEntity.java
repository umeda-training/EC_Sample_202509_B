package jp.ken.interiorShop.domain.entity;

import lombok.Data;

/*
 * 作成 : 西村
 */
@Data
public class UserAddEntity {
	
	// 名前
	private String userAddName;
	
	// フリガナ
	private String userAddKana;
	
	// 性別
	private String userAddGender;
	
	// 生年月日
	private String userAddBirthYear;
	private String userAddBirthMonth;
	private String userAddBirthDay;
	
	// 郵便番号
	private String userAddPost;
	
	// 住所
	private String userAddAddress;
	
	// 電話番号
	private String userAddPhone;

	// メールアドレス
	private String userAddMail;
	
	// パスワード
	private String userAddPass;

}
