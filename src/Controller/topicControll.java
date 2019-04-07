package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.topic;
import DAO.DAOTopic;
import DB.DBConnection;

/**
 * Servlet implementation class topicControll
 */
@WebServlet("/topicControll")
public class topicControll extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
       Connection conn=DBConnection.getJDBCConnection();
      
       String service= request.getParameter("service");
       
       //setting values default for service
       if(service==null)
       {
    	   service="viewpage";
       }
       
       //pagination Topic
       if(service.equals("viewpage"))
        {
    	 int count;
   		String countstr=request.getParameter("row");
   		String pageidstr=request.getParameter("pageid");
   		int pageid=Integer.parseInt(pageidstr);	
   		
   		if(countstr == null||countstr.equals("")||countstr.equals("0"))
   		{
   			count=2;
   		}
   		else
   		{
   			count=Integer.parseInt(countstr);
   		}
   		HttpSession session=request.getSession();
   		session.setAttribute("rowt",count);
   		
   		if(pageid == 1)
   		{
   			//1 trang thi ko phan trang
   		}
   		else
   		{	//tu 2 trang tro nen(CT phan trang)
   			pageid = pageid-1;
   			pageid = pageid*count+1;
   		}
   		//so dong nguoi dung nhap lon hon so ban ghi
   		int sumrow=DAOTopic.Getcoutrow(conn);
   		if(count>sumrow)
   		{
   			count=sumrow;
   			
   		}else
   		{
   			count=count;
   		}
   		List<topic> list =DAOTopic.displayTopic(conn, request,pageid,count);//start tu 0 start=pageid-1
   		//goi ham dem row
   	
   		int maxpage=(sumrow/count)+1;
   		request.setAttribute("maxpage",maxpage);
   		request.setAttribute("listtopic",list);
   		request.setAttribute("numberid", Integer.parseInt(pageidstr));
   			 
   		RequestDispatcher rd=request.getRequestDispatcher("View/Admin/Topoic.jsp");
   		rd.forward(request, response);
   		try {
   			conn.close();
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
        }
       
       //Delete group topic 
       if(service.equals("deletegrouptopic"))
       {
    		
    		//list chua id trong bang topic
    			List<Integer> li=DAOTopic.getID(conn);
    		//tim kiem id ketq nguoi dung tich		
    		for(int i=0;i<li.size();i++)
    		{
    			
    				String strid=request.getParameter("la["+li.get(i)+"]");			
    				if(strid==null)//truong ko dk chon tra ve null
    				{
    					
    					continue;
    				}else
    				{
    					int id=Integer.parseInt(strid);
    					
    					int kt=DAOTopic.DeleteTopic(conn, id);
    					if(kt!=0)
    					{
    						request.setAttribute("id","xoa thanh cong");
    					}
    				}		
    		}

    			RequestDispatcher rd=request.getRequestDispatcher("Topicforward?pageid=1");
    			rd.forward(request, response);
    			
    		}
       		//delete row list topic
	       if(service.equals("deleterow"))
	       {
	    	   
	    	   String idstr=request.getParameter("topicid");
		   		int id=Integer.parseInt(idstr);
		   		int kt=DAOTopic.DeleteTopic(conn, id);
	   		if(kt!=0)
	   		{
	   			request.setAttribute("id","xoa thanh cong");
	   		}
	   		RequestDispatcher rd=request.getRequestDispatcher("Topicforward?pageid=1");
	   		rd.forward(request, response);
	       }
	       
	       if(service.equals("addtopic"))
	       {
	    	   RequestDispatcher rd=request.getRequestDispatcher("View/Admin/insert.jsp");
		   		rd.forward(request, response);
	       }
	       //insert topic
	      
        
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 	
	}

}
