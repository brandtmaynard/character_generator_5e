package com.example.charactergenerator5e.ui.generator

// races contains information on the available races
// each key is the name of a race
// each value is a list of that race's subraces, or null if the race has no subraces
val races: Map<String, List<String>?> = mapOf(
    "Aarakocra" to null,
    "Aasimar" to listOf(
        "Fallen",
        "Protector",
        "Scourge"
    ),
    "Autognome" to null,
    "Bugbear" to null,
    "Centaur" to null,
    "Changeling" to null,
    "Dhampir" to null,
    "Dragonborn" to listOf(
        "Chromatic",
        "Draconblood",
        "Gem",
        "Metallic",
        "PHB",
        "Ravenite"
    ),
    "Dwarf" to listOf(
        "Duergar",
        "Hill",
        "Mark of Warding",
        "Mountain"
    ),
    "Elf" to listOf(
        "Astral",
        "Drow",
        "Eladrin",
        "High",
        "Mark of Shadow",
        "Pallid",
        "Sea",
        "Shadar-kai",
        "Wood"
    ),
    "Fairy" to null,
    "Firbolg" to null,
    "Genasi" to listOf(
        "Air",
        "Earth",
        "Fire",
        "Water"
    ),
    "Giff" to null,
    "Gith" to listOf(
        "Githyanki",
        "Githzerai"
    ),
    "Gnome" to listOf(
        "Deep",
        "Forest",
        "Mark of Scribing",
        "Rock"
    ),
    "Goblin" to null,
    "Goliath" to null,
    "Grung" to null,
    "Hadozee" to null,
    "Half-Elf" to listOf(
        "Aquatic Elf Descent",
        "Drow Descent",
        "High Elf Descent",
        "Mark of Detection",
        "Mark of Storm",
        "PHB",
        "Wood Elf Descent"
    ),
    "Half-Orc" to listOf(
        "Mark of Finding",
        "PHB"
    ),
    "Halfling" to listOf(
        "Ghostwise",
        "Lightfoot",
        "Lotusden",
        "Mark of Healing",
        "Mark of Hospitality",
        "Stout"
    ),
    "Harengon" to null,
    "Hexblood" to null,
    "Hobgoblin" to null,
    "Human" to listOf(
        "Mark of Finding",
        "Mark of Handling",
        "Mark of Making",
        "Mark of Passage",
        "Mark of Sentinel",
        "PHB"
    ),
    "Kalashtar" to null,
    "Kender" to null,
    "Kenku" to null,
    "Kobold" to null,
    "Leonin" to null,
    "Lizardfolk" to null,
    "Locathah" to null,
    "Loxodon" to null,
    "Minotaur" to null,
    "Orc" to null,
    "Owlin" to null,
    "Plasmoid" to null,
    "Reborn" to null,
    "Satyr" to null,
    "Shifter" to listOf(
        "Beasthide",
        "Longtooth",
        "Swiftstride",
        "Wildhunt"
    ),
    "Simic Hybrid" to null,
    "Tabaxi" to null,
    "Thri-kreen" to null,
    "Tiefling" to listOf(
        "Asmodeus",
        "Baalzebul",
        "Devil's Tongue",
        "Dispater",
        "Fierna",
        "Glasya",
        "Hellfire",
        "Infernal Legacy",
        "Levistus",
        "Mammon",
        "Mephistopheles",
        "PHB",
        "Winged",
        "Zariel"
    ),
    "Tortle" to null,
    "Triton" to null,
    "Vedalken" to null,
    "Verdan" to null,
    "Warforged" to null,
    "Yuan-ti Pureblood" to null
)

// classes contains information on the available classes
// each key is the name of a class
// each value is a list of that class's subclasses
val classes: Map<String, List<String>> = mapOf(
    "Artificer" to listOf(
        "Alchemist",
        "Armorer",
        "Artillerist",
        "Battle Smith"
    ),
    "Barbarian" to listOf(
        "Ancestral Guardian",
        "Battlerager",
        "Beast",
        "Berserker",
        "Storm Herald",
        "Totem Warrior",
        "Wild Magic",
        "Zealot"
    ),
    "Bard" to listOf(
        "Creation",
        "Eloquence",
        "Glamour",
        "Lore",
        "Spirits",
        "Swords",
        "Valor",
        "Whispers"
    ),
    "Cleric" to listOf(
        "Arcana",
        "Death",
        "Forge",
        "Grave",
        "Knowledge",
        "Life",
        "Light",
        "Nature",
        "Order",
        "Peace",
        "Tempest",
        "Trickery",
        "Twilight",
        "War"
    ),
    "Druid" to listOf(
        "Dreams",
        "Land",
        "Moon",
        "Shepherd",
        "Spores",
        "Stars",
        "Wildfire"
    ),
    "Fighter" to listOf(
        "Arcane Archer",
        "Battle Master",
        "Cavalier",
        "Champion",
        "Echo Knight",
        "Eldritch Knight",
        "Psi Warrior",
        "Purple Dragon Knight",
        "Rune Knight",
        "Samurai"
    ),
    "Monk" to listOf(
        "Ascendant Dragon",
        "Astral Self",
        "Drunken Master",
        "Four Elements",
        "Kensei",
        "Long Death",
        "Mercy",
        "Open Hand",
        "Shadow",
        "Sun Soul"
    ),
    "Paladin" to listOf(
        "Ancients",
        "Conquest",
        "Crown",
        "Devotion",
        "Glory",
        "Oathbreaker",
        "Redemption",
        "Vengeance",
        "Watchers"
    ),
    "Ranger" to listOf(
        "Beast Master",
        "Drake Warden",
        "Fey Wanderer",
        "Gloom Stalker",
        "Horizon Walker",
        "Hunter",
        "Monster Slayer",
        "Swarmkeeper"
    ),
    "Rogue" to listOf(
        "Arcane Trickster",
        "Assassin",
        "Inquisitive",
        "Mastermind",
        "Phantom",
        "Scout",
        "Soulknife",
        "Swashbuckler",
        "Thief"
    ),
    "Sorcerer" to listOf(
        "Aberrant Mind",
        "Clockwork Soul",
        "Divine Soul",
        "Draconic Bloodline",
        "Lunar Sorcery",
        "Shadow Sorcery",
        "Storm Sorcery",
        "Wild Magic"
    ),
    "Warlock" to listOf(
        "Archfey",
        "Celestial",
        "Fathomless",
        "Fiend",
        "Genie",
        "Great Old One",
        "Hexblade",
        "Undead",
        "Undying"
    ),
    "Wizard" to listOf(
        "Abjuration",
        "Bladesinging",
        "Chronurgy",
        "Conjuration",
        "Divination",
        "Enchantment",
        "Evocation",
        "Graviturgy",
        "Illusion",
        "Necromancy",
        "Scribes",
        "Transmutation",
        "War Magic"
    )
)

// backgrounds contains information on the available backgrounds
// each key is the name of a source book
// each value is a list of that book's backgrounds
val backgrounds: Map<String, List<String>> = mapOf(
    "AAG" to listOf(
        "Astral Drifter",
        "Wildspacer"
    ),
    "AI" to listOf(
        "Celebrity Adventurer's Scion",
        "Failed Merchant",
        "Gambler",
        "Plaintiff",
        "Rival Intern"
    ),
    "DSotDQ" to listOf(
        "Knight of Solamnia",
        "Mage of High Sorcery"
    ),
    "EGW" to listOf(
        "Augen Trust",
        "Cobalt Scholar",
        "Grinner",
        "Luxonborn",
        "Myriad Operative",
        "Revelry Pirate",
        "Volstrucker Agent"
    ),
    "ERLW" to listOf(
        "House Agent"
    ),
    "GGR" to listOf(
        "Azorius Functionary",
        "Boros Legionnaire",
        "Dimir Operative",
        "Golgari Agent",
        "Gruul Anarch",
        "Izzet Engineer",
        "Orzhov Representative",
        "Rakdos Cultist",
        "Selesnya Initiate",
        "Simic Scientist"
    ),
    "GoS" to listOf(
        "Fisher",
        "Marine",
        "Shipwright",
        "Smuggler"
    ),
    "MOT" to listOf(
        "Athlete"
    ),
    "PHB" to listOf(
        "Acolyte",
        "Charlatan",
        "Criminal",
        "Entertainer",
        "Folk Hero",
        "Gladiator",
        "Guild Artisan",
        "Guild Merchant",
        "Hermit",
        "Knight",
        "Noble",
        "Outlander",
        "Pirate",
        "Sage",
        "Sailor",
        "Soldier",
        "Spy",
        "Urchin"
    ),
    "SCAG" to listOf(
        "City Watch",
        "City Watch Investigator",
        "Clan Crafter",
        "Cloistered Scholar",
        "Courtier",
        "Faction Agent",
        "Far Traveler",
        "Inheritor",
        "Knight of the Order",
        "Mercenary Veteran",
        "Urban Bounty Hunter",
        "Uthgardt Tribe Member",
        "Waterdhavian Noble"
    ),
    "SCC" to listOf(
        "Lorehold Student",
        "Prismari Student",
        "Quandrix Student",
        "Silverquill Student",
        "Witherbloom Student"
    ),
    "ToA" to listOf(
        "Anthropologist",
        "Archaeologist"
    ),
    "VRGR" to listOf(
        "Haunted One",
        "Investigator"
    ),
    "WBtW" to listOf(
        "Feylost",
        "Witchlight Hand"
    )
)

// alignments contains information on the available alignments
// each key is the abbreviated form of an alignment
// each value is the full name of that alignment
val alignments: Map<String, String> = mapOf("LG" to "Lawful Good", "NG" to "Neutral Good", "CG" to "Chaotic Good",
"LN" to "Lawful Neutral", "N" to "Neutral", "CN" to "Chaotic Neutral",
"LE" to "Lawful Evil", "NE" to "Neutral Evil", "CE" to "Chaotic Evil")