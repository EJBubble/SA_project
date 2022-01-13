package ncu.im3069.demo.app;

import java.sql.*;


public class Book {
	
	private int id;
	
	private String departureStation;
	
	private String arrivalStation;
	
	private int amount;
	
	private int price;
	
	private Time departureTime;
	
	private Time arrivalTime;
	
	private Date trainDate;
	
	private Timestamp bookDate;
	
	private int status= 0;
	
	private int train_id;
	
	private int member_id;
	
	public Book(int id, String dStation, String aStation, int amount, int price, 
			Time dTime, Time aTime, Date trainDate, 
			Timestamp bookDate, int status, int trainId, int memberId){
		
		this.id = id;
		this.departureStation = dStation;
		this.arrivalStation = aStation;
		this.amount = amount;
		this.price = price;
		this.departureTime = dTime;
		this.arrivalTime = aTime;
		this.trainDate = trainDate;
		this.bookDate = bookDate;
		this.status = status;
		this.train_id = trainId;
		this.member_id = memberId;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getDepartureStation(){
		return this.departureStation;
	}
	
	public String getArrivalStation(){
		return this.arrivalStation;
	}
	
	public int getAmount() {
		return this.amount;
	}
	public int getPrice(){
		return this.price;
	}
	
	public Time getDepartureTime() {
		return this.departureTime;
	}
	
	public Time getArrivalTime() {
		return this.arrivalTime;
	}
	
	public Date getTrainDate(){
		return this.trainDate;
	}
	
	public Timestamp getBookDate() {
		return this.bookDate;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public int getMemberId() {
		return this.member_id;
	}
	
	public int getTrainId() {
		return this.train_id;
	}
	

	
	
}
