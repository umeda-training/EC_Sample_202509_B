package jp.ken.interiorShop.domain.repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jp.ken.interiorShop.domain.entity.ItemEntity;
import jp.ken.interiorShop.domain.mapper.UserItemMapper;

//担当者：竹内
/*repositoryクラス
 * 引数の検索条件に合わせてDBからデータ取得
 * 戻り値は、List<ItemModel>にする
 */
@Repository
public class UserMainRepository {
	
	private RowMapper<ItemEntity> userItemMapper = new UserItemMapper();
	private JdbcTemplate jdbcTemplate;
	
	//コンストラクタ
	public UserMainRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* 全件検索用
	 * メソッド名：getItemList()
	 * 引数：システム日付
	 * 戻り値：List<ItemModel>型の検索リスト
	 * 動作詳細：商品テーブルにあるシステム日付より前のもの取得
	 */
	
	public List<ItemEntity> getItemList(LocalDate date) throws SQLException{
		
		//引数の日にちを変換
		String searchDate = date.toString();
		//共通のSQL分の作成
		StringBuilder sb = createCommonSQL();
		
		//全件用の追加文
		sb.append(" WHERE");
		
		//既に発売されている条件(パラメータは引数の日にち) 
		sb.append(" release_date <= ?");
		
		//SQL文の統合
		String sql = sb.toString();
		
		//パラメータに本日の日にち挿入し、SQL分実行
		List<ItemEntity> itemList = jdbcTemplate.query(sql, userItemMapper, searchDate); 
		
		return itemList;
	}
	
	/* 共通部分のSQL文作成
	 * メソッド名：createCommonSQL()
	 * 引数：無し
	 * 戻り値：StringBuilder型の共通のSQL文
	 * 動作詳細：共通部分のSQL文作成
	 */
	
	private StringBuilder createCommonSQL() {
		
		//保管用のStringBuilderオブジェクト生成
		StringBuilder sb = new StringBuilder();
		
		//共通部分追加
		sb.append("SELECT");
		sb.append(" item_cd");
		sb.append(", item_name");
		sb.append(", item_stock");
		sb.append(", item_price");
		sb.append(", item_category");
		sb.append(", item_info");
		sb.append(", release_date");
		sb.append(" FROM");
		sb.append(" m_items");
		
		return sb;
	}
}
