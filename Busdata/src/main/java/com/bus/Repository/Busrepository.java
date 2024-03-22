package com.bus.Repository;

import com.bus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Busrepository extends JpaRepository<Bus,Long> {
    List<Bus>findAll();
    @Query(value = "select b.capacity from Bus b where b.busno=:busno",nativeQuery = true)
    Integer findCapacity(Integer busno);
}
