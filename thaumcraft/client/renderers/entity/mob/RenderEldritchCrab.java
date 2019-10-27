/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.renderers.models.entity.ModelEldritchCrab;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchCrab
/*    */   extends RenderLiving {
/* 17 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/entity/crab.png");
/*    */ 
/*    */ 
/*    */   
/* 21 */   public RenderEldritchCrab(RenderManager renderManager) { super(renderManager, new ModelEldritchCrab(), 0.5F); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   protected ResourceLocation func_110775_a(Entity entity) { return skin; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void renderCrab(EntityLiving crab, double par2, double par4, double par6, float par8, float par9) { super.func_76986_a(crab, par2, par4, par6, par8, par9); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) { renderCrab(par1Entity, par2, par4, par6, par8, par9); }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_77041_b(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
/* 42 */     GlStateManager.func_179152_a(0.8F, 0.8F, 0.8F);
/* 43 */     super.func_77041_b(entitylivingbaseIn, partialTickTime);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderEldritchCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */