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

    public WorldController() {
        init();
    }

    public void init() {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
        b2World = new World(new Vector2(0, -9.81f), true);
        player = new Player(new Vector2(0, 0), b2World);
    }

    public void update(float deltaTime) {
        cameraHelper.update(deltaTime);
        b2World.step(1 / 60f, 3, 8); //timeStep, velocityIteration, positionIteration
        player.update(deltaTime);
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
        }
        return true;
    }
}
