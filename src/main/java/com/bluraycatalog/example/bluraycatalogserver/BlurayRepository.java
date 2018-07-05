package com.bluraycatalog.example.bluraycatalogserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlurayRepository extends JpaRepository<Bluray, Long> {
}
