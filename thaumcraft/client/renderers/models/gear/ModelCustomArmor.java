/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.util.EnumHandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelCustomArmor
/*     */   extends ModelBiped
/*     */ {
/*  18 */   public ModelCustomArmor(float f, int i, int j, int k) { super(f, i, j, k); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
/*  25 */     if (entityIn instanceof EntityLivingBase) {
/*  26 */       this.field_78095_p = ((EntityLivingBase)entityIn).func_70678_g(UtilsFX.sysPartialTicks);
/*     */     }
/*     */     
/*  29 */     if (entityIn instanceof EntityArmorStand) {
/*     */       
/*  31 */       setRotationAnglesStand(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
/*     */     }
/*  33 */     else if (entityIn instanceof net.minecraft.entity.monster.EntitySkeleton || entityIn instanceof EntityZombie) {
/*  34 */       setRotationAnglesZombie(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
/*     */     }
/*     */     else {
/*     */       
/*  38 */       boolean flag = (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).func_184599_cB() > 4);
/*  39 */       this.field_78116_c.field_78796_g = netHeadYaw * 0.017453292F;
/*     */       
/*  41 */       if (flag) {
/*     */         
/*  43 */         this.field_78116_c.field_78795_f = -0.7853982F;
/*     */       }
/*     */       else {
/*     */         
/*  47 */         this.field_78116_c.field_78795_f = headPitch * 0.017453292F;
/*     */       } 
/*     */       
/*  50 */       this.field_78115_e.field_78796_g = 0.0F;
/*  51 */       this.field_178723_h.field_78798_e = 0.0F;
/*  52 */       this.field_178723_h.field_78800_c = -5.0F;
/*  53 */       this.field_178724_i.field_78798_e = 0.0F;
/*  54 */       this.field_178724_i.field_78800_c = 5.0F;
/*  55 */       float f = 1.0F;
/*     */       
/*  57 */       if (flag) {
/*     */         
/*  59 */         f = (float)(entityIn.field_70159_w * entityIn.field_70159_w + entityIn.field_70181_x * entityIn.field_70181_x + entityIn.field_70179_y * entityIn.field_70179_y);
/*  60 */         f /= 0.2F;
/*  61 */         f = f * f * f;
/*     */       } 
/*     */       
/*  64 */       if (f < 1.0F)
/*     */       {
/*  66 */         f = 1.0F;
/*     */       }
/*     */       
/*  69 */       this.field_178723_h.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F / f;
/*  70 */       this.field_178724_i.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
/*  71 */       this.field_178723_h.field_78808_h = 0.0F;
/*  72 */       this.field_178724_i.field_78808_h = 0.0F;
/*  73 */       this.field_178721_j.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
/*  74 */       this.field_178722_k.field_78795_f = MathHelper.func_76134_b(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount / f;
/*  75 */       this.field_178721_j.field_78796_g = 0.0F;
/*  76 */       this.field_178722_k.field_78796_g = 0.0F;
/*  77 */       this.field_178721_j.field_78808_h = 0.0F;
/*  78 */       this.field_178722_k.field_78808_h = 0.0F;
/*     */       
/*  80 */       if (this.field_78093_q) {
/*     */         
/*  82 */         this.field_178723_h.field_78795_f += -0.62831855F;
/*  83 */         this.field_178724_i.field_78795_f += -0.62831855F;
/*  84 */         this.field_178721_j.field_78795_f = -1.4137167F;
/*  85 */         this.field_178721_j.field_78796_g = 0.31415927F;
/*  86 */         this.field_178721_j.field_78808_h = 0.07853982F;
/*  87 */         this.field_178722_k.field_78795_f = -1.4137167F;
/*  88 */         this.field_178722_k.field_78796_g = -0.31415927F;
/*  89 */         this.field_178722_k.field_78808_h = -0.07853982F;
/*     */       } 
/*     */       
/*  92 */       this.field_178723_h.field_78796_g = 0.0F;
/*  93 */       this.field_178723_h.field_78808_h = 0.0F;
/*     */       
/*  95 */       switch (this.field_187075_l) {
/*     */         
/*     */         case EMPTY:
/*  98 */           this.field_178724_i.field_78796_g = 0.0F;
/*     */           break;
/*     */         case BLOCK:
/* 101 */           this.field_178724_i.field_78795_f = this.field_178724_i.field_78795_f * 0.5F - 0.9424779F;
/* 102 */           this.field_178724_i.field_78796_g = 0.5235988F;
/*     */           break;
/*     */         case ITEM:
/* 105 */           this.field_178724_i.field_78795_f = this.field_178724_i.field_78795_f * 0.5F - 0.31415927F;
/* 106 */           this.field_178724_i.field_78796_g = 0.0F;
/*     */           break;
/*     */       } 
/* 109 */       switch (this.field_187076_m) {
/*     */         
/*     */         case EMPTY:
/* 112 */           this.field_178723_h.field_78796_g = 0.0F;
/*     */           break;
/*     */         case BLOCK:
/* 115 */           this.field_178723_h.field_78795_f = this.field_178723_h.field_78795_f * 0.5F - 0.9424779F;
/* 116 */           this.field_178723_h.field_78796_g = -0.5235988F;
/*     */           break;
/*     */         case ITEM:
/* 119 */           this.field_178723_h.field_78795_f = this.field_178723_h.field_78795_f * 0.5F - 0.31415927F;
/* 120 */           this.field_178723_h.field_78796_g = 0.0F;
/*     */           break;
/*     */       } 
/* 123 */       if (this.field_78095_p > 0.0F) {
/*     */         
/* 125 */         EnumHandSide enumhandside = func_187072_a(entityIn);
/* 126 */         ModelRenderer modelrenderer = func_187074_a(enumhandside);
/* 127 */         float f1 = this.field_78095_p;
/* 128 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(f1) * 6.2831855F) * 0.2F;
/*     */         
/* 130 */         if (enumhandside == EnumHandSide.LEFT)
/*     */         {
/* 132 */           this.field_78115_e.field_78796_g *= -1.0F;
/*     */         }
/*     */         
/* 135 */         this.field_178723_h.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 136 */         this.field_178723_h.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 137 */         this.field_178724_i.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 138 */         this.field_178724_i.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 139 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 140 */         this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 141 */         this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/* 142 */         f1 = 1.0F - this.field_78095_p;
/* 143 */         f1 *= f1;
/* 144 */         f1 *= f1;
/* 145 */         f1 = 1.0F - f1;
/* 146 */         float f2 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 147 */         float f3 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -(this.field_78116_c.field_78795_f - 0.7F) * 0.75F;
/* 148 */         modelrenderer.field_78795_f = (float)(modelrenderer.field_78795_f - f2 * 1.2D + f3);
/* 149 */         modelrenderer.field_78796_g += this.field_78115_e.field_78796_g * 2.0F;
/* 150 */         modelrenderer.field_78808_h += MathHelper.func_76126_a(this.field_78095_p * 3.1415927F) * -0.4F;
/*     */       } 
/*     */       
/* 153 */       if (this.field_78117_n) {
/*     */         
/* 155 */         this.field_78115_e.field_78795_f = 0.5F;
/* 156 */         this.field_178723_h.field_78795_f += 0.4F;
/* 157 */         this.field_178724_i.field_78795_f += 0.4F;
/* 158 */         this.field_178721_j.field_78798_e = 4.0F;
/* 159 */         this.field_178722_k.field_78798_e = 4.0F;
/* 160 */         this.field_178721_j.field_78797_d = 13.0F;
/* 161 */         this.field_178722_k.field_78797_d = 13.0F;
/* 162 */         this.field_78116_c.field_78797_d = 4.5F;
/*     */         
/* 164 */         this.field_78115_e.field_78797_d = 4.5F;
/* 165 */         this.field_178723_h.field_78797_d = 5.0F;
/* 166 */         this.field_178724_i.field_78797_d = 5.0F;
/*     */       }
/*     */       else {
/*     */         
/* 170 */         this.field_78115_e.field_78795_f = 0.0F;
/* 171 */         this.field_178721_j.field_78798_e = 0.1F;
/* 172 */         this.field_178722_k.field_78798_e = 0.1F;
/* 173 */         this.field_178721_j.field_78797_d = 12.0F;
/* 174 */         this.field_178722_k.field_78797_d = 12.0F;
/* 175 */         this.field_78116_c.field_78797_d = 0.0F;
/*     */         
/* 177 */         this.field_78115_e.field_78797_d = 0.0F;
/* 178 */         this.field_178723_h.field_78797_d = 2.0F;
/* 179 */         this.field_178724_i.field_78797_d = 2.0F;
/*     */       } 
/*     */       
/* 182 */       this.field_178723_h.field_78808_h += MathHelper.func_76134_b(ageInTicks * 0.09F) * 0.05F + 0.05F;
/* 183 */       this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(ageInTicks * 0.09F) * 0.05F + 0.05F;
/* 184 */       this.field_178723_h.field_78795_f += MathHelper.func_76126_a(ageInTicks * 0.067F) * 0.05F;
/* 185 */       this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(ageInTicks * 0.067F) * 0.05F;
/*     */       
/* 187 */       if (this.field_187076_m == ModelBiped.ArmPose.BOW_AND_ARROW) {
/*     */         
/* 189 */         this.field_178723_h.field_78796_g = -0.1F + this.field_78116_c.field_78796_g;
/* 190 */         this.field_178724_i.field_78796_g = 0.1F + this.field_78116_c.field_78796_g + 0.4F;
/* 191 */         this.field_178723_h.field_78795_f = -1.5707964F + this.field_78116_c.field_78795_f;
/* 192 */         this.field_178724_i.field_78795_f = -1.5707964F + this.field_78116_c.field_78795_f;
/*     */       }
/* 194 */       else if (this.field_187075_l == ModelBiped.ArmPose.BOW_AND_ARROW) {
/*     */         
/* 196 */         this.field_178723_h.field_78796_g = -0.1F + this.field_78116_c.field_78796_g - 0.4F;
/* 197 */         this.field_178724_i.field_78796_g = 0.1F + this.field_78116_c.field_78796_g;
/* 198 */         this.field_178723_h.field_78795_f = -1.5707964F + this.field_78116_c.field_78795_f;
/* 199 */         this.field_178724_i.field_78795_f = -1.5707964F + this.field_78116_c.field_78795_f;
/*     */       } 
/*     */       
/* 202 */       func_178685_a(this.field_78116_c, this.field_178720_f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAnglesZombie(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
/* 211 */     super.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
/*     */     
/* 213 */     boolean flag = (entityIn instanceof EntityZombie && ((EntityZombie)entityIn).func_184734_db());
/* 214 */     float f = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
/* 215 */     float f1 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
/* 216 */     this.field_178723_h.field_78808_h = 0.0F;
/* 217 */     this.field_178724_i.field_78808_h = 0.0F;
/* 218 */     this.field_178723_h.field_78796_g = -(0.1F - f * 0.6F);
/* 219 */     this.field_178724_i.field_78796_g = 0.1F - f * 0.6F;
/* 220 */     float f2 = -3.1415927F / (flag ? 1.5F : 2.25F);
/* 221 */     this.field_178723_h.field_78795_f = f2;
/* 222 */     this.field_178724_i.field_78795_f = f2;
/* 223 */     this.field_178723_h.field_78795_f += f * 1.2F - f1 * 0.4F;
/* 224 */     this.field_178724_i.field_78795_f += f * 1.2F - f1 * 0.4F;
/* 225 */     this.field_178723_h.field_78808_h += MathHelper.func_76134_b(ageInTicks * 0.09F) * 0.05F + 0.05F;
/* 226 */     this.field_178724_i.field_78808_h -= MathHelper.func_76134_b(ageInTicks * 0.09F) * 0.05F + 0.05F;
/* 227 */     this.field_178723_h.field_78795_f += MathHelper.func_76126_a(ageInTicks * 0.067F) * 0.05F;
/* 228 */     this.field_178724_i.field_78795_f -= MathHelper.func_76126_a(ageInTicks * 0.067F) * 0.05F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAnglesStand(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
/* 234 */     if (entityIn instanceof EntityArmorStand) {
/*     */       
/* 236 */       EntityArmorStand entityarmorstand = (EntityArmorStand)entityIn;
/* 237 */       this.field_78116_c.field_78795_f = 0.017453292F * entityarmorstand.func_175418_s().func_179415_b();
/* 238 */       this.field_78116_c.field_78796_g = 0.017453292F * entityarmorstand.func_175418_s().func_179416_c();
/* 239 */       this.field_78116_c.field_78808_h = 0.017453292F * entityarmorstand.func_175418_s().func_179413_d();
/* 240 */       this.field_78116_c.func_78793_a(0.0F, 1.0F, 0.0F);
/* 241 */       this.field_78115_e.field_78795_f = 0.017453292F * entityarmorstand.func_175408_t().func_179415_b();
/* 242 */       this.field_78115_e.field_78796_g = 0.017453292F * entityarmorstand.func_175408_t().func_179416_c();
/* 243 */       this.field_78115_e.field_78808_h = 0.017453292F * entityarmorstand.func_175408_t().func_179413_d();
/* 244 */       this.field_178724_i.field_78795_f = 0.017453292F * entityarmorstand.func_175404_u().func_179415_b();
/* 245 */       this.field_178724_i.field_78796_g = 0.017453292F * entityarmorstand.func_175404_u().func_179416_c();
/* 246 */       this.field_178724_i.field_78808_h = 0.017453292F * entityarmorstand.func_175404_u().func_179413_d();
/* 247 */       this.field_178723_h.field_78795_f = 0.017453292F * entityarmorstand.func_175411_v().func_179415_b();
/* 248 */       this.field_178723_h.field_78796_g = 0.017453292F * entityarmorstand.func_175411_v().func_179416_c();
/* 249 */       this.field_178723_h.field_78808_h = 0.017453292F * entityarmorstand.func_175411_v().func_179413_d();
/* 250 */       this.field_178722_k.field_78795_f = 0.017453292F * entityarmorstand.func_175403_w().func_179415_b();
/* 251 */       this.field_178722_k.field_78796_g = 0.017453292F * entityarmorstand.func_175403_w().func_179416_c();
/* 252 */       this.field_178722_k.field_78808_h = 0.017453292F * entityarmorstand.func_175403_w().func_179413_d();
/* 253 */       this.field_178722_k.func_78793_a(1.9F, 11.0F, 0.0F);
/* 254 */       this.field_178721_j.field_78795_f = 0.017453292F * entityarmorstand.func_175407_x().func_179415_b();
/* 255 */       this.field_178721_j.field_78796_g = 0.017453292F * entityarmorstand.func_175407_x().func_179416_c();
/* 256 */       this.field_178721_j.field_78808_h = 0.017453292F * entityarmorstand.func_175407_x().func_179413_d();
/* 257 */       this.field_178721_j.func_78793_a(-1.9F, 11.0F, 0.0F);
/* 258 */       func_178685_a(this.field_78116_c, this.field_178720_f);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\gear\ModelCustomArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */