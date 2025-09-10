
package jp.ken.interiorShop.domain.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;
//担当：内川
@Repository
public class UserOrderRepository {
	
	//private RowMapper<UserOrderEntity> userOrderMapper = new UserOrderMapper();
	private JdbcTemplate jdbcTemplate;
	
	public UserOrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//ordersテーブルに注文登録
	public String insertOrder(UserOrderEntity userOrderEntity) throws Exception{
		
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
		
		jdbcTemplate.update(sql, 
				userOrderEntity.getUserId(), userOrderEntity.getUserName(), userOrderEntity.getTotal(), 
				userOrderEntity.getOrderDate(), userOrderEntity.getUserPost(), userOrderEntity.getUserAddress());
		
		return null;
	}
	
	//order_detailsテーブルに詳細登録
	public String insertOrederDetail(int orderId, int itemCd, int quantity) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO order_details");
		sb.append("(");
		sb.append("order_id");
		sb.append(", item_cd");
		sb.append(", quantity");
		sb.append(")");
		sb.append("VALUES");
		sb.append("(?, ?, ?)");
		String sql = sb.toString();
		
		jdbcTemplate.update(sql, orderId, itemCd, quantity);
		return null;
	}


}

