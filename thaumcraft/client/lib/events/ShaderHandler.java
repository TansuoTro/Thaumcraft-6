/*     */ package thaumcraft.client.lib.events;
/*     */ 
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.shader.ShaderGroup;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import thaumcraft.common.lib.potions.PotionBlurredVision;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShaderHandler
/*     */ {
/*  19 */   public static int warpVignette = 0;
/*     */   
/*     */   public static final int SHADER_DESAT = 0;
/*     */   
/*     */   public static final int SHADER_BLUR = 1;
/*     */   public static final int SHADER_HUNGER = 2;
/*     */   public static final int SHADER_SUNSCORNED = 3;
/*  26 */   public static ResourceLocation[] shader_resources = { new ResourceLocation("shaders/post/desaturatetc.json"), new ResourceLocation("shaders/post/blurtc.json"), new ResourceLocation("shaders/post/hunger.json"), new ResourceLocation("shaders/post/sunscorned.json") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkShaders(TickEvent.PlayerTickEvent event, Minecraft mc) {
/*  33 */     if (event.player.func_70644_a(PotionDeathGaze.instance)) {
/*  34 */       warpVignette = 10;
/*  35 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(0))) {
/*     */         
/*  37 */         try { setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[0]), 0); }
/*     */         
/*  39 */         catch (JsonSyntaxException jsonSyntaxException) {  }
/*  40 */         catch (IOException iOException) {}
/*     */       
/*     */       }
/*     */     }
/*  44 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(0))) {
/*  45 */       deactivateShader(0);
/*     */     } 
/*     */ 
/*     */     
/*  49 */     if (event.player.func_70644_a(PotionBlurredVision.instance)) {
/*  50 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(1))) {
/*     */         
/*  52 */         try { setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[1]), 1); }
/*     */         
/*  54 */         catch (JsonSyntaxException jsonSyntaxException) {  }
/*  55 */         catch (IOException iOException) {}
/*     */       
/*     */       }
/*     */     }
/*  59 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(1))) {
/*  60 */       deactivateShader(1);
/*     */     } 
/*     */ 
/*     */     
/*  64 */     if (event.player.func_70644_a(PotionUnnaturalHunger.instance)) {
/*  65 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(2))) {
/*     */         
/*  67 */         try { setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[2]), 2); }
/*     */         
/*  69 */         catch (JsonSyntaxException jsonSyntaxException) {  }
/*  70 */         catch (IOException iOException) {}
/*     */       
/*     */       }
/*     */     }
/*  74 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(2))) {
/*  75 */       deactivateShader(2);
/*     */     } 
/*     */ 
/*     */     
/*  79 */     if (event.player.func_70644_a(PotionSunScorned.instance)) {
/*  80 */       if (!RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(3))) {
/*     */         
/*  82 */         try { setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shader_resources[3]), 3); }
/*     */         
/*  84 */         catch (JsonSyntaxException jsonSyntaxException) {  }
/*  85 */         catch (IOException iOException) {}
/*     */       
/*     */       }
/*     */     }
/*  89 */     else if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(3))) {
/*  90 */       deactivateShader(3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setShader(ShaderGroup target, int shaderId) {
/*  96 */     if (OpenGlHelper.field_148824_g) {
/*     */       
/*  98 */       if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(shaderId))) {
/*     */         
/* 100 */         ((ShaderGroup)RenderEventHandler.shaderGroups.get(Integer.valueOf(shaderId))).func_148021_a();
/* 101 */         RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 106 */         if (target == null) {
/* 107 */           deactivateShader(shaderId);
/*     */         } else {
/* 109 */           RenderEventHandler.resetShaders = true;
/* 110 */           RenderEventHandler.shaderGroups.put(Integer.valueOf(shaderId), target);
/*     */         }
/*     */       
/* 113 */       } catch (Exception ioexception) {
/*     */         
/* 115 */         RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deactivateShader(int shaderId) {
/* 122 */     if (RenderEventHandler.shaderGroups.containsKey(Integer.valueOf(shaderId)))
/*     */     {
/* 124 */       ((ShaderGroup)RenderEventHandler.shaderGroups.get(Integer.valueOf(shaderId))).func_148021_a();
/*     */     }
/*     */     
/* 127 */     RenderEventHandler.shaderGroups.remove(Integer.valueOf(shaderId));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\events\ShaderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */