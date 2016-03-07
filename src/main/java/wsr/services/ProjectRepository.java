package wsr.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import wsr.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project save(Project report);
	List<Project> findAll();
	Project findOne(Long id);

}
