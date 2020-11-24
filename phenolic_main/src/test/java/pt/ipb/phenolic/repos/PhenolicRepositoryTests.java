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
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.repos.PhenolicRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest

public class PhenolicRepositoryTests {

    @Autowired
    private PhenolicRepository phenolicRepository;


    @Test
    public void testCreatePhenolic() {


    }
    @Test
    public void testGetPhenolic() {
    }
    @Test
    public void testUpdatePhenolic() {
    }
    @Test
    public void testDeletePhenolic() {
    }

}