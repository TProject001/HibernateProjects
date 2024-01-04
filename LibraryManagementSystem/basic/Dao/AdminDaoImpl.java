package com.library.basic.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.library.basic.entity.Admin;

public class AdminDaoImpl implements AdminDao {
	private EntityManager entityManager = MyConnection.getEntityManagerObject();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
	private Admin admin;
	private List<Admin> adminList;
	
	@Override
	public String addAdmin(Admin admin1) 
	{
		entityTransaction.begin();
			entityManager.persist(admin1);
		entityTransaction.commit();
		return "Admin Registration Done\nAdmin Id : "+admin1.getAdminId();
	}

	@Override
	public String deleteAdmin(Admin admin1) 
	{
		entityTransaction.begin();
			admin1.setAccoutStatus("DEACTIVE");
	//		entityManager.remove(admin1);
		entityTransaction.commit();
		return "Your Account has been deleted!";
	}
	
	@Override
	public Admin getAdminById(Integer adminId1)
	{	
		entityTransaction.begin();
			admin = entityManager.find(Admin.class, adminId1);
		entityTransaction.commit();
		return admin;
	}

	@Override
	public String updatePassword(Admin admin1, String pass1) 
	{
		entityTransaction.begin();
			admin1.setAdminPassword(pass1);
		entityTransaction.commit();
		return "Your Password updated successfully";
	}

	@Override
	public String updateAdminName(Admin admin1, String adminName1) 
	{
		entityTransaction.begin();
			admin1.setAdminName(adminName1);
		entityTransaction.commit();
		return "Your Name updated successfully";
	}

	@Override
	public String updateAdminEmail(Admin admin1, String adminEmail1) 
	{
		entityTransaction.begin();
			admin1.setAdminEmail(adminEmail1);
		entityTransaction.commit();
		return "Your Email Id updated successfully";
	}

	@Override
	public String updateAdminContact(Admin admin1, String adminContactNo1) 
	{
		entityTransaction.begin();
			admin1.setAdminContactNo(adminContactNo1);
		entityTransaction.commit();
		return "Your Contact No updated successfully";
	}

	@Override
	public String updateAdminAddress(Admin admin1, String adminAddress1) 
	{
		entityTransaction.begin();
			admin1.setAdminAddress(adminAddress1);
		entityTransaction.commit();
		return "Your Address updated successfully";
	}

	@Override
	public List<Admin> getAllAdmin() 
	{
		entityTransaction.begin();
			adminList = entityManager.createQuery("FROM Admin a").getResultList();
		entityTransaction.commit();
		return adminList;
	}
}
