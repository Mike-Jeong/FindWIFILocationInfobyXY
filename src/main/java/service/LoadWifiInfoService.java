package service;

import api.WifiApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import db.DBConnUtils;
import domain.WifiInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadWifiInfoService {

    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;


    public int insertWifiINfo() {

        WifiApiService wifiApiService = new WifiApiService();

        JsonArray jsonArray = wifiApiService.getWifiInfo();

        if (jsonArray.isEmpty()) {
            System.out.println(true);
            return -1;
        } else {
            Connection conn = cm.getDBCP();

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
                return -1;
            }

            try {
                System.out.println(jsonArray.size());
                for (int i = 0; i < jsonArray.size(); i++) {

                    JsonObject jo = jsonArray.get(i).getAsJsonObject();

                    double a = jo.get("LNT").getAsDouble();
                    double b = jo.get("LAT").getAsDouble();

                    if (a > 300) {
                        a /= 10;
                    }
                    if (b > 300) {
                        b /= 10;
                    }

                    WifiInfo wifiInfo = new WifiInfo(
                            jo.get("X_SWIFI_MGR_NO").toString(),
                            jo.get("X_SWIFI_WRDOFC").toString(),
                            jo.get("X_SWIFI_MAIN_NM").toString(),
                            jo.get("X_SWIFI_ADRES1").toString(),
                            jo.get("X_SWIFI_ADRES2").toString(),
                            jo.get("X_SWIFI_INSTL_FLOOR").toString(),
                            jo.get("X_SWIFI_INSTL_TY").toString(),
                            jo.get("X_SWIFI_INSTL_MBY").toString(),
                            jo.get("X_SWIFI_SVC_SE").toString(),
                            jo.get("X_SWIFI_CMCWR").toString(),
                            jo.get("X_SWIFI_CNSTC_YEAR").toString(),
                            jo.get("X_SWIFI_INOUT_DOOR").toString(),
                            jo.get("X_SWIFI_REMARS3").toString(),
                            String.valueOf(Math.min(a, b)),
                            String.valueOf(Math.max(a, b)),
                            jo.get("WORK_DTTM").toString()
                    );


                    stmt.executeUpdate("REPLACE INTO wifi VALUES("
                            + wifiInfo.getX_SWIFI_MGR_NO()
                            + "," + wifiInfo.getX_SWIFI_WRDOFC()
                            + "," + wifiInfo.getX_SWIFI_MAIN_NM()
                            + "," + wifiInfo.getX_SWIFI_ADRES1()
                            + "," + wifiInfo.getX_SWIFI_ADRES2()
                            + "," + wifiInfo.getX_SWIFI_INSTL_FLOOR()
                            + "," + wifiInfo.getX_SWIFI_INSTL_TY()
                            + "," + wifiInfo.getX_SWIFI_INSTL_MBY()
                            + "," + wifiInfo.getX_SWIFI_SVC_SE()
                            + "," + wifiInfo.getX_SWIFI_CMCWR()
                            + "," + wifiInfo.getX_SWIFI_CNSTC_YEAR()
                            + "," + wifiInfo.getX_SWIFI_INOUT_DOOR()
                            + "," + wifiInfo.getX_SWIFI_REMARS3()
                            + "," + wifiInfo.getLAT()
                            + "," + wifiInfo.getLNT()
                            + "," + wifiInfo.getWORK_DTTM() + ")");
                }

            } catch (SQLException e) {
                System.out.println(e);
                return -1;
            } finally {
                cm.closeConnection(conn, stmt, rs, "wifiapi");
            }
        }
        return jsonArray.size();
    }
}
