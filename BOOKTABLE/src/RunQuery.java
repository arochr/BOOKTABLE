

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
 * Servlet implementation class RunQuery
 */
@WebServlet("/RunQuery")
public class RunQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql=request.getParameter("x");
		List<TO> result=new ArrayList<TO>();
		
		try{  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			 
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","system","root");  
			  
			
			Statement stmt=con.createStatement();  
			  
		
			
	        ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next())
	        {
	        	TO to=new TO();
	        	for(int i=0;i<9;i++)
	        	{
	        		to.getVal(rs.getString(i+2), i);
	        		
	           
	        	}
	        	result.add(to);
	        }
	        
			//step5 close the connection object  
			con.close();  
			  
			}catch(Exception e){ System.out.println(e);}  
			  
			 
			 Gson gson=new Gson();
		        gson.toJson(result, response.getWriter());
		
			
			

	}

}
