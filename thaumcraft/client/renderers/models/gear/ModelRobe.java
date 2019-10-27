/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelRobe
/*     */   extends ModelCustomArmor
/*     */ {
/*     */   ModelRenderer Hood1;
/*     */   ModelRenderer Hood2;
/*     */   ModelRenderer Hood3;
/*     */   ModelRenderer Hood4;
/*     */   ModelRenderer Chestthing;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltB;
/*     */   ModelRenderer ClothchestL;
/*     */   ModelRenderer ClothchestR;
/*     */   ModelRenderer Book;
/*     */   ModelRenderer Scroll;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer ShoulderplateR1;
/*     */   ModelRenderer ShoulderplateR2;
/*     */   ModelRenderer ShoulderplateR3;
/*     */   ModelRenderer ShoulderplateTopR;
/*     */   ModelRenderer RArm1;
/*     */   ModelRenderer RArm2;
/*     */   ModelRenderer RArm3;
/*     */   ModelRenderer LArm1;
/*     */   ModelRenderer LArm2;
/*     */   ModelRenderer LArm3;
/*     */   ModelRenderer ShoulderplateL1;
/*     */   ModelRenderer ShoulderplateL2;
/*     */   ModelRenderer ShoulderplateL3;
/*     */   ModelRenderer ShoulderplateTopL;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ClothBackR3;
/*     */   ModelRenderer FrontclothR2;
/*     */   ModelRenderer FrontclothR1;
/*     */   ModelRenderer SideclothR2;
/*     */   ModelRenderer SideclothR1;
/*     */   ModelRenderer SideclothR3;
/*     */   ModelRenderer ClothBackR1;
/*     */   ModelRenderer ClothBackR2;
/*     */   ModelRenderer SidepanelR1;
/*     */   ModelRenderer LegpanelR6;
/*     */   ModelRenderer LegpanelR5;
/*     */   ModelRenderer LegpanelR4;
/*     */   ModelRenderer FrontclothL2;
/*     */   ModelRenderer ClothBackL3;
/*     */   ModelRenderer ClothBackL1;
/*     */   ModelRenderer FrontclothL1;
/*     */   ModelRenderer SideclothL2;
/*     */   ModelRenderer SideclothL3;
/*     */   ModelRenderer Focipouch;
/*     */   ModelRenderer SideclothL1;
/*     */   ModelRenderer ClothBackL2;
/*     */   ModelRenderer LegpanelL4;
/*     */   ModelRenderer LegpanelL5;
/*     */   ModelRenderer LegpanelL6;
/*     */   ModelRenderer SidepanelL1;
/*     */   
/*     */   public ModelRobe(float f) {
/*  79 */     super(f, 0, 128, 64);
/*  80 */     this.field_78090_t = 128;
/*  81 */     this.field_78089_u = 64;
/*     */ 
/*     */     
/*  84 */     this.Hood1 = new ModelRenderer(this, 16, 7);
/*  85 */     this.Hood1.func_78789_a(-4.5F, -9.0F, -4.6F, 9, 9, 9);
/*  86 */     this.Hood1.func_78787_b(128, 64);
/*  87 */     setRotation(this.Hood1, 0.0F, 0.0F, 0.0F);
/*     */     
/*  89 */     this.Hood2 = new ModelRenderer(this, 52, 13);
/*  90 */     this.Hood2.func_78789_a(-4.0F, -9.7F, 2.0F, 8, 9, 3);
/*  91 */     this.Hood2.func_78787_b(128, 64);
/*  92 */     setRotation(this.Hood2, -0.2268928F, 0.0F, 0.0F);
/*     */     
/*  94 */     this.Hood3 = new ModelRenderer(this, 52, 14);
/*  95 */     this.Hood3.func_78789_a(-3.5F, -10.0F, 3.5F, 7, 8, 3);
/*  96 */     this.Hood3.func_78787_b(128, 64);
/*  97 */     setRotation(this.Hood3, -0.3490659F, 0.0F, 0.0F);
/*     */     
/*  99 */     this.Hood4 = new ModelRenderer(this, 53, 15);
/* 100 */     this.Hood4.func_78789_a(-3.0F, -10.7F, 3.5F, 6, 7, 3);
/* 101 */     this.Hood4.func_78787_b(128, 64);
/* 102 */     setRotation(this.Hood4, -0.5759587F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 105 */     this.Chestthing = new ModelRenderer(this, 56, 50);
/* 106 */     this.Chestthing.func_78789_a(-2.5F, 1.0F, -4.0F, 5, 7, 1);
/* 107 */     this.Chestthing.func_78787_b(128, 64);
/* 108 */     setRotation(this.Chestthing, 0.0F, 0.0F, 0.0F);
/*     */     
/* 110 */     this.Mbelt = new ModelRenderer(this, 16, 55);
/* 111 */     this.Mbelt.func_78789_a(-4.0F, 7.0F, -3.0F, 8, 5, 1);
/* 112 */     this.Mbelt.func_78787_b(128, 64);
/* 113 */     setRotation(this.Mbelt, 0.0F, 0.0F, 0.0F);
/*     */     
/* 115 */     this.MbeltB = new ModelRenderer(this, 16, 55);
/* 116 */     this.MbeltB.func_78789_a(-4.0F, 7.0F, -4.0F, 8, 5, 1);
/* 117 */     this.MbeltB.func_78787_b(128, 64);
/* 118 */     setRotation(this.MbeltB, 0.0F, 3.141593F, 0.0F);
/*     */     
/* 120 */     this.ClothchestL = new ModelRenderer(this, 108, 38);
/* 121 */     this.ClothchestL.field_78809_i = true;
/* 122 */     this.ClothchestL.func_78789_a(2.1F, 0.5F, -3.5F, 2, 8, 1);
/* 123 */     this.ClothchestL.func_78787_b(128, 64);
/* 124 */     setRotation(this.ClothchestL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 126 */     this.ClothchestR = new ModelRenderer(this, 108, 38);
/* 127 */     this.ClothchestR.func_78789_a(-4.1F, 0.5F, -3.5F, 2, 8, 1);
/* 128 */     this.ClothchestR.func_78787_b(128, 64);
/* 129 */     setRotation(this.ClothchestR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 131 */     this.Book = new ModelRenderer(this, 81, 16);
/* 132 */     this.Book.func_78789_a(1.0F, 0.0F, 4.0F, 5, 7, 2);
/* 133 */     this.Book.func_78787_b(128, 64);
/* 134 */     setRotation(this.Book, 0.0F, 0.0F, 0.7679449F);
/*     */     
/* 136 */     this.Scroll = new ModelRenderer(this, 78, 25);
/* 137 */     this.Scroll.func_78789_a(-2.0F, 9.5F, 4.0F, 8, 3, 3);
/* 138 */     this.Scroll.func_78787_b(128, 64);
/* 139 */     setRotation(this.Scroll, 0.0F, 0.0F, 0.1919862F);
/*     */     
/* 141 */     this.BeltR = new ModelRenderer(this, 16, 36);
/* 142 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/* 143 */     this.BeltR.func_78787_b(128, 64);
/* 144 */     setRotation(this.BeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 146 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 147 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 1.9F, 8, 11, 2);
/* 148 */     this.Backplate.func_78787_b(128, 64);
/* 149 */     setRotation(this.Backplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 151 */     this.MbeltL = new ModelRenderer(this, 16, 36);
/* 152 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 153 */     this.MbeltL.func_78787_b(128, 64);
/* 154 */     setRotation(this.MbeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 156 */     this.MbeltR = new ModelRenderer(this, 16, 36);
/* 157 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 158 */     this.MbeltR.func_78787_b(128, 64);
/* 159 */     setRotation(this.MbeltR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 161 */     this.BeltL = new ModelRenderer(this, 16, 36);
/* 162 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/* 163 */     this.BeltL.func_78787_b(128, 64);
/* 164 */     setRotation(this.BeltL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 166 */     this.Chestplate = new ModelRenderer(this, 16, 25);
/* 167 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -3.0F, 8, 6, 1);
/* 168 */     this.Chestplate.func_78787_b(128, 64);
/* 169 */     setRotation(this.Chestplate, 0.0F, 0.0F, 0.0F);
/*     */     
/* 171 */     this.ShoulderplateR1 = new ModelRenderer(this, 56, 33);
/* 172 */     this.ShoulderplateR1.func_78789_a(-4.5F, -1.5F, -3.5F, 1, 4, 7);
/*     */     
/* 174 */     this.ShoulderplateR1.func_78787_b(128, 64);
/* 175 */     setRotation(this.ShoulderplateR1, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 177 */     this.ShoulderplateR2 = new ModelRenderer(this, 40, 33);
/* 178 */     this.ShoulderplateR2.func_78789_a(-3.5F, 1.5F, -3.5F, 1, 3, 7);
/*     */     
/* 180 */     this.ShoulderplateR2.func_78787_b(128, 64);
/* 181 */     setRotation(this.ShoulderplateR2, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 183 */     this.ShoulderplateR3 = new ModelRenderer(this, 40, 33);
/* 184 */     this.ShoulderplateR3.func_78789_a(-2.5F, 3.5F, -3.5F, 1, 3, 7);
/*     */     
/* 186 */     this.ShoulderplateR3.func_78787_b(128, 64);
/* 187 */     setRotation(this.ShoulderplateR3, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 189 */     this.ShoulderplateTopR = new ModelRenderer(this, 56, 25);
/* 190 */     this.ShoulderplateTopR.func_78789_a(-5.5F, -2.5F, -3.5F, 2, 1, 7);
/*     */     
/* 192 */     this.ShoulderplateTopR.func_78787_b(128, 64);
/* 193 */     setRotation(this.ShoulderplateTopR, 0.0F, 0.0F, 0.4363323F);
/*     */     
/* 195 */     this.RArm1 = new ModelRenderer(this, 88, 39);
/* 196 */     this.RArm1.func_78789_a(-3.5F, 2.5F, -2.5F, 5, 7, 5);
/*     */     
/* 198 */     this.RArm1.func_78787_b(128, 64);
/* 199 */     setRotation(this.RArm1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 201 */     this.RArm2 = new ModelRenderer(this, 76, 32);
/* 202 */     this.RArm2.func_78789_a(-3.0F, 5.5F, 2.5F, 4, 4, 2);
/*     */     
/* 204 */     this.RArm2.func_78787_b(128, 64);
/* 205 */     setRotation(this.RArm2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 207 */     this.RArm3 = new ModelRenderer(this, 88, 32);
/* 208 */     this.RArm3.func_78789_a(-2.5F, 3.5F, 2.5F, 3, 2, 1);
/*     */     
/* 210 */     this.RArm3.func_78787_b(128, 64);
/* 211 */     setRotation(this.RArm3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 213 */     this.ShoulderplateL1 = new ModelRenderer(this, 56, 33);
/* 214 */     this.ShoulderplateL1.func_78789_a(3.5F, -1.5F, -3.5F, 1, 4, 7);
/*     */     
/* 216 */     this.ShoulderplateL1.func_78787_b(128, 64);
/* 217 */     setRotation(this.ShoulderplateL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 219 */     this.ShoulderplateL2 = new ModelRenderer(this, 40, 33);
/* 220 */     this.ShoulderplateL2.func_78789_a(2.5F, 1.5F, -3.5F, 1, 3, 7);
/*     */     
/* 222 */     this.ShoulderplateL2.func_78787_b(128, 64);
/* 223 */     setRotation(this.ShoulderplateL2, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 225 */     this.ShoulderplateL3 = new ModelRenderer(this, 40, 33);
/* 226 */     this.ShoulderplateL3.func_78789_a(1.5F, 3.5F, -3.5F, 1, 3, 7);
/*     */     
/* 228 */     this.ShoulderplateL3.func_78787_b(128, 64);
/* 229 */     setRotation(this.ShoulderplateL3, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 231 */     this.ShoulderplateTopL = new ModelRenderer(this, 56, 25);
/* 232 */     this.ShoulderplateTopL.func_78789_a(3.5F, -2.5F, -3.5F, 2, 1, 7);
/*     */     
/* 234 */     this.ShoulderplateTopL.func_78787_b(128, 64);
/* 235 */     setRotation(this.ShoulderplateTopL, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 237 */     this.ShoulderR = new ModelRenderer(this, 16, 45);
/* 238 */     this.ShoulderR.field_78809_i = true;
/* 239 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 241 */     this.ShoulderR.func_78787_b(128, 64);
/* 242 */     setRotation(this.ShoulderR, 0.0F, 0.0F, 0.0F);
/*     */     
/* 244 */     this.LArm1 = new ModelRenderer(this, 88, 39);
/* 245 */     this.LArm1.field_78809_i = true;
/* 246 */     this.LArm1.func_78789_a(-1.5F, 2.5F, -2.5F, 5, 7, 5);
/*     */     
/* 248 */     this.LArm1.func_78787_b(128, 64);
/* 249 */     setRotation(this.LArm1, 0.0F, 0.0F, 0.0F);
/*     */     
/* 251 */     this.LArm2 = new ModelRenderer(this, 76, 32);
/* 252 */     this.LArm2.func_78789_a(-1.0F, 5.5F, 2.5F, 4, 4, 2);
/*     */     
/* 254 */     this.LArm2.func_78787_b(128, 64);
/* 255 */     setRotation(this.LArm2, 0.0F, 0.0F, 0.0F);
/*     */     
/* 257 */     this.LArm3 = new ModelRenderer(this, 88, 32);
/* 258 */     this.LArm3.func_78789_a(-0.5F, 3.5F, 2.5F, 3, 2, 1);
/*     */     
/* 260 */     this.LArm3.func_78787_b(128, 64);
/* 261 */     setRotation(this.LArm3, 0.0F, 0.0F, 0.0F);
/*     */     
/* 263 */     this.ShoulderL = new ModelRenderer(this, 16, 45);
/* 264 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/*     */     
/* 266 */     this.ShoulderL.func_78787_b(128, 64);
/* 267 */     this.ShoulderL.field_78809_i = true;
/* 268 */     setRotation(this.ShoulderL, 0.0F, 0.0F, 0.0F);
/*     */     
/* 270 */     this.FrontclothR1 = new ModelRenderer(this, 108, 38);
/* 271 */     this.FrontclothR1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 272 */     this.FrontclothR1.func_78793_a(-3.0F, 11.0F, -2.9F);
/* 273 */     this.FrontclothR1.func_78787_b(128, 64);
/* 274 */     setRotation(this.FrontclothR1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 276 */     this.FrontclothR2 = new ModelRenderer(this, 108, 47);
/* 277 */     this.FrontclothR2.func_78789_a(0.0F, 7.5F, 1.7F, 3, 3, 1);
/* 278 */     this.FrontclothR2.func_78793_a(-3.0F, 11.0F, -2.9F);
/* 279 */     this.FrontclothR2.func_78787_b(128, 64);
/* 280 */     setRotation(this.FrontclothR2, -0.3316126F, 0.0F, 0.0F);
/*     */     
/* 282 */     this.FrontclothL1 = new ModelRenderer(this, 108, 38);
/* 283 */     this.FrontclothL1.field_78809_i = true;
/* 284 */     this.FrontclothL1.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 285 */     this.FrontclothL1.func_78793_a(0.0F, 11.0F, -2.9F);
/* 286 */     this.FrontclothL1.func_78787_b(128, 64);
/* 287 */     setRotation(this.FrontclothL1, -0.1047198F, 0.0F, 0.0F);
/*     */     
/* 289 */     this.FrontclothL2 = new ModelRenderer(this, 108, 47);
/* 290 */     this.FrontclothL2.field_78809_i = true;
/* 291 */     this.FrontclothL2.func_78789_a(0.0F, 7.5F, 1.7F, 3, 3, 1);
/* 292 */     this.FrontclothL2.func_78793_a(0.0F, 11.0F, -2.9F);
/* 293 */     this.FrontclothL2.func_78787_b(128, 64);
/* 294 */     setRotation(this.FrontclothL2, -0.3316126F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */     
/* 298 */     this.ClothBackR1 = new ModelRenderer(this, 118, 16);
/* 299 */     this.ClothBackR1.field_78809_i = true;
/* 300 */     this.ClothBackR1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 1);
/* 301 */     this.ClothBackR1.func_78793_a(-4.0F, 11.5F, 2.9F);
/* 302 */     this.ClothBackR1.func_78787_b(128, 64);
/* 303 */     setRotation(this.ClothBackR1, 0.1047198F, 0.0F, 0.0F);
/*     */     
/* 305 */     this.ClothBackR2 = new ModelRenderer(this, 123, 9);
/* 306 */     this.ClothBackR2.func_78789_a(0.0F, 7.8F, -0.9F, 1, 2, 1);
/* 307 */     this.ClothBackR2.func_78793_a(-4.0F, 11.5F, 2.9F);
/* 308 */     this.ClothBackR2.func_78787_b(128, 64);
/* 309 */     setRotation(this.ClothBackR2, 0.2268928F, 0.0F, 0.0F);
/*     */     
/* 311 */     this.ClothBackR3 = new ModelRenderer(this, 120, 12);
/* 312 */     this.ClothBackR3.field_78809_i = true;
/* 313 */     this.ClothBackR3.func_78789_a(1.0F, 7.8F, -0.9F, 3, 3, 1);
/* 314 */     this.ClothBackR3.func_78793_a(-4.0F, 11.5F, 2.9F);
/* 315 */     this.ClothBackR3.func_78787_b(128, 64);
/* 316 */     setRotation(this.ClothBackR3, 0.2268928F, 0.0F, 0.0F);
/*     */ 
/*     */     
/* 319 */     this.ClothBackL1 = new ModelRenderer(this, 118, 16);
/* 320 */     this.ClothBackL1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 8, 1);
/* 321 */     this.ClothBackL1.func_78793_a(0.0F, 11.5F, 2.9F);
/* 322 */     this.ClothBackL1.func_78787_b(128, 64);
/* 323 */     setRotation(this.ClothBackL1, 0.1047198F, 0.0F, 0.0F);
/*     */     
/* 325 */     this.ClothBackL2 = new ModelRenderer(this, 123, 9);
/* 326 */     this.ClothBackL2.field_78809_i = true;
/* 327 */     this.ClothBackL2.func_78789_a(3.0F, 7.8F, -0.9F, 1, 2, 1);
/* 328 */     this.ClothBackL2.func_78793_a(0.0F, 11.5F, 2.9F);
/* 329 */     this.ClothBackL2.func_78787_b(128, 64);
/* 330 */     setRotation(this.ClothBackL2, 0.2268928F, 0.0F, 0.0F);
/*     */     
/* 332 */     this.ClothBackL3 = new ModelRenderer(this, 120, 12);
/* 333 */     this.ClothBackL3.func_78789_a(0.0F, 7.8F, -0.9F, 3, 3, 1);
/* 334 */     this.ClothBackL3.func_78793_a(0.0F, 11.5F, 2.9F);
/* 335 */     this.ClothBackL3.func_78787_b(128, 64);
/* 336 */     setRotation(this.ClothBackL3, 0.2268928F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */     
/* 340 */     this.SideclothL2 = new ModelRenderer(this, 116, 34);
/* 341 */     this.SideclothL2.func_78789_a(0.5F, 5.5F, -2.5F, 1, 3, 5);
/*     */     
/* 343 */     this.SideclothL2.func_78787_b(128, 64);
/* 344 */     setRotation(this.SideclothL2, 0.0F, 0.0F, -0.296706F);
/*     */     
/* 346 */     this.SideclothR1 = new ModelRenderer(this, 116, 42);
/* 347 */     this.SideclothR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 5, 5);
/*     */     
/* 349 */     this.SideclothR1.func_78787_b(128, 64);
/* 350 */     setRotation(this.SideclothR1, 0.0F, 0.0F, 0.122173F);
/*     */     
/* 352 */     this.SideclothR2 = new ModelRenderer(this, 116, 34);
/* 353 */     this.SideclothR2.func_78789_a(-1.5F, 5.5F, -2.5F, 1, 3, 5);
/*     */     
/* 355 */     this.SideclothR2.func_78787_b(128, 64);
/* 356 */     setRotation(this.SideclothR2, 0.0F, 0.0F, 0.296706F);
/*     */     
/* 358 */     this.SideclothR3 = new ModelRenderer(this, 116, 1);
/* 359 */     this.SideclothR3.func_78789_a(0.4F, 8.4F, -2.5F, 1, 3, 5);
/*     */     
/* 361 */     this.SideclothR3.func_78787_b(128, 64);
/* 362 */     setRotation(this.SideclothR3, 0.0F, 0.0F, 0.5235988F);
/*     */ 
/*     */ 
/*     */     
/* 366 */     this.SidepanelR1 = new ModelRenderer(this, 116, 25);
/* 367 */     this.SidepanelR1.func_78789_a(-2.5F, 0.5F, -2.5F, 1, 4, 5);
/*     */     
/* 369 */     this.SidepanelR1.func_78787_b(128, 64);
/* 370 */     this.SidepanelR1.field_78809_i = true;
/* 371 */     setRotation(this.SidepanelR1, 0.0F, 0.0F, 0.4363323F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 377 */     this.LegpanelR6 = new ModelRenderer(this, 82, 38);
/* 378 */     this.LegpanelR6.func_78789_a(-3.0F, 4.5F, -1.5F, 2, 3, 1);
/*     */     
/* 380 */     this.LegpanelR6.func_78787_b(128, 64);
/* 381 */     setRotation(this.LegpanelR6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 383 */     this.LegpanelR5 = new ModelRenderer(this, 76, 42);
/* 384 */     this.LegpanelR5.func_78789_a(-3.0F, 2.5F, -2.5F, 2, 3, 1);
/*     */     
/* 386 */     this.LegpanelR5.func_78787_b(128, 64);
/* 387 */     setRotation(this.LegpanelR5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 389 */     this.LegpanelR4 = new ModelRenderer(this, 76, 38);
/* 390 */     this.LegpanelR4.func_78789_a(-3.0F, 0.5F, -3.5F, 2, 3, 1);
/*     */     
/* 392 */     this.LegpanelR4.func_78787_b(128, 64);
/* 393 */     setRotation(this.LegpanelR4, -0.4363323F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 403 */     this.SideclothL3 = new ModelRenderer(this, 116, 1);
/* 404 */     this.SideclothL3.func_78789_a(-1.4F, 8.4F, -2.5F, 1, 3, 5);
/*     */     
/* 406 */     this.SideclothL3.func_78787_b(128, 64);
/* 407 */     setRotation(this.SideclothL3, 0.0F, 0.0F, -0.5235988F);
/*     */     
/* 409 */     this.Focipouch = new ModelRenderer(this, 100, 20);
/* 410 */     this.Focipouch.func_78789_a(3.5F, 0.5F, -2.5F, 3, 6, 5);
/*     */     
/* 412 */     this.Focipouch.func_78787_b(128, 64);
/* 413 */     setRotation(this.Focipouch, 0.0F, 0.0F, -0.122173F);
/*     */     
/* 415 */     this.SideclothL1 = new ModelRenderer(this, 116, 42);
/* 416 */     this.SideclothL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 5, 5);
/*     */     
/* 418 */     this.SideclothL1.func_78787_b(128, 64);
/* 419 */     setRotation(this.SideclothL1, 0.0F, 0.0F, -0.122173F);
/*     */ 
/*     */ 
/*     */     
/* 423 */     this.LegpanelL4 = new ModelRenderer(this, 76, 38);
/* 424 */     this.LegpanelL4.field_78809_i = true;
/* 425 */     this.LegpanelL4.func_78789_a(1.0F, 0.5F, -3.5F, 2, 3, 1);
/*     */     
/* 427 */     this.LegpanelL4.func_78787_b(128, 64);
/* 428 */     setRotation(this.LegpanelL4, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 430 */     this.LegpanelL5 = new ModelRenderer(this, 76, 42);
/* 431 */     this.LegpanelL5.field_78809_i = true;
/* 432 */     this.LegpanelL5.func_78789_a(1.0F, 2.5F, -2.5F, 2, 3, 1);
/*     */     
/* 434 */     this.LegpanelL5.func_78787_b(128, 64);
/* 435 */     setRotation(this.LegpanelL5, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 437 */     this.LegpanelL6 = new ModelRenderer(this, 82, 38);
/* 438 */     this.LegpanelL6.field_78809_i = true;
/* 439 */     this.LegpanelL6.func_78789_a(1.0F, 4.5F, -1.5F, 2, 3, 1);
/*     */     
/* 441 */     this.LegpanelL6.func_78787_b(128, 64);
/* 442 */     setRotation(this.LegpanelL6, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 444 */     this.SidepanelL1 = new ModelRenderer(this, 116, 25);
/* 445 */     this.SidepanelL1.func_78789_a(1.5F, 0.5F, -2.5F, 1, 4, 5);
/*     */     
/* 447 */     this.SidepanelL1.func_78787_b(128, 64);
/* 448 */     setRotation(this.SidepanelL1, 0.0F, 0.0F, -0.4363323F);
/*     */     
/* 450 */     this.field_178720_f.field_78804_l.clear();
/* 451 */     this.field_78116_c.field_78804_l.clear();
/* 452 */     this.field_78116_c.func_78792_a(this.Hood1);
/* 453 */     this.field_78116_c.func_78792_a(this.Hood2);
/* 454 */     this.field_78116_c.func_78792_a(this.Hood3);
/* 455 */     this.field_78116_c.func_78792_a(this.Hood4);
/*     */     
/* 457 */     this.field_78115_e.field_78804_l.clear();
/* 458 */     this.field_178721_j.field_78804_l.clear();
/* 459 */     this.field_178722_k.field_78804_l.clear();
/* 460 */     this.field_78115_e.func_78792_a(this.Mbelt);
/* 461 */     this.field_78115_e.func_78792_a(this.MbeltB);
/* 462 */     this.field_78115_e.func_78792_a(this.MbeltL);
/* 463 */     this.field_78115_e.func_78792_a(this.MbeltR);
/* 464 */     if (f < 1.0F) {
/* 465 */       this.field_178722_k.func_78792_a(this.Focipouch);
/* 466 */       this.field_78115_e.func_78792_a(this.FrontclothR1);
/* 467 */       this.field_78115_e.func_78792_a(this.FrontclothR2);
/* 468 */       this.field_78115_e.func_78792_a(this.FrontclothL1);
/* 469 */       this.field_78115_e.func_78792_a(this.FrontclothL2);
/*     */       
/* 471 */       this.field_78115_e.func_78792_a(this.ClothBackR1);
/* 472 */       this.field_78115_e.func_78792_a(this.ClothBackR2);
/* 473 */       this.field_78115_e.func_78792_a(this.ClothBackR3);
/*     */       
/* 475 */       this.field_78115_e.func_78792_a(this.ClothBackL1);
/* 476 */       this.field_78115_e.func_78792_a(this.ClothBackL2);
/* 477 */       this.field_78115_e.func_78792_a(this.ClothBackL3);
/*     */     } else {
/* 479 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 480 */       this.field_78115_e.func_78792_a(this.Chestthing);
/* 481 */       this.field_78115_e.func_78792_a(this.Scroll);
/* 482 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 483 */       this.field_78115_e.func_78792_a(this.Book);
/* 484 */       this.field_78115_e.func_78792_a(this.ClothchestL);
/* 485 */       this.field_78115_e.func_78792_a(this.ClothchestR);
/*     */     } 
/*     */     
/* 488 */     this.field_178723_h.field_78804_l.clear();
/* 489 */     this.field_178723_h.func_78792_a(this.ShoulderR);
/* 490 */     this.field_178723_h.func_78792_a(this.RArm1);
/* 491 */     this.field_178723_h.func_78792_a(this.RArm2);
/* 492 */     this.field_178723_h.func_78792_a(this.RArm3);
/* 493 */     this.field_178723_h.func_78792_a(this.ShoulderplateTopR);
/* 494 */     this.field_178723_h.func_78792_a(this.ShoulderplateR1);
/* 495 */     this.field_178723_h.func_78792_a(this.ShoulderplateR2);
/* 496 */     this.field_178723_h.func_78792_a(this.ShoulderplateR3);
/*     */     
/* 498 */     this.field_178724_i.field_78804_l.clear();
/* 499 */     this.field_178724_i.func_78792_a(this.ShoulderL);
/* 500 */     this.field_178724_i.func_78792_a(this.LArm1);
/* 501 */     this.field_178724_i.func_78792_a(this.LArm2);
/* 502 */     this.field_178724_i.func_78792_a(this.LArm3);
/* 503 */     this.field_178724_i.func_78792_a(this.ShoulderplateTopL);
/* 504 */     this.field_178724_i.func_78792_a(this.ShoulderplateL1);
/* 505 */     this.field_178724_i.func_78792_a(this.ShoulderplateL2);
/* 506 */     this.field_178724_i.func_78792_a(this.ShoulderplateL3);
/*     */ 
/*     */ 
/*     */     
/* 510 */     this.field_178721_j.func_78792_a(this.LegpanelR4);
/* 511 */     this.field_178721_j.func_78792_a(this.LegpanelR5);
/* 512 */     this.field_178721_j.func_78792_a(this.LegpanelR6);
/* 513 */     this.field_178721_j.func_78792_a(this.SidepanelR1);
/*     */     
/* 515 */     this.field_178721_j.func_78792_a(this.SideclothR1);
/* 516 */     this.field_178721_j.func_78792_a(this.SideclothR2);
/* 517 */     this.field_178721_j.func_78792_a(this.SideclothR3);
/*     */ 
/*     */ 
/*     */     
/* 521 */     this.field_178722_k.func_78792_a(this.LegpanelL4);
/* 522 */     this.field_178722_k.func_78792_a(this.LegpanelL5);
/* 523 */     this.field_178722_k.func_78792_a(this.LegpanelL6);
/* 524 */     this.field_178722_k.func_78792_a(this.SidepanelL1);
/*     */     
/* 526 */     this.field_178722_k.func_78792_a(this.SideclothL1);
/* 527 */     this.field_178722_k.func_78792_a(this.SideclothL2);
/* 528 */     this.field_178722_k.func_78792_a(this.SideclothL3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 534 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 536 */     float a = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 537 */     float b = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
/* 538 */     float c = Math.min(a, b);
/*     */     
/* 540 */     this.FrontclothL1.field_78795_f = c - 0.1047198F;
/* 541 */     this.FrontclothL2.field_78795_f = c - 0.3316126F;
/*     */     
/* 543 */     this.ClothBackL1.field_78795_f = -c + 0.1047198F;
/* 544 */     this.ClothBackL3.field_78795_f = -c + 0.2268928F;
/*     */ 
/*     */     
/* 547 */     if (this.field_78091_s) {
/* 548 */       float f6 = 2.0F;
/* 549 */       GL11.glPushMatrix();
/* 550 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 551 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/*     */       
/* 553 */       this.field_78116_c.func_78785_a(f5);
/*     */       
/* 555 */       GL11.glPopMatrix();
/* 556 */       GL11.glPushMatrix();
/* 557 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 558 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 559 */       this.field_78115_e.func_78785_a(f5);
/* 560 */       this.field_178723_h.func_78785_a(f5);
/* 561 */       this.field_178724_i.func_78785_a(f5);
/* 562 */       this.field_178721_j.func_78785_a(f5);
/* 563 */       this.field_178722_k.func_78785_a(f5);
/*     */       
/* 565 */       this.field_178720_f.func_78785_a(f5);
/*     */       
/* 567 */       GL11.glPopMatrix();
/*     */     } else {
/* 569 */       GL11.glPushMatrix();
/* 570 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/*     */       
/* 572 */       this.field_78116_c.func_78785_a(f5);
/* 573 */       GL11.glPopMatrix();
/* 574 */       this.field_78115_e.func_78785_a(f5);
/* 575 */       this.field_178723_h.func_78785_a(f5);
/* 576 */       this.field_178724_i.func_78785_a(f5);
/* 577 */       this.field_178721_j.func_78785_a(f5);
/* 578 */       this.field_178722_k.func_78785_a(f5);
/* 579 */       this.field_178720_f.func_78785_a(f5);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 584 */     model.field_78795_f = x;
/* 585 */     model.field_78796_g = y;
/* 586 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\gear\ModelRobe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */