package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import org.json.*;

import ncu.im3069.demo.util.DBMgr;

public class BookHelper {
	
	private BookHelper() {
        
    }
    
    /** 靜態變數，儲存MemberHelper物件 */
    private static BookHelper bh;
    
    /** 儲存JDBC資料庫連線 */
    private Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    private PreparedStatement pres = null;
    
    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個MemberHelper物件
     *
     * @return the helper 回傳MemberHelper物件
     */
    public static BookHelper getHelper() {
        /** Singleton檢查是否已經有MemberHelper物件，若無則new一個，若有則直接回傳 */
        if(bh == null) bh = new BookHelper();
        
        return bh;
    }
    
    public JSONObject create(Book b) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "INSERT INTO `saproject`.`book`(`member_id`, `train_NO`, `departure_station`, `arrival_station`, `starting_station`, `ending_station`, `departure_time`, `arrival_time`, `ticket_amount`, `ticket_price`, `train_date`, `train_type`, `created`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            int mId = b.getMId();
            int trainNO = b.getTrainNO();
            String dStation = b.getDepartureStation();
            String aStation = b.getArrivalStation();
            String sStation = b.getStartingStation();
            String eStation = b.getEndingStation();
            Time dTime = b.getDepartureTime();
            Time aTime = b.getArrivalTime();
            int amount = b.getAmount();
            int price = b.getTotalPrice();
            Date date = b.getTrainDate();
            String type = b.getTrainType();
            
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, mId);
            pres.setInt(2, trainNO);
            pres.setString(3, dStation);
            pres.setString(4, aStation);
            pres.setString(5, sStation);
            pres.setString(6, eStation);
            pres.setTime(7, dTime);
            pres.setTime(8, aTime);
            pres.setInt(9, amount);
            pres.setInt(10, price);
            pres.setDate(11, date);
            pres.setString(12, type);
            pres.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
   
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);

        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("row", row);

        return response;
    }
    
    public JSONObject getByID(String id) {
        /** 新建一個 Member 物件之 m 變數，用於紀錄每一位查詢回之會員資料 */
        Book b = null;
        /** 用於儲存所有檢索回之會員，以JSONArray方式儲存 */
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
            String sql = "SELECT * FROM `saproject`.`book` WHERE `ID` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int book_id = rs.getInt("ID");
                int member_id = rs.getInt("member_id");
                int trainNO = rs.getInt("train_NO");
                String dStation = rs.getString("departure_station");
                String aStation = rs.getString("arrival_station");
                String sStation = rs.getString("starting_station");
                String eStation = rs.getString("ending_station");
                Time dTime = rs.getTime("departure_time");
                Time aTime = rs.getTime("arrival_time");
                int amount = rs.getInt("ticket_amount");
                int price = rs.getInt("ticket_price");
                Date date = rs.getDate("train_date");
                String type = rs.getString("train_type");
                
                /** 將每一筆會員資料產生一名新Member物件 */
                b = new Book(book_id, member_id, trainNO, dStation, aStation, sStation, eStation, dTime, aTime, amount, price, date, type);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(b.getData());
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
