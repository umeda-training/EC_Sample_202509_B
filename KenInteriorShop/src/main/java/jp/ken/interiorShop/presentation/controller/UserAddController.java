package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.List;

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

}
