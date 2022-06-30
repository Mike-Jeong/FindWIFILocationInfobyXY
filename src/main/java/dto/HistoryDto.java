package dto;

import domain.History;

public class HistoryDto {

    private final String LAT;
    private final String LNT;
    private final String Date;

    public HistoryDto(History history) {
        this.LAT = history.getLAT();
        this.LNT = history.getLNT();
        this.Date = history.getDate();
    }

    public String getLAT() {
        return LAT;
    }

    public String getLNT() {
        return LNT;
    }

    public String getDate() {
        return Date;
    }
}
