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
        Log.info("Loaded ExampleJavaMod constructor.");

        // death
        Fi luaMods = Vars.dataDirectory.child("luamods");
        luaMods.mkdirs();
        for (Fi mod : luaMods.list(File::isDirectory))
            mods.add(new LuaMod(mod));
    }

    @Override
    public void init() {
        mods.each(LuaMod::callInit);
    }

    @Override
    public void loadContent(){
        mods.each(LuaMod::callLoad);
    }
}
