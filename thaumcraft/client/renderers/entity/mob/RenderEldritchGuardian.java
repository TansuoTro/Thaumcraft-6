/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.renderers.models.entity.ModelEldritchGuardian;
/*    */ import thaumcraft.common.config.ModConfig;
/*    */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderEldritchGuardian
/*    */   extends RenderLiving
/*    */ {
/*    */   protected ModelEldritchGuardian modelMain;
/* 22 */   private static final ResourceLocation[] skin = { new ResourceLocation("thaumcraft", "textures/entity/eldritch_guardian.png"), new ResourceLocation("thaumcraft", "textures/entity/eldritch_warden.png") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderEldritchGuardian(RenderManager rm, ModelEldritchGuardian par1ModelBiped, float par2) {
/* 29 */     super(rm, par1ModelBiped, par2);
/* 30 */     this.modelMain = par1ModelBiped;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   protected ResourceLocation func_110775_a(Entity entity) { return (entity instanceof EntityEldritchWarden) ? skin[1] : skin[0]; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRenderLiving(EntityLiving guardian, double par2, double par4, double par6, float par8, float par9) {
/* 41 */     GL11.glEnable(3042);
/* 42 */     GL11.glAlphaFunc(516, 0.003921569F);
/* 43 */     GL11.glBlendFunc(770, 771);
/* 44 */     float base = 1.0F;
/* 45 */     double d3 = par4;
/*    */     
/* 47 */     if (guardian instanceof EntityEldritchWarden) {
/* 48 */       d3 -= (guardian.field_70131_O * ((EntityEldritchWarden)guardian).getSpawnTimer() / 150.0F);
/*    */     } else {
/* 50 */       Entity e = Minecraft.func_71410_x().func_175606_aa();
/* 51 */       float d6 = (e.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) ? 576.0F : 1024.0F;
/* 52 */       float d7 = 256.0F;
/* 53 */       if (guardian.field_70170_p != null && guardian.field_70170_p.field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.dimensionOuterId) {
/* 54 */         base = 1.0F;
/*    */       } else {
/* 56 */         double d8 = guardian.func_70092_e(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/* 57 */         if (d8 < 256.0D) {
/* 58 */           base = 0.6F;
/*    */         } else {
/* 60 */           base = (float)(1.0D - Math.min((d6 - d7), d8 - d7) / (d6 - d7)) * 0.6F;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 65 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, base);
/*    */     
/* 67 */     super.func_76986_a(guardian, par2, d3, par6, par8, par9);
/*    */     
/* 69 */     GL11.glDisable(3042);
/* 70 */     GL11.glAlphaFunc(516, 0.1F);
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
/* 83 */   public void func_76986_a(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) { doRenderLiving(par1Entity, par2, par4, par6, par8, par9); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderEldritchGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */