<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="student_persistant.*,student_persistant.dao.UserDaoImplement,student_persistant.model.*,java.util.*"%>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"> -->
<%@ include file="header.jsp"%>
    
   <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.js"></script>
    
        <title>User Management</title>
         
</head>

<body>
<%@ include file="navbar.jsp"%>


<div  class="main_contents">
<div id="sub_content">
   <a href="generateReport?export=excel">excel</a>
<a href="generateReport?export=pdf">pdf</a>
    	
         <div class="container col-md-10 offset-md-0">
          <table id="example" class="display">
                    <thead>
                <c:if test="${urs!=null }">
                <tr>
                    
                    <th scope="col">User ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Details</th>
                    
                    
                </tr>
                </c:if>
            </thead>
            <tbody>
     
			<c:forEach items="${ur != null ? ur : urs}" var="u">
			<c:if test="${not empty u and 'User'.equals(u.role) }">
                <tr>
                    <td>USR${u.id}</td>
                    <td>${u.name }</td>
                    <td>${u.email}</td>
                    
                    
                     <td>
                    <button type="button" class="btn btn-success  " onclick="location.href='/StudentRegistrationJPA/userUpdate/${u.id}';" >
                        Update
                    </button> 
                    <button type="button" class="btn btn-danger " data-bs-toggle="modal"
                    data-bs-target="#exampleModal" onclick="deletedId('${u.id}')" ${u.name eq sessionScope.foundUser.name ? 'disabled':'' }>Delete</button>
                </td>
                 </tr>
                  </c:if>
                  <%-- 
                  <c:if test="${not empty u and 'User'.equals(u.role) }">
                <tr>
                    <td>USR${u.id}</td>
                    <td>${u.name }</td>
                    <td>${u.email}</td>
                    
                 </tr>
                  </c:if> --%>
    
               
                
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
            <form action="/StudentRegistrationJPA/userDelete" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                         <input type="hidden" name="id" id="userId">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
    
                    </div>
                    
                </div>
                </form>
            </div>
        </div>
        <%-- </c:if> --%>
                </c:forEach>
                
            </tbody>
        </table>
    
       </div>
       
</div>
</div>
       
 <script>
        $(document).ready(function() {
            $('#example').DataTable();
        });
    </script>
<script>
 function deletedId(value){
	 document.getElementById("userId").value = value;
 }

</script>
 <%@ include file="footer.jsp"%>
