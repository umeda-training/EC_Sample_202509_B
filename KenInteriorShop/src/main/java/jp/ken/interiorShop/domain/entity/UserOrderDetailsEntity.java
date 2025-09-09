package jp.ken.interiorShop.domain.entity;

import lombok.Data;

//担当：内川
@Data
public class UserOrderDetailsEntity {
	
	private int orderDetailId;  //注文詳細ID（DBでオートインクリメント）
	
	private int orderId;  //注文ID
	
	private String itemCd;  //商品コード
	
	private int quantity;  //注文数
	

}
