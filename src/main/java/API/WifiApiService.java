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
    private StringBuilder urlBuilder;

    public WifiApiService() {

        try {
            urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" + URLEncoder.encode("45544e6e74616c7336335152757a42", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int insertWifiINfo(JsonArray jsonArr) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return -1;

        }

        try {

            System.out.println(jsonArr.size());
            for (int i = 0; i < jsonArr.size(); i++) {

                JsonObject jo = jsonArr.get(i).getAsJsonObject();

                double a = jo.get("LNT").getAsDouble();
                double b = jo.get("LAT").getAsDouble();

                if (a > 300) {a /= 10;}
                if (b > 300) {b /= 10;}


                stmt.executeUpdate("REPLACE INTO wifi VALUES("
                        + jo.get("X_SWIFI_MGR_NO").toString()
                        + "," + jo.get("X_SWIFI_WRDOFC").toString()
                        + "," + jo.get("X_SWIFI_MAIN_NM").toString()
                        + "," + jo.get("X_SWIFI_ADRES1").toString()
                        + "," + jo.get("X_SWIFI_ADRES2").toString()
                        + "," + jo.get("X_SWIFI_INSTL_FLOOR").toString()
                        + "," + jo.get("X_SWIFI_INSTL_TY").toString()
                        + "," + jo.get("X_SWIFI_INSTL_MBY").toString()
                        + "," + jo.get("X_SWIFI_SVC_SE").toString()
                        + "," + jo.get("X_SWIFI_CMCWR").toString()
                        + "," + jo.get("X_SWIFI_CNSTC_YEAR").toString()
                        + "," + jo.get("X_SWIFI_INOUT_DOOR").toString()
                        + "," + jo.get("X_SWIFI_REMARS3").toString()
                        + "," + String.valueOf(Math.min(a,b))
                        + "," + String.valueOf(Math.max(a,b))
                        + "," + jo.get("WORK_DTTM").toString() + ")");
            }

        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            cm.closeConnection(conn, stmt, rs, "wifiapi");
        }

        return 1;
    }

    public int getInfoCount() throws IOException {
        StringBuilder urlModify = new StringBuilder(urlBuilder);

        urlModify.append("/" + URLEncoder.encode(String.valueOf(1), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlModify.append("/" + URLEncoder.encode(String.valueOf(1), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlModify.toString());
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

        int totalNum = -1;

        try {
            totalNum = wifiJson.get("list_total_count").getAsInt();
        } catch (Exception e) {
            System.out.println(e);
            return totalNum;
        }

        int start = 1;
        int end = 1000;
         do {

            int result = getWifiInfo(start, end);
            if (result == -1) {
                return -1;
            }
            start = end + 1;
            end += 1000;
        } while (start < totalNum);

        return totalNum;
    }

    public int getWifiInfo(int start, int end) throws IOException {

        StringBuilder urlModify = new StringBuilder(urlBuilder);
        urlModify.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlModify.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlModify.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

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

        JsonArray jsonArr = jsonObj.get("TbPublicWifiInfo").getAsJsonObject().get("row").getAsJsonArray();

        int method_rs = insertWifiINfo(jsonArr);

        if (method_rs == -1) {
            return -1;
        }
        return 1;
    }
}
