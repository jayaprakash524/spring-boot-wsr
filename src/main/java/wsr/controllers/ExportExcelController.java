package wsr.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wsr.domain.WeeklyStatusReport;
import wsr.services.ReportService;

@Controller
public class ExportExcelController {
	 private final Logger log = LoggerFactory.getLogger(this.getClass());
	static final String NA = "N.A";
	static final String EMPTY_STRING = "";
	
	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
	public void downloadExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("selectedDate") String selectedDate,Map<String, Object> model) {

		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date startDate;
		List<WeeklyStatusReport> reportList = null;

		try {
			startDate = df.parse(selectedDate);
			final Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			final int week = cal.get(Calendar.WEEK_OF_YEAR);
			final int year = cal.get(Calendar.YEAR);
			reportList = reportService.getAllByCriteria(year, week);
			if (reportList != null && reportList.size() != 0) {
				Collections.sort(reportList);

				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("CAS_WSR");
				sheet.setColumnWidth(3, 9000);
				sheet.setColumnWidth(4, 9000);
				sheet.setColumnWidth(15, 9000);
				sheet.setColumnWidth(16, 9000);
				int rowNum = 0, cellNum = 0;

				// Style the cell with borders all around.
				CellStyle style = workbook.createCellStyle();
				style.setBorderBottom(CellStyle.BORDER_MEDIUM);
				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderLeft(CellStyle.BORDER_MEDIUM);
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderRight(CellStyle.BORDER_MEDIUM);
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderTop(CellStyle.BORDER_MEDIUM);
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				style.setAlignment(CellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
						.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);

				rowNum = 2;
				cellNum = 5;
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,
						cellNum, cellNum + 3));
				// Create a new row in current sheet
				Row row = sheet.createRow(rowNum);

				// Create a new cell in current row
				Cell cell = row.createCell(cellNum++);

				// Set value to new value
				cell.setCellValue("Dev");
				cell.setCellStyle(style);

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);

				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,
						cellNum, cellNum + 3));
				cell = row.createCell(cellNum++);
				// Set value to new value
				cell.setCellValue("QA");
				cell.setCellStyle(style);

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);

				row = sheet.createRow(++rowNum);
				cellNum = 5;
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,
						cellNum, cellNum + 1));
				cell = row.createCell(cellNum++);
				// Set value to new value
				cell.setCellValue("Planned");
				cell.setCellStyle(style);

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);

				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,
						cellNum, cellNum + 1));
				cell = row.createCell(cellNum++);
				// Set value to new value
				cell.setCellValue("Actual");
				cell.setCellStyle(style);

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);

				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,
						cellNum, cellNum + 1));
				cell = row.createCell(cellNum++);
				// Set value to new value
				cell.setCellValue("Planned");
				cell.setCellStyle(style);

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);

				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,
						cellNum, cellNum + 1));
				cell = row.createCell(cellNum++);
				// Set value to new value
				cell.setCellValue("Actual");
				cell.setCellStyle(style);

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);

				row = sheet.createRow(++rowNum);
				cellNum = 1;
				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("SNo");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Project Name");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Current week accomplishments");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Plan for next week");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Start date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("End date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Start date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("End date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Start date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("End date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Start date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("End date");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Remarks");

				cell = row.createCell(cellNum++);
				cell.setCellStyle(style);
				cell.setCellValue("Health");

				style = workbook.createCellStyle();
				style.setBorderBottom(CellStyle.BORDER_MEDIUM);
				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderLeft(CellStyle.BORDER_MEDIUM);
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderRight(CellStyle.BORDER_MEDIUM);
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());
				style.setBorderTop(CellStyle.BORDER_MEDIUM);
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				style.setAlignment(CellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				style.setWrapText(true);

				Font font = workbook.createFont();
				font.setColor(HSSFColor.WHITE.index);

				CellStyle green = workbook.createCellStyle();
				green.setBorderBottom(CellStyle.BORDER_MEDIUM);
				green.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				green.setBorderLeft(CellStyle.BORDER_MEDIUM);
				green.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				green.setBorderRight(CellStyle.BORDER_MEDIUM);
				green.setRightBorderColor(IndexedColors.BLACK.getIndex());
				green.setBorderTop(CellStyle.BORDER_MEDIUM);
				green.setTopBorderColor(IndexedColors.BLACK.getIndex());
				green.setAlignment(CellStyle.ALIGN_CENTER);
				green.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				green.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				green.setFillPattern(CellStyle.SOLID_FOREGROUND);
				green.setFont(font);

				CellStyle yellow = workbook.createCellStyle();
				yellow.setBorderBottom(CellStyle.BORDER_MEDIUM);
				yellow.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				yellow.setBorderLeft(CellStyle.BORDER_MEDIUM);
				yellow.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				yellow.setBorderRight(CellStyle.BORDER_MEDIUM);
				yellow.setRightBorderColor(IndexedColors.BLACK.getIndex());
				yellow.setBorderTop(CellStyle.BORDER_MEDIUM);
				yellow.setTopBorderColor(IndexedColors.BLACK.getIndex());
				yellow.setAlignment(CellStyle.ALIGN_CENTER);
				yellow.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				yellow.setFillForegroundColor(IndexedColors.GOLD.getIndex());
				yellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
				yellow.setFont(font);

				CellStyle red = workbook.createCellStyle();
				red.setBorderBottom(CellStyle.BORDER_MEDIUM);
				red.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				red.setBorderLeft(CellStyle.BORDER_MEDIUM);
				red.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				red.setBorderRight(CellStyle.BORDER_MEDIUM);
				red.setRightBorderColor(IndexedColors.BLACK.getIndex());
				red.setBorderTop(CellStyle.BORDER_MEDIUM);
				red.setTopBorderColor(IndexedColors.BLACK.getIndex());
				red.setAlignment(CellStyle.ALIGN_CENTER);
				red.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				red.setFillForegroundColor(IndexedColors.RED.getIndex());
				red.setFillPattern(CellStyle.SOLID_FOREGROUND);
				red.setFont(font);

				int sNo = 1;
				for (WeeklyStatusReport report : reportList) {
					row = sheet.createRow(++rowNum);
					cellNum = 1;
					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(sNo++);

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(report.getProject().getName());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(report.getCurrentWeekAccomplishments());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(report.getPlanForNextWeek());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getDevPlannedStartDate())?NA:report.getDevPlannedStartDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getDevPlannedEndDate())?NA:report.getDevPlannedEndDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getDevActualStartDate())?NA:report.getDevActualStartDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getDevActualEndDate())?NA:report.getDevActualEndDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getQaPlannedStartDate())?NA:report.getQaPlannedStartDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getQaPlannedEndDate())?NA:report.getQaPlannedEndDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getQaActualStartDate())?NA:report.getQaActualStartDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(EMPTY_STRING.equals(report.getQaActualEndDate())?NA:report.getQaActualEndDate());

					cell = row.createCell(cellNum++);
					cell.setCellStyle(style);
					cell.setCellValue(report.getRemarks());

					cell = row.createCell(cellNum++);
					if (report.getHealth().equals("Green")) {
						cell.setCellStyle(green);
					} else if (report.getHealth().equals("Yellow")) {
						cell.setCellStyle(yellow);
					} else {
						cell.setCellStyle(red);
					}
					cell.setCellValue(report.getHealth());

					if (report.getPlanForNextWeek().length() >= 40
							|| report.getCurrentWeekAccomplishments().length() >= 40) {
						if (report.getCurrentWeekAccomplishments().length() > report
								.getPlanForNextWeek().length()) {
							row.setHeightInPoints(sheet
									.getDefaultRowHeightInPoints()
									* report.getCurrentWeekAccomplishments()
											.length() / 30);
						} else {
							row.setHeightInPoints(sheet
									.getDefaultRowHeightInPoints()
									* report.getPlanForNextWeek().length() / 30);
						}
					} else {
						row.setHeightInPoints(sheet
								.getDefaultRowHeightInPoints() * 4);
					}

					// row.setHeight((short)10);
				}

				for (int i = 0; i < 17; i++) {
					if (i != 3 && i != 4 &&  i != 13 && i != 14) {
						sheet.autoSizeColumn(i);
					}
					else if(i == 13){
						sheet.setColumnWidth(i, 8400);
					}
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=CAS_WSR_" + selectedDate + ".xls");

				workbook.write(response.getOutputStream());
//				response.getOutputStream().close();
			}
			else{
				model.put("message", "Nothing found!");
				response.sendRedirect("./viewOrExportWSR?message=Nothing To Export");
			}
			
		}
		catch(Exception e){
			log.error("Exception while exporting WSR",e);
		}

	}

}
