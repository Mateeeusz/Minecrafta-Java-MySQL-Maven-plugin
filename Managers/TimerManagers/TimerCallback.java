package me.mati.skyblock.Managers.TimerManagers;

public interface TimerCallback<E> {
    void success(E p0);

    void error(E p0);
}