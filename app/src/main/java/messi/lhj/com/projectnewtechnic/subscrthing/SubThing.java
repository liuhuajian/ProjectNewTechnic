package messi.lhj.com.projectnewtechnic.subscrthing;

public class SubThing {
    private static volatile SubThing subThing;

    public static SubThing getInstance(){
        if (subThing ==null){
            synchronized (SubThing.class){
                if (subThing ==null)
                    subThing = new SubThing();
            }
        }
        return subThing;
    }


}
