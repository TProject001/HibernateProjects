package com.library.basic.Dao;

import java.util.List;

import com.library.basic.entity.Admin;
import com.library.basic.entity.Student;

public interface AdminDao {
	public List<Admin> getAllAdmin();
	public String addAdmin(Admin admin1);
	public String deleteAdmin(Admin admin1);
//	public Admin getAdminById(Integer adminId);
//	public String updateAllAdminDetails(Integer adminId,Admin admin);
	public String updateAdminName(Admin admin1,String adminName);
	public String updateAdminEmail(Admin admin1,String adminEmail);
	public String updateAdminContact(Admin admin1,String adminContactNo);
	public String updateAdminAddress(Admin admin1,String adminAddress);
	public String updatePassword(Admin admin1,String adminPassword);
	public Admin getAdminById(Integer adminId);
}
