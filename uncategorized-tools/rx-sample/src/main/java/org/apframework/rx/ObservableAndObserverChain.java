package org.apframework.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableAndObserverChain {

    public static void main(String[] args) {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }).subscribe(new Observer<Integer>() {
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
        });
    }
}
