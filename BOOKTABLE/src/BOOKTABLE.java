

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class BOOKTABLE
 */
@WebServlet("/BOOKTABLE")
public class BOOKTABLE extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql=request.getParameter("x");
		String result="";
		
		try{  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			 
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","system","root");  
			  
			
			Statement stmt=con.createStatement();  
			  
		
			
	        ResultSet rs = stmt.executeQuery(sql);

	        
	        result=rs.toString();
	        
			//step5 close the connection object  
			con.close();  
			  
			}catch(Exception e){ System.out.println(e);}  
			  
			 
			 Gson gson=new Gson();
		        gson.toJson(result, response.getWriter());
		
			
			

		
	}

}
