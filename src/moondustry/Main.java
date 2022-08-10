package moondustry;

import arc.files.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.mod.*;

import java.io.*;

public class Main extends Mod{
    public final Seq<LuaMod> mods = new Seq<>();

    public Main(){
        // death
        Fi luaMods = Vars.dataDirectory.child("luamods");
        luaMods.mkdirs();
        for (Fi mod : luaMods.list(File::isDirectory))
            mods.add(new LuaMod(mod));
    }

    @Override
    public void init() {
        Log.info("Initializing mods...");
        mods.each(LuaMod::callInit);
        Log.info("Mods initialized!");
    }

    @Override
    public void loadContent(){
        Log.info("Loading content...");
        mods.each(LuaMod::callLoad);
        Log.info("Content loaded!");
    }
}
