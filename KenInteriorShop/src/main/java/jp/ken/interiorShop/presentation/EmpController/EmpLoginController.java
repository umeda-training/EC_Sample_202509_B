package jp.ken.interiorShop.presentation.EmpController;

/*


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.ken.interiorShop.presentation.EmpFormModel.EmpLoginFormModel;
import jp.ken.interiorShop.presentation.formmodel.UserLoginFormModel;

@Controller
@RequestMapping(value="/emp/Login")
@SessionAttributes("EmpLoginForm")
public class EmpLoginController {
	
	@ModelAttribute("EmpLoginForm")
	public EmpLoginFormModel setupEmpLoginForm() {
		return new EmpLoginFormModel();
	}
	
	@GetMapping
	public String toEmpLogin() {
		EmpLoginFormModel empLoginForm = new EmpLoginFormModel();
		model.addAttribute("empLoginForm", empLoginForm);
		return "empLogin";
	}
	
	
	@PostMapping
	public String loginMembers(@Validated @ModelAttribute EmpLoginFormModel empLoginForm,
			BindingResult result, Model model) throws Exception {
		String btn = (String) model.getAttribute("btn");
		if(btn != null) {
			return "empAdd";
		}
	
		if(result.hasErrors()) {
			return "empLogin";
		} else {
			List<UserLoginFormModel> formList = empLoginService.getLogin(empLoginForm);
			
			if(formList == null || formList.isEmpty()) {
				model.addAttribute("errors", "従業員IDまたはパスワードが違います");
				return "empLogin";
			}
			
			empLoginForm.setLoginName(formList.getFirst().getLoginName());
			empLoginForm.setLoginKana(formList.getFirst().getLoginKana());
			empLoginForm.setLoginEmpId(formList.getFirst().getLoginEmpId());
			empLoginForm.setLoginPass(formList.getFirst().getLoginPass());
			model.addAttribute("EmpLoginForm", EmpLoginForm);
		}
		
	}
		
}

*/
