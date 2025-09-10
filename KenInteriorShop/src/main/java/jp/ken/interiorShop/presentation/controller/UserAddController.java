package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.ken.interiorShop.common.validator.groups.ValidGroupOrder;
import jp.ken.interiorShop.presentation.formmodel.ListDataFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserAddFormModel;

/*
 * 作成 : 西村
 * 新規会員登録画面
 */
@Controller
public class UserAddController {
	// 生年月日 リスト情報作成メソッド
	private List<ListDataFormModel> getNumberList(int start, int end) {
		List<ListDataFormModel> numList = new ArrayList<ListDataFormModel>();
		// 初期表示の「---」を挿入
		numList.add(new ListDataFormModel("", "---"));
		for(int i=start; i<=end; i++) {
			numList.add(new ListDataFormModel(Integer.toString(i), Integer.toString(i)));
		}
		return numList;
	}
	
	// 性別 リスト情報作成メソッド
	private Map<String, String> getGenderMap() {
		Map<String, String> genderMap = new LinkedHashMap<String, String>();
		
		genderMap.put("", "---");
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
			return "未回答";
		default:
			return "---";
		}
	}
	
	@GetMapping(value="/user/add")
	public String toAdd(Model model) {
		UserAddFormModel userAddForm = new UserAddFormModel();
		// 初期選択 (リスト : ---)
		userAddForm.setGender("---");			// 性別
		userAddForm.setBirthYear("---");		// 生年月日(年)
		userAddForm.setBirthMonth("---");	// 生年月日(月)
		userAddForm.setBirthDay("---");		// 生年月日(日)
		model.addAttribute("userAddForm", userAddForm);

		setModel(userAddForm, model);
		
		return "userAdd";
	}
	
	@PostMapping(value="/user/add")
	public String toConfirm(@Validated(ValidGroupOrder.class) @ModelAttribute UserAddFormModel userAddForm, 
			BindingResult result, Model model) {
//		boolean errFlg = false;	// エラー有無フラグ
		// バリデーションチェック
		if(result.hasErrors()) {
			setModel(userAddForm, model);
			return "userAdd";
		}
		
		String tmpGender = userAddForm.getGender();				// 性別
		String tmpBirthYear = userAddForm.getBirthYear();		// 生年月日(年)
		String tmpBirthMonth = userAddForm.getBirthMonth();		// 生年月日(月)
		String tmpBirthDay = userAddForm.getBirthDay();			// 生年月日(日)
		
		// 性別未選択時
		if(setGender(tmpGender) == "---") {
			model.addAttribute("genderError", "性別を選択してください");
			return "userAdd";
		}
		// 生年月日未選択時
		if(tmpBirthYear.isEmpty() || tmpBirthYear == "---"
				|| tmpBirthMonth.isEmpty() || tmpBirthMonth == "---"
				|| tmpBirthDay.isEmpty() || tmpBirthDay == "---") {
			model.addAttribute("birthError", "生年月日を選択してください");
			return "userAdd";
		}
		// 入力エラーがなければ、DB会員テーブルに登録
			// 入力データを引数として登録メソッド呼び出し
			// ポップアップウィンドウ表示
		JOptionPane.showMessageDialog(null, "ご登録ありがとうございます。\n"
				+ "会員登録が完了しました。\n");
		
		return "userLogin";
	}
	
	public void setModel(@ModelAttribute UserAddFormModel userAddForm, Model model) {
		
		Calendar calendar = Calendar.getInstance();
		// 性別リスト
		model.addAttribute("genderMap", getGenderMap());
		// 生年月日リスト
		model.addAttribute("listBirthYears", getNumberList(1900, calendar.get(Calendar.YEAR)));
		model.addAttribute("listBirthMonths", getNumberList(1, 12));
		model.addAttribute("listBirthDays", getNumberList(1, 31));

	}

}
