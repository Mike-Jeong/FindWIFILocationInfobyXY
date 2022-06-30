package api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WifiApiService {
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

    public JsonArray getWifiInfo() {

        try {
            StringBuilder urlModify = new StringBuilder(urlBuilder);
            urlModify.append("/" + URLEncoder.encode(String.valueOf(1), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlModify.append("/" + URLEncoder.encode(String.valueOf(1000), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
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
            return jsonArr;
        } catch (Exception e) {
            System.out.println(e);
            return new JsonArray();
        }
    }
}
