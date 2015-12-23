package clinics.jpa.repository;

import org.springframework.stereotype.Repository;

import clinics.entity.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer> {

}
