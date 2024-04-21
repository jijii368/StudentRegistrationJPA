<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="student_persistant.*,student_persistant.dao.CourseDaoImplement,student_persistant.model.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>Course Management</title>
</head>

<body>
<%@ include file="navbar.jsp"%>
<%
CourseDaoImplement cdao = new CourseDaoImplement();
	List<CourseModel> srs=cdao.allCourse();
	request.setAttribute("list", srs);
%>
    <div class="main_contents">
    <div id="sub_content">
        
        	<h3 style="color: red">${error }</h3>
           
       
    	
        <table class="table table-striped" id="stduentTable">
            <thead>
                <c:if test="${list!=null }">
                <tr>
                    
                    <th scope="col">Course ID</th>
                    <th scope="col">Course Name</th>
                   <!--  <th scope="col">Details</th> -->
                    
                </tr>
                </c:if>
            </thead>
            <tbody>
            <c:forEach items="${list }" var="u">
                <tr>
                    <td>COU${u.id}</td>
                    <td>${u.course_name }</td>
                    
                     <%-- <td>
         
                    <button type="button" class="btn btn-danger mb-3" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" onclick="deletedId('${u.id}')">Delete</button>
                    </td> --%>
                    
    
                
    
                </tr>
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
             <form action="/StudentRegistrationJPA/courseDelete" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Course Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                         <input type="hidden" name="id" id="courseId">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success col-md-2" data-bs-dismiss="modal" >Ok</button>
    
                    </div>
                    
                </div>
                </form>
            </div>
        </div>
                </c:forEach>
            </tbody>
        </table>
    
       
       
    </div>
</div>
<script>
 function deletedId(value){
	 document.getElementById("courseId").value = value;
 }

</script>
 <%@ include file="footer.jsp"%>
 <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
 

