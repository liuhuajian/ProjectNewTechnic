package messi.lhj.com.projectnewtechnic.subscrthing;

public interface Subject {
    void registerObserver(InterObser interObser);

    void unregisterObserver(InterObser interObser);

    void notifyData();

    void unregisterAllObserver();
}
