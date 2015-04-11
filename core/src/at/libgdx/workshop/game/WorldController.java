package at.libgdx.workshop.game;

import at.libgdx.workshop.utils.CameraHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by Lukas on 11.04.2015.
 */
public class WorldController extends InputAdapter {
    public CameraHelper cameraHelper;

    public WorldController() {
        init();
    }

    public void init() {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
    }

    public void update(float deltaTime) {
        cameraHelper.update(deltaTime);
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
