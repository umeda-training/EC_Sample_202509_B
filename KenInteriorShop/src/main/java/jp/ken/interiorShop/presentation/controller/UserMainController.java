package jp.ken.interiorShop.presentation.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.presentation.formmodel.CartFormModel;
import jp.ken.interiorShop.presentation.formmodel.ItemModel;
import jp.ken.interiorShop.presentation.formmodel.UserMainFormModel;
import jp.ken.interiorShop.service.UserMainService;

//担当者：竹内
@Controller
@SessionAttributes({"toDetailItem", "cartItemList", "cartList"})
public class UserMainController {
	
	//商品詳細用のセッションオブジェクトの生成
	@ModelAttribute("toDetailItem")
	public ItemModel setupItemModel() {
		return new ItemModel();
	}
	
	
	//コンストラクタ用
	private UserMainService userMainService;
	
	/** 
	 * デフォルトコンストラクタ
	 */
	
	public UserMainController(UserMainService userMainService) {
		this.userMainService = userMainService;
	}
	
	/* カート個数計算
	 * メソッド名：itemSum()
	 * 引数：List<CartFormModel>型のカートリスト
	 * 戻り値：String型の個数が表示
	 * 動作詳細：カートのなかにある個数を計算し、表示
	 */
	
	/*
	public String itemSum(List<CartFormModel> cartList) {
		int itemTotal = 0;
		for(CartFormModel cart : cartList) {
			itemTotal += cart.getBuyAmount();
		}
	}
	*/
	
	/*
	 * Get通信
	 * ・メインメニューに飛んだ際は、商品一覧がでている
	 * ・次月の商品があれば表示
	 * ・戻り値 "usrMain"
	 */
	
	@GetMapping(value = "/user/main")
	public String toUserMain(Model model, HttpSession session) throws SQLException {
		//メインタイトルテキスト
		model.addAttribute("headline", "メインメニュー");
		
		//商品一覧を全件閲覧用
		
		//リストの作成
		List<ItemModel> item_list = new ArrayList<ItemModel>();
		//サービスから商品を全件取得
		item_list = userMainService.search();
		//商品リストをモデルに登録
		model.addAttribute("item_list", item_list);
		
		
		//次月発売予定の商品の閲覧用
		
		//次月用のリスト作成
		List<ItemModel> next_item_list = new ArrayList<ItemModel>();
		//サービスから次月商品のリスト取得
		int next_month = LocalDate.now().getMonthValue() + 1;  //翌月を取得
		//next_item_list = userMainService.searchNextItems(next_month);
		//次月商品リストをモデルに登録
		model.addAttribute("next_item_list", next_item_list);
		
		//カート個数表示用
		List<CartFormModel> cartList = (List<CartFormModel>) session.getAttribute("cartList");
		if(cartList != null && !cartList.isEmpty()) {
			String cartListNumber = "現在のカート個数：" + Integer.toString(cartList.size());
			model.addAttribute("cartListNumber", cartListNumber);
		}
		return "userMain";
	}
	
	/*
	 * Post通信
	 * 戻り値："userItem"
	 * ・ワードでの検索
	 * ・カテゴリでの検索
	 * ・絞り込み(金額)
	 * ・絞り込みクリア
	 * 
	 * 戻り値："userItem"
	 * ・商品を押下時に該当商品に遷移
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value ="/user/main")
	public String UserMainControl(@Validated @ModelAttribute UserMainFormModel userMainFormModel, 
			BindingResult result, Model model, HttpServletRequest request) throws SQLException {
		//メインタイトルテキスト用
		model.addAttribute("headline", "検索結果");
		
		//取得した商品情報格納先
		List<ItemModel> toDetailItem = new ArrayList<ItemModel>();
		
		//押下した商品情報取得
		
		String toDetail = request.getParameter("detail");
		
		//エラーがある場合バリデーションを表示
		if(result.hasErrors()) {
			return "userMain";}
		if(toDetail != null) {
			//取得した商品コードからデータを取得
			
			toDetailItem = userMainService.search(toDetail);
			
			
			//セッションに格納
			model.addAttribute("toDetailItem", toDetailItem);
			
			return "/user/item";
		}
		return "userMain";
			/*
		} else {
			//保管用のリスト作成
			List<ItemModel> item_list = new ArrayList<ItemModel>();
			
			//検索条件のリスト化
			List<UserMainFormModel> form_list = new ArrayList<UserMainFormModel>();
			form_list = (List<UserMainFormModel>) userMainFormModel;
			//ワード検索(検索にワードがあるとき)の時
			if(userMainFormModel.getSearch_word() != null)
			//検索ワードの取得
			
			//カテゴライズ済みのリストを取得？
			
			//検索ワード、リストをサービスに渡して、結果リストをもらう
			
			//もらったリストをモデルに登録
			
			return "userMain";
			
			//カテゴリ検索(カテゴリに値がある時)の時
			if(userMainFormModel.getCategory() != null)
			//分類カテゴリの取得
			
			//分類するカテゴリをサービスに渡して、リストをもらう
			
			//もらったリストをモデルに登録
			
			return "userMain";
			
			//絞り込み(絞り込みが押されたとき)の時
			//if(userMainFormModel.getFirst_filter_num() != null || userMainFormModel.getLast_filter_num())
			//入力された金額を取得
			
			//現在表示されている商品一覧を取得
			
			//絞り込みの
			
			return "userMain";
			
			//絞り込みクリア(クリアが押されたとき)の時←JavaScriptで出来そう
			
			
			//return "userMain";
			
			//商品押下時
			
			}*/
		
		
	}
}
