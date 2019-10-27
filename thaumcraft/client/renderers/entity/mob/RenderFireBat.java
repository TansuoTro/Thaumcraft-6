/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBat;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.entity.ModelFireBat;
/*     */ import thaumcraft.common.entities.monster.EntityFireBat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderFireBat
/*     */   extends RenderLiving
/*     */ {
/*     */   private int renderedBatSize;
/*     */   
/*     */   public RenderFireBat(RenderManager rm) {
/*  28 */     super(rm, new ModelFireBat(), 0.25F);
/*  29 */     this.renderedBatSize = ((ModelFireBat)this.field_77045_g).getBatSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82443_a(EntityFireBat par1EntityBat, double par2, double par4, double par6, float par8, float par9) {
/*  36 */     int var10 = ((ModelFireBat)this.field_77045_g).getBatSize();
/*     */     
/*  38 */     if (var10 != this.renderedBatSize) {
/*     */       
/*  40 */       this.renderedBatSize = var10;
/*  41 */       this.field_77045_g = new ModelBat();
/*     */     } 
/*     */     
/*  44 */     super.func_76986_a(par1EntityBat, par2, par4, par6, par8, par9);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected void func_82442_a(EntityFireBat par1EntityBat, float par2) { GL11.glScalef(0.35F, 0.35F, 0.35F); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected void func_82445_a(EntityFireBat par1EntityBat, double par2, double par4, double par6) { super.func_77039_a(par1EntityBat, par2, par4, par6); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82444_a(EntityFireBat par1EntityBat, float par2, float par3, float par4) {
/*  59 */     if (!par1EntityBat.getIsBatHanging()) {
/*     */       
/*  61 */       GL11.glTranslatef(0.0F, MathHelper.func_76134_b(par2 * 0.3F) * 0.1F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/*  65 */       GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*     */     } 
/*     */     
/*  68 */     super.func_77043_a(par1EntityBat, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) { func_82442_a((EntityFireBat)par1EntityLiving, par2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) { func_82444_a((EntityFireBat)par1EntityLiving, par2, par3, par4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   protected void func_77039_a(EntityLivingBase par1EntityLiving, double par2, double par4, double par6) { func_82445_a((EntityFireBat)par1EntityLiving, par2, par4, par6); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) { func_82443_a((EntityFireBat)par1EntityLiving, par2, par4, par6, par8, par9); }
/*     */ 
/*     */ 
/*     */   
/* 103 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/firebat.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   protected ResourceLocation func_110775_a(Entity entity) { return rl; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderFireBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */