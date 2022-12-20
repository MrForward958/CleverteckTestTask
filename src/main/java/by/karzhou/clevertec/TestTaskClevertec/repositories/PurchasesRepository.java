package by.karzhou.clevertec.TestTaskClevertec.repositories;

import by.karzhou.clevertec.TestTaskClevertec.models.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, Integer> {
}
