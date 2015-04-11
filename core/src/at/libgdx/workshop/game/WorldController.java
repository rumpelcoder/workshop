package at.libgdx.workshop.game;

import at.libgdx.workshop.utils.CameraHelper;

/**
 * Created by Lukas on 11.04.2015.
 */
public class WorldController {
    public CameraHelper cameraHelper;

    public WorldController() {
        init();
    }

    public void init() {
        cameraHelper = new CameraHelper();
    }

    public void update(float deltaTime) {
        cameraHelper.update(deltaTime);
    }
}
