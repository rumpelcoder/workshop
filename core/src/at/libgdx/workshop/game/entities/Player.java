package at.libgdx.workshop.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lukas on 11.04.2015.
 */
public class Player extends GameObject {
    public static final float JUMP_FORCE = 9f;
    private static final float ACCELERATION = 0.5f;
    private static final float MAX_SPEED = 3f;
    private TextureRegion texture;
    private World b2World;
    private Body b2Body;
    private boolean left;
    private boolean right;
    private boolean touchingGround;
    private Sound sound;

    public Player(Vector2 position, World b2World) {
        super();
        this.b2World = b2World;
        this.position = position;
        init();
    }


    private void init() {
        dimension.set(0.2f, 0.2f);
        origin.x = dimension.x / 2;
        origin.y = dimension.y / 2;
        texture = assets.findRegion("player");
        sound = Gdx.audio.newSound(Gdx.files.internal("bounce.mp3"));
        initPhysics();
    }

    private void initPhysics() {
        //create body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);

        //create body in world
        b2Body = b2World.createBody(bodyDef);

        //create shape
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(dimension.x / 2);

        //create fixture to attach shape to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0;

        b2Body.createFixture(fixtureDef);
        b2Body.setLinearDamping(1f);
        b2Body.setBullet(true);

        circleShape.dispose(); //clean up!!
        b2Body.setUserData(this);
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


    public void update(float deltaTime) {
        touchingGround = false;
        move();
        position = b2Body.getPosition();
        rotation = b2Body.getAngle() * MathUtils.radiansToDegrees;
    }

    public void move() {
        Vector2 toApply = new Vector2();
        if (left) {
            toApply.x = -ACCELERATION;
        } else if (right) {
            toApply.x = ACCELERATION;
        }
        if ((b2Body.getLinearVelocity().x > MAX_SPEED && toApply.x > 0) || (b2Body.getLinearVelocity().x < -MAX_SPEED && toApply.x < 0)) {
            toApply.x = 0;
        }
        b2Body.applyForceToCenter(toApply, true);
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void jump() {
        testGround();
        if (touchingGround) {
            sound.play();
            b2Body.applyForceToCenter(0, JUMP_FORCE, true);
        }
    }

    public void testGround() {
        b2World.rayCast(new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                if (fixture != null) {
                    if (fraction < 0.8f) {
                        touchingGround = true;
                    }
                }
                return 0;
            }
        }, b2Body.getWorldCenter(), new Vector2(b2Body.getWorldCenter().x, b2Body.getWorldCenter().y - 0.2f));
    }

    public Body getBody() {
        return b2Body;
    }
}
