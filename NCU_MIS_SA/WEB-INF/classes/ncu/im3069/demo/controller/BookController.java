package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import ncu.im3069.demo.app.Book;
import ncu.im3069.demo.app.BookHelper;
import ncu.im3069.tools.JsonReader;
import java.sql.*;
/**
 * Servlet implementation class Book
 */
@WebServlet("/api/book.do")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private BookHelper bh =  BookHelper.getHelper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String id = jsr.getParameter("id");
        
        JSONObject query = bh.getByID(id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "會員資料取得成功");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        String str1 = jso.getString("mId");
        int mId = Integer.parseInt(str1);
        
        String str2 = jso.getString("trainNO");
        int trainNO = Integer.parseInt(str2);
        
        String dStation = jso.getString("departureStation");
        String aStation = jso.getString("ArrivalStation");
        String sStation = jso.getString("startingStation");
        String eStation = jso.getString("endingStaton");
        
        String str3 = jso.getString("departureTime");
        Time dTime = java.sql.Time.valueOf(str3);
        String str4 = jso.getString("arrivalTime");
        Time aTime = java.sql.Time.valueOf(str4);
        
        String str5 = jso.getString("amount");
        int amount = Integer.parseInt(str5);
        String str6 = jso.getString("price");
        int price = Integer.parseInt(str6);
        
        String str7 = jso.getString("date");
        Date date = java.sql.Date.valueOf(str7);
        String type = jso.getString("trainType");
        
        Book b = new Book(mId, trainNO, dStation, aStation, sStation, eStation, dTime, aTime, amount, price, date, type);
		
        JSONObject data = bh.create(b);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 註冊會員資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
		
	}
	 
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
