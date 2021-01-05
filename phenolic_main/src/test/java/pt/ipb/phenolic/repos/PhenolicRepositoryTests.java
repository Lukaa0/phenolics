package pt.ipb.phenolic.repos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pt.ipb.phenolic.PhenolicApplication;
import pt.ipb.phenolic.models.Molecule;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.models.Source;
import pt.ipb.phenolic.repos.PhenolicRepository;

import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PhenolicApplication.class})

//@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class PhenolicRepositoryTests {

    @MockBean
    private PhenolicRepository phenolicRepository;
    @MockBean
    private MoleculeRepository moleculeRepository;
    @MockBean
    private LambdaRepository lambdaRepository;
    @MockBean
    private MSFragmentRepository MSFragmentRepository;
    @MockBean
    private SourceRepository sourceRepository;

    @Test
    public void testCreatePhenolic() {

        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId((long)0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);



        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        long id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

    }
    @Test
    public void testGetPhenolic() {
        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId((long)0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);



        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        long id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, (long)0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

    }
    @Test
    public void testUpdatePhenolic() {
        Set<Molecule> molecule = null;
        Set<Molecule> molecule_2 = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId((long)0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);

        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        long id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, (long)0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

        phenolic.setMolecules(molecule_2);
        phenolicRepository.save(phenolic);

        assertEquals(phenolic.getMolecules(), molecule_2);



    }
    @Test
    public void testDeletePhenolic() {
        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId((long)0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);



        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        long id = phenolic.getId();
        String name = phenolic.getName();



        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

        phenolicRepository.delete(phenolic);
        assertFalse(phenolicRepository.findById(phenolic.getId()).isPresent());

    }

}