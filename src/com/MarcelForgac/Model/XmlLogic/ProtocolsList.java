package com.MarcelForgac.Model.XmlLogic;

import javax.xml.xpath.XPathExpressionException;

/**
 * Created by marcelforgac on 13.3.15.
 *
 */

public abstract class ProtocolsList {

    private static XmlList list = null;

    static {
        if(list == null) {
            list = new XmlList("ip_protocol_list.xml");
        }
    }

    public static String get(String name) {
        return compile(String.format("/protocols/protocol[name='%s']/hex", name));
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