package com.river.ms.business.entity;

import java.util.List;

public class ProjectStageMessage {

	private List<ProjectSetup> projectSetups;

	private List<ProjectFeasibility> projectFeasibilitys;

	private List<ProjectDecision> projectDecisions;

	private List<ProjectModification> projectModifications;

	private List<ProjectEvaluate> projectEvaluates;

	public List<ProjectSetup> getProjectSetups() {
		return projectSetups;
	}

	public void setProjectSetups(List<ProjectSetup> projectSetups) {
		this.projectSetups = projectSetups;
	}

	public List<ProjectFeasibility> getProjectFeasibilitys() {
		return projectFeasibilitys;
	}

	public void setProjectFeasibilitys(List<ProjectFeasibility> projectFeasibilitys) {
		this.projectFeasibilitys = projectFeasibilitys;
	}

	public List<ProjectDecision> getProjectDecisions() {
		return projectDecisions;
	}

	public void setProjectDecisions(List<ProjectDecision> projectDecisions) {
		this.projectDecisions = projectDecisions;
	}

	public List<ProjectModification> getProjectModifications() {
		return projectModifications;
	}

	public void setProjectModifications(List<ProjectModification> projectModifications) {
		this.projectModifications = projectModifications;
	}

	public List<ProjectEvaluate> getProjectEvaluates() {
		return projectEvaluates;
	}

	public void setProjectEvaluates(List<ProjectEvaluate> projectEvaluates) {
		this.projectEvaluates = projectEvaluates;
	}

	public ProjectStageMessage() {
		super();
	}

	public ProjectStageMessage(List<ProjectSetup> projectSetups, List<ProjectFeasibility> projectFeasibilitys,
			List<ProjectDecision> projectDecisions, List<ProjectModification> projectModifications,
			List<ProjectEvaluate> projectEvaluates) {
		super();
		this.projectSetups = projectSetups;
		this.projectFeasibilitys = projectFeasibilitys;
		this.projectDecisions = projectDecisions;
		this.projectModifications = projectModifications;
		this.projectEvaluates = projectEvaluates;
	}

}
