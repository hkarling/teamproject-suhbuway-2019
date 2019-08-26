package project.suhbuway.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import project.suhbuway.dto.Complain;
import project.suhbuway.dto.User;
import project.suhbuway.service.complain.ComplainService;

@Controller
public class ComplainController {
	private String path ="C:\\Users\\Lenovo\\Desktop\\save"; // 파일 저장 경로
	
	@Autowired
	private ComplainService service;
	
	/*
	 * 문의사항폼 
	 * */
	@RequestMapping("/board/complainList")
	public ModelAndView selectAll(HttpServletRequest request, HttpSession session, Principal principal) {
		ModelAndView mv = new ModelAndView();
    	String kakaoId = (String)session.getAttribute("userId");//카카오 로그인
    	// 시큐리티 로그인 유저 id
    	if( principal!= null ) {
    		String user = principal.getName();
    		if(!user.equals("admin")) {
    			//System.out.println("principaluser: " + user);
            	user = user.replace("User(", "" );
            	user = user.replace(")", "" );
            	String [] userdd= user.split(",");
            	String userId =  userdd[0].replace("userId=", "" );
            	
        		List<Complain> userComplain = service.selectByUserId(userId);
        		mv.addObject("list", userComplain );   
            	
        	} else {
        		mv.addObject("admin", "admin" );
       		 	List<Complain> list = service.selectAll();
       		 	mv.addObject("list", list );
        	}
    	}
    	// 카카오 로그인 유저
    	if( kakaoId!=null ) {
    		List<Complain> kakaoComplain = service.selectByUserId(kakaoId);
    		mv.addObject("list", kakaoComplain );   		
    	}
		mv.setViewName("board/complainList");
	return mv;
	}
	
	/*
	 * �Խ��� ��ȣ�� �˻�
	 * */
	@RequestMapping("/board/Read/{complainId}")
	public ModelAndView selectByCompainId(@PathVariable int complainId) {
		Complain complain=service.selectByCompainId(complainId);
		return new ModelAndView("board/complainRead","complain",complain);
	}
	
	/*
	 * �۾��� ��
	 * */
	@RequestMapping("/board/write")
	public String insertForm() {
		return "board/complainWrite";
	}
	
	/*
	 * �Խñ� �Է�
	 * */
	 @RequestMapping("/board/insert")
	 public String insert(Complain complain) throws IOException {
		 MultipartFile file = complain.getFile(); // complain으로 들어온 file을 꺼내서 file변수에 저장.
			if(file.getSize() > 0) {//파일이 첨부되었다면
				String fName = file.getOriginalFilename();
				long fSize = file.getSize();
				complain.setFileName(fName);// dto에 저장
				complain.setFileSize(fSize); // dto에 저장
				//파일 저장
				file.transferTo(new File(path +"/"+ fName));//파일 저장
			}		
			
		 service.insert(complain);
		 return "redirect:complainList";
	 }
	 
	 /*
	  * �����ϱ�
	  * */
	 @RequestMapping("/board/delete")
	 public String delete(String userId, int complainId) {
		 service.delete(userId, complainId);
		 return "redirect:complainList";
	 }
	 
	 /*
	 * 수정 폼
	 * */
	@RequestMapping("/board/updateForm")
	public ModelAndView updateForm(int complainId) {
		Complain complain = service.selectByCompainId(complainId);
		return new ModelAndView("board/complainUpdateForm","complain",complain);
	}
	
	 /*
	 * 수정 하기
	 * */
	@RequestMapping("/board/update")
	public String update(Complain complain) throws IOException{
		 MultipartFile file = complain.getFile(); // complain으로 들어온 file을 꺼내서 file변수에 저장.
			if(file.getSize() > 0) {//파일이 첨부되었다면
				String fName = file.getOriginalFilename();
				long fSize = file.getSize();
				complain.setFileName(fName);// dto에 저장
				complain.setFileSize(fSize); // dto에 저장
				//파일 저장
				file.transferTo(new File(path +"/"+ fName));//파일 저장
			}
			
		service.update(complain);
		return "redirect:complainList";
	}
	
	/*
	 * 다운로드 기능
	 * */
	@RequestMapping("/board/down")
	public ModelAndView downLoadView(HttpSession session, String fileName) {
		File file = new File(path+"/"+fileName);//파일을 찾아서 file이란 변수에 넣어준다
		return new ModelAndView("downLoadView", "fileName",file);
	}
	
	/*
	 * 관리자 답변 폼
	 * */
	@RequestMapping("/board/replyForm")
	public ModelAndView replyForm(int complainId) {
		return new ModelAndView("board/complainReply","complainId",complainId);
	}
		
	/*
	 * 관리자 답변
	 * */
	@RequestMapping("/board/reply")
	public String reply(Complain complain) {
		service.replyUpdate(complain);
		return "redirect:complainList";
	}
	
}
