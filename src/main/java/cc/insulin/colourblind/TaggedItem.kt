package cc.insulin.colourblind

import net.runelite.api.ItemID

/**
 * Enum for groups of tagged item IDs
 *
 * @property shouldTag A function which takes in a [ColourblindConfig] instance and returns whether this group of
 * items should be displayed as tagged
 * @property tagText A function which takes in a [ColourblindConfig] instance and returns the tag name of these items
 * @property itemIds An array of the item IDs which should be tagged
 */
enum class TaggedItem(
        val shouldTag: (config: ColourblindConfig) -> Boolean,
        var tagText: (config: ColourblindConfig) -> String,
        vararg var itemIds: Int
) {
    GRIMY(
            { it.showGrimyHerbs() },
            { it.grimyHerbsTag() },
            ItemID.GRIMY_GUAM_LEAF,
            ItemID.GRIMY_MARRENTILL,
            ItemID.GRIMY_TARROMIN,
            ItemID.GRIMY_HARRALANDER,
            ItemID.GRIMY_RANARR_WEED,
            ItemID.GRIMY_TOADFLAX,
            ItemID.GRIMY_IRIT_LEAF,
            ItemID.GRIMY_AVANTOE,
            ItemID.GRIMY_KWUARM,
            ItemID.GRIMY_SNAPDRAGON,
            ItemID.GRIMY_CADANTINE,
            ItemID.GRIMY_LANTADYME,
            ItemID.GRIMY_DWARF_WEED,
            ItemID.GRIMY_TORSTOL,
            // Jungle Potion
            ItemID.GRIMY_SNAKE_WEED,
            ItemID.GRIMY_ARDRIGAL,
            ItemID.GRIMY_SITO_FOIL,
            ItemID.GRIMY_VOLENCIA_MOSS,
            ItemID.GRIMY_ROGUES_PURSE,
            // Chambers of Xeric
            ItemID.GRIMY_NOXIFER,
            ItemID.GRIMY_GOLPAR,
            ItemID.GRIMY_BUCHU_LEAF,
    ),
    NON_GRIMY(
            { it.showCleanHerbs() },
            { it.cleanHerbsTag() },
            ItemID.GUAM_LEAF,
            ItemID.MARRENTILL,
            ItemID.TARROMIN,
            ItemID.HARRALANDER,
            ItemID.RANARR_WEED,
            ItemID.TOADFLAX,
            ItemID.IRIT_LEAF,
            ItemID.AVANTOE,
            ItemID.KWUARM,
            ItemID.SNAPDRAGON,
            ItemID.CADANTINE,
            ItemID.LANTADYME,
            ItemID.DWARF_WEED,
            ItemID.TORSTOL,
            // Jungle Potion
            ItemID.SNAKE_WEED,
            ItemID.ARDRIGAL,
            ItemID.SITO_FOIL,
            ItemID.VOLENCIA_MOSS,
            ItemID.ROGUES_PURSE,
            // Chambers of Xeric
            ItemID.NOXIFER,
            ItemID.GOLPAR,
            ItemID.BUCHU_LEAF,
    );

    companion object {
        /**
         * Looks up an item ID and tries to find a [TaggedItem] matching it. Returns null if no items were found.
         *
         * @param itemId Item ID
         * @return [TaggedItem]
         */
        fun lookup(itemId: Int): TaggedItem? {
            return values().firstOrNull { it.itemIds.contains(itemId) }
        }
    }
}