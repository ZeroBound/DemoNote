/**
 * @author zzw
 */

public enum Config {
    /*长 中 短*/
    L_TiME(5000), M_TIME(3000),S_TIME(1000);

    private final int timeout;

    Config (int timeout){
        this.timeout = timeout;
    }

    public int getTime(){
        String windowsOS = "Windows 7";
        if(windowsOS.equals(getOS())){
            return this.timeout*2;
        }else{
            return this.timeout;
        }
    }

    private String getOS(){
        return System.getProperty("os.name");
    }
}
