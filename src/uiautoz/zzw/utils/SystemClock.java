package uiautoz.zzw.utils;

/**
 * @author zzw
 * @date 2019/9/25
 **/
public class SystemClock {

    public static void sleep(long mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
