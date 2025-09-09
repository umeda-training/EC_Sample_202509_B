package jp.ken.interiorShop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.UserOrderDetailsEntity;

//担当：内川
public class UserOrderDetailsMapper implements RowMapper<UserOrderDetailsEntity>{
	
	@Override
	public UserOrderDetailsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserOrderDetailsEntity userOrderDetailsEntity = new UserOrderDetailsEntity();
		
		userOrderDetailsEntity.setOrderDetailId(rs.getInt("order_detail_id"));
		userOrderDetailsEntity.setOrderId(rs.getInt("order_id"));
		userOrderDetailsEntity.setItemCd(rs.getString("item_cd"));
		userOrderDetailsEntity.setQuantity(rs.getInt("quantity"));
		
		return userOrderDetailsEntity;
	}

}
