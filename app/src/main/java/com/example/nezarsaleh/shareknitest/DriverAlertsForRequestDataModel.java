package com.example.nezarsaleh.shareknitest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Nezar Saleh on 10/10/2015.
 */
public class DriverAlertsForRequestDataModel extends ArrayList<Parcelable> implements Parcelable  {


public  int RequestId;
    public  String PassengerName,RouteName,Remarks,RequestDate,PassengerMobile,AccountPhoto,AccountGender,NationalityEnName;


    protected DriverAlertsForRequestDataModel(Parcel in) {
        RequestId = in.readInt();
        PassengerName = in.readString();
        RouteName = in.readString();
        Remarks = in.readString();
        RequestDate = in.readString();
        PassengerMobile = in.readString();
        AccountPhoto = in.readString();
        AccountGender = in.readString();
        NationalityEnName = in.readString();
    }

    public static final Creator<DriverAlertsForRequestDataModel> CREATOR = new Creator<DriverAlertsForRequestDataModel>() {
        @Override
        public DriverAlertsForRequestDataModel createFromParcel(Parcel in) {
            return new DriverAlertsForRequestDataModel(in);
        }

        @Override
        public DriverAlertsForRequestDataModel[] newArray(int size) {
            return new DriverAlertsForRequestDataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    public int getRequestId() {
        return RequestId;
    }

    public void setRequestId(int requestId) {
        RequestId = requestId;
    }

    public String getPassengerName() {
        return PassengerName;
    }

    public void setPassengerName(String passengerName) {
        PassengerName = passengerName;
    }

    public String getRouteName() {
        return RouteName;
    }

    public void setRouteName(String routeName) {
        RouteName = routeName;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    public String getPassengerMobile() {
        return PassengerMobile;
    }

    public void setPassengerMobile(String passengerMobile) {
        PassengerMobile = passengerMobile;
    }

    public String getAccountPhoto() {
        return AccountPhoto;
    }

    public void setAccountPhoto(String accountPhoto) {
        AccountPhoto = accountPhoto;
    }

    public String getAccountGender() {
        return AccountGender;
    }

    public void setAccountGender(String accountGender) {
        AccountGender = accountGender;
    }

    public String getNationalityEnName() {
        return NationalityEnName;
    }

    public void setNationalityEnName(String nationalityEnName) {
        NationalityEnName = nationalityEnName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(RequestId);
        dest.writeString(PassengerName);
        dest.writeString(RouteName);
        dest.writeString(Remarks);
        dest.writeString(RequestDate);
        dest.writeString(PassengerMobile);
        dest.writeString(AccountPhoto);
        dest.writeString(AccountGender);
        dest.writeString(NationalityEnName);
    }
}
