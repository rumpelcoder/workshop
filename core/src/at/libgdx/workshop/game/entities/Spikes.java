package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lukas on 29.03.2015.
 */
public class Spikes extends GameObject {

    private TextureRegion texture;
    private Body b2Body;
    private World b2World;

    public Spikes(Vector2 position, World b2World) {
        super();
        this.position = position;
        this.dimension = new Vector2(0.5f, 0.25f);
        this.b2World = b2World;
        init();
    }

    private void init() {
        texture = assets.findRegion("spikes");
        initPhysics();
    }

    private void initPhysics() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(position.x + dimension.x / 2f, position.y + dimension.y / 2f));
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        b2Body = b2World.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(dimension.x / 2f, dimension.y / 2f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        b2Body.createFixture(fixtureDef);
        b2Body.setUserData(this);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, dimension.x, dimension.y);
    }
}
