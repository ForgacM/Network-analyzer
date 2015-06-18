package com.MarcelForgac.Model.XmlLogic;

import com.MarcelForgac.Model.XmlLogic.XmlList;

import javax.xml.xpath.XPathExpressionException;

/**
 * Created by marcelforgac on 19.3.15.
 */
public abstract class EthernetList {

    private static XmlList list = null;

    static {
        if(list == null) {
            list = new XmlList("ether_type_list.xml");
        }
    }

    public static String get(String name) {
        return compile(String.format("/types/type[name='%s']/hex", name));
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
