package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lukas on 11.04.2015.
 */
public class BasicBlock extends GameObject {
    private TextureRegion texture;
    private World b2World;
    private Body b2Body;

    public BasicBlock(Vector2 position, BasicBlockType type, World b2World) {
        super();
        this.b2World = b2World;
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
        initPhysics();
    }

    private void initPhysics() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(position.x, position.y));
        bodyDef.type = BodyDef.BodyType.StaticBody;

        b2Body = b2World.createBody(bodyDef);

        EdgeShape edgeShape = new EdgeShape();
        edgeShape.set(new Vector2(0, dimension.y), dimension);
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
        BlockGrass, BlockDirt
    }
}
