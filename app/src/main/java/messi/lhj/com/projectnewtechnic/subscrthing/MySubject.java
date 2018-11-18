package messi.lhj.com.projectnewtechnic.subscrthing;

import java.util.ArrayList;
import java.util.List;

import messi.lhj.com.projectnewtechnic.util.Logger;

public class MySubject implements Subject {
    private List<InterObser> obserList;
    private static volatile MySubject sMySubject;
    private String data;

    public MySubject(){
        this.obserList = new ArrayList<>();
    }

    public static MySubject getInstance(){
        if (sMySubject ==null){
            Logger.d("getInstance --->");
            synchronized (MySubject.class){
                if (sMySubject ==null){
                    sMySubject = new MySubject();
                }
            }
        }
        return sMySubject;
    }

    @Override
    public void registerObserver(InterObser interObser) {
        if (!obserList.contains(interObser)){
            Logger.d("add observer"+interObser);
            obserList.add(interObser);
        }
    }

    @Override
    public void unregisterObserver(InterObser interObser) {
        if (obserList.contains(interObser)){
            Logger.d("remove observer"+interObser);
            obserList.remove(interObser);
        }
    }

    @Override
    public void unregisterAllObserver(){
        if (obserList!=null) {
            obserList.clear();
        }
    }

    @Override
    public void notifyData() {
        for (InterObser interObser:obserList){
            interObser.receiveData(data);
        }
    }

    public void sendMsg(String data){
        this.data = data;
        notifyData();
    }
}
