package com.library.basic.service;

import java.util.List;

import com.library.basic.entity.Admin;

public interface AdminService {
	public void addAdmin(Admin admin);
	public Admin getAdminById(Integer adminId);
	public void deleteAdmin(Admin admin1);
//	public void getAdminProfile(Integer adminId);
	public void updateAdminName(Admin adminId,String adminName);
	public void updateAdminEmail(Admin admin,String adminEmail);
	public void updateAdminContact(Admin admin,String adminContactNo);
	public void updateAdminAddress(Admin admin,String adminAddress);
	public void updateAdminPassword(Admin admin,String adminPassword);
	
	public List<Admin> getAllAdmin();
}
