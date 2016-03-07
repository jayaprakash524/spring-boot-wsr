package wsr.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wsr.domain.Project;

@Component("projectService")
@Transactional
public class ProjectService {

	private final ProjectRepository repository;

	@Autowired
	public ProjectService(ProjectRepository repository) {
		this.repository = repository;
	}

	public boolean save(Project project) {
		repository.save(project);
		return true;
	}
	
	public List<Project> getAll() {
		List<Project> projects = repository.findAll();
		Collections.sort(projects);
		return projects;
	}
	
	public Project findOne(long id) {
		return repository.findOne(id);
	}

}
