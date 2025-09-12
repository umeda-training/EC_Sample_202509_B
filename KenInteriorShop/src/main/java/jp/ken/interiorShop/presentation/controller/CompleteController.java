package jp.ken.interiorShop.presentation.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import jp.ken.interiorShop.service.UserOrderService;

/*　作成者：竹内
 * 注文完了コントローラー 
 */

@Controller
public class CompleteController {
	
	//コンストラクタインジェクション
	private UserOrderService userOrederService;
	
	public CompleteController(UserOrderService userOrederService) {
		this.userOrederService = userOrederService;
	}
	
	/* Get通信
	 * メソッド名：toComplete()
	 * 引数：＊＊
	 * 戻り値：＊＊
	 * 動作詳細：注文完了のGet通信、注文テーブルから最新の1件を取得
	 */
	
	@GetMapping(value = "/complete")
	public String toComplete(HttpSession session, Model model) {
		//カート情報のクリア
		session.removeAttribute("cartList");
		
		//表示用の注文ID取得
		//String order_id = userOrederService.searchOrder();
		String order_id = "【注文ID入るよ】";
		
		//注文IDをモデルに登録
		model.addAttribute("order_id", order_id);
		
		//注文時間をモデルに登録
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");
		String orderTime = fomatter.format(time);
		model.addAttribute("orderTime", orderTime);
		
		return "complete";
	}
	
	/* Post通信
	 * メソッド名：toFinish()
	 * 引数：＊＊
	 * 戻り値：＊＊
	 * 動作詳細：注文完了後、メインメニューに戻る、ログアウトなど分岐
	 */
	
	@PostMapping(value = "complete" , params="top")
	public String toFinish(HttpSession session, Model model) {
		//ログインセッションを保持したままメインメニューに戻る
		
		return "/user/main";
	}
	
	@PostMapping(value = "complete" , params="logout")
	public String tologoutFinish(HttpSession session, Model model,SessionStatus status) {
	//ログアウトして会員メインメニューに戻る
		//sessionアノテーションのオブジェクトの削除
		status.setComplete();
		
		return "redirect:/user/main";
	}
}
