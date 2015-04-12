package at.libgdx.workshop.game;

import at.libgdx.workshop.game.entities.Coin;
import at.libgdx.workshop.game.entities.Player;
import at.libgdx.workshop.game.entities.Spikes;
import at.libgdx.workshop.utils.CameraHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Lukas on 11.04.2015.
 */
public class WorldController extends InputAdapter {
    public CameraHelper cameraHelper;
    public Player player;
    public long timeElapsed;
    int coinCount = 0;
    private World b2World;
    private Level level;
    private boolean debug = true;
    private boolean reset;

    public WorldController() {
        init();
    }

    public void init() {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
        b2World = new World(new Vector2(0, -9.81f), true);
        player = new Player(new Vector2(0.5f, 1.5f), b2World);
        cameraHelper.setTarget(player.getBody());
        level = new Level(b2World);
        b2World.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fA = contact.getFixtureA();
                Fixture fB = contact.getFixtureB();
                if (fB.getBody().getUserData() instanceof Player && fA.getBody().getUserData() instanceof Spikes ||
                        fA.getBody().getUserData() instanceof Player && fB.getBody().getUserData() instanceof Spikes) {
                    reset = true;
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public void update(float deltaTime) {
        timeElapsed += deltaTime * 1000;
        cameraHelper.update(deltaTime);
        b2World.step(1 / 60f, 3, 8); //timeStep, velocityIteration, positionIteration
        if (reset) {
            reset();
        }
        player.update(deltaTime);
        level.update(deltaTime);
        if (player.getBody().getPosition().y < -3) {
            reset = true;
        }
        testCoins();
    }

    private void reset() {
        reset = false;
        player.getBody().setTransform(new Vector2(0.5f, 1.5f), 0);
        player.getBody().setLinearVelocity(new Vector2(0, 0));
        player.getBody().setAngularVelocity(0);
        timeElapsed = 0;
        coinCount = 0;
    }

    private void testCoins() {
        Rectangle playerRect = new Rectangle();
        Rectangle coinRect = new Rectangle();
        playerRect.set(player.position.x, player.position.y, player.dimension.x, player.dimension.y);
        for (Coin coin : level.getCoins()) {
            if (coin.isCollected()) {
                continue;
            }
            coinRect.set(coin.position.x, coin.position.y, coin.dimension.x, coin.dimension.y);
            if (!playerRect.overlaps(coinRect)) {
                continue;
            }
            coin.setCollected(true);
            coinCount += 1;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.PLUS:
                cameraHelper.addZoom(-0.2f);
                break;
            case Input.Keys.MINUS:
                cameraHelper.addZoom(0.2f);
                break;
            case Input.Keys.LEFT:
                player.setLeft(true);
                break;
            case Input.Keys.RIGHT:
                player.setRight(true);
                break;
            case Input.Keys.D:
                debug = !debug;
                break;
            case Input.Keys.SPACE:
            case Input.Keys.UP:
                player.jump();
                break;
            case Input.Keys.R:
                reset = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                player.setLeft(false);
                break;
            case Input.Keys.RIGHT:
                player.setRight(false);
                break;
        }
        return false;
    }

    public boolean isDebug() {
        return debug;
    }

    public World getB2World() {
        return b2World;
    }

    public Level getLevel() {
        return level;
    }
}
