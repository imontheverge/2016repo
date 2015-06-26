package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by christian on 5/29/2015.
 */
public class Player {

    public Animation playerIdleAnimation;
    Vector2 velocity;
    Vector2 position;
    public Rectangle playerRectangle;
    Sprite sprite;

    public void DrawPlayer(SpriteBatch batch, float animationTime)
    {
        TextureRegion region = playerIdleAnimation.getKeyFrame(animationTime);

        sprite = new Sprite(playerIdleAnimation.getKeyFrame(animationTime));
        sprite.setScale(3);

        //batch.draw(sprite, sprite.getX(), sprite.getY(), 0, 0, sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), 0);
        sprite.draw(batch);
    }

    public void UpdatePlayer(float animationTime)
    {
        //sprite.setPosition(velocity.x, -3);
        playerRectangle = new Rectangle(position.x, position.y, playerIdleAnimation.getKeyFrame(animationTime).getRegionWidth(), playerIdleAnimation.getKeyFrame(animationTime).getRegionHeight());
    }
}
