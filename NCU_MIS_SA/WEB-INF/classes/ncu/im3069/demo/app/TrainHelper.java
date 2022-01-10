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
	
	public JSONObject getTrain(String departureStation, String arrivalStation ,Time time1, Time time2) {
		
		TrainSelect ts = null;
		
		JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
		
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT `traindetail`.`ID`, `traindetail`.`TrainNO`, \r\n" + 
            		"`traindetail`.`StopSequence` AS 'Stop1S',`traindetail`.`StopName` AS 'Stop1', `traindetail`.`StopDepartureTime` AS 'Stop1_DT', \r\n" + 
            		"`traindetail2`.`StopSequence` AS 'Stop2S' ,`traindetail2`.`StopName` AS 'Stop2', `traindetail2`.`StopArrivalTime` AS 'Stop2_AT'\r\n" + 
            		"FROM `traindetail`, `traindetail2`\r\n" + 
            		"WHERE `traindetail`.`StopName` = ? \r\n" + 
            		"AND `traindetail2`.`StopName` = ? \r\n" + 
            		"AND `traindetail`.`StopSequence` < `traindetail2`.`StopSequence`\r\n" + 
            		"AND `traindetail`.`ID` = `traindetail2`.`ID`\r\n" + 
            		"AND `traindetail`.`StopDepartureTime` > ? \r\n" + 
            		"AND `traindetail`.`StopDepartureTime` < ? ";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, departureStation);
            pres.setString(2, arrivalStation);
            pres.setTime(3, time1);
            pres.setTime(4, time2);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int id = rs.getInt("ID");
                String number = rs.getString("TrainNO");
                int sequence1 = rs.getInt("Stop1S");
                String name1 = rs.getString("Stop1");
                String dt = rs.getString("Stop1_DT");
                int sequence2 = rs.getInt("Stop2S");
                String name2 = rs.getString("Stop2");
                String at = rs.getString("Stop2_AT");
                
                
                /** 將每一筆會員資料產生一名新Member物件 */
                ts = new TrainSelect(id, number, sequence1, name1, dt, sequence2, name2, at);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(ts.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
	}
}
