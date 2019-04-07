package Controller;

import java.io.IOException;
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
 * Servlet implementation class Topicforward
 */
@WebServlet("/Topicforward")
public class Topicforward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Topicforward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn=DBConnection.getJDBCConnection();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
