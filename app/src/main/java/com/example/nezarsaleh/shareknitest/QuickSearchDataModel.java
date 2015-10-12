package com.example.nezarsaleh.shareknitest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by nezar on 9/20/2015.
 */
public class QuickSearchDataModel extends ArrayList<Parcelable> implements Parcelable {

    String AccountName, DriverEnName, From_EmirateName_en
            , From_RegionName_en, SDG_Route_Name_en, SDG_Route_PreferredGender
            , SDG_Route_Start_Date, SDG_Route_Start_FromTime, To_EmirateName_en
            , To_RegionName_en, VehicleDescription, Nationality_en, AccountEmail
            , AccountMobile, AccountPhoto,SDG_RouteDays,Rating;


    int AvilableOrRequiredSeats, SDG_Route_FromEmirate_ID
            , SDG_Route_FromRegion_ID, SDG_Route_ID, SDG_Route_ToEmirate_ID
            , SDG_Route_ToRegion_ID, DriverId, SDG_Route_NoOfSeats;

    double SDG_Route_Coordinates_Start_Lat, SDG_Route_Coordinates_End_Lat
            , SDG_Route_Coordinates_Start_Lng, SDG_Route_Coordinates_End_Lng;





    public QuickSearchDataModel(Parcel in) {
        AccountName = in.readString();
        DriverEnName = in.readString();
        From_EmirateName_en = in.readString();
        From_RegionName_en = in.readString();
        SDG_Route_Name_en = in.readString();
        SDG_Route_PreferredGender = in.readString();
        SDG_Route_Start_Date = in.readString();
        SDG_Route_Start_FromTime = in.readString();
        To_EmirateName_en = in.readString();
        To_RegionName_en = in.readString();
        VehicleDescription = in.readString();
        Nationality_en = in.readString();
        AccountEmail = in.readString();
        AccountMobile = in.readString();
        AccountPhoto = in.readString();
        AvilableOrRequiredSeats = in.readInt();
        SDG_Route_FromEmirate_ID = in.readInt();
        SDG_Route_FromRegion_ID = in.readInt();
        SDG_Route_ID = in.readInt();
        SDG_Route_ToEmirate_ID = in.readInt();
        SDG_Route_ToRegion_ID = in.readInt();
        DriverId = in.readInt();
        SDG_Route_NoOfSeats = in.readInt();
        SDG_Route_Coordinates_Start_Lat = in.readDouble();
        SDG_Route_Coordinates_End_Lat = in.readDouble();
        SDG_Route_Coordinates_Start_Lng = in.readDouble();
        SDG_Route_Coordinates_End_Lng = in.readDouble();
        Rating=in.readString();
        SDG_RouteDays=in.readString();
    }

    public static final Creator<QuickSearchDataModel> CREATOR = new Creator<QuickSearchDataModel>() {
        @Override
        public QuickSearchDataModel createFromParcel(Parcel in) {
            return new QuickSearchDataModel(in);
        }

        @Override
        public QuickSearchDataModel[] newArray(int size) {
            return new QuickSearchDataModel[size];
        }
    };


    public void setSDG_RouteDays(String SDG_RouteDays) {
        this.SDG_RouteDays = SDG_RouteDays;
    }

    public String getSDG_RouteDays() {
        return SDG_RouteDays;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getDriverEnName() {
        return DriverEnName;
    }

    public void setDriverEnName(String driverEnName) {
        DriverEnName = driverEnName;
    }

    public String getFrom_EmirateName_en() {
        return From_EmirateName_en;
    }

    public void setFrom_EmirateName_en(String from_EmirateName_en) {
        From_EmirateName_en = from_EmirateName_en;
    }

    public String getFrom_RegionName_en() {
        return From_RegionName_en;
    }

    public void setFrom_RegionName_en(String from_RegionName_en) {
        From_RegionName_en = from_RegionName_en;
    }

    public String getSDG_Route_Name_en() {
        return SDG_Route_Name_en;
    }

    public void setSDG_Route_Name_en(String SDG_Route_Name_en) {
        this.SDG_Route_Name_en = SDG_Route_Name_en;
    }

    public String getSDG_Route_PreferredGender() {
        return SDG_Route_PreferredGender;
    }

    public void setSDG_Route_PreferredGender(String SDG_Route_PreferredGender) {
        this.SDG_Route_PreferredGender = SDG_Route_PreferredGender;
    }

    public String getSDG_Route_Start_Date() {
        return SDG_Route_Start_Date;
    }

    public void setSDG_Route_Start_Date(String SDG_Route_Start_Date) {
        this.SDG_Route_Start_Date = SDG_Route_Start_Date;
    }

    public String getSDG_Route_Start_FromTime() {
        return SDG_Route_Start_FromTime;
    }

    public void setSDG_Route_Start_FromTime(String SDG_Route_Start_FromTime) {
        this.SDG_Route_Start_FromTime = SDG_Route_Start_FromTime;
    }

    public String getTo_EmirateName_en() {
        return To_EmirateName_en;
    }

    public void setTo_EmirateName_en(String to_EmirateName_en) {
        To_EmirateName_en = to_EmirateName_en;
    }

    public String getTo_RegionName_en() {
        return To_RegionName_en;
    }

    public void setTo_RegionName_en(String to_RegionName_en) {
        To_RegionName_en = to_RegionName_en;
    }

    public String getVehicleDescription() {
        return VehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        VehicleDescription = vehicleDescription;
    }

    public String getNationality_en() {
        return Nationality_en;
    }

    public void setNationality_en(String nationality_en) {
        Nationality_en = nationality_en;
    }

    public String getAccountEmail() {
        return AccountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        AccountEmail = accountEmail;
    }

    public String getAccountMobile() {
        return AccountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        AccountMobile = accountMobile;
    }

    public String getAccountPhoto() {
        return AccountPhoto;
    }

    public void setAccountPhoto(String accountPhoto) {
        AccountPhoto = accountPhoto;
    }

    public int getAvilableOrRequiredSeats() {
        return AvilableOrRequiredSeats;
    }

    public void setAvilableOrRequiredSeats(int avilableOrRequiredSeats) {
        AvilableOrRequiredSeats = avilableOrRequiredSeats;
    }

    public int getSDG_Route_FromEmirate_ID() {
        return SDG_Route_FromEmirate_ID;
    }

    public void setSDG_Route_FromEmirate_ID(int SDG_Route_FromEmirate_ID) {
        this.SDG_Route_FromEmirate_ID = SDG_Route_FromEmirate_ID;
    }

    public int getSDG_Route_FromRegion_ID() {
        return SDG_Route_FromRegion_ID;
    }

    public void setSDG_Route_FromRegion_ID(int SDG_Route_FromRegion_ID) {
        this.SDG_Route_FromRegion_ID = SDG_Route_FromRegion_ID;
    }

    public int getSDG_Route_ID() {
        return SDG_Route_ID;
    }

    public void setSDG_Route_ID(int SDG_Route_ID) {
        this.SDG_Route_ID = SDG_Route_ID;
    }

    public int getSDG_Route_ToEmirate_ID() {
        return SDG_Route_ToEmirate_ID;
    }

    public void setSDG_Route_ToEmirate_ID(int SDG_Route_ToEmirate_ID) {
        this.SDG_Route_ToEmirate_ID = SDG_Route_ToEmirate_ID;
    }

    public int getSDG_Route_ToRegion_ID() {
        return SDG_Route_ToRegion_ID;
    }

    public void setSDG_Route_ToRegion_ID(int SDG_Route_ToRegion_ID) {
        this.SDG_Route_ToRegion_ID = SDG_Route_ToRegion_ID;
    }

    public int getDriverId() {
        return DriverId;
    }

    public void setDriverId(int driverId) {
        DriverId = driverId;
    }

    public int getSDG_Route_NoOfSeats() {
        return SDG_Route_NoOfSeats;
    }

    public void setSDG_Route_NoOfSeats(int SDG_Route_NoOfSeats) {
        this.SDG_Route_NoOfSeats = SDG_Route_NoOfSeats;
    }

    public double getSDG_Route_Coordinates_Start_Lat() {
        return SDG_Route_Coordinates_Start_Lat;
    }

    public void setSDG_Route_Coordinates_Start_Lat(double SDG_Route_Coordinates_Start_Lat) {
        this.SDG_Route_Coordinates_Start_Lat = SDG_Route_Coordinates_Start_Lat;
    }

    public double getSDG_Route_Coordinates_End_Lat() {
        return SDG_Route_Coordinates_End_Lat;
    }

    public void setSDG_Route_Coordinates_End_Lat(double SDG_Route_Coordinates_End_Lat) {
        this.SDG_Route_Coordinates_End_Lat = SDG_Route_Coordinates_End_Lat;
    }

    public double getSDG_Route_Coordinates_Start_Lng() {
        return SDG_Route_Coordinates_Start_Lng;
    }

    public void setSDG_Route_Coordinates_Start_Lng(double SDG_Route_Coordinates_Start_Lng) {
        this.SDG_Route_Coordinates_Start_Lng = SDG_Route_Coordinates_Start_Lng;
    }

    public double getSDG_Route_Coordinates_End_Lng() {
        return SDG_Route_Coordinates_End_Lng;
    }

    public void setSDG_Route_Coordinates_End_Lng(double SDG_Route_Coordinates_End_Lng) {
        this.SDG_Route_Coordinates_End_Lng = SDG_Route_Coordinates_End_Lng;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getRating() {
        return Rating;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(AccountName);
        dest.writeString(DriverEnName);
        dest.writeString(From_EmirateName_en);
        dest.writeString(From_RegionName_en);
        dest.writeString(SDG_Route_Name_en);
        dest.writeString(SDG_Route_PreferredGender);
        dest.writeString(SDG_Route_Start_Date);
        dest.writeString(SDG_Route_Start_FromTime);
        dest.writeString(To_EmirateName_en);
        dest.writeString(To_RegionName_en);
        dest.writeString(VehicleDescription);
        dest.writeString(Nationality_en);
        dest.writeString(AccountEmail);
        dest.writeString(AccountMobile);
        dest.writeString(AccountPhoto);
        dest.writeInt(AvilableOrRequiredSeats);
        dest.writeInt(SDG_Route_FromEmirate_ID);
        dest.writeInt(SDG_Route_FromRegion_ID);
        dest.writeInt(SDG_Route_ID);
        dest.writeInt(SDG_Route_ToEmirate_ID);
        dest.writeInt(SDG_Route_ToRegion_ID);
        dest.writeInt(DriverId);
        dest.writeInt(SDG_Route_NoOfSeats);
        dest.writeDouble(SDG_Route_Coordinates_Start_Lat);
        dest.writeDouble(SDG_Route_Coordinates_End_Lat);
        dest.writeDouble(SDG_Route_Coordinates_Start_Lng);
        dest.writeDouble(SDG_Route_Coordinates_End_Lng);
        dest.writeString(SDG_RouteDays);
        dest.writeString(Rating);
    }
}

