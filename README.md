# moondustry
lua mod loader for mindustry

after loading this mod once you can find a luamods directory in mindustry data directory
mods are just folders with main.lua inside
you can use require for importing java packages/your lua files

use run, boolf, boolp, floatf, floatp, cons, prov, func to transform functions into mindustry objects
example: cons(function (c) <do something with c> end) returns a Cons object

use extend to extend classes
usage: extend(Class, Table, Constructor params...)
table contains key / value pairs to change block values
example: extend(Wall, {health = 938, requirements = {ItemStack:new(Items.copper, 10}, buildVisibility = BuildVisibility.shown}, "gorodmi-wall")
