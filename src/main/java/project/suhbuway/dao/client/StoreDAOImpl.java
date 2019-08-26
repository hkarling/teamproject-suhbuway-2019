package project.suhbuway.dao.client;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.suhbuway.dto.Store;

@Repository
public class StoreDAOImpl implements StoreDAO {

	@Autowired
	SqlSession session;
	
	@Override
	public List<Store> selectAllStore() {
		List<Store> list = session.selectList("storeMapper.selectStore");
		return list;
	}

	@Override
	public Store selectStoreByStoreId(String storeId) {
		Store result = session.selectOne("storeMapper.selectStore", storeId);
		return result;
	}
}
