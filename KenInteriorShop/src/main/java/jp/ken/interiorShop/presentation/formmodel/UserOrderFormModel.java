package jp.ken.interiorShop.presentation.formmodel;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserOrderFormModel implements Serializable{

	//クレジットカードの情報
	@NotEmpty(message = "未入力項目があります")
	@Pattern(regexp = "^[0-9]{16}$", message = "カード番号は16桁の数字で入力してください")
	private String cardNumber;
	
	@NotEmpty(message = "未入力項目があります")
	@Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$", message = "有効期限はMM/YY形式で入力してください")
	private String expiration;
	
	@NotEmpty(message = "未入力項目があります")
	@Pattern(regexp = "^[0-9]{3}$", message = "セキュリティコードは3桁の数字で入力してください")
	private String securityCode;
	
	
	//お届け先に表示する顧客の情報
	@NotEmpty(message = "未入力項目があります")
	private String userAddress;
	
	@NotEmpty(message = "未入力項目があります")
	@Pattern(regexp = "^[0-9]{7}$", message = "入力形式に誤りがあります。")
	private String userPost;
	
	//支払方法の情報を取得
	private String payment;
	
}
