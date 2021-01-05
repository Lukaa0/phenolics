package pt.ipb.phenolic.repos;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import pt.ipb.phenolic.models.Food;
import pt.ipb.phenolic.PhenolicApplication;
import pt.ipb.phenolic.models.Molecule;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest

public class MoleculeRepositoryTests {

    @Autowired
    private MoleculeRepository moleculeRepository;


    @Test
    public void TestCreateMolecule(){
        Molecule molecule = new Molecule();


        molecule.setId((long)0);



        moleculeRepository.save(molecule);


        assertNotNull(molecule);

        long id = molecule.getId();

        assertEquals(id,0);




    }

    @Test
    public void TestReadMolecule(){
        Molecule molecule = new Molecule();


        molecule.setId((long)0);



        moleculeRepository.save(molecule);


        assertNotNull(molecule);

        long id = molecule.getId();

        assertEquals(id,0);


    }

    @Test
    public void TestUpdateMolecule(){
        Molecule molecule = new Molecule();

        molecule.setId((long)0);



        moleculeRepository.save(molecule);


        assertNotNull(molecule);

        long id = molecule.getId();

        assertEquals(id,0);


        moleculeRepository.save(molecule);




    }

    @Test
    public void TestDeleteMolecule(){

        Molecule molecule = new Molecule();

        molecule.setId((long)0);



        moleculeRepository.save(molecule);


        assertNotNull(molecule);

        long id = molecule.getId();

        assertEquals(id,0);


        moleculeRepository.delete((molecule));

        assertFalse(moleculeRepository.findById(molecule.getId()).isPresent());


    }
}
