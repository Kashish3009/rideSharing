package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class MatchRepositoryTest {
    @InjectMocks
    private MatchRepository matchRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        matchRepository = mock(MatchRepository.class);
    }
    @Test
    void saveValidMatchShouldReturnSavedMatch() {
        // Arrange
        Match match = new Match("match123", "rider123", List.of("driver1", "driver2"));
        when(matchRepository.save(any(Match.class))).thenReturn(match);
        // Act
        Match savedMatch = matchRepository.save(match);
        // Assert
        assertEquals(match, savedMatch);
        verify(matchRepository, times(1)).save(match);
    }

    @Test
    void findAllForNoMatchesShouldReturnEmptyList() {
        // Act
        List<Match> matches = matchRepository.findAll();
        // Assert
        assertTrue(matches.isEmpty());
    }

    @Test
    void findByIdForExistingMatchIdShouldReturnMatchOptional() {
        // Arrange
        Match match = new Match("match123", "rider123", List.of("driver1", "driver2"));
        when(matchRepository.save(any(Match.class))).thenReturn(match);
        matchRepository.save(match);
        when(matchRepository.findById(match.getId())).thenReturn(Optional.of(match));
        // Act
        Optional<Match> optionalMatch = matchRepository.findById(match.getId());
        // Assert
        assertEquals(match, optionalMatch.get());
    }

    @Test
    void findByIdForNonexistentMatchIdShouldReturnEmptyOptional() {
        // Arrange
        String nonexistentId = "nonexistent123";
        // Act
        Optional<Match> optionalMatch = matchRepository.findById(nonexistentId);
        // Assert
        assertFalse(optionalMatch.isPresent());
    }

    @Test
    void existsByIdForNonexistentMatchIdShouldReturnFalse() {
        // Arrange
        String nonexistentId = "nonexistent123";
        // Act
        boolean exists = matchRepository.existsById(nonexistentId);
        // Assert
        assertFalse(exists);
    }
}
