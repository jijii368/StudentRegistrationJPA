package student_persistant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
@NamedStoredProcedureQuery(name="userUpdate",procedureName = "userUpdate",
parameters = {@StoredProcedureParameter(name="u_id",mode = ParameterMode.IN,type = int.class),
			@StoredProcedureParameter(name="u_name",mode = ParameterMode.IN,type = String.class),
			@StoredProcedureParameter(name="u_email",mode = ParameterMode.IN,type = String.class),
			@StoredProcedureParameter(name="u_password",mode = ParameterMode.IN,type = String.class),
			@StoredProcedureParameter(name="u_role",mode = ParameterMode.IN,type = String.class)},
resultClasses = {UserModel.class})


@NamedStoredProcedureQuery(name = "userDelete",procedureName = "userDelete",parameters = {@StoredProcedureParameter(name="uid",mode = ParameterMode.IN,type = int.class)},resultClasses = {UserModel.class})
@Entity
@Table(name = "user")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	

}
