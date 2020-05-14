package product;

public abstract class Product {
    private String ID = "";
    private String name = "";
    private int price = 0;
    private String category = "";
    private String descreption = "";

    protected Product() {
    }

    protected Product(String name,String ID, int price, String category, String descreption) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.descreption = descreption;
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

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

}
