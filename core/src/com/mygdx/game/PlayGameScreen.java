package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by christian on 5/29/2015.
 * **/

    public class PlayGameScreen implements Screen {

     // FOR DEBUG/////////
     ////////////////*/
        private ShapeRenderer shapeRenderer;
    ///////////////
    ///////////////

        final MyGdxGame game;

        private float animationTime;

        Texture background;
        Texture floor;

        Sprite backgroundSprite;

        private OrthographicCamera camera;
        private Viewport viewport;
        float WORLD_HEIGHT = 32; //screen height in world coords
        float WORLD_WIDTH = 18; //screen width in world coords

        float velocityResetTimer = 10f;
        float previousVelSwipe;
        float swipeVelocity = 5f;

        public PlayGameScreen (final MyGdxGame gam) {

            this.game = gam;
            camera = new OrthographicCamera(); //every libgdx needs a camera;
            viewport = new StretchViewport(720, 1280, camera); // set the viewport to view what the camera sees
            animationTime = 0.0f;

            background = new Texture("background.png");
            floor = new Texture("floor background.png");

            backgroundSprite = new Sprite(background);
            backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

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
                /*game.player.velocity.y += 0.5f;
                if(game.player.velocity.y >= game.maxUpwardVelocity.y)
                {
                    game.player.velocity.y = game.maxUpwardVelocity.y;
                }*/

                    if(velocityResetTimer > 0.1f)
                    {
                        previousVelSwipe = swipeVelocity;
                        swipeVelocity = previousVelSwipe / 2;
                        game.player.velocity.y += swipeVelocity;
                    }
                }
            }));

            ///////////////////////////
            shapeRenderer = new ShapeRenderer();
            //////////////////////////
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
            game.batch.draw(backgroundSprite, 0, 0);
            game.player.DrawPlayer(game.batch, animationTime);

            game.batch.end();

            /////////////////////
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(game.player.sprite.getX(), game.player.sprite.getY(), 200, 200);
            shapeRenderer.end();
            /////////////////////

            game.guiBatch.begin();
            game.font.draw(game.guiBatch, "Velocity: " + game.player.velocity, 200, 200);
            game.font.draw(game.guiBatch, "Timer: " + velocityResetTimer, 200, 250);
            game.font.draw(game.guiBatch, "PrevVelocitySwipe: " + previousVelSwipe, 200, 300);
            game.font.draw(game.guiBatch, "CurrentVelocitySwipe: " + swipeVelocity, 200, 350);
            game.guiBatch.end();


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

            velocityResetTimer -= Gdx.graphics.getDeltaTime();
            if(velocityResetTimer <= 0)
            {
                velocityResetTimer = 5;
                swipeVelocity = 5;
            }
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