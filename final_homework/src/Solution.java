import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Класс Solution содержит два метода для создания и записи JSON файлов.
 * Метод writeTop5Products создает и записывает JSON файл с топ 5 товаров с наибольшим количеством продаж.
 * Метод writeAverageSalesPerDay создает и записывает JSON файл с средним количеством проданных товаров в день.
 */
public class Solution {
    // Метод для создания и записи JSON файла с топ 5 товаров с наибольшим
    // количеством продаж
    public static void writeTop5Products(String fileName, HashMap<Integer, Product> products, ArrayList<Sale> sales) {
        try {
            // Создаем объект для записи JSON файла
            FileWriter writer = new FileWriter(fileName);

            // Создаем объект для форматирования JSON файла
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Создаем коллекцию для хранения количества продаж по каждому товару
            HashMap<Integer, Integer> salesCount = new HashMap<>();

            // Проходим по всем продажам
            for (Sale sale : sales) {
                // Получаем ID товара и количество проданных товаров
                int productId = sale.getProductId();
                int quantity = sale.getQuantity();

                // Обновляем коллекцию с количеством продаж по товару
                salesCount.put(productId, salesCount.getOrDefault(productId, 0) + quantity);
            }

            // Создаем коллекцию для сортировки товаров по количеству продаж в порядке
            // убывания
            TreeMap<Integer, Integer> sortedSalesCount = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    // Сравниваем количество продаж по двум товарам
                    int count1 = salesCount.get(o1);
                    int count2 = salesCount.get(o2);

                    // Если количество продаж равно, то сравниваем ID товаров
                    if (count1 == count2) {
                        return o1.compareTo(o2);
                    }

                    // Иначе, сортируем по количеству продаж в обратном порядке
                    return count2 - count1;
                }
            });

            // Добавляем все элементы из коллекции с количеством продаж в отсортированную
            // коллекцию
            sortedSalesCount.putAll(salesCount);

            // Создаем список для хранения топ 5 товаров
            List<Product> top5Products = new ArrayList<>();

            // Проходим по первым пяти элементам отсортированной коллекции
            for (int productId : sortedSalesCount.keySet()) {
                if (top5Products.size() == 5) {
                    break; // Прерываем цикл, если список заполнен
                }

                // Получаем объект класса Product по ID товара
                Product product = products.get(productId);

                // Добавляем объект в список
                top5Products.add(product);
            }

            // Преобразуем список в JSON строку
            String json = gson.toJson(top5Products);

            // Записываем JSON строку в файл
            writer.write(json);

            // Закрываем поток записи
            writer.close();
        } catch (IOException e) {
            // Обрабатываем исключения
            e.printStackTrace();
        }
    }

    // Метод для создания и записи JSON файла с среднее количество проданных
    // товаров в день
    public static void writeAverageSalesPerDay(String fileName, ArrayList<Sale> sales) {
        try {
            // Создаем объект для записи JSON файла
            FileWriter writer = new FileWriter(fileName);

            // Создаем объект для форматирования JSON файла
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Создаем коллекцию для хранения количества проданных товаров по каждой дате
            HashMap<Date, Integer> salesCount = new HashMap<>();

            // Проходим по всем продажам
            for (Sale sale : sales) {
                // Получаем дату и количество проданных товаров
                Date date = sale.getDate();
                int quantity = sale.getQuantity();

                // Обновляем коллекцию с количеством продаж по дате
                salesCount.put(date, salesCount.getOrDefault(date, 0) + quantity);
            }

            // Создаем коллекцию для хранения среднего количества проданных товаров по
            // каждой дате
            HashMap<Date, Double> averageSales = new HashMap<>();

            // Подсчитываем общее количество проданных товаров
            int totalQuantity = 0;
            for (int quantity : salesCount.values()) {
                totalQuantity += quantity;
            }

            // Подсчитываем общее количество дней с продажами
            int totalDays = salesCount.size();

            // Вычисляем среднее количество проданных товаров в день
            double averageQuantity = (double) totalQuantity / totalDays;

            // Проходим по всем датам в коллекции с количеством продаж
            for (Date date : salesCount.keySet()) {
                // Добавляем дату и среднее в коллекцию
                averageSales.put(date, averageQuantity);
            }

            // Создаем коллекцию для сортировки дат в хронологическом порядке
            TreeSet<Date> sortedDates = new TreeSet<>(averageSales.keySet());

            // Создаем список для хранения дат и средних значений в формате JSON объекта
            List<JsonObject> result = new ArrayList<>();

            // Проходим по всем отсортированным датам
            for (Date date : sortedDates) {
                // Получаем среднее количество проданных товаров в этот день
                double average = averageSales.get(date);

                // Преобразуем дату в строку в формате "dd.MM.yyyy"
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String dateStr = sdf.format(date);

                // Создаем JSON объект с датой и средним значением
                JsonObject obj = new JsonObject();
                obj.addProperty("date", dateStr);
                obj.addProperty("average", String.format("%.2f", average));

                // Добавляем JSON объект в список
                result.add(obj);
            }

            // Преобразуем список в JSON строку
            String json = gson.toJson(result);

            // Записываем JSON строку в файл
            writer.write(json);

            // Закрываем поток записи
            writer.close();
        } catch (IOException e) {
            // Обрабатываем исключения
            e.printStackTrace();
        }
    }
}
