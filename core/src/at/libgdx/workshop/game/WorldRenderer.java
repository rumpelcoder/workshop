package at.libgdx.workshop.game;

import at.libgdx.workshop.game.entities.BasicBlock;
import at.libgdx.workshop.utils.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import static at.libgdx.workshop.game.entities.BasicBlock.BasicBlockType;

/**
 * Created by Lukas on 11.04.2015.
 */
public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    private BasicBlock block;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
        block = new BasicBlock(new Vector2(0, 0), BasicBlockType.BlockGrass);
    }

    public void render() {
        worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        block.render(batch);
        batch.end();

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
