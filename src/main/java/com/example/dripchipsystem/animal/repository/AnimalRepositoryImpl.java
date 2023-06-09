package com.example.dripchipsystem.animal.repository;

import com.example.dripchipsystem.animal.model.Animal;
import com.example.dripchipsystem.animal.model.Gender;
import com.example.dripchipsystem.animal.model.LifeStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepositoryCustom {
    private EntityManager entityManager;

    @Override
    public List<Animal> search(OffsetDateTime startDateTime, OffsetDateTime endDateTime,
                               Integer chipperId, Long chippingLocationId,
                               LifeStatus lifeStatus, Gender gender, int from, int size) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = cb.createQuery(Animal.class);
        Root<Animal> animalRoot = criteriaQuery.from(Animal.class);

        List<Predicate> predicates = new ArrayList<>();
        if (startDateTime != null) {
            predicates.add(cb.greaterThanOrEqualTo(animalRoot.get("chippingDateTime"), Timestamp.from(startDateTime.toInstant()) ));
        }
        if (endDateTime != null) {
            predicates.add(cb.lessThanOrEqualTo(animalRoot.get("chippingDateTime"), Timestamp.from(endDateTime.toInstant())));
        }
        if (chipperId != null) {
            predicates.add(cb.equal(animalRoot.get("chipper").get("id"), chipperId));
        }
        if (chippingLocationId != null) {
            predicates.add(cb.equal(animalRoot.get("chippingLocationId"), chippingLocationId));
        }
        if (lifeStatus != null) {
            predicates.add(cb.equal(animalRoot.get("lifeStatus"), lifeStatus));
        }
        if (gender != null) {
            predicates.add(cb.equal(animalRoot.get("gender"), gender));
        }
        Order order = cb.asc(animalRoot.get("id"));

        criteriaQuery
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(order);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
}
