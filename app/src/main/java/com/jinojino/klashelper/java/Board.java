package com.jinojino.klashelper.java;

public class Board {
    String ClassName;
    String Instructor;
    String ClassCode;

    public Board(){
        this.ClassName = "null";
        this.ClassCode = "null";
        this.Instructor = "null";
    }

    public Board(String ClassName, String Instructor, String ClassCode) {
        this.ClassName = ClassName;
        this.Instructor = Instructor;
        this.ClassCode = ClassCode;
    }

    public String getClassName() { return ClassName; }

    public String getInstructor() { return Instructor; }

    public String getClassCode() { return ClassCode; }

    public void setClassName(String className) { ClassName = className; }

    public void setInstructor(String instructor) { Instructor = instructor; }

    public void setClassCode(String classCode) { ClassCode = classCode; }
}
