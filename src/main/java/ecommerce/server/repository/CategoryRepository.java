package ecommerce.server.repository;

import ecommerce.server.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categories (name, slug) VALUES (:name, :slug)", nativeQuery = true)
    void addCategory(@Param("name") String name, @Param("slug") String slug);

    @Transactional
    @Query(value = "SELECT id, name, slug FROM categories", nativeQuery = true)
    List<Category> getAllCategories();

    @Transactional
    @Query(value = "SELECT * FROM categories WHERE :id = id", nativeQuery = true)
    Optional<Category> getCategoryById(@Param("id") Integer id);
}
