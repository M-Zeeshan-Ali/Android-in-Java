package com.codewithdr.bus_ticket_system;

public class route_class {
    int id;
    String name;
    String cname;
    String bname;

    public route_class(int id, String name, String cname, String bname) {
        this.id = id;
        this.name = name;
        this.cname = cname;
        this.bname = bname;
    }

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
