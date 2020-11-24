package pt.ipb.phenolic.repos;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest

public class SourceRepositoryTests {

    @Autowired
    private SourceRepository sourceRepository;


    @Test
    public void TestCreateSource(){

    }

    @Test
    public void TestReadSource(){

    }

    @Test
    public void TestUpdateSource(){

    }

    @Test
    public void TestDeleteSource(){

    }
}
