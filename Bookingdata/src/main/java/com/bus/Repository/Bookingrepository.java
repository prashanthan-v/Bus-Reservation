package com.bus.Repository;

import com.bus.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Bookingrepository extends JpaRepository<Booking,Integer> {

    List<Booking>findAll();
    @Query(value = "select count(b.busno)from Booking b where b.busno=:busno",nativeQuery = true )
    Integer getcount(Integer busno);
}
