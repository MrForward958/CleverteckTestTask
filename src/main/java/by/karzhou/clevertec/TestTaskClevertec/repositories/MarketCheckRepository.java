package by.karzhou.clevertec.TestTaskClevertec.repositories;

import by.karzhou.clevertec.TestTaskClevertec.models.MarketCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketCheckRepository extends JpaRepository<MarketCheck, Integer> {

    public List<MarketCheck> findAll();

    public Optional<MarketCheck> findMarketCheckById(int id);
}
