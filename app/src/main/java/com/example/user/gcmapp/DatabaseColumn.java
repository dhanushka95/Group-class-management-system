package com.example.user.gcmapp;

public class DatabaseColumn {

    private String class_name,student_name;
    private String class_location;
    private String class_phone_no,student_phone_no;
    private String class_Id;
    private String class_no_days;
    private String current_date;
    private String check;
    private String test_no;


    public String getTest_no() {
        return test_no;
    }

    public void setTest_no(String test_no) {
        this.test_no = test_no;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
        if(check.equals("true")){
            this.selected=true;
        }else {
            this.selected=false;
        }
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getClass_no_days() {
        return class_no_days;
    }

    public void setClass_no_days(String class_no_days) {
        this.class_no_days = class_no_days;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_phone_no() {
        return student_phone_no;
    }

    public void setStudent_phone_no(String student_phone_no) {
        this.student_phone_no = student_phone_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    private String student_id;
    private int id;
    private String marks;

    private boolean selected;
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getMarks() {
        return marks;
    }
    public void setMarks(String marks) {
        this.marks = marks;
    }


    public DatabaseColumn() {


    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_location() {
        return class_location;
    }

    public void setClass_location(String class_location) {
        this.class_location = class_location;
    }

    public String getClass_phone_no() {
        return class_phone_no;
    }

    public void setClass_phone_no(String class_phone_no) {
        this.class_phone_no = class_phone_no;
    }

    public String getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(String class_Id) {
        this.class_Id = class_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
