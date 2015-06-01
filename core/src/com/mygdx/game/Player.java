package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by christian on 5/29/2015.
 */
public class Player {

    public Animation playerIdleAnimation;
    Vector2 velocity;
    Vector2 position;

    public float scaleX = 1;
    public float scaleY = 1;


    public void DrawPlayer(SpriteBatch batch, float animationTime)
    {
        TextureRegion region = playerIdleAnimation.getKeyFrame(animationTime);

        batch.draw(region, position.x, position.y);
    }

    public void UpdatePlayer(float animationTime)
    {
        position.add(velocity);
    }
}
