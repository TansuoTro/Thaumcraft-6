/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.fx.ParticleEngine;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.entities.monster.EntityWisp;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderWisp
/*    */   extends Render
/*    */ {
/*    */   public RenderWisp(RenderManager rm) {
/* 21 */     super(rm);
/* 22 */     this.field_76989_e = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
/* 27 */     if (((EntityLiving)entity).func_110143_aJ() <= 0.0F)
/*    */       return; 
/* 29 */     double xx = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * pticks;
/* 30 */     double yy = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * pticks;
/* 31 */     double zz = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * pticks;
/*    */     
/* 33 */     int color = 0;
/*    */     
/* 35 */     if (Aspect.getAspect(((EntityWisp)entity).getType()) != null) {
/* 36 */       color = Aspect.getAspect(((EntityWisp)entity).getType()).getColor();
/*    */     }
/*    */     
/* 39 */     GL11.glPushMatrix();
/* 40 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 41 */     GL11.glDepthMask(false);
/*    */     
/* 43 */     func_110776_a(ParticleEngine.particleTexture);
/*    */     
/* 45 */     UtilsFX.renderFacingQuad(xx, yy, zz, 64, 64, 512 + entity.field_70173_aa % 16, 0.4F, 16777215, 1.0F, 1, pticks);
/*    */ 
/*    */ 
/*    */     
/* 49 */     UtilsFX.renderFacingQuad(xx, yy, zz, 64, 64, 320 + entity.field_70173_aa % 16, 0.75F, 16777215, 0.25F, 1, pticks);
/*    */ 
/*    */ 
/*    */     
/* 53 */     func_110776_a(UtilsFX.nodeTexture);
/*    */     
/* 55 */     UtilsFX.renderFacingQuad(xx, yy, zz, 32, 32, 800 + entity.field_70173_aa % 16, 0.75F, color, 0.5F, 1, pticks);
/*    */ 
/*    */ 
/*    */     
/* 59 */     GL11.glDepthMask(true);
/* 60 */     GL11.glAlphaFunc(516, 0.1F);
/* 61 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt(entity, d, d1, d2, f, f1); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderWisp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */