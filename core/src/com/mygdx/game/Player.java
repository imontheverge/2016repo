package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by christian on 5/29/2015.
 */
public class Player {

    public Sprite playerSprite;
    public Animation playerIdleAnimation;

    public float scaleX = 1;
    public float scaleY = 1;


    public void DrawPlayer(SpriteBatch batch, float animationTime, float x, float y)
    {
        TextureRegion region = playerIdleAnimation.getKeyFrame(animationTime);

        batch.draw(region, x, y);
    }

    public void UpdatePlayer(float animationTime)
    {

    }
}
