package edu.miu.cs.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.cs.cs544.domain.CountryRegion;

@Repository
@Transactional
public interface CountryRegionRepository extends JpaRepository<CountryRegion, String> {

}
