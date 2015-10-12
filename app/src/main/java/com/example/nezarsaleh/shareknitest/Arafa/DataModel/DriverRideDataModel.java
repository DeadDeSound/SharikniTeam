package com.example.nezarsaleh.shareknitest.Arafa.DataModel;

/**
 * Created by Fantom on 03/09/2015.
 */
public class DriverRideDataModel {

    public String Name;
    public String Gender;
    public String emirateFrom;
    public String emirateTo;
    public String reigonFrom;
    public String reigonTo;
    public String streetFrom;
    public String streetTo;
    public String Basis;
    public String VehicleManuName;
    public String VechilePhoto;
    public String Nationality;
    public int ID;
    int NoOfSeats;
    int VehicleManYear;
    int VehicleNoOfSeats;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public void setBasis(String basis) {
        Basis = basis;
    }

    public void setEmirateFrom(String emirateFrom) {
        this.emirateFrom = emirateFrom;
    }

    public void setEmirateTo(String emirateTo) {
        this.emirateTo = emirateTo;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setNoOfSeats(int noOfSeats) {
        NoOfSeats = noOfSeats;
    }

    public void setReigonFrom(String reigonFrom) {
        this.reigonFrom = reigonFrom;
    }

    public void setReigonTo(String reigonTo) {
        this.reigonTo = reigonTo;
    }

    public void setStreetFrom(String streetFrom) {
        this.streetFrom = streetFrom;
    }

    public void setStreetTo(String streetTo) {
        this.streetTo = streetTo;
    }

    public void setVechilePhoto(String vechilePhoto) {
        VechilePhoto = vechilePhoto;
    }

    public void setVehicleManuName(String vehicleManuName) {
        VehicleManuName = vehicleManuName;
    }

    public void setVehicleManYear(int vehicleManYear) {
        VehicleManYear = vehicleManYear;
    }

    public void setVehicleNoOfSeats(int vehicleNoOfSeats) {
        VehicleNoOfSeats = vehicleNoOfSeats;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getName() {
        return Name;
    }

    public int getNoOfSeats() {
        return NoOfSeats;
    }

    public int getVehicleManYear() {
        return VehicleManYear;
    }

    public int getVehicleNoOfSeats() {
        return VehicleNoOfSeats;
    }

    public String getBasis() {
        return Basis;
    }

    public String getEmirateFrom() {
        return emirateFrom;
    }

    public String getEmirateTo() {
        return emirateTo;
    }

    public String getGender() {
        return Gender;
    }

    public String getReigonFrom() {
        return reigonFrom;
    }

    public String getReigonTo() {
        return reigonTo;
    }

    public String getStreetFrom() {
        return streetFrom;
    }

    public String getStreetTo() {
        return streetTo;
    }

    public String getVechilePhoto() {
        return VechilePhoto;
    }

    public String getVehicleManuName() {
        return VehicleManuName;
    }
}
