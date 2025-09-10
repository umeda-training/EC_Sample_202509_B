package jp.ken.interiorShop.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.UserInfoEntity;

/*
 * 作成 : 西村
 */
public class UserInfoMapper implements RowMapper<UserInfoEntity> {

	@Override
	public UserInfoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		
		userInfoEntity.setUserId(rs.getInt("user_id"));
		userInfoEntity.setUserName(rs.getString("user_name"));
		userInfoEntity.setUserKana(rs.getString("user_kana"));
		userInfoEntity.setUserGender(rs.getString("user_gender"));
		userInfoEntity.setUserBirth(rs.getDate("user_birth"));
		userInfoEntity.setUserPost(rs.getString("user_post"));
		userInfoEntity.setUserAddress(rs.getString("user_address"));
		userInfoEntity.setUserPhone(rs.getString("user_phone"));
		userInfoEntity.setUserMail(rs.getString("user_mail"));
		userInfoEntity.setUserPass(rs.getString("user_password"));
		
		return userInfoEntity;
	}

}
