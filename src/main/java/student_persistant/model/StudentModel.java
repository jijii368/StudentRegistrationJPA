package student_persistant.model;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;



@NamedStoredProcedureQuery(name = "stuCourseDelete",procedureName = "stuCourseDelete",parameters = {@StoredProcedureParameter(name="stu_id",mode = ParameterMode.IN,type = int.class)},resultClasses = {Student_Course.class})
@Entity
@Table(name = "student")
public class StudentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private String registration_date;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String education;
	
	 @Transient
	private String attend;
	
	 @Lob
	@Column(nullable = false,columnDefinition = "LONGBLOB")
	private byte[] photo;
	
	@Transient
	private MultipartFile file;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch =
			  FetchType.LAZY)
			  private Set<Student_Course>student_Course;
			  
			  

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAttend() {
		return attend;
	}

	public void setAttend(String attend) {
		this.attend = attend;
	}

	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
