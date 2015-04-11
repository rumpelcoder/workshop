package at.libgdx.workshop;

import at.libgdx.workshop.game.WorldController;
import at.libgdx.workshop.game.WorldRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class JumpAndRoll extends ApplicationAdapter {


    private WorldController worldController;
    private WorldRenderer worldRenderer;

	@Override
	public void create () {
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);
    }

    /**
     * the game loop
     */
    @Override
	public void render () {
        //update game world
        worldController.update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
    }
}
