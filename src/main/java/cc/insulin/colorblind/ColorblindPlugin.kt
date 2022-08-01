package cc.insulin.colorblind

import com.google.inject.Provides
import net.runelite.api.Client
import net.runelite.api.SpriteID.MINIMAP_ORB_HITPOINTS_ICON
import net.runelite.api.SpriteID.PLAYER_KILLER_SKULL
import net.runelite.api.VarPlayer
import net.runelite.api.events.VarbitChanged
import net.runelite.client.callback.ClientThread
import net.runelite.client.config.ConfigManager
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.events.ConfigChanged
import net.runelite.client.game.SpriteManager
import net.runelite.client.plugins.Plugin
import net.runelite.client.plugins.PluginDescriptor
import net.runelite.client.ui.overlay.OverlayManager
import net.runelite.client.util.ImageUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

@PluginDescriptor(name = "Colorblind Helper")
class ColorblindPlugin : Plugin() {
    private val log = logger()

    @Inject
    lateinit var config: ColorblindConfig

    @Inject
    lateinit var overlayManager: OverlayManager

    @Inject
    lateinit var overlay: ColorblindItemOverlay

    @Inject
    lateinit var client: Client

    @Inject
    lateinit var clientThread: ClientThread

    @Inject
    lateinit var spriteManager: SpriteManager

    override fun startUp() {
        checkAndSetIcons()
        overlayManager.add(overlay)
    }

    override fun shutDown() {
        clientThread.invoke(Runnable { restoreHpIcon() })
        overlayManager.remove(overlay)
    }

    @Subscribe
    fun onConfigChanged(event: ConfigChanged) {
        if (event.group != "colorblind-helper") return

        if (event.key == "skullPoison") {
            checkAndSetIcons()
        }
    }

    @Provides
    fun provideConfig(configManager: ConfigManager): ColorblindConfig {
        return configManager.getConfig(ColorblindConfig::class.java)
    }

    @Subscribe
    fun onVarbitChanged(varBit: VarbitChanged) {
        if (varBit.index == VarPlayer.IS_POISONED.id) {
            log.debug("Poison change")
            checkAndSetIcons()
        }
    }

    fun checkAndSetIcons() {
        clientThread.invoke(Runnable {
            val poisonState = client.getVar(VarPlayer.IS_POISONED)
            val diseaseState = client.getVar(VarPlayer.DISEASE_VALUE)
            log.debug("Using skull for poison: ${config.skullPoison()}, poisonState: $poisonState, diseaseState: $diseaseState")
            if (config.skullPoison() && (poisonState > 0 || diseaseState > 0)) {
                log.info("Setting skull")
                skullHpIcon()
            } else {
                log.info("Restoring HP icon")
                restoreHpIcon()
            }
        })
    }

    private fun skullHpIcon() {
        client.widgetSpriteCache.reset()
        val spriteOverrides = client.spriteOverrides

        spriteManager.getSprite(PLAYER_KILLER_SKULL, 0)?.let {
            val skull = ImageUtil.resizeCanvas(it, 30, 33)
            val skullSprite = ImageUtil.getImageSpritePixels(skull, client)
            spriteOverrides[MINIMAP_ORB_HITPOINTS_ICON] = skullSprite
        }
    }

    private fun restoreHpIcon() {
        client.widgetSpriteCache.reset()
        client.spriteOverrides.remove(MINIMAP_ORB_HITPOINTS_ICON)
    }
}

/**
 * Creates a SLF4J logger named with the class which it is called from.
 *
 * @return A SLF4J [Logger] instance named with the calling class.
 */
@Suppress("unused")
inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        // grab the parent class if this class is a companion object
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }

    return LoggerFactory.getLogger(T::class.java)
}