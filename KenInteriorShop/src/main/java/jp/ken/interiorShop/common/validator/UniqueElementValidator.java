package jp.ken.interiorShop.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jp.ken.interiorShop.common.annotation.UniqueEmail;

/*
 * 作成 : 西村
 */
public class UniqueElementValidator implements ConstraintValidator<UniqueEmail, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// メールアドレスのスペース入力を除外
		value = value.replaceAll(" ", "").replaceAll("　", "");
		// 会員テーブル検索
//		UserEntity user = UserAddRepository.searchByMail(value).orElse(null);
//		if(user != null) {
//			return false;
//		}
		return true;
	}

}
