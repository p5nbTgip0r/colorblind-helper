package cc.insulin.colourblind

import net.runelite.client.config.Config
import net.runelite.client.config.ConfigGroup
import net.runelite.client.config.ConfigItem
import net.runelite.client.config.ConfigSection
import java.awt.Color

@ConfigGroup("colourblind-helper")
interface ColourblindConfig : Config {
    companion object {
        @ConfigSection(
                name = "Items",
                description = "The items to display tags on to help discern them",
                position = 99
        )
        const val identificationSection = "identification"
    }

    @JvmDefault
    @ConfigItem(
            keyName = "textColour",
            name = "Colour",
            position = -2,
            description = "The colour of the tag"
    )
    fun textColour(): Color = Color.WHITE

    @JvmDefault
    @ConfigItem(
            keyName = "skullPoison",
            name = "Display skull when poisoned",
            position = -2,
            description = "Displays a skull in place of a heart in the minimap HP orb when poisoned, venomed, or diseased"
    )
    fun skullPoison(): Boolean = true

    @JvmDefault
    @ConfigItem(
            keyName = "grimyHerbsTag",
            name = "Grimy Herbs Tag",
            description = "The tag to display on grimy herbs",
            section = identificationSection
    )
    fun grimyHerbsTag(): String = "G"

    @JvmDefault
    @ConfigItem(
            keyName = "showGrimyHerbs",
            name = "Grimy Herbs",
            description = "Show a tag on grimy herbs",
            section = identificationSection
    )
    fun showGrimyHerbs(): Boolean = true

    @JvmDefault
    @ConfigItem(
            keyName = "cleanHerbsTag",
            name = "Clean Herbs Tag",
            description = "The text to display on clean herbs",
            section = identificationSection
    )
    fun cleanHerbsTag(): String = "C"

    @JvmDefault
    @ConfigItem(
            keyName = "showCleanHerbs",
            name = "Clean Herbs",
            description = "Show a tag on clean herbs",
            section = identificationSection
    )
    fun showCleanHerbs(): Boolean = true
}