package ch.stair.platypus.domain;

public abstract class Observer<T> {
    public abstract void onFinished(T t);
}
