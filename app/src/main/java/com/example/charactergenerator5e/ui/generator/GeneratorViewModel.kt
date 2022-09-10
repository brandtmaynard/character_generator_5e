package com.example.charactergenerator5e.ui.generator

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GeneratorViewModel : ViewModel() {
//  liveData to automatically display the results
    private var _currentRace = MutableLiveData("Race")
    val currentRace: LiveData<String>
        get() = _currentRace

    private var _currentClass = MutableLiveData("Class")
    val currentClass: LiveData<String>
        get() = _currentClass

    private var _currentBackground = MutableLiveData("Background")
    val currentBackground: LiveData<String>
        get() = _currentBackground

    private var _currentAlignment = MutableLiveData("Alignment")
    val currentAlignment: LiveData<String>
        get() = _currentAlignment

//  sharedPreferences file to persist settings across sessions
    lateinit var sharedPref: SharedPreferences
    
//  collections which will hold the currently selected options
    private lateinit var raceOptions: MutableMap<String, MutableSet<String>?>
    private lateinit var classOptions: MutableMap<String, MutableSet<String>>
    private lateinit var backgroundOptions: MutableList<Pair<String, String>>
    private lateinit var alignmentOptions: MutableList<String>

//  collections to allow enabling/disabling specific options
//  settings is set to whichever list the settings fragment will need
    val raceSettings = MutableLiveData<List<Entry>>()
    val classSettings = MutableLiveData<List<Entry>>()
    val backgroundSettings = MutableLiveData<List<Entry>>()
    val alignmentSettings = MutableLiveData<List<Entry>>()
    var settings: LiveData<List<Entry>> = raceSettings

//  race options which a character becomes after birth, allowing them to have a second race
    private val lineages = listOf("Dhampir", "Hexblood", "Reborn")

//  options for specific races and classes which meaningfully flavor the resulting character
    private val chromaticDragons = listOf("Black (Acid)", "Blue (Lightning)", "Green (Poison)", "Red (Fire)", "White (Cold)")
    private val metallicDragons = listOf("Brass (Fire)", "Bronze (Lightning)", "Copper (Acid)", "Gold (Fire)", "Silver (Cold)")
    private val gemDragons = listOf("Amethyst (Force)", "Crystal (Radiant)", "Emerald (Psychic)", "Sapphire (Thunder)", "Topaz (Necrotic)")
    private val druidLands = listOf("Arctic", "Coast", "Desert", "Forest", "Grassland", "Mountain", "Swamp", "Underdark")
    private val warlockGenies = listOf("Dao (Earth)", "Djinni (Air)", "Efreeti (Fire)", "Marid (Water)")
    private val sorcererDivinities = listOf("Good", "Evil", "Law", "Chaos", "Neutrality")

//  generate Entries for each category and store them in the appropriate Settings variable
    fun generateAllEntries() {
        raceSettings.value = generateEntries(races)
        classSettings.value = generateEntries(classes)
        backgroundSettings.value = generateEntries(backgrounds)
        alignmentSettings.value = alignments.keys.map {CategoryEntry(it, sharedPref.getBoolean(it, true), 0)}
    }
    
//  generate Entries with "enabled" read from sharedPreferences, default true
    private fun generateEntries(category: Map<String, List<String>?>): List<Entry> {
        val entries = mutableListOf<Entry>()
        for ((name, subentries) in category) {
            val parentIndex = entries.size
            entries.add(CategoryEntry(name, sharedPref.getBoolean(name, true), subentries?.size ?: 0))
            subentries?.sorted()?.forEach {
                entries.add(SubcategoryEntry(it, sharedPref.getBoolean("$name, $it", true), parentIndex, name))
            }
        }
        return entries
    }

//  update the Options collections and sharedPreferences with whichever choices have been enabled in the settings
    fun updateOptions() {
        val editPref = sharedPref.edit()
        raceOptions = mutableMapOf()
        classOptions = mutableMapOf()
        backgroundOptions = mutableListOf()
        alignmentOptions = mutableListOf()
        for (raceSetting in raceSettings.value!!) {
            editPref.putBoolean(if (raceSetting is SubcategoryEntry) "${raceSetting.parentName}, ${raceSetting.name}" else raceSetting.name, raceSetting.enabled.get())
            if (raceSetting.enabled.get()) {
                if (raceSetting is CategoryEntry) {
                    raceOptions[raceSetting.name] = if (raceSetting.numberOfChildren > 0) mutableSetOf() else null
                } else if (raceSetting is SubcategoryEntry) {
                    raceOptions[raceSetting.parentName]?.add(raceSetting.name)
                }
            }
        }
        for (classSetting in classSettings.value!!) {
            editPref.putBoolean(if (classSetting is SubcategoryEntry) "${classSetting.parentName}, ${classSetting.name}" else classSetting.name, classSetting.enabled.get())
            if (classSetting.enabled.get()) {
                if (classSetting is CategoryEntry) {
                    classOptions[classSetting.name] = mutableSetOf()
                } else if (classSetting is SubcategoryEntry) {
                    classOptions[classSetting.parentName]?.add(classSetting.name)
                }
            }
        }
        for (backgroundSetting in backgroundSettings.value!!) {
            editPref.putBoolean(if (backgroundSetting is SubcategoryEntry) "${backgroundSetting.parentName}, ${backgroundSetting.name}" else backgroundSetting.name, backgroundSetting.enabled.get())
            if (backgroundSetting.enabled.get() && backgroundSetting is SubcategoryEntry) {
                backgroundOptions.add(Pair(backgroundSetting.name, backgroundSetting.parentName))
            }
        }
        for (alignmentSetting in alignmentSettings.value!!) {
            editPref.putBoolean(alignmentSetting.name, alignmentSetting.enabled.get())
            if (alignmentSetting.enabled.get()) {
                alignmentOptions.add(alignments[alignmentSetting.name]!!)
            }
        }
        editPref.apply()
    }

//  generate a new character by rerolling all four options
    fun generate() {
        _currentRace.value = generateRace()
        _currentClass.value = generateClass()
        _currentBackground.value = generateBackground()
        _currentAlignment.value = if (alignmentOptions.isNotEmpty()) alignmentOptions.random() else "no alignments selected"
    }

//  generate a new race of the form "{Race}(, {Subrace})(, {Extra})"
//  usingLineage represents whether or not this is a starting race for a gothic lineage
    private fun generateRace(usingLineage: Boolean = false): String {
        try {
            val chosenRace = raceOptions.keys.random()
            when {
//              determine original race for gothic lineages
                chosenRace in lineages -> {
//                  if this call of generateRace is determining the original race for another, return immediately
                    if (usingLineage) {
                        return chosenRace
                    }
                    val originalRace: String = generateRace(true)
//                  if another lineage is rolled as the original race, only return this lineage
//                  otherwise, return both
                    return if (originalRace in lineages) {
                        chosenRace
                    } else {
                        "$chosenRace, $originalRace"
                    }
                }
//              determine draconic ancestry for dragonborn
                chosenRace == "Dragonborn" -> {
                    val subrace = raceOptions[chosenRace]!!.random()
                    val color = when (subrace) {
                        "Chromatic" -> {
                            chromaticDragons.random()
                        }
                        "Metallic" -> {
                            metallicDragons.random()
                        }
                        "Gem" -> {
                            gemDragons.random()
                        }
//                      the remaining Dragonborn subraces can be chromatic or metallic but not gem
                        else -> {
                            (chromaticDragons + metallicDragons).random()
                        }
                    }
                    return "$chosenRace, $subrace, $color"
                }
//              if the chosen race has no subraces, return the race
//              otherwise, choose a subrace and return it
                raceOptions[chosenRace] == null -> {
                    return chosenRace
                }
                else -> {
                    return "$chosenRace, ${raceOptions[chosenRace]!!.random()}"
                }
            }
        } catch (e: Exception) {
            return "no races selected"
        }
    }

//  generate a new class of the form "{Class}, {Subclass}(, {Extra})"
    private fun generateClass(): String {
        return try {
            val chosenClass = classOptions.keys.random()
            val subclass = classOptions[chosenClass]!!.random()
            when {
//              determine type of land for Circle of the Land Druids
                chosenClass == "Druid" && subclass == "Land" -> {
                    "$chosenClass, $subclass, ${druidLands.random()}"
                }
//              determine dragon ancestor for Draconic Bloodline Sorcerers
                chosenClass == "Sorcerer" && subclass == "Draconic Bloodline" -> {
                    "$chosenClass, $subclass, ${(chromaticDragons + metallicDragons).random()}"
                }
//              determine affinity for Divine Soul Sorcerers' power sources
                chosenClass == "Sorcerer" && subclass == "Divine Soul" -> {
                    "$chosenClass, $subclass, ${sorcererDivinities.random()}"
                }
//              determine type of genie for Genie Warlocks
                chosenClass == "Warlock" && subclass == "Genie" -> {
                    "$chosenClass, $subclass, ${warlockGenies.random()}"
                }
                else -> "$chosenClass, $subclass"
            }
        } catch (e: Exception) {
            "no classes selected"
        }
    }

//  generate a new background of the form "{Background}, {Book}"
    private fun generateBackground(): String {
        return try {
            val (chosenBackground, chosenBook) = backgroundOptions.random()
            "$chosenBackground, $chosenBook"
        } catch (e: Exception) {
            "no backgrounds selected"
        }
    }
}