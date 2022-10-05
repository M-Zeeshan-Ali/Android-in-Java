package com.codewithdr.bus_ticket_system;

public class user_class {
    int id;
    String name;
    String email;
    String phone;
    String cnic;
    String password;
    String status;

    public user_class(int id, String name, String email, String phone, String cnic, String password, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnic = cnic;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
