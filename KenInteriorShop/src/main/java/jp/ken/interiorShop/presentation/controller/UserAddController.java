package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import jp.ken.interiorShop.presentation.formmodel.ListDataFormModel;

/*
 * 作成 : 西村
 */
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
		Map<String, String> genderMap = new LinkedHashMap<String, String>();
		
		genderMap.put("0", "---");
		genderMap.put("man", "男");
		genderMap.put("woman", "女");
		genderMap.put("no-answer", "未回答");
		
		return genderMap;
	}
	
	// 性別 選択メソッド
	private String setGender(String gender) {
		switch(gender) {
		case "man":
			return "男";
		case "woman":
			return "女";
		case "no-answer":
			return "その他";
		default:
			return "";
		}
	}
	
//	@GetMapping(value="/user/add")
//	public String toAdd(Model model) {
//		EmployeeForm userAddForm = new EmployeeForm();
//		// 初期選択
//		employeeForm.setGender("man");			// 性別：男
//		employeeForm.setBirthYear("1970");		// 生年月日(年)：1970
//		employeeForm.setEmpYear("2000");		// 入社日(年)：2000
//		employeeForm.setEmpMonth("4");			// 入社日(月)：4
//		employeeForm.setPost("0");				// 部署：開発部
//		model.addAttribute("employeeForm", employeeForm);
//
//		setModel(employeeForm, model);
//		
//		return "employeeRegistration";
//	}

}
