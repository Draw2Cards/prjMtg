package enums;

public enum Zone {
    library("library"),
    hand("hand"),
    battlefield("battlefield"),
    graveyard("graveyard"),
    stack("stack"),
    exile("exile"),
    command("command"),
    ;

    private final String text;

    Zone(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
