/**
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.arquillian.ajocado.testng.ftest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
@RunWith(Arquillian.class)
public class RestartBrowserTestCase extends SampleApplication {

    private static final String JSESSIONID = "JSESSIONID";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return createDeploymentForClass(RestartBrowserTestCase.class);
    }

    @Test
    public void testJSessionIdChange() {
        openContext();

        if (!selenium.isCookiePresent(JSESSIONID)) {
            Assert.fail("Cookie " + JSESSIONID + " is not present");
        }

        String jSessionId1 = selenium.getCookieByName(JSESSIONID).getValue();

        selenium.restartBrowser();

        openContext();

        if (!selenium.isCookiePresent(JSESSIONID)) {
            Assert.fail("Cookie " + JSESSIONID + " is not present");
        }

        String jSessionId2 = selenium.getCookieByName(JSESSIONID).getValue();

        Assert.assertFalse(jSessionId1.equals(jSessionId2));
    }
}
