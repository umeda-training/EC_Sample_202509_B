package jp.ken.interiorShop.domain.EmpRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//担当：濱邊
//注文管理に関するDBアクセスを担当するリポジトリクラス

@Repository
public class EmpOrderRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	//注文一覧取得用メソッド
	/*
	public List<UserOrderFormModel> getOrderList() throws Exception{
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append("SELECT");
		sb.append("SELECT");
		sb.append("SELECT");
		sb.append("SELECT");
		sb.append("SELECT");
		sb.append("SELECT");
		
		
		//List<EmpOrderFormModel> orderList = jdbcTemplate.query(sql,)
		
		return 
	}
	*/
	

}
