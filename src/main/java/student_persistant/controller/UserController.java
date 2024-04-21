package student_persistant.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import student_persistant.dao.UserDaoImplement;
import student_persistant.model.UserModel;


@Component
@ComponentScan("student_persistant")
@Controller
public class UserController {
	
	@Autowired
	UserDaoImplement userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {

		return new ModelAndView ("login","user",new UserModel());
	}
	@RequestMapping(value = "/userSearch", method = RequestMethod.GET)
	public String userSearch(HttpServletRequest request,HttpServletResponse response)throws IOException {
		UserDaoImplement userdao = new UserDaoImplement();
		 List<UserModel> urs = userdao.getallUser();

		request.setAttribute("urs", urs);
		request.getServletContext().setAttribute("list", urs);
		return "userManagement";
	}
	@RequestMapping(value = "/mnu", method = RequestMethod.GET)
	public String MNU() {

		return "menu";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
	    
		session.invalidate();
		return "redirect:/";
	    }
	
@RequestMapping(value = "/login", method = RequestMethod.POST)
	
	public String login(@ModelAttribute("user") UserModel user, ModelMap model,HttpSession session) {
	    List<UserModel> users = userDao.userBy(user.getName(),user.getPassword());
	   
	    
		  if (users.isEmpty()) {
		  model.addAttribute("error","Invalid username or password.");
		  model.addAttribute("user", user);
		  System.out.println(user.getName());
		    System.out.println(user.getPassword());
		  return "login";
		  }
		  UserModel foundUser=users.get(0);
		  if(!foundUser.getPassword().equals(user.getPassword())) {
			  model.addAttribute("error", "Password is incorrect");
			  return "login";
		  }
		  session.setAttribute("foundUser", foundUser);
		  session.setAttribute("user_id", foundUser.getId());
		  session.setAttribute("role", foundUser.getRole());
		  session.setAttribute("name", foundUser.getName());
		  if(foundUser.getRole().equals("Admin")) {
			  return "menu";
		  }else if(foundUser.getRole().equals("User")) {
			  session.setAttribute("role", foundUser.getRole());
			  return "menu";
		  }
		  model.addAttribute("error", "Login Failed");
		 
	   return "login";
	   
	}
	@RequestMapping(value = "/usr", method = RequestMethod.GET)
	public ModelAndView userRegistration() {

		return new ModelAndView ("userRegistration","user",new UserModel());
	}
	@RequestMapping(value = "/userCreate", method = RequestMethod.POST)
	public String userCreate(@ModelAttribute("user") UserModel user,@RequestParam(value = "code", defaultValue = "") String code, ModelMap model) {
		/* List<UserModel> users = userDao.userBy(user.getName(),user.getPassword()); */
		List<UserModel>users=userDao.userBy(user.getName(), user.getPassword());
		
		List<UserModel>usr=userDao.getallUser();
		boolean isDuplicate=usr.stream().anyMatch(u ->u.getEmail().equalsIgnoreCase(user.getEmail()));
		if(isDuplicate) {
			model.addAttribute("error","Email is already taken.Please use different email.");
			return"userRegistration";
		}else {
		if (user.getRole().equals("User") && users.isEmpty()) {
			userDao.createUser(user);
			
			System.out.println(user.getPassword());
			model.addAttribute("success", "Successfully register,Please login <^^>");
			return "login";
		}if (user.getRole().equals("Admin") && code.equals("123")) {
			userDao.createUser(user);
			
			model.addAttribute("success", "Successfully register,Please login <^^>");
			return "login";
		}if (user.getRole().equals("Admin") && users.isEmpty()) {
			model.addAttribute("user",user);			
			model.addAttribute("success", "Please Enter Admin Code");
			return "userRegistration";
		} else {
			System.out.println(user.getRole());
			model.addAttribute("error", "Registration Fail,This account is already create!");
			return "userRegistration";
		}
		}

	}
	
	
	
	@RequestMapping(value = "/userDelete", method = RequestMethod.GET)
	public String deleteUser(@RequestParam int id) {
		System.out.println("sdfdfvvvvvvvvvvvvvvvvvvvv"+id);
		int status = userDao.getUserDelete(id);
		
		return "userManagement";

	}
	@RequestMapping(value="/userUpdate/{id}",method = RequestMethod.GET)
	 public ModelAndView userUpdate(@PathVariable("id") int id, ModelMap model) {
	     List<UserModel> list = userDao.userId(id);
	     model.addAttribute("users", list);
	     model.addAttribute("id",id);
	     return new ModelAndView("userUpdate", "user", new UserModel());
	 }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	 public String updateUser(@ModelAttribute("user") UserModel user,
	           ModelMap model,HttpSession session) throws IOException {
			
	     int status = userDao.updateUserRegistration(user);
	     if(user.getRole().equals("User")) {
	    	  session.setAttribute("name", user.getName());
	        	 model.addAttribute("success", "Update Successful<^^>");
		         return"menu";
	     }
	     
	     if (status == 1) {
	         model.addAttribute("success", "Update Successful<^^>");
	         session.setAttribute("name", user.getName());
	         return "menu";
	     } else {
	         model.addAttribute("error", "Update Failed!");
	         return "userUpdate";
	     }
	 }
}
