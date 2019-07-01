package uk.ac.ebi.test.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.ebi.test.entities.SecurityUser;

@RepositoryRestResource
public interface SecurityUserRepository extends PagingAndSortingRepository<SecurityUser, Long> {
    SecurityUser findByEmail(@Param("email") String email);
}
