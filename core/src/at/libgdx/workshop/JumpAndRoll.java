package at.libgdx.workshop;

import at.libgdx.workshop.game.WorldController;
import at.libgdx.workshop.game.WorldRenderer;
import com.badlogic.gdx.ApplicationAdapter;

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
	}
}
