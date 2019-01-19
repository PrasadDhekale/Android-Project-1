package com.example.prasad.project;

public class VehicleItem {
    private String plate_number;
    private String state;
    private String city;
    private String vehicle_type;
    private String drop_location;
    private String fine;
    private String documents;

    public VehicleItem(String plate_number, String state, String city, String vehicle_type, String drop_location, String fine, String documents){
        this.plate_number = plate_number;
        this.city = city;
        this.state = state;
        this.vehicle_type = vehicle_type;
        this.documents = documents;
        this.drop_location = drop_location;
        this.fine = fine;
    }

    public String getPlate_number(){
        return plate_number;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getVehicle_type(){
        return vehicle_type;
    }
    public String getDrop_location(){return drop_location;}

    public String getDocuments() {
        return documents;
    }

    public String getFine() {
        return fine;
    }
}
