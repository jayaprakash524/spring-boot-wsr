package wsr.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wsr.domain.WeeklyStatusReport;

@Component("reportService")
@Transactional
public class ReportService {

	private final ReportRepository repository;
	private final ProjectRepository projectRepository;
	
	@Autowired  
    private SessionFactory sessionFactory;
	

	@Autowired
	public ReportService(ReportRepository repository,ProjectRepository projectRepository) {
		this.repository = repository;
		this.projectRepository=projectRepository;
	}

	public boolean save(WeeklyStatusReport report) {
		repository.save(report);
		return true;
	}
	
	public Iterable<WeeklyStatusReport> getAll() {
		return repository.findAll();
	}
	
	public List<WeeklyStatusReport> getAllByCriteria(int year, int week) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(WeeklyStatusReport.class);
		cr.add(Restrictions.eq("week", week));
		cr.add(Restrictions.eq("year", year));
		return cr.list();
	}
	
	
	public WeeklyStatusReport getReportByCriteria(int year, int week, Long projectId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(WeeklyStatusReport.class);
		cr.add(Restrictions.eq("week", week));
		cr.add(Restrictions.eq("year", year));
		cr.add(Restrictions.eq("project", projectRepository.findOne(projectId)));
		
		List<WeeklyStatusReport> reportList  = cr.list();
		
		if(reportList!=null && reportList.size()>0){
			return reportList.get(0);
		}
		else{
			return null;
		}
		
	}
	
	/*public WeeklyStatusReport getById(Long id) {
		return repository.findOne(id);
	}*/

}
