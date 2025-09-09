
package jp.ken.interiorShop.domain.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;
import jp.ken.interiorShop.domain.mapper.UserOrderMapper;

@Repository
public class UserOrderRepository {
	
	private RowMapper<UserOrderEntity> userOrderMapper = new UserOrderMapper();
	private JdbcTemplate jdbcTemplate;
	
	public UserOrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//ordersテーブルに注文登録
	public String insertOrder() throws Exception{
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO orders");
		sb.append("(");
		sb.append("user_id");
		sb.append(", user_name");
		sb.append(", total");
		sb.append(", order_date");
		sb.append(", order_post");
		sb.append(", order_address");
		sb.append(")");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?)");
		String sql = sb.toString();
		
		//jdbcTemplate.query(sql, );
		
		return null;
	}
	

}

