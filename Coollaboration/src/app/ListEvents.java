package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import app.Dao;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ListEvents
 */
@WebServlet("/ListEvents")
public class ListEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao db = new Dao();

    public ListEvents() {
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray list;
		String user = request.getParameter("user");
	    String visibility = request.getParameter("visibility");
	    boolean liked = (boolean) request.getAttribute("liked");
	    int listCode = (int) request.getAttribute("code");
	    //int listCodeNum = Integer.valueOf(request.getParameter("code"));

        switch (listCode) {
        case 1:  list = listMySkillsEvents(user);
                 break;
        case 2:  list = listMyPublicEvents(user, visibility);
                 break;
        case 3:  list = listMyPrivateEvents(user, visibility);
                 break;
        case 4:  list = listLikedEvents(user, liked);
                 break;
        case 5:  list = listAll();
        		 break;
    }
	}



	public JSONArray listMySkillsEvents(String user) {
		JSONArray list = null;
		try {
			Connection con = db.connectDB();
			String listQuery = "select eventID, eventname, date, creator, skills, image from event where skills = " + "(select skills from user where user = " + user + ")";
			ResultSet rs = con.createStatement().executeQuery(listQuery);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i=1; i<=numColumns; i++) {
					String column_name = rsmd.getColumnName(i);
					obj.put(column_name, rs.getObject(column_name));
				}
				list.put(obj);
				}
			rs.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public JSONArray listMyPublicEvents(String user, String visibility){
		JSONArray list = null;
		try {
			Connection con = db.connectDB();
			String listQuery = "select eventID, eventname, date, creator, skills, image from event where visibility = public";
			ResultSet rs = con.createStatement().executeQuery(listQuery);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i=1; i<=numColumns; i++) {
					String column_name = rsmd.getColumnName(i);
					obj.put(column_name, rs.getObject(column_name));
				}
				list.put(obj);
				}
			rs.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public JSONArray listMyPrivateEvents(String user, String visibility){
		JSONArray list = null;
		try {
			Connection con = db.connectDB();
			String listQuery = "select eventID, eventname, date, creator, skills, image from event where visibility = private";
			ResultSet rs = con.createStatement().executeQuery(listQuery);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i=1; i<=numColumns; i++) {
					String column_name = rsmd.getColumnName(i);
					obj.put(column_name, rs.getObject(column_name));
				}
				list.put(obj);
				}
			rs.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public JSONArray listLikedEvents(String user, boolean liked){
		JSONArray list = null;
		try {
			Connection con = db.connectDB();
			String listQuery = "select eventID, eventname, date, creator, skills, image from event where user = " + user + " and liked = true";
			ResultSet rs = con.createStatement().executeQuery(listQuery);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i=1; i<=numColumns; i++) {
					String column_name = rsmd.getColumnName(i);
					obj.put(column_name, rs.getObject(column_name));
				}
				list.put(obj);
				}
			rs.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public JSONArray listAll(){
		JSONArray list = null;
		try {
			Connection con = db.connectDB();
			String listQuery = "select eventID, eventname, date, creator, skills, image from event";
			ResultSet rs = con.createStatement().executeQuery(listQuery);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i=1; i<=numColumns; i++) {
					String column_name = rsmd.getColumnName(i);
					obj.put(column_name, rs.getObject(column_name));
				}
				list.put(obj);
				}
			rs.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}



}
