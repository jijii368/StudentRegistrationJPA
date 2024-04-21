package student_persistant.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import student_persistant.model.CourseModel;
import student_persistant.model.StudentModel;
import student_persistant.model.Student_Course;
import student_persistant.service.JPAUtil;

@Service("studentcourseDao")
@Component
public class StudentCourseImplement implements StudentCourseIntreface{
	
	@Override
	public int createStudent(Student_Course srs) {
		int status = 0;
		 EntityManager em=null;
		try {
			 em=JPAUtil.getEntityManagerFactory().createEntityManager();
			 em.getTransaction().begin();
			 em.persist(srs);
			em.getTransaction().commit();
			status = 1;
		} finally {
			em.close();
		}
		 return status;
		
	}

	@Override
	public List<Student_Course> getStudentCoursesById(int id){
		EntityManager em = null;
		List<Student_Course>srs=new ArrayList<Student_Course>();
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			TypedQuery<Student_Course> query = em.createQuery("SELECT c FROM Student_Course c WHERE c.student.id = :id ", Student_Course.class);
			
			query.setParameter("id", id);
			srs=query.getResultList();
		} finally {
			em.close();
		}
		return srs;
	}
	
	@Override
	public int studentCourse(int student_id,int course_id) {
		int status = 0;
		EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
		 try {
	         em.getTransaction().begin();

	         Student_Course studentCourse = new Student_Course();
	         studentCourse.setId(student_id);
	         studentCourse.setId(course_id);

	         em.persist(studentCourse);

	         em.getTransaction().commit();
	         status = 1;

	     } catch (Exception e) {
	         System.out.println(e.getMessage());
	         e.printStackTrace();
	         if (em.getTransaction().isActive()) {
	             em.getTransaction().rollback();
	         }
	     } finally {
	         em.close();
	     }
		 return status;
	}
	
	@Override
    public int stuCourseDelete(int id) {
        int status = 0;
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Student_Course stc WHERE stc.id=:id", Student_Course.class);
            query.setParameter("id",id);
            status= query.executeUpdate();
            em.getTransaction().commit();
        }finally {

            if (em != null) {
                em.close();
            }
        }

        return status;
    }
}
	


