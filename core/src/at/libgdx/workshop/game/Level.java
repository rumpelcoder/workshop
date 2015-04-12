package at.libgdx.workshop.game;

import at.libgdx.workshop.game.entities.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 11.04.2015.
 */
public class Level {
    List<GameObject> gameObjects;
    private List<Coin> coins;
    private World b2World;

    public Level(World b2World) {
        this.b2World = b2World;
        init();
    }

    private void init() {
        gameObjects = new ArrayList<GameObject>();
        coins = new ArrayList<Coin>();

        gameObjects.add(new DecoBlock(new Vector2(-2, 1), DecoBlock.DecoBlockType.Bush));
        gameObjects.add(new DecoBlock(new Vector2(3, 1), DecoBlock.DecoBlockType.Cactus));
        gameObjects.add(new DecoBlock(new Vector2(7, 3), DecoBlock.DecoBlockType.Cloud));
        gameObjects.add(new DecoBlock(new Vector2(1, 3), DecoBlock.DecoBlockType.Cloud));
        gameObjects.add(new DecoBlock(new Vector2(4, 2.6f), DecoBlock.DecoBlockType.Cloud));
        gameObjects.add(new DecoBlock(new Vector2(11, 3f), DecoBlock.DecoBlockType.Cloud));
        gameObjects.add(new DecoBlock(new Vector2(5, 1), DecoBlock.DecoBlockType.Plant));
        gameObjects.add(new DecoBlock(new Vector2(9, 2), DecoBlock.DecoBlockType.Rock));

        for (int i = -5; i < 20; i++) {
            if (i == 8) {
                gameObjects.add(new BasicBlock(new Vector2(i, 0), BasicBlock.BasicBlockType.BlockSlopeStart, b2World));
            } else if (i == 9) {
                gameObjects.add(new BasicBlock(new Vector2(i, 0), BasicBlock.BasicBlockType.BlockDirt, b2World));
            } else if (i == 10) {
                gameObjects.add(new BasicBlock(new Vector2(i, 0), BasicBlock.BasicBlockType.BlockSlopeStart, b2World, true));
            } else {
                gameObjects.add(new BasicBlock(new Vector2(i, 0), BasicBlock.BasicBlockType.BlockGrass, b2World));
            }

            gameObjects.add(new BasicBlock(new Vector2(i, -1), BasicBlock.BasicBlockType.BlockDirt, b2World));
            gameObjects.add(new BasicBlock(new Vector2(i, -2), BasicBlock.BasicBlockType.BlockDirt, b2World));
            gameObjects.add(new BasicBlock(new Vector2(i, -3), BasicBlock.BasicBlockType.BlockDirt, b2World));

        }

        gameObjects.add(new BasicBlock(new Vector2(9, 1), BasicBlock.BasicBlockType.BlockGrass, b2World));
        gameObjects.add(new BasicBlock(new Vector2(8, 1), BasicBlock.BasicBlockType.BlockSlope, b2World));
        gameObjects.add(new BasicBlock(new Vector2(10, 1), BasicBlock.BasicBlockType.BlockSlope, b2World, true));
        gameObjects.add(new JumpPad(new Vector2(1, 1f), b2World));
        gameObjects.add(new Spikes(new Vector2(2f, 1f), b2World));
        Coin c = new Coin(new Vector2(2.2f, 2f));
        gameObjects.add(c);
        coins.add(c);
        c = new Coin(new Vector2(10, 2f));
        gameObjects.add(c);
        coins.add(c);
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

    public List<Coin> getCoins() {
        return coins;
    }
}
