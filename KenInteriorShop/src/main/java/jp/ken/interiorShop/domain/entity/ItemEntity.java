package jp.ken.interiorShop.domain.entity;

import java.sql.Date;

import lombok.Data;

//担当者：竹内
@Data
public class ItemEntity {
	
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
	
	//商品詳細
	private String itemInfo;
	
	//発売日
	private Date releaseDate;
}
