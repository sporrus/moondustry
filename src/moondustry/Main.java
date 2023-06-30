package moondustry;

import arc.files.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.mod.*;

import java.io.*;

public class Main extends Mod{
    public final LuaMod luaStuff;

    public Main(){
        luaStuff = new LuaMod(Vars.tree.get("assets/lua"));
    }

    @Override
    public void init() {
        Log.info("(LUA) Initializing files...");
        mods.each(LuaMod::callInit);
        Log.info("(LUA) Files initialized!");
    }

    @Override
    public void loadContent(){
        Log.info("(LUA) Loading content...");
        mods.each(LuaMod::callLoad);
        Log.info("(LUA) Content loaded!");
    }
}
