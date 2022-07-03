
package config;

import api.WifiApiService;
import repository.HistoryDao;
import repository.WifiInfoDao;
import service.HistoryService;
import service.LoadWifiInfoService;
import service.SearchWifiInfoService;

public class ApplicationConfig {

    private static HistoryService historyService = null;
    private static WifiApiService wifiApiService = null;
    private static SearchWifiInfoService searchWifiInfoService = null;
    private static LoadWifiInfoService loadWifiInfoService = null;
    private static HistoryDao historyDao = null;
    private static WifiInfoDao wifiInfoDao = null;

    public static HistoryService getHistoryService() {

        if (historyService == null) {
            historyService = new HistoryService();
            System.out.println("historyService 생성");
        }
        return historyService;
    }

    public static WifiApiService getWifiApiService() {

        if (wifiApiService == null) {
            wifiApiService = new WifiApiService();
            System.out.println("wifiApiService 생성");
        }
        return wifiApiService;
    }

    public static SearchWifiInfoService getSearchWifiInfoService() {

        if (searchWifiInfoService == null) {
            searchWifiInfoService = new SearchWifiInfoService();
            System.out.println("searchWifiInfoService 생성");
        }
        return searchWifiInfoService;
    }

    public static LoadWifiInfoService getLoadWifiInfoService() {

        if (loadWifiInfoService == null) {
            loadWifiInfoService = new LoadWifiInfoService();
            System.out.println("loadWifiInfoService 생성");
        }

        return loadWifiInfoService;
    }

    public static HistoryDao getHistoryDao() {

        if (historyDao == null) {
            historyDao = new HistoryDao();
            System.out.println("historyDao 생성");
        }

        return historyDao;
    }

    public static WifiInfoDao getWifiInfoDao() {

        if (wifiInfoDao == null) {
            wifiInfoDao = new WifiInfoDao();
            System.out.println("wifiInfoDao 생성");
        }

        return wifiInfoDao;
    }
}