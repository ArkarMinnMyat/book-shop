package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;

    public Customer(String name,String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(mappedBy = "customer")
    private List<Order> orders=
            new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles =
            new HashSet<>();

    public void addRole(Role role){
        role.getCustomers().add(this);
        this.roles.add(role);
    }

    public void addOrder(Order order){
        order.setCustomer(this);
        orders.add(order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
