package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;

	private Animation rabbitIdle; //creates the libgdx animation object.
	private Animation mouseIdle;

	private float animationTime;

	private OrthographicCamera camera;
	private Viewport viewport;

	boolean isRabbitSelected = true; //know when to switch between rabbit and mouse

	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(); //every libgdx needs a camera;
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera); // set the viewport to view what the camera sees
		animationTime = 0.0f;

		//there is an easier way to do this using a texture atlas but for a small application
		//there isn't a reason to worry about memory
		//the float number 0.2f is the speed of the animation. smaller number = faster animation
		rabbitIdle = new Animation(0.2f, new TextureRegion(new Texture("Rabbitidle1.png")),
										 new TextureRegion(new Texture("Rabbitidle2.png")),
										 new TextureRegion(new Texture("Rabbitidle3.png")),
										 new TextureRegion(new Texture("Rabbitidle4.png")),
										 new TextureRegion(new Texture("Rabbitidle5.png")),
										 new TextureRegion(new Texture("Rabbitidle6.png")),
										 new TextureRegion(new Texture("Rabbitidle7.png")));
		rabbitIdle.setPlayMode(Animation.PlayMode.LOOP); //will loop from 1 to 7 then start back at 1

		mouseIdle = new Animation(0.2f, new TextureRegion(new Texture("Mouseidle1.png")),
										new TextureRegion(new Texture("Mouseidle2.png")),
										new TextureRegion(new Texture("Mouseidle3.png")),
										new TextureRegion(new Texture("Mouseidle4.png")),
										new TextureRegion(new Texture("Mouseidle5.png")));
		mouseIdle.setPlayMode(Animation.PlayMode.LOOP); // same for every sprite you have

		isRabbitSelected = true; //double initialize just to make sure you didnt forget
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//this gets confusing. if you dont have seperate methods for drawing and updating the render method
		//turns into a mess. always draw before you update.
		drawScene();
		updateScene();
	}

	private void drawScene()
	{
		batch.begin();
		batch.draw(rabbitIdle.getKeyFrame(animationTime), 100, 100); // this gets the frame of the animation and draw it at 200, 200
		batch.draw(mouseIdle.getKeyFrame(animationTime), 200, 200);
		batch.end();

		// in libgdx 0, 0 is the bottom left of the screen which is just straight retarded.
	}

	private void updateScene()
	{
		float deltaTime = Gdx.graphics.getDeltaTime(); //just getting milliseconds or whatever libgdx uses
		animationTime += deltaTime; //update the animation.
	}
}
