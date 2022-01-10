package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import org.json.*;

import ncu.im3069.demo.util.DBMgr;

public class TrainHelper {
	
	private TrainHelper() {
        
    }
	
	/** 靜態變數，儲存MemberHelper物件 */
    private static TrainHelper th;
    
    /** 儲存JDBC資料庫連線 */
    private Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    private PreparedStatement pres = null;
    
	
	public static TrainHelper getHelper() {
        /** Singleton檢查是否已經有MemberHelper物件，若無則new一個，若有則直接回傳 */
        if(th == null) th = new TrainHelper();
        
        return th;
    }
	
	public JSONObject getTrain() {
		
	}
}
