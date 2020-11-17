package pt.ipb.phenolic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ipb.phenolic.models.Source;

public interface SourceRepository extends JpaRepository<Source, Integer> {
}
