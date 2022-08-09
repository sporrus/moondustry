package moondustry;

import arc.*;
import arc.util.*;
import mindustry.game.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

public class Lua {
    public static void infuseGlobals(Globals globals) {
        globals.set("Log", CoerceJavaToLua.coerce(Log.class));
        globals.set("Events", CoerceJavaToLua.coerce(Events.class));
        globals.set("EventType", CoerceJavaToLua.coerce(EventType.class));
        globals.set("WorldLoadEvent", CoerceJavaToLua.coerce(EventType.WorldLoadEvent.class));
    }
}
