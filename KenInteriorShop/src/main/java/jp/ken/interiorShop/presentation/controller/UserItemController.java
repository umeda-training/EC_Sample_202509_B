package jp.ken.interiorShop.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.ken.interiorShop.presentation.formmodel.ItemModel;

//担当者：竹内

@Controller
@SessionAttributes("toDetailItem")
public class UserItemController {
	
	/* Get通信
	 * メソッド名：()
	 * 引数：＊＊
	 * 戻り値：＊＊
	 * 動作詳細：商品情報を表示
	 */
	@GetMapping(value = "/user/item")
	public String itemDetail(Model model) {
		//メインタイトルテキスト
		model.addAttribute("headline", "メインメニュー");
		
		//押下された商品情報の取得
		ItemModel toDetailItem = (ItemModel) model.getAttribute("toDetailItem"); 
		
		//商品情報の有無の分岐
		if(toDetailItem == null) {
			return "redirect:/userMain";
		} else {
			
			return "userItem";
		}
	}
	/*
	@PostMapping(value ="/user/item")
	public String toCart() {
		
	
	}*/
}
