
package jp.ken.interiorShop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.UserOrderEntity;

//担当：内川
public class UserOrderMapper implements RowMapper<UserOrderEntity>{
	
	@Override
	public UserOrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserOrderEntity userOrderEntity = new UserOrderEntity();
		
		userOrderEntity.setOrderId(rs.getInt("order_id"));
		userOrderEntity.setUserId(rs.getInt("user_id"));
		userOrderEntity.setUserName(rs.getString("user_name"));
		userOrderEntity.setTotal(rs.getInt("total"));
		userOrderEntity.setUserAddress(rs.getString("user_address"));
		userOrderEntity.setUserPost(rs.getInt("user_post"));
		
		return userOrderEntity;
	}	
}