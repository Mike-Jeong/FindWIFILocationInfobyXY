package service;

import api.WifiApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import config.ApplicationConfig;
import domain.WifiInfo;
import repository.WifiInfoDao;

public class LoadWifiInfoService {

    WifiApiService wifiApiService;
    WifiInfoDao wifiInfoDAO;

    public int insertWifiINfo() {

        wifiApiService = ApplicationConfig.getWifiApiService();
        wifiInfoDAO = ApplicationConfig.getWifiInfoDao();

        JsonArray jsonArray = wifiApiService.getWifiInfo();

        if (jsonArray.isEmpty()) {
            return -1;
        } else {
            int dataSize = jsonArray.size();

            for (int i = 0; i < dataSize; i++) {
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

                boolean status = wifiInfoDAO.insert(wifiInfo);

                if (!status) {
                    return -1;
                }
            }
            return dataSize;
        }
    }
}
