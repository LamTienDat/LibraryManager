package com.example.librarymanage;

public class TableModel2 {
    private String admin_id;
    private String password;
    private String admin_name;
    private String admin_phone_num;

    public TableModel2(String admin_id, String password, String admin_name, String admin_phone_num) {
        this.admin_id = admin_id;
        this.password = password;
        this.admin_name = admin_name;
        this.admin_phone_num = admin_phone_num;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getPassword() {
        return password;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public String getAdmin_phone_num() {
        return admin_phone_num;
    }
}
