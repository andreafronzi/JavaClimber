package it.unibo.view.gamelaunchedview.renderers.skingRegistry.api;

/**
 * <p>This interface rapresent a registry of the skin.</p>
 */
public interface SkinRegistry {
    
    /**
     * <p>Gets the {@link SkinSet} associated with the given skin name.</p>
     *
     * @param skinName the name of the skin to retrieve
     * @return the SkinSet associated with the given skin name
     */
    SkinSet getSkinSet(String skinName);
}
