package by.karzhou.clevertec.TestTaskClevertec.services;

import by.karzhou.clevertec.TestTaskClevertec.until.ProductNotFoundException;
import by.karzhou.clevertec.TestTaskClevertec.models.Product;
import by.karzhou.clevertec.TestTaskClevertec.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }


    public Product findById(int id){
        Optional<Product> foundProduct = productRepository.findProductById(id);
       return foundProduct.orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void update(Product updateProduct, int id){
        updateProduct.setId(id);
        productRepository.save(updateProduct);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
