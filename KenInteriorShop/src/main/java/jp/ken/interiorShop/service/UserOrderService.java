package jp.ken.interiorShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.repository.UserOrderRepository;

//担当：内川
@Service
public class UserOrderService {
	
	private UserOrderRepository userOrderRepository;
	private ModelMapper formMapper;
	
	
	public UserOrderService(UserOrderRepository userOrderRepository, ModelMapper formMapper) {
		this.userOrderRepository = userOrderRepository;
		this.formMapper = formMapper;
	}
	/*
	 * 注文が確定した時の処理
	 */
	
	


}
