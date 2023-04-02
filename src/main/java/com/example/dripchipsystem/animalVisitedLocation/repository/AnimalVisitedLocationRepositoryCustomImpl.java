package com.example.dripchipsystem.animalVisitedLocation.repository;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animalVisitedLocation.model.AnimalVisitedLocation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AnimalVisitedLocationRepositoryCustomImpl implements AnimalVisitedLocationRepositoryCustom {
    private EntityManager entityManager;
    @Override
    public List<AnimalVisitedLocation> search(Animal animal,LocalDateTime startDateTime, LocalDateTime endDateTime,
                                              int from, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AnimalVisitedLocation> criteriaQuery = cb.createQuery(AnimalVisitedLocation.class);
        Root<AnimalVisitedLocation> locationRoot = criteriaQuery.from(AnimalVisitedLocation.class);

        List<Predicate> predicates = new ArrayList<>();
        if (startDateTime != null) {
            predicates.add(cb.greaterThanOrEqualTo(locationRoot.get("startDateTime"), startDateTime));
        }
        if (endDateTime != null) {
            predicates.add(cb.lessThanOrEqualTo(locationRoot.get("endDateTime"), endDateTime));
        }

        Join<AnimalVisitedLocation, Animal> join = locationRoot.join("animal");
        predicates.add(join.getOn());

        Order order = cb.asc(locationRoot.get("id"));
        criteriaQuery
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(order);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
}
