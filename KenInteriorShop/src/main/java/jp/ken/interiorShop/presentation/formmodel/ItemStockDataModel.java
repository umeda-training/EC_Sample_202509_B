package jp.ken.interiorShop.presentation.formmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

//作成者：竹内

/*
 * 商品詳細で在庫数のセレクトリストを作るクラス
 * 
 */

@Data
@AllArgsConstructor
public class ItemStockDataModel {
	
	private String label;
	
	private String data;
}
