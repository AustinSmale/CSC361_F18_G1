package com.csc361.g1.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CameraHelper {
	private static final String TAG = CameraHelper.class.getName();
	
	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;
	
	private Vector2 position;
	private float zoom;
	private Sprite target;
	
	
	public CameraHelper () {
		position = new Vector2();
		zoom = 1.0f;
	}
	
	public void update (float deltaTime) {
		if (!hasTarget()) return;
		position.x = target.getX() + target.getOriginX();
		position.y = target.getY() + target.getOriginY();
	}
}
