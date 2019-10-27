/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelEldritchGolem;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchGolem
/*    */   extends RenderLiving
/*    */ {
/*    */   protected ModelEldritchGolem modelMain;
/* 19 */   private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/entity/eldritch_golem.png");
/*    */ 
/*    */   
/*    */   public RenderEldritchGolem(RenderManager rm, ModelEldritchGolem par1ModelBiped, float par2) {
/* 23 */     super(rm, par1ModelBiped, par2);
/* 24 */     this.modelMain = par1ModelBiped;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   protected ResourceLocation func_110775_a(Entity entity) { return skin; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) { GL11.glScalef(1.7F, 1.7F, 1.7F); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving golem, double par2, double par4, double par6, float par8, float par9) {
/* 41 */     GL11.glEnable(3042);
/* 42 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 43 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 45 */     super.func_76986_a(golem, par2, par4, par6, par8, par9);
/*    */     
/* 47 */     GL11.glDisable(3042);
/* 48 */     GL11.glAlphaFunc(516, 0.1F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) { doRenderLiving(par1Entity, par2, par4, par6, par8, par9); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderEldritchGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */