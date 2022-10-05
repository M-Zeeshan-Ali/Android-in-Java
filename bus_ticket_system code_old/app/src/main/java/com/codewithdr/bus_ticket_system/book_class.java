package com.codewithdr.bus_ticket_system;

public class book_class {
    int booking_id;
    String uid;
    String bname;
    String saddress;
    String eaddress;
    String date;

    public book_class(int booking_id, String uid, String bname, String saddress, String eaddress, String date) {
        this.booking_id = booking_id;
        this.uid = uid;
        this.bname = bname;
        this.saddress = saddress;
        this.eaddress = eaddress;
        this.date = date;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public String getUid() {
        return uid;
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
