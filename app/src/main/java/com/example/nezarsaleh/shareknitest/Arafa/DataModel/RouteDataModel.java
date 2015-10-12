package com.example.nezarsaleh.shareknitest.Arafa.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fantom on 03/09/2015.
 */
public class RouteDataModel implements Parcelable {

    public int ID;
    public int NoOfSeats;
    public int VehicelId;
    public String ArName;
    public String EnName;
    public String PreferredGender;
    public String FromEmirateAr;
    public String FromEmirateEn;
    public String ToEmirateAr;
    public String ToEmirateEn;
    public String FromRegionAr;
    public String FromRegionEn;
    public String ToRegionAr;
    public String ToRegionEn;
    public String StartFromTime;
    public String EndToTime;
    public String StartToTime;
    public String EndFromTime;

    public RouteDataModel(Parcel obtain) {
        ID = obtain.readInt();
        NoOfSeats = obtain.readInt();
        VehicelId = obtain.readInt();
        ArName = obtain.readString();
        EnName = obtain.readString();
        PreferredGender = obtain.readString();
        FromEmirateAr = obtain.readString();
        FromEmirateEn = obtain.readString();
        ToEmirateAr = obtain.readString();
        ToEmirateEn = obtain.readString();
        FromRegionAr = obtain.readString();
        FromRegionEn = obtain.readString();
        ToRegionAr = obtain.readString();
        ToRegionEn = obtain.readString();
        StartFromTime = obtain.readString();
        EndToTime = obtain.readString();
        StartToTime = obtain.readString();
        EndFromTime = obtain.readString();
        FromStreetName = obtain.readString();
        ToStreetName = obtain.readString();
        BasisAr = obtain.readString();
        BasisEn = obtain.readString();
        NationalityAr = obtain.readString();
        NationalityEn = obtain.readString();
        PrefLanguageAr = obtain.readString();
        PrefLanguageEn = obtain.readString();
        StartDate = obtain.readString();
    }

    public static final Creator<RouteDataModel> CREATOR = new Creator<RouteDataModel>() {
        @Override
        public RouteDataModel createFromParcel(Parcel in) {
            return new RouteDataModel(in);
        }

        @Override
        public RouteDataModel[] newArray(int size) {
            return new RouteDataModel[size];
        }
    };

    public String getFromStreetName() {
        return FromStreetName;
    }

    public void setFromStreetName(String fromStreetName) {
        FromStreetName = fromStreetName;
    }

    public String getStartFromTime() {
        return StartFromTime;
    }

    public void setStartFromTime(String startFromTime) {
        StartFromTime = startFromTime;
    }

    public String getEndToTime() {
        return EndToTime;
    }

    public void setEndToTime(String endToTime) {
        EndToTime = endToTime;
    }

    public String getStartToTime() {
        return StartToTime;
    }

    public void setStartToTime(String startToTime) {
        StartToTime = startToTime;
    }

    public String getEndFromTime() {
        return EndFromTime;
    }

    public void setEndFromTime(String endFromTime) {
        EndFromTime = endFromTime;
    }

    public String getToStreetName() {
        return ToStreetName;
    }

    public void setToStreetName(String toStreetName) {
        ToStreetName = toStreetName;
    }

    public String FromStreetName;
    public String ToStreetName;
    public String BasisAr;
    public String BasisEn;
    public String NationalityAr;
    public String NationalityEn;
    public String PrefLanguageAr;
    public String PrefLanguageEn;
    public String StartDate;
    public Boolean IsSmoking;
    public Boolean IsRounded;
    public Boolean IsPassenger;
    public Boolean Saturday;
    public Boolean Sunday;
    public Boolean Monday;
    public Boolean Tuesday;
    public Boolean Wednesday;
    public Boolean Thursday;
    public Boolean Friday;

    public String getPrefLanguageEn() {
        return PrefLanguageEn;
    }

    public void setPrefLanguageEn(String prefLanguageEn) {
        PrefLanguageEn = prefLanguageEn;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNoOfSeats() {
        return NoOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        NoOfSeats = noOfSeats;
    }

    public int getVehicelId() {
        return VehicelId;
    }

    public void setVehicelId(int vehicelId) {
        VehicelId = vehicelId;
    }

    public String getPreferredGender() {
        return PreferredGender;
    }

    public void setPreferredGender(String preferredGender) {
        PreferredGender = preferredGender;
    }

    public String getBasisAr() {
        return BasisAr;
    }

    public void setBasisAr(String basisAr) {
        BasisAr = basisAr;
    }

    public String getNationalityAr() {
        return NationalityAr;
    }

    public void setNationalityAr(String nationalityAr) {
        NationalityAr = nationalityAr;
    }

    public String getNationalityEn() {
        return NationalityEn;
    }

    public void setNationalityEn(String nationalityEn) {
        NationalityEn = nationalityEn;
    }

    public String getPrefLanguageAr() {
        return PrefLanguageAr;
    }

    public void setPrefLanguageAr(String prefLanguageAr) {
        PrefLanguageAr = prefLanguageAr;
    }

    public Boolean getIsSmoking() {
        return IsSmoking;
    }

    public void setIsSmoking(Boolean isSmoking) {
        IsSmoking = isSmoking;
    }

    public Boolean getIsRounded() {
        return IsRounded;
    }

    public void setIsRounded(Boolean isRounded) {
        IsRounded = isRounded;
    }

    public Boolean getIsPassenger() {
        return IsPassenger;
    }

    public void setIsPassenger(Boolean isPassenger) {
        IsPassenger = isPassenger;
    }

    public Boolean getSaturday() {
        return Saturday;
    }

    public void setSaturday(Boolean saturday) {
        Saturday = saturday;
    }

    public Boolean getSunday() {
        return Sunday;
    }

    public void setSunday(Boolean sunday) {
        Sunday = sunday;
    }

    public Boolean getMonday() {
        return Monday;
    }

    public void setMonday(Boolean monday) {
        Monday = monday;
    }

    public Boolean getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        Tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        Wednesday = wednesday;
    }

    public Boolean getThursday() {
        return Thursday;
    }

    public void setThursday(Boolean thursday) {
        Thursday = thursday;
    }

    public Boolean getFriday() {
        return Friday;
    }

    public void setFriday(Boolean friday) {
        Friday = friday;
    }

    public void setArName(String arName) {
        ArName = arName;
    }

    public void setEnName(String enName) {
        EnName = enName;
    }

    public void setFromEmirateAr(String fromEmirateAr) {
        FromEmirateAr = fromEmirateAr;
    }

    public void setFromEmirateEn(String fromEmirateEn) {
        FromEmirateEn = fromEmirateEn;
    }

    public void setToEmirateAr(String toEmirateAr) {
        ToEmirateAr = toEmirateAr;
    }

    public void setToEmirateEn(String toEmirateEn) {
        ToEmirateEn = toEmirateEn;
    }

    public void setFromRegionAr(String fromRegionAr) {
        FromRegionAr = fromRegionAr;
    }

    public void setFromRegionEn(String fromRegionEn) {
        FromRegionEn = fromRegionEn;
    }

    public void setToRegionAr(String toRegionAr) {
        ToRegionAr = toRegionAr;
    }

    public void setToRegionEn(String toRegionEn) {
        ToRegionEn = toRegionEn;
    }

    public void setBasisEn(String basisEn) {
        BasisEn = basisEn;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getArName() {
        return ArName;
    }

    public String getEnName() {
        return EnName;
    }

    public String getFromEmirateAr() {
        return FromEmirateAr;
    }

    public String getFromEmirateEn() {
        return FromEmirateEn;
    }

    public String getToEmirateAr() {
        return ToEmirateAr;
    }

    public String getToEmirateEn() {
        return ToEmirateEn;
    }

    public String getFromRegionAr() {
        return FromRegionAr;
    }

    public String getFromRegionEn() {
        return FromRegionEn;
    }

    public String getToRegionAr() {
        return ToRegionAr;
    }

    public String getToRegionEn() {
        return ToRegionEn;
    }

    public String getBasisEn() {
        return BasisEn;
    }

    public String getStartDate() {
        return StartDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeInt(NoOfSeats);
        dest.writeInt(VehicelId);
        dest.writeString(ArName);
        dest.writeString(EnName);
        dest.writeString(PreferredGender);
        dest.writeString(FromEmirateAr);
        dest.writeString(FromEmirateEn);
        dest.writeString(ToEmirateAr);
        dest.writeString(ToEmirateEn);
        dest.writeString(FromRegionAr);
        dest.writeString(FromRegionEn);
        dest.writeString(ToRegionAr);
        dest.writeString(ToRegionEn);
        dest.writeString(StartFromTime);
        dest.writeString(EndToTime);
        dest.writeString(StartToTime);
        dest.writeString(EndFromTime);
        dest.writeString(FromStreetName);
        dest.writeString(ToStreetName);
        dest.writeString(BasisAr);
        dest.writeString(BasisEn);
        dest.writeString(NationalityAr);
        dest.writeString(NationalityEn);
        dest.writeString(PrefLanguageAr);
        dest.writeString(PrefLanguageEn);
        dest.writeString(StartDate);
    }
}
