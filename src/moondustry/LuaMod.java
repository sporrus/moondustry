package moondustry;

import arc.files.*;
import arc.util.*;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

public class LuaMod {
    public final Globals globals = JsePlatform.standardGlobals();
    public final Fi root;
    public final LuaValue main;

    public LuaMod(Fi root) {
        this.root = root;
        Lua.infuseGlobals(globals);
        globals.load("package.path = '" + root.absolutePath() + "/?.lua'").call();
        main = globals.loadfile(this.root.child("main.lua").absolutePath());
        main.call();
    }

    public void callInit() {
        try {
            globals.get("init").call();
        } catch (Exception e) {
            Log.err("Mod " + root.absolutePath() + " doesnt have init function!");
            Log.err(e);
        }
    }

    public void callLoad() {
        try {
            globals.get("load").call();
        } catch (Exception e) {
            Log.err("Mod " + root.absolutePath() + " doesnt have load function!");
            Log.err(e);
        }
    }
}
