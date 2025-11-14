package com.example.libreria.repository;

import com.example.libreria.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    // TODO: Implementar los métodos de la reserva
    List<Reservation> findAllByUserId(Long userId);
    List<Reservation> findAllByStatusIs(Reservation.ReservationStatus status);
    @Query("""
        SELECT r FROM Reservation r
        WHERE (r.status = 'ACTIVE' OR r.status = 'OVERDUE')
        AND r.expectedReturnDate < CURRENT_DATE
    """)
    List<Reservation> findOverdueReservations();
}

