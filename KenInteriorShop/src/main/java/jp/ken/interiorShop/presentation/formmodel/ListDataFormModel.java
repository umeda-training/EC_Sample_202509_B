package jp.ken.interiorShop.presentation.formmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListDataFormModel {
	
	private String label;	//表示用
	private String data;	//データ用
}
