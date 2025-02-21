package com.cst438.airlinereservation.domain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

import com.cst438.airlinereservation.controller.FlightController;
import com.cst438.airlinereservation.services.UserDetailsServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name= "USERTABLE")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String username;
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Flight> bookedFlights;
    public void addBookedFlight(Flight flight) {
        bookedFlights.add(flight);
        flight.setUser(this); // Set the owning side of the relationship
    }
    public User() {
        // Initialize the bookedFlights list
        this.bookedFlights = new ArrayList<>();
    }

    // existing code...



    public long getUserid() {
        return id;
    }

    public void setUserid(long userid) {
        id = userid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<Flight> getBookedFlights() {
        return bookedFlights;
    }

    public void setBookedFlights(List<Flight> bookedFlights) {
        this.bookedFlights = bookedFlights;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}

