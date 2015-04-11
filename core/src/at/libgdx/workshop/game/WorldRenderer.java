package at.libgdx.workshop.game;

import at.libgdx.workshop.utils.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Lukas on 11.04.2015.
 */
public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
    }

    public void render() {

    }

    public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width; //calculate aspect ratio
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
