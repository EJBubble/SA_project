package ncu.im3069.demo.app;

import java.sql.*;

import org.json.JSONObject;


public class Book {
	
	private int id;
	
	private int member_id;
	
	private int trainNO;
	
	private String departureStation;
	
	private String arrivalStation;
	
	private String startingStation;
	
	private String endingStation;
	
	private String departureTime;
	
	private String arrivalTime;
	
	private int amount;
	
	private int totalPrice;
	
	private String trainDate;
	
	private String trainType;
	
	private Timestamp created;

	//Post
	public Book(int mID, int trainNO, String dStation, String aStation, 
			String sStation, String eStation, String dTime, String aTime, 
			int amount, int totalPrice, String trainDate, 
			String trainType){
		
		this.trainNO = trainNO;
		this.member_id = mID;
		this.departureStation = dStation;
		this.arrivalStation = aStation;
		this.startingStation = sStation;
		this.endingStation = eStation;
		this.departureTime = dTime;
		this.arrivalTime = aTime;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.trainDate = trainDate;
		this.trainType = trainType;
	}
	
	//Get
	public Book(int id, int mID, int trainNO, String dStation, String aStation, 
			String sStation, String eStation, String dTime, String aTime, 
			int amount, int totalPrice, String trainDate, 
			String trainType){
		
		this.id = id;
		this.trainNO = trainNO;
		this.member_id = mID;
		this.departureStation = dStation;
		this.arrivalStation = aStation;
		this.startingStation = sStation;
		this.endingStation = eStation;
		this.departureTime = dTime;
		this.arrivalTime = aTime;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.trainDate = trainDate;
		this.trainType = trainType;
	}
	
	
	public int getID(){
		return this.id;
	}
	
	public int getMId(){
		return this.member_id;
	}
	
	public int getTrainNO()
	{
		return this.trainNO;	
	}
	
	public String getDepartureStation(){
		return this.departureStation;
	}
	
	public String getArrivalStation(){
		return this.arrivalStation;
	}
	
	public String getStartingStation(){
		return this.startingStation;
	}
	
	public String getEndingStation(){
		return this.endingStation;
	}
	
	public String getDepartureTime() {
		return this.departureTime;
	}
	
	public String getArrivalTime() {
		return this.arrivalTime;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public int getTotalPrice(){
		return this.totalPrice;
	}
	
	public String getTrainDate(){
		return this.trainDate;
	}
	
	public String getTrainType() {
		return this.trainType;
	}
	
	public Timestamp getCreated() {
		return this.created;
	}

	public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("member_id", getMId());
        jso.put("trainNO", getTrainNO());
        jso.put("departureStation", getDepartureStation());
        jso.put("arrivalStation", getArrivalStation());
        jso.put("startingStation", getStartingStation());
        jso.put("endingStation", getEndingStation());
        jso.put("departureTime", getDepartureTime());
        jso.put("arrivalTime", getArrivalTime());
        jso.put("ticket_amount", getAmount());
        jso.put("ticket_price", getTotalPrice());
        jso.put("train_date", getTrainDate());
        jso.put("train_type", getTrainType());
         
        return jso;
    }
}
