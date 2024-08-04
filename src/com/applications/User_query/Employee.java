package com.applications.User_query;

public class Employee {
    private int Id;
    private People people;
    private String reporting_To;
    private String name;
    private int age;
    private String designation;
    private String department;

    public enum getEmployee{
        AGE("int"),
        ID("int"),
        NAME("string"),
        DESIGNATION("string"),
        DEPARTMENT("string"),
        REPORTING_TO("string");
        private String type;
        private String field;

        public String getType() {
            return type;
        }

        public void setType(String type ) {
            this.type = type;
        }

        getEmployee(String type){
            this.type = type;
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getReporting_to() {
        return reporting_To;
    }

    public void setReporting_To(String reporting_To) {
        this.reporting_To = reporting_To;
    }

    public Employee(int id, People people, String reporting_To) {
        Id = id;
        this.people = people;
        this.reporting_To = reporting_To;
    }

    public Employee(int id, String reporting_To, String name, int age, String designation, String department) {
        Id = id;
        this.people = people;
        this.reporting_To = reporting_To;
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.department = department;
    }
}
