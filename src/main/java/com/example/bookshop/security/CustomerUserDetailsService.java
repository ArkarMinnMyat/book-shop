package com.example.bookshop.security;

import com.example.bookshop.dao.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerDao customerDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao
                .findCustomerByName(username)
                .map(SecurityCustomer::new)
                .orElseThrow(()-> new UsernameNotFoundException("Error!!!"));
    }
}
