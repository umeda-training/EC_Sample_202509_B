package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import jp.ken.interiorShop.presentation.formmodel.ListDataFormModel;

@Controller
public class UserAddController {
	// 生年月日 リスト情報作成メソッド
	private List<ListDataFormModel> getNumberList(int start, int end) {
		List<ListDataFormModel> numList = new ArrayList<ListDataFormModel>();
		for(int i=start; i<=end; i++) {
			numList.add(new ListDataFormModel(Integer.toString(i), Integer.toString(i)));
		}
		return numList;
	}
	
	// 性別 リスト情報作成メソッド
	private Map<String, String> getGenderMap() {
		Map<String, String> postMap = new LinkedHashMap<String, String>();
		
		postMap.put("0", "開発部");
		postMap.put("1", "人事部");
		postMap.put("2", "営業部");
		postMap.put("3", "総務部");
		postMap.put("4", "経理部");
		
		return postMap;
	}

}
