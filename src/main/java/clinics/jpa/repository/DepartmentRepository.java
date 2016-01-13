package clinics.jpa.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import clinics.entity.Department;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {

	public Department findByName(String name);

	public List<Department> findAllByNameLike(String name);
}
