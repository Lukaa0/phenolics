package pt.ipb.phenolic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ipb.phenolic.models.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
