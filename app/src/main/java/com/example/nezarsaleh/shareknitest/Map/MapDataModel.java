package com.example.nezarsaleh.shareknitest.Map;

/**
 * Created by nezar on 9/4/2015.
 */
public class MapDataModel {
    String FromEmirateEnName,ToEmirateEnName,FromRegionEnName,ToRegionEnName;
    String FromEmirateArName,FromRegionArName,ToRegionArName,ToEmirateArName;




    Double longitude;
    Double latitude;

    int FromEmirateId,ToEmirateId,FromRegionId,ToRegionId;







    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
            this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


    public String getFromEmirateEnName() {
        return FromEmirateEnName;
    }

    public void setFromEmirateEnName(String fromEmirateEnName) {
        FromEmirateEnName = fromEmirateEnName;
    }

    public String getToEmirateEnName() {
        return ToEmirateEnName;
    }

    public void setToEmirateEnName(String toEmirateEnName) {
        ToEmirateEnName = toEmirateEnName;
    }

    public String getFromRegionEnName() {
        return FromRegionEnName;
    }

    public void setFromRegionEnName(String fromRegionEnName) {
        FromRegionEnName = fromRegionEnName;
    }

    public String getToRegionEnName() {
        return ToRegionEnName;
    }

    public void setToRegionEnName(String toRegionEnName) {
        ToRegionEnName = toRegionEnName;
    }

    public int getFromEmirateId() {
        return FromEmirateId;
    }

    public void setFromEmirateId(int fromEmirateId) {
        FromEmirateId = fromEmirateId;
    }

    public int getToEmirateId() {
        return ToEmirateId;
    }

    public void setToEmirateId(int toEmirateId) {
        ToEmirateId = toEmirateId;
    }

    public int getFromRegionId() {
        return FromRegionId;
    }

    public void setFromRegionId(int fromRegionId) {
        FromRegionId = fromRegionId;
    }

    public int getToRegionId() {
        return ToRegionId;
    }

    public void setToRegionId(int toRegionId) {
        ToRegionId = toRegionId;
    }


    public String getFromEmirateArName() {
        return FromEmirateArName;
    }

    public void setFromEmirateArName(String fromEmirateArName) {
        FromEmirateArName = fromEmirateArName;
    }

    public String getFromRegionArName() {
        return FromRegionArName;
    }

    public void setFromRegionArName(String fromRegionArName) {
        FromRegionArName = fromRegionArName;
    }

    public String getToRegionArName() {
        return ToRegionArName;
    }

    public void setToRegionArName(String toRegionArName) {
        ToRegionArName = toRegionArName;
    }

    public String getToEmirateArName() {
        return ToEmirateArName;
    }

    public void setToEmirateArName(String toEmirateArName) {
        ToEmirateArName = toEmirateArName;
    }
}
