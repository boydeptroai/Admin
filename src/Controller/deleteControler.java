package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.IDTopicofuser;
import BEAN.topic;
import DAO.DAOTopic;
import DB.DBConnection;

/**
 * Servlet implementation class deleteControler
 */
@WebServlet("/deleteControler")
public class deleteControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=DBConnection.getJDBCConnection();
		String service= request.getParameter("service");
		
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

}
