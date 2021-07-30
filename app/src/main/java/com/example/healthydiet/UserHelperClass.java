package com.example.healthydiet;

public class UserHelperClass {

    String name, email, sex, height, weight, age;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String sex, String height, String weight,String age) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String weight) {
        this.age = age;
    }
}
