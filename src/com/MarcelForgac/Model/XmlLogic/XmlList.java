package com.MarcelForgac.Model.XmlLogic;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by marcelforgac on 13.3.15.
 *
 */

public class XmlList {

	protected Document xmlDocument = null;
	protected XPath xPath =  XPathFactory.newInstance().newXPath();

	public XmlList(String fileName) {

		InputStream in = getClass().getResourceAsStream("/" + fileName);

		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			xmlDocument = builder.parse(in);

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
