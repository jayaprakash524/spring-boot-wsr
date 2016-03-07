<%@ include file="header.jsp"%>
<body>
	<div class="container">

		<div class="page-header">
			<h1>
				View or Export <small></small>
			</h1>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<form role="form" id="WSRForm" method="post">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="project">Pick a Date:</label>
								<div class="input-group">
									<input type="text" name="selectedDate" id="selectedDate"
										class="form-control" readonly value="${selectedDate}">
									<span class="input-group-addon btn selectedDateIcon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
							</div>
						</div>
						<div class="col-sm-1">
							<div class="form-group">
								<div class="input-group">
									<input type="submit" name="submit" id="go" value="Go"
										class="btn btn-info pull-right buttons">
								</div>
							</div>
						</div>

						<div class="col-sm-1">
							<div class="form-group">
								<div class="input-group">
									<input type="submit" name="export" id="export"
										value="Export to Excel"
										class="btn btn-info pull-right buttons">
								</div>
							</div>
						</div>
					</form>
				</div>
				<c:if test="${message!=null}">
					<h3 class="col-sm-12 text-center">${message }</h3>
				</c:if>
				<c:if test="${reportList!=null}">
					<div class="col-sm-3">
						<c:choose>
							<c:when test="${projectList.size()==0}">
								<h4 class="green">All projects recorded their report this
									week!</h4>
							</c:when>
							<c:otherwise>
								<h4 class="orange">Projects yet to submit this week's
									report:</h4>
								<ol>
									<c:forEach var="project" items="${projectList}">
										<li>${project.getName()}</li>
									</c:forEach>
								</ol>
							</c:otherwise>
						</c:choose>
					</div>
					<c:if test="${ reportList.size()!=0 }">
						<div class="col-sm-9">
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="text-center">Project</th>
										<th class="text-center">Current Week Accomplishments</th>
										<th class="text-center">Plan for Next Week</th>
										<th class="text-center">Health</th>
										<th class="text-center">Edit</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="report" items="${reportList}">
										<tr>
											<td class="text-center">${report.getProject().getName() }</td>
											<td class="justify">${report.getCurrentWeekAccomplishments() }</td>
											<td class="justify">${report.getPlanForNextWeek() }</td>
											<td class="healthIcon text-center"><c:choose>
													<c:when test="${report.getHealth() =='Green'}">
														<span class="glyphicon glyphicon-thumbs-up green"
															title="Fine!"></span>
													</c:when>
													<c:when test="${report.getHealth() =='Yellow'}">
														<span class="glyphicon glyphicon-alert yellow"
															title="Needs Assistance!"></span>
													</c:when>
													<c:otherwise>
														<span class="glyphicon glyphicon-thumbs-down red"
															title="Critical!"></span>
													</c:otherwise>
												</c:choose></td>
											<td class="text-center"><a
												href="editWSR?project=${report.getProject().getId()}&selectedDate=${selectedDate}"><span
													class="glyphicon glyphicon-pencil"></span></a></td>

										</tr>
									</c:forEach>



								</tbody>
							</table>
						</div>
					</c:if>
				</c:if>


			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>