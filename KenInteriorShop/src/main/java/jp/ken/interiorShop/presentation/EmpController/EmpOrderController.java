package jp.ken.interiorShop.presentation.EmpController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empOrder")
public class EmpOrderController {
	
	
	@GetMapping
	public String toLogout(){
	//ユーザーを /empLogin にリダイレクト（ログイン画面へ移動）させる。
		return "redirect:/empLogin";
	}
	
	/*
	@PostMapping
	public List<OrderModel> searchOrder(int order_id, String user_name, Date order_date){
		
	}
	*/

}
