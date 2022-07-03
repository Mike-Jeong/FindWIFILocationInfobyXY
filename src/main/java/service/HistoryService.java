package service;

import config.ApplicationConfig;
import domain.History;
import dto.HistoryDto;
import repository.HistoryDao;

import java.util.ArrayList;

public class HistoryService {
    HistoryDao historyDAO;

    public boolean deleteHistory(HistoryDto historyDto) {

        historyDAO = ApplicationConfig.getHistoryDao();

        History history = new History(historyDto.getLAT(), historyDto.getLNT(), historyDto.getDate());

        return historyDAO.delete(history);
    }

    public ArrayList<HistoryDto> getHistory() {

        historyDAO = new HistoryDao();
        ArrayList<History> historyList = historyDAO.get();
        ArrayList<HistoryDto> historyDtoList = new ArrayList<>();

        if (historyList == null) {
            return null;
        }

        for (int i = 0; i < historyList.size(); i++) {
            History history = historyList.get(i);
            historyDtoList.add(new HistoryDto((i + 1), history.getLAT(), history.getLNT(), history.getDate()));
        }

        return historyDtoList;

    }
}
