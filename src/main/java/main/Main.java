package main;


import configuration.JPAConfig;
import entity.CustomerEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static CustomerRepository customerRepository = (CustomerRepository) context.getBean("customerRepository");

    public static void main(String[] args) {
       // createCustomer();
       // listAllCustomer();
       // findById(3);
       // findByName("Leo");
        // findByPhoneOrEmail("0862427300", "Leo.mt34@gmail.com.vn");
        findBySexAndYears();

    }

    private static void createCustomer() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Leo");
        customerEntity.setBirthdate(LocalDate.parse("1992-10-01"));
        customerEntity.setAddress("SG");
        customerEntity.setSex("Nam");
        customerEntity.setEmail("Leo.mt34@gmail.com");
        customerEntity.setPhone("0877427300");

        CustomerEntity result = customerRepository.save(customerEntity);
        if (result != null) {
            System.out.println("A new customer saved successfully, customer Id " + customerEntity.getId());
        }
    }

    private static void listAllCustomer() {
        List<CustomerEntity> list = (List<CustomerEntity>) customerRepository.findAll();
        System.out.println("List Customer:");
        for (CustomerEntity customer : list) {
            System.out.println(customer.toString());
        }
    }

    private static void findById(int id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if (!customerEntity.isPresent()) {
            System.out.println("Not exist");
        } else
            System.out.println(customerEntity.toString());
    }

    private static void findByName(String name) {
        List<CustomerEntity> listCustomer = customerRepository.findByName(name);
        if (listCustomer.isEmpty()) {
            System.out.println("Not exist");
        } else
            for (CustomerEntity customer : listCustomer) {
                System.out.println(customer.toString());
            }
    }

    private static void findByPhoneOrEmail(String phone, String email) {
        List<CustomerEntity> customerEntityList = customerRepository.findByPhoneOrEmail(phone, email);
        if (customerEntityList.isEmpty()) {
            System.out.println("Customer not exist");
        } else
            for (CustomerEntity customer : customerEntityList) {
                System.out.println(customer.toString());
            }
    }

    private static void findBySexAndYears() {
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusDays(20);
        LocalDate after = now.minusYears(30);
        List<CustomerEntity> customerEntityList = customerRepository.findBySexAndBirthdateBetween( "Nam", after, before);
        if (customerEntityList.isEmpty()) {
            System.out.println("Customer not exist");
        } else
            for (CustomerEntity customer : customerEntityList) {
                System.out.println(customer.toString());
            }
    }


}
