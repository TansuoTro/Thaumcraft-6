/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintacle;
/*     */ 
/*     */ public class ModelTaintacle
/*     */   extends ModelBase {
/*     */   public ModelTaintacle(int length, boolean seed) {
/*  14 */     this.tentacle = new ModelRendererTaintacle(this);
/*     */     
/*  16 */     this.orb = new ModelRendererTaintacle(this);
/*  17 */     this.length = 10;
/*  18 */     this.seed = false;
/*     */ 
/*     */ 
/*     */     
/*  22 */     this.seed = seed;
/*  23 */     int var3 = 0;
/*  24 */     this.length = length;
/*  25 */     this.field_78089_u = 64;
/*  26 */     this.field_78090_t = 64;
/*  27 */     this.tentacle = new ModelRendererTaintacle(this, 0, 0);
/*     */     
/*  29 */     this.tentacle.func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  30 */     this.tentacle.field_78800_c = 0.0F;
/*  31 */     this.tentacle.field_78798_e = 0.0F;
/*  32 */     this.tentacle.field_78797_d = 12.0F;
/*  33 */     this.tents = new ModelRendererTaintacle[length];
/*  34 */     for (int k = 0; k < length - 1; k++) {
/*     */       
/*  36 */       this.tents[k] = new ModelRendererTaintacle(this, 0, 16);
/*  37 */       this.tents[k].func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  38 */       (this.tents[k]).field_78797_d = -8.0F;
/*     */       
/*  40 */       if (k == 0) {
/*     */         
/*  42 */         this.tentacle.func_78792_a(this.tents[k]);
/*     */       }
/*     */       else {
/*     */         
/*  46 */         this.tents[k - 1].func_78792_a(this.tents[k]);
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     if (!seed) {
/*  51 */       this.orb = new ModelRendererTaintacle(this, 0, 56);
/*  52 */       this.orb.func_78789_a(-2.0F, -2.0F, -2.0F, 4, 4, 4);
/*  53 */       this.orb.field_78797_d = -8.0F;
/*  54 */       this.tents[length - 2].func_78792_a(this.orb);
/*     */       
/*  56 */       this.tents[length - 1] = new ModelRendererTaintacle(this, 0, 32);
/*  57 */       this.tents[length - 1].func_78789_a(-6.0F, -6.0F, -6.0F, 12, 12, 12);
/*  58 */       (this.tents[length - 1]).field_78797_d = -8.0F;
/*  59 */       this.tents[length - 2].func_78792_a(this.tents[length - 1]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelRenderer tentacle;
/*     */   public ModelRenderer[] tents;
/*     */   public ModelRenderer orb;
/*     */   private int length;
/*     */   private boolean seed;
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
/*  71 */     float flail = 0.0F;
/*  72 */     float ht = 0.0F;
/*  73 */     int at = 0;
/*  74 */     if (entity instanceof EntityTaintacle) {
/*  75 */       EntityTaintacle tentacle = (EntityTaintacle)entity;
/*  76 */       flail = tentacle.flailIntensity;
/*  77 */       ht = tentacle.field_70737_aN;
/*     */       
/*  79 */       float mod = par6 * 0.2F;
/*  80 */       float fs = (flail > 1.0F) ? 3.0F : (1.0F + ((flail > 1.0F) ? mod : -mod));
/*  81 */       float fi = flail + ((ht > 0.0F || at > 0) ? mod : -mod);
/*     */       
/*  83 */       this.tentacle.field_78795_f = 0.0F;
/*  84 */       for (int k = 0; k < this.length - 1; k++) {
/*     */         
/*  86 */         (this.tents[k]).field_78795_f = 0.15F * fi * MathHelper.func_76126_a(par3 * 0.1F * fs - k / 2.0F);
/*  87 */         (this.tents[k]).field_78808_h = 0.1F / fi * MathHelper.func_76126_a(par3 * 0.15F - k / 2.0F);
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     if (entity instanceof EntityTaintSeed) {
/*  92 */       EntityTaintSeed seed = (EntityTaintSeed)entity;
/*  93 */       ht = seed.field_70737_aN / 200.0F;
/*  94 */       flail = 0.1F;
/*  95 */       float mod = par6 * 0.2F;
/*  96 */       float fs = (flail > 1.0F) ? 3.0F : (1.0F + ((flail > 1.0F) ? mod : -mod));
/*  97 */       float fi = flail + ((ht > 0.0F || at > 0) ? mod : -mod);
/*  98 */       fi *= 3.0F;
/*     */       
/* 100 */       this.tentacle.field_78795_f = 0.0F;
/* 101 */       for (int k = 0; k < this.length - 1; k++) {
/*     */         
/* 103 */         (this.tents[k]).field_78795_f = 0.2F + 0.01F * k * k + ht + seed.attackAnim;
/* 104 */         (this.tents[k]).field_78808_h = 0.1F / fi * MathHelper.func_76126_a(par3 * 0.05F - k / 2.0F) / 5.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 115 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/* 116 */     GL11.glPushMatrix();
/* 117 */     GL11.glEnable(3042);
/* 118 */     GL11.glBlendFunc(770, 771);
/* 119 */     if (par1Entity instanceof EntityTaintSeed) {
/* 120 */       GL11.glTranslatef(0.0F, 1.0F, -0.2F);
/* 121 */       GL11.glScalef(par1Entity.field_70130_N * 0.6F, par1Entity.field_70131_O, par1Entity.field_70130_N * 0.6F);
/* 122 */       ((ModelRendererTaintacle)this.tentacle).render(par7, this.seed ? 0.82F : 0.85F);
/*     */     } else {
/* 124 */       float height = 0.0F;
/* 125 */       float hc = par1Entity.field_70131_O * 10.0F;
/* 126 */       if (par1Entity.field_70173_aa < hc) {
/* 127 */         height = (hc - par1Entity.field_70173_aa) / hc * par1Entity.field_70131_O;
/*     */       }
/* 129 */       GL11.glTranslatef(0.0F, ((par1Entity.field_70131_O == 3.0F) ? 0.6F : 1.2F) + height, 0.0F);
/* 130 */       GL11.glScalef(par1Entity.field_70131_O / 3.0F, par1Entity.field_70131_O / 3.0F, par1Entity.field_70131_O / 3.0F);
/* 131 */       ((ModelRendererTaintacle)this.tentacle).render(par7, 0.88F);
/*     */     } 
/* 133 */     GL11.glDisable(3042);
/* 134 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelTaintacle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */