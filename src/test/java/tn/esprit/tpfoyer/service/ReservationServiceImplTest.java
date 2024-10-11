package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito for the test class
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository; // Mock the repository

    @InjectMocks
    private ReservationServiceImpl reservationService; // Inject the mock repository into the service

    @Test
    public void testRetrieveAllReservations() {
        // Arrange: Create a mock reservation
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation("1234");
        mockReservation.setEstValide(true);

        // Mock the repository method to return the mock reservation
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(mockReservation));

        // Act: Call the service method
        List<Reservation> reservations = reservationService.retrieveAllReservations();

        // Assert: Verify that the service returns the expected reservation
        assertEquals(1, reservations.size());
        assertEquals("1234", reservations.get(0).getIdReservation());
    }

    @Test
    public void testRetrieveReservation() {
        // Arrange: Create a mock reservation
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation("1234");

        // Mock the repository method
        when(reservationRepository.findById("1234")).thenReturn(Optional.of(mockReservation));

        // Act: Call the service method
        Reservation reservation = reservationService.retrieveReservation("1234");

        // Assert: Verify the result
        assertEquals("1234", reservation.getIdReservation());
    }

    @Test
    public void testAddReservation() {
        // Arrange: Create a mock reservation
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation("1234");

        // Mock the save method
        when(reservationRepository.save(mockReservation)).thenReturn(mockReservation);

        // Act: Call the service method
        Reservation addedReservation = reservationService.addReservation(mockReservation);

        // Assert: Verify the result
        assertEquals("1234", addedReservation.getIdReservation());
    }

    @Test
    public void testModifyReservation() {
        // Arrange: Create a mock reservation
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation("1234");

        // Mock the save method
        when(reservationRepository.save(mockReservation)).thenReturn(mockReservation);

        // Act: Call the service method
        Reservation modifiedReservation = reservationService.modifyReservation(mockReservation);

        // Assert: Verify the result
        assertEquals("1234", modifiedReservation.getIdReservation());
    }

    @Test
    public void testTrouverResSelonDateEtStatus() {
        // Arrange: Create mock data
        Reservation mockReservation = new Reservation();
        mockReservation.setIdReservation("1234");

        // Mock the repository method
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(any(Date.class), eq(true)))
                .thenReturn(Collections.singletonList(mockReservation));

        // Act: Call the service method
        List<Reservation> reservations = reservationService.trouverResSelonDateEtStatus(new Date(), true);

        // Assert: Verify the result
        assertEquals(1, reservations.size());
        assertEquals("1234", reservations.get(0).getIdReservation());
    }

    @Test
    public void testRemoveReservation() {
        // Arrange: Do nothing when deleteById is called
        doNothing().when(reservationRepository).deleteById("1234");

        // Act: Call the service method
        reservationService.removeReservation("1234");

        // Assert: Verify that the repository's deleteById method was called
        verify(reservationRepository, times(1)).deleteById("1234");
    }
}