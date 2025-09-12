package jp.ken.interiorShop.presentation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.presentation.formmodel.CartFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserOrderFormModel;

//内川：途中

@Controller
public class CartController {
	
	/*
	 * セッションからカート情報を取得
	 */
	@GetMapping(value = "/user/cart")
	public String toCart(HttpSession session, Model model) {
		
		@SuppressWarnings("unchecked")
		List<CartFormModel> cartList = (List<CartFormModel>) session.getAttribute("cartList");
		
		//カートが空の場合
		if(cartList == null || cartList.isEmpty()) {
			model.addAttribute("message", "カートは空です");
		} else {
			model.addAttribute("cartList", cartList);
		}
		return "cart";
	}
	
	/*
	 * セッションからカート情報を取得して、カートの商品を削除
	 * JavaScriptで増減・削除をして、最終的な数字をControllerで受け取る
	 * 
	 */
	
	@PostMapping(value = "/user/cart")
	public String toUpdateCart(HttpSession session, Model model) {
		//ログイン情報の取得
		UserLoginFormModel userLoginFormModel = (UserLoginFormModel) session.getAttribute("UserLoginForm");
		if(userLoginFormModel == null) {
			return "redirect:/user/login";
		}
		//カートの情報を取得する
		@SuppressWarnings("unchecked")
		List<CartFormModel> cartList = (List<CartFormModel>) session.getAttribute("cartList");
		if(cartList == null || cartList.isEmpty()) {
			model.addAttribute("message", "カートは空です");
			return "cart";
		}
		
		UserOrderFormModel userOrderFormModel = new UserOrderFormModel();
		userOrderFormModel.setUserPost(userLoginFormModel.getUserPost());
		userOrderFormModel.setUserAddress(userLoginFormModel.getUserAddress());
		
		model.addAttribute("userOrderFormModel", userOrderFormModel);
		model.addAttribute("itemList", cartList);
		return "userOrder"; 
	}

}
