package jp.ken.interiorShop.domain.entity;

import lombok.Data;

//担当：内川
@Data
public class UserOrderDetailsEntity {
	
	//注文詳細ID（DBでオートインクリメント）
	private int orderDetailId;  
	
	//注文ID
	private int orderId;  
	
	//商品コード
	private String itemCd;  
	
	//注文数
	private int quantity;  

}
