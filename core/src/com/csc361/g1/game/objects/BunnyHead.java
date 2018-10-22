package com.csc361.g1.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.csc361.g1.game.Assets;
import com.csc361.g1.util.CharacterSkin;
import com.csc361.g1.util.Constants;
import com.csc361.g1.util.GamePreferences;
import com.badlogic.gdx.math.MathUtils;
import com.csc361.g1.util.AudioManager;
// Chapter 12
import com.badlogic.gdx.graphics.g2d.Animation;


/**
 * Bunny Head class that has all the logic for the bunny head
 * 
 * @author Austin
 *
 */
public class BunnyHead extends AbstractGameObject {
	public static final String TAG = BunnyHead.class.getName();
	public ParticleEffect dustParticles = new ParticleEffect();
	private final float JUMP_TIME_MAX = 0.3f;
	private final float JUMP_TIME_MIN = 0.1f;
	private final float JUMP_TIME_OFFSET_FLYING = JUMP_TIME_MAX - 0.018f;
	
	//Chapter 12
	private Animation animNormal;
	private Animation animCopterTransform;
	private Animation animCopterTransformBack;
	private Animation animCopterRotate;

	public enum VIEW_DIRECTION {
		LEFT, RIGHT
	}

	public enum JUMP_STATE {
		GROUNDED, FALLING, JUMP_RISING, JUMP_FALLING
	}

	private TextureRegion regHead;
	public VIEW_DIRECTION viewDirection;
	public float timeJumping;
	public JUMP_STATE jumpState;
	public boolean hasFeatherPowerup;
	public float timeLeftFeatherPowerup;

	/**
	 * Constructor to create the bunny head
	 */
	public BunnyHead() {
		init();
	}

	/**
	 * Initialize the bunny head and all of the abstract game objects instance
	 * variables
	 */
	public void init() {
		dimension.set(1, 1);
		regHead = Assets.instance.bunny.head;
		
		//Chapter 12 animation
		animNormal = Assets.instance.bunny.animNormal;
		animCopterTransform = Assets.instance.bunny.animCopterTransform;
		animCopterTransformBack =
		Assets.instance.bunny.animCopterTransformBack;
		animCopterRotate = Assets.instance.bunny.animCopterRotate;
		setAnimation(animNormal);
		
		// Center image on game object
		origin.set(dimension.x / 2, dimension.y / 2);
		// Bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		// Set physics values
		terminalVelocity.set(3.0f, 4.0f);
		friction.set(12.0f, 0.0f);
		acceleration.set(0.0f, -25.0f);
		// View direction
		viewDirection = VIEW_DIRECTION.RIGHT;
		// Jump state
		jumpState = JUMP_STATE.FALLING;
		timeJumping = 0;
		// Power-ups
		hasFeatherPowerup = false;
		timeLeftFeatherPowerup = 0;
		// Particles
		dustParticles.load(Gdx.files.internal("particles/dust.p"), Gdx.files.internal("particles"));
	}

	/**
	 * If the jump key was pressed make the bunny head jump in game. Keeps track of
	 * the states of the jump of the bunny head
	 * 
	 * @param jumpKeyPressed
	 *            whether the key was pressed or not
	 */
	public void setJumping(boolean jumpKeyPressed) {
		switch (jumpState) {
		case GROUNDED: // Character is standing on a platform
			if (jumpKeyPressed) {
				AudioManager.instance.play(Assets.instance.sounds.jump);
				// Start counting jump time from the beginning
				timeJumping = 0;
				jumpState = JUMP_STATE.JUMP_RISING;
			}
			break;
		case JUMP_RISING: // Rising in the air
			if (!jumpKeyPressed)
				jumpState = JUMP_STATE.JUMP_FALLING;
			break;
		case FALLING:// Falling down
		case JUMP_FALLING: // Falling down after jump
			if (jumpKeyPressed && hasFeatherPowerup) {
				AudioManager.instance.play(Assets.instance.sounds.jumpWithFeather, 1, MathUtils.random(1.0f, 1.1f));
				timeJumping = JUMP_TIME_OFFSET_FLYING;
				jumpState = JUMP_STATE.JUMP_RISING;
			}
			break;
		}
	}

	/**
	 * Set the boolean instance variable true for hasFeatherPowerup and set the
	 * duration to the constant time for the feather item. Can be picked up again
	 * during its duration and reset the time
	 * 
	 * @param pickedUp
	 *            whether or not a feather was picked up
	 */
	public void setFeatherPowerup(boolean pickedUp) {
		hasFeatherPowerup = pickedUp;
		if (pickedUp) {
			timeLeftFeatherPowerup = Constants.ITEM_FEATHER_POWERUP_DURATION;
		}
	}

	/**
	 * Check to see that bunny head has time left on the feather power up and has
	 * the power up
	 * 
	 * @return true or false depending on the conditions
	 */
	public boolean hasFeatherPowerup() {
		return hasFeatherPowerup && timeLeftFeatherPowerup > 0;
	}

	/**
	 * Override the update method to update the time remaining for the feather and
	 * air time as well as the direction the bunny head is facing
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (velocity.x != 0) {
			viewDirection = velocity.x < 0 ? VIEW_DIRECTION.LEFT : VIEW_DIRECTION.RIGHT;
		}
		if (timeLeftFeatherPowerup > 0) {
			timeLeftFeatherPowerup -= deltaTime;
			if (timeLeftFeatherPowerup < 0) {
				// disable power-up
				timeLeftFeatherPowerup = 0;
				setFeatherPowerup(false);
			}
		}
		dustParticles.update(deltaTime);
	}

	/**
	 * Override the update Motion in the Y direction becuase the bunny head has the
	 * ability to jump which could change the motion of it compared to another game
	 * object
	 */
	@Override
	protected void updateMotionY(float deltaTime) {
		switch (jumpState) {
		case GROUNDED:
			jumpState = JUMP_STATE.FALLING;
			// Only draw the dust if the bunny head is on the ground
			if (velocity.x != 0) {
				dustParticles.setPosition(position.x + dimension.x / 2, position.y);
				dustParticles.start();
			}
			break;
		case JUMP_RISING:
			// Keep track of jump time
			timeJumping += deltaTime;
			// Jump time left?
			if (timeJumping <= JUMP_TIME_MAX) {
				// Still jumping
				velocity.y = terminalVelocity.y;
			}
			break;
		case FALLING:
			break;
		case JUMP_FALLING:
			// Add delta times to track jump time
			timeJumping += deltaTime;
			// Jump to minimal height if jump key was pressed too short
			if (timeJumping > 0 && timeJumping <= JUMP_TIME_MIN) {
				// Still jumping
				velocity.y = terminalVelocity.y;
			}
		}
		if (jumpState != JUMP_STATE.GROUNDED)
			dustParticles.allowCompletion();
			super.updateMotionY(deltaTime);
	}

	/**
	 * Render the bunny head based on its positions and set the color if it has a
	 * powerup
	 */
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;

		// Draw Particles
		dustParticles.draw(batch);

		// Apply Skin Color
		batch.setColor(CharacterSkin.values()[GamePreferences.instance.charSkin].getColor());

		// Set special color when game object has a feather power-up
		if (hasFeatherPowerup)
			batch.setColor(1.0f, 0.8f, 0.0f, 1.0f);
		// Draw image
		reg = regHead;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x,
				scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
				viewDirection == VIEW_DIRECTION.LEFT, false);
		// Reset color to white
		batch.setColor(1, 1, 1, 1);
	}
}
