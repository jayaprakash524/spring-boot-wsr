<%@ include file="header.jsp" %>
<body>
	<div class="container">

		<div class="page-header">
			<h1>
				Week Status Report <small></small>
			</h1>
			<div class="well well-sm">
				<strong><span class="glyphicon glyphicon-asterisk"></span>Required
					Field</strong>
			</div>
		</div>

		<!-- Registration form - START -->
		<div class="container">
			<div class="row">
				<form role="form" action="./updateWSR" method="post">
					<div class="col-sm-6">
						<div class="form-group">
							<label for="project">Project Name</label>
							<div class="input-group">${report.getProject().getName()}</div>
							<input type="hidden" value="${report.getId()}" name="id" /> <input
								type="hidden" value="${report.getYear()}" name="year" /> <input
								type="hidden" value="${report.getWeek()}" name="week" /> <input
								type="hidden" value="${report.getProject().getId()}"
								name="project" />

						</div>
						<div class="form-group">
							<label for="currentWeekAccomplishments">Current week
								accomplishments</label>
							<div class="input-group">
								<textarea class="form-control" id="currentWeekAccomplishments"
									name="currentWeekAccomplishments"
									placeholder="Enter currentWeekAccomplishments" rows="5"
									required>${report.getCurrentWeekAccomplishments()}</textarea>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="planForNextWeek">Plan for next week</label>
							<div class="input-group">
								<textarea class="form-control" id="planForNextWeek"
									name="planForNextWeek" placeholder="Enter planForNextWeek"
									required rows="5">${report.getPlanForNextWeek()}</textarea>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="health">Health</label>
							<div class="input-group">
								<select name="health" id="health" class="form-control">
									<option value="Green">Green</option>
									<option value="Yellow">Yellow</option>
									<option value="Red">Red</option>
								</select> <span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="remarks">Remarks</label>
							<div class="input-group">
								<textarea name="remarks" id="remarks" class="form-control"
									rows="5" placeholder="Enter Remarks" >${report.getRemarks()}</textarea>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>

					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label for="devPlannedStartDate">Dev - Planned Start Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="devPlannedStartDate"
									name="devPlannedStartDate"
									placeholder="Enter devPlannedStartDate"
									value="${report.getDevPlannedStartDate()}" required readonly>
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="devPlannedEndDate">Dev - Planned End Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="devPlannedEndDate"
									name="devPlannedEndDate" placeholder="Enter devPlannedEndDate "
									required readonly value="${report.getDevPlannedEndDate()}">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="devActualStartDate">Dev - Actual Start Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="devActualStartDate"
									name="devActualStartDate"
									placeholder="Enter devActualStartDate " required readonly
									value="${report.getDevActualStartDate()}"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="devActualEndDate">Dev - Actual End Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="devActualEndDate"
									name="devActualEndDate" placeholder="Enter devActualEndDate "
									required readonly value="${report.getDevActualEndDate()}">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>

						<div class="form-group">
							<label for="qaPlannedStartDate">QA - Planned Start Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="qaPlannedStartDate"
									name="qaPlannedStartDate"
									placeholder="Enter qaPlannedStartDate " required readonly
									value="${report.getQaPlannedStartDate()}"> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="qaPlannedEndDate">QA - Planned End Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="qaPlannedEndDate"
									name="qaPlannedEndDate" placeholder="Enter qaPlannedEndDate "
									required readonly value="${report.getQaPlannedEndDate()}">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="qaActualStartDate">QA - Actual Start Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="qaActualStartDate"
									name="qaActualStartDate" placeholder="Enter qaActualStartDate "
									required readonly value="${report.getQaActualStartDate()}">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="qaActualEndDate">QA - Actual End Date</label>
							<div class="input-group">
								<input type="text" class="form-control" id="qaActualEndDate"
									name="qaActualEndDate" placeholder="Enter qaActualEndDate "
									required readonly value="${report.getQaActualEndDate()}">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-asterisk"></span></span>
							</div>
						</div>

					</div>
					<div class="col-sm-12">
						<input type="submit" name="submit" id="submit" value="Submit"
							class="btn btn-info center-block">
					</div>
				</form>

			</div>
		</div>
		<!-- Registration form - END -->

	</div>
	<script>
		$('#health').val("${report.getHealth()}");
	</script>
	
	<%@ include file="footer.jsp" %>
</body>
</html>