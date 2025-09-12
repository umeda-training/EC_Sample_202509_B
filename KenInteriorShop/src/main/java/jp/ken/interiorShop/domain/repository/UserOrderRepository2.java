package jp.ken.interiorShop.domain.repository;

import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;
import jp.ken.interiorShop.domain.mapper.UserOrderMapper;

/*
 * 作成者：竹内
 * 完成後、UserOrderRepositoryクラスに統合
 */
public class UserOrderRepository2 {
private JdbcTemplate jdbcTemplate;
private RowMapper<UserOrderEntity> userOrderMapper = new UserOrderMapper(); 
//ここは追加しないとあかん↑↑↑

	public UserOrderRepository2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public UserOrderEntity getOrder() throws SQLException{
		//SQL結果格納用
		UserOrderEntity userOrderEntity = new UserOrderEntity();
		
		//SQL作成
		StringBuilder sb = new StringBuilder();
		//暫定SQL
		sb.append("SELECT");
		sb.append(" *");
		sb.append(" FROM");
		sb.append(" orders");
		sb.append(" WHERE");
		sb.append(" oreder_id =");
		sb.append("(SELECT MAX");
		sb.append("(order_id)");
		sb.append(" FROM");
		sb.append(" orders");
		
		String sql = sb.toString();
		
		userOrderEntity = jdbcTemplate.queryForObject(sql, userOrderMapper);
				
		return userOrderEntity;
	}
}
