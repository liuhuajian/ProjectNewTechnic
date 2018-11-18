package messi.lhj.com.projectnewtechnic.subscrthing;

import messi.lhj.com.projectnewtechnic.util.Logger;

public class Observer2 implements InterObser {

    public Observer2(Subject subject){
        subject.registerObserver(this);
    }
    @Override
    public void receiveData(String data) {
        Logger.d("Observer2-->"+data);
    }
}
