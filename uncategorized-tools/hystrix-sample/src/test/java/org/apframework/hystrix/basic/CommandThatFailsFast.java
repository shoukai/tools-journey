/**
 * Copyright 2012 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apframework.hystrix.basic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;

/**
 * Sample {@link HystrixCommand} that does not have a fallback implemented
 * so will "fail fast" when failures, rejections, short-circuiting etc occur.
 */
public class CommandThatFailsFast extends HystrixCommand<String> {

    private final boolean throwException;

    public CommandThatFailsFast(boolean throwException) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.throwException = throwException;
    }

    @Override
    protected String run() {
        if (throwException) {
            throw new RuntimeException("failure from CommandThatFailsFast");
        } else {
            return "success";
        }
    }

    protected String getFallback() {
        // 如果返回正常值，后续测试方法会失效
        throw new UnsupportedOperationException("No fallback available.");
    }

    public static class UnitTest {

        @Test
        public void testSuccess() {
            assertEquals("success", new CommandThatFailsFast(false).execute());
        }

        @Test
        public void testFailure() {
            try {
                new CommandThatFailsFast(true).execute();
                fail("we should have thrown an exception");
            } catch (HystrixRuntimeException e) {
                assertEquals("failure from CommandThatFailsFast", e.getCause().getMessage());
                e.printStackTrace();
            }
        }
    }
}
