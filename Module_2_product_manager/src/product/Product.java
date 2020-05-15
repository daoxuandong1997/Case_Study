package product;

public abstract class Product {
    private String ID = "";
    private String name = "";
    private int price = 0;
    private String category = "";
    private String brand = "";

    protected Product() {
    }

    protected Product(String category,String ID, int price, String brand, String name) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
