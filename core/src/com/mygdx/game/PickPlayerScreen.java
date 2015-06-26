package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by christian on 5/29/2015.
 */
public class PickPlayerScreen implements Screen {
    MyGdxGame game;

    OrthographicCamera camera;

    Texture RabbitSelect;
    Texture MouseSelect;
    Texture CatSelect;

    Rectangle CatRect;
    Rectangle RabbitRect;
    Rectangle MouseRect;

    public PickPlayerScreen(MyGdxGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        RabbitSelect = new Texture("Rabbitidle1.png");
        MouseSelect = new Texture("Mouseidle1.png");
        CatSelect = new Texture("catidle1.png");

        RabbitRect = new Rectangle(50, 250, RabbitSelect.getWidth(), RabbitSelect.getHeight());
        MouseRect = new Rectangle(350, 250, MouseSelect.getWidth(), MouseSelect.getHeight());
        CatRect = new Rectangle(50, 450, CatSelect.getWidth(), CatSelect.getHeight());
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Tap a character", 100, 100);
        game.batch.draw(RabbitSelect, RabbitRect.getX(), RabbitRect.getY());
        game.batch.draw(MouseSelect, MouseRect.getX(), MouseRect.getY());
        game.batch.draw(CatSelect, CatRect.getX(), CatRect.getY());
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new PlayGameScreen(game));
            dispose();
        }

        Vector3 tempCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(tempCoords);

        if(RabbitRect.contains(tempCoords.x, tempCoords.y)){
            game.setScreen(new PlayGameScreen(game));
            game.player = new Rabbit();
            game.player.sprite = new Sprite();
            game.player.position = new Vector2();
            game.player.velocity = new Vector2();
            //game.player.sprite.setPosition();position.x = game.startPosX;
            //game.player.position.y = game.startPosY;
            game.player.sprite.setPosition(game.startPosX, game.startPosY);
        }

        if(MouseRect.contains(tempCoords.x, tempCoords.y)){
            game.setScreen(new PlayGameScreen(game));
            game.player = new Mouse();
            game.player.position = new Vector2();
            game.player.velocity = new Vector2();
            //game.player.position.x= game.startPosX;
            //game.player.position.y = game.startPosY;
            game.player.sprite.setPosition(game.startPosX, game.startPosY);
        }

        if(CatRect.contains(tempCoords.x, tempCoords.y)){
            game.setScreen(new PlayGameScreen(game));
            game.player = new Cat();
            game.player.position = new Vector2();
            game.player.velocity = new Vector2();
            //game.player.position.x= game.startPosX;
            //game.player.position.y = game.startPosY;
            game.player.sprite.setPosition(game.startPosX, game.startPosY);
        }
    }

    @Override
    public void resize(int width, int heigh)
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
