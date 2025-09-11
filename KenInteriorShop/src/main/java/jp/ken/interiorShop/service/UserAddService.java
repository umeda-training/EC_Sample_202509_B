package jp.ken.interiorShop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.ken.interiorShop.domain.entity.UserAddEntity;
import jp.ken.interiorShop.domain.repository.UserAddRepository;
import jp.ken.interiorShop.presentation.formmodel.UserAddFormModel;

//作成 : 西村
//新規会員登録画面
@Service
public class UserAddService {

	private UserAddRepository userAddRepository;
	private ModelMapper modelMapper;
	
	public UserAddService(UserAddRepository userAddRepository, ModelMapper modelMapper) {
		this.userAddRepository = userAddRepository;
		this.modelMapper = modelMapper;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int addUser(UserAddFormModel form) throws Exception {
		UserAddEntity entity = null;
		
		entity = convert(form);
		
		int resultRow = userAddRepository.registUser(entity);
		
		return resultRow;
	}
	
	private UserAddEntity convert(UserAddFormModel form) {
		
		UserAddEntity entity = modelMapper.map(form, UserAddEntity.class);
		
		return entity;
	}

}
