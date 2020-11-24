package pt.ipb.phenolic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ipb.phenolic.models.Molecule;

public interface MoleculeRepository extends JpaRepository<Molecule, Long> {
}
