package jp.ken.interiorShop.domain.entity;


import java.time.LocalDateTime;

import lombok.Data;
//担当：内川
@Data
public class UserOrderEntity {

	// 注文ID（DBでオートインクリメント）
	private int orderId;
	
	// 顧客ID
	private int userId;
    //注文者名
	private String userName;

	//合計金額
	private int total;
	
	//注文日
	private LocalDateTime orderDate;

	//郵便番号
    private int userPost;
    
    //住所
    private String userAddress;
}
