package jp.ken.interiorShop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.ItemEntity;

public class UserItemMapper implements RowMapper<ItemEntity> {
	
	@Override
	public ItemEntity mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		ItemEntity itemEntity = new ItemEntity();
		
		itemEntity.setItemCd(rs.getString("item_cd"));
		itemEntity.setItemName(rs.getString("item_name"));
		itemEntity.setItemStock(rs.getInt("item_stock"));
		itemEntity.setItemPrice(rs.getInt("item_price"));
		itemEntity.setItemCategory(rs.getString("item_category"));
		itemEntity.setItemInfo(rs.getString("item_info"));
		itemEntity.setReleaseDate(rs.getDate("release_date"));
		
		return itemEntity;
	}

}
