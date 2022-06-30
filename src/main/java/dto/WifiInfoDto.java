package dto;

import domain.WifiInfo;

public class WifiInfoDto {
    private final String Distance;
    private final String X_SWIFI_MGR_NO;
    private final String X_SWIFI_WRDOFC;
    private final String X_SWIFI_MAIN_NM;
    private final String X_SWIFI_ADRES1;
    private final String X_SWIFI_ADRES2;
    private final String X_SWIFI_INSTL_FLOOR;
    private final String X_SWIFI_INSTL_TY;
    private final String X_SWIFI_INSTL_MBY;
    private final String X_SWIFI_SVC_SE;
    private final String X_SWIFI_CMCWR;
    private final String X_SWIFI_CNSTC_YEAR;
    private final String X_SWIFI_INOUT_DOOR;
    private final String X_SWIFI_REMARS3;
    private final String LAT;
    private final String LNT;
    private final String WORK_DTTM;

    public WifiInfoDto(String Distance, WifiInfo wifiInfo) {

        this.Distance = Distance;
        this.X_SWIFI_MGR_NO = wifiInfo.getX_SWIFI_MGR_NO();
        this.X_SWIFI_WRDOFC = wifiInfo.getX_SWIFI_WRDOFC();
        this.X_SWIFI_MAIN_NM = wifiInfo.getX_SWIFI_MAIN_NM();
        this.X_SWIFI_ADRES1 = wifiInfo.getX_SWIFI_ADRES1();
        this.X_SWIFI_ADRES2 = wifiInfo.getX_SWIFI_ADRES2();
        this.X_SWIFI_INSTL_FLOOR = wifiInfo.getX_SWIFI_INSTL_FLOOR();
        this.X_SWIFI_INSTL_TY = wifiInfo.getX_SWIFI_INSTL_TY();
        this.X_SWIFI_INSTL_MBY = wifiInfo.getX_SWIFI_INSTL_MBY();
        this.X_SWIFI_SVC_SE = wifiInfo.getX_SWIFI_SVC_SE();
        this.X_SWIFI_CMCWR = wifiInfo.getX_SWIFI_CMCWR();
        this.X_SWIFI_CNSTC_YEAR = wifiInfo.getX_SWIFI_CNSTC_YEAR();
        this.X_SWIFI_INOUT_DOOR = wifiInfo.getX_SWIFI_INOUT_DOOR();
        this.X_SWIFI_REMARS3 = wifiInfo.getX_SWIFI_REMARS3();
        this.LAT = wifiInfo.getLAT();
        this.LNT = wifiInfo.getLNT();
        this.WORK_DTTM = wifiInfo.getWORK_DTTM();

    }

    public String getX_SWIFI_MGR_NO() {
        return X_SWIFI_MGR_NO;
    }

    public String getX_SWIFI_WRDOFC() {
        return X_SWIFI_WRDOFC;
    }

    public String getX_SWIFI_MAIN_NM() {
        return X_SWIFI_MAIN_NM;
    }

    public String getX_SWIFI_ADRES1() {
        return X_SWIFI_ADRES1;
    }

    public String getX_SWIFI_ADRES2() {
        return X_SWIFI_ADRES2;
    }

    public String getX_SWIFI_INSTL_FLOOR() {
        return X_SWIFI_INSTL_FLOOR;
    }

    public String getX_SWIFI_INSTL_TY() {
        return X_SWIFI_INSTL_TY;
    }

    public String getX_SWIFI_INSTL_MBY() {
        return X_SWIFI_INSTL_MBY;
    }

    public String getX_SWIFI_SVC_SE() {
        return X_SWIFI_SVC_SE;
    }

    public String getX_SWIFI_CMCWR() {
        return X_SWIFI_CMCWR;
    }

    public String getX_SWIFI_CNSTC_YEAR() {
        return X_SWIFI_CNSTC_YEAR;
    }

    public String getX_SWIFI_INOUT_DOOR() {
        return X_SWIFI_INOUT_DOOR;
    }

    public String getX_SWIFI_REMARS3() {
        return X_SWIFI_REMARS3;
    }

    public String getDistance() {
        return Distance;
    }

    public String getLAT() {
        return LAT;
    }

    public String getLNT() {
        return LNT;
    }

    public String getWORK_DTTM() {
        return WORK_DTTM;
    }
}
