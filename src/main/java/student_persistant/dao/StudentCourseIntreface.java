package student_persistant.dao;



import java.util.List;

import student_persistant.model.Student_Course;

public interface StudentCourseIntreface {
	
	public int createStudent(Student_Course srs);
	
	public List<Student_Course> getStudentCoursesById(int id);
	
	public int studentCourse(int student_id,int course_id);
	
	public int stuCourseDelete(int id);
	
	

}
