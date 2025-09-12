package jp.ken.interiorShop.presentation.EmpController;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.EmpService.EmpOrderService;
import jp.ken.interiorShop.presentation.EmpFormModel.EmpOrderFormModel;


//担当：濱邊

/**
 * 画面とデータをつなぐクラス
 * - 注文一覧表示
 * - 住所変更
 * - 注文取消
 * - ログイン判定
 */

@Controller
//@RequestMapping("/order")
public class EmpOrderController {
	
	private EmpOrderService empOrderService;
	
	public EmpOrderController(EmpOrderService empOrderService) {
		this.empOrderService = empOrderService;
	}
	
	
	/*Get通信
	 * 画面遷移時、ログイン情報ないときログイン画面へ遷移
	 * ログインあり・注文ありの場合、注文情報全件表示
	 * ログインあり・注文なしの場合、エラーメッセージ表示
	 */
	@GetMapping(value="/emp/order")
	public String toEmpOrder(HttpSession session, Model model) throws Exception{
		
		//セッションからログイン情報取得
		EmpOrderFormModel empOrderFormModel = (EmpOrderFormModel)session.getAttribute("empLogin");
		
		//ログインチェック
		if(empOrderFormModel == null) {
			//ログイン情報がない場合
		return "redirect:/empLogin";
		}else {
			//ログイン情報がある場合
			List<EmpOrderFormModel> orderList = empOrderService.searchOrder();
			
			if(orderList.isEmpty()) {
				model.addAttribute("error", "注文情報がありません。");
			}
			//モデルに登録
			model.addAttribute("order_list", orderList);
			return "empOrder";
		}
	}
	
	/* Post通信
	 * ログインチェック
	 * 検索条件が空の時、全件表示
	 * 
	 * 検索機能	 */
	@PostMapping(value="/emp/order", params="search")
	public String searchOrder(@RequestParam (required = false) int orderId,
							  @RequestParam (required = false) String userName,
							  @RequestParam (required = false) Date orderDate,
							  HttpSession session,
							  Model model) throws Exception{
		
		//ログインチェック
		if(session.getAttribute("empLogin") == null) {
			return "redirect:/empLogin";
		}
		
		List<EmpOrderFormModel> orderList;
		
		//検索条件が空の時、全件取得
		if(orderId == 0 && (userName == null || userName.isEmpty()) && orderDate == null) {
			orderList = empOrderService.searchOrder();
			
		}else {
		//検索条件があるとき
			orderList = empOrderService.searchOrder(orderId, userName, orderDate);
		}
		
		if(orderList.isEmpty()) {
			model.addAttribute("error", "注文情報がありません。");
		}
		
		model.addAttribute("order_list", orderList);
		return "empOrder";
	}
	

	/** 届け先住所変更 */
    @PostMapping(value="emp/order", params="update")
    public String updateOrder(@RequestParam int orderId,
                                @RequestParam String address,
                                HttpSession session)throws Exception {
    	//ログインチェック
        if (session.getAttribute("empLogin") == null) {
            return "redirect:/empLogin";
        }
        empOrderService.updateOrder(orderId, address);
        return "redirect:/emp/order";
    }
    
    
    
    /** 注文取消し */
    @PostMapping(value="emp/order", params="cancel")
    public String deleteOrder(@RequestParam int orderId,HttpSession session)throws Exception {
    	//ログインチェック
        if (session.getAttribute("empLogin") == null) {
            return "redirect:/empLogin";
        }
        empOrderService.deleteOrder(orderId);
        return "redirect:/emp/order";
    }


}
