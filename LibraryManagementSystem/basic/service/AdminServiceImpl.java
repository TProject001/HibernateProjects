package com.library.basic.service;

import java.util.List;

import com.library.basic.Dao.AdminDao;
import com.library.basic.Dao.AdminDaoImpl;
import com.library.basic.entity.Admin;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao = new AdminDaoImpl();
	private static Admin admin;
//	private static Scanner scanner = new Scanner(System.in);

	@Override
	public Admin getAdminById(Integer adminId) 
	{
		return adminDao.getAdminById(adminId);
	}
	
	@Override
	public void addAdmin(Admin admin) 
	{
		System.out.println(adminDao.addAdmin(admin));
	}
	
	@Override
	public void deleteAdmin(Admin admin1) 
	{
		System.out.println(adminDao.deleteAdmin(admin1));	
	}

	@Override
	public void updateAdminName(Admin admin1, String adminName) 
	{
		System.out.println(adminDao.updateAdminName(admin1, adminName));
	}

	@Override
	public void updateAdminEmail(Admin admin1, String adminEmail) 
	{
		System.out.println(adminDao.updateAdminEmail(admin1, adminEmail));	
	}

	@Override
	public void updateAdminContact(Admin admin1, String adminContactNo) 
	{
		System.out.println(adminDao.updateAdminContact(admin1, adminContactNo));
	}

	@Override
	public void updateAdminAddress(Admin admin1, String adminAddress) 
	{
		System.out.println(adminDao.updateAdminAddress(admin1, adminAddress));
	}

	@Override
	public void updateAdminPassword(Admin admin1, String adminPassword) 
	{
		System.out.println(adminDao.updatePassword(admin1, adminPassword));
	}

	@Override
	public List<Admin> getAllAdmin() 
	{
		return adminDao.getAllAdmin(); 
	}
}
