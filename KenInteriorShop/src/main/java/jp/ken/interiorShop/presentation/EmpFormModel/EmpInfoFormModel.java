package jp.ken.interiorShop.presentation.EmpFormModel;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//松田

@Data
public class EmpInfoFormModel implements Serializable {
	
	@NotEmpty(message = "必須入力です")
	private String name;
	
	@NotEmpty(message = "必須入力です")
	private String furigana;
	
	private Integer empId;
	
	@NotEmpty(message = "必須入力です")
	@Pattern(regexp ="^[a-zA-Z0-9]{8}$", message = "英数字8桁を入力してください")
	private String password;

}
