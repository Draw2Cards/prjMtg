package enums;

public enum Type {
    creature("creature"),
    artifact("artifact"),
    enchantment("enchantment"),
    land("land"),
    planeswalker("planeswalker"),
    token("token"),
    instant("instant"),
    sorcery("sorcery"),
    dungeon("dungeon"),
    ;

    private final String text;

    Type(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
