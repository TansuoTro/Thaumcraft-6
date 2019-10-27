/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderFocusCloud
/*    */   extends Render
/*    */ {
/*    */   private Random random;
/*    */   
/*    */   public RenderFocusCloud(RenderManager rm) {
/* 16 */     super(rm);
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.random = new Random();
/*    */     this.field_76989_e = 0.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt(entity, d, d1, d2, f, f1); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderFocusCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */