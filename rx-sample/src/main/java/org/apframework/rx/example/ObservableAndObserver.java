package org.apframework.rx.example;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableAndObserver {
    public static void main(String[] args) throws InterruptedException {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                log.debug("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                log.debug("" + value);
            }

            @Override
            public void onError(Throwable e) {
                log.debug("error");
            }

            @Override
            public void onComplete() {
                log.debug("complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }
}
