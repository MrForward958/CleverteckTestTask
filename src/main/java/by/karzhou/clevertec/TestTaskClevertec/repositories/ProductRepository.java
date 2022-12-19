package by.karzhou.clevertec.TestTaskClevertec.repositories;

import by.karzhou.clevertec.TestTaskClevertec.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAll();

    public Optional<Product> findProductById(int id);
}
