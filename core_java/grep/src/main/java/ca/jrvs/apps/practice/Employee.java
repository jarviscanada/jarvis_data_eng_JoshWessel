package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Employee {

  private int id;
  private String name;
  private int age;
  private long salary;

  public Employee() {}

  public Employee(int id, String name, int age, long salary) {

    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return id == employee.id && age == employee.age && salary == employee.salary
        && Objects.equals(name, employee.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary);
  }

  public static void main(String[] args) {
    Map<Employee, List<String>> empStrMap = new HashMap<>();

    Employee bill = new Employee(1, "Bill", 20, 40000);
    List<String> billPreviousCompanies = Arrays.asList("TD", "RBC", "CIBC");
    empStrMap.put(bill, billPreviousCompanies);

    Employee ted = new Employee(2, "Ted", 25, 45000);
    List<String> tedPreviousCompanies = Arrays.asList("Here", "There", "Somewhere else, probably");
    empStrMap.put(ted, tedPreviousCompanies);

    System.out.println("Bill Hashcode: " + bill.hashCode());
    System.out.println("Bill Value: " + empStrMap.get(bill).toString());
  }
}
