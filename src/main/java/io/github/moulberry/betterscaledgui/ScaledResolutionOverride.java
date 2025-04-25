package io.github.moulberry.betterscaledgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;

public class ScaledResolutionOverride {

    public static float offsety = 0;

    //What the current GUI is using. This is updated whenever a new GUI is opened
    // this is to ensure that the current scale and the GuiScreen#width/height fields
    // are consistent
    private static int currentScaleOverride = -1;

    //What we want the GUI scale to be, currentScaleOverride is set to this by
    // GuiScreen#setWorldAndRenderer
    private static int desiredScaleOverride = -1;

    private static int hotbarScaleOverride = -1;

    public static void setHotbarScaleOverride(int override) {
        hotbarScaleOverride = override;
    }

    public static int getHotbarScaleOverride() {
        return hotbarScaleOverride;
    }

    public static float getHotbarScaleFactor() {
        int val = getHotbarScaleOverride(); // 1-4
    
        switch (val) {
            case 1: 
                offsety = 0F;
                return 0.75F;  // small
            case 2: 
                offsety = 0F;
                return 1.0F;   // normal
            case 3: 
                offsety = 0F;
                return 1.5F;  // large
            case 4: // auto → match GUI scale
                Minecraft mc = Minecraft.getMinecraft();
                ScaledResolution sr = new ScaledResolution(mc);
                int guiScale = mc.gameSettings.guiScale;
                if (guiScale == 0) guiScale = 1000;
                int scale = 1;
                while (scale < guiScale && mc.displayWidth / (scale + 1) >= 320 && mc.displayHeight / (scale + 1) >= 240) {
                    ++scale;
                }
                return scale / 2.0F; // rough mapping
            default: 
                offsety = 0F;
                return 1.0F;
        }
    }

    //Scaling override for ScaledResolution. Should only be set to currentScaleOverride
    // during rendering
    private static int scaleOverride = -1;

    public static int getScaleOverride() {
        return scaleOverride;
    }

    public static void setScaleOverride(int scaleOverride) {
        ScaledResolutionOverride.scaleOverride = scaleOverride;
    }

    public static int getCurrentScaleOverride() {
        return currentScaleOverride;
    }

    public static void setCurrentScaleOverride(int currentScaleOverride) {
        ScaledResolutionOverride.currentScaleOverride = currentScaleOverride;
    }

    public static int getDesiredScaleOverride() {
        return desiredScaleOverride;
    }

    public static void setDesiredScaleOverride(int desiredScaleOverride) {
        ScaledResolutionOverride.desiredScaleOverride = desiredScaleOverride;
    }

}
