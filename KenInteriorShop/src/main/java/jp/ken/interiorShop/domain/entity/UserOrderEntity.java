package jp.ken.interiorShop.domain.entity;


import java.time.LocalDateTime;

import lombok.Data;
//担当：内川
@Data
public class UserOrderEntity {

	private int orderId;           // 注文ID（DBでオートインクリメント）
	private int userId;            // 顧客ID
	private String userName;      //注文者名

	private int total;              // 合計金額
	private LocalDateTime orderDate; // 注文日

    private int userPost;       // 郵便番号
    private String userAddress;    // 住所

}
