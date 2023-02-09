package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AccountSpecification implements Specification<Account> {
    private String lastName;
    private String firstName;

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (lastName != null) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
        }
        if (firstName != null) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}