package jp.ken.interiorShop.domain.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.UserInfoEntity;
import jp.ken.interiorShop.domain.mapper.UserInfoMapper;

//作成 : 西村
//会員テーブル検索
@Repository
public class UserSearchRepository {

	private RowMapper<UserInfoEntity> userInfoMapper = new UserInfoMapper();
	private JdbcTemplate jdbcTemplate;
	
	public UserSearchRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// 会員情報検索メソッド(ログイン処理)
	public UserInfoEntity getUser(String mail, String pass) throws Exception {
		StringBuilder sb = createCommonSQL();
		sb.append(" WHERE");
		sb.append(" user_mail = ?");
		sb.append(" AND");
		sb.append(" user_password = ?;");
		String sql = sb.toString();
		
		List<UserInfoEntity> entityList = jdbcTemplate.query(sql, userInfoMapper, mail, pass);
		// 検索結果が1件であればその結果を返す。それ以外はnullを返す
		if(entityList.size() == 1) {
			return entityList.getFirst();
		} else {
			return new UserInfoEntity();
		}
		
	}
	
	// 会員情報検索メソッド(メールアドレス重複確認)
//	public UserInfoEntity getUserByMail(String mail) throws Exception {
//		StringBuilder sb = createCommonSQL();
//		sb.append(" WHERE");
//		sb.append(" user_mail = ?");
//		String sql = sb.toString();
//		
//		List<UserInfoEntity> entityList =  jdbcTemplate.query(sql, userInfoMapper, mail);
//		// 検索結果が1件であればその結果を返す。それ以外はnullを返す
//		if(entityList.size() == 1) {
//			return entityList.getFirst();
//		} else {
//			return null;
//		}
//	}
	
	// 会員情報検索メソッド(メールアドレス重複確認)
	public int chkUserByMail(String mail) throws Exception {
		StringBuilder sb = createCommonSQL();
		sb.append(" WHERE");
		sb.append(" user_mail = ?");
		String sql = sb.toString();
		
		List<UserInfoEntity> entityList =  jdbcTemplate.query(sql, userInfoMapper, mail);
		// 検索結果の件数を返す
		return entityList.size();
	}
	
	// 検索SQL共通部分作成
	private StringBuilder createCommonSQL() {
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
		
		return sb;
	}

}
