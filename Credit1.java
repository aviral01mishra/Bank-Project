import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class Credit1 extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String accountno=req.getParameter("accountno");
		long credit=Long.parseLong(req.getParameter("amount"));
		
	
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s1=con.createStatement();
		    ResultSet rs=s1.executeQuery("select balance from accountdata where accountno='"+accountno+"'");
			while(rs.next()){
				
			      long amount=Long.parseLong(rs.getString(1));
			
			      long newbalance=amount+credit;
			      Statement s=con.createStatement();
			      s.executeUpdate("update  accountdata set balance="+newbalance+" where accountno='"+accountno+"'");
				  out.println("new balance is" +newbalance);
			}
			RequestDispatcher disp=req.getRequestDispatcher("AfterLogin.html");
				disp.include(req,res);
			
			
			
			
		}
		catch(Exception e){System.out.println(e);}
	}
}