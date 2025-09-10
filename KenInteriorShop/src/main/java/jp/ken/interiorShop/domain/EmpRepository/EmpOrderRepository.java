package jp.ken.interiorShop.domain.EmpRepository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.presentation.EmpFormModel.EmpOrderFormModel;

//担当：濱邊
//注文管理に関するDBアクセスを担当するリポジトリクラス

@Repository
public class EmpOrderRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	//注文一覧取得用メソッド
	public List<EmpOrderFormModel> getOrderList() throws Exception{
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" o.order_id,");
		sb.append(" o.user_name,");
		sb.append(" o.order_date,");
		sb.append(" o.order_address,");
		sb.append(" d.item_cd,");
		sb.append(" d.quantity");
		sb.append("FROM orders o");
		sb.append("INNER JOIN order_details d ON o.order_id = d.order_id");
		sb.append("ORDER BY o.order_id");
		String sql = sb.toString();
		
		//BeanPropertyRowMapper<>で、SQLの結果セット（ResultSet）をJavaのオブジェクトに自動でマッピング
		RowMapper<EmpOrderFormModel> mapper = new BeanPropertyRowMapper<>(EmpOrderFormModel.class);
		//データベースから複数行の結果を取得する
		List<EmpOrderFormModel> orderList = jdbcTemplate.query(sql,mapper);
		
		return orderList;
	}
	

}
