package enums;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicReference;

public enum ObjectType {
    object("object", "class objects.Object"),
    card("card", "class objects.Card"),
    creature("creature", "class objects.Creature"),
    planeswalker("planeswalker", "class objects.Planeswalker"),
    spell("spell", "class objects.Spell"),
    dungeon("dungeon", "class objects.Dungeon"),
    ;

    private String text;
    private String className;

    ObjectType(String text, String className) {
        this.text = text;
        this.className = className;
    }

    @Override
    public final String toString()  {
        return text;
    }

    public final String getClassName() {return className;}

    public static ObjectType valueOfClass(String className){
        AtomicReference<ObjectType> objectType = new AtomicReference<>(ObjectType.object);
        EnumSet.allOf(ObjectType.class).forEach(object -> {
            if (className.compareTo(object.getClassName()) == 0)
            {
                objectType.set(object);
            }
        });

        return objectType.get();
    }
}
