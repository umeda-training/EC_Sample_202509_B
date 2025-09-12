package jp.ken.interiorShop.domain.mapper;

/*
松田
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.ken.interiorShop.domain.entity.EmpInfoEntity;

public class EmpInfoMapper implements RowMapper<EmpInfoEntity> {

	
	@Override
	public EmpInfoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpInfoEntity EmpInfoEntity = new EmpInfoEntity();
		
		EmpInfoEntity.setEmpName(rs.getString("emp_name"));
		EmpInfoEntity.setEmpKana(rs.getString("emp_kana"));
		EmpInfoEntity.setEmpId(rs.getString("emp_id"));
		EmpInfoEntity.setEmpPass(rs.getString("emp_password"));
		
		return EmpInfoEntity;
	}
	
}



