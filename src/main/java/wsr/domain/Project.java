package wsr.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Project implements Comparable<Project>{
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WeeklyStatusReport> getReports() {
		return reports;
	}

	public void setReports(List<WeeklyStatusReport> reports) {
		this.reports = reports;
	}

	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<WeeklyStatusReport> reports;


	@Override
	public int compareTo(Project o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.id);
	}
	
	@Override
	public boolean equals(Object o){
		if(this.id == ((Project)o).id){
			return true;
		}
		else{
			return false;
		}
				
	}

}
