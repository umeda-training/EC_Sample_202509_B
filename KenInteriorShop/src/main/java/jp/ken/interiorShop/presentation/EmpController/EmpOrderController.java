package jp.ken.interiorShop.presentation.EmpController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//担当：濱邊
@Controller
@RequestMapping("/empOrder")
public class EmpOrderController {
	
	/*
	@GetMapping
	public String getOrderList(HttpSession session, Model model){
		
		//セッションからログイン情報取得
		EmpOrderFormModel empOrderFormModel = (EmpOrderFormModel)session.getAttribute("empLogin");
		
		//ログイン情報がない場合
		if(empOrderFormModel == null) {
		//ユーザーを /empLogin にリダイレクト（ログイン画面へ移動）させる。
		return "redirect:/empLogin";
		}
		
		//ログイン情報がある場合
		List<EmpOrderFormModel> orderList = EmpOrderService.searchOrder();
		
		//モデルに登録
		model.addAttribute("order_list", orderList);
			
			
		
		
	}
	*/
	
	/*
	@PostMapping
	public List<OrderModel> searchOrder(int order_id, String user_name, Date order_date){
		
	}
	*/

}
