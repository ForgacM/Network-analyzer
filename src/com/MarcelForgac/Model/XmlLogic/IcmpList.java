package com.MarcelForgac.Model.XmlLogic;

import javax.xml.xpath.XPathExpressionException;

/**
 * Created by marcelforgac on 22.3.15.
 */
public class IcmpList {

	private static XmlList list = null;

	static {
		if (list == null) {
			list = new XmlList("icmp_list.xml");
		}
	}

	public static String get(String name) {
		return compile(String.format("/types/type[name='%s']/dec", name));
	}

	public static String getNameBydec (Integer dec) {
		return compile(String.format("/types/type[dec='%d']/name", dec));
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
