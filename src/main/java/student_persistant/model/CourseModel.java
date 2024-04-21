 package student_persistant.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;


@NamedStoredProcedureQuery(name="courseUpdate",procedureName = "courseUpdate",
parameters = {@StoredProcedureParameter(name="c_id",mode = ParameterMode.IN,type = int.class),
			@StoredProcedureParameter(name="c_name",mode = ParameterMode.IN,type = String.class)},
resultClasses = {CourseModel.class})

@NamedStoredProcedureQuery(name = "courseDelete",procedureName = "courseDelete",parameters = {@StoredProcedureParameter(name="cid",mode = ParameterMode.IN,type = int.class)},resultClasses = {CourseModel.class})
@Entity
@Table(name = "course")
public class CourseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String course_name;
	
	
	 @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
	  private Set<Student_Course>student_Course;
	 
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	

}
