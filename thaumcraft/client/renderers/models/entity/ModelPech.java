/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
/*     */ 
/*     */ public class ModelPech
/*     */   extends ModelBiped {
/*     */   ModelRenderer Jowls;
/*     */   ModelRenderer LowerPack;
/*     */   ModelRenderer UpperPack;
/*     */   
/*     */   public ModelPech() {
/*  16 */     this.field_78090_t = 128;
/*  17 */     this.field_78089_u = 64;
/*     */     
/*  19 */     this.field_78115_e = new ModelRenderer(this, 34, 12);
/*  20 */     this.field_78115_e.func_78789_a(-3.0F, 0.0F, 0.0F, 6, 10, 6);
/*  21 */     this.field_78115_e.func_78793_a(0.0F, 9.0F, -3.0F);
/*  22 */     this.field_78115_e.func_78787_b(128, 64);
/*  23 */     this.field_78115_e.field_78809_i = true;
/*  24 */     setRotation(this.field_78115_e, 0.3129957F, 0.0F, 0.0F);
/*     */     
/*  26 */     this.field_178721_j = new ModelRenderer(this, 35, 1);
/*  27 */     this.field_178721_j.field_78809_i = true;
/*  28 */     this.field_178721_j.func_78789_a(-2.9F, 0.0F, 0.0F, 3, 6, 3);
/*  29 */     this.field_178721_j.func_78793_a(0.0F, 18.0F, 0.0F);
/*  30 */     this.field_178721_j.func_78787_b(128, 64);
/*  31 */     this.field_178721_j.field_78809_i = true;
/*  32 */     setRotation(this.field_178721_j, 0.0F, 0.0F, 0.0F);
/*  33 */     this.field_178721_j.field_78809_i = false;
/*     */     
/*  35 */     this.field_178722_k = new ModelRenderer(this, 35, 1);
/*  36 */     this.field_178722_k.func_78789_a(-0.1F, 0.0F, 0.0F, 3, 6, 3);
/*  37 */     this.field_178722_k.func_78793_a(0.0F, 18.0F, 0.0F);
/*  38 */     this.field_178722_k.func_78787_b(128, 64);
/*  39 */     this.field_178722_k.field_78809_i = true;
/*  40 */     setRotation(this.field_178722_k, 0.0F, 0.0F, 0.0F);
/*     */     
/*  42 */     this.field_78116_c = new ModelRenderer(this, 2, 11);
/*  43 */     this.field_78116_c.func_78789_a(-3.5F, -5.0F, -5.0F, 7, 5, 5);
/*  44 */     this.field_78116_c.func_78793_a(0.0F, 8.0F, 0.0F);
/*  45 */     this.field_78116_c.func_78787_b(128, 64);
/*  46 */     this.field_78116_c.field_78809_i = true;
/*  47 */     setRotation(this.field_78116_c, 0.0F, 0.0F, 0.0F);
/*     */     
/*  49 */     this.Jowls = new ModelRenderer(this, 1, 21);
/*  50 */     this.Jowls.func_78789_a(-4.0F, -1.0F, -6.0F, 8, 3, 5);
/*  51 */     this.Jowls.func_78793_a(0.0F, 8.0F, 0.0F);
/*  52 */     this.Jowls.func_78787_b(128, 64);
/*  53 */     this.Jowls.field_78809_i = true;
/*  54 */     setRotation(this.Jowls, 0.0F, 0.0F, 0.0F);
/*     */     
/*  56 */     this.LowerPack = new ModelRenderer(this, 0, 0);
/*  57 */     this.LowerPack.func_78789_a(-5.0F, 0.0F, 0.0F, 10, 5, 5);
/*  58 */     this.LowerPack.func_78793_a(0.0F, 10.0F, 3.5F);
/*  59 */     this.LowerPack.func_78787_b(128, 64);
/*  60 */     this.LowerPack.field_78809_i = true;
/*  61 */     setRotation(this.LowerPack, 0.3013602F, 0.0F, 0.0F);
/*     */     
/*  63 */     this.UpperPack = new ModelRenderer(this, 64, 1);
/*  64 */     this.UpperPack.func_78789_a(-7.5F, -14.0F, 0.0F, 15, 14, 11);
/*  65 */     this.UpperPack.func_78793_a(0.0F, 10.0F, 3.0F);
/*  66 */     this.UpperPack.func_78787_b(128, 64);
/*  67 */     this.UpperPack.field_78809_i = true;
/*  68 */     setRotation(this.UpperPack, 0.4537856F, 0.0F, 0.0F);
/*     */     
/*  70 */     this.field_178723_h = new ModelRenderer(this, 52, 2);
/*  71 */     this.field_178723_h.field_78809_i = true;
/*  72 */     this.field_178723_h.func_78789_a(-2.0F, 0.0F, -1.0F, 2, 6, 2);
/*  73 */     this.field_178723_h.func_78793_a(-3.0F, 10.0F, -1.0F);
/*  74 */     this.field_178723_h.func_78787_b(128, 64);
/*  75 */     this.field_178723_h.field_78809_i = true;
/*  76 */     setRotation(this.field_178723_h, 0.0F, 0.0F, 0.0F);
/*  77 */     this.field_178723_h.field_78809_i = false;
/*     */     
/*  79 */     this.field_178724_i = new ModelRenderer(this, 52, 2);
/*  80 */     this.field_178724_i.func_78789_a(0.0F, 0.0F, -1.0F, 2, 6, 2);
/*  81 */     this.field_178724_i.func_78793_a(3.0F, 10.0F, -1.0F);
/*  82 */     this.field_178724_i.func_78787_b(128, 64);
/*  83 */     this.field_178724_i.field_78809_i = true;
/*  84 */     setRotation(this.field_178724_i, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  89 */     func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
/*  90 */     this.field_78115_e.func_78785_a(par7);
/*  91 */     this.field_178721_j.func_78785_a(par7);
/*  92 */     this.field_178722_k.func_78785_a(par7);
/*  93 */     this.field_78116_c.func_78785_a(par7);
/*  94 */     this.Jowls.func_78785_a(par7);
/*  95 */     this.LowerPack.func_78785_a(par7);
/*  96 */     this.UpperPack.func_78785_a(par7);
/*  97 */     this.field_178723_h.func_78785_a(par7);
/*  98 */     this.field_178724_i.func_78785_a(par7);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 102 */     model.field_78795_f = x;
/* 103 */     model.field_78796_g = y;
/* 104 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
/* 110 */     this.field_78116_c.field_78796_g = par4 / 57.295776F;
/* 111 */     this.field_78116_c.field_78795_f = par5 / 57.295776F;
/*     */     
/* 113 */     float mumble = 0.0F;
/* 114 */     if (entity instanceof EntityPech) {
/* 115 */       mumble = ((EntityPech)entity).mumble;
/*     */     }
/*     */     
/* 118 */     this.Jowls.field_78796_g = this.field_78116_c.field_78796_g;
/* 119 */     this.Jowls
/*     */       
/* 121 */       .field_78795_f = this.field_78116_c.field_78795_f + 0.2617994F + MathHelper.func_76134_b(par1 * 0.6662F) * par2 * 0.25F + 0.34906587F * Math.abs(MathHelper.func_76126_a(mumble / 8.0F));
/*     */     
/* 123 */     this.field_178723_h.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
/* 124 */     this.field_178724_i.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
/* 125 */     this.field_178723_h.field_78808_h = 0.0F;
/* 126 */     this.field_178724_i.field_78808_h = 0.0F;
/* 127 */     this.field_178721_j.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
/* 128 */     this.field_178722_k.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
/* 129 */     this.field_178721_j.field_78796_g = 0.0F;
/* 130 */     this.field_178722_k.field_78796_g = 0.0F;
/*     */     
/* 132 */     this.LowerPack.field_78796_g = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.125F;
/* 133 */     this.LowerPack.field_78808_h = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.125F;
/*     */     
/* 135 */     if (this.field_78093_q) {
/* 136 */       this.field_178723_h.field_78795_f += -0.62831855F;
/* 137 */       this.field_178724_i.field_78795_f += -0.62831855F;
/* 138 */       this.field_178721_j.field_78795_f = -1.2566371F;
/* 139 */       this.field_178722_k.field_78795_f = -1.2566371F;
/* 140 */       this.field_178721_j.field_78796_g = 0.31415927F;
/* 141 */       this.field_178722_k.field_78796_g = -0.31415927F;
/*     */     } 
/*     */     
/* 144 */     this.field_178723_h.field_78796_g = 0.0F;
/* 145 */     this.field_178724_i.field_78796_g = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 149 */     if (this.field_78095_p > -9990.0F) {
/* 150 */       float f6 = this.field_78095_p;
/* 151 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 152 */       this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 153 */       this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/* 154 */       f6 = 1.0F - this.field_78095_p;
/* 155 */       f6 *= f6;
/* 156 */       f6 *= f6;
/* 157 */       f6 = 1.0F - f6;
/* 158 */       float f7 = MathHelper.func_76126_a(f6 * 3.1415927F);
/* 159 */       float f8 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/*     */       
/* 161 */       this.field_178723_h.field_78795_f = (float)(this.field_178723_h.field_78795_f - f7 * 1.2D + f8);
/*     */       
/* 163 */       this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/* 164 */       this.field_178723_h.field_78808_h = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F;
/*     */     } 
/*     */     
/* 167 */     if (entity.func_70093_af()) {
/* 168 */       this.field_178723_h.field_78795_f += 0.4F;
/* 169 */       this.field_178724_i.field_78795_f += 0.4F;
/*     */     } 
/*     */     
/* 172 */     this.field_178723_h.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 173 */     this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
/* 174 */     this.field_178723_h.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/* 175 */     this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */