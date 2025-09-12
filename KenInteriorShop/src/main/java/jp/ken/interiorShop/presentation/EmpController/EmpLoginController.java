package jp.ken.interiorShop.presentation.EmpController;

/*
 松田
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.ken.interiorShop.EmpService.EmpLoginService;
import jp.ken.interiorShop.common.validator.groups.ValidGroupOrder;
import jp.ken.interiorShop.presentation.EmpFormModel.EmpLoginFormModel;

@Controller
@SessionAttributes("EmpLoginForm")
public class EmpLoginController {
	
		private EmpLoginService empLoginService;
	
	public EmpLoginController(EmpLoginService empLoginService) {
		this.empLoginService = empLoginService;
	}
	
	@ModelAttribute("EmpLoginForm")
	public EmpLoginFormModel setupEmpLoginForm() {
		return new EmpLoginFormModel();
	}
	
	@GetMapping(value = "/emp/login")
	public String toEmpLogin(SessionStatus status, Model model) {
		EmpLoginFormModel form = new EmpLoginFormModel();
		model.addAttribute("empLoginFormModel", form);
		return "empLogin";
	}
	
	
	@PostMapping(value = "/emp/login")
	public String loginMembers(@Validated(ValidGroupOrder.class) @ModelAttribute EmpLoginFormModel loginForm,
			BindingResult result, Model model) throws Exception {

		if(result.hasErrors()) {
			return "empLogin";
		} else {
			EmpLoginFormModel form = empLoginService.getLogin(loginForm);
			String tmpId = form.getEmpId();
			if(tmpId == null || tmpId.isEmpty()) {
				model.addAttribute("errors", "従業員IDまたはパスワードが違います");
				return "empLogin";
			}
			
			EmpLoginFormModel empLoginForm = new EmpLoginFormModel();
			empLoginForm.setEmpName(form.getEmpName());
			empLoginForm.setEmpKana(form.getEmpKana());
			empLoginForm.setEmpId(form.getEmpId());
			empLoginForm.setEmpPass(form.getEmpPass());
			model.addAttribute("EmpLoginForm", empLoginForm);
		}
		
		
		return "redirect:/emp/main";
	}
		
}

 
