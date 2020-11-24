package pt.ipb.phenolic.repos;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pt.ipb.phenolic.models.Food;
import pt.ipb.phenolic.models.Molecule;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PhenolicApplication.class})

@DataJpaTest


public class FoodRepositoryTests {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void TestCreateFood(){
        Food food = new Food();
        List<Molecule> molecule = null;
        food.setId(0);
        food.setMolecules(molecule);

        foodRepository.save(food);
        assertNotNull(food);

        int id = food.getId();
        assertEquals(id, 0);
        assertEquals(food.getMolecules(), molecule);







    }

    @Test
    public void TestReadFood(){
        Food food = new Food();
        List<Molecule> molecule = null;
        food.setId(0);
        food.setMolecules(molecule);

        foodRepository.save(food);
        assertNotNull(food);

        int id = food.getId();
        assertEquals(id, 0);
        assertEquals(food.getMolecules(), molecule);

    }

    @Test
    public void TestUpdateFood(){
        Food food = new Food();
        List<Molecule> molecule = null;
        List<Molecule> molecule_2 = null;
        food.setId(0);
        food.setMolecules(molecule);

        foodRepository.save(food);
        assertNotNull(food);

        int id = food.getId();
        assertEquals(id, 0);
        assertEquals(food.getMolecules(), molecule);

        food.setMolecules(molecule_2);

        foodRepository.save(food);
        assertEquals(food.getMolecules(), molecule_2);




    }

    @Test
    public void TestDeleteFood(){

        Food food = new Food();
        List<Molecule> molecule = null;
        List<Molecule> molecule_2 = null;
        food.setId(0);
        food.setMolecules(molecule);

        foodRepository.save(food);
        assertNotNull(food);

        int id = food.getId();
        assertEquals(id, 0);
        assertEquals(food.getMolecules(), molecule);

        food.setMolecules(molecule_2);

        foodRepository.save(food);
        assertEquals(food.getMolecules(), molecule_2);

        foodRepository.delete(food);

        assertFalse(foodRepository.findById(food.getId()).isPresent());



    }
}
