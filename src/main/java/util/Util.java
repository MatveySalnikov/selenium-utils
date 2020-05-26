package util;

public class Util {

    public static void myWait(int millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
