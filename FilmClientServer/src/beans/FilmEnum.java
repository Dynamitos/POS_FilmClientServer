package beans;

public enum FilmEnum {

    TITLE("Title"),DESCRIPTION("Description"),LANGUAGE("Language"),PRICE("Price"),YEAR("Year");
    
    private String name;
    
    FilmEnum(String name) {
        this.name = name;
    }
    
}
