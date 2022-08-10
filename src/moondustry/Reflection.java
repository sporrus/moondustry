package moondustry;

import arc.struct.*;

import java.lang.reflect.*;

public class Reflection {
    public static ObjectMap<String, Field> getFieldMap(Class<?> clazz) {
        ObjectMap<String, Field> map = new ObjectMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) map.put(f.getName(), f);
        if (clazz.getSuperclass() != null) map.putAll(getFieldMap(clazz.getSuperclass()));
        return map;
    }
}
