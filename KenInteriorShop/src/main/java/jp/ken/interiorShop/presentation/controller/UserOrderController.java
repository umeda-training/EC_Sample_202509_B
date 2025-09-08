/*
package jp.ken.interiorShop.presentation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;



@Controller
public class UserOrderController {
	
	@GetMapping(value = "/userorder")
	public String toOrder(HttpSession session, Model model) {
		
		//カートに情報がない場合、カート画面へリダイレクト
		List<ItemModel> cart = (List<ItemModel>) session.getAttribute("cart");
		if(cart == null || cart.isEmpty()) {
		return "redirect:/cart";
		} else {
		return "/userorder";
		}
	}
	
	@PostMapping(value = "/userorder")
	public String toOrder(@Validated @ModelAttribute UserOrderForm userOrderForm, Bindingresult result, Model model) {
		
		return "";
	}
}
*/
