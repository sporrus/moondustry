package moondustry;

import arc.files.*;
import arc.util.*;
import mindustry.*;
import mindustry.mod.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

import java.io.*;

public class Main extends Mod{
    public Main(){
        Log.info("Loaded ExampleJavaMod constructor.");

        // death
        Fi luaMods = Vars.dataDirectory.child("luamods");
        luaMods.mkdirs();
        for (Fi mod : luaMods.list(File::isDirectory)) {
            Globals globals = JsePlatform.standardGlobals();
            globals.set("Log", CoerceJavaToLua.coerce(Log.class));
            LuaValue chunk = globals.loadfile(mod.child("main.lua").absolutePath());
            chunk.call();
        }
    }

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");
    }

}
