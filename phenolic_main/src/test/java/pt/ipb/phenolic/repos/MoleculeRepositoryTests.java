package pt.ipb.phenolic.repos;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest

public class MoleculeRepositoryTests {

    @Autowired
    private MoleculeRepository moleculeRepository;


    @Test
    public void TestCreateMolecule(){

    }

    @Test
    public void TestReadMolecule(){

    }

    @Test
    public void TestUpdateolecule(){

    }

    @Test
    public void TestDeleteMolecule(){

    }
}
