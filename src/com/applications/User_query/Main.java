package com.applications.User_query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static HashMap<Integer,Employee> employeeHashMap;
    public static Scanner sc;
    public static void main(String[] args){
        employeeHashMap = new HashMap<>();
        sc = new Scanner(System.in);
        initializeValues();
        printAllEmployees(employeeHashMap);
        hierarchyReporting();
        while (true) {
            processQuery();
        }
    }
    public static void initializeValues(){
        System.out.println("Enter the number of employees:");
        int num = sc.nextInt();
        for(int i=1;i<=num;i++){
            System.out.println("Enter the Name:");
            String name = sc.next();
            System.out.println("Enter the Age:");
            int age = sc.nextInt();
            System.out.println("Enter the Designation:");
            String designation = sc.next();
            System.out.println("Enter the Department:");
            String department = sc.next();
            System.out.println("Enter the Reporting To:");
            String reportingTo = sc.next();
            Employee employee = new Employee(i,reportingTo,name,age,designation,department);
            employeeHashMap.put(i,employee);
        }
    }
    public static void printAllEmployees(HashMap<Integer,Employee> map){
        for(Map.Entry m : map.entrySet()){
            Employee employee = (Employee) m.getValue();
            System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getAge()+" "+employee.getDesignation()+" "+employee.getDesignation()+" "+employee.getReporting_to());
        }
    }
    public static void processQuery(){
        String s = "",ss="";
        do{
            s = sc.next();
            if(!s.equals("exit")) {
                if(ss.equals("")){
                    ss = s;
                }else {
                    ss = ss + " " + s;
                }
            }
        }
        while (!s.equals("exit"));
        if(ss.contains("and")){
            String[] arr = Arrays.stream(ss.split("and")).filter((a)-> !a.isEmpty()).map(String::trim).toArray(String[]::new);
            HashMap<Integer,Employee> tempHash = new HashMap<>(employeeHashMap);
            for (String string : arr) {
                tempHash = getData(string, tempHash);
            }
            printAllEmployees(tempHash);
        }
        else{
            HashMap<Integer,Employee> tempHash = getData(ss,employeeHashMap);
            printAllEmployees(tempHash);
        }
    }
    public static HashMap<Integer,Employee> getData(String ss,HashMap<Integer,Employee> map){
        String[] arr = ss.split(" ");
        arr[0] = arr[0].trim();
        String sss = "get"+arr[0].substring(0,1).toUpperCase()+arr[0].substring(1,arr[0].length());
        HashMap<Integer,Employee> tempHashMap = new HashMap<>();
        for(Map.Entry m : map.entrySet()){
            Employee employee = (Employee) m.getValue();
            Employee.getEmployee d = Employee.getEmployee.valueOf(arr[0].toUpperCase());
            try {
                Method ms = Employee.class.getDeclaredMethod(sss);
                Object rv = ms.invoke(employee);
                if(isValid(d,arr,rv)){
                    tempHashMap.put((int)m.getKey(),(Employee) m.getValue());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        }
        return tempHashMap;
    }
    public static boolean isValid(Employee.getEmployee d,String[] arr,Object rv){
        if(d.getType().equals("int")){
            int val = Integer.parseInt(arr[2]);
            int fieldVal = (int) rv;
            switch (arr[1]){
                case ">":{
                    if(fieldVal>val){
                        return true;
                    }
                    break;
                }
                case "<":{
                    if(fieldVal<val){
                        return true;
                    }
                    break;
                }
                case "!=":{
                    if(fieldVal!=val){
                        return true;
                    }
                    break;
                }
                case "==":{
                    if(fieldVal==val){
                        return true;
                    }
                    break;
                }
            }
        }
        else{
            String val = arr[2];
            String fieldVal = (String) rv;
            switch (arr[1]){
                case "startswith":{
                    for(int i=0;i<val.length();i++){
                        if(fieldVal.charAt(i)!=val.charAt(i)){
                            return false;
                        }
                    }
                    return true;
                }
                case "contains":{
                    return fieldVal.contains(val);
                }
                case "endswith":{
                    for(int i=fieldVal.length()-1,j=val.length()-1;j>=0;i--,j--){
                        if(fieldVal.charAt(i)!=val.charAt(j)){
                            return false;
                        }
                    }
                    return true;
                }
                case "notcontains":{
                    return !fieldVal.contains(val);
                }
                case "equals":{
                    return fieldVal.equals(val);
                }
                case "notequals":{
                    return !fieldVal.equals(val);
                }
            }
        }
        return false;
    }
    public static void hierarchyReporting(){
        System.out.println("Enter the employee name:");
        String name = sc.next();

        while(!name.isEmpty()){
            System.out.println(name);
            String ss = "name equals "+name;
            name = "";
            HashMap<Integer,Employee> tempHash = getData(ss,employeeHashMap);
            for(Map.Entry m : tempHash.entrySet()){
                name = ((Employee)m.getValue()).getReporting_to();
            }
        }
    }
}
