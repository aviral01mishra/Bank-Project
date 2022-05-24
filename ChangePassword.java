import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class ChangePassword extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException{
	    res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String pass3=req.getParameter("passname3");
		String pass1=req.getParameter("passname1");
	    String pass2=req.getParameter("passname2");
		try{
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","password");
			Statement s=con.createStatement();
			
			if(pass1.equals(pass2)){
			s.executeUpdate("update employee set password='"+pass1+"' where password='"+pass3+"'");
			RequestDispatcher disp=req.getRequestDispatcher("index.html");
			out.println("password updated login again");
			disp.include(req,res);
			
			}
			else{
				JOptionPane.showMessageDialog(null,"passwords do not match","Alert message",JOptionPane.WARNING_MESSAGE);
					RequestDispatcher dispatcher=req.getRequestDispatcher("Changepassword.html");
					dispatcher.forward(req,res);
			}
		}
		catch(Exception e){}
	
	}
}
	
	
	