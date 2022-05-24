import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;
public class NewAccount extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
	    String user=req.getParameter("txtname");
		String adhar=req.getParameter("adharno");
		String mobile=req.getParameter("mobileno");
		String email=req.getParameter("emailname");
		String father=req.getParameter("fathername");
		String accountype=req.getParameter("accounttype");
		String balance=req.getParameter("balanceno");
		String gender=req.getParameter("gendername");
		String update="";
		
		
        Random random=new Random();		
		String s="1234567890";
		char[] otp=new char[11];
		for(int i=0;i<11;i++){
			otp[i]=s.charAt(random.nextInt(s.length()));
			}
			
			String strArray[]=new String[otp.length];
			for(int i=0;i<otp.length;i++){
				strArray[i]=String.valueOf(otp[i]);
			}
			String s1=Arrays.toString(strArray);
			
			String res1="";
			for(String num:strArray){
				res1+=num;
			}
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			PreparedStatement ps=con.prepareStatement("insert into accountdata values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,user);
			ps.setString(2,res1);
			ps.setString(3,adhar);
			ps.setString(4,mobile);
			ps.setString(5,email);
			ps.setString(6,father);
			ps.setString(7,accountype);
			ps.setString(8,balance);
			ps.setString(9,gender);
			ps.setString(10,update);
			ps.executeUpdate();
			PreparedStatement ps1=con.prepareStatement("select * from accountdata where accountno='"+res1+"'");
		
			
				ResultSet rs=ps1.executeQuery();
				while(rs.next()){
					out.println("<br>name= "+rs.getString(1));
					out.println("<br>account no= "+rs.getString(2));
					out.println("<br>addhar= "+rs.getString(3));
					out.println("<br>mobileno= "+rs.getString(4));
					out.println("<br>email id= "+rs.getString(5));
					out.println("<br>fathername= "+rs.getString(6));
					out.println("<br>accountype= "+rs.getString(7));
					out.println("<br>balance= "+rs.getString(8));
					out.println("<br>gender= "+rs.getString(9));
				}
				RequestDispatcher disp=req.getRequestDispatcher("AfterLogin.html");
				disp.include(req,res);
			
				out.println("<br>registered succesfully");
			
		
			
			
			}
			catch(Exception e){out.println(e);}
			out.close();
			
			
		
	}
}