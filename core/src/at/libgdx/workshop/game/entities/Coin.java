package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Coin extends GameObject {

    private static final int FRAME_COLS = 8;
    TextureRegion[] animationFrames;
    float startTime = 0;
    boolean collected = false;
    private Animation animation;

    public Coin(Vector2 position) {
        super();
        this.position = position;
        this.dimension = new Vector2(0.25f, 0.2f);
        init();
    }

    private void init() {
        TextureRegion t = assets.findRegion("coin_gold");
        TextureRegion[][] tmp = t.split(t.getRegionWidth() / FRAME_COLS, t.getRegionHeight());              // #8
        animationFrames = new TextureRegion[FRAME_COLS];
        int index = 0;
        for (int j = 0; j < FRAME_COLS; j++) {
            animationFrames[index++] = tmp[0][j];
        }
        startTime = 0;
        animation = new Animation(0.1f, animationFrames);
    }

    @Override
    public void render(SpriteBatch batch) {
        if (!collected) {
            batch.draw(animation.getKeyFrame(startTime, true), position.x, position.y, dimension.x, dimension.y);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        startTime += deltaTime;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean b) {
        collected = b;
    }
}
