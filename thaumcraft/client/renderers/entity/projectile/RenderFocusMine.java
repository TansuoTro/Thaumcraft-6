/*    */ package thaumcraft.client.renderers.entity.projectile;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelGrappler;
/*    */ import thaumcraft.common.entities.projectile.EntityFocusMine;
/*    */ 
/*    */ public class RenderFocusMine
/*    */   extends Render {
/*    */   ResourceLocation beam;
/*    */   private ModelGrappler model;
/*    */   
/*    */   public RenderFocusMine(RenderManager rm) {
/* 20 */     super(rm);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.beam = new ResourceLocation("thaumcraft", "textures/entity/mine.png");
/*    */     this.field_76989_e = 0.0F;
/*    */     this.model = new ModelGrappler();
/*    */   }
/*    */   public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
/* 30 */     GL11.glPushMatrix();
/* 31 */     GL11.glEnable(3042);
/* 32 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 34 */     GL11.glTranslated(x, y, z);
/* 35 */     EntityFocusMine mine = (EntityFocusMine)entity;
/*    */     
/* 37 */     float f = (mine.counter + pticks) % 8.0F / 8.0F;
/*    */     
/* 39 */     int i = 61680;
/* 40 */     int j = i % 65536;
/* 41 */     int k = i / 65536;
/* 42 */     OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, j, k);
/*    */     
/* 44 */     GL11.glColor4f(1.0F, 1.0F - f, 1.0F - f, 1.0F);
/*    */     
/* 46 */     func_110776_a(this.beam);
/* 47 */     GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * pticks - 90.0F, 0.0F, 1.0F, 0.0F);
/* 48 */     GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * pticks, 0.0F, 0.0F, 1.0F);
/* 49 */     this.model.render();
/*    */     
/* 51 */     GL11.glDisable(3042);
/* 52 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 53 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) { renderEntityAt(entity, d, d1, d2, f, f1); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   protected ResourceLocation func_110775_a(Entity entity) { return TextureMap.field_110575_b; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\projectile\RenderFocusMine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */