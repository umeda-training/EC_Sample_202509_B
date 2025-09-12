package jp.ken.interiorShop.domain.EmpRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.presentation.EmpFormModel.EmpOrderDetailsFormModel;
import jp.ken.interiorShop.presentation.EmpFormModel.EmpOrderFormModel;


//担当：濱邊
//注文管理に関するDBアクセスを担当するリポジトリクラス
@Repository
public class EmpOrderRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public EmpOrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	 /**
     * 注文一覧を取得する
     */
    public List<EmpOrderFormModel> getOrderList() {
        String sql = "SELECT order_id, user_name, order_address, order_date FROM orders ORDER BY order_id";

        //SQLクエリを実行して、結果セット（ResultSet）を1行ずつ処理する
        List<EmpOrderFormModel> orderList = jdbcTemplate.query(sql, (rs, rowNum) -> {
        	EmpOrderFormModel order = new EmpOrderFormModel();
            order.setOrderId(rs.getInt("order_id"));
            order.setUserName(rs.getString("user_name"));
            order.setAddress(rs.getString("order_address"));
            order.setOrderDate(rs.getDate("order_date"));
            return order;
        });

        // 注文ごとの商品明細を追加で取得してセット
        for (EmpOrderFormModel order : orderList) {
            List<EmpOrderDetailsFormModel> details = jdbcTemplate.query(
                "SELECT item_cd, quantity FROM order_details WHERE order_id = ?",
                (rs, rowNum) -> {
                	EmpOrderDetailsFormModel detail = new EmpOrderDetailsFormModel();
                    detail.setItemCd(rs.getString("item_cd"));
                    detail.setQuantity(rs.getInt("quantity"));
                    return detail;
                },
                order.getOrderId()
            );
            order.setOrder_details(details);
        }
        return orderList;
    }
    
    /*
     * 検索された注文一覧を取得する
     */
     public List<EmpOrderFormModel> getOrderList(int orderId, String userName, Date orderDate){
    	 StringBuilder sb = new StringBuilder();
    	 sb.append("SELECT order_id, user_name, order_address, order_date FROM orders WHERE 1=1");
    	 List<Object> params = new ArrayList<>();
    	 
    	 if(orderId != 0) {
    		 sb.append(" AND order_id = ?");
    		 params.add(orderId);
    	 }
    	 
    	 if(userName != null && !userName.isEmpty()) {
    		 sb.append(" AND user_name = ?");
    		 params.add(userName);
    	 }
    	 
    	 if(orderDate != null) {
    		 sb.append(" AND order_date = ?");
    		 params.add(orderDate);
    	 }
    	
    	//SQLクエリを実行して、結果セット（ResultSet）を1行ずつ処理する
         @SuppressWarnings("deprecation")
         List<EmpOrderFormModel> orderList = jdbcTemplate.query(
        		 sb.toString(),
        		 params.toArray(),
        		 (rs, rowNum) -> {
		         	EmpOrderFormModel order = new EmpOrderFormModel();
		            order.setOrderId(rs.getInt("order_id"));
		            order.setUserName(rs.getString("user_name"));
		            order.setAddress(rs.getString("order_address"));
		            order.setOrderDate(rs.getDate("order_date"));
		            return order;
		         }
        );

         // 注文ごとの商品明細を追加で取得してセット
        for (EmpOrderFormModel order : orderList) {
            List<EmpOrderDetailsFormModel> details = jdbcTemplate.query(
                "SELECT item_cd, quantity FROM order_details WHERE order_id = ?",
                (rs, rowNum) -> {
                	EmpOrderDetailsFormModel detail = new EmpOrderDetailsFormModel();
                    detail.setItemCd(rs.getString("item_cd"));
                    detail.setQuantity(rs.getInt("quantity"));
                    return detail;
                },
                order.getOrderId()
            );
            order.setOrder_details(details);
        }
        
        return orderList;
    }



    /**
     * 注文の住所を更新する
     */
    public void updateOrder(int orderId, String newAddress) {
        String sql = "UPDATE orders SET order_address = ? WHERE order_id = ?";
        jdbcTemplate.update(sql, newAddress, orderId);
    }
    
	/*
	 * 注文を取り消す
	 */
    public void deleteOrder(int orderId) {
    	//注文詳細テーブル
    	jdbcTemplate.update("DELETE FROM order_details WHERE order_id = ?", orderId);
    	//注文テーブル
    	jdbcTemplate.update("DELETE FROM orders WHERE order_id = ?", orderId);
    }
    
	/*	
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
	*/
	

}
