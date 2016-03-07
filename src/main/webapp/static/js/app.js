/**
 * 
 */

$(document).ready(function() {
	
	$('#currentWeek').click(function(){
		if($(this).is(':checked')){
			$('#currentWeekAccomplishments').val('N.A');	
		}
		else{
			$('#currentWeekAccomplishments').val('');
		}
		
	});
	
	$('#nextWeek').click(function(){
		if($(this).is(':checked')){
			$('#planForNextWeek').val('N.A');	
		}
		else{
			$('#planForNextWeek').val('');
		}
		
	});
	
	$('#devPlannedStartDate').datepicker({
		format : "dd-M-yyyy",
		clearBtn : true,
		calendarWeeks : true,
		autoclose : true,
		todayHighlight : true,
	}).on('changeDate', function(selected) {
		$('#devPlannedEndDate').val('');
		var startDate = new Date(selected.date.valueOf());
		$('#devPlannedEndDate').datepicker({
			format : "dd-M-yyyy",
			clearBtn : true,
			calendarWeeks : true,
			autoclose : true,
			todayHighlight : true,
			startDate : startDate
		});
		$('#devPlannedEndDate').datepicker('setStartDate', startDate);
	}).on('clearDate', function(selected) {
		$('#devPlannedEndDate').val('');
	});

	$('#devActualStartDate').datepicker({
		format : "dd-M-yyyy",
		clearBtn : true,
		calendarWeeks : true,
		autoclose : true,
		todayHighlight : true,
		endDate : new Date()
	}).on('changeDate', function(selected) {
		$('#devActualEndDate').val('');
		var startDate = new Date(selected.date.valueOf());
		$('#devActualEndDate').datepicker({
			format : "dd-M-yyyy",
			clearBtn : true,
			calendarWeeks : true,
			autoclose : true,
			todayHighlight : true,
			startDate : startDate,
			endDate : new Date()
		});
		$('#devActualEndDate').datepicker('setStartDate', startDate);
	}).on('clearDate', function(selected) {
		$('#devActualEndDate').val('');
	});

	$('#qaPlannedStartDate').datepicker({
		format : "dd-M-yyyy",
		clearBtn : true,
		calendarWeeks : true,
		autoclose : true,
		todayHighlight : true
	}).on('changeDate', function(selected) {
		$('#qaPlannedEndDate').val('');
		var startDate = new Date(selected.date.valueOf());
		$('#qaPlannedEndDate').datepicker({
			format : "dd-M-yyyy",
			clearBtn : true,
			calendarWeeks : true,
			autoclose : true,
			todayHighlight : true,
			startDate : startDate
		});
		$('#qaPlannedEndDate').datepicker('setStartDate', startDate);
	}).on('clearDate', function(selected) {
		$('#qaPlannedEndDate').val('');
	});

	$('#qaActualStartDate').datepicker({
		format : "dd-M-yyyy",
		clearBtn : true,
		calendarWeeks : true,
		autoclose : true,
		todayHighlight : true,
		endDate : new Date()
	}).on('changeDate', function(selected) {
		$('#qaActualEndDate').val('');
		var startDate = new Date(selected.date.valueOf());
		$('#qaActualEndDate').datepicker({
			format : "dd-M-yyyy",
			clearBtn : true,
			calendarWeeks : true,
			autoclose : true,
			todayHighlight : true,
			startDate : startDate,
			endDate : new Date()
		});
		$('#qaActualEndDate').datepicker('setStartDate', startDate);
	}).on('clearDate', function(selected) {
		$('#qaActualEndDate').val('');
	});

	$('#selectedDate').datepicker({
		format : "dd-M-yyyy",
		clearBtn : true,
		calendarWeeks : true,
		autoclose : true,
		todayHighlight : true,
		orientation : "top left",
		beforeShowDay : function(d) {
			return d.getDay() == 5;
		}
	});
	
	$('.selectedDateIcon').click(function(){
		$(this).parent().find('#selectedDate').datepicker('show');
	});

	$('#go').click(function(e) {
		if(validateExportForm())
		$('#WSRForm').attr('action', './getWSRs');
		else
			e.preventDefault();
	});

	$('#export').click(function(e) {
		if(validateExportForm()){
			$('h3.col-sm-12').fadeOut('slow');
			$('#WSRForm').attr('action', './downloadExcel');
		}
		
		else
			e.preventDefault();
	});
	
	$('#submit').click(function(e){
		$('input, select, textarea').removeClass('danger');
		
		if($('#project').val()==''){
			$('#project').addClass('danger');
			e.preventDefault();
		}
	/*	if($('#devPlannedStartDate').val()!='' && $('#devPlannedEndDate').val()==''){
			$('#devPlannedEndDate').addClass('danger');
			e.preventDefault();
		}
		if($('#devActualStartDate').val()!='' && $('#devActualEndDate').val()==''){
			$('#devActualEndDate').addClass('danger');
			e.preventDefault();
		}
		if($('#qaActualStartDate').val()!='' && $('#qaActualEndDate').val()==''){
			$('#qaActualEndDate').addClass('danger');
			e.preventDefault();
		}
		if($('#qaActualStartDate').val()!='' && $('#qaActualEndDate').val()==''){
			$('#qaActualEndDate').addClass('danger');
			e.preventDefault();
		}*/
		if($('#currentWeekAccomplishments').val()==''){
			$('#currentWeekAccomplishments').addClass('danger');
		}
		if($('#planForNextWeek').val()==''){
			$('#planForNextWeek').addClass('danger');
		}
		if($('#health').val()==''){
			$('#health').addClass('danger');
			e.preventDefault();
		}
	});

});

function validateExportForm(){
	var selectedDate = $('#selectedDate').val();
	if(selectedDate==''){
		$('#selectedDate').addClass('danger');
		return false;
	}
	return true;
}
