
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="student_persistant.*,student_persistant.dao.UserDaoImplement,student_persistant.dao.CourseDaoImplement,student_persistant.dao.StudentDaoImplement,student_persistant.model.*,java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="header.jsp"%>
        <title>Student Delete and Update</title>

<%@ include file="navbar.jsp"%>


		<div class="main_contents">
    		<div id="sub_content">
          <form:form action="/StudentRegistrationJPA/studentUpdate" modelAttribute="student" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Update and Delete</h2>
            <h4 class="col-md-6 offset-md-2 mb-2 mt-2" style="color: red;">${error }</h4>
            <h4 class="col-md-6 offset-md-2 mb-2 mt-2" style="color: green;">${success }</h4>
        <%-- <c:forEach items="${stu }" var="stu"> --%>
        
            <div class="row mb-4">
                <div class="col-md-2"></div>
                
                <div class="col-md-4">
                     <input type="hidden" class="form-control"  name="id" id="studentId" value="${id}">
                </div>
            </div>
            
             <c:forEach items="${stu }" var="stu">
              <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="name" name="name" value="${stu.name }">
					</div>
					 <div id="nameError" class="col-md-4" style="color: red;"></div>
				</div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input  type="date" class="form-control" id="date" name="registration_date" value="${stu.registration_date }">
                </div>
                 <div id="dateError" class="col-md-4" style="color: red;"></div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                         <input <c:if test="${stu.gender=='male' }">checked</c:if> class="form-check-input" type="radio" name="gender" id="gridRadios1" value="male"
                            >
                        <label class="form-check-label" for="gridRadios1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <input <c:if test="${stu.gender=='female' }">checked</c:if> class="form-check-input" type="radio" name="gender" id="gridRadios2" value="female">
                        <label class="form-check-label" for="gridRadios2">
                            Female
                        </label>
                    </div>
				</div>
				 <div id="genderError" class="col-md-4" style="color: red;"></div>
            </fieldset>
    
            <div class="row mb-4" >
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="tel" class="form-control" id="phone" name="phone" value="${stu.phone }">
                </div>
                 <div id="phoneError" class="col-md-4" style="color: red;"></div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                   <select class="form-select" aria-label="Education" id="education" name="education">
                        <option <c:if test="${stu.education=='Bachelor of Information Technology' }">selected</c:if> value="Bachelor of Information Technology">Bachelor of Information Technology</option>
                        <option <c:if test="${stu.education=='Diploma in IT' }">selected</c:if> value="Diploma in IT">Diploma in IT</option>
                        <option <c:if test="${stu.education=='Bachelor of Computer Science' }">selected</c:if> value="Bachelor of Computer Science">Bachelor of Computer Science</option>
    
                    </select>
                </div>
                 <div id="educationError" class="col-md-4" style="color: red;"></div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
                
    
               
                  <div class="col-md-4">
				<c:set var="id" value="${stu.id}" />
				
				  <c:forEach items="${course_list }" var="list">
				   	 <div class="form-check-inline col-md-3">
				                        <input  class="form-check-input"  type="checkbox" value="${list.id}"
				                         <c:forEach var="st" items="${courseId}">  <c:if test="${st eq list.id  }">checked</c:if> </c:forEach>
				                         name="attend" id="attend" value="${list.course_name }"  multiple="multiple">
				                        <label class="form-check-label" for="gridRadios2">
				                           ${list.course_name }
				                        </label>
				                    </div>
				    </c:forEach>
                </div>
                 <div id="attendError" class="col-md-4" style="color: red;"></div>
                
    
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Photo</label>
                <div class="col-md-4">
                <div class="col-md-2"></div>
                <c:set var="photo" value="${stu.photo}" />
                <img  src="<c:url value="/assets/image/${photo}" />" alt="..."
								style="width: 70px; height: 70px; border-radius: 45%;">
                    <input type="file" class="form-control"  id="part" name="file" >
                </div>
                
                <div id="partError" class="col-md-4" style="color: red;"></div>
            </div>
    			 
              <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                   
                        <button type="submit" class="btn btn-secondary" >
                          
                            <span>Update</span>
                        </button>
                   
    
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                       
                       <span>Delete</span> 
                    </button>
    
                  
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="location.href = '/StudentRegistrationJPA/studentDelete/${stu.id}';">Ok</button>
                                    <button type="button" class="btn btn-danger" onclick="location.href = '/StudentRegistrationJPA/studentSearch';">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                   
                    
                </div>
    
            </div> 
               
    
    </c:forEach>
            </form:form>
            
       </div>
       </div>

<script>
    function validateForm() {
      /*   var studentId = document.getElementById("studentId").value; */
        var name = document.getElementById("name").value;
        var date = document.getElementById("date").value;
        var gender = document.querySelector('input[name="gender"]:checked');
        var phone = document.getElementById("phone").value;
        var education = document.getElementById("education").value;
        var attend = document.querySelectorAll('input[name="attend"]:checked');
        var part = document.getElementById("part").value;
  
            // Validate studentId
            /* if (studentId== "") {

            	document.getElementById("studentIdError").innerHTML = "Please enter Student ID";
                return false;
            } */
            
           

	/// Validate name
		if (name== "") {
			document.getElementById("nameError").innerHTML = "Please enter Name";
			return false;
		}

		// Validate date
		if (date== "") {
			document.getElementById("dateError").innerText = "Please enter Date ";
			return false;
		}

		// Validate gender
		if (!gender) {
			document.getElementById("genderError").innerText = "Please select Gender";
			return false;
		}

		// Validate phone
		if (phone== "") {
			document.getElementById("phoneError").innerText = "Please enter Phone";
			return false;
		}else if (!isValidPhone(phone)) {
			document.getElementById("phoneError").innerHTML = "Phone No is invalid !!!";
            isValid = false;
          }

		// Validate education
		if (education== "") {
			document.getElementById("educationError").innerText = "Please select Education";
			return false;
		}
		/* if (part== "") {
			document.getElementById("partError").innerText = "Select photo";
			return false;
		} */
		 function isValidPhone(phone){
	        	var phoneRegex=/[0]{1}[1,2,9]{1}[0-9]{9}/;
	        		return phoneRegex.test(phone);
	        }
		// Validate attend
		if (attend.length === 0) {
			document.getElementById("attendError").innerText = "Select at least one";
			return false;
		}

		document.getElementById("attendError").innerText = "";
		return true;
	}
    
    
</script>
    
<%@ include file="footer.jsp"%>
