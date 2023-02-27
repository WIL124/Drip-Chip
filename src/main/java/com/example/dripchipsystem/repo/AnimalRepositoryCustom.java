package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.Gender;
import com.example.dripchipsystem.model.LifeStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface AnimalRepositoryCustom {
    List<Animal> search(OffsetDateTime startDateTime, OffsetDateTime endDateTime, Integer chipperId, Long chippingLocationId,
                        LifeStatus lifeStatus, Gender gender, int from, int size);
}
