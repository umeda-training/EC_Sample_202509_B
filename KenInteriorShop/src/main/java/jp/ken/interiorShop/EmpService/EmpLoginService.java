package jp.ken.interiorShop.EmpService;
/*
松田


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jp.ken.interiorShop.domain.EmpRepository.EmpLoginRepository;
import jp.ken.interiorShop.domain.entity.EmpInfoEntity;
import jp.ken.interiorShop.presentation.EmpFormModel.EmpLoginFormModel;

@Service
public class EmpLoginService {
	
	private EmpLoginRepository EmpLoginRepository;
	private ModelMapper modelMapper;
	
	public EmpLoginService(EmpLoginRepository EmpLoginRepository, ModelMapper modelMapper) {
		this.EmpLoginRepository = EmpLoginRepository;
		this.modelMapper = modelMapper;
	}
	
	public EmpLoginFormModel getLogin(EmpLoginFormModel form) throws Exception {
		EmpInfoEntity entity = null;
		
		String Id = form.getEmpId();
		String pass = form.getEmpPass();
		
		entity = EmpLoginRepository.getEmp(Id, pass);
		return modelMapper.map(entity, EmpLoginFormModel.class);
	}
	
}

*/
