package ncu.im3069.demo.app;

import org.json.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;


public class Train {

	private int id;
	
	private String Number;
	
	private String TypeName;
	
	private String TypePrice;
	
	private String StartingStation;
	
	private String EndingStation;
	
	private Time StartingStationDepartureTime;
	
	private Date TrainDate;
	
	public Train(int id, String number, String TypeName, String TypePrice, String StartingStation, String EndingStation,
			Time time, Date date){
		
		this.id = id;
		this.Number = number;
		this.TypeName = TypeName;
		this.TypePrice = TypePrice;
		this.StartingStation = StartingStation;
		this.EndingStation = EndingStation;
		this.StartingStationDepartureTime = time;
		this.TrainDate = date;
		
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getNO(){
		return this.Number;
	}
	
	public String getTypeName(){
		return this.TypeName;
	}
	
	public String getTypePrice(){
		return this.TypePrice;
	}
	
	public String getStartStation(){
		return this.StartingStation;
	}
	public String getEndingStation(){
		return this.EndingStation;
	}
	public Time getTime(){
		return this.StartingStationDepartureTime;
	}
	public Date getDate(){
		return this.TrainDate;
	}
}
