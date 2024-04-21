package student_persistant.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import student_persistant.model.CourseModel;
import student_persistant.model.Student_Course;

import student_persistant.service.JPAUtil;


@Service("courseDao")
@Component
public class CourseDaoImplement implements CourseDaoInterface{
	
@Override
public int createCourse(CourseModel cr) {
		
		int status=0;
		 EntityManager em=null;
		 try {
			 em=JPAUtil.getEntityManagerFactory().createEntityManager();
			 em.getTransaction().begin();
			 em.persist(cr);
			em.getTransaction().commit();
			status = 1;
		} finally {
			em.close();
		}
		return status;
	}
@Override
public List<CourseModel> allCourse(){
	EntityManager em = null;
	List<CourseModel>srs=new ArrayList<CourseModel>();
	try {
		 em=JPAUtil.getEntityManagerFactory().createEntityManager();
		 em.getTransaction().begin();
		 srs=em.createQuery("SELECT c FROM CourseModel c ", CourseModel.class).getResultList();
		 em.getTransaction().commit();
	} catch (Exception e) {
		if(em!=null && em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
		e.printStackTrace();
		
	}finally {
		if(em!=null) {
			em.close();
		}
	}
	return srs;
	
}
@Override
public int updateCourse(CourseModel course) {
	int status=0;
	EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
	try {
		em.getTransaction().begin();
		StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("courseUpdate");
		sp.setParameter("u_id", course.getId());
        sp.setParameter("u_name", course.getCourse_name());
		
        sp.execute();
		 status=1; 
       em.getTransaction().commit();
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}finally {
		em.close();
	}
	return status;
}
@Override
public List<CourseModel> courseId(int id){
	EntityManager em = null;
	List<CourseModel>srs=new ArrayList<CourseModel>();
	try {
		em = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<CourseModel> query = em.createQuery("SELECT c FROM CourseModel c WHERE c.id = :id ", CourseModel.class);
		
		query.setParameter("id", id);
		srs=query.getResultList();
	} finally {
		em.close();
	}
	return srs;
}
@Override
public int getCourseDelete(int id) {
	int status=0;
	EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
	try {
		em.getTransaction().begin();
		StoredProcedureQuery sp=em.createNamedStoredProcedureQuery("courseDelete");
		sp.setParameter("cid", id);
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
public List<CourseModel> course(String course_name){
	EntityManager em = null;
	List<CourseModel> srs=new ArrayList<CourseModel>();
	
	try {
		em=JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<CourseModel> query = em.createQuery("SELECT c FROM CourseModel c WHERE c.course_name = :course_name ", CourseModel.class);
		query.setParameter("course_name", course_name);
		srs=query.getResultList();
	} finally {
		em.close();
	}
	
	return srs;
}
@Override
public CourseModel getCourseById(int id) {
	EntityManager em =null;
	try {
		em = JPAUtil.getEntityManagerFactory().createEntityManager();
		String jpql ="SELECT c FROM CourseModel c WHERE c.id = :id";
		TypedQuery<CourseModel> query = em.createQuery(jpql,CourseModel.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	} finally {
		if (em != null && em.isOpen()) {
            em.close();
        }
	}
}


}
