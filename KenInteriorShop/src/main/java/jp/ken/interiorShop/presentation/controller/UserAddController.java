package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
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
import jp.ken.interiorShop.service.UserAddService;

/*
 * 作成 : 西村
 * 新規会員登録画面
 */
@Controller
public class UserAddController {
	
	private UserAddService userAddService;
	
	public UserAddController(UserAddService userAddService) {
		this.userAddService = userAddService;
	}
	
	// 生年月日 リスト情報作成メソッド
	private List<ListDataFormModel> getNumberList(int start, int end) {
		List<ListDataFormModel> numList = new ArrayList<ListDataFormModel>();
		// 初期表示の「---」を挿入
		numList.add(new ListDataFormModel("---", ""));
		for(int i=start; i<=end; i++) {
			numList.add(new ListDataFormModel(Integer.toString(i), Integer.toString(i)));
		}
		return numList;
	}
	
	// 性別 リスト情報作成メソッド
	private Map<String, String> getGenderMap() {
		Map<String, String> genderMap = new LinkedHashMap<String, String>();
		
		genderMap.put("", "---");
		genderMap.put("0", "男");
		genderMap.put("1", "女");
		genderMap.put("2", "未回答");
		
		return genderMap;
	}
	
	// 性別 選択メソッド
//	private String setGender(String gender) {
//		switch(gender) {
//		case "0":
//			return "男";
//		case "1":
//			return "女";
//		case "2":
//			return "未回答";
//		default:
//			return "---";
//		}
//	}
	
	@GetMapping(value="/user/add")
	public String toAdd(Model model) {
		UserAddFormModel userAddForm = new UserAddFormModel();
		// 初期選択 (リスト : ---)
		userAddForm.setUserAddGender("---");			// 性別
		userAddForm.setUserAddBirthYear("---");		// 生年月日(年)
		userAddForm.setUserAddBirthMonth("---");	// 生年月日(月)
		userAddForm.setUserAddBirthDay("---");		// 生年月日(日)
		model.addAttribute("userAddFormModel", userAddForm);

		setModel(userAddForm, model);
		
		return "userAdd";
	}
	
	@PostMapping(value="/user/add")
	public String toConfirm(@Validated(ValidGroupOrder.class) @ModelAttribute UserAddFormModel userAddForm, 
			BindingResult result, Model model) throws Exception {
//		boolean errFlg = false;	// エラー有無フラグ
		// バリデーションチェック
		if(result.hasErrors()) {
			setModel(userAddForm, model);
			return "userAdd";
		}
		
		String tmpBirthYear = userAddForm.getUserAddBirthYear();		// 生年月日(年)
		String tmpBirthMonth = userAddForm.getUserAddBirthMonth();		// 生年月日(月)
		String tmpBirthDay = userAddForm.getUserAddBirthDay();			// 生年月日(日)
		
		// 生年月日未選択時
		if(tmpBirthYear.isEmpty() || tmpBirthMonth.isEmpty() || tmpBirthDay.isEmpty()) {
			model.addAttribute("birthError", "生年月日を選択してください");
			setModel(userAddForm, model);
			return "userAdd";
		}
		// 入力エラーがなければ、DB会員テーブルに登録
		// 登録結果を0(失敗) / 1(成功)で返す
		int numberOfRow = userAddService.addUser(userAddForm);
		if(numberOfRow == 0) {
			model.addAttribute("error", "登録に失敗しました");
			return "userAdd";
		}
		// ポップアップウィンドウ表示
		System.setProperty("java.awt.headless", "false");
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);		//前面に表示させるための処理
		JOptionPane.showMessageDialog(frame, "ご登録ありがとうございます。\n"
				+ "会員登録が完了しました。\n");
		frame.setAlwaysOnTop(false);
		
		return "redirect:/user/login";
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
