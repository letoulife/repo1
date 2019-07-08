package cn.itheima.xml.dom4j;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.w3c.Document;

public class TestDom4j {
	
	/**
	 * 408 使用JDOM解析XML文档
	 */
	@Test
	public void testReadWebXML() {
		try {
			// 1.获取解析器
			SAXReader saxReader = new SAXReader();
			// 2.获得document文档对象
			Document doc = saxReader.read("src/cn/itheima/xml/schema/web.xml");
			// 3.获取根元素
			Element rootElement = doc.getRootElement();
			// System.out.println(rootElement.getName());//获取根元素的名称
			// System.out.println(rootElement.attributeValue("version"));//获取根元素中的属性值
			// 4.获取根元素下的子元素
			List<Element> childElements = rootElement.elements();
			// 5.遍历子元素
			for (Element element : childElements) {
				//6.判断元素名称为servlet的元素
				if ("servlet".equals(element.getName())) {
					//7.获取servlet-name元素
					Element servletName = element.element("servlet-name");
					//8.获取servlet-class元素
					Element servletClass = element.element("servlet-class");
					System.out.println(servletName.getText());
					System.out.println(servletClass.getText());
				}
				
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 409 使用XPATH读取XML文件
	 */
	public static void testXPath() {
		try {  
            //解析文档  
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();  
            domFactory.setNamespaceAware(true); // never forget this!  
            DocumentBuilder builder = domFactory.newDocumentBuilder();  
            org.w3c.dom.Document doc = builder.parse("src/city.xml");  
              
             XPathFactory factory = XPathFactory.newInstance(); //创建 XPathFactory  
             XPath xpath = factory.newXPath();//用这个工厂创建 XPath 对象  
              
             NodeList nodes = (NodeList)xpath.evaluate("location/property", doc, XPathConstants.NODESET);  
             String name = "";  
             String value = "";  
             for (int i = 0; i < nodes.getLength(); i++) {  
                  Node node = nodes.item(i);    
                  name = (String) xpath.evaluate("name", node, XPathConstants.STRING);  
                  value = (String) xpath.evaluate("value", node, XPathConstants.STRING);  
                  System.out.println("name="+name+";value="+value);  
             }  
              
              
	    } catch (Exception e) {  
	          // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	}

	public static void main(String[] args) {
		testXPath();
	}
}
