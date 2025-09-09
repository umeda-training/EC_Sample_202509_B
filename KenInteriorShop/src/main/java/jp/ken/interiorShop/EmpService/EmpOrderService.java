package jp.ken.interiorShop.EmpService;

import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.EmpRepository.EmpOrderRepository;

//担当：濱邊
//注文情報検索のロジックを持つサービスクラス
@Service
public class EmpOrderService {
	private EmpOrderRepository empOrderRepository;
	//private ModelMapper modelMapper;
	
	/*
	public List<EmpOrderFormModel> searchOrder() throws Exception{
		
		return empOrderRepository.getOrderList();
	}
	*/

}
