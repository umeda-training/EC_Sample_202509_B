package jp.ken.interiorShop.presentation.EmpController;

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
	
	
	@GetMapping("/order")
	public String toEmpOrder(HttpSession session, Model model) throws Exception{
		
		//セッションからログイン情報取得
		EmpOrderFormModel empOrderFormModel = (EmpOrderFormModel)session.getAttribute("empLogin");
		
		//ログイン情報がない場合
		if(empOrderFormModel == null) {
		//ユーザーを /empLogin にリダイレクト（ログイン画面へ移動）させる。
		return "redirect:/empLogin";
		}else {
			//ログイン情報がある場合
			List<EmpOrderFormModel> orderList = empOrderService.searchOrder();
			//モデルに登録
			model.addAttribute("order_list", orderList);
			return "order_list";
		}
		
		
	}
	
	/*
	 public String getOrderList(Model model) throws Exception {
		List<EmpOrderFormModel> orderList = empOrderService.searchOrder();
		//モデルに登録
		model.addAttribute("order_list", orderList);
				
		return "order_list";
	}
	 */
	
	/*
	@PostMapping
	public List<OrderModel> searchOrder(int order_id, String user_name, Date order_date){
		
	}
	*/
	
	/** 注文住所変更 */
    @PostMapping("/update")
    public String updateAddress(@RequestParam int orderId,
                                @RequestParam String address,
                                HttpSession session)throws Exception {
        if (session.getAttribute("empLogin") == null) {
            return "redirect:/empLogin";
        }
        empOrderService.updateOrder(orderId, address);
        return "redirect:/emp/order";
    }


}
