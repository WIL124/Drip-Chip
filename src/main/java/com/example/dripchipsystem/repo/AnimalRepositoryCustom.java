package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Animal;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnimalRepositoryCustom {
    List<Animal> search(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer chipperId, Long chippingLocationId,
                        String lifeStatus, String gender, int from, int size);
}
