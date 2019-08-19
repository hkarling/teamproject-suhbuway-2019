package project.suhbuway.service.complain;

import java.util.List;

import org.springframework.stereotype.Service;

import project.suhbuway.dto.Complain;

@Service
public interface ComplainService {
	/**
	  * ���ڵ� ��ü �˻�
	  * */
	  List<Complain> selectAll();
	  
	  /**
		  * �Խ��� ��ȣ�� �˻�
		  * */
	  Complain selectByCompainId(int complainId);
	  
	/**
	 * ���ڵ� ����
	 * */
	  int insert(Complain complain);
	  
	  /**
	   * ������ ������ư�� ������ �� ����
	   * */
	   int delete(String userId, int complainId);
	  
	   /**
	    * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	    * */
	   int update(Complain complain);
	
}