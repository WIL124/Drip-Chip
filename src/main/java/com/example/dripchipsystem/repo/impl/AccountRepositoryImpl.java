package com.example.dripchipsystem.repo.impl;

import com.example.dripchipsystem.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.* ;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private EntityManager entityManager;

    @Override
    public List<Account> findByFilter(String firstName, String lastName, String email, int from, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
        Root<Account> accountRoot = criteriaQuery.from(Account.class);

        List<Predicate> predicates = new ArrayList<>();
        if (firstName != null) {
            predicates.add(cb.like(cb.upper(accountRoot.get("firstName")), "%" + firstName.toUpperCase() + "%"));
        }
        if (lastName != null) {
            predicates.add(cb.like(cb.upper(accountRoot.get("lastName")), "%" + lastName.toUpperCase() + "%"));
        }
        if (email != null) {
            predicates.add(cb.like(cb.upper(accountRoot.get("email")), "%" + email.toUpperCase() + "%"));
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
