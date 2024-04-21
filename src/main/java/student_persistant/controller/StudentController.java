package student_persistant.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import student_persistant.dao.CourseDaoImplement;
import student_persistant.dao.StudentCourseImplement;
import student_persistant.dao.StudentDaoImplement;
import student_persistant.model.CourseModel;
import student_persistant.model.StudentModel;
import student_persistant.model.Student_Course;

@MultipartConfig
@Component
@ComponentScan("student_persistant")
@Controller
public class StudentController {
	
	@Autowired
	StudentDaoImplement studentDao;
	@Autowired
	CourseDaoImplement courseDao;
	
	@Autowired
	private StudentCourseImplement courseImplement;
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView login() {

		return new ModelAndView("studentRegistration", "student", new StudentModel());
	}
	
	@RequestMapping(value = "/studentCreate", method = RequestMethod.POST)
	  public String insertCourse(@ModelAttribute("student") StudentModel student, ModelMap model,
			                     @RequestParam("attend")List<Integer> attend) {
			
		try {
	          MultipartFile multipartFile = student.getFile();
         
	          byte[] photoByte = multipartFile.getBytes();
	          student.setPhoto(photoByte);
				
	          StudentModel student1 = studentDao.createStudent(student);
	          if(attend != null) {
          for(Integer courseId:attend) {
        	  CourseModel courseModel = courseDao.getCourseById(courseId); 
        	  System.out.println("sfff"+courseModel.getCourse_name());
        	  Student_Course student_Course = new Student_Course();
        	  student_Course.setCourse(courseModel);
        	  student_Course.setStudent(student1);
        	  courseImplement.createStudent(student_Course);
          }
	        	  
	            model.addAttribute("success", "Successful Register<^^>");
	              return "studentRegistration";
	          } else {
	                model.addAttribute("error", "Error occurred while registering the student.");
	                return "studentRegistration";
	          }
	      } catch (IOException e) {
	        
	      }
	      return "studentRegistration";
	      }

	@RequestMapping(value = "/stuUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView studentUpdate(@PathVariable("id") int id, ModelMap model) {
		List<StudentModel> list = studentDao.studentId(id);
		
		List<Student_Course> student_Courses = courseImplement.getStudentCoursesById(id);
		
		List<Integer> courseId = student_Courses.stream().map(s -> s.getCourse().getId()).toList();
		model.addAttribute("courseId",courseId);
		
		model.addAttribute("stu", list);
		model.addAttribute("id", id);
		model.addAttribute("course_list",courseDao.allCourse());
		
		
		System.out.println(courseDao.allCourse());
		return new ModelAndView("studentUpdate", "student", new StudentModel());
	}
	
	@RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
	public String studentsUpdate(@ModelAttribute("student") StudentModel student, ModelMap model,RedirectAttributes redirect, @RequestParam("attend")List<Integer> attend) {
	 
		List<Student_Course> student_Courses = courseImplement.getStudentCoursesById(student.getId());
		for(Student_Course student_Course:student_Courses) {
			int i = courseImplement.stuCourseDelete(student_Course.getId());
		}
		

			try {
		          MultipartFile multipartFile = student.getFile();
	         
		          byte[] photoByte = multipartFile.getBytes();
		          student.setPhoto(photoByte);
		       
		          int status = studentDao.updateStudentRegistration(student);
					if (status == 1) {

						List<StudentModel> students = studentDao.studentCourse(student.getName(), student.getPhone());
						
						for (String courseId : student.getAttend().split(",")) {
							List<CourseModel> courses = courseDao.course(courseId);
							if (!courses.isEmpty()) {
								courseImplement.studentCourse(students.get(0).getId(), courses.get(0).getId());
							}
						}
						
						 if(attend != null) {
				              for(Integer courseId:attend) {
				            	  CourseModel courseModel = courseDao.getCourseById(courseId); 
				            	  System.out.println("sfff"+courseModel.getCourse_name());
				            	  Student_Course student_Course = new Student_Course();
				            	  student_Course.setCourse(courseModel);
				            	  student_Course.setStudent(student);
				            	  courseImplement.createStudent(student_Course);
				              }
						 }
						
			
			redirect.addFlashAttribute("success","Updated successfully <^^>");
			return "redirect:/stuUpdate/%s".formatted(student.getId());
			
		} else {
			redirect.addFlashAttribute("error", "Update Failed!!");
			return "redirect:/stuUpdate/"+student.getId();
		}
					
	} catch (IOException e) {
		redirect.addFlashAttribute("error", "Error occurred while uploading the file.");
		return "redirect:/stuUpdate/{stuId}"+student.getId();
	}
		}
				
	
	@GetMapping("/studentSearch")
	public String viewStudents(Model model,HttpServletRequest request) {
		List<StudentModel> studentModels = studentDao.getAllStudentInfo();
		Map<Integer, List<String>> carMap = new HashMap<>();
 		for(StudentModel studentModel:studentModels) {
			List<Student_Course> studentCourses = courseImplement.getStudentCoursesById(studentModel.getId());
		System.out.println("size"+studentCourses.size());
		
			List<String> courseNameList = studentCourses.stream().map(stc->stc.getCourse().getCourse_name()).toList();
 		carMap.put(studentModel.getId(), courseNameList);
 		}
		request.getServletContext().setAttribute("list", studentModels);

		model.addAttribute("list",studentModels);
		model.addAttribute("carMap", carMap);
		return "studentSearch";
	}
	
	@RequestMapping(value = "/studentDelete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Integer id) {
		List<Student_Course> student_Courses = courseImplement.getStudentCoursesById(id);
		for(Student_Course student_Course:student_Courses) {
			int i = courseImplement.stuCourseDelete(student_Course.getId());
		}
		int status = studentDao.studentDelete(id);
		return "redirect:/studentSearch";

	}
	
	
}
