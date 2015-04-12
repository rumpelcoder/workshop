package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lukas on 29.03.2015.
 */
public class DecoBlock extends GameObject {

    DecoBlockType type;
    private TextureRegion textureHanging;

    public DecoBlock(Vector2 position, DecoBlockType type) {
        super();
        this.type = type;
        this.position = position;
        this.dimension = new Vector2(1f, 1f);
        init();
    }

    private void init() {
        switch (type) {
            case Rock:
                textureHanging = assets.findRegion("rock");
                break;
            case Cactus:
                textureHanging = assets.findRegion("cactus");
                break;
            case Cloud:
                textureHanging = assets.findRegion("cloud1");
                dimension = new Vector2(1, 0.5f);
                break;
            case Bush:
                textureHanging = assets.findRegion("bush");
                break;
            case Plant:
            default:
                textureHanging = assets.findRegion("plant");
                break;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(textureHanging, position.x, position.y, dimension.x, dimension.y);
    }

    public enum DecoBlockType {
        Rock, Cactus, Cloud, Bush, Plant
    }
}
