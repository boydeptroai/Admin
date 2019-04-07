<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Kanji study </title>

    <!-- Bootstrap -->
    <link href="./Template/css/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="./Template/css/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="./Template/css/vendors/nprogress/nprogress.css" rel="stylesheet">
    
    <!-- Custom Theme Style -->
    <link href="./Template/css/build/css/custom.min.css" rel="stylesheet">
    <!-- link list table -->
    
  	  <link rel="stylesheet" href="./Template/List/css/font-awesome.min.css"> 
      <link rel="stylesheet" href="./Template/List/css/main.css">
 	 <link rel="stylesheet" href="./Template/List/style.css">    
     <link rel="stylesheet" href="./Template/List/css/responsive.css"> 
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
      										<!--Begin-Menuleft -->
        
        <jsp:include page="Menuleft.jsp"/>
        									<!-- End-Menuleft -->

       										<!--Begin header  -->
         <jsp:include page="HeaderAdmin.jsp"/> 
        									<!--End Header    -->

        
         <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
          	 <div class="page-title">
          	              <div class="title_left">
          	                <h3>Kanji<small> Just add class <strong>footer_fixed</strong></small></h3>
          	              </div>
          	            </div>
              <div class="clearfix"></div>

              <div class="row">  

                                                      <!--   Begin content -->
             <div class="product-status mg-tb-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                             <h4>Kanji mới</h4></br>
                             <c:if test="${message!=null}">
                           				 ${message}
                           	
                             </c:if>
                             	<form action="topicControll" method="get">
								<input type="hidden" name="pageid" value="1">
								Số bản ghi: <input type="text" name="row" required="required" style="height: 27px;">
								<input type="submit" value="ok">
								 <input type="hidden" name="service" value="viewpage"/>
							</form>
                            <div class="add-product" style="background: #555555;">
                                <a href="topicControll?service=addtopic">Thêm </a>
                            </div>
                             <div class="x_content">
                          <%=request.getAttribute("id")!=null?request.getAttribute("id"):"" %>
                             <form action=deleteControler method="post">
                            <table id="datatable-checkbox" class="table table-striped table-bordered bulk_action">
                                <tr>
                                	<th><input type="checkbox" id="check-all" class="flat" style="width: 25px;height: 25px;"></th>
                                    <th>ID ảnh</th>
                                    <th>Ảnh chủ đề</th>
                                    <th>Tên chủ đề</th>
                                     <th>Tên chủ đề</th>
                                    <th>Chỉnh sửa</th>
                                    
                                   
                                </tr>
                                
                               <c:forEach items="${listtopic}" var="list">
                              
                                <tr>
                                  
                                	 <th><input type="checkbox" id="check-all" class="flat" name="la[${list.topicid}]" value="${list.topicid}" style="width: 20px;height: 20px;"></th>
                                  
                                    <td>${list.topicid}</td>
                                    <td>${list.name}</td>                                  
                                    <td><img alt="" src="./imagetopic/${list.img}"></td>
                                    <td>${list.guide}</td>
                                
                                    <td>
                                       <a href="topicControll?topicid=${list.topicid}&service=deleterow" class="fa fa-trash-o" aria-hidden="true"  title="Trash" onclick="return(confirm('Vui lòng xác nhận'))" style="width: 40%; height: 40px;line-height:40px;left:20px;"></a>
                                        
                                   		<a href="DeleteonerowController?topicid=${list.topicid}" class="fa fa-pencil-square-o" aria-hidden="true" title="Edit" style="width: 45%; height: 40px;line-height:40px;"></a>
                                    </td> 
                                </tr>  
                                    </c:forEach>
                            </table>
                            <input type="hidden" name="service" value="deletegrouptopic"/>
                            <input type="submit" value="DELETE"/>
                            </form>
                        </div>
                            <jsp:include page="paginationTopic.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
                    </div> 
                    		<!--End content  -->
           </div></div></div>
        <!-- /page content -->

       										<!--Begin Footer  -->
         <jsp:include page="footerAdmin.jsp"/> 
        									<!--End Footer  -->
      </div>		
    </div>

    <!-- jQuery -->
    <script src="./Template/css/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="./Template/css/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="./Template/css/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="./Template/css/vendors/nprogress/nprogress.js"></script>
    <!-- validator -->
    <script src="./Template/css/vendors/validator/validator.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="./Template/css/build/js/custom.min.js"></script>
  
  </body>
</html>