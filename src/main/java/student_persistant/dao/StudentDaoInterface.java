package student_persistant.dao;


import java.util.ArrayList;
import java.util.List;

import student_persistant.*;
import student_persistant.model.CourseModel;
import student_persistant.model.StudentModel;
import student_persistant.model.UserModel;


public interface StudentDaoInterface {
	
	public StudentModel createStudent(StudentModel srs);
	
	public List<StudentModel> getAllStudentInfo();
	
	public int updateStudentRegistration(StudentModel srs) ;
	
	public List<StudentModel> studentCourse(String name,String phone);
	
	public int studentDelete(int id);
	
	public List<StudentModel> studentId(int studentId); 
	/* public int stuCourseDelete(int student_id); */
	
	 
	
	/* public List<CourseModel> getCourseByStudentId(int id); */
	
	
	/* public List<StudentModel> allStudentUser(); */
	 
		/*
		 * public List<StudentModel> studentName(String name);
		 * 
		 * public List<StudentModel> courseName(String attend);
		 */
	 
	 
	

}
