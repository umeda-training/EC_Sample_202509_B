package jp.ken.interiorShop.presentation.formmodel;

import lombok.Data;

/*
 * カートに入れる購入モデルクラス
 */

@Data
public class CartFormModel {
	
	private String selectItem;
	
	private ItemModel selectItemdetail;
	
	private int buyAmount;
}
