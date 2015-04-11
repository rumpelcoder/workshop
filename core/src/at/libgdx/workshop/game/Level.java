package at.libgdx.workshop.game;

import at.libgdx.workshop.game.entities.BasicBlock;
import at.libgdx.workshop.game.entities.GameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import static at.libgdx.workshop.game.entities.BasicBlock.BasicBlockType.BlockGrass;

/**
 * Created by Lukas on 11.04.2015.
 */
public class Level {
    List<GameObject> gameObjects;
    private World b2World;

    public Level(World b2World) {
        this.b2World = b2World;
        init();
    }

    private void init() {
        gameObjects = new ArrayList<GameObject>();
        gameObjects.add(new BasicBlock(new Vector2(0, 0), BlockGrass, b2World));


    }

    public void render(SpriteBatch batch) {
        for (GameObject gameObject : gameObjects) {
            gameObject.render(batch);
        }
    }

    public void update(float deltaTime) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
        }
    }

    public void reset() {
        init();
    }
}
