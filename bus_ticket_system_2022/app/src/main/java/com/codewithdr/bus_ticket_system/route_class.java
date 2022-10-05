package com.codewithdr.bus_ticket_system;

public class route_class {
    int id;
    String name;
    String cname;
    String bname;
//Alt+Insert //route class constructor
    public route_class(int id, String name, String cname, String bname) {
        this.id = id;
        this.name = name;
        this.cname = cname;     //constructor of Class route_class (Alt+Insert)
        this.bname = bname;
    }
//getter method
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCname() {
        return cname;
    }

    public String getBname() {
        return bname;
    }
}
