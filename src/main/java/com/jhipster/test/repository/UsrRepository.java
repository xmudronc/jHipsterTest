package com.jhipster.test.repository;

import com.jhipster.test.domain.Usr;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Usr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsrRepository extends JpaRepository<Usr, Long> {

}
