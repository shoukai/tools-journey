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

import java.util.concurrent.Future;

import org.junit.Test;

import rx.Observable;
import rx.Observer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        System.out.println(name + " already run ");
        return "Hello " + name + "!";
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() {
            assertEquals("Hello World!", new CommandHelloWorld("World").execute());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
        }

        @Test
        public void testAsynchronous1() throws Exception {
            assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
        }

        @Test
        public void testAsynchronous2() throws Exception {

            Future<String> fWorld = new CommandHelloWorld("World").queue();
            Future<String> fBob = new CommandHelloWorld("Bob").queue();

            assertEquals("Hello World!", fWorld.get());
            assertEquals("Hello Bob!", fBob.get());
        }

        @Test
        public void testObservable() {
            Observable<String> fWorld = new CommandHelloWorld("World").observe();
            Observable<String> fBob = new CommandHelloWorld("Bob").observe();

            System.out.println("do toObservable ");

            fBob.subscribe(v -> System.out.println("onNext: " + v));
            fWorld.subscribe(v -> System.out.println("onNext: " + v));
        }
    }
}
