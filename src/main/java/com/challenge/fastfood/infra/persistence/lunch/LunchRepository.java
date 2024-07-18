package com.challenge.fastfood.infra.persistence.lunch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepository extends JpaRepository<LunchEntity, Long> {
}
