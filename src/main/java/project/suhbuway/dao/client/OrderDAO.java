package project.suhbuway.dao.client;

import java.util.List;

import project.suhbuway.dto.OrderItem;
import project.suhbuway.dto.OrderList;

public interface OrderDAO {

	/**
	 * 유저별 주문 리스트들을 불러오는 메서드
	 */
	public List<OrderList> selectOrderListsByUser(String userId);

	/**
	 * DB에 주문을 넣기위한 메소드
	 */
	public int insertOrder(OrderList orderList);

	/**
	 * DB에 주문을 넣기위한 메소드
	 */
	public int insertItem(OrderItem orderItem);

	/**
	 * 주문 아이템들을 저장할때 번호를 불러오기 위한 메서드
	 */
	public int getLastItemId();
	
}
