package pt.ipb.phenolic.repos;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pt.ipb.phenolic.PhenolicApplication;
import pt.ipb.phenolic.models.Phenolic;
import pt.ipb.phenolic.models.Source;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest

public class SourceRepositoryTests {

    @Autowired
    private SourceRepository sourceRepository;


    @Test
    public void TestCreateSource(){
        Source source = new Source();
        source.setId((long)0);
        source.setName("Testing");
        Set<Phenolic> phenolic=null;
        source.setPhenolics(phenolic);
        sourceRepository.save(source);
        assertNotNull(source);

        long id = source.getId();
        String name = source.getName();

        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(source.getPhenolics(), phenolic);






    }

    @Test
    public void TestReadSource(){
        Source source = new Source();
        source.setId((long)0);
        source.setName("Testing");
        Set<Phenolic> phenolic=null;
        source.setPhenolics(phenolic);
        sourceRepository.save(source);
        assertNotNull(source);

        long id = source.getId();
        String name = source.getName();

        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(source.getPhenolics(), phenolic);

    }

    @Test
    public void TestUpdateSource(){
        Source source = new Source();
        source.setId((long)0);
        source.setName("Testing");
        Set<Phenolic> phenolic=null;
        Set<Phenolic> phenolic_2=null;

        source.setPhenolics(phenolic);
        sourceRepository.save(source);
        assertNotNull(source);

        long id = source.getId();
        String name = source.getName();

        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(source.getPhenolics(), phenolic);

        source.setPhenolics(phenolic_2);
        sourceRepository.save(source);

        assertEquals(source.getPhenolics(), phenolic_2);





    }

    @Test
    public void TestDeleteSource(){

        Source source = new Source();
        source.setId((long)0);
        source.setName("Testing");
        Set<Phenolic> phenolic=null;
        Set<Phenolic> phenolic_2=null;

        source.setPhenolics(phenolic);
        sourceRepository.save(source);
        assertNotNull(source);

        long id = source.getId();
        String name = source.getName();

        assertEquals(id, 0);
        assertEquals(name, "Testing");
        assertEquals(source.getPhenolics(), phenolic);

        source.setPhenolics(phenolic_2);
        sourceRepository.save(source);

        assertEquals(source.getPhenolics(), phenolic_2);


        sourceRepository.delete(source);

        assertFalse(sourceRepository.findById(source.getId()).isPresent());


    }
}
