/*
 * Copyright 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.logging.logback;

import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;

/**
 * {@link ThrowableProxyConverter} that adds some additional whitespace around the stack
 * trace.
 *
 * @author Phillip Webb
 * @since 1.0.0
 */
public class WhitespaceThrowableProxyConverter extends ThrowableProxyConverter {

	@Override
	protected String throwableProxyToString(IThrowableProxy tp) {
		// Cache separator and the super result to avoid repeated accesses/concatenation allocations.
		String sep = CoreConstants.LINE_SEPARATOR;
		String s = super.throwableProxyToString(tp);
		// If s is null, concatenation would produce "null", which has length 4.
		int sLen = (s == null) ? 4 : s.length();
		StringBuilder sb = new StringBuilder(sep.length() * 2 + sLen);
		sb.append(sep).append(s).append(sep);
		return sb.toString();
	}

}
