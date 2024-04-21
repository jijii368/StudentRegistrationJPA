
<%@page import="java.util.List"%>
<%@page import="student_persistant.dao.UserDaoImplement"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="student_persistant.*,student_persistant.dao.UserDaoImplement,student_persistant.model.UserModel" %>



<div id="testheader">
	<div class="container">
		<div class="row mb-5">
			<div class="col-md-5">
				<a href="/StudentRegistrationJPA/student"><h3>Student Registration</h3></a>
			</div>
			
			
			
			<div class="col-md-4">
			
				<p>${sessionScope.role}: ${sessionScope.name}</p>
				
				<p>
				<%
            Date currentDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = formatter.format(currentDate);
            out.println(formattedDate);
          %>
			</p>
			
			</div>
			<div class="col-md-1">
				<input type="button" class="btn btn-secondary" id="lgnout-button"
					value="Log Out"
					onclick="location.href='/StudentRegistrationJPA/logout'">
			</div>
		</div>
	</div>
</div>
<c:if test="${sessionScope.role=='Admin'}">

	<div class="sidenav">

		<button class="dropdown-btn">
			Class Management <i class="fa fa-caret-down"></i>
		</button>

		<div class="dropdown-container">
			<a href="/StudentRegistrationJPA/course">Course Registration </a>
			<a href="/StudentRegistrationJPA/allCourse">Course Management</a> <a
				href="/StudentRegistrationJPA/student">Student Registration </a>
			<a href="/StudentRegistrationJPA/studentSearch">Student Search
			</a>
		</div>
		<a href="/StudentRegistrationJPA/userSearch">Users Management</a>
	</div>


	<script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</c:if>
<c:if test="${sessionScope.role=='User'}">

	   
<div class="sidenav">

		<a class="btn btn-secondary" onclick="location.href='/StudentRegistrationJPA/userUpdate/${sessionScope.foundUser.id}';">UpdateProfile</a>
		<a class="btn btn-secondary" href="/StudentRegistrationJPA/student">Student Registration </a>
		
		</div>
		
		<script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</div>
</c:if>