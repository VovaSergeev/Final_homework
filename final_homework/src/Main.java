import java.util.ArrayList;
import java.util.HashMap;

/**
 * Точка старта приложения
 */
public class Main {
    public static void main(String[] args) {
        // Задание 1
        // Читаем и анализируем XML файлы с данными
        HashMap<Integer, Product> products = XmlParser.readProducts("data/products.xml");
        // HashMap<Integer, Seller> sellers = readSellers("sellers.xml");
        // ArrayList<Stock> stocks = readStocks("stocks.xml");
        ArrayList<Sale> sales = XmlParser.readSales("data/sales.xml");

        // Задание 1
        Solution.writeTop5Products("top5products.json", products, sales);
        // Задание 2
        Solution.writeAverageSalesPerDay("averagesales.json", sales);
    }
}
