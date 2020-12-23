package cc.insulin.colourblind

import com.google.inject.Inject
import net.runelite.api.widgets.WidgetID
import net.runelite.api.widgets.WidgetItem
import net.runelite.client.game.ItemManager
import net.runelite.client.ui.FontManager
import net.runelite.client.ui.overlay.WidgetItemOverlay
import net.runelite.client.ui.overlay.components.TextComponent
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle

class ColourblindItemOverlay @Inject constructor(
        private val config: ColourblindConfig,
        private val itemManager: ItemManager
) : WidgetItemOverlay() {

    init {
        showOnInventory()
        showOnBank()
        showOnInterfaces(WidgetID.KEPT_ON_DEATH_GROUP_ID, WidgetID.GUIDE_PRICE_GROUP_ID, WidgetID.LOOTING_BAG_GROUP_ID, WidgetID.SEED_BOX_GROUP_ID, WidgetID.KINGDOM_GROUP_ID)
    }

    override fun renderItemOverlay(graphics: Graphics2D, id: Int, widget: WidgetItem) {
        val item = itemManager.canonicalize(id).let { realId ->
            TaggedItem.lookup(realId) ?: return
        }

        if (item.shouldTag(config)) {
            graphics.font = FontManager.getRunescapeSmallFont()
            renderText(graphics, widget.canvasBounds, item)
        }
    }

    private fun renderText(graphics: Graphics2D, bounds: Rectangle, item: TaggedItem) {
        val textComponent = TextComponent()
        textComponent.setPosition(Point(bounds.x - 1, bounds.y + bounds.height - 1))
        textComponent.setColor(config.textColour())
        textComponent.setText(item.tagText(config))
        textComponent.render(graphics)
    }
}