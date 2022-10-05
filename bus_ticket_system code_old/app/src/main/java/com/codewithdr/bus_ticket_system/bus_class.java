package com.codewithdr.bus_ticket_system;

public class bus_class {
    int id;         //int variable is used and declare the id ,only integer value is  allowd
    String name;    //string variable is used to declare string value only, like "alphabetic"
    String city;    // sting varibale is city
    String stime;      //bus starting time or set time
    String etime;   //bus end_time  selction
    //bus_class is define which indluse bus,id ,name ,city ,set_time and end_time
    public bus_class(int id, String name, String city, String stime, String etime) {
        this.id = id;               //
        this.name = name;
        this.city = city;
        this.stime = stime;
        this.etime = etime;
    }
//getter class is call ,publicily  to get the name ,city,set_time and endtime.

    public int getId()
    {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }
}