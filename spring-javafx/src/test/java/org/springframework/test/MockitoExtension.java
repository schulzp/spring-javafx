/*
 * Copyright 2015 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.springframework.test;

import org.junit.gen5.api.extension.InstancePostProcessor;
import org.junit.gen5.api.extension.TestExecutionContext;
import org.mockito.MockitoAnnotations;

public class MockitoExtension implements InstancePostProcessor {

    @Override
    public void postProcessTestInstance(TestExecutionContext testExecutionContext, Object testInstance) throws Exception {
        MockitoAnnotations.initMocks(testInstance);
    }

}