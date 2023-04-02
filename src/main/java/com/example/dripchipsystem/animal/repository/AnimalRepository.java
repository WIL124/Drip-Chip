package com.example.dripchipsystem.animal.repository;

import com.example.dripchipsystem.common.repository.CommonRepository;
import com.example.dripchipsystem.animal.model.Animal;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CommonRepository<Animal>, AnimalRepositoryCustom {
}
