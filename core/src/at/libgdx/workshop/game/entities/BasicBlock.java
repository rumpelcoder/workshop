package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lukas on 11.04.2015.
 */
public class BasicBlock extends GameObject {
    private final boolean flipped;
    private TextureRegion texture;
    private World b2World;
    private Body b2Body;
    private BasicBlockType type;
    private boolean physics = true;

    public BasicBlock(Vector2 position, BasicBlockType type, World b2World) {
        this(position, type, b2World, false);
    }

    public BasicBlock(Vector2 position, BasicBlockType type, World b2World, boolean flipped) {
        super();
        this.b2World = b2World;
        this.position = position;
        this.dimension = new Vector2(1, 1);
        this.type = type;
        this.flipped = flipped;
        switch (type) {
            case BlockDirt:
                texture = assets.findRegion("grassCenter");
                physics = false;
                break;
            case BlockGrass:
                texture = assets.findRegion("grassMid");
                break;
            case BlockSlope:
                texture = assets.findRegion("grassHillLeft");
                break;
            case BlockSlopeStart:
                texture = assets.findRegion("grassHillLeft2");
                physics = false;
                break;
        }
        if (flipped) {
            texture = new TextureRegion(texture); //clone texture for performing flip
            texture.flip(true, false);
        }
        if (physics) {
            initPhysics();
        }
    }

    private void initPhysics() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(position.x, position.y));
        bodyDef.type = BodyDef.BodyType.StaticBody;

        b2Body = b2World.createBody(bodyDef);

        EdgeShape edgeShape = new EdgeShape();
        if (type == BasicBlockType.BlockSlope) {
            if (flipped) {
                edgeShape.set(new Vector2(0, dimension.y), new Vector2(dimension.x, 0));
            } else {
                edgeShape.set(new Vector2(0, 0), dimension);
            }
        } else {
            edgeShape.set(new Vector2(0, dimension.y), dimension);
        }
        edgeShape.setVertex0(new Vector2(-dimension.x, dimension.y));
        edgeShape.setVertex3(new Vector2(2 * dimension.x, dimension.y));
        edgeShape.setHasVertex0(true);
        edgeShape.setHasVertex3(true);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = edgeShape;
        fixtureDef.friction = 1f;

        b2Body.createFixture(fixtureDef);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, dimension.x, dimension.y);
    }

    public enum BasicBlockType {
        BlockGrass, BlockDirt, BlockSlope, BlockSlopeStart
    }
}
