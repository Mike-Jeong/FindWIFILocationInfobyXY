package API;

import DB.DBConnUtils;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WifiApiService {

    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public int insertWifiINfo(JsonArray jsonArr) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String count = "";
        try {
            rs = stmt.executeQuery("select count(*) from wifi");

            while (rs.next()) {
                count = rs.getString("count(*)");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cm.closeConnection(conn, stmt, rs, "loadwifi");
        }

        return 1;
    }

    public static int getWifiInfo() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        urlBuilder.append("/" + URLEncoder.encode("45544e6e74616c7336335152757a42", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("1000", "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        Gson gson = new Gson();
        JsonElement json = gson.fromJson(sb.toString(), JsonElement.class);
        JsonObject jsonObj = json.getAsJsonObject();
        JsonObject wifiJson = jsonObj.get("TbPublicWifiInfo").getAsJsonObject();

        JsonArray jsonArr = wifiJson.get("row").getAsJsonArray();

        for (int i = 0; i < jsonArr.size(); i++) {

        }


        int totalNum = -1;
        try {
            totalNum = wifiJson.get("list_total_count").getAsInt();
        } catch (Exception e) {
            System.out.println(e);
            return totalNum;
        }


        return totalNum;
    }
}
