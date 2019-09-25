package uiautoz.zzw.element;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author zzw
 *
 * xml 解析类
 */
public class UiDumpHandler extends DefaultHandler {
	private List<UiDump> dumps = null;

	@Override
	public void startDocument(){
		dumps = new ArrayList<UiDump>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

		String attribNode = "node";

		if (attribNode.equals(qName)) {
			UiDump dump = new UiDump();
			dump.setText(attributes.getValue("text"));
			dump.setResourceId(attributes.getValue("resource-id"));
			dump.setClassName(attributes.getValue("class"));
			dump.setContentDesc(attributes.getValue("content-desc"));
			dump.setCheckable(attributes.getValue("checkable"));
			dump.setChecked(attributes.getValue("checked"));
			dump.setClickable(attributes.getValue("clickable"));
			dump.setBounds(attributes.getValue("bounds"));

			dumps.add(dump);
		}
	}

	/**
	 * 获取解析 xml 的结果
	 *
	 * @param xml xml stream
	 * @return List
	 */
	public List<UiDump> getDumps(InputStream xml) {

		// 获得解析工厂 SAXParserFactory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 工厂获取解析器SAXParser
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

		// 解析器解析
		UiDumpHandler handler = new UiDumpHandler();
		try {
			if (parser != null) {
				parser.parse(xml, handler);
			}
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return handler.getDumps();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void endDocument(){

	}


	private List<UiDump> getDumps() {
		return dumps;
	}

}
