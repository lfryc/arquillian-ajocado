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
package org.jboss.arquillian.ajocado.waiting.ajax;

import org.jboss.arquillian.ajocado.javascript.JavaScript;
import org.jboss.arquillian.ajocado.waiting.conversion.Convertor;
import org.jboss.arquillian.ajocado.waiting.retrievers.RetrievedValueHolder;

/**
 * JavaScript code obtaining the value convertable to <tt>T</tt> type.
 * 
 * @param <T>
 *            object, which should be from string representation inside JavaScript converted to object representation
 * @author <a href="mailto:lfryc@redhat.com">Lukas Fryc</a>
 * @version $Revision$
 */
public interface JavaScriptRetriever<T> extends RetrievedValueHolder<T> {

    /**
     * Gets the code for retrieving value on JavaScript side.
     * 
     * @return the JavaScript
     */
    JavaScript getJavaScriptRetrieve();

    /**
     * Gets the convertor for converting JavaScript representation to Object representation and vice versa.
     * 
     * @return the convertor for converting JavaScript representation to Object representation and vice versa.
     */
    Convertor<T, String> getConvertor();
}
