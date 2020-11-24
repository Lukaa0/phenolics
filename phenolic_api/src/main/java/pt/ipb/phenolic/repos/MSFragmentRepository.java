package pt.ipb.phenolic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.phenolic.models.MSFragment;

@Repository
public interface MSFragmentRepository extends JpaRepository<MSFragment, Integer> {
}
