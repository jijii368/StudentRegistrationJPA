
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="student_persistant.*,student_persistant.dao.StudentDaoImplement,student_persistant.model.*,java.util.*"%>
<%@ page import="java.util.Base64" %>
<%@ include file="header.jsp"%>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.js"></script>
    <script>
        $(document).ready(function() {
            $('#example').DataTable();
        });
    </script>
        <title>Student Search</title>
</head>

<body>
<%@ include file="navbar.jsp"%>
<%
	StudentDaoImplement sdao = new StudentDaoImplement();
	 List<StudentModel> stu = sdao.getAllStudentInfo();

	request.setAttribute("srs", stu);
%>
      
<div class="main_contents">
    <div id="sub_content">
    <a href="generateReports?export=excel">excel</a>
<a href="generateReports?export=pdf">pdf</a>
<c:if test="${srs!=null }">
   
        <div class="container col-md-10 offset-md-0">
          <table id="example" class="display">
                    <thead>
                    
          <tr>
            <th scope="col">&#128526;</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
             <th scope="col">Photo</th>
            <th scope="col">Details</th>
          </tr>
          
        </thead>
        <tbody>
	      <c:forEach items="${list != null ? list :srs}" var="u" varStatus="status">
	          <tr>
	           <td>${status.index+1}</td>
				<td>STU${u.getId()}</td>
				<td>${u.getName()}</td>
				<td>
				<c:forEach items="${carMap[u.id]}"  var="c" varStatus="status">
					${c}
				<c:if test="${not status.last}">,</c:if>
				</c:forEach>
				</td>
				<td>
                              <c:choose>
                               <c:when test="${empty u.getPhoto()}">
                               <span>No Photo Available</span>
                               
                               </c:when>
                               <c:otherwise>
                                 <img src="data:image/**;base64,${Base64.getEncoder().encodeToString(u.getPhoto())}" alt="Student Photo" width="100px" height="100px">
                               </c:otherwise>
                              </c:choose>
                         </td>

				<td>
	              <a href="/StudentRegistrationJPA/stuUpdate/${u.getId()}" class="btn btn-secondary mb-2">See More</a> 
	            </td>
	           </tr>
	         </c:forEach>
          
         
          
        </tbody>
      </table>
      </div>
      </c:if>
    </div>
</div>

 <%@ include file="footer.jsp"%>      