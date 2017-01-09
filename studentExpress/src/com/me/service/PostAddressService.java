package com.me.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.PostReceiveAddressDao;
import com.me.dao.PostReceiveAddressDaoXml;
import com.me.dao.PostSendAddressDao;
import com.me.dao.PostSendAddressDaoXml;
import com.me.domain.PostReceiveAddress;
import com.me.domain.PostSendAddress;

@Service
public class PostAddressService {

	@Autowired
	private PostSendAddressDao postSendAddressDao;

	@Autowired
	private PostReceiveAddressDao postReceiveAddressDao;

	@Autowired
	private PostReceiveAddressDaoXml postReceiveAddressDaoXml;

	@Autowired
	private PostSendAddressDaoXml postSendAddressDaoXml;

	public PostSendAddress selectPostSendAddressById(int postAddressID) {
		return postSendAddressDao.selectById(postAddressID);
	}

	public List<PostSendAddress> selectPostSendAddressesByUserId(String userID) {
		return postSendAddressDao.selectByUserId(userID);
	}

	public boolean insertPostAddress(PostSendAddress postSendAddress) {
		return postSendAddressDao.insertPostAddress(postSendAddress) > 0;
	}

	public boolean insertPostReceiveAddress(
			PostReceiveAddress postReceiveAddress) {
		return postReceiveAddressDao
				.insertPostReceiveAddress(postReceiveAddress) > 0;
	}

	public PostReceiveAddress findPostReceiveAddressByid(int receiveAddressID) {
		return postReceiveAddressDao.findByid(receiveAddressID);
	}

	public List<PostReceiveAddress> findPostReceiveAddressesByUserId(
			String userID) {
		return postReceiveAddressDao.findByUserId(userID);
	}

	public int insertPostReceiveAddressXml(PostReceiveAddress postReceiveAddress) {
		if (postReceiveAddress.isReceiveIsDefault()) {
		postReceiveAddressDao.updateDefault(postReceiveAddress.getUserID());
		}
		postReceiveAddressDaoXml.insertPostReceiveAddress(postReceiveAddress);		
		return postReceiveAddress.getReceiveAddressID();
	}

	public int insertPostSendAddress(PostSendAddress postSendAddress) {
		if (postSendAddress.isPostIsDefault()) {
		postSendAddressDao.updateDefaultAddress(postSendAddress.getUserID());
		}
		postSendAddressDaoXml.insertSendAddress(postSendAddress);		
		return postSendAddress.getPostAddressID();

	}

	public JSONObject deletePostReceiveAddress(int receiveAddressID) {
		JSONObject jsonObject = new JSONObject();
		try {
			postReceiveAddressDao.deleteByUpdate(receiveAddressID);  //通过将用户名置为空来调整显示
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("state", "error");
			return jsonObject;
		}
		 jsonObject.put("state", "success");
		 return jsonObject;
	}

	public JSONObject deletePostSendAddress(int postAddressID) {
		JSONObject jsonObject = new JSONObject();
		try {
			postSendAddressDao.deleteByUpdate(postAddressID);  ////通过将用户名置为空来调整显示
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 jsonObject.put("state", "error");
			 return jsonObject;
		}
		jsonObject.put("state", "success");
		return jsonObject;
	}

	public JSONObject updatePostSendAddress(PostSendAddress postSendAddress) {
		JSONObject jsonObject = new JSONObject();
		try {
			postSendAddressDaoXml.updateSendAddress(postSendAddress);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("state", "error");
			return jsonObject;
		}
		jsonObject.put("state", "success");
		return jsonObject;
	}

	public JSONObject updatePostReceiveAddress(
			PostReceiveAddress postReceiveAddress) {
		JSONObject jsonObject = new JSONObject();
		try {
			postReceiveAddressDaoXml
					.updatePostReceiveAddress(postReceiveAddress);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("state", "error");
			return jsonObject;
		}
		jsonObject.put("state", "success");
		return jsonObject;
	}
}
