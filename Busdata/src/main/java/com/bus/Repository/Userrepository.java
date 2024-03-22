package com.bus.Repository;


import com.bus.model2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userrepository extends JpaRepository<User,Integer> {
    Optional<User>findByUsername(String username);

}
