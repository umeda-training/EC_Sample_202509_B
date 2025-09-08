package jp.ken.interiorShop.domain.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.UserLoginEntity;
import jp.ken.interiorShop.domain.mapper.UserLoginMapper;

@Repository
public class UserLoginRepository {

	private RowMapper<UserLoginEntity> userLoginMapper = new UserLoginMapper();
	private JdbcTemplate jdbcTemplate;
	
	public UserLoginRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// 会員情報検索メソッド(ログイン処理)
	public List<UserLoginEntity> getUser(String mail, String pass) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" user_id,");
		sb.append(" user_name,");
		sb.append(" user_kana,");
		sb.append(" user_gender,");
		sb.append(" user_birth,");
		sb.append(" user_post,");
		sb.append(" user_address,");
		sb.append(" user_phone,");
		sb.append(" user_mail,");
		sb.append(" user_password");
		sb.append(" FROM");
		sb.append(" users");
		sb.append(" WHERE");
		sb.append(" user_mail = ?");
		sb.append(" AND");
		sb.append(" user_password = ?");
		String sql = sb.toString();
		
		return jdbcTemplate.query(sql, userLoginMapper, mail, pass);
		
	}
}
