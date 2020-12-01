package pt.ipb.phenolic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.phenolic.models.Phenolic;

@Repository
public interface PhenolicRepository extends JpaRepository<Phenolic, Integer> {
}
