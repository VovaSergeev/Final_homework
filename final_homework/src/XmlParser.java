import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Класс XmlParser предоставляет методы для чтения и анализа XML файлов.
 * Он содержит методы для чтения XML файлов с товарами, продавцами, данными по наличию товаров у продавцов и продажами.
 * Каждый метод возвращает соответствующую коллекцию объектов, полученных из XML файла.
 */
public class XmlParser {
    // Метод для чтения и анализа XML файла, который возвращает список элементов с
    // заданным тегом
    public static ArrayList<Element> readXML(String fileName, String tagName) {
        ArrayList<Element> elements = new ArrayList<>(); // Коллекция для хранения элементов
        try {
            // Создаем объекты для чтения и анализа XML файла
            File file = new File(fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            // Получаем список элементов с заданным тегом
            NodeList nodeList = doc.getElementsByTagName(tagName);

            // Проходим по всем элементам списка
            for (int i = 0; i < nodeList.getLength(); i++) {
                // Получаем текущий элемент
                Element element = (Element) nodeList.item(i);

                // Добавляем элемент в коллекцию
                elements.add(element);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            // Обрабатываем исключения
            e.printStackTrace();
        }
        return elements; // Возвращаем коллекцию элементов
    }

    // Метод для чтения и анализа XML файла с товарами
    public static HashMap<Integer, Product> readProducts(String fileName) {
        HashMap<Integer, Product> products = new HashMap<>(); // Коллекция для хранения товаров
        // Вызываем общий метод для чтения XML файла с тегом "product"
        ArrayList<Element> elements = readXML(fileName, "product");
        // Проходим по всем элементам коллекции
        for (Element element : elements) {
            // Получаем атрибуты элемента
            int id = Integer.parseInt(element.getAttribute("id"));
            String name = element.getAttribute("name");

            // Создаем объект класса Product
            Product product = new Product(id, name);

            // Добавляем объект в коллекцию
            products.put(id, product);
        }
        return products; // Возвращаем коллекцию товаров
    }

    // Метод для чтения и анализа XML файла с продавцами
    public static HashMap<Integer, Seller> readSellers(String fileName) {
        HashMap<Integer, Seller> sellers = new HashMap<>(); // Коллекция для хранения продавцов
        // Вызываем общий метод для чтения XML файла с тегом "seller"
        ArrayList<Element> elements = readXML(fileName, "seller");
        // Проходим по всем элементам коллекции
        for (Element element : elements) {
            // Получаем атрибуты элемента
            int id = Integer.parseInt(element.getAttribute("id"));
            String lastName = element.getAttribute("lastName");
            String firstName = element.getAttribute("firstName");

            // Создаем объект класса Seller
            Seller seller = new Seller(id, lastName, firstName);

            // Добавляем объект в коллекцию
            sellers.put(id, seller);
        }
        return sellers; // Возвращаем коллекцию продавцов
    }

    // Метод для чтения и анализа XML файла с данными по наличию товаров у продавцов
    public static ArrayList<Stock> readStocks(String fileName) {
        ArrayList<Stock> stocks = new ArrayList<>(); // Коллекция для хранения наличия товаров у продавцов
        // Вызываем общий метод для чтения XML файла с тегом "stock"
        ArrayList<Element> elements = readXML(fileName, "stock");
        // Проходим по всем элементам коллекции
        for (Element element : elements) {
            // Получаем атрибуты элемента
            int sellerId = Integer.parseInt(element.getAttribute("sellerId"));
            int productId = Integer.parseInt(element.getAttribute("productId"));
            double price = Double.parseDouble(element.getAttribute("price"));
            int quantity = Integer.parseInt(element.getAttribute("quantity"));

            // Создаем объект класса Stock
            Stock stock = new Stock(sellerId, productId, price, quantity);

            // Добавляем объект в коллекцию
            stocks.add(stock);
        }
        return stocks; // Возвращаем коллекцию наличия товаров у продавцов
    }

    // Метод для чтения и анализа XML файла с продажами
    public static ArrayList<Sale> readSales(String fileName) {
        ArrayList<Sale> sales = new ArrayList<>(); // Коллекция для хранения продаж
        try {
            // Вызываем общий метод для чтения XML файла с тегом "sale"
            ArrayList<Element> elements = readXML(fileName, "sale");
            // Проходим по всем элементам коллекции
            for (Element element : elements) {
                // Получаем атрибуты элемента
                int id = Integer.parseInt(element.getAttribute("id"));
                int sellerId = Integer.parseInt(element.getAttribute("sellerId"));
                int productId = Integer.parseInt(element.getAttribute("productId"));
                int quantity = Integer.parseInt(element.getAttribute("quantity"));
                String dateStr = element.getAttribute("date");

                // Преобразуем строку в объект класса Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date date = sdf.parse(dateStr);

                // Создаем объект класса Sale
                Sale sale = new Sale(id, sellerId, productId, quantity, date);

                // Добавляем объект в коллекцию
                sales.add(sale);
            }
        } catch (ParseException e) {
            // Обрабатываем исключения
            e.printStackTrace();
        }
        return sales; // Возвращаем коллекцию продаж
    }
}
