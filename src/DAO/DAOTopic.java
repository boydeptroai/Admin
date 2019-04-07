package DAO;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileUploadIOException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.MultipartStream.MalformedStreamException;

import BEAN.topic;

public class DAOTopic {
	
	//View list topic
	public static List<topic> displayTopic(Connection conn,HttpServletRequest request,int start,int count)
	{
		List<topic> list=new ArrayList<topic>();
		PreparedStatement ptmt=null;
		String sql="SELECT * FROM tbl_topic LIMIT "+(start-1)+" ,"+count+"";
		try {
			ptmt=conn.prepareStatement(sql);
			//previous chay tu cuoi len dau cho sqlsver
		//	ptmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs= ptmt.executeQuery();
			if(rs.isBeforeFirst())//db co dulieu
			{
				while(rs.next())
				{
					 topic tp=new topic();
					 int topicid=rs.getInt("topic_id");
					 String topicname=rs.getString("topic_name");
					 String topicimg=rs.getString("topic_img");
					 String topicguide=rs.getString("topic_guide");
					 tp.setTopicid(topicid); 
					 tp.setImg(topicimg);
					 tp.setName(topicname);
					 tp.setGuide(topicguide);
					 
					 list.add(tp);
				}
			}
			else
			{
				request.setAttribute("message","khong co chu de nao");
			}
			
			ptmt.close();
			rs.close();
			// previous chay tu cuoi len dau
		/*	if(rs.isBeforeFirst())
			{
				while(rs.previous())
				{
					 topic tp=new topic();
					 int topicid=rs.getInt("topic_id");
					 String topicname=rs.getString("topic_name");
					 String topicimg=rs.getString("topic_img");
					 String topicguide=rs.getString("topic_guide");
					 tp.setTopicid(topicid); 
					 tp.setImg(topicimg);
					 tp.setName(topicname);
					 tp.setGuide(topicguide);
					 
					 list.add(tp);
				}
			}
			else
			{
				request.setAttribute("message","khong co chu de nao");
			}
			
			ptmt.close();
			rs.close();*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("message",e.getMessage());
			}
		
		return list;
		
	}

	//inserttopic
	public static boolean insertTopic( Connection conn,HttpServletRequest request,String file,String name,String nb)
	{
		PreparedStatement ptmt=null;
		String sql="insert into tbl_topic(topic_name,topic_guide,topic_img) values(?,?,?)";
		try {
			ptmt=conn.prepareStatement(sql);
			
			//String tpname=tp.getName();
			//String tpguide=tp.getGuide();
		//	String tpimg=tp.getImg();
			
			ptmt.setString(1,name);
			ptmt.setString(2,nb);
			ptmt.setString(3,file);
			
			int kt=ptmt.executeUpdate();
			if(kt!=0)
			{
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("messageinsert", e.getMessage());
		}
		return false;
	}
	
	//upload file
	public static void uploadFile(HttpServletRequest request,HttpServletResponse response,Connection conn) throws IOException
	{	/*final String address="E:\\";*/
		
		ServletContext context=request.getServletContext();//goi realpath
		PrintWriter out=response.getWriter();
		final String address=context.getRealPath("/imagetopic/");
		
		final int MaxMemorySize=1024*1024*3; //3MB
		final int MaxRequestSize=1024*1024*50;//50MB
		
		
		// Check that we have a file upload request--khong co ectype trong form upload
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart)
		{
			request.setAttribute("messageinsert","Not have:multipart/form-data trong form");
			//out.println("Not have:multipart/form-data trong form");
		}
		else
		{
			// Create a factory for disk-based file items--tao bo nho tam
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			//kick co vung nho tam va dia chi cua no
			factory.setSizeThreshold(MaxMemorySize);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			// Create a new file upload handler--su ly upload
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// Set overall request size constraint--kich thuong file upload
			upload.setSizeMax(MaxRequestSize);
			// Parse the request--list chua file upload
		
			
	        try {
	            // parses the request's content to extract file data
	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	           
	            //list khac null
	            if (formItems != null && formItems.size() > 0) {
	                // iterates over form's fields
	                for (FileItem item : formItems) {
	                	
	                    // processes only fields that are not form fields
	                	
	                    if (!item.isFormField()) {
	                    	String filename = new File(item.getName()).getName();//lay ten file 
	                        String filePath = address + File.separator + filename;//dia chi luu file
	                        File storeFile = new File(filePath);
	                        request.setAttribute("filename",filename);
	                      
	                    
	                        //kiem tra file ton tai chua
	                        boolean kt=storeFile.exists();
	                        if(kt==true)
	                        {
	                        	request.setAttribute("messageinsert","file exists");
	                        //	out.println("file exists");
	                        }
	                        else
	                        {
	                        // saves the file on disk
	                        item.write(storeFile);
	                       
	                      
	                        }
	                        
	                    }else 
	                    {
	                    	 String name = item.getFieldName ();
	                    	
	                    	 
	                    	 if(name.equals("name"))
	                    	 {
	                    		 String value = item.getString ();
	                    		 request.setAttribute("name",value);
	                    	 }
	                    	 if(name.equals("nd"))
	                    	 {
	                    		 
		                    		 String value1 = item.getString ();
		                    		 request.setAttribute("name1",value1);
		                    	 
	                    	 }
	                    	 if(name.equals("service"))
	                    	 {
	                    		 String value3 = item.getString ();
	                    		 request.setAttribute("addtopic",value3);
	                    	 }
	                    	 
	                    }
	                    
	                    
	                }
	            }
	        } catch (Exception ex) {
	            request.setAttribute("messageinsert","There was an error: " + ex.getMessage());
	        	
	        }
			
		}
		
	}	
		//delete  topic
	public static int  DeleteTopic(Connection conn, int id)

	{	PreparedStatement ptmt=null;
		int n=0;
		String sql="DELETE FROM tbl_topic where topic_id=?";
		try {
			ptmt=conn.prepareStatement(sql);
			ptmt.setInt(1,id);
			n=ptmt.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
		
		return n;
		
	}
	//get count row
	public static int Getcoutrow(Connection conn)

	{int n=0;
		PreparedStatement ptmt=null;
		String sql="select count(*)from tbl_topic";
		try {
			ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			rs.next();
			n=rs.getInt(1);
			ptmt.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	return n;	
	}
	//list getID user
	public static List<Integer> getID(Connection conn)
	{
		List<Integer> li= new ArrayList<Integer>();
		PreparedStatement ptmt=null;
		String sql="select topic_id from tbl_topic";
		try {
			ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			if(rs.isBeforeFirst())
			{
				//IDTopicofuser id=new IDTopicofuser();
				while(rs.next())
				{	int id=rs.getInt("topic_id");
					//id.setId(rs.getInt("topic_id"));
					li.add(id);
				}
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return li;
	}
	public static topic viewTopicByID(Connection conn, int id)
	{	PreparedStatement ptmt=null;
		topic tp=new topic();
		int n=0;
		String sql="select *from tbl_topic where topic_id=?";
		try {
			ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			
			if(rs.next())
			{
				tp.setName(rs.getString("topic_name"));
				tp.setGuide(rs.getString("topic_guide"));
				tp.setImg(rs.getString("topic_img"));
				tp.setTopicid(rs.getInt("topic_id"));
				return tp;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tp;
	}
}
