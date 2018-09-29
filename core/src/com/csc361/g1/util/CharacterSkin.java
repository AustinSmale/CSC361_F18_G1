package com.csc361.g1.util;

import com.badlogic.gdx.graphics.Color;

/**
 * The characters skin, allows to set the color of the bunny head
 * 
 * @author Austin
 *
 */
public enum CharacterSkin {
	WHITE("White", 1.0f, 1.0f, 1.0f), GRAY("Gray", 0.7f, 0.7f, 0.7f), BROWN("Brown", 0.7f, 0.5f, 0.3f);
	private String name;
	private Color color = new Color();

	private CharacterSkin(String name, float r, float g, float b) {
		this.name = name;
		color.set(r, g, b, 1.0f);
	}

	/**
	 * Just print out the name of the character 
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Return the current color of the head
	 * @return
	 */
	public Color getColor() {
		return color;
	}
}
