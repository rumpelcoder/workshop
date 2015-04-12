package at.libgdx.workshop.game;

import at.libgdx.workshop.game.entities.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import static at.libgdx.workshop.game.entities.BasicBlock.BasicBlockType.*;

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
        gameObjects.add(new JumpPad(new Vector2(4, 0), b2World));
        gameObjects.add(new Spikes(new Vector2(3, 0), b2World));
        for (int i = -5; i < 5; i++) {
            gameObjects.add(new BasicBlock(new Vector2(i, -1), BlockGrass, b2World));
            gameObjects.add(new BasicBlock(new Vector2(i, -2), BlockDirt, b2World));
            gameObjects.add(new BasicBlock(new Vector2(i, -3), BlockDirt, b2World));
            gameObjects.add(new BasicBlock(new Vector2(i, -4), BlockDirt, b2World));
        }

        gameObjects.add(new BasicBlock(new Vector2(5, 0), BlockSlope, b2World));
        gameObjects.add(new BasicBlock(new Vector2(5, -1), BlockSlopeStart, b2World));
        gameObjects.add(new BasicBlock(new Vector2(6, 0), BlockGrass, b2World));
        gameObjects.add(new BasicBlock(new Vector2(6, -1), BlockDirt, b2World));
        gameObjects.add(new BasicBlock(new Vector2(7, 0), BlockSlope, b2World, true));
        gameObjects.add(new BasicBlock(new Vector2(7, -1), BlockSlopeStart, b2World, true));

        Coin c = new Coin(new Vector2(2.2f, 0.1f));
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
