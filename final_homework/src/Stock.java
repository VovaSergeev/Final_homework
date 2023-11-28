// Класс для представления наличия товаров у продавцов
class Stock {
    private int sellerId; // ID продавца
    private int productId; // ID товара
    private double price; // Цена товара
    private int quantity; // Количество товара в наличии у продавца

    // Конструктор класса
    public Stock(int sellerId, int productId, double price, int quantity) {
        this.sellerId = sellerId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры для полей класса
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}