package jp.ken.interiorShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.entity.UserInfoEntity;
import jp.ken.interiorShop.domain.repository.UserSearchRepository;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;

//作成 : 西村
//会員テーブル検索
@Service
public class UserSearchService {

	private UserSearchRepository userSearchRepository;
	private ModelMapper modelMapper;
	
	public UserSearchService(UserSearchRepository userSearchRepository, ModelMapper modelMapper) {
		this.userSearchRepository = userSearchRepository;
		this.modelMapper = modelMapper;
	}
	
	// ログインメソッド(メールアドレス＆パスワードで検索)
	public UserLoginFormModel getLogin(UserLoginFormModel form) throws Exception {
		UserInfoEntity entity = null;
//		List<UserLoginFormModel> formList = null;
		
		String logMail = form.getLoginMail();
		String logPass = form.getLoginPass();
		
		entity = userSearchRepository.getUser(logMail, logPass);
		return modelMapper.map(entity, UserLoginFormModel.class);
	}
	
//	private List<UserLoginFormModel> convert(List<UserInfoEntity> entityList) {
//		List<UserLoginFormModel> formList = new ArrayList<UserLoginFormModel>();
//		
//		for(UserInfoEntity entity : entityList) {
//			UserLoginFormModel form = modelMapper.map(entity, UserLoginFormModel.class);
//			formList.add(form);
//		}
//		return formList;
//	}

}
