package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import BEAN.topic;
import DAO.DAOTopic;
import DB.DBConnection;

/**
 * Servlet implementation class insertTopicController
 */
@WebServlet("/insertTopicController")

public class insertTopicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertTopicController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		Connection conn=DBConnection.getJDBCConnection();						
	try 
	{
		
		DAOTopic.uploadFile(request, response,conn);
		String file=(String)request.getAttribute("filename");
		String name=(String)request.getAttribute("name");
		System.out.println(name);
		String nd=(String)request.getAttribute("name1");
		boolean kt= DAOTopic.insertTopic(conn, request, file, name, nd);
		if(kt)
		{	request.setAttribute("messageinsert","success");	
		}

		conn.close();
	} catch (SQLException e) {

		request.setAttribute("messageinsert", e.getMessage());
	}
	RequestDispatcher rd=request.getRequestDispatcher("View/Admin/insert.jsp");
	rd.forward(request, response);

}
}
