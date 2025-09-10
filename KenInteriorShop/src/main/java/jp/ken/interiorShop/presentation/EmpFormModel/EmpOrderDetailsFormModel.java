package jp.ken.interiorShop.presentation.EmpFormModel;

import lombok.Data;

//担当：濱邊
@Data
public class EmpOrderDetailsFormModel {
	//商品コード
	private String itemCd;
	
	//注文数
	private int quantity;

}
