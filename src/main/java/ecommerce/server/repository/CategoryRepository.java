package ecommerce.server.repository;

import ecommerce.server.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Transactional
    @Query(value = "INSERT INTO categories c (name) VALUES (:name)", nativeQuery = true)
    @Modifying
    void addCategory(String name);

    @Transactional
    @Query(value = "SELECT * FROM categories", nativeQuery = true)
    List<Category> getAllCategories();
}
