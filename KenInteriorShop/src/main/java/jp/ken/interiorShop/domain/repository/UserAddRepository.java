package jp.ken.interiorShop.domain.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.UserAddEntity;

//作成 : 西村
//会員テーブル 登録
@Repository
public class UserAddRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserAddRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int registUser(UserAddEntity userAddEntity) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" users");
		sb.append(" (");
		sb.append(" user_name,");
		sb.append(" user_kana,");
		sb.append(" user_gender,");
		sb.append(" user_birth,");
		sb.append(" user_post,");
		sb.append(" user_address,");
		sb.append(" user_phone,");
		sb.append(" user_mail,");
		sb.append(" user_password");
		sb.append(" )");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?, ?, ?, ?);");
		String sql = sb.toString();
		
		String birthDate = userAddEntity.getUserAddBirthYear() + "-" 
							+ userAddEntity.getUserAddBirthMonth() + "-"
							+ userAddEntity.getUserAddBirthDay();
		
		Object[] parameters = { userAddEntity.getUserAddName(), userAddEntity.getUserAddKana(),
				userAddEntity.getUserAddGender(), birthDate, userAddEntity.getUserAddPost(),
				userAddEntity.getUserAddAddress(), userAddEntity.getUserAddPhone(),
				userAddEntity.getUserAddMail(), userAddEntity.getUserAddPass() };
		
		int numberOfRow = 0;
		numberOfRow = jdbcTemplate.update(sql, parameters);
		
		return numberOfRow;
	}

}
