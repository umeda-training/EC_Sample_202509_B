
package jp.ken.interiorShop.presentation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.presentation.formmodel.CartFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserOrderFormModel;
import jp.ken.interiorShop.service.UserOrderService;

/*
 * 担当：内川
 * 9/12
 */


@Controller
public class UserOrderController {
	
	private UserOrderService userOrderService;
	
	public UserOrderController(UserOrderService userOrderService) {
		this.userOrderService = userOrderService;
	}
	
	@GetMapping(value = "/user/order")
	public String toOrder(HttpSession session, Model model) {
		/*
		 * ログイン情報の取得
		 * 未ログインの場合はログイン画面へ
		 */
		UserLoginFormModel userLoginFormModel = (UserLoginFormModel) session.getAttribute("UserLoginForm");
		if(userLoginFormModel == null) {
			return "redirect:/user/login";
		}
		
		/**
		 * カートに情報の取得
		 * カート情報がない場合、カート画面へリダイレクト
		 */
		@SuppressWarnings("unchecked")
		List<CartFormModel> item_list = (List<CartFormModel>) session.getAttribute("item_list");
		if(item_list == null || item_list.isEmpty()) {
			return "redirect:/user/cart";
		} 
		/**
		 * ログイン済み、カートに商品が入っている場合
		 * 初期値としてログインユーザーの郵便番号と住所を表示させる
		 * セッションに登録されている会員の住所と郵便番号を表示する
		 */
		
		UserOrderFormModel userOrderFormModel = new UserOrderFormModel();
		userOrderFormModel.setUserPost(userLoginFormModel.getUserPost());
		userOrderFormModel.setUserAddress(userLoginFormModel.getUserAddress());
		
		model.addAttribute("userOrderFormModel", userOrderFormModel);
		model.addAttribute("itemList", item_list);
		
		return "userOrder";
	}
	
	@PostMapping(value = "/user/order")
	public String toOrder(@Validated @ModelAttribute UserOrderFormModel userOrderFormModel, BindingResult result, HttpSession session, Model model) throws Exception {
		
		//ログイン情報を取得
		UserLoginFormModel userLoginFormModel = (UserLoginFormModel) session.getAttribute("UserLoginForm");
		userOrderFormModel.setUserId(userLoginFormModel.getUserId());
		userOrderFormModel.setUserName(userLoginFormModel.getUserName());


		/*
		 * 入力エラーがある場合、注文画面へ戻る
		 */
		if(result.hasErrors()) {
			return "userOrder";
		}
		//セッションからカート情報を取得する
		@SuppressWarnings("unchecked")
		List<CartFormModel> cartList = (List<CartFormModel>) session.getAttribute("cartList");
		
		/*
		 * 代金引換選択→注文確定（DBに情報登録）→注文詳細テーブルに情報登録
		 */
		if("cash".equals(userOrderFormModel.getPayment())){
			userOrderService.registUserOrder(userOrderFormModel, cartList);
			
			return "complate";
		} 
		/*
		 * カードを選択→カード情報の入力を確認→注文確定（DBに情報登録）→注文詳細テーブルに情報登録
		 */
		else if("card".equals(userOrderFormModel.getPayment())){
			userOrderService.registUserOrder(userOrderFormModel, cartList);
			
			return "complate";
		}
		return null;	
	}
}

