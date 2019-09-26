package uiautoz;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import uiautoz.zzw.AdbDevice;
import uiautoz.zzw.AndroidKeyCode;
import uiautoz.zzw.element.Element;
import uiautoz.zzw.element.PositionQuery;
import uiautoz.zzw.utils.ShellUtils;


/**
 * @author zzw
 *
 * 测试类
 */
public class ForTest {
	public static void main(String[] args){
		AdbDevice device = new AdbDevice();
		//获取设备信息
		System.out.println("设备序列号: " + device.getDeviceId());
		int[] resolution = device.getScreenResolution();
		System.out.println("设备屏幕分辨率: " + resolution[0] + "x" + resolution[1]);
		System.out.println("设备Android版本: " + device.getAndroidVersion());
		System.out.println("设备SDK版本: " + device.getSdkVersion());
		System.out.println("设备电池状态： " + device.getBatteryStatus());
		System.out.println("设备电池温度： " + device.getBatteryTemp());
		System.out.println("设备电池电量： " + device.getBatteryLevel());

  		device.startActivity("com.android.settings/.Settings");
		//device.swipeToUp();

		PositionQuery position = new PositionQuery();
		Element display_element = position.findElementByText("显示");
		device.tap(display_element);
		device.tap(display_element);

		int[][] b = {{1, 3, 5}, {1, 3, 5}};
		System.out.println(device.mergeToString("_" ,b));
	}
}
