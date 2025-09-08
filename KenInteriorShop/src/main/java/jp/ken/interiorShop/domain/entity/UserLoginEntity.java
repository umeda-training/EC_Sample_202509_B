package jp.ken.interiorShop.domain.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class UserLoginEntity {

	// 会員ID
	private int loginId;
	
	// 会員名
	private String loginName;
	
	// フリガナ
	private String loginKana;
	
	// 性別
	private String loginGender;
	
	// 生年月日
	private Date loginBirth;
	
	// 郵便番号
	private String loginPost;
	
	// 住所
	private String loginAddress;
	
	// 電話番号
	private String loginPhone;

	// メールアドレス
	private String loginMail;
	
	// パスワード
	private String loginPass;

}
