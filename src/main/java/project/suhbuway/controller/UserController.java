package project.suhbuway.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.suhbuway.dto.User;
import project.suhbuway.service.user.UserService;

/**
 * 유저관리 맵핑은 이곳으로..
 * @author hkarling
 */
@Controller
public class UserController {
	@Autowired
	private UserService service;

	// 로그인폼
    @RequestMapping("/user/login")
    public void login() {}
    
    @RequestMapping("/user/myPage")
    public void myPage() {}
   
    // 회원가입폼
    @RequestMapping("/user/register")
    public void registerForm() {}
    
    // 회원가입하기
    @RequestMapping("/userRegister")
    public String register( User user ) {
    	System.out.println("-------------회원가입-------------");
    	System.out.print( "id: " + user.getUserId() + "Name: " + user.getUserName() );
    	System.out.print( "Password: " + user.getUserPassword() + "Phone: " + user.getUserPhone() );
    	System.out.println( "Email: " + user.getUserEmail() );
    	
    	service.joinUser(user);
		//UserDTO dbIserDTO = service.loginCheck(userDTO);
    	
    	return "index";// 회원가입 성공하면 메인으로 이동
    }
    
    
    // FailHandler의 tiles 적용위한 controller
    @RequestMapping("/errorLogin")
	public ModelAndView test(HttpServletRequest request) {
    	String errorMessage =(String)request.getAttribute("errorMessage");
		return new ModelAndView("user/login" , "errorMessage" ,errorMessage );
	}
    
}
