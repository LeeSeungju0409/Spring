package com.yedam.app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName); // find: select, By: from , Where: 칼럼 , And //참고사이트 : https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

  Customer findById(long id);
  
  List<Customer> findByLastNameOrFirstName(String lastName, String firstName);
}