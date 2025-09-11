package jp.ken.interiorShop.presentation.formmodel;

import java.util.List;

import lombok.Data;

/*
 * カートに入れる購入モデルクラス
 */

@Data
public class CartFormModel {
	
	private List<ItemModel> itemList;
	
	
	private String buyAmount;
}
