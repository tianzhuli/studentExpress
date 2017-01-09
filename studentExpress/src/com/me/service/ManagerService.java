package com.me.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ExpressUserDao;
import com.me.dao.ManagerDao;
import com.me.dao.SystemInformationDao;
import com.me.domain.Manager;
import com.me.domain.SystemInformation;

@Service
public class ManagerService {
	@Autowired
	private SystemInformationDao systemInformationDao;

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private ExpressUserDao expressUserDao;

	public Boolean ManagerLogin(Manager manager) {
		return managerDao.ManagerLogin(manager) > 0;
	}

	public void insertSystemInformation(SystemInformation systemInformation) {
		try {
			systemInformationDao.insertSystemInformation(systemInformation);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<Map> selectActive() {
		List<Map> list = new ArrayList<Map>();
		try {
			list = expressUserDao.selectActive();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map> selectInformation() {
		List<Map> list = new ArrayList<Map>();
		try {
			list = systemInformationDao.selectAllInformation();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public void deleteInformation(int systemInformationID){
		try {
			systemInformationDao.deleteInformation(systemInformationID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
