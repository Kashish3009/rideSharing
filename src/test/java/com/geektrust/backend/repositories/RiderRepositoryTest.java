package com.geektrust.backend.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Rider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("RiderRepositoryTest")
@ExtendWith(MockitoExtension.class)
public class RiderRepositoryTest {

    @InjectMocks
    private RiderRepository riderRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        riderRepository = mock(RiderRepository.class);
    }

    @Test
    public void saveRiderShouldReturnSavedRider() {
        // Arrange
        Rider rider = new Rider("R1",3,5);
        when(riderRepository.save(rider)).thenReturn(rider);
        // Act
        Rider savedRider = riderRepository.save(rider);
        // Assert
        Assertions.assertEquals(rider, savedRider);
    }

    @Test
    public void findAllForRiderShouldReturnAllRiders() {
        // Arrange
        Rider rider1 = new Rider("R1",3,5);
        Rider rider2 = new Rider("R2",1,1);

        riderRepository.save(rider1);
        riderRepository.save(rider2);
        List<Rider> expectedRiders = List.of(rider1, rider2);
        when(riderRepository.findAll()).thenReturn(expectedRiders);
        // Act
        List<Rider> riders = riderRepository.findAll();
        // Assert
        Assertions.assertEquals(2, riders.size());
    }

    @Test
    public void findByIdForRiderShouldReturnRiderById() {
        // Arrange
        Rider rider = new Rider("R1",3,5);
        when(riderRepository.findById("R1")).thenReturn(Optional.of(rider));
        // Act
        Optional<Rider> foundRider = riderRepository.findById("R1");
        // Assert
       Assertions.assertEquals(rider, foundRider.get());
    }

    @Test
    public void findByIdForRiderShouldReturnEmptyOptionalForNonExistingId() {
        // Act
        Optional<Rider> foundRider = riderRepository.findById("R1");
        // Assert
        Assertions.assertFalse(foundRider.isPresent());
    }

    @Test
    public void existsByIdForRiderShouldReturnTrueForExistingId() {
        // Arrange
        Rider rider = new Rider("R1",3,5);
        when(riderRepository.existsById(rider.getId())).thenReturn(true);
        // Act
        boolean exists = riderRepository.existsById("R1");
        // Assert
        Assertions.assertTrue(exists);
    }

    @Test
    public void existsByIdForRiderShouldReturnFalseForNonExistingId() {
        // Act
        boolean exists = riderRepository.existsById("R1");
        // Assert
        Assertions.assertFalse(exists);
    }
}
