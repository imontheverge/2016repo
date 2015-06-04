package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by christian on 5/29/2015.
 */
public class PlayGameScreen implements Screen {
    final MyGdxGame game;

    private float animationTime;

    Texture background;
    Sprite backgroundSprite;

    private OrthographicCamera camera;
    private Viewport viewport;
    float WORLD_HEIGHT = 50;
    float WORLD_WIDTH = 15;

    public PlayGameScreen (final MyGdxGame gam) {
        float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

        this.game = gam;
        camera = new OrthographicCamera(WORLD_HEIGHT * aspectRatio, 25); //every libgdx needs a camera;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera); // set the viewport to view what the camera sees
        //camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        animationTime = 0.0f;

        background = new Texture("background.png");
        backgroundSprite = new Sprite(background);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
                game.player.velocity.y += 0.5f;
                if(game.player.velocity.y >= game.maxUpwardVelocity.y)
                {
                    game.player.velocity.y = game.maxUpwardVelocity.y;
                }
            }
        }));

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
        camera.position.set(game.player.position.x + 150, game.player.position.y + 150, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundSprite, -100, 0);
        game.player.DrawPlayer(game.batch, animationTime);
        game.font.draw(game.batch, "Velocity: " + game.player.velocity, 400, 200 );
        game.batch.end();

        // in libgdx 0, 0 is the bottom left of the screen which is just straight retarded.
    }

    private void updateScene()
    {
        float deltaTime = Gdx.graphics.getDeltaTime(); //just getting milliseconds or whatever libgdx uses
        animationTime += deltaTime; //update the animation.

        game.player.velocity.y -= 0.01f;
        if(game.player.velocity.y <= game.maxGravity.y)
        {
            game.player.velocity.y = game.maxGravity.y;
        }

        game.player.UpdatePlayer(animationTime);
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
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
