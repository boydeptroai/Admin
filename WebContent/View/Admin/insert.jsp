<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="./Template/ckeditor/ckeditor.js"></script>
    
    <!-- Custom Theme Style -->
    <link href="./Template/css/build/css/custom.min.css" rel="stylesheet">
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
             <div class="clearfix"></div> 
              <div class="row">  
               <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                             <h4>Kanji mới</h4></br>  
                             
              									 <!--    content -->  
             		 <%=request.getAttribute("messageinsert") %>  
             
             <form method="post" action="insertTopicController" enctype="multipart/form-data" name="test" style="width: 100%;">
           	 <table border="0">
    			  <tr>
                    <td>Ten Chu de: </td>
                    <td><input type="text" name="name" size="50"/></td>
              	  </tr>
               	  <tr>
                    <td>Noi dung: </td>
                     <td><textarea class="ckeditor" name="nd" cols="80" rows="10"></textarea>
                    <!--  <td><input type="text" name="nd" size="50"/></td> -->
                </tr>
                  <tr>
                    <td>uploatfile: </td>
                    <td><input type="file" name="file" size="50"/></td>
                    
                    
                    
                </tr>
                <tr>
                    <td colspan="2">
                    	 <input type="hidden" name="service" value="addtopic"/>
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
           
            
        </form>                               
            	  </div>
               </div>
             </div>
          </div>
        </div>
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