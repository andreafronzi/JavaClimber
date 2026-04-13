package it.unibo.view.GameLaunchedView.renderers.skingRegistry.impl;

import it.unibo.view.SpriteEnum;
import it.unibo.view.GameLaunchedView.renderers.skingRegistry.api.SkinSet;

public record SkinSetImpl (SpriteEnum left, SpriteEnum right) implements SkinSet { }
