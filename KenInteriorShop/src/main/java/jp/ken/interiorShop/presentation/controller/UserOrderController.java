
package jp.ken.interiorShop.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.presentation.formmodel.ItemModel;
import jp.ken.interiorShop.presentation.formmodel.UserOrderFormModel;
import jp.ken.interiorShop.service.UserOrderService;

//担当：内川

@Controller
public class UserOrderController {
	
	private UserOrderService userOrderService;
	
	public UserOrderController(UserOrderService userOrderService) {
		this.userOrderService = userOrderService;
	}
	
	@GetMapping(value = "/userOrder")
	public String toOrder(HttpSession session, Model model) {
		
		/**
		 * ログイン情報がない場合は、ログイン画面へリダイレクトする処理
		 * セッション情報を使用してログイン状態の確認
		 */
		 /*
		  *  = session.getAttribute("");
		  *  if(login == null){
		  *  	return "/login";
		  *  }
		  *  
		  */
		
		/**
		 * カートに情報がない場合、カート画面へリダイレクト
		 */
		@SuppressWarnings("unchecked")
		List<ItemModel> item_list = new ArrayList<ItemModel>();
		model.getAttribute("item_list");
		if(item_list == null || item_list.isEmpty()) {
		return "redirect:/cart";
		} 
		/**
		 * ログイン済み、カートに商品が入っている場合
		 * 初期値としてログインユーザーの郵便番号と住所を表示させる
		 * セッションに登録されている会員の住所と郵便番号を表示する
		 */
		/*List<ItemModel> itemList = (List<ItemModel>) session.getAttribute("item_list");
			if(itemList != null || !itemList.isEmpty()) {
				//セッションからログインユーザーの郵便番号と住所を取得
				return "/userOrder";
			}
		*/
		
		
		return "/userOrder";
		
	}
	
	@PostMapping(value = "/userOrder")
	public String toOrder(@Validated @ModelAttribute UserOrderFormModel userOrderFormModel, BindingResult result, Model model) throws Exception {
		
		
		/*
		 * 入力エラーがある場合、注文画面へ戻る
		 */
		if(result.hasErrors()) {
			return "/userOrder";
		}
		//代金引換選択→注文確定（DBに情報登録）
		else if("cash".equals(userOrderFormModel.getPayment())){
			userOrderService.registUserOrder(userOrderFormModel);
			return "/complete";
		} 
		//カードを選択→カード情報の入力を確認→注文確定（DBに情報登録）
		else if("card".equals(userOrderFormModel.getPayment())){
			
			return "/complete";
		}
		return null;
		
		
		
		/**
		 *代金引換を選択、お届け先にエラーがなければ注文確定
		 *DBにお届け先の郵便番号と住所を登録
		**/
		
		
		/**
		 * クレジットカードを選択した場合。カード情報を入力。お届け先の郵便番号、住所が入力されている
		 * DBにお届け先の郵便番号と住所を登録
		 **/
		
		
	
	}
}

