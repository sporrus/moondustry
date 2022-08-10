package moondustry;

import arc.*;
import arc.func.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

import java.lang.reflect.*;

public class Lua {
    public static void infuseGlobals(Globals globals) {
        globals.set("Log", CoerceJavaToLua.coerce(Log.class));
        globals.set("Items", CoerceJavaToLua.coerce(Items.class));
        globals.set("Blocks", CoerceJavaToLua.coerce(Blocks.class));
        globals.set("Block", CoerceJavaToLua.coerce(Block.class));
        globals.set("Liquids", CoerceJavaToLua.coerce(Liquids.class));
        globals.set("UnitTypes", CoerceJavaToLua.coerce(UnitTypes.class));
        globals.set("Vars", CoerceJavaToLua.coerce(Vars.class));
        globals.set("Core", CoerceJavaToLua.coerce(Core.class));
        globals.set("Call", CoerceJavaToLua.coerce(Call.class));
        globals.set("Events", CoerceJavaToLua.coerce(Events.class));
        globals.set("EventType", CoerceJavaToLua.coerce(EventType.class));
        globals.set("WorldLoadEvent", CoerceJavaToLua.coerce(EventType.WorldLoadEvent.class));
        globals.set("Stats", CoerceJavaToLua.coerce(Stats.class));
        globals.set("ConsumeLiquidFilter", CoerceJavaToLua.coerce(ConsumeLiquidFilter.class));
        globals.set("Wall", CoerceJavaToLua.coerce(Wall.class));
        globals.set("Category", CoerceJavaToLua.coerce(Category.class));
        globals.set("ItemStack", CoerceJavaToLua.coerce(ItemStack.class));
        globals.set("BuildVisibility", CoerceJavaToLua.coerce(BuildVisibility.class));
        globals.set("ItemTurret", CoerceJavaToLua.coerce(ItemTurret.class));
        globals.set("BasicBulletType", CoerceJavaToLua.coerce(BasicBulletType.class));
        globals.set("run", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Runnable) a::call);
            }
        });
        globals.set("boolf", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Boolf<Object>) (b) ->
                        (boolean) CoerceLuaToJava.coerce(a.call(CoerceJavaToLua.coerce(b)), boolean.class));
            }
        });
        globals.set("boolp", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Boolp) () ->
                        (boolean) CoerceLuaToJava.coerce(a.call(), boolean.class));
            }
        });
        globals.set("floatf", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Floatf<Object>) (b) ->
                        (float) CoerceLuaToJava.coerce(a.call(CoerceJavaToLua.coerce(b)), float.class));
            }
        });
        globals.set("floatp", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Floatp) () ->
                        (float) CoerceLuaToJava.coerce(a.call(), float.class));
            }
        });
        globals.set("cons", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Cons<Object>) (b) -> a.call(CoerceJavaToLua.coerce(b)));
            }
        });
        globals.set("prov", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Prov<Object>) () ->
                        CoerceLuaToJava.coerce(a.call(), Object.class));
            }
        });
        globals.set("func", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Func<Object, Object>) (b) ->
                        CoerceLuaToJava.coerce(a.call(CoerceJavaToLua.coerce(b)), Object.class));
            }
        });
        globals.set("extend", new VarArgFunction() {
            @Override
            public LuaValue invoke(Varargs var) {
                try {
                    if (var.narg() < 2) return LuaValue.NIL;
                    LuaValue a = var.arg(1);
                    LuaValue b = var.arg(2);
                    if (!(CoerceLuaToJava.coerce(a, Object.class) instanceof Class<?>)) return LuaValue.NIL;
                    if (!b.istable()) return LuaValue.NIL;
                    Class<?> clazz = (Class<?>) CoerceLuaToJava.coerce(a, Class.class);
                    Object[] params = new Object[var.narg() - 2];
                    Class<?>[] classes = new Class<?>[params.length];
                    for (int i = 0; i < params.length; i++) {
                        params[i] = CoerceLuaToJava.coerce(var.arg(i + 3), Object.class);
                        classes[i] = params[i].getClass();
                    }
                    Object obj = clazz.getDeclaredConstructor(classes).newInstance(params);
                    ObjectMap<String, Field> fieldMap = Reflection.getFieldMap(clazz);
                    LuaValue k = LuaValue.NIL;
                    while (true) {
                        Varargs n = b.next(k);
                        if ((k = n.arg1()).isnil())
                            break;
                        LuaValue v = n.arg(2);
                        String key = (String) CoerceLuaToJava.coerce(k, String.class);
                        if (!fieldMap.containsKey(key)) continue;
                        Field field = fieldMap.get(key);
                        Object val = CoerceLuaToJava.coerce(v,  field.getType());
                        field.setAccessible(true);
                        field.set(obj, val);
                    }
                    return CoerceJavaToLua.coerce(obj);
                } catch (Exception e) {
                    Log.err("extend died", e);
                }
                return LuaValue.NIL;
            }
        });
    }
}
