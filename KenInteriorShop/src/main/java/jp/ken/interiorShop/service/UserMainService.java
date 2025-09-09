package jp.ken.interiorShop.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.repository.UserMainRepository;
import jp.ken.interiorShop.presentation.formmodel.ItemModel;

//担当者：竹内
@Service
public class UserMainService {
	 
	private UserMainRepository userMainRepository;
	
	//コンストラクタインジェクション
	public UserMainService(UserMainRepository userMainRepository) {
		this.userMainRepository = userMainRepository;
	}
	
	/*
	 * 全件検索用
	 * メソッド名：search()
	 * 引数無し
	 * 戻り値：List<itemModel>型の検索結果リスト
	 * repositoryクラスでテーブルのリストを全て取得
	 */
	public List<ItemModel> search() throws SQLException{
		//検索結果格納先
		List<ItemModel> item_list = null;
		
		//repositoryの全件検索メソッド呼び出し
		//item_list = userMainRepository.getItemList();
		
		return item_list;
	}
	
	/*次月販売予定商品検索用
	 * メソッド名：searchNextItems()
	 * 引数：システム日付の翌月
	 * 戻り値：List<itemModel>型の検索結果リスト
	 * repositoryクラスでitemテーブルにある翌月の商品リストを取得
	 */
	public List<ItemModel> searchNextItems(int next_month) throws SQLException{
		//検索結果格納先
		List<ItemModel> item_list = null;
		
		//repositoryの次月商品検索メソッド呼び出し
		//item_list = userMainRepository.getNextItemList(next_month);
		
		
		return item_list;
	}
	/*単語検索用
	 * メソッド名：search()　※オーバーロード
	 * 引数：カテゴリボタンの文字列、検索ワード
	 * 戻り値：List<itemModel>型の検索結果リスト
	 * repositoryクラスでitemテーブルにある検索ワードで該当する商品リストを取得
	 */
	public List<ItemModel> search(String category,String search_word){
		//検索結果格納先
		List<ItemModel> item_list = null;
		
		//repositoryのワード検索メソッド呼び出し
		//item_list = userMainRepository.getItemList(category, search_word);
		
		return item_list;
	}
	
	/*カテゴリ検索用
	 * メソッド名：search()　※オーバーロード
	 * 引数：カテゴリボタンの文字列
	 * 戻り値：：List<itemModel>型の検索結果リスト
	 * repositoryクラスでitemテーブルにある検索カテゴリで該当するリストを取得
	 * ※※※※※※※※必要か模索中※※※※※※※※※
	 */
	
	
	/*絞り込み検索用
	 * メソッド名：search()
	 * 引数：カテゴリボタンの文字列、検索ワード
	 * 戻り値：List<itemModel>型の検索結果リスト
	 * repositoryクラスでitemテーブルにある検索、絞り込みで該当する商品リストを取得
	 */
	public List<ItemModel> search(String category, String search_word, String first_filter_num, String last_filter_num){
		//検索結果格納先
		List<ItemModel> item_list = null;
		
		//絞り込み金額をint型に変換
		int low_value = Integer.parseInt(first_filter_num);
		int high_value = Integer.parseInt(last_filter_num);
		
		
		//repositoryクラスの詳細検索メソッド呼び出し
		//item_list = userMainRepository.getItemList(category, search_word, low_value, high_value);
		
		return item_list;
	}
}
