package jp.ken.interiorShop.presentation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jp.ken.interiorShop.presentation.formmodel.ItemModel;
import jp.ken.interiorShop.service.UserMainService;

//担当者：竹内

@Controller
@SessionAttributes("toDetailItem")
public class UserItemController {
	
	//コンストラクタ用
		private UserMainService userMainService;
		
		/** 
		 * デフォルトコンストラクタ
		 */
		
		public UserItemController(UserMainService userMainService) {
			this.userMainService = userMainService;
		}
	
	/* Get通信
	 * メソッド名：()
	 * 引数：＊＊
	 * 戻り値：＊＊
	 * 動作詳細：商品情報を表示
	 */
	@GetMapping(value = "/user/item")
	public String itemDetail(Model model,HttpServletRequest request) throws SQLException {
		//メインタイトルテキスト
		model.addAttribute("headline", "商品詳細");
		//取得した商品情報格納先
				List<ItemModel> toDetailItem = new ArrayList<ItemModel>();
				
				//押下した商品情報取得
				
				String toDetail = request.getParameter("detail");
				
				if(toDetail != null) {
					//取得した商品コードからデータを取得
					
					toDetailItem = userMainService.search(toDetail);
					
					
					//セッションに格納
					model.addAttribute("toDetailItem", toDetailItem);
					
					//タイトル用
					model.addAttribute("pagetitle", toDetailItem.get(0).getItemName());
					return "userItem";
				} else {
				return "userMain";
				}
		/*
		//押下された商品情報の取得
		ItemModel toDetailItem = (ItemModel) model.getAttribute("toDetailItem"); 
		
		//商品情報の有無の分岐
		if(toDetailItem == null) {
			return "redirect:/userMain";
		}
		
		return "userItem";
		*/
	}
	/*
	@PostMapping(value ="/user/item")
	public String toCart() {
		
		//前に戻る時セッションオブジェクト破壊しといて
	
	}*/
}
