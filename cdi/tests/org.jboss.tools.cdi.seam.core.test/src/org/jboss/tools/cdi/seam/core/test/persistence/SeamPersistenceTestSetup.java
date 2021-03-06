/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.cdi.seam.core.test.persistence;

import junit.extensions.TestSetup;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.jboss.tools.test.util.JobUtils;
import org.jboss.tools.test.util.ResourcesUtils;

/**
 * @author Alexey Kazakov
 */
public class SeamPersistenceTestSetup extends TestSetup {

	public static final String PLUGIN_ID = "org.jboss.tools.cdi.seam.core.test";
	public static final String PROJECT_NAME = "Seam3PersistenceTest";
	public static final String PROJECT_PATH = "/projects/Seam3PersistenceTest";
	public static final String DEPENDENT_PROJECT_NAME = "Seam3DependentPersistenceTest";
	public static final String DEPENDENT_PROJECT_PATH = "/projects/" + DEPENDENT_PROJECT_NAME;

	protected IProject project;
	protected IProject dependentProject;

	public SeamPersistenceTestSetup(Test test) {
		super(test);
	}

	@Override
	protected void setUp() throws Exception {
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME);
		if(!project.exists()) {
			project = ResourcesUtils.importProject(PLUGIN_ID, PROJECT_PATH);
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
		}
		dependentProject = ResourcesPlugin.getWorkspace().getRoot().getProject(DEPENDENT_PROJECT_NAME);
		if(!dependentProject.exists()) {
			dependentProject = ResourcesUtils.importProject(PLUGIN_ID, DEPENDENT_PROJECT_PATH);
			dependentProject.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
		}
	}

	@Override
	protected void tearDown() throws Exception {
		boolean saveAutoBuild = ResourcesUtils.setBuildAutomatically(false);
		project.delete(true, true, null);
		dependentProject.delete(true, true, null);
		JobUtils.waitForIdle();
		ResourcesUtils.setBuildAutomatically(saveAutoBuild);
	}
}