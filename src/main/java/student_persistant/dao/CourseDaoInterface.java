package student_persistant.dao;


import java.util.List;

import student_persistant.model.CourseModel;
import student_persistant.model.StudentModel;
import student_persistant.model.UserModel;

public interface CourseDaoInterface {
	
	public int createCourse(CourseModel cr);
	
	public List<CourseModel> allCourse();
	
	public int updateCourse(CourseModel course);
	
	public List<CourseModel> courseId(int id);
	
	public int getCourseDelete(int id);
	
	public List<CourseModel> course(String course_name);
	
	public CourseModel getCourseById(int id);
	
	 
	

}
