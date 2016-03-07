package wsr.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wsr.domain.Project;
import wsr.domain.WeeklyStatusReport;
import wsr.services.ProjectService;
import wsr.services.ReportService;

@Controller
class ReportController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReportService reportService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/saveWSR", method = RequestMethod.POST)
	public String saveWSR(@ModelAttribute WeeklyStatusReport report, Map<String, Object> model) {
		try {
			final Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			final int week = cal.get(Calendar.WEEK_OF_YEAR);
			final int year = cal.get(Calendar.YEAR);

			WeeklyStatusReport existingReport = reportService.getReportByCriteria(year, week, report.getProject()
					.getId());

			String message = null;
			if (existingReport == null) {
				report.setYear(year);
				report.setWeek(week);
				reportService.save(report);
				message = "Record Successfully saved!";
			} else {
				message = "Report Already Submitted";
			}

			model.put("message", message);
			return "success";
		} catch (Exception e) {
			log.error("Exception while saving WSR", e);
			return "error";
		}

	}

	@RequestMapping(value = "/updateWSR", method = RequestMethod.POST)
	public String updateWSR(@ModelAttribute WeeklyStatusReport report, Map<String, Object> model) {
		try {
			reportService.save(report);
			model.put("message", "Successfully Updated!");
		} catch (Exception e) {
			model.put("message", "Error");
			log.error("Exception while updating WSR", e);
		}

		return "success";
	}

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		try {

			final Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			final int week = cal.get(Calendar.WEEK_OF_YEAR);
			final int year = cal.get(Calendar.YEAR);
			
			
			List<Project> remainingProjects = getRemainingProjects(year, week);

			model.put("projects", remainingProjects);
			return "index";
		} catch (Exception e) {
			log.error("Exception while Loading home page", e);
			return "error";
		}

	}

	@RequestMapping(value = "/getWSRs", method = RequestMethod.POST)
	public String getWSRs(Map<String, Object> model, @RequestParam("selectedDate") String selectedDate) {
		try {
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			Date startDate = df.parse(selectedDate);
			final Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			final int week = cal.get(Calendar.WEEK_OF_YEAR);
			final int year = cal.get(Calendar.YEAR);
			List<WeeklyStatusReport> reportList = reportService.getAllByCriteria(year, week);
			Collections.sort(reportList);

			List<Project> remainingProjects = getRemainingProjects(year, week);
			
			int index = 0;
			for(WeeklyStatusReport report : reportList){
				if(report.getCurrentWeekAccomplishments().contains("\n")){
					report.setCurrentWeekAccomplishments(report.getCurrentWeekAccomplishments().replace("\n", "<br />"));
				}
				if(report.getPlanForNextWeek().contains("\n")){
					report.setPlanForNextWeek(report.getPlanForNextWeek().replace("\n", "<br />"));
				}
				if(report.getRemarks().contains("\n")){
					report.setRemarks(report.getRemarks().replace("\n", "<br />"));
				}
				reportList.set(index++, report);
			}
			
			model.put("projectList", remainingProjects);
			model.put("reportList", reportList);
			model.put("selectedDate", selectedDate);
			return "export";

		} catch (Exception e) {
			log.error("Exception while getting WSRs", e);
			return "error";
		}

	}

	@RequestMapping(value = "/editWSR", method = RequestMethod.GET)
	public String editWSR(Map<String, Object> model, @RequestParam("selectedDate") String selectedDate,
			@RequestParam("project") Long projectId) {
		try {
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			Date startDate = df.parse(selectedDate);
			final Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			final int week = cal.get(Calendar.WEEK_OF_YEAR);
			final int year = cal.get(Calendar.YEAR);
			WeeklyStatusReport report = reportService.getReportByCriteria(year, week, projectId);
			model.put("report", report);
			model.put("selectedDate", selectedDate);
			return "editWSR";

		} catch (Exception e) {
			log.error("Exception while getting WSRs", e);
			return "error";
		}

	}

	@RequestMapping(value = "/viewOrExportWSR", method = RequestMethod.GET)
	public String viewOrExport(@RequestParam(value = "message", required = false) String message,
			Map<String, Object> model) {
		try {
			model.put("message", message);
			return "export";
		} catch (Exception e) {
			log.error("Exception while loading view or export page", e);
			return "error";
		}

	}
	
	List<Project> getRemainingProjects(int year, int week){
		List<Project> projectList = projectService.getAll();
		
		List<WeeklyStatusReport> existingReports = reportService.getAllByCriteria(year, week);
		
		for (WeeklyStatusReport report : existingReports) {
			projectList.remove(report.getProject());
		}
		return projectList;
	}
	/*
	 * public WeeklyStatusReport setFormattedDates(WeeklyStatusReport report) {
	 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); try {
	 * report.setDevPlannedStartDateFormatted
	 * (sdf.format(sdf.parse(report.getDevPlannedStartDate())));
	 * report.setDevPlannedEndDateFormatted
	 * (sdf.format(sdf.parse(report.getDevPlannedEndDate())));
	 * report.setQaPlannedStartDateFormatted
	 * (sdf.format(sdf.parse(report.getQaPlannedStartDate())));
	 * report.setQaPlannedEndDateFormatted
	 * (sdf.format(sdf.parse(report.getQaPlannedStartDate())));
	 * report.setDevActualStartDateFormatted
	 * (sdf.format(sdf.parse(report.getDevActualStartDate())));
	 * report.setDevActualEndDateFormatted
	 * (sdf.format(sdf.parse(report.getDevActualEndDate())));
	 * report.setQaActualStartDateFormatted
	 * (sdf.format(sdf.parse(report.getQaActualStartDate())));
	 * report.setQaActualEndDateFormatted
	 * (sdf.format(sdf.parse(report.getQaActualStartDate()))); } catch
	 * (ParseException e) { e.printStackTrace(); }
	 * 
	 * return report; }
	 */

}
