package com.uday;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<User> listusers = service.listAll();
		model.addAttribute("listusers", listusers);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewuserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "new_user";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveuser(@ModelAttribute("user") User user) {
		service.save(user);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEdituserPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		User user = service.get(id);
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteuser(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
