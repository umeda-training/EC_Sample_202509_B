package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.presentation.formmodel.ItemModel;

//内川：途中

@Controller
public class CartController {
	
	/*
	 * セッションからカート情報を取得
	 */
	@GetMapping(value = "/user/cart")
	public String toCart(HttpSession session, Model model) {
		/*@SuppressWarnings("unchecked")
		//ItemModel⇒CartItemModelに変更予定
		List<ItemModel> cart = (List<ItemModel>) session.getAttribute("cart");
		*/
		List<ItemModel> cart = new ArrayList<>();
		//仮データ
		ItemModel item1 = new ItemModel();
		item1.setItemName("机");
		item1.setItemStock(5);
		item1.setItemPrice(5000);
		cart.add(item1);
		
		ItemModel item2 = new ItemModel();
		item2.setItemName("イス");
		item2.setItemStock(10);
		item2.setItemPrice(800);
		cart.add(item2);
		
		if(cart == null || cart.isEmpty()) {
			cart = new ArrayList<>();
			model.addAttribute("message", "カートに商品を追加してください");
		}
		model.addAttribute("cart", cart);
		
		return "cart";
	}
	
	/*
	 * セッションからカート情報を取得して、カートの商品を削除
	 * JavaScriptで増減・削除をして、最終的な数字をControllerで受け取る
	 * 
	 */
	
	@PostMapping(value = "/user/cart")
	public String toUpdateCart(HttpSession session, Model model) {
		/*
		@SuppressWarnings("unchecked")
		//ItemModel⇒CartItemModelに変更予定
		List<ItemModel> cart = (List<ItemModel>) session.getAttribute("cart");
		*/
		
		return "cart"; 
	}

}
