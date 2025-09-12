package jp.ken.interiorShop.presentation.EmpFormModel;

/*
 * 松田
 */

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jp.ken.interiorShop.common.validator.groups.ValidGroup1;
import jp.ken.interiorShop.common.validator.groups.ValidGroup2;
import lombok.Data;

@Data
public class EmpLoginFormModel {
	
		// 従業員名
		private String empName;
		
		// フリガナ
		private String empKana;

		// 従業員ID
		@NotEmpty(message = "従業員IDは必須入力です", groups = ValidGroup1.class)
		private String empId;
		
		// パスワード
		@NotEmpty(message = "パスワードは必須入力です", groups = ValidGroup1.class)
		@Pattern(regexp ="^[a-zA-Z0-9]{8}$", message = "英数字8桁を入力してください"
				, groups = ValidGroup2.class)
		private String empPass;
	
	
}
