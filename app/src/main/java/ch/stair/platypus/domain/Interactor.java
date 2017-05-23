package ch.stair.platypus.domain;

import java.util.concurrent.Executor;

public abstract class Interactor<T, Params> {

    private final Executor executor;

    Interactor(Executor executor) {
        this.executor = executor;
    }

    public void execute(Observer<T> observer, Params params) {
        this.executor.execute(() -> {
            T result = Interactor.this.execute(params);
            observer.onFinished(result);
        });
    }

    protected abstract T execute(Params params);
}
