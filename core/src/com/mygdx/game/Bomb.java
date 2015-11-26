package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Dinna on 20/11/2015.
 */
public class Bomb {
    Sprite[] spBomb;
    SpriteBatch spriteBatch;
    int j, nSpeed, nIndex;
    boolean isExploded;
    float x, y;

    Bomb(TextureAtlas taBombExplode, float fX, float fY, int nCurrentIndex, Sprite sprChar) {
        x = fX - (sprChar.getWidth()/2);
        y = fY;
        nIndex = nCurrentIndex;
        spriteBatch = new SpriteBatch();
        isExploded = false;
        spBomb = new Sprite[16];
        j = 0;
        nSpeed = 0;

        //Set position of bomb based on the direction of character, 0=up 1=down 2=left 3=right
        if (nIndex == 0) {
            y += sprChar.getHeight();
        } else if (nIndex == 1) {
            y -= sprChar.getHeight();
        } else if (nIndex == 2) {
            x -= sprChar.getWidth() + 10;
        } else if (nIndex == 3) {
            x += sprChar.getWidth() + 10;
        }

        //Loop through regions of the TextureAtlas and assign each to an index of the array
        for(int a = 0; a < 16; a++) {
            spBomb[a] = new Sprite(taBombExplode.findRegion("frame_"+ a));
            spBomb[a].setOrigin(spBomb[a].getHeight() / 2, spBomb[a].getWidth() / 2);
            spBomb[a].setSize(50, 50);
            spBomb[a].setPosition(x, y);
        }
//        aniBomb = new Animation(0.10f, spBomb);
    }

    public void render () {
        spriteBatch.begin();

//        elapsedTime += Gdx.graphics.getDeltaTime();
//        spriteBatch.draw(aniBomb.getKeyFrame(elapsedTime, false), x, y, 50, 50);
//        isExploded = isExploded(j);
//        if (!isExploded(j)) {
//            j++;
//        }

        spBomb[j].draw(spriteBatch);
        nSpeed++;   //nSpeed changes the time interval at which the sprites are drawn
        if (nSpeed%8 == 0) {
            isExploded = isExploded(j);
            j++;
        }
        spriteBatch.end();
    }

    //Check if index is at the end of the animation
    public boolean isExploded(int j) {
        if (j == 15) {
            return true;
        }
        return false;
    }
}
