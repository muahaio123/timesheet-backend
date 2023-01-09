package com.beaconfire.authservice.service;

import com.beaconfire.authservice.dao.EmployeesDAO;
import com.beaconfire.authservice.entity.Employees;
import com.beaconfire.authservice.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationEmployeeDetailsService implements UserDetailsService {
    private final EmployeesDAO employeesDAO;

    @Autowired
    public ApplicationEmployeeDetailsService(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employees> employeeOptional = employeesDAO.loadEmployeeByEmail(email);

        if (!employeeOptional.isPresent()) {
            throw new UsernameNotFoundException("Email does not exist");
        }

        Employees employee = employeeOptional.get();

        return AuthUserDetail.builder()
                .username(employee.getEmail())
                .password(new BCryptPasswordEncoder().encode(employee.getPassword()))
                .employeeId(employee.getId())
                .authorities(getAuthoritiesFromExistingUser(employee))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    @Transactional
    public List<GrantedAuthority> getAuthoritiesFromExistingUser(Employees employee) {
        // make that list of user's permission into granted authorities
        List<GrantedAuthority> userAuthorities = new ArrayList<>();

        userAuthorities.add(new SimpleGrantedAuthority("read"));
        userAuthorities.add(new SimpleGrantedAuthority("write"));
        userAuthorities.add(new SimpleGrantedAuthority("update"));
        userAuthorities.add(new SimpleGrantedAuthority("delete"));

        return userAuthorities;
    }
}
