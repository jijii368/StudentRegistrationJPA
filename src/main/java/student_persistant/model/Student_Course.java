package student_persistant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
@NamedStoredProcedureQuery(name = "studentCourse",procedureName = "studentCourse",
parameters = {@StoredProcedureParameter(name="name_in",mode = ParameterMode.IN,type = String.class),
				@StoredProcedureParameter(name="phone_in",mode = ParameterMode.IN,type = String.class)},resultClasses= {Student_Course.class})
@Entity
@Table(name = "student_course")
public class Student_Course {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentModel student;
	
	 
	@JoinColumn(name = "course_id")
	private CourseModel course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
	}

	public CourseModel getCourse() {
		return course;
	}

	public void setCourse(CourseModel course) {
		this.course = course;
	}

}
