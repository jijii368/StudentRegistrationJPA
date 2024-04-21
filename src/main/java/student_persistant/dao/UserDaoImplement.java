package student_persistant.dao;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import student_persistant.model.UserModel;
import student_persistant.service.JPAUtil;


@Service("userDao")
@Component
public class UserDaoImplement implements UserDaoInterface {

	
	@Override
	public List<UserModel> userBy(String name, String password) {

		EntityManager em = null;
		List<UserModel> urs = new ArrayList<UserModel>();
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			TypedQuery<UserModel> query = em.createQuery("SELECT u FROM UserModel u WHERE u.name = :name AND u.password = :password", UserModel.class);
			
			query.setParameter("name", name);
			query.setParameter("password", password);
			
			urs = query.getResultList();
			
		} finally {
			em.close();
		}

		return urs;

	}
	
		@Override
		 public int createUser(UserModel ur) {
			int status = 0;
			 EntityManager em=null;
			try {
				 em=JPAUtil.getEntityManagerFactory().createEntityManager();
				 em.getTransaction().begin();
				 em.persist(ur);
				em.getTransaction().commit();
				status = 1;
			} finally {
				em.close();
			}
			 return status;
		 }

		@Override	
		 public List<UserModel> getallUser(){
			EntityManager em = null;
			List<UserModel>urs=new ArrayList<UserModel>();
			try {
				em  = JPAUtil.getEntityManagerFactory().createEntityManager();
				 em.getTransaction().begin();
				urs=em.createQuery("SELECT u FROM UserModel u ", UserModel.class).getResultList();
				em.getTransaction().commit();
			} catch (Exception e) {
				if(em!=null && em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
				e.printStackTrace();
			} finally {
				if(em!=null) {
					em.close();
				}
			}
			
			return urs;
		}
		
		@Override
		 public List<UserModel> userByName(int id,String name){
			EntityManager em = null;
			List<UserModel>urs=new ArrayList<UserModel>();
			try {
				em = JPAUtil.getEntityManagerFactory().createEntityManager();
				
				TypedQuery<UserModel> query = em.createQuery("SELECT u FROM UserModel u WHERE u.id = :id AND u.name = :name", UserModel.class);
				
				query.setParameter("id", id);
				query.setParameter("name", name);
				
				urs = query.getResultList();
				
			} finally {
				em.close();
			}
			return urs;
		}
		
		@Override
		public List<UserModel> userId(int id){
			EntityManager em = null;
			List<UserModel>urs=new ArrayList<UserModel>();
			try {
				em = JPAUtil.getEntityManagerFactory().createEntityManager();
				TypedQuery<UserModel> query = em.createQuery("SELECT u FROM UserModel u WHERE u.id = :id ", UserModel.class);
				
				query.setParameter("id", id);
				urs=query.getResultList();
			} finally {
				em.close();
			}
			
			return urs;
			
		}
		
		@Override
		public List<UserModel> userBy(String name){
			EntityManager em = null;
			List<UserModel>urs=new ArrayList<UserModel>();
			try {
				em=JPAUtil.getEntityManagerFactory().createEntityManager();
				TypedQuery<UserModel> query = em.createQuery("SELECT u FROM UserModel u WHERE u.name = :name ", UserModel.class);
				query.setParameter("name", name);
				urs=query.getResultList();
			} finally {
				em.close();
			}
			
			return urs;
		}
		
		@Override
		public int getUserDelete(int id) {
			int status=0;
			EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
			try {
				em.getTransaction().begin();
				StoredProcedureQuery sp=em.createNamedStoredProcedureQuery("userDelete");
				sp.setParameter("uid", id);
				sp.execute();
				status=1;
				em.getTransaction().commit();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				em.close();
			}
			return status;
		}
		
		@Override
		 public int updateUserRegistration(UserModel urs ) {
			int status=0;
			EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
			/* entityManager.getEntityManagerFactory().getCache().evictAll(); */
			/* EntityManager.flush(); */
			
			try {
				em.getTransaction().begin();
				StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("userUpdate");
	            
	            sp.setParameter("u_id", urs.getId());
	            sp.setParameter("u_name", urs.getName());
	            sp.setParameter("u_email", urs.getEmail());
	            sp.setParameter("u_password", urs.getPassword());
	            sp.setParameter("u_role", urs.getRole());
	            
	            sp.execute();
				 status=1; 
	            em.getTransaction().commit();
	            em.getEntityManagerFactory().getCache().evictAll();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				em.close();
			}
			return status;
			
		}
		
}
