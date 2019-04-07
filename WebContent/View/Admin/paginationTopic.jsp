<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PaginationTopic</title>
</head>
<body>
				<input type="hidden" name="pageid" value="1">
 						
                            
                        <div class="custom-pagination">
                          <nav aria-label="Page navigation example">
                            <c:if test="${numberid==1}">
                            
						<ul class = "pagination">
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=1&row=<%=session.getAttribute("rowt")%>" >1</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=2&row=<%=session.getAttribute("rowt")%>">2</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=${numberid+1}&row=<%=session.getAttribute("rowt")%>">Next</a></li>
						</ul>
						</c:if>
						<c:if test="${numberid==maxpage}">
						<ul class = "pagination">
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=${numberid-1}&row=<%=session.getAttribute("rowt")%>">Previous</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=1&row=<%=session.getAttribute("rowt")%>">1</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=2&row=<%=session.getAttribute("rowt")%>">2</a></li>
						   
						</ul>
						</c:if>
						<c:if test="${numberid>1&&numberid<maxpage}">
						<ul class = "pagination">
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=${numberid-1}&row=<%=session.getAttribute("rowt")%>">Previous</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=1&row=<%=session.getAttribute("rowt")%>">1</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=2&row=<%=session.getAttribute("rowt")%>">2</a></li>
						   <li  class="page-item"><a class="page-link" href = "Topicforward?pageid=${numberid+1}&row=<%=session.getAttribute("rowt")%>">Next</a></li>
						</ul>
						</c:if>
						</nav>
                        </div>
                           
</body>
</html>