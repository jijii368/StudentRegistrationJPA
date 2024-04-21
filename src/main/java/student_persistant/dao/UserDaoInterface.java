package student_persistant.dao;



import java.util.List;



import student_persistant.model.UserModel;

public interface UserDaoInterface {
	
	 public List<UserModel> userBy(String name,String password); 
	
	
	 public int createUser(UserModel ur);
	 
	 public List<UserModel> getallUser(); 
	 
	 public List<UserModel> userByName(int id,String name);
	 
	 public List<UserModel> userId(int id);

	 public List<UserModel> userBy(String name);
	 
	 public int getUserDelete(int id);
	 
	 public int updateUserRegistration(UserModel user);
}
