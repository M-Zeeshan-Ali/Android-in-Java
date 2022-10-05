package com.codewithdr.bus_ticket_system;

public class booking_class {

    int bid;
    String uemail;
    String bname;
    String saddress;
    String eaddress;
    String date;

    public booking_class(int bid, String uemail, String bname, String saddress, String eaddress, String date) {
        this.bid = bid;
        this.uemail = uemail;
        this.bname = bname;
        this.saddress = saddress;
        this.eaddress = eaddress;
        this.date = date;
    }

    public int getBid() {
        return bid;
    }

    public String getUemail() {
        return uemail;
    }

    public String getBname() {
        return bname;
    }

    public String getSaddress() {
        return saddress;
    }

    public String getEaddress() {
        return eaddress;
    }

    public String getDate() {
        return date;
    }
}
