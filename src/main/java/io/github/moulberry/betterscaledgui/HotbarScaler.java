package io.github.moulberry.betterscaledgui;

import java.lang.annotation.ElementType;

import javafx.scene.transform.Scale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.GlStateManager;

public class HotbarScaler {

    @SubscribeEvent
    public void onRenderHotbarPre(RenderGameOverlayEvent.Pre event) {
    if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR && ScaledResolutionOverride.getHotbarScaleOverride() > 0) {
        //float scale = ScaledResolutionOverride.getHotbarScaleFactor();

        //GlStateManager.pushMatrix();

        //ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        //float centerX = sr.getScaledWidth() / 2.0F;
        //float y = sr.getScaledHeight() - 22.0F + ScaledResolutionOverride.offsety;

        // translate to hotbar center, scale, then translate back
        //GlStateManager.translate(centerX, y, 0);
        //GlStateManager.scale(scale, scale, scale);
        //GlStateManager.translate(-centerX, -y, 0);
        scaleElement(event, RenderGameOverlayEvent.ElementType.HOTBAR);
        }
    

        if (event.type == RenderGameOverlayEvent.ElementType.HEALTH && ScaledResolutionOverride.getHotbarScaleOverride() > 0) {
            scaleElement(event, RenderGameOverlayEvent.ElementType.HEALTH);
        }

        if (event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE && ScaledResolutionOverride.getHotbarScaleOverride() > 0) {
            scaleElement(event, RenderGameOverlayEvent.ElementType.EXPERIENCE);
        }

        if (event.type == RenderGameOverlayEvent.ElementType.FOOD && ScaledResolutionOverride.getHotbarScaleOverride() > 0) {
            scaleElement(event, RenderGameOverlayEvent.ElementType.FOOD);
        }
    }
    
    private void scaleElement(RenderGameOverlayEvent.Pre event, RenderGameOverlayEvent.ElementType type) {
        float scale = ScaledResolutionOverride.getHotbarScaleFactor();

        // Apply different vertical offsets for different elements (optional)
        float yOffset = 0.0F;  // You can adjust this value to move elements upwards

        // Perform the translation and scaling
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        float centerX = sr.getScaledWidth() / 2.0F;
        float y = 0;

        switch (type) {
            case HOTBAR:
                y = sr.getScaledHeight() + ScaledResolutionOverride.offsety;  // Adjust the hotbar Y position
                break;
            case HEALTH:
                y = sr.getScaledHeight() + ScaledResolutionOverride.offsety;;  // Adjust health bar position
                break;
            case EXPERIENCE:
                y = sr.getScaledHeight() + ScaledResolutionOverride.offsety;  // Adjust experience bar position
                break;
            case FOOD:
                y = sr.getScaledHeight() + ScaledResolutionOverride.offsety;  // Adjust hunger bar position
                break;
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(centerX, y, 0);
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.translate(-centerX, -y, 0);
    }

    @SubscribeEvent
    public void onRenderHotbarPost(RenderGameOverlayEvent.Post event) {
        if ((event.type == RenderGameOverlayEvent.ElementType.HOTBAR || 
         event.type == RenderGameOverlayEvent.ElementType.HEALTH || 
         event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE || 
         event.type == RenderGameOverlayEvent.ElementType.FOOD) && ScaledResolutionOverride.getHotbarScaleOverride() > 0) {
        GlStateManager.popMatrix();
        }
    }
    
}
