package ca.jrvs.apps.practice;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

public class ComparableComparatorPractice {

  static class EmployeeComparator implements Comparator<Employee> {
    public int compare(Employee emp1, Employee emp2) {
      if (emp1.age > emp2.age) {
        return 1;
      }
      else if (emp1.age < emp2.age) {
        return -1;
      }
      else {
        return 0;
      }
    }
  }

  public static class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private int age;
    private long salary;

    public int compareTo(Employee emp1) {
      if (this.age > emp1.age) {
        return 1;
      }
      else if (this.age < emp1.age) {
        return -1;
      }
      else {
        return 0;
      }
    }

    public Employee() {

    }

    public Employee(int id, String name, int age, long salary) {
      this.id = id;
      this.name = name;
      this.age = age;
      this.salary = salary;
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

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public long getSalary() {
      return salary;
    }

    public void setSalary(long salary) {
      this.salary = salary;
    }
  }


  public static void main(String[] args) {

    List<Employee> employees = new ArrayList<>();

    Employee emp1 = new Employee(1, "Bill", 17, 30000);
    Employee emp2 = new Employee(2, "Ted", 16, 25000);
    Employee emp3 = new Employee(3, "Excellent", 25, 40000);
    Employee emp4 = new Employee(4, "Adventure", 15, 60000);

    employees.add(emp1);
    employees.add(emp2);
    employees.add(emp3);
    employees.add(emp4);

    Collections.sort(employees, new EmployeeComparator());
    for (int i = 0; i < employees.size(); i++) {
      System.out.println(employees.get(i).getName() + employees.get(i).getAge());
    }

    List<Employee> employees2 = new ArrayList<>();

    Employee emp5 = new Employee(1, "Bill", 45, 30000);
    Employee emp6 = new Employee(2, "Ted", 18, 25000);
    Employee emp7 = new Employee(3, "Excellent", 55, 40000);
    Employee emp8 = new Employee(4, "Adventure", 28, 60000);

    employees2.add(emp5);
    employees2.add(emp6);
    employees2.add(emp7);
    employees2.add(emp8);

    Collections.sort(employees2);
    for (int i = 0; i < employees2.size(); i++) {
      System.out.println(employees2.get(i).getName() + employees2.get(i).getAge());
    }

  }
}





