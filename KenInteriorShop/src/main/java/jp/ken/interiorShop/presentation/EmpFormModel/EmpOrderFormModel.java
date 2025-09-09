package jp.ken.interiorShop.presentation.EmpFormModel;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;


//担当：濱邊
@Data
public class EmpOrderFormModel implements Serializable {
	//注文ID
	private int order_Id;
	
	//注文氏名
	private String user_name;
	
	//注文日
	private Date order_date;
	
	//住所
	private String order_address;
	
	//注文内容
	private int total;
	
	
	
	

}
