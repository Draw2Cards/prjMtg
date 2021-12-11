package enums;

public enum Owner {
    player("player"),
    opponent("opponent")
    ;


    private final String text;

    Owner(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}