package jp.ken.interiorShop.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.entity.UserLoginEntity;
import jp.ken.interiorShop.domain.repository.UserLoginRepository;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;

@Service
public class UserLoginService {

	private UserLoginRepository userLoginRepository;
	private ModelMapper modelMapper;
	
	public UserLoginService(UserLoginRepository userLoginRepository, ModelMapper modelMapper) {
		this.userLoginRepository = userLoginRepository;
		this.modelMapper = modelMapper;
	}
	
	// ログインメソッド(メールアドレス＆パスワードで検索)
	public List<UserLoginFormModel> getLogin(UserLoginFormModel form) throws Exception {
		List<UserLoginEntity> entityList = null;
		List<UserLoginFormModel> formList = null;
		
		String logMail = form.getLoginMail();
		String logPass = form.getLoginPass();

		entityList = userLoginRepository.getUser(logMail, logPass);
		formList = convert(entityList);
		
		return formList;
	}
	
	private List<UserLoginFormModel> convert(List<UserLoginEntity> entityList) {
		List<UserLoginFormModel> formList = new ArrayList<UserLoginFormModel>();
		
		for(UserLoginEntity entity : entityList) {
			UserLoginFormModel form = modelMapper.map(entity, UserLoginFormModel.class);
			formList.add(form);
		}
		return formList;
	}
}
