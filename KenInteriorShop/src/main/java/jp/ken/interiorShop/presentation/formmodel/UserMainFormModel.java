package jp.ken.interiorShop.presentation.formmodel;

import java.io.Serializable;

import lombok.Data;

/*
 *フォームクラス 
 * メインページから検索情報を格納
 * 
 */

@Data
public class UserMainFormModel implements Serializable{
	
	private String search_word;
	
	private String category;
	
	private String first_filter_num;
	
	private String last_filter_num;
}
