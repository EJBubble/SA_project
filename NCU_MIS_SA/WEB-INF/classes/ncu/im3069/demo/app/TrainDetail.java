package ncu.im3069.demo.app;

import java.util.*;
import java.sql.*;
import java.sql.Date;
public class TrainDetail {

	private int id;
	
	private Date date;
	
	private String number;
	
	private int direction;
	
	private int stopSequence;
	
	private String stopName;
	
	private Time ArrivalTime;
	
	private Time DepartureTime;

	public TrainDetail(int id, Date date, String number, int directon, int stopSequence, 
			String stopName, Time ArrivalTime, Time DepartureTime) {
		
		this.id = id;
		this.date = date;
		this.number = number;
		this.direction = direction;
		this.stopSequence = stopSequence;
		this.stopName = stopName;
		this.ArrivalTime = ArrivalTime;
		this.DepartureTime = DepartureTime;
		
		
	}
	
	public int getID(){
		return this.id;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public String getNO(){
		return this.number;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public int getStopSequence() {
		return this.stopSequence;
	}
	
	public String getStopName() {
		return this.stopName;
	}
	
	public Time getArrivalTime() {
		return this.ArrivalTime;
	}
	
	public Time getDepartureTime() {
		return this.DepartureTime;
	}
}
	