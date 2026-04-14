package it.unibo.view.gamelaunchedview.renderers.skingRegistry.impl;

import it.unibo.view.SpriteEnum;
import it.unibo.view.gamelaunchedview.renderers.skingRegistry.api.SkinSet;

public record SkinSetImpl (SpriteEnum left, SpriteEnum right) implements SkinSet { }
