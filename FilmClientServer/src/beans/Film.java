package beans;

public class Film {
    
    private String title;
    private String description;
    private String language;
    private double price;
    private int year;

    public Film(String title, String description, String language, double price, int year) {
        this.title = title;
        this.description = description;
        this.language = language;
        this.price = price;
        this.year = year;
    }

    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    
}
