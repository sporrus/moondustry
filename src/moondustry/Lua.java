package moondustry;

import arc.*;
import arc.func.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

public class Lua {
    public static void infuseGlobals(Globals globals) {
        globals.set("Log", CoerceJavaToLua.coerce(Log.class));
        globals.set("Vars", CoerceJavaToLua.coerce(Vars.class));
        globals.set("Events", CoerceJavaToLua.coerce(Events.class));
        globals.set("EventType", CoerceJavaToLua.coerce(EventType.class));
        globals.set("WorldLoadEvent", CoerceJavaToLua.coerce(EventType.WorldLoadEvent.class));
        globals.set("cons", new LibFunction() {
            @Override
            public LuaValue call(LuaValue a) {
                if (!a.isfunction()) return LuaValue.NIL;
                return CoerceJavaToLua.coerce((Cons<Object>)(b) -> a.call(CoerceJavaToLua.coerce(b)));
            }
        });
    }
}
