package uiautoz.zzw.element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uiautoz.zzw.utils.ShellUtils;
import uiautoz.zzw.*;
import uiautoz.zzw.utils.SystemClock;


/**
 * @author zzw
 *
 * 元素坐标位置查找器
 */
public class PositionQuery {
	private Pattern pattern = Pattern.compile("([0-9]+)");

	/** 获取系统临时存储目录 **/
	private String tempFile = System.getProperty("java.io.tmpdir");
	private String temp = new File(tempFile + File.separator +"dumpfile").getAbsolutePath();

	private Map<Integer, String> attrib = new HashMap<Integer, String>();
	private ArrayList<HashMap> attribs;
	private InputStream xml;
	private List<UiDump> dumps;

	public PositionQuery() {
		File dumpFile = new File(temp);
		if (!dumpFile.exists()) {
			dumpFile.mkdir();
		}
	}

	/**
	 * 通过text定位单个元素
	 *
	 * @param text text
	 * @return 返回元素位置坐标
	 */
	public Element findElementByText(String text) {
		return this.findElement(ElementAttribs.TEXT, text);
	}

	/**
	 * 通过text定位多个同属性的相同元素
	 *
	 * @param text text
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsByText(String text) {
		return this.findElements(ElementAttribs.TEXT, text);
	}

	/**
	 * 通过resource-id定位单个元素
	 *
	 * @param resourceId resource-id
	 * @return 返回元素位置坐标
	 */
	public Element findElementById(String resourceId) {
		return this.findElement(ElementAttribs.RESOURCE_ID, resourceId);
	}

	/**
	 * 通过resource-id定位多个同属性的相同元素
	 *
	 * @param resourceId resource-id
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsById(String resourceId) {
		return this.findElements(ElementAttribs.RESOURCE_ID, resourceId);
	}

	/**
	 * 通过class定位单个元素
	 *
	 * @param className className
	 * @return 返回元素位置坐标
	 */
	public Element findElementByClass(String className) {
		return this.findElement(ElementAttribs.CLASS, className);
	}

	/**
	 * 通过class定位多个同属性的相同元素
	 *
	 * @param className className
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsByClass(String className) {
		return this.findElements(ElementAttribs.CLASS, className);
	}

	/**
	 * 通过checked定位单个元素
	 *
	 * @param checked checked
	 * @return 返回元素位置坐标
	 */
	public Element findElementByChecked(String checked) {
		return this.findElement(ElementAttribs.CHECKED, checked);
	}

	/**
	 * 通过checked定位多个同属性的相同元素
	 *
	 * @param checked checked
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsByChecked(String checked) {
		return this.findElements(ElementAttribs.CHECKED, checked);
	}

	/**
	 * 通过checkable定位单个元素
	 *
	 * @param checkable checkable
	 * @return 返回元素位置坐标
	 */
	public Element findElementByCheckable(String checkable) {
		return this.findElement(ElementAttribs.CHECKABLE, checkable);
	}

	/**
	 * 通过checkable定位多个同属性的相同元素
	 *
	 * @param checkable checkable
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsByCheckable(String checkable) {
		return this.findElements(ElementAttribs.CHECKABLE, checkable);
	}

	/**
	 * 通过content-desc定位单个元素
	 *
	 * @param contentdesc content-desc
	 * @return 返回元素位置坐标
	 */
	public Element findElementByContentdesc(String contentdesc) {
		return this.findElement(ElementAttribs.CONTENT_DESC, contentdesc);
	}

	/**
	 * 通过content-desc定位多个同属性的相同元素
	 *
	 * @param contentdesc content-desc
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsByContentdesc(String contentdesc) {
		return this.findElements(ElementAttribs.CONTENT_DESC, contentdesc);
	}

	/**
	 * 通过clickable定位单个元素
	 *
	 * @param clickable clickable
	 * @return 返回元素位置坐标
	 */
	public Element findElementByClickable(String clickable) {
		return this.findElement(ElementAttribs.CLICKABLE, clickable);
	}

	/**
	 * 通过clickable定位多个同属性的相同元素
	 *
	 * @param clickable clickable
	 * @return 返回元素位置坐标集合
	 */
	public ArrayList<Element> findElementsByClickable(String clickable) {
		return this.findElements(ElementAttribs.CLICKABLE, clickable);
	}

	/**
	 * 通过text获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param text text
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsByText(String text) {
		return this.getBounds(ElementAttribs.TEXT, text);
	}

	/**
	 * 通过resource-id获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param resourceId resource-id
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsById(String resourceId) {
		return this.getBounds(ElementAttribs.RESOURCE_ID, resourceId);
	}

	/**
	 * 通过class获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param className className
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsByClass(String className) {
		return this.getBounds(ElementAttribs.CLASS, className);
	}

	/**
	 * 通过checked获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param checked checkable
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsByChecked(String checked) {
		return this.getBounds(ElementAttribs.CHECKED, checked);
	}

	/**
	 * 通过checkable获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param checkable checkable
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsByCheckable(String checkable) {
		return this.getBounds(ElementAttribs.CHECKABLE, checkable);
	}

	/**
	 * 通过content-desc获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param contentdesc content-desc
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsByContentdesc(String contentdesc) {
		return this.getBounds(ElementAttribs.CONTENT_DESC, contentdesc);
	}

	/**
	 * 通过clickable获取元素区域坐标，即bounds属性，方便截图对比功能
	 *
	 * @param clickable clickable
	 * @return 返回元素区域坐标集合
	 */
	public ArrayList<int[]> getBoundsByClickable(String clickable) {
		return this.getBounds(ElementAttribs.CLICKABLE, clickable);
	}

	/**
	 * 通过resource-id获取元素text属性
	 *
	 * @param resourceId  resource-id
	 * @return 返回text集合
	 */
	public ArrayList<String> getTextById(String resourceId) {
		return this.getText(ElementAttribs.RESOURCE_ID, resourceId);
	}

	/**
	 * 通过class获取元素text属性
	 *
	 * @param className class name
	 * @return 返回text集合
	 */
	public ArrayList<String> getTextByClass(String className) {
		return this.getText(ElementAttribs.CLASS, className);
	}


	/**
	 * UI dump 元素操作
	 */
	private void uidump() {
		String remotePath = "/data/local/tmp/uidump.xml ";
		String dumpRes = ShellUtils.getShellOut(ShellUtils.shell("uiautomator dump "+ remotePath));
		
		if("".equals(dumpRes)){
			ShellUtils.cmd("pull " + remotePath + temp);
			SystemClock.sleep(500);
		}
		
		ShellUtils.shell("rm " + remotePath);
		File file = new File(temp + File.separator +"uidump.xml").getAbsoluteFile();
		try {
			xml = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		dumps = new UiDumpHandler().getDumps(xml);
	}

	/**
	 * 查找单个元素的坐标点
	 *
	 * @param att 属性
	 * @param str 属性值
	 * @return ArrayList Element
	 */
	private Element findElement(int att, String str) {
		uidump();
		// 获取元素区域中心 bounds 值
		CharSequence input = getAttrib(att, str).get(ElementAttribs.BOUNDS);

		if (input == null) {
			throw new TestException("未在当前界面找到元素(" + str + ")");
		}

		Matcher mat = pattern.matcher(input);
		int[] bound = getCenterPoint(mat);

		return setXY(bound[0], bound[1]);
	}

	/**
	 * 查找所有相同元素的坐标点
	 *
	 * @param att 属性
	 * @param str 属性值
	 * @return ArrayList Elements
	 */
	private ArrayList<Element> findElements(int att, String str) {
		uidump();
		ArrayList<Element> elements = new ArrayList<Element>();
		ArrayList<HashMap> attribs = getAttribs(att, str);

		for (HashMap hashMap : attribs) {
			Matcher mat = pattern.matcher((String) hashMap .get(ElementAttribs.BOUNDS));

			int[] bound = getCenterPoint(mat);
	
			Element element = setXY(bound[0], bound[1]);
			elements.add(element);
		}

		return elements;
	}

	/**
	 * 获取元素坐标点属性值
	 *
	 * @param att 属性
	 * @param str 属性值
	 * @return ArrayList Bounds
	 */
	private ArrayList<int[]> getBounds(int att, String str) {
		uidump();
		ArrayList<int[]> bounds = new ArrayList<int[]>();
		ArrayList<HashMap> attribs = getAttribs(att, str);

		for (HashMap hashMap : attribs) {
			Matcher mat = pattern.matcher((String) hashMap.get(ElementAttribs.BOUNDS));

			int[] bound = getCenterPoint(mat);

			bounds.add(bound);
		}

		return bounds;
	}

	/**
	 * 获取元素text属性值
	 *
	 * @param att 属性
	 * @param str 属性值
	 * @return ArrayList text
	 */
	private ArrayList<String> getText(int att, String str) {
		uidump();
		ArrayList<String> text = new ArrayList<String>();
		ArrayList<HashMap> attribs = getAttribs(att, str);

		for (HashMap hashMap : attribs) {
			text.add((String) hashMap.get(ElementAttribs.TEXT));
		}

		return text;
	}

	/**
	 *  获取单个元素属性值集合
	 *
	 * @param att 属性
	 * @param str 属性值
	 * @return HashMap for attrib
	 */
	private HashMap<Integer, String> getAttrib(int att, String str) {
		for (UiDump dump : dumps) {
			boolean flag = isCurNode(att, str, dump);
			if (flag) {
				attrib = saveElement(attrib, dump);
				break;
			}
		}

		return (HashMap<Integer, String>) attrib;
	}

	/**
	 * 获取多个元素的属性值集合
	 *
	 * @param att 属性
	 * @param str 属性值
	 * @return ArrayList for attribs
	 */
	private ArrayList<HashMap> getAttribs(int att, String str) {
		HashMap<Integer, String> temp = null;
		attribs = new ArrayList<HashMap>();
		for (UiDump dump : dumps) {
			boolean flag = isCurNode(att, str, dump);
			temp = new HashMap<Integer, String>();

			if (flag) {
				temp =  saveElement(temp, dump);
				attribs.add(temp);
			}
			temp = null;
		}

		return attribs;
	}

	/**
	 * 元素存储器
	 *
	 * @param storage 存储器
	 * @param dump nodes
	 * @return  存储器
	 */
	private HashMap<Integer, String> saveElement(Map<Integer, String> storage, UiDump dump){
		storage.put(ElementAttribs.TEXT, dump.getText());
		storage.put(ElementAttribs.RESOURCE_ID, dump.getResourceId());
		storage.put(ElementAttribs.CLASS, dump.getClassName());
		storage.put(ElementAttribs.CHECKED, dump.getChecked());
		storage.put(ElementAttribs.CHECKABLE, dump.getCheckable());
		storage.put(ElementAttribs.CONTENT_DESC, dump.getContentDesc());
		storage.put(ElementAttribs.CLICKABLE, dump.getCheckable());
		storage.put(ElementAttribs.BOUNDS, dump.getBounds());

		return (HashMap<Integer, String>) storage;
	}

	/**判断是否为当前节点**/
	private boolean isCurNode(int att, String str, UiDump dump){
		boolean flag = false;
		switch (att) {
			case 0:
				flag = str.equals(dump.getText());
				break;
			case 1:
				flag = str.equals(dump.getResourceId());
				break;
			case 2:
				flag = str.equals(dump.getClassName());
				break;
			case 3:
				flag = str.equals(dump.getChecked());
				break;
			case 4:
				flag = str.equals(dump.getCheckable());
				break;
			case 5:
				flag = str.equals(dump.getContentDesc());
				break;
			case 6:
				flag = str.equals(dump.getClickable());
				break;
			default:
				break;
		}

		return flag;
	}

	/**
	 * 设置元素坐标点
	 *
	 * @param x x_potion
	 * @param y y_potion
	 * @return Element
	 */
	private Element setXY(int x, int y){
		Element element = new Element();
		element.setX(x);
		element.setY(y);
		
		return element;
	}

	/** 获取中心坐标点 **/
	private int[] getCenterPoint(Matcher matcher){

		ArrayList<Integer> coords = new ArrayList<Integer>();
		while (matcher.find()) {
			coords.add(Integer.valueOf(matcher.group()));
		}
		int startX = coords.get(0);
		int startY= coords.get(1);
		int endX = coords.get(2);
		int endY = coords.get(3);

		int centerX = (endX - startX) / 2 + startX;
		int centerY = (endY - startY) / 2 + startY;

		return new int[]{centerX, centerY};
	}
}
