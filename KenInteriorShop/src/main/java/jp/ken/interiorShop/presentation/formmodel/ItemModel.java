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
	
	//商品コード兼画像ファイル名
	private String item_cd;       
	
	//商品名
	private String item_name;
	
	//在庫数
	private int item_stock;
	
	//商品単価
	private int item_price;
	
	//商品カテゴリ
	private String item_category;
	
	//商品情報
	private String item_info;
	
	//発売日
	private Date release_date;
}
