package com.MarcelForgac.Model.XmlLogic;

import javax.xml.xpath.XPathExpressionException;

/**
 * Created by marcelforgac on 13.3.15.
 *
 */

public abstract class PortsList {

	private static XmlList list = null;

	static {
		if(list == null) {
			list = new XmlList("port_list.xml");
		}
	}

	public static String get(String name) {
		return compile(String.format("/ports/port[name='%s']/hex", name));
	}

	public static String getNameByHex (String hex) {
		return compile(String.format("/ports/port[hex='%s']/name", hex));
	}


	private static String compile(String expression) {
		try {
			return list.xPath.compile(expression).evaluate(list.xmlDocument);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return "";
	}

}
