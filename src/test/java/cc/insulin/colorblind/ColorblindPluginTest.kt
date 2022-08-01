package cc.insulin.colorblind

import net.runelite.client.RuneLite
import net.runelite.client.externalplugins.ExternalPluginManager

fun main(args: Array<String>) {
    ExternalPluginManager.loadBuiltin(ColorblindPlugin::class.java)
    RuneLite.main(args)
}