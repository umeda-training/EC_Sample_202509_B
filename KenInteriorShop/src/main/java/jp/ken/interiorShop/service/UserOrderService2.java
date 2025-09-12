package jp.ken.interiorShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;
import jp.ken.interiorShop.domain.repository.UserOrderRepository;

/*
 * 作成者：竹内
 * 完成後UserOrederService2に貼り付け
 * 
 */
@Service
public class UserOrderService2 {
	
	private UserOrderRepository userOrderRepository;
	private ModelMapper formMapper;
	
	
	public UserOrderService2(UserOrderRepository userOrderRepository, ModelMapper formMapper) {
		this.userOrderRepository = userOrderRepository;
		this.formMapper = formMapper;
	}
	
	public int searchOrder() {
		//結果の注文ID格納用
		int order_id;
		
		//最新注文データを検索し、注文IDを抽出
		UserOrderEntity userOrderEntity = new UserOrderEntity();
		//userOrderEntity = userOrderRepository.getOrder();
		order_id = userOrderEntity.getOrderId();
		
		return order_id;
	}
}
