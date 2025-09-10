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
	@GetMapping(value = "/cart")
	public String toCart(HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
		List<ItemModel> cart = (List<ItemModel>) session.getAttribute("cart");
		if(cart == null || cart.isEmpty()) {
			cart = new ArrayList<>();
			model.addAttribute("message", "カートに商品を追加してください");
		}
		model.addAttribute("cart", cart);
		
		return "/cart";
	}
	
	/*
	 * セッションからカート情報を取得して、カートの商品を増減
	 * 
	 * 
	 */
	
	@PostMapping(value = "/cart")
	public String toUpdateCart(HttpSession session, Model model) {
		
		return "/cart"; 
	}

}
