package student_persistant.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import student_persistant.model.CourseModel;
import student_persistant.model.StudentModel;
import student_persistant.model.Student_Course;
import student_persistant.model.UserModel;
import student_persistant.service.JPAUtil;

@Service("studentDao")
@Component
public class StudentDaoImplement implements StudentDaoInterface{
	
	@Override
	public StudentModel createStudent(StudentModel srs) {
		 MultipartFile multipartFile = srs.getFile();
		 System.out.println(srs.getFile());
		 System.out.println(srs.getFile().getOriginalFilename());
	     String fileName = multipartFile.getOriginalFilename();
	     System.out.println(fileName);
		int status=0;
		EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
		 try {
	            em.getTransaction().begin();
	           
	            
	            em.persist(srs);
	            em.getTransaction().commit();
	            status = 1;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
		return srs;
		
	}
	public String findCourseNameByStudentId(int id) {
		EntityManager em=JPAUtil.getEntityManagerFactory().createEntityManager();
		StringBuilder names=new StringBuilder();
		try{
			em.getTransaction().begin();
			Query query=em.createQuery("Select c.course_name From Student_Course sc Join sc.course c Where sc.student.id = :studentId" );
			query.setParameter("studentId",id);
			List<String>resuList=query.getResultList();
			for(String name:resuList) {
				if(names.length()>0) {
					names.append(",");
				}
				names.append(name);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return names.toString();
	}
	
	@Override
	public List<StudentModel> getAllStudentInfo(){
		EntityManager em =null;
	        List<StudentModel> students = new ArrayList<StudentModel>();
	        
	        try {
	        	em  = JPAUtil.getEntityManagerFactory().createEntityManager();
	        	em.getTransaction().begin();
	        	 List<StudentModel>list=em.createQuery("SELECT u FROM StudentModel u ", StudentModel.class).getResultList();
	        	for(StudentModel student:list) {
	        		String attends=findCourseNameByStudentId(student.getId());
	        		student.setAttend(attends);
	        		students.add(student);
	        	}
	        	
	        	
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
	        return students;
	}
	
	@Override
	public int updateStudentRegistration(StudentModel srs) {
	    MultipartFile multipartFile = srs.getFile();
	    String fileName = multipartFile.getOriginalFilename();
	    int status = 0;
	    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	    try {
	        em.getTransaction().begin();
	        StudentModel student = em.find(StudentModel.class, srs.getId());
	        student.setName(srs.getName());
	        student.setRegistration_date(srs.getRegistration_date());
	        student.setGender(srs.getGender());
	        student.setPhone(srs.getPhone());
	        student.setEducation(srs.getEducation());
	        student.setFile(srs.getFile());
	        em.getTransaction().commit();
	        status = 1;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return status;
	}
	@Override
	public List<StudentModel> studentCourse(String name,String phone){
		List<StudentModel> srs=new ArrayList<StudentModel>();
		 EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		 try {
			 em.getTransaction().begin();
			 Query query = em.createQuery("SELECT s FROM StudentModel s WHERE s.name = :name AND s.phone = :phone");
	            query.setParameter("name", name);
	            query.setParameter("phone", phone);

	            srs = query.getResultList();
	            em.getTransaction().commit();
			
		} catch (Exception e) {
			 System.out.println(e.getMessage());
	            e.printStackTrace();
		}finally {
			em.close();
		}
		
		
		return srs;
	}
	
	
	@Override
	public List<StudentModel> studentId(int studentId) {
		List<StudentModel> srs = new ArrayList<StudentModel>();
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT s FROM StudentModel s WHERE s.id = :studentId");
			query.setParameter("studentId", studentId);
			srs = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
		return srs;
	}
	 
	@Override
    public int studentDelete(int id) {
        int status = 0;
        EntityManager em = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM StudentModel stc WHERE stc.id=:id", StudentModel.class);
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
