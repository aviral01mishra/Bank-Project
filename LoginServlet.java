import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class LoginServlet extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
        String type=req.getParameter("type");
		String user=req.getParameter("txtname");	
		String password=req.getParameter("passname");
		HttpSession session=req.getSession();
		session.setAttribute("name",user);
		
		try{
			int flag=0;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();
			if(type.equals("User")){
			    ResultSet rs=s.executeQuery("select name,password from employee");
			
			         while(rs.next()){
				         if(user.equals(rs.getString(1))&& password.equals(rs.getString(2))){
					     out.println("welcome " +user);
					 
					     RequestDispatcher dispatcher=req.getRequestDispatcher("AfterLogin.html");
					     dispatcher.include(req,res);
					     flag=1;
					     break;
						 }
					 }
			
				
			  
			
				
				if(flag==0){
					JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					RequestDispatcher dispatcher=req.getRequestDispatcher("index.html");
					dispatcher.include(req,res);
				
				
				}
			}
			else{
				
				        if(user.equals("simran")&& password.equals("admin")){
					     out.println("welcome " +user);
					     RequestDispatcher dispatcher1=req.getRequestDispatcher("Accountdetails.html");
					     dispatcher1.forward(req,res);
						
						
						}
						else{
					    JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					    RequestDispatcher dispatcher=req.getRequestDispatcher("index.html");
					     dispatcher.include(req,res);
				
				
				}  
				
			}
		}
					
					
		
		catch(Exception e){}
			
	
	}
}














