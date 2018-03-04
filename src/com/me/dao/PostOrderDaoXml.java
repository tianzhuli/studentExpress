package com.me.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.me.domain.PostOrder;
import com.me.web.GetPostOrderCommand;

@Repository
public interface PostOrderDaoXml {
	
	public int updatePostOrder(PostOrder postOrder);
	
	public int insertPostOrder(PostOrder postOrder);
	
	public List<Map> selectPostOrder(GetPostOrderCommand getPostOrderCommand );
	
	public List<Map> selectPostOrderView(GetPostOrderCommand getPostOrderCommand );
	
	public List<Map> selectPostAddressView(GetPostOrderCommand getPostOrderCommand );
	
}
