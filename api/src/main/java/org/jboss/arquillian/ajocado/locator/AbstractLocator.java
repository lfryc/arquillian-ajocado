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
package org.jboss.arquillian.ajocado.locator;

import org.jboss.arquillian.ajocado.format.SimplifiedFormat;

/**
 * <p>
 * Abstract implementation of locator.
 * </p>
 * 
 * <p>
 * Able to return the locator as string for use in Selenium {@link #inSeleniumRepresentation()}
 * </p>
 * 
 * @param <T>
 *            the type of locator which can be derived from this locator
 * 
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public abstract class AbstractLocator<T extends Locator<T>> implements Locator<T> {

    private String locator;

    /**
     * The locator constructor
     * 
     * @param locator
     *            the string representation of locator (without the locator type prefix)
     */
    public AbstractLocator(String locator) {
        if (locator == null) {
            throw new IllegalArgumentException("locator can't be null");
        }
        this.locator = locator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.ajocado.locator.Locator#getRawLocator()
     */
    public String getRawLocator() {
        return locator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.ajocado.locator.Locator#format(java.lang.Object[])
     */
    @SuppressWarnings("unchecked")
    public Locator<T> format(Object... args) {
        String newLocator = SimplifiedFormat.format(locator, args);
        try {
            return (T) this.getClass().getConstructor(String.class).newInstance(newLocator);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.ajocado.selenium.SeleniumRepresentable#inSeleniumRepresentation()
     */
    public String inSeleniumRepresentation() {
        final LocationStrategy locationStrategy = getLocationStrategy();

        return SimplifiedFormat.format("{0}={1}", locationStrategy.getStrategyName(), getRawLocator());
    }

    @Override
    public String toString() {
        return inSeleniumRepresentation();
    }
}
