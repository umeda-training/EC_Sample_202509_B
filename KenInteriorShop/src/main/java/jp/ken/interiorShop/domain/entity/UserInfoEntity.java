package jp.ken.interiorShop.domain.entity;

import java.sql.Date;

import lombok.Data;

/*
 * 作成 : 西村
 */
@Data
public class UserInfoEntity {

	// 会員ID
	private int userId;
	
	// 会員名
	private String userName;
	
	// フリガナ
	private String userKana;
	
	// 性別
	private String userGender;
	
	// 生年月日
	private Date userBirth;
	
	// 郵便番号
	private String userPost;
	
	// 住所
	private String userAddress;
	
	// 電話番号
	private String userPhone;

	// メールアドレス
	private String userMail;
	
	// パスワード
	private String userPass;


}
