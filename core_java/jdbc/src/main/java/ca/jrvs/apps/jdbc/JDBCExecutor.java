package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCExecutor {

  public static void main(String... args) {
   DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost","hplussport","postgres","password");

   try {
     Connection connection = dcm.getConnection();
     CustomerDAO customerDAO = new CustomerDAO(connection);

     // Additions to 2. Creating Data:
     //Customer customer = new Customer();
     //customer.setFirstName("Abraham");
     //customer.setLastName("Lincoln");
     //customer.setEmail("abe@lincoln.com");
     //customer.setPhone("(555) 555-5555");
     //customer.setAddress("1234 Main St");
     //customer.setCity("Los Angeles");
     //customer.setState("CA");
     //customer.setZipCode("55555");
     //customerDAO.create(customer);

     // Additions to 2. Reading Data:
     //Customer customer = customerDAO.findById(1000);
     //System.out.println(customer.getFirstName() + " " + customer.getLastName());

     // Additions to 2. Updating Data:
     //Customer customer = customerDAO.findById(10000);
     //System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
     //customer.setEmail("so-crates@dustinthe.wind");
     //customer = customerDAO.update(customer);
     //System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());

     // Additions to 2. Deleting Data:
     //Customer customer = new Customer();
     //customer.setFirstName("William");
     //customer.setLastName("Shakespeare");
     //customer.setEmail("william@shakespeare.com");
     //customer.setAddress("1234 Main St");
     //customer.setCity("Oakland");
     //customer.setState("CA");
     //customer.setPhone("(555) 444-3333");
     //customer.setZipCode("01234");

     //Customer dbCustomer = customerDAO.create(customer);
     //System.out.println(dbCustomer);
     //dbCustomer = customerDAO.findById(dbCustomer.getId());
     //System.out.println(dbCustomer);
     //dbCustomer.setEmail("will@shakespeare.com");
     //dbCustomer = customerDAO.update(dbCustomer);
     //System.out.println(dbCustomer);
     //customerDAO.delete(dbCustomer.getId());

     // Additions to 3. Using stored procedures
     OrderDAO orderDAO = new OrderDAO(connection);
     List<Order> orders = orderDAO.getOrdersForCustomer(789);
     orders.forEach(System.out::println);



//     customerDAO.findAllSorted(20).forEach(System.out::println);
//     System.out.println("paged");
//     for (int i = 1; i < 3; i++) {
//       System.out.println("Page number: " + i);
//       customerDAO.findAllPaged(10, i).forEach(System.out::println);
//     }
   } catch(SQLException e) {
     e.printStackTrace();
   }
  }
}
