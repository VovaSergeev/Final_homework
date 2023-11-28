import java.util.Date;
// Класс для представления продажи

class Sale {
    private int id; // ID продажи
    private int sellerId; // ID продавца
    private int productId; // ID товара
    private int quantity; // Количество проданных товаров
    private Date date; // Дата продажи

    // Конструктор класса
    public Sale(int id, int sellerId, int productId, int quantity, Date date) {
        this.id = id;
        this.sellerId = sellerId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
    }

    // Геттеры и сеттеры для полей класса
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}