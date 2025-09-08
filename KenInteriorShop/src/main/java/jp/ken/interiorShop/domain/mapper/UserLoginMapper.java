package jp.ken.interiorShop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.UserLoginEntity;

public class UserLoginMapper implements RowMapper<UserLoginEntity>  {

	@Override
	public UserLoginEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		
		userLoginEntity.setLoginId(rs.getInt("user_id"));
		userLoginEntity.setLoginName(rs.getString("user_name"));
		userLoginEntity.setLoginKana(rs.getString("user_kana"));
		userLoginEntity.setLoginGender(rs.getString("user_gender"));
		userLoginEntity.setLoginBirth(rs.getDate("user_birth"));
		userLoginEntity.setLoginPost(rs.getString("user_post"));
		userLoginEntity.setLoginAddress(rs.getString("user_address"));
		userLoginEntity.setLoginPhone(rs.getString("user_phone"));
		userLoginEntity.setLoginMail(rs.getString("user_mail"));
		userLoginEntity.setLoginPass(rs.getString("user_password"));
		
		return userLoginEntity;
	}

}
