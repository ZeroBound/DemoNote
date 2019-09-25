package uiautoz.zzw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import uiautoz.zzw.element.Element;
import uiautoz.zzw.utils.*;


/**
 * @author zzw
 */
public class AdbDevice {
	private static Pattern PATTERN_NUM = Pattern.compile("([0-9]+)");
	private static Pattern PATTERN_PKG = Pattern.compile("([\" \"][0-9]+)");
	private static Pattern PATTERN_PKG_ATY = Pattern.compile("([a-zA-Z0-9.]+/.[a-zA-Z0-9.]+)");
	private static final long DEFAULT_TIME = 500;

	public AdbDevice() {
		ShellUtils.getCheckAndroidHome();
		ShellUtils.cmd("wait-for-device");
//		getDevices();
	}


	/**
	 * 获取设备的id号
	 *
	 * @return 返回设备id号
	 */
	public String getDeviceId() {
		return shell("getprop ro.boot.serialno");
	}

	/**
	 * 获取设备中Android的版本号
	 *
	 * @return 返回Android版本号
	 */
	public String getAndroidVersion() {
		return shell("getprop ro.build.version.release");
	}

	/**
	 * 获取设备中SDK的版本号
	 *
	 * @return 返回SDK版本号
	 */
	public int getSdkVersion() {
		String sdkVersion = shell("getprop ro.build.version.sdk");

		return Integer.valueOf(sdkVersion);
	}

	/**
	 * 获取设备屏幕的分辨率
	 *
	 * @return 返回分辨率数组
	 */
	public int[] getScreenResolution() {
		String displayInfo = shell("dumpsys display | grep PhysicalDisplayInfo");
		ArrayList<Integer> out = ReUtils.matchInteger(PATTERN_NUM, displayInfo);

		return new int[] { out.get(0), out.get(1) };
	}

	/**
	 * 返回设备电池电量
	 *
	 * @return 返回电量数值
	 */
	public int getBatteryLevel() {
		String out = shell("dumpsys battery | grep level");

		return Integer.valueOf(out.split(": ")[1]);
	}

	/**
	 * 返回设备电池温度
	 *
	 * @return 返回温度数值
	 */
	public double getBatteryTemp() {
		String out = shell("dumpsys battery | grep temperature");

		return new Integer(out.split(": ")[1]) / 10.0;
	}

	/**
	 * 获取电池充电状态: 1 : BATTERY_STATUS_UNKNOWN, 未知状态 2 : BATTERY_STATUS_CHARGING,
	 * 充电状态 3 : BATTERY_STATUS_DISCHARGING, 放电状态 4 :
	 * BATTERY_STATUS_NOT_CHARGING, 未充电 5 : BATTERY_STATUS_FULL, 充电已满
	 *
	 * @return 返回状态数值
	 */
	public int getBatteryStatus() {
		String out = shell("dumpsys battery | grep status");

		return Integer.valueOf(out.split(": ")[1]);
	}

	/**
	 * 获取设备上当前界面的package和activity
	 *
	 * @return 返回package/activity
	 */
	public String getFocusedPackageAndActivity() {
		Process ps = ShellUtils.shell("dumpsys input | grep FocusedApplication");
		ArrayList<String> component = ReUtils.matchString(PATTERN_PKG_ATY, ShellUtils.getShellOut(ps));

		// 会有FocusedApplication: <null>情况发生
		if (component.isEmpty()) {
			String out = ShellUtils.getShellOut(ShellUtils.shell("dumpsys window w | grep \\/ | grep name="));

			return ReUtils.matchString(PATTERN_PKG_ATY, out) .get(0);
		}

		return component.get(0);
	}

	/**
	 * 获取设备上当前界面的包名
	 *
	 * @return 返回包名
	 */
	public String getCurrentPackageName() {
		return getFocusedPackageAndActivity().split("/")[0];
	}

	/**
	 * 获取设备上当前界面的activity
	 *
	 * @return 返回activity名
	 */
	public String getCurrentActivity() {
		return getFocusedPackageAndActivity().split("/")[1];
	}

	/**
	 *	获取 pid 值
	 *
	 * @param packageName 应用对应的包名
	 * @return 返回pid值
	 */
	public int getPid(String packageName) {
		String outId = shell("ps | grep -w " + packageName);
		ArrayList<Integer> num = ReUtils.matchInteger(PATTERN_PKG, outId);

		if (num.isEmpty()) {
			throw new TestException("应用包名不存在或者进程未开启...");
		}

		return num.get(0);
	}

	/**
	 *
	 * @param pid 进程的pid值
	 * @return 进程被杀死，返回true，否则返回false
	 */
	public boolean killProcess(int pid) {
		String out = shell("kill " + pid);

		return "".equals(out);
	}

	/**
	 * 退出当前应用
	 *
	 */
	public void quitCurrentApp() {
		shell("am force-stop " + getCurrentPackageName());
	}

	/**
	 * 重置当前应用，清除当前应用的数据且重启该应用
	 *
	 */
	public void resetApp() {
		String component = getFocusedPackageAndActivity();
		clearAppDate(getCurrentPackageName());
		startActivity(component);
	}

	/**
	 * 获取设备中的系统应用列表
	 *
	 * @return 返回系统应用列表
	 */
	public ArrayList<String> getSystemAppList() {
		Process ps = ShellUtils.shell("pm list packages -s");
		return getApps(ps);
	}

	/**
	 * 获取设备中的第三方应用列表
	 *
	 * @return 返回第三方应用列表
	 */
	public ArrayList<String> getThirdAppList() {
		Process ps = ShellUtils.shell("pm list packages -3");
		return getApps(ps);
	}

	/**
	 * 获取所有 app
	 *
	 * @param ps process
	 * @return  ArrayList
	 */
	public ArrayList<String> getApps(Process ps) {
		ArrayList<String> apps = new ArrayList<String>();

		String shellOut = ShellUtils.getShellOut(ps);

		Pattern pattern = Pattern.compile(("[a-z]+:[a-zA-Z0-9.]+"));
		ArrayList<String> result = ReUtils.matchString(pattern, shellOut);

		for (String string : result) {
			apps.add(string.split(":")[1]);
		}

		return apps;
	}

	/**
	 * 获取启动应用所花的时间
	 *
	 * @param component package/activity
	 * @return 返回时间值
	 */
	public int getAppStartTotalTime(String component) {
		String out = shell("am start -W " + component + " | grep TotalTime");

		return Integer.valueOf(out.split(": ")[1]);
	}


	public static ArrayList<String> getDevices(){
		String out = ShellUtils.getShellOut(ShellUtils.cmd("devices"));
		// TODO
		return (ArrayList<String>) Arrays.asList(out);
	}

	/**
	 * 复制设备上的文件至本地
	 *
	 * @param remotePath 设备上文件路径
	 * @param localPath 本地路径
	 * @return Run cmd result
	 */
	public String pullFile(String remotePath, String localPath) {
		return ShellUtils.getShellOut(ShellUtils.cmd("pull " + remotePath + " " + localPath));
	}

	/**
	 * 推送本地文件至设备
	 *
	 * @param localPath 本地文件路径
	 * @param remotePath 设备上的存储路径
	 * @return Run cmd result
	 */
	public String pushFile(String localPath, String remotePath) {
		return ShellUtils.getShellOut(ShellUtils.cmd("push " + localPath + " " + remotePath));
	}

	/**
	 * 安装应用
	 *
	 * @param appPath apk文件所在路径,apk名不能是中文名
	 * @return Run cmd result
	 */
	public String installApp(String appPath) {
		return ShellUtils.getShellOut(ShellUtils.cmd("install " + appPath));
	}

	/**
	 * 判断应用是否已经安装
	 *
	 * @param packageName 应用的包名
	 * @return 已安装，返回true，否则返回false
	 */
	public boolean isInstalled(String packageName) {
		Process ps = ShellUtils.shell("pm list package");

		return getApps(ps).contains(packageName);
	}

	/**
	 * 卸载指定应用
	 *
	 * @param packageName 应用包名，非apk名
	 * @return Run cmd result
	 */
	public String removeApp(String packageName) {
		return ShellUtils.getShellOut(ShellUtils.cmd("uninstall " + packageName));
	}

	/**
	 * 清除应用的用户数据
	 *
	 * @param packageName 应用的包名
	 * @return 清楚成功返回true, 否则返回false
	 */
	public boolean clearAppDate(String packageName) {
		String shellStr = "pm clear " + packageName;

		return "Success".equals(shell(shellStr));
	}

	/**
	 * 重启设备
	 */
	public void reboot() {
		ShellUtils.cmd("reboot");
	}

	/**
	 * 重启设备进入fastboot模式
	 */
	public void fastboot() {
		ShellUtils.cmd("reboot bootloader");
	}

	/**
	 * 执行shell命令
	 *
	 * @param command   shell命令
	 * @return 返回执行命令后输出的内容
	 */
	public String shell(String command) {
		return ShellUtils.getShellOut(ShellUtils.shell(command));
	}

	public String amStart(String...args){
		StringBuilder sb = new StringBuilder();
		sb.append("am start ");

		for(String arg: args){
			sb.append(arg).append(" ");
		}

		return shell(sb.toString().trim());
	}

	/**
	 * 启动一个应用
	 *
	 * @param component 应用包名加主类名，packageName/Activity
	 * @return Run cmd result
	 */
	public String startActivity(String component) {
		return amStart("-n", component);
	}

	/**
	 * 使用默认浏览器打开一个网页
	 *
	 * @param url 网页地址
	 * @return Run cmd result
	 */
	public String startWebpage(String url) {
		return amStart("-a android.intent.action.VIEW -d", url);
	}

	/**
	 * 使用拨号器拨打号码
	 *
	 * @param number 电话号码
	 * @return Run cmd result
	 */
	public String callPhone(int number) {
		return amStart("-a android.intent.action.CALL -d tel:", String.valueOf(number));
	}

	/**
	 * 发送一个按键事件
	 *
	 * @param keycode  键值
	 */
	public void sendKeyEvent(int keycode) {
		ShellUtils.shell("input keyevent " + keycode);
		SystemClock.sleep(DEFAULT_TIME);
	}

	/**
	 * 长按物理键
	 *
	 * @param keycode 键值
	 */
	public void longPressKey(int keycode) {
		String param1 = "DispatchKey(0,0,0," + keycode + ",0,0,0,0)";
		String param2 = "UserWait(1500)";
		String param3 = "DispatchKey(0,0,1," + keycode + ",0,0,0,0)";
		String param = param1 + "\n" + param2 + "\n" + param3;
		MonkeyScriptUtils.writeScript(param);
		runMonkey();
		SystemClock.sleep(DEFAULT_TIME);
	}

	/**
	 * 发送一个点击事件
	 *
	 * @param x x坐标
	 * @param y y坐标
	 */
	public void tap(int x, int y) {
		ShellUtils.shell("input tap " + x + " " + y);
		SystemClock.sleep(DEFAULT_TIME);
	}

	/**
	 * 发送一个点击事件
	 *
	 * @param x  x小于1，自动乘以分辨率转换为实际坐标，大于1，当做实际坐标处理
	 * @param y  y小于1，自动乘以分辨率转换为实际坐标，大于1，当做实际坐标处理
	 */
	public void tap(double x, double y) {
		double[] coords = ratio(x, y);
		ShellUtils.shell("input tap " + mergeToString(" ", coords));
		SystemClock.sleep(DEFAULT_TIME);
	}

	/**
	 * 发送一个点击事件
	 *
	 * @param e
	 *            元素对象
	 */
	public void tap(Element e) {
		ShellUtils.shell("input tap " + e.getX() + " " + e.getY());
		sleep(DEFAULT_TIME);
	}

	/**
	 * 发送一个滑动事件
	 *
	 * @param startX 起始x坐标
	 * @param startY  起始y坐标
	 * @param endX  结束x坐标
	 * @param endY 结束y坐标
	 * @param ms 持续时间
	 */
	public void swipe(int startX, int startY, int endX, int endY, long ms) {

		String param = mergeToString(",", startX , startY , endX ,endY ,ms);
		MonkeyScriptUtils.writeScript("Drag(" + param + ")");
		runMonkey();

		/*
		 * if (getSdkVersion() < 19) { ShellUtils.shell("input swipe " + startX
		 * + " " + startY + " " + endX + " " + endY); } else { ShellUtils.shell(
		 * "input swipe " + startX + " " + startY + " " + endX + " " + endY +
		 * " " + ms); }
		 */
		sleep(DEFAULT_TIME);
	}

	/**
	 * 发送一个滑动事件
	 *
	 * @param startX
	 *            起始x坐标
	 * @param startY
	 *            起始y坐标
	 * @param endX
	 *            结束x坐标
	 * @param endY
	 *            结束y坐标
	 * @param ms
	 *            持续时间
	 */
	public void swipe(double startX, double startY, double endX, double endY, long ms) {

		int supportSdk = 19;
		double[] coords = ratio(startX, startY, endX, endY);

		String allPoint = mergeToString(" ", coords);

		if (supportSdk > getSdkVersion() ) {
			ShellUtils.shell("input swipe " + allPoint);
		} else {
			ShellUtils .shell("input swipe "+ allPoint  + " " + ms);
		}

		sleep(DEFAULT_TIME);
	}

	/**
	 * 发送一个滑动事件
	 *
	 * @param e1  起始元素
	 * @param e2 终点元素
	 * @param ms  持续时间
	 */
	public void swipe(Element e1, Element e2, long ms) {
		String param = mergeToString(",", e1.getX(),e1.getY() ,e2.getX(), e2.getY(),ms);
		MonkeyScriptUtils.writeScript("Drag(" + param + ")");
		runMonkey();

		/*
		 * if (getSdkVersion() < 19) { ShellUtils.shell("input swipe " +
		 * e1.getX() + " " + e1.getY() + " " + e2.getX() + " " + e2.getY()); }
		 * else { ShellUtils.shell("input swipe " + e1.getX() + " " + e1.getY()
		 * + " " + e2.getX() + " " + e2.getY() + " " + ms); }
		 */

		sleep(500);
	}

	/**
	 * 左滑屏幕
	 */
	public void swipeToLeft() {
		swipe(0.8, 0.5, 0.2, 0.5, DEFAULT_TIME);
	}

	/**
	 * 右滑屏幕
	 */
	public void swipeToRight() {
		swipe(0.2, 0.5, 0.8, 0.5, DEFAULT_TIME);
	}

	/**
	 * 上滑屏幕
	 */
	public void swipeToUp() {
		swipe(0.5, 0.7, 0.5, 0.3, DEFAULT_TIME);
	}

	/**
	 * 下滑屏幕
	 */
	public void swipeToDown() {
		swipe(0.5, 0.3, 0.5, 0.7, DEFAULT_TIME);
	}

	/**
	 * 发送一个长按事件
	 *
	 * @param x  x坐标
	 * @param y y坐标
	 */
	public void longPress(int x, int y) {
		String param = "PressAndHold(" + x + "," + y + ",1500)";
		MonkeyScriptUtils.writeScript(param);
		runMonkey();
	}

	/**
	 * 发送一个长按事件
	 *
	 * @param x x坐标
	 * @param y y坐标
	 */
	public void longPress(double x, double y) {
		swipe(x, y, x, y, 1500);
	}

	/**
	 * 发送一个长按事件
	 *
	 * @param e  元素对象
	 */
	public void longPress(Element e) {
		String param = "PressAndHold(" + e.getX() + "," + e.getY() + ",1500)";
		MonkeyScriptUtils.writeScript(param);
		runMonkey();
	}

	/**
	 * 缩放事件
	 *
	 * @param startX1 第一起始点x坐标
	 * @param startY1  第一起始点y坐标
	 * @param endX1  第一终点x坐标
	 * @param endY1 第一终点y坐标
	 * @param startX2  第二起始点x坐标
	 * @param startY2 第二起始点y坐标
	 * @param endX2 第二终点x坐标
	 * @param endY2  第二终点y坐标
	 * @param ms  持续时间
	 */
	public void pinchZoom(int startX1, int startY1, int endX1, int endY1, int startX2, int startY2, int endX2,
						  int endY2, long ms) {
		String param = mergeToString(",", startX1, startY1,endX1, endY1 , startX2 , startY2 ,endX2, endY2 , ms);
		MonkeyScriptUtils.writeScript("PinchZoom(" + param + ")");
		runMonkey();
	}

	/**
	 * 发送一段文本，只支持英文，多个空格视为一个空格
	 *
	 * @param text 英文文本
	 */
	public void sendText(String text) {
		String[] str = text.split(" ");
		ArrayList<String> out = new ArrayList<String>();
		for (String string : str) {
			if (!("").equals(string)) {
				out.add(string);
			}
		}

		int length = out.size();
		for (int i = 0; i < length; i++) {
			ShellUtils.shell("input text " + out.get(i));
			sleep(100);
			if (i != length - 1) {
				sendKeyEvent(AndroidKeyCode.SPACE);
			}
		}
	}

	/**
	 * 清除文本
	 *
	 * @param text
	 *            清除文本框中的text
	 */
	public void clearText(String text) {
		int length = text.length();
		for (int i = length; i > 0; i--) {
			sendKeyEvent(AndroidKeyCode.BACKSPACE);
		}
	}

	private double[] ratio(double x, double y) {
		int[] display = getScreenResolution();
		double[] coords = new double[2];

		getCoords(x, y, display, coords);
		return coords;
	}

	private double[] ratio(double startX, double startY, double endX, double endY) {
		int[] display = getScreenResolution();
		double[] coords = new double[4];

		getCoords(startX, startY, display, coords);

		if (endX < 1) {
			coords[2] = display[0] * endX;
		} else {
			coords[2] = endX;
		}

		if (endY < 1) {
			coords[3] = display[1] * endY;
		} else {
			coords[3] = endY;
		}

		return coords;
	}

	private void getCoords(double x, double y, int[] display, double[] coords) {
		if (x < 1) {
			coords[0] = display[0] * x;
		} else {
			coords[0] = y;
		}

		if (y < 1) {
			coords[1] = display[1] * y;
		} else {
			coords[1] = y;
		}
	}

	private void runMonkey() {
		shell("monkey -f /data/local/tmp/monkey.txt 1");
	}

	/**
	 *
	 * @param separator 分隔符
	 * @param args 可以是单个参数 int String Array
	 * @return 带分隔符的字符串
	 */
	public String mergeToString(String separator, Object...args){
		String pattern = "[\\[\\]]*";
		String comma = "," , space = " ";
		String raw = join(separator, args).trim();

		if(comma.equals(separator)){
			pattern =  "[\\s\\[\\]]*";
		}else if(space.equals(separator)){
			pattern =  "[,\\[\\]]*";
		}else{
			return  raw.replaceAll(comma+space, separator).replaceAll(pattern, "");
		}
		return raw.replaceAll(pattern, "");

	}

	/** 再将符号插入**/
	private String join(String separator,Object[] args){
		if(args.length == 0){
			return "";
		}

		StringBuilder builder = new StringBuilder(objectToString(args[0]));
		for(int i=1; i<args.length;i++){
			builder.append(separator);
			builder.append(args[i]);
		}

		// 结果 [1, 2, 3, 4]
		return builder.toString();
	}

	/** 先将 Object 对象转换为 String **/
	private String objectToString(Object obj){
		if(obj.getClass().isArray()){
			if(obj instanceof Object[]){
				return Arrays.deepToString((Object[]) obj);
			}else if(obj instanceof double[]){
				return Arrays.toString((double[]) obj);
			}else if(obj instanceof float[]){
				return Arrays.toString((float[]) obj);
			}else if(obj instanceof int[]){
				return Arrays.toString((int[]) obj);
			}
			return "[...]";
		}
		return obj.toString();
	}

	public void sleep(long millis) {
		SystemClock.sleep(millis);
	}
}
