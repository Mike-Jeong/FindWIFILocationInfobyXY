package repository;

import db.DBConnUtils;
import domain.WifiInfo;
import dto.WifiInfoDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WifiInfoDao {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public boolean insert(WifiInfo wifiInfo) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        try {
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

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            cm.closeConnection(conn, stmt, rs, "wifiapi");
        }
        return true;
    }

    public ArrayList<WifiInfoDto> getNearWifiInfo(double lat, double lnt) {

        ArrayList<WifiInfoDto> wifiInfoList = new ArrayList<>();

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        try {

            rs = stmt.executeQuery("select round((ST_Distance_Sphere(POINT(" + " convert( " + lnt + ", decimal(15,5))," + "convert( " + lat + ", decimal(15,5))), " +
                    "POINT(convert(w.LNT, decimal(15,5)), convert(w.LAT, decimal(15,7)))) * 0.001), 4)  as Distance, w.* " +
                    "from wifi w order by Distance limit 20");

            while (rs.next()) {

                wifiInfoList.add(new WifiInfoDto(
                                rs.getString("Distance"),
                                rs.getString("X_SWIFI_MGR_NO"),
                                rs.getString("X_SWIFI_WRDOFC"),
                                rs.getString("X_SWIFI_MAIN_NM"),
                                rs.getString("X_SWIFI_ADRES1"),
                                rs.getString("X_SWIFI_ADRES2"),
                                rs.getString("X_SWIFI_INSTL_FLOOR"),
                                rs.getString("X_SWIFI_INSTL_TY"),
                                rs.getString("X_SWIFI_INSTL_MBY"),
                                rs.getString("X_SWIFI_SVC_SE"),
                                rs.getString("X_SWIFI_CMCWR"),
                                rs.getString("X_SWIFI_CNSTC_YEAR"),
                                rs.getString("X_SWIFI_INOUT_DOOR"),
                                rs.getString("X_SWIFI_REMARS3"),
                                rs.getString("LAT"),
                                rs.getString("LNT"),
                                rs.getString("WORK_DTTM")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            cm.closeConnection(conn, stmt, rs, "main");
        }

        return wifiInfoList;
    }
}
