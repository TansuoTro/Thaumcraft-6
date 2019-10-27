/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
/*     */ 
/*     */ public class ModelTaintSeed
/*     */   extends ModelBase {
/*     */   public ModelTaintSeed() {
/*  14 */     this.tentacle = new ModelRendererTaintSeed(this);
/*     */     
/*  16 */     this.orb = new ModelRendererTaintSeed(this);
/*  17 */     this.length = 8;
/*     */ 
/*     */ 
/*     */     
/*  21 */     this.field_78089_u = 64;
/*  22 */     this.field_78090_t = 64;
/*  23 */     this.tentacle = new ModelRendererTaintSeed(this, 0, 0);
/*     */     
/*  25 */     this.tentacle.func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  26 */     this.tentacle.field_78800_c = 0.0F;
/*  27 */     this.tentacle.field_78798_e = 0.0F;
/*  28 */     this.tentacle.field_78797_d = 12.0F;
/*  29 */     this.tents = new ModelRendererTaintSeed[this.length];
/*  30 */     for (int k = 0; k < this.length - 1; k++) {
/*     */       
/*  32 */       this.tents[k] = new ModelRendererTaintSeed(this, 0, (k < this.length - 4) ? 16 : ((k == this.length - 4) ? 48 : 56));
/*  33 */       if (k < this.length - 4) {
/*  34 */         this.tents[k].func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  35 */         (this.tents[k]).field_78797_d = -8.0F;
/*     */       } else {
/*  37 */         this.tents[k].func_78789_a(-2.0F, -2.0F, -2.0F, 4, 4, 4);
/*  38 */         (this.tents[k]).field_78797_d = (k == this.length - 4) ? -8.0F : -4.0F;
/*     */       } 
/*     */ 
/*     */       
/*  42 */       if (k == 0) {
/*     */         
/*  44 */         this.tentacle.func_78792_a(this.tents[k]);
/*     */       }
/*     */       else {
/*     */         
/*  48 */         this.tents[k - 1].func_78792_a(this.tents[k]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer tentacle;
/*     */   
/*     */   public ModelRenderer[] tents;
/*     */   
/*     */   public ModelRenderer orb;
/*     */   private int length;
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
/*  62 */     float flail = 0.0F;
/*  63 */     float ht = 0.0F;
/*  64 */     int at = 0;
/*  65 */     EntityTaintSeed seed = (EntityTaintSeed)entity;
/*  66 */     ht = seed.field_70737_aN / 200.0F;
/*  67 */     flail = 0.1F;
/*  68 */     float mod = par6 * 0.2F;
/*  69 */     float fs = (flail > 1.0F) ? 3.0F : (1.0F + ((flail > 1.0F) ? mod : -mod));
/*  70 */     float fi = flail + ((ht > 0.0F || at > 0) ? mod : -mod);
/*  71 */     fi *= 3.0F;
/*     */     
/*  73 */     this.tentacle.field_78795_f = 0.0F;
/*  74 */     for (int k = 0; k < this.length - 1; k++) {
/*     */       
/*  76 */       (this.tents[k]).field_78795_f = 0.1F / fi * MathHelper.func_76126_a(par3 * 0.06F - k / 2.0F) / 5.0F + ht + seed.attackAnim;
/*  77 */       (this.tents[k]).field_78808_h = 0.1F / fi * MathHelper.func_76126_a(par3 * 0.05F - k / 2.0F) / 5.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  87 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/*  88 */     GL11.glPushMatrix();
/*  89 */     GL11.glEnable(3042);
/*  90 */     GL11.glBlendFunc(770, 771);
/*  91 */     float height = 0.0F;
/*  92 */     float hc = par1Entity.field_70131_O * 10.0F;
/*  93 */     if (par1Entity.field_70173_aa < hc) {
/*  94 */       height = (hc - par1Entity.field_70173_aa) / hc * par1Entity.field_70131_O;
/*     */     }
/*  96 */     GL11.glTranslatef(0.0F, ((par1Entity.field_70131_O == 3.0F) ? 0.6F : 1.2F) + height, 0.0F);
/*  97 */     GL11.glScalef(par1Entity.field_70131_O / 2.0F, par1Entity.field_70131_O / 2.0F, par1Entity.field_70131_O / 2.0F);
/*  98 */     ((ModelRendererTaintSeed)this.tentacle).render(par7, par1Entity.field_70173_aa + Minecraft.func_71410_x().func_184121_ak(), 1.6F);
/*  99 */     GL11.glDisable(3042);
/* 100 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelTaintSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */