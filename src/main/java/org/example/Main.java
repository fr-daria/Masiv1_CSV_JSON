package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        ClientLog log = new ClientLog();
        Basket b = new Basket(new int[]{60, 45, 24, 340}, new String[]{"1. Хлеб", "2. Соль", "3. Вода", "4. Вино"});
        b.addToCart(3, 5);
        log.log(3,5);
        try {
            log.exportAsCSV(new File("log.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        b.printCart();
        b.saveJSON(new File("text.json"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("shop.xml");

        Element root = doc.getDocumentElement();
        Element load = (Element) root.getElementsByTagName("load").item(0);
        Element save = (Element) root.getElementsByTagName("save").item(0);
        Element login = (Element) root.getElementsByTagName("log").item(0);

        Boolean isLoad = Boolean.parseBoolean(load.getElementsByTagName("enabled").item(0).getTextContent());
        String loadFile = load.getElementsByTagName("fileName").item(0).getTextContent();
        String loadFormat = load.getElementsByTagName("format").item(0).getTextContent();

        Boolean isSave = Boolean.parseBoolean(save.getElementsByTagName("enabled").item(0).getTextContent());
        String saveFile = save.getElementsByTagName("fileName").item(0).getTextContent();
        String saveFormat = save.getElementsByTagName("format").item(0).getTextContent();

        Boolean isLog = Boolean.parseBoolean(login.getElementsByTagName("enabled").item(0).getTextContent());
        String logFile = login.getElementsByTagName("fileName").item(0).getTextContent();
    }
}