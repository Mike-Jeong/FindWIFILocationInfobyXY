package service;

import domain.History;
import dto.WifiInfoDto;
import repository.HistoryDao;
import repository.WifiInfoDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SearchWifiInfoService {

    public ArrayList<WifiInfoDto> getWifiInfoList(double lat, double lnt) {

        WifiInfoDao wifiInfoDAO = new WifiInfoDao();
        HistoryDao historyDAO = new HistoryDao();

        History history = new History(String.valueOf(lat), String.valueOf(lnt), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        boolean result = historyDAO.insert(history);

        if (!result) {
            return null;
        }

        return wifiInfoDAO.getNearWifiInfo(lat, lnt);
    }
}
