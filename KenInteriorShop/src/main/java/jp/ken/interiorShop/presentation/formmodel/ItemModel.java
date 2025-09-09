package jp.ken.interiorShop.presentation.formmodel;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

//担当者：竹内

/*
 * 商品モデルクラス 
 *・m_itemsテーブルをもとに作成
 */

@Data
public class ItemModel implements Serializable {
	
	private String item_cd;       //商品コード兼画像ファイル名
	
	private String item_name;
	
	private int item_stock;
	
	private int item_price;
	
	private String item_category;
	
	private String item_info;
	
	private Date release_date;
}
