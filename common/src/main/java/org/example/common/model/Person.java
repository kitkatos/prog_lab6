package org.example.common.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

@Data
public class Person implements Validatable{
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private String name;
    private Date birthday;
    private Long height;
    private int weight;
    private String passportID;

    public Person(String name, Date birthday, Long height, int weight, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID.trim();
    }

    @Override
    public String toString(){
        return "Person{name=" + name + ", birthday=" + DATE_FORMAT.format(birthday)
        + ", height=" + height + ", weight=" + weight 
        + ", passportID=" + passportID + "}";
    }

    @Override
    public boolean validate(){
        if (name == null || name.isEmpty()) return false;
        if (birthday == null) return false;
        if (height <= 0) return false;
        if (weight <= 0) return false;
        return (!(passportID == null) && passportID.length() >= 6 && passportID.length() <= 47);
    }
}