package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.repo.impl.AnimalRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CommonRepository<Animal>, AnimalRepositoryCustom {
}
