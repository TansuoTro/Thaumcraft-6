/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.projectile.EntityThrowable;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.common.entities.projectile.EntityFocusProjectile;
/*    */ 
/*    */ 
/*    */ public class RenderNoProjectile
/*    */   extends Render
/*    */ {
/*    */   public RenderNoProjectile(RenderManager rm) {
/* 16 */     super(rm);
/* 17 */     this.field_76989_e = 0.1F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(EntityThrowable tg, double x, double y, double z, float fq) {
/* 23 */     if (tg instanceof EntityFocusProjectile) {
/* 24 */       EntityFocusProjectile gp = (EntityFocusProjectile)tg;
/* 25 */       float qq = fq - gp.lastRenderTick;
/* 26 */       if (qq < 0.0F) qq++; 
/* 27 */       if (qq > 0.2D) gp.renderParticle(fq);
/*    */     
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt((EntityThrowable)entity, d, d1, d2, f1); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderNoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */