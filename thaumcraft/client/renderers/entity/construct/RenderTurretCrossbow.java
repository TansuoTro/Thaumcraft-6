/*    */ package thaumcraft.client.renderers.entity.construct;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.client.renderers.models.entity.ModelCrossbow;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderTurretCrossbow
/*    */   extends RenderLiving
/*    */ {
/* 18 */   public RenderTurretCrossbow(RenderManager rm) { super(rm, new ModelCrossbow(), 0.5F); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float func_77040_d(EntityLivingBase e, float p_77040_2_) {
/* 23 */     ((EntityTurretCrossbow)e).loadProgressForRender = ((EntityTurretCrossbow)e).getLoadProgress(p_77040_2_);
/* 24 */     e.field_70761_aq = 0.0F;
/* 25 */     e.field_70760_ar = 0.0F;
/* 26 */     return super.func_77040_d(e, p_77040_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_77041_b(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
/* 31 */     entitylivingbaseIn.field_70761_aq = 0.0F;
/* 32 */     entitylivingbaseIn.field_70760_ar = 0.0F;
/* 33 */     super.func_77041_b(entitylivingbaseIn, partialTickTime);
/*    */   }
/*    */   
/* 36 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/crossbow.png");
/*    */ 
/*    */ 
/*    */   
/* 40 */   protected ResourceLocation func_110775_a(Entity entity) { return rl; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\construct\RenderTurretCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */