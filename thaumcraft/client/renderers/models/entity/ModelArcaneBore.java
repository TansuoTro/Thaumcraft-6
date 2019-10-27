/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelArcaneBore
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer crystal;
/*     */   ModelRenderer leg2;
/*     */   ModelRenderer tripod;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer magbase;
/*     */   ModelRenderer base;
/*     */   ModelRenderer domebase;
/*     */   ModelRenderer dome;
/*     */   ModelRenderer tip;
/*     */   
/*     */   public ModelArcaneBore() {
/*  24 */     this.field_78090_t = 64;
/*  25 */     this.field_78089_u = 32;
/*     */     
/*  27 */     this.leg2 = new ModelRenderer(this, 20, 10);
/*  28 */     this.leg2.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  29 */     this.leg2.func_78793_a(0.0F, 12.0F, 0.0F);
/*  30 */     this.leg2.func_78787_b(64, 32);
/*  31 */     setRotation(this.leg2, 0.5235988F, 1.570796F, 0.0F);
/*  32 */     this.tripod = new ModelRenderer(this, 13, 0);
/*  33 */     this.tripod.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 2, 3);
/*  34 */     this.tripod.func_78793_a(0.0F, 12.0F, 0.0F);
/*  35 */     this.tripod.func_78787_b(64, 32);
/*  36 */     setRotation(this.tripod, 0.0F, 0.0F, 0.0F);
/*  37 */     this.leg3 = new ModelRenderer(this, 20, 10);
/*  38 */     this.leg3.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  39 */     this.leg3.func_78793_a(0.0F, 12.0F, 0.0F);
/*  40 */     this.leg3.func_78787_b(64, 32);
/*  41 */     setRotation(this.leg3, 0.5235988F, 3.141593F, 0.0F);
/*  42 */     this.leg4 = new ModelRenderer(this, 20, 10);
/*  43 */     this.leg4.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  44 */     this.leg4.func_78793_a(0.0F, 12.0F, 0.0F);
/*  45 */     this.leg4.func_78787_b(64, 32);
/*  46 */     setRotation(this.leg4, 0.5235988F, 4.712389F, 0.0F);
/*  47 */     this.leg1 = new ModelRenderer(this, 20, 10);
/*  48 */     this.leg1.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/*  49 */     this.leg1.func_78793_a(0.0F, 12.0F, 0.0F);
/*  50 */     this.leg1.func_78787_b(64, 32);
/*  51 */     setRotation(this.leg1, 0.5235988F, 0.0F, 0.0F);
/*     */     
/*  53 */     this.base = new ModelRenderer(this, 32, 0);
/*  54 */     this.base.func_78789_a(-3.0F, -6.0F, -3.0F, 6, 6, 6);
/*  55 */     this.base.func_78793_a(0.0F, 13.0F, 0.0F);
/*  56 */     this.base.func_78787_b(64, 32);
/*  57 */     setRotation(this.base, 0.0F, 0.0F, 0.0F);
/*  58 */     this.crystal = new ModelRenderer(this, 32, 25);
/*  59 */     this.crystal.func_78789_a(-1.0F, -4.0F, 5.0F, 2, 2, 2);
/*  60 */     this.crystal.func_78793_a(0.0F, 0.0F, 0.0F);
/*  61 */     this.crystal.func_78787_b(64, 32);
/*  62 */     setRotation(this.crystal, 0.0F, 0.0F, 0.0F);
/*  63 */     this.domebase = new ModelRenderer(this, 32, 19);
/*  64 */     this.domebase.func_78789_a(-2.0F, -5.0F, 3.0F, 4, 4, 1);
/*  65 */     this.domebase.func_78793_a(0.0F, 0.0F, 0.0F);
/*  66 */     this.domebase.func_78787_b(64, 32);
/*  67 */     setRotation(this.domebase, 0.0F, 0.0F, 0.0F);
/*  68 */     this.dome = new ModelRenderer(this, 44, 16);
/*  69 */     this.dome.func_78789_a(-2.0F, -5.0F, 4.0F, 4, 4, 4);
/*  70 */     this.dome.func_78793_a(0.0F, 0.0F, 0.0F);
/*  71 */     this.dome.func_78787_b(64, 32);
/*  72 */     setRotation(this.dome, 0.0F, 0.0F, 0.0F);
/*     */     
/*  74 */     this.magbase = new ModelRenderer(this, 0, 18);
/*  75 */     this.magbase.func_78789_a(-1.0F, -4.0F, -6.0F, 2, 2, 3);
/*  76 */     this.magbase.func_78793_a(0.0F, 0.0F, 0.0F);
/*  77 */     this.magbase.func_78787_b(64, 32);
/*  78 */     this.magbase.field_78809_i = true;
/*  79 */     setRotation(this.magbase, 0.0F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.tip = new ModelRenderer(this, 0, 9);
/*  82 */     this.tip.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 3, 3);
/*  83 */     this.tip.func_78793_a(0.0F, -3.0F, -6.0F);
/*  84 */     this.tip.func_78787_b(64, 32);
/*  85 */     this.tip.field_78809_i = true;
/*  86 */     setRotation(this.tip, -1.570796F, 0.0F, 0.0F);
/*     */     
/*  88 */     this.base.func_78792_a(this.crystal);
/*  89 */     this.base.func_78792_a(this.dome);
/*  90 */     this.base.func_78792_a(this.domebase);
/*  91 */     this.base.func_78792_a(this.magbase);
/*  92 */     this.base.func_78792_a(this.tip);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  97 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/*  98 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*  99 */     this.leg2.func_78785_a(f5);
/* 100 */     this.tripod.func_78785_a(f5);
/* 101 */     this.leg3.func_78785_a(f5);
/* 102 */     this.leg4.func_78785_a(f5);
/* 103 */     this.leg1.func_78785_a(f5);
/*     */     
/* 105 */     GL11.glEnable(3042);
/* 106 */     GL11.glBlendFunc(770, 771);
/* 107 */     this.base.func_78785_a(f5);
/* 108 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 113 */     model.field_78795_f = x;
/* 114 */     model.field_78796_g = y;
/* 115 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float headpitch, float headyaw, float p_78087_6_, Entity entity) {
/* 121 */     this.base.field_78796_g = headpitch / 57.295776F;
/* 122 */     this.base.field_78795_f = headyaw / 57.295776F;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelArcaneBore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */