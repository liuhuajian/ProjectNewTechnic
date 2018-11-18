package messi.lhj.com.projectnewtechnic.subscrthing;

import messi.lhj.com.projectnewtechnic.util.Logger;

public class Observer1 implements InterObser {

    public Observer1(Subject subject){
        subject.registerObserver(this);
    }
    @Override
    public void receiveData(String data) {
        Logger.d("Observer1-->"+data);
    }
}
