package jp.ken.interiorShop.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.ken.interiorShop.common.annotation.UniqueEmail;
import jp.ken.interiorShop.domain.entity.UserInfoEntity;
import jp.ken.interiorShop.domain.repository.UserSearchRepository;

/*
 * 作成 : 西村
 * メールアドレス重複チェック
 */
public class UniqueElementValidator implements ConstraintValidator<UniqueEmail, String> {

	private UserSearchRepository userSearchRepository;
	
	public UniqueElementValidator(UserSearchRepository userSearchRepository) {
		this.userSearchRepository = userSearchRepository;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// メールアドレスのスペース入力を除外
		value = value.replaceAll(" ", "").replaceAll("　", "");
		// 会員テーブル検索
		UserInfoEntity user = null;
		try {
			user = userSearchRepository.getUserByMail(value);
		} catch (Exception e) {
			System.out.println("メールアドレス重複チェックで例外発生");
			return false;
		}
		if(user != null) {
			// 同じメールアドレスが既に存在する場合
			return false;
		}
		return true;
	}

}
