package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by christian on 5/29/2015.
 */
public class PlayGameScreen implements Screen {
    final MyGdxGame game;

    private Animation rabbitIdle; //creates the libgdx animation object.
    private Animation mouseIdle;

    private float animationTime;

    private OrthographicCamera camera;
    private Viewport viewport;

    Rabbit rabbit;
    Mouse mouse;

    public PlayGameScreen (final MyGdxGame gam) {
        this.game = gam;
        camera = new OrthographicCamera(); //every libgdx needs a camera;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera); // set the viewport to view what the camera sees
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        animationTime = 0.0f;

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener(){
            @Override
            public void onUp()
            {

            }
            @Override
            public void onDown()
            {

            }
            @Override
            public void onLeft()
            {

            }
            @Override
            public void onRight()
            {

            }
        }));

        rabbit = new Rabbit();
        mouse = new Mouse();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //this gets confusing. if you dont have seperate methods for drawing and updating the render method
        //turns into a mess. always draw before you update.
        drawScene();
        updateScene();
    }

    private void drawScene()
    {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        rabbit.DrawPlayer(game.batch, animationTime, 200, 200);
        mouse.DrawPlayer(game.batch, animationTime, 100, 100);
        game.batch.end();

        // in libgdx 0, 0 is the bottom left of the screen which is just straight retarded.
    }

    private void updateScene()
    {
        float deltaTime = Gdx.graphics.getDeltaTime(); //just getting milliseconds or whatever libgdx uses
        animationTime += deltaTime; //update the animation.
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        //any music here
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
