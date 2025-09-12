package jp.ken.interiorShop.domain.repository;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;

/*
 * 作成者：竹内
 * 完成後、UserOrderRepositoryクラスに統合
 */
public class UserOrderRepository2 {
private JdbcTemplate jdbcTemplate;
	
	public UserOrderRepository2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public UserOrderEntity getOrder() throws SQLException{
		//SQL結果格納用
		UserOrderEntity userOrderEntity = new UserOrderEntity();
		
		//SQL作成
		StringBuilder sb = new StringBuilder();
		
		return userOrderEntity;
	}
}
