package jp.ken.interiorShop.domain.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.UserInfoEntity;

//作成 : 西村
//会員テーブル 変更/削除
@Repository
public class UserInfoRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserInfoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int updateUser(UserInfoEntity userinfoEntity) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE");
		sb.append(" users");
		sb.append(" SET");
		sb.append(" user_name = ?");
		sb.append(", user_kana = ?");
		sb.append(", user_post = ?");
		sb.append(", user_address = ?");
		sb.append(", user_phone = ?");
		sb.append(", user_mail = ?");
		sb.append(", user_password = ?");
		sb.append(" WHERE");
		sb.append(" user_id = ?");
		String sql = sb.toString();
		
		Object[] parameters = { userinfoEntity.getUserName(), userinfoEntity.getUserKana(),
				userinfoEntity.getUserPost(), userinfoEntity.getUserAddress(), userinfoEntity.getUserPhone(),
				userinfoEntity.getUserMail(), userinfoEntity.getUserPass(), userinfoEntity.getUserId() };
		
		int numberOfRow = 0;
		numberOfRow = jdbcTemplate.update(sql, parameters);
		
		return numberOfRow;
	}

}
