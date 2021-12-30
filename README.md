# Random Character Generator for Dungeons &amp; Dragons 5th Edition
This Android app randomly generates a race, class, background, and alignment from the core and supplemental rulebooks and adventures for Dungeons & Dragons 5th Edition.
It does not provide mechanical information about any of these options or how to play in general.

Format:

> Race, Subrace, Extra  
> Class, Subclass, Extra  
> Background, Book  
> Alignment

If a race has no subraces, "Subrace" will be omitted.
If a race had no subrace in its original printing and a later book added subraces, the original subrace will be named by the book's initialization
(currently this only applies to Dragonborn, Half-Elves, Half-Orcs, Humans, and Tieflings, all from the Player's Handbook, abbreviated "PHB").

"Extra" is used in a handful of cases where an extra decision can be made to further define the character:

* If the chosen race is Dhampir, Hexblood, or Reborn (all from Van Richten's Guide to Ravenloft),
the app will generate another race to serve as the original, complete with "Subrace" and "Extra" if applicable.
If the second race generated is one of these three, the first race will be presented alone.
For example, a Dhampir could be a "Dhampir, a "Dhampir, Triton", a "Dhampir, Elf, Wood", or a "Dhampir, Dragonborn, Gem, Topaz (Necrotic).

* If the chosen race is Dragonborn, the app will generate a draconic ancestry from the types available to that subrace.
PHB, Draconblood, and Ravenite can be any Chromatic or Metallic option

  * Chromatic: Black (Acid), Blue (Lightning), Green (Poison), Red (Fire), White (Cold)
  
  * Gem: Amethyst (Force), Crystal (Radiant), Emerald (Psychic), Sapphire (Thunder), Topaz (Necrotic)
  
  * Metallic: Brass (Fire), Bronze (Lightning), Copper (Acid), Gold (Fire), Silver (Cold)

* If the chosen class is Druid and the chosen subclass is Circle of the Land, the app will generate a type of land:
Arctic, Coast, Desert, Forest, Grassland, Mountain, Swamp, Underdark.

* If the chosen class is Sorcerer and the chosen subclass is Divine Soul, the app will generate an affinity for the source of their divine power:
Good, Evil, Law, Chaos, Neutrality.

* If the chosen class is Sorcerer and the chosen subclass is Draconic Bloodline, the app will generate a dragon ancestor from the types listed above for Chromatic and Metallic Dragonborn.

* If the chosen class is Warlock and the chosen subclass is Genie, the app will generate a type of genie to be their patron:
Dao (Earth), Djinni (Air), Efreeti (Fire), Marid (Water).

The available options for the four categories can be limited in the settings menu. "Extra" options such as Dragonborn ancestry cannot be limited.
