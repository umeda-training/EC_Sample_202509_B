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
	private String itemCd;       
	
	//商品名
	private String itemName;
	
	//在庫数
	private int itemStock;
	
	//商品単価
	private int itemPrice;
	
	//商品カテゴリ
	private String itemCategory;
	
	//商品情報
	private String itemInfo;
	
	//発売日
	private Date releaseDate;
}
