package ncu.im3069.demo.app;

import java.sql.*;
import java.sql.Date;

import org.json.JSONObject;

public class TrainSelect {

	private int id;
	
	private String number;
	
	private int stop1Sequence;
	
	private String stop1Name;
	
	private String stop1DepartureTime;
	
	private int stop2Sequence;
	
	private String stop2Name;
	
	private String stop2ArrivalTime;
	
	

	public TrainSelect(int id, String number, int stop1Sequence, String stop1Name, String stop1DepartureTime, 
			int stop2Sequence, String stop2Name, String stop2ArrivalTime) {
		
		this.id = id;
		this.number = number;
		this.stop1Sequence = stop1Sequence;
		this.stop1Name = stop1Name;
		this.stop1DepartureTime = stop1DepartureTime;
		this.stop2Sequence = stop2Sequence;
		this.stop2Name = stop2Name;
		this.stop2ArrivalTime = stop2ArrivalTime;

	}
	
	public int getID(){
		return this.id;
	}
	
	public String getNO(){
		return this.number;
	}
	
	public int getStop1Sequence() {
		return this.stop1Sequence;
	}
	
	public String getStop1Name() {
		return this.stop1Name;
	}
	
	public String getStop1DepartureTime() {
		return this.stop1DepartureTime;
	}
	
	public int getStop2Sequence() {
		return this.stop2Sequence;
	}
	
	public String getStop2Name() {
		return this.stop2Name;
	}
	
	public String getStop2ArrivalTime() {
		return this.stop2ArrivalTime;
	}
	
	public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getID());
        jso.put("trainNO", getNO());
        jso.put("sequence1", getStop1Sequence());
        jso.put("stop1", getStop1Name());
        jso.put("departureTime",getStop1DepartureTime());
        jso.put("sequence2", getStop2Sequence());
        jso.put("stop2", getStop1Name());
        jso.put("arrivalTime",getStop2ArrivalTime());
        
       
        
        
        return jso;
    }
}
	