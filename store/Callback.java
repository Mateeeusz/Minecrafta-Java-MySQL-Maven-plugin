package me.mati.skyblock.store;

public interface Callback<T> {
    void done(T p0);

    void error(Throwable p0);
}
