package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lukas on 11.04.2015.
 */
public class BasicBlock extends GameObject {
    private TextureRegion texture;

    public BasicBlock(Vector2 position, BasicBlockType type) {
        super();
        this.position = position;
        this.dimension = new Vector2(1, 1);
        switch (type) {
            case BlockDirt:
                texture = assets.findRegion("grassCenter");
                break;
            case BlockGrass:
                texture = assets.findRegion("grassMid");
                break;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, dimension.x, dimension.y);
    }

    public enum BasicBlockType {
        BlockGrass, BlockDirt
    }
}
