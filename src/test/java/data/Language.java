package data;

public enum Language {
    SELENIDE("SELENIDE"),
    JUNIT5("JUNIT5");
    public final String description;

    Language(String description){
        this.description = description;
    }
}
