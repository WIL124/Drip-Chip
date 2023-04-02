package com.example.dripchipsystem.animal.repository;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animal.model.Gender;
import com.example.dripchipsystem.animal.model.LifeStatus;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface AnimalRepositoryCustom {
    List<Animal> search(OffsetDateTime startDateTime, OffsetDateTime endDateTime, Integer chipperId, Long chippingLocationId,
                        LifeStatus lifeStatus, Gender gender, int from, int size);
}
