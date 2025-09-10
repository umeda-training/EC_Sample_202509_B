package jp.ken.interiorShop.presentation.EmpFormModel;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import lombok.Data;


//担当：濱邊
@Data
public class EmpOrderFormModel implements Serializable {
	//注文ID
	private int orderId;
	
	//注文氏名
	private String userName;
	
	//注文日
	private Date orderDate;
	
	//住所
	private String address;
	
	//注文内容(注文詳細：商品名×個数)
	private List<EmpOrderDetailsFormModel> order_details;
	
}
