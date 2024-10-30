package com.yedam.app;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor // @Builder, @NoArgsConstructor, @AllArgsConstructor는 세트임
public class Customer {
  // 기본키 지정 시작
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO) // 시퀀스정책: 시퀀스 쓸거냐 말거냐. 기본키 어떻게 쓸거냐. GenerationType.AUTO = db맞춰서 써라.
  private Long id; // 테이블 이름
  // 끝. 여기까지가 id에 대한 어노테이션 
  
  @Column(length = 30, nullable = false) // varchar같은거 바꿔주는 속성.
  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "customer") // mappedBy: 단순 조회만 하고싶을때
  List<Address> address; //일대다이니까 List로 받음
//  @OneToOne
//  @JoinColumn(name = "address_id")
//  Address address;
  
//  @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "address_id")
//  private List<Address> addresses = new ArrayList<>();
//  
  
  
//  
//  protected Customer() {}
//
//  public Customer(String firstName, String lastName) {
//    this.firstName = firstName;
//    this.lastName = lastName;
//  }

//  @Override
//  public String toString() {
//    return String.format(
//        "Customer[id=%d, firstName='%s', lastName='%s']",
//        id, firstName, lastName);
//  }
//
//  public Long getId() {
//    return id;
//  }
//
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
}