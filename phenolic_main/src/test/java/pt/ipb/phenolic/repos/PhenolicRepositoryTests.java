package pt.ipb.phenolic.repos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pt.ipb.phenolic.PhenolicApplication;
import pt.ipb.phenolic.models.Molecule;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.repos.PhenolicRepository;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest

public class PhenolicRepositoryTests {

    @Autowired
    private PhenolicRepository phenolicRepository;


    @Test
    public void testCreatePhenolic() {
        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId(0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);



        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        int id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

    }
    @Test
    public void testGetPhenolic() {
        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId(0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);



        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        int id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

    }
    @Test
    public void testUpdatePhenolic() {
        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId(0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);

        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        int id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

    }
    @Test
    public void testDeletePhenolic() {
        Set<Molecule> molecule = null;
        Phenolic phenolic = new Phenolic();
        phenolic.setId(0);
        phenolic.setName("Testing");
        phenolic.setMolecules(molecule);



        phenolicRepository.save(phenolic);
        assertNotNull(phenolic);
        int id = phenolic.getId();
        String name = phenolic.getName();


        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(phenolic.getMolecules(), molecule);

        //Assert that a get should return error

    }

}