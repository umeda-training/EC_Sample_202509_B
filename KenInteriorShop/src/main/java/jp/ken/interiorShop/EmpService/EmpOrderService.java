package jp.ken.interiorShop.EmpService;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.EmpRepository.EmpOrderRepository;
import jp.ken.interiorShop.presentation.EmpFormModel.EmpOrderFormModel;

//担当：濱邊
//注文情報検索のロジックを持つサービスクラス
@Service
public class EmpOrderService {
	
	private EmpOrderRepository empOrderRepository;

	public EmpOrderService(EmpOrderRepository empOrderRepository) {
		this.empOrderRepository = empOrderRepository;
	}
	
	//注文一覧検索
	public List<EmpOrderFormModel> searchOrder() throws Exception{
		return empOrderRepository.getOrderList();
	}
	
	//住所変更
	public void updateOrder(int orderId, String newAddress) throws Exception{
		empOrderRepository.updateAddress(orderId, newAddress);
	}
	
	//注文取消し
	

}
