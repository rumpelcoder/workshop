package at.libgdx.workshop.game;

import at.libgdx.workshop.game.entities.Player;
import at.libgdx.workshop.utils.CameraHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Lukas on 11.04.2015.
 */
public class WorldController extends InputAdapter {
    public CameraHelper cameraHelper;
    public Player player;
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
    }

    public void update(float deltaTime) {
        cameraHelper.update(deltaTime);
        b2World.step(1 / 60f, 3, 8); //timeStep, velocityIteration, positionIteration
        if (reset) {
            reset();
        }
        player.update(deltaTime);
        level.update(deltaTime);
    }

    private void reset() {
        reset = false;
        player.getBody().setTransform(new Vector2(0.5f, 1.5f), 0);
        player.getBody().setLinearVelocity(new Vector2(0, 0));
        player.getBody().setAngularVelocity(0);
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
