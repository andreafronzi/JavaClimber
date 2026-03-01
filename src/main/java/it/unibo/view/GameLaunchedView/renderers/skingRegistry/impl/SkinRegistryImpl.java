package it.unibo.view.GameLaunchedView.renderers.skingRegistry.impl;

import java.util.HashMap;

import it.unibo.view.SpriteEnum;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinRegistry;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinSet;

/**
 * <p>Implementation of the {@link SkinRegistry} interface, responsible for managing and providing access to character skins.</p>
 */
public class SkinRegistryImpl implements SkinRegistry{

    /**
     * A HashMap that stores the mapping between skin ids and their corresponding SkinSet instances.
     */
    private final HashMap<String, SkinSet> skins;

    /**
     * Constructor for the SkinRegistryImpl class. Initializes the skin registry and loads the available skins.
     */
    public SkinRegistryImpl() {
        this.skins = new HashMap<>();
        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SkinSet getSkinSet(final String skinName) {
        return this.skins.get(skinName);
    }

    /**
     * Loads the available skins into the registry. This method populates the skins HashMap with predefined skin sets.
     */
    private void load() {
        this.skins.put("astro", new SkinSetImpl(SpriteEnum.ASTRO_LEFT, SpriteEnum.ASTRO_RIGHT));
    }
    
}
