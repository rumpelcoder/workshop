package at.libgdx.workshop.desktop;

import at.libgdx.workshop.JumpAndRoll;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DesktopLauncher {
    private static boolean rebuildAtlas = true;

    public static void main(String[] arg) {
        if (rebuildAtlas) {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            settings.edgePadding = true;
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.debug = false;
            settings.duplicatePadding = true; //avoid inner padding
            TexturePacker.process(settings, "../../assets_raw", "images", "assets");
        }
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new JumpAndRoll(), config);
	}
}
