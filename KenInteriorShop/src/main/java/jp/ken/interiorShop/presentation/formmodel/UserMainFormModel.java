package jp.ken.interiorShop.presentation.formmodel;

import java.io.Serializable;

import lombok.Data;

//担当者：竹内

/*
 *フォームクラス 
 * メインページから検索情報を格納
 * 
 */

@Data
public class UserMainFormModel implements Serializable{
	
	//検索ワード
	private String search_word;
	
	//検索カテゴリ
	private String category;
	
	//絞り込み最低値段
	private String first_filter_num;
	
	//絞り込み最高値段
	private String last_filter_num;
}
