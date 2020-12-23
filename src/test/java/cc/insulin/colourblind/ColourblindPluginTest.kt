package cc.insulin.colourblind

import net.runelite.client.RuneLite
import net.runelite.client.externalplugins.ExternalPluginManager

fun main(args: Array<String>) {
    ExternalPluginManager.loadBuiltin(ColourblindPlugin::class.java)
    RuneLite.main(args)
}