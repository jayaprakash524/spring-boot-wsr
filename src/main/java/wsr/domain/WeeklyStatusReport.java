package wsr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WeeklyStatusReport implements Comparable<WeeklyStatusReport>{

	
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Project project;

	@Column(nullable = false, length=2000)
	private String currentWeekAccomplishments;

	@Column(nullable = false, length=2000)
	private String planForNextWeek;

	@Column(nullable = true)
	private String devPlannedStartDate;
	@Column(nullable = true)
	private String devPlannedEndDate;
	@Column(nullable = true)
	private String devActualStartDate;
	@Column(nullable = true)
	private String devActualEndDate;
	@Column(nullable = true)
	private String qaPlannedStartDate;
	@Column(nullable = true)
	private String qaPlannedEndDate;
	@Column(nullable = true)
	private String qaActualStartDate;
	@Column(nullable = true)
	private String qaActualEndDate;

	public void setDevPlannedStartDate(String devPlannedStartDate) {
		this.devPlannedStartDate = devPlannedStartDate;
	}

	public void setDevPlannedEndDate(String devPlannedEndDate) {
		this.devPlannedEndDate = devPlannedEndDate;
	}

	public void setDevActualStartDate(String devActualStartDate) {
		this.devActualStartDate = devActualStartDate;
	}

	public void setDevActualEndDate(String devActualEndDate) {
		this.devActualEndDate = devActualEndDate;
	}

	public void setQaPlannedStartDate(String qaPlannedStartDate) {
		this.qaPlannedStartDate = qaPlannedStartDate;
	}

	public void setQaPlannedEndDate(String qaPlannedEndDate) {
		this.qaPlannedEndDate = qaPlannedEndDate;
	}

	public void setQaActualStartDate(String qaActualStartDate) {
		this.qaActualStartDate = qaActualStartDate;
	}

	public void setQaActualEndDate(String qaActualEndDate) {
		this.qaActualEndDate = qaActualEndDate;
	}


	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	private int week;
	private int year;

	private Date modifiedDate = new Date();
	@Column(nullable = false)
	private String health;

	@Column(nullable = true, length=2000)
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getCurrentWeekAccomplishments() {
		return currentWeekAccomplishments;
	}

	public void setCurrentWeekAccomplishments(String currentWeekAccomplishments) {
		this.currentWeekAccomplishments = currentWeekAccomplishments;
	}


	public String getDevPlannedStartDate() {
		return devPlannedStartDate;
	}

	public String getDevPlannedEndDate() {
		return devPlannedEndDate;
	}

	public String getDevActualStartDate() {
		return devActualStartDate;
	}

	public String getDevActualEndDate() {
		return devActualEndDate;
	}

	public String getQaPlannedStartDate() {
		return qaPlannedStartDate;
	}

	public String getQaPlannedEndDate() {
		return qaPlannedEndDate;
	}

	public String getQaActualStartDate() {
		return qaActualStartDate;
	}

	public String getQaActualEndDate() {
		return qaActualEndDate;
	}

	public String getPlanForNextWeek() {
		return planForNextWeek;
	}

	public void setPlanForNextWeek(String planForNextWeek) {
		this.planForNextWeek = planForNextWeek;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int compareTo(WeeklyStatusReport o) {
		return this.project.getId().compareTo(o.project.getId());
	}

}
