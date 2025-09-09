package jp.ken.interiorShop.domain.entity;

import java.sql.Date;

import lombok.Data;

//担当者：竹内
@Data
public class ItemEntity {
	
	private String itemCd;
	
	private String itemName;
	
	private int itemStock;
	
	private int itemPrice;
	
	private String itemCategory;
	
	private String itemInfo;
	
	private Date releaseDate;
}
