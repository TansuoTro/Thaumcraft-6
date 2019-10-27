/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelCrossbow
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer crossl3;
/*     */   ModelRenderer crossr3;
/*     */   ModelRenderer loadbarcross;
/*     */   ModelRenderer loadbarl;
/*     */   ModelRenderer loadbarr;
/*     */   ModelRenderer barrel;
/*     */   ModelRenderer basebarcross;
/*     */   ModelRenderer ammobox;
/*     */   ModelRenderer crossbow;
/*     */   ModelRenderer basebarr;
/*     */   ModelRenderer basebarl;
/*     */   ModelRenderer crossl1;
/*     */   ModelRenderer crossl2;
/*     */   ModelRenderer crossr1;
/*     */   ModelRenderer crossr2;
/*     */   ModelRenderer tripod;
/*     */   ModelRenderer leg3;
/*     */   ModelRenderer leg4;
/*     */   ModelRenderer leg1;
/*     */   ModelRenderer leg2;
/*     */   
/*     */   public ModelCrossbow() {
/*  38 */     this.field_78090_t = 64;
/*  39 */     this.field_78089_u = 32;
/*     */     
/*  41 */     this.crossbow = new ModelRenderer(this, 28, 14);
/*  42 */     this.crossbow.func_78789_a(-2.0F, 0.0F, -7.0F, 4, 2, 14);
/*  43 */     this.crossbow.func_78793_a(0.0F, 10.0F, 0.0F);
/*  44 */     this.crossbow.func_78787_b(64, 32);
/*  45 */     this.crossbow.field_78809_i = true;
/*  46 */     setRotation(this.crossbow, 0.0F, 0.0F, 0.0F);
/*  47 */     this.basebarr = new ModelRenderer(this, 40, 23);
/*  48 */     this.basebarr.func_78789_a(-1.0F, 0.0F, 7.0F, 1, 2, 5);
/*  49 */     this.basebarr.func_78793_a(0.0F, 0.0F, 0.0F);
/*  50 */     this.basebarr.func_78787_b(64, 32);
/*  51 */     this.basebarr.field_78809_i = true;
/*  52 */     setRotation(this.basebarr, 0.0F, -0.1396263F, 0.0F);
/*  53 */     this.basebarl = new ModelRenderer(this, 40, 23);
/*  54 */     this.basebarl.func_78789_a(0.0F, 0.0F, 7.0F, 1, 2, 5);
/*  55 */     this.basebarl.func_78793_a(0.0F, 0.0F, 0.0F);
/*  56 */     this.basebarl.func_78787_b(64, 32);
/*  57 */     this.basebarl.field_78809_i = true;
/*  58 */     setRotation(this.basebarl, 0.0F, 0.1396263F, 0.0F);
/*  59 */     this.barrel = new ModelRenderer(this, 20, 28);
/*  60 */     this.barrel.func_78789_a(-1.0F, -1.0F, -8.0F, 2, 2, 2);
/*  61 */     this.barrel.func_78793_a(0.0F, 0.0F, 0.0F);
/*  62 */     this.barrel.func_78787_b(64, 32);
/*  63 */     this.barrel.field_78809_i = true;
/*  64 */     setRotation(this.barrel, 0.0F, 0.0F, 0.0F);
/*  65 */     this.basebarcross = new ModelRenderer(this, 0, 13);
/*  66 */     this.basebarcross.func_78789_a(-2.0F, 0.5F, 10.0F, 4, 1, 1);
/*  67 */     this.basebarcross.func_78793_a(0.0F, 0.0F, 0.0F);
/*  68 */     this.basebarcross.func_78787_b(64, 32);
/*  69 */     this.basebarcross.field_78809_i = true;
/*  70 */     setRotation(this.basebarcross, 0.0F, 0.0F, 0.0F);
/*  71 */     this.ammobox = new ModelRenderer(this, 38, 0);
/*  72 */     this.ammobox.func_78789_a(-2.0F, -5.0F, -6.0F, 4, 5, 9);
/*  73 */     this.ammobox.func_78793_a(0.0F, 0.0F, 0.0F);
/*  74 */     this.ammobox.func_78787_b(64, 32);
/*  75 */     this.ammobox.field_78809_i = true;
/*  76 */     setRotation(this.ammobox, 0.0F, 0.0F, 0.0F);
/*     */     
/*  78 */     this.loadbarcross = new ModelRenderer(this, 0, 13);
/*  79 */     this.loadbarcross.func_78789_a(-2.0F, -8.5F, -0.5F, 4, 1, 1);
/*  80 */     this.loadbarcross.func_78793_a(0.0F, 0.0F, 0.0F);
/*  81 */     this.loadbarcross.func_78787_b(64, 32);
/*  82 */     this.loadbarcross.field_78809_i = true;
/*  83 */     setRotation(this.loadbarcross, -0.5585054F, 0.0F, 0.0F);
/*  84 */     this.loadbarl = new ModelRenderer(this, 0, 15);
/*  85 */     this.loadbarl.func_78789_a(2.0F, -9.0F, -1.0F, 1, 11, 2);
/*  86 */     this.loadbarl.func_78793_a(0.0F, 0.0F, 0.0F);
/*  87 */     this.loadbarl.func_78787_b(64, 32);
/*  88 */     this.loadbarl.field_78809_i = true;
/*  89 */     setRotation(this.loadbarl, -0.5585054F, 0.0F, 0.0F);
/*  90 */     this.loadbarr = new ModelRenderer(this, 0, 15);
/*  91 */     this.loadbarr.func_78789_a(-3.0F, -9.0F, -1.0F, 1, 11, 2);
/*  92 */     this.loadbarr.func_78793_a(0.0F, 0.0F, 0.0F);
/*  93 */     this.loadbarr.func_78787_b(64, 32);
/*  94 */     this.loadbarr.field_78809_i = true;
/*  95 */     setRotation(this.loadbarr, -0.5585054F, 0.0F, 0.0F);
/*     */     
/*  97 */     this.crossl1 = new ModelRenderer(this, 0, 0);
/*  98 */     this.crossl1.func_78789_a(0.0F, 0.0F, -6.0F, 5, 2, 1);
/*  99 */     this.crossl1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 100 */     this.crossl1.func_78787_b(64, 32);
/* 101 */     this.crossl1.field_78809_i = true;
/* 102 */     setRotation(this.crossl1, 0.0F, -0.2443461F, 0.0F);
/* 103 */     this.crossl2 = new ModelRenderer(this, 0, 0);
/* 104 */     this.crossl2.func_78789_a(4.0F, 0.0F, -5.0F, 3, 2, 1);
/* 105 */     this.crossl2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 106 */     this.crossl2.func_78787_b(64, 32);
/* 107 */     this.crossl2.field_78809_i = true;
/* 108 */     setRotation(this.crossl2, 0.0F, -0.2443461F, 0.0F);
/* 109 */     this.crossl3 = new ModelRenderer(this, 0, 0);
/* 110 */     this.crossl3.func_78789_a(6.0F, 0.0F, -4.0F, 2, 2, 1);
/* 111 */     this.crossl3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 112 */     this.crossl3.func_78787_b(64, 32);
/* 113 */     this.crossl3.field_78809_i = true;
/* 114 */     setRotation(this.crossl3, 0.0F, -0.2443461F, 0.0F);
/* 115 */     this.crossr1 = new ModelRenderer(this, 0, 0);
/* 116 */     this.crossr1.func_78789_a(-5.0F, 0.0F, -6.0F, 5, 2, 1);
/* 117 */     this.crossr1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 118 */     this.crossr1.func_78787_b(64, 32);
/* 119 */     this.crossr1.field_78809_i = true;
/* 120 */     setRotation(this.crossr1, 0.0F, 0.2443461F, 0.0F);
/* 121 */     this.crossr2 = new ModelRenderer(this, 0, 0);
/* 122 */     this.crossr2.func_78789_a(-7.0F, 0.0F, -5.0F, 3, 2, 1);
/* 123 */     this.crossr2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 124 */     this.crossr2.func_78787_b(64, 32);
/* 125 */     this.crossr2.field_78809_i = true;
/* 126 */     setRotation(this.crossr2, 0.0F, 0.2443461F, 0.0F);
/* 127 */     this.crossr3 = new ModelRenderer(this, 0, 0);
/* 128 */     this.crossr3.func_78789_a(-8.0F, 0.0F, -4.0F, 2, 2, 1);
/* 129 */     this.crossr3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 130 */     this.crossr3.func_78787_b(64, 32);
/* 131 */     this.crossr3.field_78809_i = true;
/* 132 */     setRotation(this.crossr3, 0.0F, 0.2443461F, 0.0F);
/*     */     
/* 134 */     this.leg2 = new ModelRenderer(this, 20, 10);
/* 135 */     this.leg2.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 136 */     this.leg2.func_78793_a(0.0F, 12.0F, 0.0F);
/* 137 */     this.leg2.func_78787_b(64, 32);
/* 138 */     this.leg2.field_78809_i = true;
/* 139 */     setRotation(this.leg2, 0.5235988F, 1.570796F, 0.0F);
/* 140 */     this.tripod = new ModelRenderer(this, 13, 0);
/* 141 */     this.tripod.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 2, 3);
/* 142 */     this.tripod.func_78793_a(0.0F, 12.0F, 0.0F);
/* 143 */     this.tripod.func_78787_b(64, 32);
/* 144 */     this.tripod.field_78809_i = true;
/* 145 */     setRotation(this.tripod, 0.0F, 0.0F, 0.0F);
/* 146 */     this.leg3 = new ModelRenderer(this, 20, 10);
/* 147 */     this.leg3.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 148 */     this.leg3.func_78793_a(0.0F, 12.0F, 0.0F);
/* 149 */     this.leg3.func_78787_b(64, 32);
/* 150 */     this.leg3.field_78809_i = true;
/* 151 */     setRotation(this.leg3, 0.5235988F, 3.141593F, 0.0F);
/* 152 */     this.leg4 = new ModelRenderer(this, 20, 10);
/* 153 */     this.leg4.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 154 */     this.leg4.func_78793_a(0.0F, 12.0F, 0.0F);
/* 155 */     this.leg4.func_78787_b(64, 32);
/* 156 */     this.leg4.field_78809_i = true;
/* 157 */     setRotation(this.leg4, 0.5235988F, 4.712389F, 0.0F);
/* 158 */     this.leg1 = new ModelRenderer(this, 20, 10);
/* 159 */     this.leg1.func_78789_a(-1.0F, 1.0F, -1.0F, 2, 13, 2);
/* 160 */     this.leg1.func_78793_a(0.0F, 12.0F, 0.0F);
/* 161 */     this.leg1.func_78787_b(64, 32);
/* 162 */     this.leg1.field_78809_i = true;
/* 163 */     setRotation(this.leg1, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 165 */     this.crossbow.func_78792_a(this.ammobox);
/* 166 */     this.crossbow.func_78792_a(this.barrel);
/* 167 */     this.crossbow.func_78792_a(this.basebarcross);
/* 168 */     this.crossbow.func_78792_a(this.basebarr);
/* 169 */     this.crossbow.func_78792_a(this.basebarl);
/*     */     
/* 171 */     this.crossbow.func_78792_a(this.loadbarcross);
/* 172 */     this.crossbow.func_78792_a(this.loadbarl);
/* 173 */     this.crossbow.func_78792_a(this.loadbarr);
/*     */     
/* 175 */     this.crossbow.func_78792_a(this.crossl1);
/* 176 */     this.crossbow.func_78792_a(this.crossl2);
/* 177 */     this.crossbow.func_78792_a(this.crossl3);
/* 178 */     this.crossbow.func_78792_a(this.crossr1);
/* 179 */     this.crossbow.func_78792_a(this.crossr2);
/* 180 */     this.crossbow.func_78792_a(this.crossr3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 186 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 187 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 189 */     this.crossbow.func_78785_a(f5);
/*     */     
/* 191 */     this.leg2.func_78785_a(f5);
/* 192 */     this.tripod.func_78785_a(f5);
/* 193 */     this.leg3.func_78785_a(f5);
/* 194 */     this.leg4.func_78785_a(f5);
/* 195 */     this.leg1.func_78785_a(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 200 */     model.field_78795_f = x;
/* 201 */     model.field_78796_g = y;
/* 202 */     model.field_78808_h = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float headpitch, float headyaw, float p_78087_6_, Entity entity) {
/* 207 */     this.crossbow.field_78796_g = headpitch / 57.295776F;
/* 208 */     this.crossbow.field_78795_f = headyaw / 57.295776F;
/*     */     
/* 210 */     if (this.field_78095_p > -9990.0F) {
/*     */       
/* 212 */       float f6 = this.field_78095_p;
/* 213 */       this.crossl3
/* 214 */         .field_78796_g = -0.2F + MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F;
/* 215 */       this.crossr3
/* 216 */         .field_78796_g = 0.2F - MathHelper.func_76126_a(MathHelper.func_76129_c(f6) * 3.1415927F * 2.0F) * 0.2F;
/*     */     } 
/*     */     
/* 219 */     float lp = ((EntityTurretCrossbow)entity).loadProgressForRender;
/* 220 */     this.loadbarr
/* 221 */       .field_78795_f = -0.5F + MathHelper.func_76126_a(MathHelper.func_76129_c(lp) * 3.1415927F * 2.0F) * 0.5F;
/*     */     
/* 223 */     if (entity.func_184218_aH() && entity.func_184187_bx() != null && entity.func_184187_bx() instanceof net.minecraft.entity.item.EntityMinecart) {
/* 224 */       this.leg1.field_82908_p = -0.5F;
/* 225 */       this.leg2.field_82908_p = -0.5F;
/* 226 */       this.leg3.field_82908_p = -0.5F;
/* 227 */       this.leg4.field_82908_p = -0.5F;
/* 228 */       this.leg1.field_78795_f = 0.1F;
/* 229 */       this.leg2.field_78795_f = 0.1F;
/* 230 */       this.leg3.field_78795_f = 0.1F;
/* 231 */       this.leg4.field_78795_f = 0.1F;
/*     */     } else {
/* 233 */       this.leg1.field_82908_p = 0.0F;
/* 234 */       this.leg2.field_82908_p = 0.0F;
/* 235 */       this.leg3.field_82908_p = 0.0F;
/* 236 */       this.leg4.field_82908_p = 0.0F;
/* 237 */       this.leg1.field_78795_f = 0.5F;
/* 238 */       this.leg2.field_78795_f = 0.5F;
/* 239 */       this.leg3.field_78795_f = 0.5F;
/* 240 */       this.leg4.field_78795_f = 0.5F;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */