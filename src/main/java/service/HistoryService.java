package service;

import domain.History;
import dto.HistoryDto;
import repository.HistoryDao;

import java.util.ArrayList;

public class HistoryService {

    public boolean deleteHistory(HistoryDto historyDto) {

        HistoryDao historyDAO = new HistoryDao();

        History history = new History(historyDto.getLAT(), historyDto.getLNT(), historyDto.getDate());

        return historyDAO.delete(history);
    }

    public ArrayList<HistoryDto> getHistory() {

        HistoryDao historyDAO = new HistoryDao();
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
