// Класс для представления товара
class Product {
    private int id; // ID товара
    private String name; // Наименование товара

    // Конструктор класса
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры для полей класса
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}