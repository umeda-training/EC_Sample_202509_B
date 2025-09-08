package jp.ken.interiorShop.domain.entity;


import lombok.Data;

@Data
public class UserOrderEntity {
	
	//カート情報
	//private List<ItemEntity> cartItems;

	//クレジットカード情報
	

	//お届け先の顧客情報
	private String userAddress;
	
	private int userPost;

}
