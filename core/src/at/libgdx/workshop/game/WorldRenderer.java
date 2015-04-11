package at.libgdx.workshop.game;

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
    }

    private void init() {

    }

    public void render() {

    }

    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
