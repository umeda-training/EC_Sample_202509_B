package jp.ken.interiorShop.presentation.EmpFormModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import lombok.Data;

//松田

@Data
public class EmpAddFormModel {
	
	// 従業員名
	@NotEmpty(message = "従業員氏名は必須です",groups = ValidGroup1.class)
	private String empLoginName;
	
	// フリガナ
	@NotEmpty(message = "フリガナは必須です",groups = ValidGroup1.class)
	private String empLoginKana;

	// 従業員ID
	@NotEmpty(message = "従業員IDは必須です",groups = ValidGroup1.class)
	private String empId;
	
	// パスワード
	@NotEmpty(message = "必須入力です",groups = ValidGroup1.class)
	@Pattern(regexp ="^[a-zA-Z0-9]{8}$", message = "英数字8桁を入力してください",groups = ValidGroup2.class)
	private String password;

}
