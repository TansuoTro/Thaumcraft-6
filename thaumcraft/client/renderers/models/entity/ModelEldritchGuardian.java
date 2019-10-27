/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelEldritchGuardian
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Hood4;
/*     */   ModelRenderer Cloak3;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer HoodEye;
/*     */   ModelRenderer Hood1;
/*     */   ModelRenderer Hood2;
/*     */   ModelRenderer Hood3;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer Cloak1;
/*     */   ModelRenderer Cloak2;
/*     */   ModelRenderer ShoulderplateTopR;
/*     */   ModelRenderer ShoulderplateR1;
/*     */   ModelRenderer ShoulderplateR2;
/*     */   ModelRenderer ShoulderplateR3;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ArmR3;
/*     */   ModelRenderer ArmL1;
/*     */   ModelRenderer ArmL3;
/*     */   ModelRenderer ArmR1;
/*     */   ModelRenderer ArmR2;
/*     */   ModelRenderer ArmL2;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderplateLtop;
/*     */   ModelRenderer ShoulderplateL1;
/*     */   ModelRenderer ShoulderplateL2;
/*     */   ModelRenderer ShoulderplateL3;
/*     */   ModelRenderer LegpanelR4;
/*     */   ModelRenderer LegpanelR5;
/*     */   ModelRenderer LegpanelR6;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer BackpanelR1;
/*     */   ModelRenderer BackpanelR2;
/*     */   ModelRenderer BackpanelR3;
/*     */   ModelRenderer BackpanelL3;
/*     */   ModelRenderer LegpanelL4;
/*     */   ModelRenderer LegpanelL5;
/*     */   ModelRenderer LegpanelL6;
/*     */   ModelRenderer SidepanelL1;
/*     */   ModelRenderer SidepanelR4;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer BackpanelL2;
/*     */   ModelRenderer LegpanelC1;
/*     */   ModelRenderer LegpanelC2;
/*     */   ModelRenderer LegpanelC3;
/*     */   ModelRenderer SidepanelR3;
/*     */   ModelRenderer SidepanelL4;
/*     */   ModelRenderer SidepanelL3;
/*     */   ModelRenderer SidepanelR2;
/*     */   ModelRenderer SidepanelL2;
/*     */   private float partialTicks;
/*     */   
/*     */   public ModelEldritchGuardian() {
/*  79 */     this.field_78090_t = 128;
/*  80 */     this.field_78089_u = 64;
/*     */     
/*  82 */     this.BeltR = new ModelRenderer(this, 76, 44);
/*  83 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/*  84 */     this.BeltR.func_78793_a(0.0F, -6.0F, 0.0F);
/*  85 */     this.BeltR.func_78787_b(128, 64);
/*  86 */     this.BeltR.field_78809_i = true;
/*  87 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/*  88 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/*  89 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/*  90 */     this.Mbelt.func_78793_a(0.0F, -6.0F, 0.0F);
/*  91 */     this.Mbelt.func_78787_b(128, 64);
/*  92 */     this.Mbelt.field_78809_i = true;
/*  93 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/*  94 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/*  95 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/*  96 */     this.MbeltL.func_78793_a(0.0F, -6.0F, 0.0F);
/*  97 */     this.MbeltL.func_78787_b(128, 64);
/*  98 */     this.MbeltL.field_78809_i = true;
/*  99 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/* 100 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/* 101 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 102 */     this.MbeltR.func_78793_a(0.0F, -6.0F, 0.0F);
/* 103 */     this.MbeltR.func_78787_b(128, 64);
/* 104 */     this.MbeltR.field_78809_i = true;
/* 105 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/* 106 */     this.BeltL = new ModelRenderer(this, 76, 44);
/* 107 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 108 */     this.BeltL.func_78793_a(0.0F, -6.0F, 0.0F);
/* 109 */     this.BeltL.func_78787_b(128, 64);
/* 110 */     this.BeltL.field_78809_i = true;
/* 111 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 114 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 115 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -4.0F, 8, 7, 2);
/* 116 */     this.Chestplate.func_78793_a(0.0F, -6.0F, 0.0F);
/* 117 */     this.Chestplate.func_78787_b(128, 64);
/* 118 */     this.Chestplate.field_78809_i = true;
/* 119 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 121 */     this.HoodEye = new ModelRenderer(this, 0, 0);
/* 122 */     this.HoodEye.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 123 */     this.HoodEye.func_78793_a(0.0F, -6.0F, 0.0F);
/* 124 */     this.HoodEye.func_78787_b(128, 64);
/* 125 */     this.HoodEye.field_78809_i = true;
/* 126 */     setRotation(this.HoodEye, 0.0F, 0.0F, 0.0F);
/*     */     
/* 128 */     this.Hood1 = new ModelRenderer(this, 40, 12);
/* 129 */     this.Hood1.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/* 130 */     this.Hood1.func_78793_a(0.0F, -6.0F, 0.0F);
/* 131 */     this.Hood1.func_78787_b(128, 64);
/* 132 */     this.Hood1.field_78809_i = true;
/* 133 */     setRotation(this.Hood1, 0.0F, 0.0F, 0.0F);
/* 134 */     this.Hood2 = new ModelRenderer(this, 36, 28);
/* 135 */     this.Hood2.func_78789_a(-3.5F, -8.7F, 2.0F, 7, 7, 3);
/* 136 */     this.Hood2.func_78787_b(128, 64);
/* 137 */     this.Hood2.field_78809_i = true;
/* 138 */     setRotation(this.Hood2, -0.2268928F, 0.0F, 0.0F);
/* 139 */     this.Hood3 = new ModelRenderer(this, 22, 19);
/* 140 */     this.Hood3.func_78789_a(-3.0F, -9.0F, 2.5F, 6, 6, 3);
/* 141 */     this.Hood3.func_78787_b(128, 64);
/* 142 */     this.Hood3.field_78809_i = true;
/* 143 */     setRotation(this.Hood3, -0.3490659F, 0.0F, 0.0F);
/* 144 */     this.Hood4 = new ModelRenderer(this, 40, 4);
/* 145 */     this.Hood4.func_78789_a(-2.5F, -9.7F, 3.5F, 5, 5, 3);
/* 146 */     this.Hood4.func_78787_b(128, 64);
/* 147 */     this.Hood4.field_78809_i = true;
/* 148 */     setRotation(this.Hood4, -0.5759587F, 0.0F, 0.0F);
/* 149 */     this.Hood1.func_78792_a(this.Hood2);
/* 150 */     this.Hood1.func_78792_a(this.Hood3);
/* 151 */     this.Hood1.func_78792_a(this.Hood4);
/*     */ 
/*     */     
/* 154 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 155 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/* 156 */     this.Backplate.func_78793_a(0.0F, -6.0F, 0.0F);
/* 157 */     this.Backplate.func_78787_b(128, 64);
/* 158 */     this.Backplate.field_78809_i = true;
/* 159 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 161 */     this.ShoulderplateTopR = new ModelRenderer(this, 110, 37);
/* 162 */     this.ShoulderplateTopR.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/* 163 */     this.ShoulderplateTopR.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 164 */     this.ShoulderplateTopR.func_78787_b(128, 64);
/* 165 */     this.ShoulderplateTopR.field_78809_i = true;
/* 166 */     setRotation(this.ShoulderplateTopR, -0.3665191F, 0.3141593F, 0.4363323F);
/* 167 */     this.ShoulderplateR1 = new ModelRenderer(this, 110, 45);
/* 168 */     this.ShoulderplateR1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/* 169 */     this.ShoulderplateR1.func_78793_a(5.0F, -4.0F, 0.0F);
/* 170 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 171 */     this.ShoulderplateR1.field_78809_i = true;
/* 172 */     setRotation(this.ShoulderplateR1, -0.3665191F, -0.3141593F, -0.4363323F);
/* 173 */     this.ShoulderplateR2 = new ModelRenderer(this, 94, 45);
/* 174 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/* 175 */     this.ShoulderplateR2.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 176 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 177 */     this.ShoulderplateR2.field_78809_i = true;
/* 178 */     setRotation(this.ShoulderplateR2, -0.3665191F, 0.3141593F, 0.4363323F);
/* 179 */     this.ShoulderplateR3 = new ModelRenderer(this, 94, 45);
/* 180 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/* 181 */     this.ShoulderplateR3.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 182 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 183 */     this.ShoulderplateR3.field_78809_i = true;
/* 184 */     setRotation(this.ShoulderplateR3, -0.3665191F, 0.3141593F, 0.4363323F);
/*     */     
/* 186 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 187 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/* 188 */     this.ShoulderR.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 189 */     this.ShoulderR.func_78787_b(128, 64);
/* 190 */     this.ShoulderR.field_78809_i = true;
/* 191 */     setRotation(this.ShoulderR, -0.3665191F, 0.122173F, 0.0349066F);
/*     */     
/* 193 */     this.ArmL1 = new ModelRenderer(this, 72, 8);
/* 194 */     this.ArmL1.func_78789_a(-1.0F, 2.5F, -1.5F, 4, 10, 5);
/* 195 */     this.ArmL1.func_78793_a(5.0F, -4.0F, 0.0F);
/* 196 */     this.ArmL1.func_78787_b(128, 64);
/* 197 */     this.ArmL1.field_78809_i = true;
/* 198 */     setRotation(this.ArmL1, -0.9599311F, -0.1047198F, -0.1919862F);
/* 199 */     this.ArmL2 = new ModelRenderer(this, 76, 28);
/* 200 */     this.ArmL2.func_78789_a(-1.0F, 9.5F, 3.5F, 4, 3, 3);
/* 201 */     this.ArmL2.func_78787_b(128, 64);
/* 202 */     this.ArmL2.field_78809_i = true;
/* 203 */     this.ArmL3 = new ModelRenderer(this, 76, 23);
/* 204 */     this.ArmL3.func_78789_a(-1.0F, 6.5F, 3.5F, 4, 3, 2);
/* 205 */     this.ArmL3.func_78787_b(128, 64);
/* 206 */     this.ArmL3.field_78809_i = true;
/* 207 */     this.ArmL1.func_78792_a(this.ArmL2);
/* 208 */     this.ArmL1.func_78792_a(this.ArmL3);
/*     */     
/* 210 */     this.ArmR1 = new ModelRenderer(this, 72, 8);
/* 211 */     this.ArmR1.func_78789_a(-3.0F, 2.5F, -1.5F, 4, 10, 5);
/* 212 */     this.ArmR1.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 213 */     this.ArmR1.func_78787_b(128, 64);
/* 214 */     this.ArmR1.field_78809_i = true;
/* 215 */     setRotation(this.ArmR1, -0.9599311F, 0.1047198F, 0.1919862F);
/* 216 */     this.ArmR2 = new ModelRenderer(this, 76, 28);
/* 217 */     this.ArmR2.func_78789_a(-3.0F, 9.5F, 3.5F, 4, 3, 3);
/* 218 */     this.ArmR2.func_78787_b(128, 64);
/* 219 */     this.ArmR2.field_78809_i = true;
/* 220 */     this.ArmR3 = new ModelRenderer(this, 76, 23);
/* 221 */     this.ArmR3.func_78789_a(-3.0F, 6.5F, 3.5F, 4, 3, 2);
/* 222 */     this.ArmR3.func_78787_b(128, 64);
/* 223 */     this.ArmR3.field_78809_i = true;
/* 224 */     this.ArmR1.func_78792_a(this.ArmR2);
/* 225 */     this.ArmR1.func_78792_a(this.ArmR3);
/*     */     
/* 227 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 228 */     this.ShoulderL.field_78809_i = true;
/* 229 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/* 230 */     this.ShoulderL.func_78793_a(5.0F, -4.0F, 0.0F);
/* 231 */     this.ShoulderL.func_78787_b(128, 64);
/*     */     
/* 233 */     setRotation(this.ShoulderL, -0.3665191F, -0.122173F, -0.0349066F);
/*     */     
/* 235 */     this.ShoulderplateLtop = new ModelRenderer(this, 110, 37);
/* 236 */     this.ShoulderplateLtop.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/* 237 */     this.ShoulderplateLtop.func_78793_a(5.0F, -4.0F, 0.0F);
/* 238 */     this.ShoulderplateLtop.func_78787_b(128, 64);
/* 239 */     this.ShoulderplateLtop.field_78809_i = true;
/* 240 */     setRotation(this.ShoulderplateLtop, -0.3665191F, -0.3141593F, -0.4363323F);
/* 241 */     this.ShoulderplateL1 = new ModelRenderer(this, 110, 45);
/* 242 */     this.ShoulderplateL1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/* 243 */     this.ShoulderplateL1.func_78793_a(-5.0F, -4.0F, 0.0F);
/* 244 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 245 */     this.ShoulderplateL1.field_78809_i = true;
/* 246 */     setRotation(this.ShoulderplateL1, -0.3665191F, 0.3141593F, 0.4363323F);
/* 247 */     this.ShoulderplateLtop.field_78809_i = false;
/*     */     
/* 249 */     this.ShoulderplateL2 = new ModelRenderer(this, 94, 45);
/* 250 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/* 251 */     this.ShoulderplateL2.func_78793_a(5.0F, -4.0F, 0.0F);
/* 252 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 253 */     this.ShoulderplateL2.field_78809_i = true;
/* 254 */     setRotation(this.ShoulderplateL2, -0.3665191F, -0.3141593F, -0.4363323F);
/* 255 */     this.ShoulderplateL2.field_78809_i = false;
/*     */     
/* 257 */     this.ShoulderplateL3 = new ModelRenderer(this, 94, 45);
/* 258 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/* 259 */     this.ShoulderplateL3.func_78793_a(5.0F, -4.0F, 0.0F);
/* 260 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 261 */     this.ShoulderplateL3.field_78809_i = true;
/* 262 */     setRotation(this.ShoulderplateL3, -0.3665191F, -0.3141593F, -0.4363323F);
/* 263 */     this.ShoulderplateL3.field_78809_i = false;
/* 264 */     this.LegpanelR4 = new ModelRenderer(this, 0, 43);
/* 265 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/* 266 */     this.LegpanelR4.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 267 */     this.LegpanelR4.func_78787_b(128, 64);
/* 268 */     this.LegpanelR4.field_78809_i = true;
/* 269 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/* 270 */     this.LegpanelR5 = new ModelRenderer(this, 0, 47);
/* 271 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/* 272 */     this.LegpanelR5.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 273 */     this.LegpanelR5.func_78787_b(128, 64);
/* 274 */     this.LegpanelR5.field_78809_i = true;
/* 275 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/* 276 */     this.LegpanelR6 = new ModelRenderer(this, 6, 43);
/* 277 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/* 278 */     this.LegpanelR6.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 279 */     this.LegpanelR6.func_78787_b(128, 64);
/* 280 */     this.LegpanelR6.field_78809_i = true;
/* 281 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 283 */     this.BackpanelR1 = new ModelRenderer(this, 0, 18);
/* 284 */     this.BackpanelR1.func_78789_a(-3.0F, 0.5F, 2.5F, 5, 3, 1);
/* 285 */     this.BackpanelR1.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 286 */     this.BackpanelR1.func_78787_b(128, 64);
/* 287 */     this.BackpanelR1.field_78809_i = true;
/* 288 */     setRotation(this.BackpanelR1, 0.4363323F, 0.0F, 0.0F);
/* 289 */     this.BackpanelR2 = new ModelRenderer(this, 0, 18);
/* 290 */     this.BackpanelR2.func_78789_a(-3.0F, 2.5F, 1.5F, 5, 3, 1);
/* 291 */     this.BackpanelR2.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 292 */     this.BackpanelR2.func_78787_b(128, 64);
/* 293 */     this.BackpanelR2.field_78809_i = true;
/* 294 */     setRotation(this.BackpanelR2, 0.4363323F, 0.0F, 0.0F);
/* 295 */     this.BackpanelR3 = new ModelRenderer(this, 0, 18);
/* 296 */     this.BackpanelR3.func_78789_a(-3.0F, 4.5F, 0.5F, 5, 3, 1);
/* 297 */     this.BackpanelR3.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 298 */     this.BackpanelR3.func_78787_b(128, 64);
/* 299 */     this.BackpanelR3.field_78809_i = true;
/* 300 */     setRotation(this.BackpanelR3, 0.4363323F, 0.0F, 0.0F);
/* 301 */     this.BackpanelL3 = new ModelRenderer(this, 0, 18);
/* 302 */     this.BackpanelL3.func_78789_a(-2.0F, 4.5F, 0.5F, 5, 3, 1);
/* 303 */     this.BackpanelL3.func_78793_a(2.0F, 6.0F, 0.0F);
/* 304 */     this.BackpanelL3.func_78787_b(128, 64);
/* 305 */     this.BackpanelL3.field_78809_i = true;
/* 306 */     setRotation(this.BackpanelL3, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 308 */     this.LegpanelL4 = new ModelRenderer(this, 0, 43);
/* 309 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/* 310 */     this.LegpanelL4.func_78793_a(2.0F, 6.0F, 0.0F);
/* 311 */     this.LegpanelL4.func_78787_b(128, 64);
/* 312 */     this.LegpanelL4.field_78809_i = true;
/* 313 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/* 314 */     this.LegpanelL4.field_78809_i = false;
/*     */     
/* 316 */     this.LegpanelL5 = new ModelRenderer(this, 0, 47);
/* 317 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/* 318 */     this.LegpanelL5.func_78793_a(2.0F, 6.0F, 0.0F);
/* 319 */     this.LegpanelL5.func_78787_b(128, 64);
/* 320 */     this.LegpanelL5.field_78809_i = true;
/* 321 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/* 322 */     this.LegpanelL5.field_78809_i = false;
/*     */     
/* 324 */     this.LegpanelL6 = new ModelRenderer(this, 6, 43);
/* 325 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/* 326 */     this.LegpanelL6.func_78793_a(2.0F, 6.0F, 0.0F);
/* 327 */     this.LegpanelL6.func_78787_b(128, 64);
/* 328 */     this.LegpanelL6.field_78809_i = true;
/* 329 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/* 330 */     this.LegpanelL6.field_78809_i = false;
/*     */     
/* 332 */     this.BackpanelL1 = new ModelRenderer(this, 0, 18);
/* 333 */     this.BackpanelL1.func_78789_a(-2.0F, 0.5F, 2.5F, 5, 3, 1);
/* 334 */     this.BackpanelL1.func_78793_a(2.0F, 6.0F, 0.0F);
/* 335 */     this.BackpanelL1.func_78787_b(128, 64);
/* 336 */     this.BackpanelL1.field_78809_i = true;
/* 337 */     setRotation(this.BackpanelL1, 0.4363323F, 0.0F, 0.0F);
/* 338 */     this.BackpanelL2 = new ModelRenderer(this, 0, 18);
/* 339 */     this.BackpanelL2.func_78789_a(-2.0F, 2.5F, 1.5F, 5, 3, 1);
/* 340 */     this.BackpanelL2.func_78793_a(2.0F, 6.0F, 0.0F);
/* 341 */     this.BackpanelL2.func_78787_b(128, 64);
/* 342 */     this.BackpanelL2.field_78809_i = true;
/* 343 */     setRotation(this.BackpanelL2, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 345 */     this.SidepanelL1 = new ModelRenderer(this, 0, 22);
/* 346 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/* 347 */     this.SidepanelL1.func_78793_a(2.0F, 6.0F, 0.0F);
/* 348 */     this.SidepanelL1.func_78787_b(128, 64);
/* 349 */     this.SidepanelL1.field_78809_i = true;
/* 350 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/* 351 */     this.SidepanelR1 = new ModelRenderer(this, 0, 22);
/* 352 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/* 353 */     this.SidepanelR1.func_78793_a(-2.0F, 6.0F, 0.0F);
/* 354 */     this.SidepanelR1.func_78787_b(128, 64);
/* 355 */     this.SidepanelR1.field_78809_i = true;
/* 356 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */ 
/*     */ 
/*     */     
/* 360 */     this.SidepanelR2 = new ModelRenderer(this, 0, 54);
/* 361 */     this.SidepanelR2.func_78789_a(0.0F, 0.0F, -0.5F, 1, 5, 5);
/* 362 */     this.SidepanelR2.func_78793_a(-4.5F, 9.5F, -2.0F);
/* 363 */     this.SidepanelR2.func_78787_b(128, 64);
/* 364 */     this.SidepanelR2.field_78809_i = true;
/* 365 */     setRotation(this.SidepanelR2, 0.0F, 0.0F, 0.122173F);
/* 366 */     this.SidepanelR3 = new ModelRenderer(this, 0, 35);
/* 367 */     this.SidepanelR3.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 368 */     this.SidepanelR3.func_78793_a(0.0F, 5.0F, 0.0F);
/* 369 */     this.SidepanelR3.func_78787_b(128, 64);
/* 370 */     this.SidepanelR3.field_78809_i = true;
/* 371 */     setRotation(this.SidepanelR3, 0.0F, 0.0F, 0.296706F);
/* 372 */     this.SidepanelR4 = new ModelRenderer(this, 24, 35);
/* 373 */     this.SidepanelR4.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 374 */     this.SidepanelR4.func_78793_a(0.0F, 3.0F, 0.0F);
/* 375 */     this.SidepanelR4.func_78787_b(128, 64);
/* 376 */     this.SidepanelR4.field_78809_i = true;
/* 377 */     setRotation(this.SidepanelR4, 0.0F, 0.0F, 0.5235988F);
/*     */ 
/*     */     
/* 380 */     this.SidepanelL2 = new ModelRenderer(this, 0, 54);
/* 381 */     this.SidepanelL2.func_78789_a(0.0F, 0.0F, -0.5F, 1, 5, 5);
/* 382 */     this.SidepanelL2.func_78793_a(4.5F, 9.5F, -2.0F);
/* 383 */     this.SidepanelL2.func_78787_b(128, 64);
/* 384 */     this.SidepanelL2.field_78809_i = true;
/* 385 */     setRotation(this.SidepanelL2, 0.0F, 0.0F, -0.122173F);
/* 386 */     this.SidepanelL3 = new ModelRenderer(this, 0, 35);
/* 387 */     this.SidepanelL3.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 388 */     this.SidepanelL3.func_78793_a(0.0F, 5.0F, 0.0F);
/* 389 */     this.SidepanelL3.func_78787_b(128, 64);
/* 390 */     this.SidepanelL3.field_78809_i = true;
/* 391 */     setRotation(this.SidepanelL3, 0.0F, 0.0F, -0.296706F);
/* 392 */     this.SidepanelL4 = new ModelRenderer(this, 24, 35);
/* 393 */     this.SidepanelL4.func_78789_a(0.0F, 0.0F, -0.5F, 1, 3, 5);
/* 394 */     this.SidepanelL4.func_78793_a(0.0F, 3.0F, 0.0F);
/* 395 */     this.SidepanelL4.func_78787_b(128, 64);
/* 396 */     this.SidepanelL4.field_78809_i = true;
/* 397 */     setRotation(this.SidepanelL4, 0.0F, 0.0F, -0.5235988F);
/*     */ 
/*     */     
/* 400 */     this.LegpanelC1 = new ModelRenderer(this, 16, 45);
/* 401 */     this.LegpanelC1.func_78789_a(-3.0F, 0.0F, -0.5F, 6, 8, 1);
/* 402 */     this.LegpanelC1.func_78793_a(0.0F, 5.5F, -3.0F);
/* 403 */     this.LegpanelC1.func_78787_b(128, 64);
/* 404 */     this.LegpanelC1.field_78809_i = true;
/* 405 */     setRotation(this.LegpanelC1, 0.0F, 0.0F, 0.0F);
/* 406 */     this.LegpanelC2 = new ModelRenderer(this, 16, 54);
/* 407 */     this.LegpanelC2.func_78789_a(-3.0F, 0.0F, -0.5F, 6, 4, 1);
/* 408 */     this.LegpanelC2.func_78793_a(0.0F, 8.0F, 0.0F);
/* 409 */     this.LegpanelC2.func_78787_b(128, 64);
/* 410 */     this.LegpanelC2.field_78809_i = true;
/* 411 */     setRotation(this.LegpanelC2, 0.0F, 0.0F, 0.0F);
/* 412 */     this.LegpanelC3 = new ModelRenderer(this, 32, 59);
/* 413 */     this.LegpanelC3.func_78789_a(-3.0F, 0.0F, -0.5F, 6, 4, 1);
/* 414 */     this.LegpanelC3.func_78793_a(0.0F, 4.0F, 0.0F);
/* 415 */     this.LegpanelC3.func_78787_b(128, 64);
/* 416 */     this.LegpanelC3.field_78809_i = true;
/* 417 */     setRotation(this.LegpanelC3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 419 */     this.Cloak1 = new ModelRenderer(this, 106, 0);
/* 420 */     this.Cloak1.func_78789_a(0.0F, 0.0F, -0.5F, 10, 18, 1);
/* 421 */     this.Cloak1.func_78793_a(-5.0F, -6.0F, 4.0F);
/* 422 */     this.Cloak1.func_78787_b(128, 64);
/* 423 */     this.Cloak1.field_78809_i = true;
/* 424 */     setRotation(this.Cloak1, 0.0F, 0.0F, 0.0F);
/* 425 */     this.Cloak2 = new ModelRenderer(this, 106, 19);
/* 426 */     this.Cloak2.func_78789_a(0.0F, 0.0F, -0.5F, 10, 4, 1);
/* 427 */     this.Cloak2.func_78793_a(0.0F, 18.0F, 0.0F);
/* 428 */     this.Cloak2.func_78787_b(128, 64);
/* 429 */     this.Cloak2.field_78809_i = true;
/* 430 */     setRotation(this.Cloak2, 0.0F, 0.0F, 0.0F);
/* 431 */     this.Cloak3 = new ModelRenderer(this, 106, 24);
/* 432 */     this.Cloak3.func_78789_a(0.0F, 0.0F, -0.5F, 10, 4, 1);
/* 433 */     this.Cloak3.func_78793_a(0.0F, 4.0F, 0.0F);
/* 434 */     this.Cloak3.func_78787_b(128, 64);
/* 435 */     this.Cloak3.field_78809_i = true;
/* 436 */     setRotation(this.Cloak3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 438 */     this.LegpanelC1.func_78792_a(this.LegpanelC2);
/* 439 */     this.LegpanelC2.func_78792_a(this.LegpanelC3);
/* 440 */     this.SidepanelL2.func_78792_a(this.SidepanelL3);
/* 441 */     this.SidepanelL3.func_78792_a(this.SidepanelL4);
/* 442 */     this.SidepanelR2.func_78792_a(this.SidepanelR3);
/* 443 */     this.SidepanelR3.func_78792_a(this.SidepanelR4);
/* 444 */     this.Cloak1.func_78792_a(this.Cloak2);
/* 445 */     this.Cloak2.func_78792_a(this.Cloak3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 455 */   public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) { this.partialTicks = p_78086_4_; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 461 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 462 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 464 */     this.BeltR.func_78785_a(f5);
/* 465 */     this.Mbelt.func_78785_a(f5);
/* 466 */     this.MbeltL.func_78785_a(f5);
/* 467 */     this.MbeltR.func_78785_a(f5);
/* 468 */     this.BeltL.func_78785_a(f5);
/*     */     
/* 470 */     this.Chestplate.func_78785_a(f5);
/* 471 */     this.Hood1.func_78785_a(f5);
/*     */     
/* 473 */     this.Backplate.func_78785_a(f5);
/*     */     
/* 475 */     this.ShoulderplateTopR.func_78785_a(f5);
/* 476 */     this.ShoulderplateR1.func_78785_a(f5);
/* 477 */     this.ShoulderplateR2.func_78785_a(f5);
/* 478 */     this.ShoulderplateR3.func_78785_a(f5);
/*     */     
/* 480 */     this.ShoulderR.func_78785_a(f5);
/*     */     
/* 482 */     this.ArmL1.func_78785_a(f5);
/* 483 */     this.ArmR1.func_78785_a(f5);
/*     */     
/* 485 */     this.ShoulderL.func_78785_a(f5);
/* 486 */     this.ShoulderplateLtop.func_78785_a(f5);
/* 487 */     this.ShoulderplateL1.func_78785_a(f5);
/* 488 */     this.ShoulderplateL2.func_78785_a(f5);
/* 489 */     this.ShoulderplateL3.func_78785_a(f5);
/* 490 */     this.LegpanelR4.func_78785_a(f5);
/* 491 */     this.LegpanelR5.func_78785_a(f5);
/* 492 */     this.LegpanelR6.func_78785_a(f5);
/*     */     
/* 494 */     this.BackpanelR1.func_78785_a(f5);
/* 495 */     this.BackpanelR2.func_78785_a(f5);
/* 496 */     this.BackpanelR3.func_78785_a(f5);
/* 497 */     this.BackpanelL3.func_78785_a(f5);
/* 498 */     this.LegpanelL4.func_78785_a(f5);
/* 499 */     this.LegpanelL5.func_78785_a(f5);
/* 500 */     this.LegpanelL6.func_78785_a(f5);
/*     */     
/* 502 */     this.BackpanelL1.func_78785_a(f5);
/* 503 */     this.BackpanelL2.func_78785_a(f5);
/*     */ 
/*     */     
/* 506 */     this.Cloak1.func_78785_a(f5);
/*     */     
/* 508 */     this.SidepanelR1.func_78785_a(f5);
/* 509 */     this.SidepanelL1.func_78785_a(f5);
/*     */     
/* 511 */     this.SidepanelL2.func_78785_a(f5);
/* 512 */     this.SidepanelR2.func_78785_a(f5);
/*     */     
/* 514 */     this.LegpanelC1.func_78785_a(f5);
/*     */     
/* 516 */     if (entity instanceof EntityEldritchWarden) {
/* 517 */       GL11.glPushMatrix();
/* 518 */       GL11.glEnable(3042);
/* 519 */       GL11.glBlendFunc(770, 1);
/* 520 */       GL11.glScaled(1.01D, 1.01D, 1.01D);
/* 521 */       int j = (int)(195.0F + MathHelper.func_76126_a(entity.field_70173_aa / 3.0F) * 15.0F + 15.0F);
/* 522 */       int k = j % 65536;
/* 523 */       int l = j / 65536;
/* 524 */       OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, k / 1.0F, l / 1.0F);
/* 525 */       this.HoodEye.func_78785_a(f5);
/* 526 */       GL11.glDisable(3042);
/* 527 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
/* 536 */     super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
/*     */     
/* 538 */     this.Hood1.field_78796_g = par4 / 57.295776F;
/* 539 */     this.Hood1.field_78795_f = par5 / 57.295776F;
/* 540 */     this.HoodEye.field_78795_f = this.Hood1.field_78795_f;
/* 541 */     this.HoodEye.field_78796_g = this.Hood1.field_78796_g;
/* 542 */     float alr = 0.0F;
/* 543 */     float all = 0.0F;
/* 544 */     if (entity instanceof EntityEldritchGuardian) {
/* 545 */       alr = ((EntityEldritchGuardian)entity).armLiftR;
/* 546 */       all = ((EntityEldritchGuardian)entity).armLiftL;
/*     */     } 
/* 548 */     if (entity instanceof EntityEldritchWarden) {
/* 549 */       alr = ((EntityEldritchWarden)entity).armLiftR;
/* 550 */       all = ((EntityEldritchWarden)entity).armLiftL;
/*     */     } 
/* 552 */     this.ArmL1.field_78795_f = -1.0F - all + MathHelper.func_76126_a(((entity.field_70173_aa + 20) + this.partialTicks) / 10.0F) * 0.08F;
/* 553 */     this.ArmR1.field_78795_f = -1.0F - alr + MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 10.0F) * 0.08F;
/*     */     
/* 555 */     this.LegpanelC1.field_78795_f = -0.15F + MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 8.0F) * 0.12F;
/* 556 */     this.LegpanelC2.field_78795_f = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 5.0F) / 8.0F) * 0.13F;
/* 557 */     this.LegpanelC3.field_78795_f = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 10.0F) / 8.0F) * 0.14F;
/*     */     
/* 559 */     this.Cloak1.field_78795_f = 0.2F + MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 7.0F) * 0.08F;
/* 560 */     this.Cloak2.field_78795_f = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 5.0F) / 7.0F) * 0.1F;
/* 561 */     this.Cloak3.field_78795_f = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 10.0F) / 7.0F) * 0.12F;
/*     */     
/* 563 */     this.SidepanelL2.field_78808_h = -0.2F + MathHelper.func_76126_a(((entity.field_70173_aa + 10) + this.partialTicks) / 8.0F) * 0.12F;
/* 564 */     this.SidepanelL3.field_78808_h = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks + 5.0F) / 8.0F) * 0.13F;
/* 565 */     this.SidepanelL4.field_78808_h = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks) / 8.0F) * 0.14F;
/*     */     
/* 567 */     this.SidepanelR2.field_78808_h = 0.2F + MathHelper.func_76126_a(((entity.field_70173_aa - 5) + this.partialTicks) / 8.0F) * 0.12F;
/* 568 */     this.SidepanelR3.field_78808_h = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 10.0F) / 8.0F) * 0.13F;
/* 569 */     this.SidepanelR4.field_78808_h = MathHelper.func_76126_a((entity.field_70173_aa + this.partialTicks - 15.0F) / 8.0F) * 0.14F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 574 */     model.field_78795_f = x;
/* 575 */     model.field_78796_g = y;
/* 576 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelEldritchGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */