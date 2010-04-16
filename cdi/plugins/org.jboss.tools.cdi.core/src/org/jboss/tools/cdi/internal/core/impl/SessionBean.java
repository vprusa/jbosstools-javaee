/******************************************************************************* 
 * Copyright (c) 2009 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.cdi.internal.core.impl;

import org.jboss.tools.cdi.core.IAnnotationDeclaration;
import org.jboss.tools.cdi.core.ISessionBean;

public class SessionBean extends ClassBean implements ISessionBean {

	/*
	 * (non-Javadoc)
	 * @see org.jboss.tools.cdi.core.ISessionBean#getStatefulDeclaration()
	 */
	public IAnnotationDeclaration getStatefulDeclaration() {
		return getDefinition().getStatefulAnnotation();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jboss.tools.cdi.core.ISessionBean#getStatelessDeclaration()
	 */
	public IAnnotationDeclaration getStatelessDeclaration() {
		return getDefinition().getStatelessAnnotation();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jboss.tools.cdi.core.ISessionBean#isStateful()
	 */
	public boolean isStateful() {
		return getDefinition().getStatefulAnnotation() != null;
	}
}