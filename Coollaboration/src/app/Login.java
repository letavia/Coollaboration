package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isCorrect;

    public login() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		    String email = request.getParameter("email");
			String password = request.getParameter("password");

			isCorrect = verifyUser(email, password);
			response.setContentType("text/html");
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(arg0);
	}

	public boolean verifyUser(String email, String password){
		isCorrect = false;
		Connection con;

		try{
			con = new Dao().connectDB();
			String query="select email, password from user where email='"+email+"'";
			ResultSet rs=con.createStatement().executeQuery(query);
			while (rs != null)
				if (password.equals(rs.getString("password"))) {
					isCorrect = true;
					//HttpSession session=request.getSession();

			}
			//ResultSet rs=preparedStmt.executeQuery("select username, password, AES_DECRYPT(password, username) from users where user_id=1");
			con.close();
			}
			catch(Exception e){}

		return isCorrect;
	}

}






