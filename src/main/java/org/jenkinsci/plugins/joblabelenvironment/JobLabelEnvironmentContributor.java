package org.jenkinsci.plugins.joblabelenvironment;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.EnvironmentContributor;
import hudson.model.Job;
import hudson.model.TaskListener;

import java.util.logging.Logger;

@Extension
public class JobLabelEnvironmentContributor extends EnvironmentContributor {
	@Override
	public void buildEnvironmentFor(Job job, EnvVars env, TaskListener listener) {
		if (!(job instanceof AbstractProject)) {
			LOGGER.finer(String.format("Skipping JOB_LABELS for %s because it's the wrong type", job.getFullDisplayName()));
			return;
		}
		AbstractProject project = (AbstractProject)job;
		String labelString = project.getAssignedLabelString();
		if (labelString == null) {
			LOGGER.finer(String.format("No label specified for job %s", project.getFullDisplayName()));
			return;
		}
		env.put("JOB_LABELS", labelString);
		LOGGER.fine(String.format("JOB_LABELS for %s are %s", project.getFullDisplayName(), labelString));
	}

	private static final Logger LOGGER = Logger.getLogger(JobLabelEnvironmentContributor.class.getName());
}