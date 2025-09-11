package jp.ken.interiorShop.domain.EmpRepository;

/*
松田


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.EmpInfoEntity;
import jp.ken.interiorShop.domain.mapper.EmpInfoMapper;

public class EmpLoginRepository {

	
	private RowMapper<EmpInfoEntity> empInfoMapper = new EmpInfoMapper();
	private JdbcTemplate jdbcTemplate;
	
	public EmpLoginRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
		// 従業員情報検索メソッド(ログイン処理)
	public EmpInfoEntity getEmp(String EmpId, String pass) throws Exception {
		StringBuilder sb = createCommonSQL();
		sb.append(" WHERE");
		sb.append(" emp_id = ?");
		sb.append(" AND");
		sb.append(" emp_password = ?;");
		String sql = sb.toString();
		
		List<EmpInfoEntity> entityList = jdbcTemplate.query(sql, empInfoMapper, EmpId, pass);
		// 検索結果が1件であればその結果を返す。それ以外はnullを返す
		if(entityList.size() == 1) {
			return entityList.getFirst();
		} else {
			return new EmpInfoEntity();
		}
		
	}
		
	
	// 検索SQL共通部分作成
	private StringBuilder createCommonSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" emp_name,");
		sb.append(" emp_kana,");
		sb.append(" emp_id,");
		sb.append(" emp_password");
		sb.append(" FROM");
		sb.append(" emp");
		
		return sb;
	}
	
}

*/
