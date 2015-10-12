package com.example.nezarsaleh.shareknitest.Arafa.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Fantom on 03/09/2015.
 */


public class BestRouteDataModelDetails extends ArrayList<Parcelable> implements Parcelable {

    public String FromEm, FromReg, ToEm, ToReg;
    public int FromEmId,ToEmId,FromRegid,ToRegId;
    public String RouteEnName,DriverName;
    public  String SDG_Route_Start_FromTime,Nationality_en,SDG_RouteDays;



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    int ID;

    public BestRouteDataModelDetails(Parcel in) {
        FromEm = in.readString();
        FromReg = in.readString();
        ToReg = in.readString();
        ToEm = in.readString();
        FromEmId = in.readInt();
        FromRegid=in.readInt();
        ToEmId=in.readInt();
        ToRegId=in.readInt();
        RouteEnName=in.readString();
        DriverName=in.readString();

        SDG_Route_Start_FromTime=in.readString();
        Nationality_en=in.readString();
        SDG_RouteDays=in.readString();

    }

    public BestRouteDataModelDetails() {

    }

    public static final Creator<BestRouteDataModelDetails> CREATOR = new Creator<BestRouteDataModelDetails>() {
        @Override
        public BestRouteDataModelDetails createFromParcel(Parcel in) {
            return new BestRouteDataModelDetails(in);
        }

        @Override
        public BestRouteDataModelDetails[] newArray(int size) {
            return new BestRouteDataModelDetails[size];
        }
    };



    public String getRouteEnName() {
        return RouteEnName;
    }

    public void setRouteEnName(String routeEnName) {
        RouteEnName = routeEnName;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }


    public int getFromEmId() {
        return FromEmId;
    }

    public void setFromEmId(int fromEmId) {
        FromEmId = fromEmId;
    }

    public int getToEmId() {
        return ToEmId;
    }

    public void setToEmId(int toEmId) {
        ToEmId = toEmId;
    }

    public int getFromRegid() {
        return FromRegid;
    }

    public void setFromRegid(int fromRegid) {
        FromRegid = fromRegid;
    }

    public int getToRegId() {
        return ToRegId;
    }

    public void setToRegId(int toRegId) {
        ToRegId = toRegId;
    }

    public void setFromEm(String fromEm) {
        FromEm = fromEm;
    }

    public void setToEm(String toEm) {
        ToEm = toEm;
    }

    public void setFromReg(String fromReg) {
        FromReg = fromReg;
    }

    public void setToReg(String toReg) {
        ToReg = toReg;
    }


    public String getFromEm() {
        return FromEm;
    }

    public String getFromReg() {
        return FromReg;
    }

    public String getToEm() {
        return ToEm;
    }

    public String getToReg() {
        return ToReg;
    }


    public void setSDG_RouteDays(String SDG_RouteDays) {
        this.SDG_RouteDays = SDG_RouteDays;
    }

    public void setNationality_en(String nationality_en) {
        Nationality_en = nationality_en;
    }

    public void setSDG_Route_Start_FromTime(String SDG_Route_Start_FromTime) {
        this.SDG_Route_Start_FromTime = SDG_Route_Start_FromTime;
    }


    public String getSDG_RouteDays() {
        return SDG_RouteDays;
    }


    public String getNationality_en() {
        return Nationality_en;
    }

    public String getSDG_Route_Start_FromTime() {
        return SDG_Route_Start_FromTime;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(FromEm);
        parcel.writeString(ToEm);
        parcel.writeString(FromReg);
        parcel.writeString(ToReg);
        parcel.writeInt(FromEmId);
        parcel.writeInt(FromRegid);
        parcel.writeInt(ToEmId);
        parcel.writeInt(ToRegId);
        parcel.writeString(RouteEnName);
        parcel.writeString(DriverName);
        parcel.writeString(SDG_Route_Start_FromTime);
        parcel.writeString(Nationality_en);
        parcel.writeString(SDG_RouteDays);

    }
}
