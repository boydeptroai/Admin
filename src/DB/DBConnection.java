package DB;
import java.sql.*;
public class DBConnection {
	public static Connection getJDBCConnection()
	{	Connection conn=null;
		final String url="jdbc:mysql://localhost:3306/demoadmin?useUnicode=true&characterEncoding=UTF-8";
		final String user="root";
		final String password="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public ResultSet getData(String sql)
	{ 
		Connection conn=getJDBCConnection();
		ResultSet rs=null;
		PreparedStatement ptmt=null;
		try {
			ptmt=conn.prepareStatement(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public static void main(String[] args) {
		Connection connection=getJDBCConnection();
		if(connection!=null) {
			System.out.println("thanhcong");
		}else {
			System.out.println("thatbai");
		}
	}
}
