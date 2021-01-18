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

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.junit.Test;
import rx.Observable;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * The obligatory "Hello World!" showing a simple implementation of a {@link HystrixCommand}.
 */
public class CommandHelloObservableWorld extends HystrixObservableCommand<String> {

    private final String name;

    public CommandHelloObservableWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        System.out.println("already run");
        return Observable.just("Hello " + name + "!");
    }

    public static class UnitTest {

        @Test
        public void testObserve() {
            Observable<String> fWorld = new CommandHelloObservableWorld("World").observe();
            Observable<String> fBob = new CommandHelloObservableWorld("Bob").observe();

            System.out.println("do toObservable ");

            fBob.subscribe(v -> System.out.println("onNext: " + v));
            fWorld.subscribe(v -> System.out.println("onNext: " + v));
        }

        @Test
        public void testObservable() {
            Observable<String> fWorld = new CommandHelloObservableWorld("World").toObservable();
            Observable<String> fBob = new CommandHelloObservableWorld("Bob").toObservable();

            System.out.println("do toObservable ");

            fBob.subscribe(v -> System.out.println("onNext: " + v));
            fWorld.subscribe(v -> System.out.println("onNext: " + v));
        }
    }
}
