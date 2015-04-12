package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lukas on 28.03.2015.
 */
public class JumpPad extends GameObject {

    private static final float JUMP_FORCE = 10f;
    private static float UP_TIME = 0.5f;
    private TextureRegion textureDown;
    private TextureRegion textureUp;
    private boolean triggered;
    private World b2World;
    private Body b2Body;
    private float startTime = 0;

    public JumpPad(Vector2 position, World b2World) {
        super();
        this.position = position;
        this.dimension = new Vector2(0.5f, 0.2f);
        this.b2World = b2World;
        init();
    }

    private void init() {
        triggered = false;
        textureDown = assets.findRegion("springboardDown");
        textureUp = assets.findRegion("springboardUp");
        startTime = 0;
        initPhysics();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        startTime += deltaTime;
        if (startTime > UP_TIME) {
            triggered = false;
        }
        checkBodies();
    }

    private void checkBodies() {
        float border = dimension.x / 2.5f;
        float lowerX = position.x + border;
        float lowerY = position.y + dimension.y / 2f;
        b2World.QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                if (fixture.getBody().getUserData() instanceof Player) {
                    trigger();
                    fixture.getBody().setLinearVelocity(b2Body.getLinearVelocity().x, 0);
                    fixture.getBody().applyForceToCenter(0, JUMP_FORCE * 1.5f, true);
                }
                return false;
            }
        }, lowerX, lowerY, lowerX + dimension.x - 2 * border, lowerY + 0.1f);
    }

    private void initPhysics() {
        BodyDef jumpPad = new BodyDef();
        jumpPad.position.set(new Vector2(position.x + dimension.x / 2f, position.y + dimension.y / 2f));
        jumpPad.type = BodyDef.BodyType.StaticBody;
        b2Body = b2World.createBody(jumpPad);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(dimension.x / 2f, dimension.y / 2f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 0.5f;
        b2Body.createFixture(fixtureDef);
        polygonShape.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (triggered) {
            batch.draw(textureUp, position.x, position.y, dimension.x, dimension.y * 1.5f);
        } else {
            batch.draw(textureDown, position.x, position.y, dimension.x, dimension.y);
        }

    }


    public void trigger() {
        triggered = true;
        startTime = 0;
    }

    public boolean isTriggered() {
        return triggered;
    }
}
