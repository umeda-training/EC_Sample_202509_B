package jp.ken.interiorShop.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;
import jp.ken.interiorShop.domain.repository.UserOrderRepository;
import jp.ken.interiorShop.presentation.formmodel.CartFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserOrderFormModel;

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
	 * 注文が確定した時のorderテーブルに登録をする
	 */
	@Transactional(rollbackFor = Exception.class)
	//登録するだけだからvoid
	public void registUserOrder (UserOrderFormModel userOrderForm, List<CartFormModel> cartList) throws Exception {
		
		//フォームから受け取った内容を、Entityにコピー
		UserOrderEntity userOrderEntity = formMapper.map(userOrderForm, UserOrderEntity.class);
		
		// 注文日をセット
	    userOrderEntity.setOrderDate(LocalDate.now());
	    //ordersテーブルに登録する処理
		int orderId = userOrderRepository.insertOrder(userOrderEntity);	
		
		//order_detailsテーブルに登録する処理		

		for(int i = 0; i < cartList.size(); i++) {
			CartFormModel cart = cartList.get(i);
			String itemCd = cart.getSelectItem();
			int quantity = cart.getBuyAmount();
			userOrderRepository.insertOrderDetail(orderId, itemCd, quantity);
		}
		
		//userOrderRepository.insertOrderDetail(orderId, itemCd, quantity);
	}

}
