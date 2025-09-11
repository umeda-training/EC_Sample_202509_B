package jp.ken.interiorShop.presentation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.presentation.formmodel.CartFormModel;
import jp.ken.interiorShop.presentation.formmodel.ItemModel;
import jp.ken.interiorShop.presentation.formmodel.ItemStockDataModel;
import jp.ken.interiorShop.service.UserMainService;

//担当者：竹内

@Controller
@SessionAttributes({"toDetailItem", "cartList","detailItem"})
public class UserItemController {
	
	/*カート用のセッションオブジェクトの生成
	@ModelAttribute("cartList")
	public ArrayList<CartFormModel> setupCartList{
		return new ArrayList<CartFormModel>();
	}
	*/
	
	//コンストラクタ用
	private UserMainService userMainService;
	
	/** 
	 * デフォルトコンストラクタ
	 */
	
	public UserItemController(UserMainService userMainService) {
		this.userMainService = userMainService;
	}
		
	/* 在庫数リスト作成
	 * メソッド名：getNumberList()
	 * 引数：1～在庫数
	 * 戻り値：List<ItemStockDataModel>型の数値リスト
	 * 動作詳細：1～在庫数のデータを作る、他にセレクトリスト作るときに併用可
	 */
	
	private List<ItemStockDataModel> getNumberList(int start, int end){
		List<ItemStockDataModel> numberList = new ArrayList<ItemStockDataModel>();
		for(int i = start; i <= end ; i++) {
			numberList.add(new ItemStockDataModel(Integer.toString(i), Integer.toString(i)));
		}
		return numberList;
	}
	
	/* Get通信
	 * メソッド名：()
	 * 引数：＊＊
	 * 戻り値：＊＊
	 * 動作詳細：商品情報を表示
	 */
	@SuppressWarnings("unused")
	@GetMapping(value = "/user/item")
	public String itemDetail(Model model,HttpSession session,HttpServletRequest request) throws SQLException {
		//メインタイトルテキスト
		model.addAttribute("headline", "商品詳細");
		//取得した商品情報格納先
		List<ItemModel> toDetailItem = new ArrayList<ItemModel>();
		
		//押下した商品コード取得
		String toDetail = request.getParameter("detail");
		
		//商品コードが送られてなかったらメインにリダイレクト
		if(toDetailItem == null && !toDetail.isEmpty()) {
			return "redirect:/userMain";
		}
		//カートオブジェクト作成・セッション登録
		List<CartFormModel> cartList = new ArrayList<CartFormModel>();
		model.addAttribute("cartList", cartList);
		
		if(toDetail != null) {
			//取得した商品コードから商品を検索
			toDetailItem = userMainService.search(toDetail);
			
			//セッションチェック用(自分用)
			model.addAttribute("toDetailItem", toDetailItem.get(0));
			
			//セッションに格納
			ItemModel detailItem = null;
			for(ItemModel tmpItem : toDetailItem) {
				detailItem = tmpItem;
			}
			
			model.addAttribute("detailItem", detailItem);
			
			//注文用のオブジェクトを登録
			CartFormModel cartFormModel = new CartFormModel();
			model.addAttribute("cartFormModel", cartFormModel);
			
			//在庫数を表示用
			int itemStock = toDetailItem.get(0).getItemStock();
			model.addAttribute("stockList", getNumberList(1, itemStock));
			
			//タイトル用
			model.addAttribute("pagetitle", toDetailItem.get(0).getItemName());
			
			return "userItem";
			} else {
				return "redirect:/userMain";
			}
	}
	
	/* Post通信
	 * メソッド名：()
	 * 引数：＊＊
	 * 戻り値：＊＊
	 * 動作詳細：カートに商品を追加、在庫数の選択、前に戻る
	 */
	
	@PostMapping(value ="/user/item", params = "cart")
	public String insertCart(@ModelAttribute CartFormModel cartFormModel, Model model,HttpSession session, 
			HttpServletRequest request) throws SQLException {
		//セッションからカート情報を取得
		List<CartFormModel> cartList = (List<CartFormModel>) session.getAttribute("cartList");
		
		//カート情報が空の場合、オブジェクト作成
		if(cartList == null) {
			cartList = new ArrayList<CartFormModel>();
		}
		//押下された商品コードをCartFormModelに保管
		String select = request.getParameter("select");
		ItemModel detailItem = userMainService.search(select).get(0);
		cartFormModel.setSelectItem(select);
		cartFormModel.setSelectItemdetail(detailItem);
		
		//カートに追加を押した情報をカートに追加
		cartList.add(cartFormModel);
		
		//追加後、セッションに登録
		session.setAttribute("cartList", cartList);
		
		//再表示用
		model.addAttribute("detailItem", detailItem);
		model.addAttribute("stockList", getNumberList(1, detailItem.getItemStock()));
		
		return "userItem";
	}
	
	@PostMapping(value ="/user/item", params = "back")
	public String toBack() {
		
		return "redirect:/userMain";
	}
	
	@PostMapping(value ="/user/item", params = "check")
	public String toCart(Model model,HttpSession session) {
		model.addAttribute("cartList", session.getAttribute("cartList"));
		System.out.println("カートへ");
		return "cart";
	}
}
