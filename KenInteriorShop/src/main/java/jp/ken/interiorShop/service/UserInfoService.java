package jp.ken.interiorShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ken.interiorShop.domain.entity.UserInfoEntity;
import jp.ken.interiorShop.domain.repository.UserInfoRepository;
import jp.ken.interiorShop.presentation.formmodel.UserInfoFormModel;

//作成 : 西村
//会員情報 変更・削除
@Service
public class UserInfoService {

	private UserInfoRepository userInfoRepository;
	private ModelMapper modelMapper;
	
	public UserInfoService(UserInfoRepository userInfoRepository, ModelMapper modelMapper) {
		this.userInfoRepository = userInfoRepository;
		this.modelMapper = modelMapper;
	}
	
	// 会員情報 変更
	@Transactional(rollbackFor = Exception.class)
	public int updateUser(UserInfoFormModel form) throws Exception {
		UserInfoEntity entity = null;
		
		entity = convert(form);
		
		int resultRow = userInfoRepository.updateUser(entity);
		
		return resultRow;
	}
	
	private UserInfoEntity convert(UserInfoFormModel form) {
		
		UserInfoEntity entity = modelMapper.map(form, UserInfoEntity.class);
		
		return entity;
	}

}
