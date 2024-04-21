<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="student_persistant.*,student_persistant.dao.CourseDaoImplement,student_persistant.model.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>Student Registration</title>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

<%@ include file="navbar.jsp"%></br>

<%
CourseDaoImplement cdao = new CourseDaoImplement();
	List<CourseModel> srs=cdao.allCourse();
	request.setAttribute("list", srs);
%>
		<div class="main_contents">
    		<div id="sub_content">
    		   <h4 class="col-md-6 offset-md-2 mb-2 mt-2" style="color: green;">${success }</h4>
         <form:form action="/StudentRegistrationJPA/studentCreate" modelAttribute="student" method="POST" enctype="multipart/form-data" >

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Registration</h2>
            <h4 class="col-md-6 offset-md-2 mb-2 mt-2" style="color: red;">${error }</h4>
           
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" oninput="validateName(this)" >
						<div id="nameError"  style="color: red;"></div>
					</div>
					 
					 <!-- <div id="error" class="col-md-2" style="color: red;"></div> -->
				</div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input  type="date" class="form-control" id="date" name="registration_date" oninput="validateDate(this)">
                    <div id="dateError"  style="color: red;"></div>
                </div>
                 
                 <!-- <div id="error" class="col-md-2" style="color: red;"></div> -->
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <input  class="form-check-input" type="radio" name="gender" id="gridRadios1" value="male" >
                            
                        <label class="form-check-label" for="gridRadios1">
                            Male
                        </label>
                       
                    </div>
               
                    <div class="form-check-inline">
                        <input  class="form-check-input" type="radio" name="gender" id="gridRadios2" value="female" >
                        <label class="form-check-label" for="gridRadios2">
                            Female
                        </label>
                        
                    </div>
                    <!-- <div id="genderError" class="col-md-4" style="color: red;"></div> -->
				</div>
				
				 
            </fieldset>
    
            <div class="row mb-4" >
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                
                <div class="col-md-4">
                    <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter Phone No" oninput="validatePhone(this)" maxlength="11">
                     <div id="error"  style="color: red;"></div>
                </div>
                 <!-- <div id="phoneError" class="col-md-4" style="color: red;"></div> -->
                
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select class="form-select" aria-label="Education" id="education" name="education" >
                        <option value="Bachelor of Information Technology">Bachelor of Information Technology</option>
                        <option  value="Diploma in IT">Diploma in IT</option>
                        <option  value="Bachelor of Computer Science">Bachelor of Computer Science</option>
    
                    </select>
                   
                </div>
                
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
              

                <div class="col-md-4">
				                    
				  <c:forEach items="${list }" var="list">
				   	 <div class="form-check-inline col-md-3">
				                        <input  class="form-check-input"  type="checkbox" name="attend" id="attend" value="${list.id }"  multiple="multiple" oninput="validateAttend(this)" >
				                        <label class="form-check-label" for="gridRadios2">
				                           ${list.course_name }
				                        </label>
				                        <div id="attendError"  style="color: red;"></div>
				                    </div>
				    </c:forEach>
				     
                </div>
              
    
    
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <form:label path="file" class="col-md-2 col-form-label">Photo</form:label>
                <div class="col-md-4">
                    <form:input type="file" class="form-control" id="file" path="file" onblur="validatePhoto(this)"/>
                    <div id="photoError" style="color: red;"></div>
                </div>
                <!-- <div id="partError" class="col-md-4" style="color: red;"></div> -->
                <!-- <div id="error" class="col-md-2" style="color: red;"></div> -->
            </div>
    			 
            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                    <button type="reset" class="btn btn-danger ">
    
                        Reset
                    </button>
                    <button type="button" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal" >
                        Add
                    </button>
                    
                   
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Student Registration</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5 style="color: rgb(127, 209, 131);">Sure Registered?</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
                               
                            </div>
                        </div>
                    </div>
            </div>
           
                </div>

    
            </div>
    
            </form:form>
            
       </div>
       </div>

<script>

function validateName(input) {
	  var nameRegex = /^[a-zA-Z ]{2,30}$/;
	  if (!nameRegex.test(input.value)) {
	    document.getElementById("nameError").innerText = "Name should contain only letters and space, and should be between 2 and 30 characters long.";
	    return false;
	  } else {
	    document.getElementById("nameError").innerText = "";
	    return true;
	  }
	}
	
function validateDate(input) {
	  var dateRegex = document.getElementById("date");
	  if (!dateRegex.value.trim()) {
	    document.getElementById("dateError").innerText = "Invalid date format. Should be in MM/DD/YYYY format.";
	    return false;
	  } else {
	    document.getElementById("dateError").innerText = "";
	    return true;
	  }
	}
/* function validateGender(input) {
	  var genderRegex = /^(Male|Female)$/;
	  if (!genderRegex.test(input.value)) {
	    document.getElementById("genderError").innerText = "Invalid gender.";
	    return false;
	  } else {
	    document.getElementById("genderError").innerText = "";
	    return true;
	  }
	} */
	
function validatePhone(input){
	input.value = input.value.replace(/[^0-9]/g,'');
	if(input.value.length < 7){
		document.getElementById("error").innerText  = "Phone number should be at least 7 digits!!"
		return false;
	}else{
		document.getElementById("error").innerText = ""
		return true;
	}
}

/* function validateEducation(input) {
	  if (input.value === "") {
	    document.getElementById("educationError").innerText = "Education is required.";
	    return false;
	  } else {
	    document.getElementById("educationError").innerText = "";
	    return true;
	  }
	} */
	function validateAttend() {
	    var checkboxes = document.querySelectorAll("input[name='attend']:checked");
	    if (checkboxes.length === 0) {
	        document.getElementById('attendError').innerText = 'Please enter valid course names separated by commas.';
	        return false;
	    } else {
	        document.getElementById('attendError').innerText = '';
	        return true;
	    }
	}

 function validatePhoto(input) {
	  var fileTypes = ['jpg', 'jpeg', 'png'];
	  var fileExtensions = input.value.toLowerCase().split('.').pop();
	  for (var i = 0; i < fileTypes.length; i++) {
	    if (fileExtensions == fileTypes[i]) {
	      document.getElementById("photoError").innerText = "";
	      return true;
	    }
	  }
	  document.getElementById("photoError").innerText = "Invalid file format. Accepted formats: jpg, jpeg, png.";
	  return false;
	} 
	
	

       
</script>
    
<%@ include file="footer.jsp"%>