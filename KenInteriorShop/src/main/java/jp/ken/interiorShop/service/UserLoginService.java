package jp.ken.interiorShop.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.entity.UserInfoEntity;
import jp.ken.interiorShop.domain.repository.UserSearchRepository;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;

/*
 * 作成 : nishimura
 */
@Service
public class UserLoginService {

	private UserSearchRepository userSearchRepository;
	private ModelMapper modelMapper;
	
	public UserLoginService(UserSearchRepository userSearchRepository, ModelMapper modelMapper) {
		this.userSearchRepository = userSearchRepository;
		this.modelMapper = modelMapper;
	}
	
	// ログインメソッド(メールアドレス＆パスワードで検索)
	public List<UserLoginFormModel> getLogin(UserLoginFormModel form) throws Exception {
		List<UserInfoEntity> entityList = null;
		List<UserLoginFormModel> formList = null;
		
		String logMail = form.getLoginMail();
		String logPass = form.getLoginPass();

		entityList = userSearchRepository.getUser(logMail, logPass);
		formList = convert(entityList);
		
		return formList;
	}
	
	private List<UserLoginFormModel> convert(List<UserInfoEntity> entityList) {
		List<UserLoginFormModel> formList = new ArrayList<UserLoginFormModel>();
		
		for(UserInfoEntity entity : entityList) {
			UserLoginFormModel form = modelMapper.map(entity, UserLoginFormModel.class);
			formList.add(form);
		}
		return formList;
	}
}
