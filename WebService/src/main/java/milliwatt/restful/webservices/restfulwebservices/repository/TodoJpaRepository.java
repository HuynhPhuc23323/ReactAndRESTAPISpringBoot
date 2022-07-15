package milliwatt.restful.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import milliwatt.restful.webservices.restfulwebservices.entity.Todo;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long>{

	List<Todo> findByUsername(String username);
	
}
