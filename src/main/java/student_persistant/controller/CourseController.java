 package student_persistant.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import student_persistant.dao.CourseDaoImplement;
import student_persistant.dao.StudentCourseImplement;
import student_persistant.model.CourseModel;
import student_persistant.model.Student_Course;
import student_persistant.model.UserModel;



@Component
@ComponentScan("student_persistant")
@Controller
public class CourseController {
	
	@Autowired
	CourseDaoImplement courseDao;
	
	@Autowired
	StudentCourseImplement courseImplement;

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView course() {

		return new ModelAndView("courseRegistration","course",new CourseModel());
	}
	@RequestMapping(value = "/insertCourse",method = RequestMethod.POST)
	public String insertCourse(@ModelAttribute("course")CourseModel courseModel,ModelMap model) {

		
		int status=courseDao.createCourse(courseModel);
		if(status>0) {
			model.addAttribute("success","Insert Successfully<^^>");
		}else {
			model.addAttribute("error","Course Name is Dublicate!");
		}
		return "courseRegistration";
	}
	
	@RequestMapping(value = "/allCourse", method = RequestMethod.GET)
	public String allCourse() {

		return "courseManagement";
	}
	@RequestMapping(value="/courseUpdate",method = RequestMethod.GET)
	 public ModelAndView courseUpdate(@RequestParam int id, ModelMap model) {
	     List<CourseModel> list = courseDao.courseId(id);
	     model.addAttribute("courses", list);
	     return new ModelAndView("courseUpdate", "course", new CourseModel());
	 }
	
	@RequestMapping(value = "/couUpdate", method = RequestMethod.POST)
	 public String updateCourse(@ModelAttribute("course") CourseModel course,
	                         
	                          ModelMap model) throws IOException {
			
	     int status = courseDao.updateCourse(course);
	    
	     if (status == 1) {
	         model.addAttribute("success", "Update Successful<^^>");
	         return "menu";
	     } else {
	         model.addAttribute("error", "Update Failed!");
	         return "courseUpdate";
	     }
	 }
		/*
		 * @RequestMapping(value = "/courseDelete", method = RequestMethod.GET) public
		 * String deleteUser(@RequestParam int id) {
		 * System.out.println("sdfdfvvvvvvvvvvvvvvvvvvvv"+id); List<Student_Course>
		 * student_Courses = courseImplement.getStudentCoursesById(id);
		 * for(Student_Course student_Course:student_Courses) { int i =
		 * courseImplement.stuCourseDelete(student_Course.getId()); } int status =
		 * courseDao.getCourseDelete(id);
		 * 
		 * return "courseManagement";
		 * 
		 * }
		 */
}
