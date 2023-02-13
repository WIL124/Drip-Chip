package com.example.dripchipsystem.repo.impl;

import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.repo.AnimalRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepositoryCustom {
    private EntityManager entityManager;

    @Override
    public List<Animal> search(LocalDateTime startDateTime, LocalDateTime endDateTime,
                               Integer chipperId, Long chippingLocationId,
                               String lifeStatus, String gender, int from, int size) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = cb.createQuery(Animal.class);
        Root<Animal> accountRoot = criteriaQuery.from(Animal.class);

        List<Predicate> predicates = new ArrayList<>();
        if (startDateTime != null) {
            predicates.add(cb.greaterThanOrEqualTo(accountRoot.get("startDateTime"), startDateTime));
        }
        if (endDateTime != null) {
            predicates.add(cb.lessThanOrEqualTo(accountRoot.get("endDateTime"), endDateTime));
        }
        if (chipperId != null) {
            predicates.add(cb.equal(accountRoot.get("chipperId"), chipperId));
        }
        if (chippingLocationId != null) {
            predicates.add(cb.equal(accountRoot.get("chippingLocationId"), chippingLocationId));
        }
        if (lifeStatus != null) {
            predicates.add(cb.equal(accountRoot.get("lifeStatus"), lifeStatus));
        }
        if (gender != null) {
            predicates.add(cb.equal(accountRoot.get("gender"), gender));
        }
        Order order = cb.asc(accountRoot.get("id"));

        criteriaQuery
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(order);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
}
