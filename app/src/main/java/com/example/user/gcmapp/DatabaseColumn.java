package com.example.user.gcmapp;

public class DatabaseColumn {

    private String name;
    private String location;
    private String phone_no;
    private String groupId;
    private int id;
    private String marks="0";

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    private  String studentName,StudentPhoneNumber,StudentId;
    private boolean selected;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhoneNumber() {
        return StudentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        StudentPhoneNumber = studentPhoneNumber;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public DatabaseColumn(int id, String name, String location, String phone_no, String groupId, String studentName, String studentPhoneNumber, String studentId) {
        this.name = name;
        this.location = location;
        this.phone_no = phone_no;
        this.groupId = groupId;
        this.studentName = studentName;
        StudentPhoneNumber = studentPhoneNumber;
        StudentId = studentId;
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }




    public DatabaseColumn(int i, String group_name, String group_location, String phone_no, String group_id) {
        this.id=i;
        this.name=group_name;
        this.location=group_location;
        this.phone_no=phone_no;
        this.groupId=group_id;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone_no() {
        return phone_no;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
