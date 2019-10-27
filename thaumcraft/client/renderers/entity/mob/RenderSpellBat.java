/*     */ package thaumcraft.client.renderers.entity.mob;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.model.ModelBat;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.renderers.models.entity.ModelFireBat;
/*     */ import thaumcraft.common.entities.monster.EntitySpellBat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderSpellBat
/*     */   extends RenderLiving
/*     */ {
/*     */   private int renderedBatSize;
/*     */   
/*     */   public RenderSpellBat(RenderManager rm) {
/*  30 */     super(rm, new ModelFireBat(), 0.25F);
/*  31 */     this.renderedBatSize = ((ModelFireBat)this.field_77045_g).getBatSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82443_a(EntitySpellBat bat, double par2, double par4, double par6, float par8, float par9) {
/*  36 */     int var10 = ((ModelFireBat)this.field_77045_g).getBatSize();
/*     */     
/*  38 */     if (var10 != this.renderedBatSize) {
/*     */       
/*  40 */       this.renderedBatSize = var10;
/*  41 */       this.field_77045_g = new ModelBat();
/*     */     } 
/*  43 */     GL11.glPushMatrix();
/*  44 */     GL11.glEnable(3042);
/*  45 */     GL11.glBlendFunc(770, 771);
/*  46 */     Color c = new Color(bat.color);
/*  47 */     float r = c.getRed() / 255.0F;
/*  48 */     float g = c.getGreen() / 255.0F;
/*  49 */     float b = c.getBlue() / 255.0F;
/*  50 */     GL11.glColor4f(r, g, b, 0.5F);
/*  51 */     super.func_76986_a(bat, par2, par4, par6, par8, par9);
/*  52 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  53 */     GL11.glDisable(3042);
/*  54 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  59 */   protected void func_82442_a(EntitySpellBat par1EntityBat, float par2) { GL11.glScalef(0.35F, 0.35F, 0.35F); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected void func_82445_a(EntitySpellBat par1EntityBat, double par2, double par4, double par6) { super.func_77039_a(par1EntityBat, par2, par4, par6); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82444_a(EntitySpellBat par1EntityBat, float par2, float par3, float par4) {
/*  69 */     GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*  70 */     super.func_77043_a(par1EntityBat, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) { func_82442_a((EntitySpellBat)par1EntityLiving, par2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) { func_82444_a((EntitySpellBat)par1EntityLiving, par2, par3, par4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   protected void func_77039_a(EntityLivingBase par1EntityLiving, double par2, double par4, double par6) { func_82445_a((EntitySpellBat)par1EntityLiving, par2, par4, par6); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) { func_82443_a((EntitySpellBat)par1EntityLiving, par2, par4, par6, par8, par9); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/spellbat.png");
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected ResourceLocation func_110775_a(Entity entity) { return rl; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderSpellBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */