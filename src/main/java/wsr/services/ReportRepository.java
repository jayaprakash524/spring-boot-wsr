package wsr.services;

import org.springframework.data.repository.CrudRepository;

import wsr.domain.WeeklyStatusReport;

public interface ReportRepository extends CrudRepository<WeeklyStatusReport, Long> {
	
	WeeklyStatusReport save(WeeklyStatusReport report);
	Iterable<WeeklyStatusReport> findAll();
//	WeeklyStatusReport findOne(long id);

}
