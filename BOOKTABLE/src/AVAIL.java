

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AVAIL
 */
@WebServlet("/AVAIL")
public class AVAIL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String date=request.getParameter("date");
		String time = request.getParameter("time");
		List<String> result=new ArrayList<String>();
		try{  
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","system","root");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		  
		//step4 execute query  
	/*	ResultSet rs=stmt.executeQuery("select * from emp");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  */
		
		
		String sql = "SELECT table_number FROM BOOK_TABLE where BOOK_DATE='"+date+"' and START_TIME="+time+" order by table_number";
		
        ResultSet rs = stmt.executeQuery(sql);

        //where name='"+data+"'
        // Extract data from result set
        
        while(rs.next())
        {
           result.add(rs.getString(1));
           System.out.println(rs.getString(1));
        }
        
        
		//step5 close the connection object  
		con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		 
		 Gson gson=new Gson();
	        gson.toJson(result, response.getWriter());
	
		
		
		
	}

}
