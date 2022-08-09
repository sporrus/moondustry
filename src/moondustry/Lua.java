package moondustry;

import arc.*;
import arc.func.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

import java.math.*;

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
        globals.set("Events", CoerceJavaToLua.coerce(Events.class));
        globals.set("EventType", CoerceJavaToLua.coerce(EventType.class));
        globals.set("WorldLoadEvent", CoerceJavaToLua.coerce(EventType.WorldLoadEvent.class));
        globals.set("Stats", CoerceJavaToLua.coerce(Stats.class));
        globals.set("ConsumeLiquidFilter", CoerceJavaToLua.coerce(ConsumeLiquidFilter.class));
        globals.set("run", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Runnable) a::call);
            }
        });
        globals.set("boolf", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Boolf<Object>)(b) ->
                        (boolean) CoerceLuaToJava.coerce(a.call(CoerceJavaToLua.coerce(b)), boolean.class));
            }
        });
        globals.set("boolp", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Boolp)() ->
                        (boolean) CoerceLuaToJava.coerce(a.call(), boolean.class));
            }
        });
        globals.set("floatf", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Floatf<Object>)(b) ->
                        (float) CoerceLuaToJava.coerce(a.call(CoerceJavaToLua.coerce(b)), float.class));
            }
        });
        globals.set("floatp", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Floatp)() ->
                        (float) CoerceLuaToJava.coerce(a.call(), float.class));
            }
        });
        globals.set("cons", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Cons<Object>)(b) -> a.call(CoerceJavaToLua.coerce(b)));
            }
        });
        globals.set("prov", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Prov<Object>)() ->
                        CoerceLuaToJava.coerce(a.call(), Object.class));
            }
        });
        globals.set("func", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Func<Object, Object>)(b) ->
                        CoerceLuaToJava.coerce(a.call(CoerceJavaToLua.coerce(b)), Object.class));
            }
        });
    }
}
