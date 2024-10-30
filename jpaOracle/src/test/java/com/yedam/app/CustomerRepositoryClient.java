package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CustomerRepositoryClient {
	
	@Autowired CustomerRepository repository;
	@Autowired AddressRepository addressRepository;
	//@Test
	void save() {
		
		repository.save(Customer.builder().firstName("김").lastName("길동").build());
		
		Customer customer = repository.findById(1L); //findById: select from where
		assertEquals(customer.getFirstName(), "김");
	}
	
	//@Test
	void find() {
		List<Customer> cust = repository.findByLastNameOrFirstName("홍", "기자"); // 칼럼 대소문자 주의
		System.out.println(cust);
	}
	
//	@Test
//	@Transactional
//	void manyToOne() {
//		Customer customer = new Customer("홍", "기자");
//		repository.save(customer);
//		
//		
//		//Address insert
//		addressRepository.save( Address.builder()
//										.zipcode("01440")
//										.address("대구")
//										.detail_address("4번지")
//										.customer(customer)
//										.build());
//		addressRepository.save( Address.builder()
//				.zipcode("01441")
//				.address("서울")
//				.detail_address("100번지")
//				.customer(customer)
//				.build());
//		
//		// customer 조회
//		System.out.println( repository.findAll());
	
	//@Test
	void oneToOneCustomerOwner() {
		
		//Customer 등록
		Customer customer = Customer.builder().firstName("김").lastName("길동")
				.build();
		repository.save(customer);
		//Address 등록
		Address address = Address.builder().zipcode("11122").address("대구").customer(customer).build();
		addressRepository.save(address);
		
		address = Address.builder().zipcode("00011").address("서울").customer(customer).build();
		addressRepository.save(address);
		
		//Optional<Address> cust = addressRepository.findById(1L); //Optional: null check까지 해줌.
		
		//System.out.println(cust.get().getZipcode() + ":" + cust.get().getCustomer().getFirstName());
		
	}
	
	
	@Test
	@Transactional
	void oneToMany() {
		Customer customer = repository.findById(1L);
		System.out.println(customer.getFirstName());
		
		customer.getAddress().forEach(addr -> System.out.println(addr.getZipcode()));
	}
}
