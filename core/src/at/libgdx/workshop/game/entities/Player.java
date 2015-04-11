package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lukas on 11.04.2015.
 */
public class Player extends GameObject {
    private TextureRegion texture;

    public Player(Vector2 position) {
        this.position = position;
        init();
    }


    private void init() {
        dimension.set(0.2f, 0.2f);
        origin.x = dimension.x / 2;
        origin.y = dimension.y / 2;
        texture = assets.findRegion("player");
    }


    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,
                position.x - dimension.x / 2, position.y - dimension.y / 2,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation);
    }


}
