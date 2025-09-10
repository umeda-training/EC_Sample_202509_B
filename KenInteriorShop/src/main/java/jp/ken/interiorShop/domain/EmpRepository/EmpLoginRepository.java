package jp.ken.interiorShop.domain.EmpRepository;

/*

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmpLoginRepository {

	
	private RowMapper<EmpLoginEntity> empLoginMapper = new empLoginMapper();
	private JdbcTemplate jdbcTemplate;
	
	public EmpLoginRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<EmpLoginEntity> getEmp(String empId, String pass) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" emp_name,");
		sb.append(" emp_kana,");
		sb.append(" emp_Id,");
		sb.append(" emp_password");
		sb.append(" FROM");
		sb.append(" emp");
		sb.append(" WHERE");
		sb.append(" emp_id = ?");
		sb.append(" AND");
		sb.append(" user_password = ?");
		String sql = sb.toString();
		
		return jdbcTemplate.query(sql, userLoginMapper, mail, pass);
		
	}
	
}

*/
