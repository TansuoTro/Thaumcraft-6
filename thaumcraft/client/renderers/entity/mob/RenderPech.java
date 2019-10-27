/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelPech;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderPech
/*    */   extends RenderLiving
/*    */ {
/*    */   protected ModelPech modelMain;
/*    */   protected ModelPech modelOverlay;
/* 20 */   private static final ResourceLocation[] skin = { new ResourceLocation("thaumcraft", "textures/entity/pech_forage.png"), new ResourceLocation("thaumcraft", "textures/entity/pech_thaum.png"), new ResourceLocation("thaumcraft", "textures/entity/pech_stalker.png") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderPech(RenderManager rm, ModelPech par1ModelBiped, float par2) {
/* 28 */     super(rm, par1ModelBiped, par2);
/* 29 */     this.modelMain = par1ModelBiped;
/* 30 */     this.modelOverlay = new ModelPech();
/* 31 */     func_177094_a(new LayerHeldItemPech(this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 36 */   protected ResourceLocation func_110775_a(Entity entity) { return skin[((EntityPech)entity).getPechType()]; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
/* 41 */     float f2 = 1.0F;
/* 42 */     GL11.glColor3f(f2, f2, f2);
/* 43 */     double d3 = par4 - par1EntityLiving.func_70033_W();
/*    */     
/* 45 */     if (par1EntityLiving.func_70093_af())
/*    */     {
/* 47 */       d3 -= 0.125D;
/*    */     }
/*    */     
/* 50 */     super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) { doRenderLiving(par1Entity, par2, par4, par6, par8, par9); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */