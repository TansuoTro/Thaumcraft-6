/*     */ package thaumcraft.client.lib;@SideOnly(Side.CLIENT)
/*     */ public class RenderCubes { public IBlockAccess blockAccess; public boolean flipTexture; public boolean field_152631_f; public boolean renderAllFaces; public boolean useInventoryTint; public boolean renderFromInside; public double renderMinX; public double renderMaxX; public double renderMinY; public double renderMaxY; public double renderMinZ; public double renderMaxZ; public boolean lockBlockBounds; public boolean partialRenderBounds; public final Minecraft minecraftRB; public int uvRotateEast; public int uvRotateWest; public int uvRotateSouth; public int uvRotateNorth; public int uvRotateTop; public int uvRotateBottom; public float aoLightValueScratchXYZNNN; public float aoLightValueScratchXYNN; public float aoLightValueScratchXYZNNP; public float aoLightValueScratchYZNN; public float aoLightValueScratchYZNP;
/*     */   public float aoLightValueScratchXYZPNN;
/*     */   public float aoLightValueScratchXYPN;
/*     */   public float aoLightValueScratchXYZPNP;
/*     */   public float aoLightValueScratchXYZNPN;
/*     */   public float aoLightValueScratchXYNP;
/*     */   public float aoLightValueScratchXYZNPP;
/*     */   public float aoLightValueScratchYZPN;
/*     */   public float aoLightValueScratchXYZPPN;
/*     */   public float aoLightValueScratchXYPP;
/*     */   public float aoLightValueScratchYZPP;
/*     */   public float aoLightValueScratchXYZPPP;
/*     */   public float aoLightValueScratchXZNN;
/*     */   public float aoLightValueScratchXZPN;
/*     */   
/*     */   public RenderCubes(IBlockAccess p_i1251_1_) {
/*  18 */     this.useInventoryTint = true;
/*  19 */     this.renderFromInside = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     this.blockAccess = p_i1251_1_;
/* 139 */     this.field_152631_f = false;
/* 140 */     this.flipTexture = false;
/* 141 */     this.minecraftRB = Minecraft.func_71410_x();
/*     */   } public float aoLightValueScratchXZNP; public float aoLightValueScratchXZPP; public int aoBrightnessXYZNNN; public int aoBrightnessXYNN; public int aoBrightnessXYZNNP; public int aoBrightnessYZNN; public int aoBrightnessYZNP; public int aoBrightnessXYZPNN; public int aoBrightnessXYPN; public int aoBrightnessXYZPNP; public int aoBrightnessXYZNPN; public int aoBrightnessXYNP; public int aoBrightnessXYZNPP; public int aoBrightnessYZPN; public int aoBrightnessXYZPPN; public int aoBrightnessXYPP; public int aoBrightnessYZPP; public int aoBrightnessXYZPPP; public int aoBrightnessXZNN; public int aoBrightnessXZPN; public int aoBrightnessXZNP; public int aoBrightnessXZPP; public int brightnessTopLeft; public int brightnessBottomLeft; public int brightnessBottomRight; public int brightnessTopRight; public float colorRedTopLeft; public float colorRedBottomLeft; public float colorRedBottomRight; public float colorRedTopRight; public float colorGreenTopLeft; public float colorGreenBottomLeft; public float colorGreenBottomRight; public float colorGreenTopRight; public float colorBlueTopLeft; public float colorBlueBottomLeft; public float colorBlueBottomRight; public float colorBlueTopRight; private static final String __OBFID = "CL_00000940"; private static RenderCubes instance;
/*     */   public RenderCubes() {
/*     */     this.useInventoryTint = true;
/*     */     this.renderFromInside = false;
/* 146 */     this.minecraftRB = Minecraft.func_71410_x();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderBounds(double p_147782_1_, double p_147782_3_, double p_147782_5_, double p_147782_7_, double p_147782_9_, double p_147782_11_) {
/* 152 */     if (!this.lockBlockBounds) {
/*     */       
/* 154 */       this.renderMinX = p_147782_1_;
/* 155 */       this.renderMaxX = p_147782_7_;
/* 156 */       this.renderMinY = p_147782_3_;
/* 157 */       this.renderMaxY = p_147782_9_;
/* 158 */       this.renderMinZ = p_147782_5_;
/* 159 */       this.renderMaxZ = p_147782_11_;
/* 160 */       this.partialRenderBounds = (this.minecraftRB.field_71474_y.field_74348_k >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void overrideBlockBounds(double p_147770_1_, double p_147770_3_, double p_147770_5_, double p_147770_7_, double p_147770_9_, double p_147770_11_) {
/* 170 */     this.renderMinX = p_147770_1_;
/* 171 */     this.renderMaxX = p_147770_7_;
/* 172 */     this.renderMinY = p_147770_3_;
/* 173 */     this.renderMaxY = p_147770_9_;
/* 174 */     this.renderMinZ = p_147770_5_;
/* 175 */     this.renderMaxZ = p_147770_11_;
/* 176 */     this.lockBlockBounds = true;
/* 177 */     this.partialRenderBounds = (this.minecraftRB.field_71474_y.field_74348_k >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFaceYNeg(Block p_147768_1_, double p_147768_2_, double p_147768_4_, double p_147768_6_, TextureAtlasSprite p_147768_8_, float red, float green, float blue, int bright) {
/* 186 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 188 */     double d3 = p_147768_8_.func_94214_a(this.renderMinX * 16.0D);
/* 189 */     double d4 = p_147768_8_.func_94214_a(this.renderMaxX * 16.0D);
/* 190 */     double d5 = p_147768_8_.func_94207_b(this.renderMinZ * 16.0D);
/* 191 */     double d6 = p_147768_8_.func_94207_b(this.renderMaxZ * 16.0D);
/*     */     
/* 193 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*     */       
/* 195 */       d3 = p_147768_8_.func_94209_e();
/* 196 */       d4 = p_147768_8_.func_94212_f();
/*     */     } 
/*     */     
/* 199 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*     */       
/* 201 */       d5 = p_147768_8_.func_94206_g();
/* 202 */       d6 = p_147768_8_.func_94210_h();
/*     */     } 
/*     */     
/* 205 */     double d7 = d4;
/* 206 */     double d8 = d3;
/* 207 */     double d9 = d5;
/* 208 */     double d10 = d6;
/*     */     
/* 210 */     if (this.uvRotateBottom == 2) {
/*     */       
/* 212 */       d3 = p_147768_8_.func_94214_a(this.renderMinZ * 16.0D);
/* 213 */       d5 = p_147768_8_.func_94207_b(16.0D - this.renderMaxX * 16.0D);
/* 214 */       d4 = p_147768_8_.func_94214_a(this.renderMaxZ * 16.0D);
/* 215 */       d6 = p_147768_8_.func_94207_b(16.0D - this.renderMinX * 16.0D);
/* 216 */       d9 = d5;
/* 217 */       d10 = d6;
/* 218 */       d7 = d3;
/* 219 */       d8 = d4;
/* 220 */       d5 = d6;
/* 221 */       d6 = d9;
/*     */     }
/* 223 */     else if (this.uvRotateBottom == 1) {
/*     */       
/* 225 */       d3 = p_147768_8_.func_94214_a(16.0D - this.renderMaxZ * 16.0D);
/* 226 */       d5 = p_147768_8_.func_94207_b(this.renderMinX * 16.0D);
/* 227 */       d4 = p_147768_8_.func_94214_a(16.0D - this.renderMinZ * 16.0D);
/* 228 */       d6 = p_147768_8_.func_94207_b(this.renderMaxX * 16.0D);
/* 229 */       d7 = d4;
/* 230 */       d8 = d3;
/* 231 */       d3 = d4;
/* 232 */       d4 = d8;
/* 233 */       d9 = d6;
/* 234 */       d10 = d5;
/*     */     }
/* 236 */     else if (this.uvRotateBottom == 3) {
/*     */       
/* 238 */       d3 = p_147768_8_.func_94214_a(16.0D - this.renderMinX * 16.0D);
/* 239 */       d4 = p_147768_8_.func_94214_a(16.0D - this.renderMaxX * 16.0D);
/* 240 */       d5 = p_147768_8_.func_94207_b(16.0D - this.renderMinZ * 16.0D);
/* 241 */       d6 = p_147768_8_.func_94207_b(16.0D - this.renderMaxZ * 16.0D);
/* 242 */       d7 = d4;
/* 243 */       d8 = d3;
/* 244 */       d9 = d5;
/* 245 */       d10 = d6;
/*     */     } 
/*     */     
/* 248 */     double d11 = p_147768_2_ + this.renderMinX;
/* 249 */     double d12 = p_147768_2_ + this.renderMaxX;
/* 250 */     double d13 = p_147768_4_ + this.renderMinY;
/* 251 */     double d14 = p_147768_6_ + this.renderMinZ;
/* 252 */     double d15 = p_147768_6_ + this.renderMaxZ;
/*     */     
/* 254 */     if (this.renderFromInside) {
/*     */       
/* 256 */       d11 = p_147768_2_ + this.renderMaxX;
/* 257 */       d12 = p_147768_2_ + this.renderMinX;
/*     */     } 
/*     */     
/* 260 */     int i = bright;
/* 261 */     int j = i >> 16 & 0xFFFF;
/* 262 */     int k = i & 0xFFFF;
/*     */     
/* 264 */     tessellator.func_178180_c().func_181662_b(d11, d13, d15).func_187315_a(d8, d10).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 265 */     tessellator.func_178180_c().func_181662_b(d11, d13, d14).func_187315_a(d3, d5).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 266 */     tessellator.func_178180_c().func_181662_b(d12, d13, d14).func_187315_a(d7, d9).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 267 */     tessellator.func_178180_c().func_181662_b(d12, d13, d15).func_187315_a(d4, d6).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFaceYPos(Block p_147806_1_, double p_147806_2_, double p_147806_4_, double p_147806_6_, TextureAtlasSprite p_147806_8_, float red, float green, float blue, int bright) {
/* 275 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */ 
/*     */     
/* 278 */     double d3 = p_147806_8_.func_94214_a(this.renderMinX * 16.0D);
/* 279 */     double d4 = p_147806_8_.func_94214_a(this.renderMaxX * 16.0D);
/* 280 */     double d5 = p_147806_8_.func_94207_b(this.renderMinZ * 16.0D);
/* 281 */     double d6 = p_147806_8_.func_94207_b(this.renderMaxZ * 16.0D);
/*     */     
/* 283 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*     */       
/* 285 */       d3 = p_147806_8_.func_94209_e();
/* 286 */       d4 = p_147806_8_.func_94212_f();
/*     */     } 
/*     */     
/* 289 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*     */       
/* 291 */       d5 = p_147806_8_.func_94206_g();
/* 292 */       d6 = p_147806_8_.func_94210_h();
/*     */     } 
/*     */     
/* 295 */     double d7 = d4;
/* 296 */     double d8 = d3;
/* 297 */     double d9 = d5;
/* 298 */     double d10 = d6;
/*     */     
/* 300 */     if (this.uvRotateTop == 1) {
/*     */       
/* 302 */       d3 = p_147806_8_.func_94214_a(this.renderMinZ * 16.0D);
/* 303 */       d5 = p_147806_8_.func_94207_b(16.0D - this.renderMaxX * 16.0D);
/* 304 */       d4 = p_147806_8_.func_94214_a(this.renderMaxZ * 16.0D);
/* 305 */       d6 = p_147806_8_.func_94207_b(16.0D - this.renderMinX * 16.0D);
/* 306 */       d9 = d5;
/* 307 */       d10 = d6;
/* 308 */       d7 = d3;
/* 309 */       d8 = d4;
/* 310 */       d5 = d6;
/* 311 */       d6 = d9;
/*     */     }
/* 313 */     else if (this.uvRotateTop == 2) {
/*     */       
/* 315 */       d3 = p_147806_8_.func_94214_a(16.0D - this.renderMaxZ * 16.0D);
/* 316 */       d5 = p_147806_8_.func_94207_b(this.renderMinX * 16.0D);
/* 317 */       d4 = p_147806_8_.func_94214_a(16.0D - this.renderMinZ * 16.0D);
/* 318 */       d6 = p_147806_8_.func_94207_b(this.renderMaxX * 16.0D);
/* 319 */       d7 = d4;
/* 320 */       d8 = d3;
/* 321 */       d3 = d4;
/* 322 */       d4 = d8;
/* 323 */       d9 = d6;
/* 324 */       d10 = d5;
/*     */     }
/* 326 */     else if (this.uvRotateTop == 3) {
/*     */       
/* 328 */       d3 = p_147806_8_.func_94214_a(16.0D - this.renderMinX * 16.0D);
/* 329 */       d4 = p_147806_8_.func_94214_a(16.0D - this.renderMaxX * 16.0D);
/* 330 */       d5 = p_147806_8_.func_94207_b(16.0D - this.renderMinZ * 16.0D);
/* 331 */       d6 = p_147806_8_.func_94207_b(16.0D - this.renderMaxZ * 16.0D);
/* 332 */       d7 = d4;
/* 333 */       d8 = d3;
/* 334 */       d9 = d5;
/* 335 */       d10 = d6;
/*     */     } 
/*     */     
/* 338 */     double d11 = p_147806_2_ + this.renderMinX;
/* 339 */     double d12 = p_147806_2_ + this.renderMaxX;
/* 340 */     double d13 = p_147806_4_ + this.renderMaxY;
/* 341 */     double d14 = p_147806_6_ + this.renderMinZ;
/* 342 */     double d15 = p_147806_6_ + this.renderMaxZ;
/*     */     
/* 344 */     if (this.renderFromInside) {
/*     */       
/* 346 */       d11 = p_147806_2_ + this.renderMaxX;
/* 347 */       d12 = p_147806_2_ + this.renderMinX;
/*     */     } 
/*     */     
/* 350 */     int i = bright;
/* 351 */     int j = i >> 16 & 0xFFFF;
/* 352 */     int k = i & 0xFFFF;
/* 353 */     tessellator.func_178180_c().func_181662_b(d12, d13, d15).func_187315_a(d4, d6).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 354 */     tessellator.func_178180_c().func_181662_b(d12, d13, d14).func_187315_a(d7, d9).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 355 */     tessellator.func_178180_c().func_181662_b(d11, d13, d14).func_187315_a(d3, d5).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 356 */     tessellator.func_178180_c().func_181662_b(d11, d13, d15).func_187315_a(d8, d10).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFaceZNeg(Block p_147761_1_, double p_147761_2_, double p_147761_4_, double p_147761_6_, TextureAtlasSprite p_147761_8_, float red, float green, float blue, int bright) {
/* 364 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */ 
/*     */     
/* 367 */     double d3 = p_147761_8_.func_94214_a(this.renderMinX * 16.0D);
/* 368 */     double d4 = p_147761_8_.func_94214_a(this.renderMaxX * 16.0D);
/*     */     
/* 370 */     if (this.field_152631_f) {
/*     */       
/* 372 */       d4 = p_147761_8_.func_94214_a((1.0D - this.renderMinX) * 16.0D);
/* 373 */       d3 = p_147761_8_.func_94214_a((1.0D - this.renderMaxX) * 16.0D);
/*     */     } 
/*     */     
/* 376 */     double d5 = p_147761_8_.func_94207_b(16.0D - this.renderMaxY * 16.0D);
/* 377 */     double d6 = p_147761_8_.func_94207_b(16.0D - this.renderMinY * 16.0D);
/*     */ 
/*     */     
/* 380 */     if (this.flipTexture) {
/*     */       
/* 382 */       double d7 = d3;
/* 383 */       d3 = d4;
/* 384 */       d4 = d7;
/*     */     } 
/*     */     
/* 387 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*     */       
/* 389 */       d3 = p_147761_8_.func_94209_e();
/* 390 */       d4 = p_147761_8_.func_94212_f();
/*     */     } 
/*     */     
/* 393 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*     */       
/* 395 */       d5 = p_147761_8_.func_94206_g();
/* 396 */       d6 = p_147761_8_.func_94210_h();
/*     */     } 
/*     */     
/* 399 */     double d7 = d4;
/* 400 */     double d8 = d3;
/* 401 */     double d9 = d5;
/* 402 */     double d10 = d6;
/*     */     
/* 404 */     if (this.uvRotateEast == 2) {
/*     */       
/* 406 */       d3 = p_147761_8_.func_94214_a(this.renderMinY * 16.0D);
/* 407 */       d4 = p_147761_8_.func_94214_a(this.renderMaxY * 16.0D);
/* 408 */       d5 = p_147761_8_.func_94207_b(16.0D - this.renderMinX * 16.0D);
/* 409 */       d6 = p_147761_8_.func_94207_b(16.0D - this.renderMaxX * 16.0D);
/* 410 */       d9 = d5;
/* 411 */       d10 = d6;
/* 412 */       d7 = d3;
/* 413 */       d8 = d4;
/* 414 */       d5 = d6;
/* 415 */       d6 = d9;
/*     */     }
/* 417 */     else if (this.uvRotateEast == 1) {
/*     */       
/* 419 */       d3 = p_147761_8_.func_94214_a(16.0D - this.renderMaxY * 16.0D);
/* 420 */       d4 = p_147761_8_.func_94214_a(16.0D - this.renderMinY * 16.0D);
/* 421 */       d5 = p_147761_8_.func_94207_b(this.renderMaxX * 16.0D);
/* 422 */       d6 = p_147761_8_.func_94207_b(this.renderMinX * 16.0D);
/* 423 */       d7 = d4;
/* 424 */       d8 = d3;
/* 425 */       d3 = d4;
/* 426 */       d4 = d8;
/* 427 */       d9 = d6;
/* 428 */       d10 = d5;
/*     */     }
/* 430 */     else if (this.uvRotateEast == 3) {
/*     */       
/* 432 */       d3 = p_147761_8_.func_94214_a(16.0D - this.renderMinX * 16.0D);
/* 433 */       d4 = p_147761_8_.func_94214_a(16.0D - this.renderMaxX * 16.0D);
/* 434 */       d5 = p_147761_8_.func_94207_b(this.renderMaxY * 16.0D);
/* 435 */       d6 = p_147761_8_.func_94207_b(this.renderMinY * 16.0D);
/* 436 */       d7 = d4;
/* 437 */       d8 = d3;
/* 438 */       d9 = d5;
/* 439 */       d10 = d6;
/*     */     } 
/*     */     
/* 442 */     double d11 = p_147761_2_ + this.renderMinX;
/* 443 */     double d12 = p_147761_2_ + this.renderMaxX;
/* 444 */     double d13 = p_147761_4_ + this.renderMinY;
/* 445 */     double d14 = p_147761_4_ + this.renderMaxY;
/* 446 */     double d15 = p_147761_6_ + this.renderMinZ;
/*     */     
/* 448 */     if (this.renderFromInside) {
/*     */       
/* 450 */       d11 = p_147761_2_ + this.renderMaxX;
/* 451 */       d12 = p_147761_2_ + this.renderMinX;
/*     */     } 
/*     */     
/* 454 */     int i = bright;
/* 455 */     int j = i >> 16 & 0xFFFF;
/* 456 */     int k = i & 0xFFFF;
/* 457 */     tessellator.func_178180_c().func_181662_b(d11, d14, d15).func_187315_a(d7, d9).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 458 */     tessellator.func_178180_c().func_181662_b(d12, d14, d15).func_187315_a(d3, d5).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 459 */     tessellator.func_178180_c().func_181662_b(d12, d13, d15).func_187315_a(d8, d10).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 460 */     tessellator.func_178180_c().func_181662_b(d11, d13, d15).func_187315_a(d4, d6).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFaceZPos(Block p_147734_1_, double p_147734_2_, double p_147734_4_, double p_147734_6_, TextureAtlasSprite p_147734_8_, float red, float green, float blue, int bright) {
/* 468 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 470 */     double d3 = p_147734_8_.func_94214_a(this.renderMinX * 16.0D);
/* 471 */     double d4 = p_147734_8_.func_94214_a(this.renderMaxX * 16.0D);
/* 472 */     double d5 = p_147734_8_.func_94207_b(16.0D - this.renderMaxY * 16.0D);
/* 473 */     double d6 = p_147734_8_.func_94207_b(16.0D - this.renderMinY * 16.0D);
/*     */ 
/*     */     
/* 476 */     if (this.flipTexture) {
/*     */       
/* 478 */       double d7 = d3;
/* 479 */       d3 = d4;
/* 480 */       d4 = d7;
/*     */     } 
/*     */     
/* 483 */     if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
/*     */       
/* 485 */       d3 = p_147734_8_.func_94209_e();
/* 486 */       d4 = p_147734_8_.func_94212_f();
/*     */     } 
/*     */     
/* 489 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*     */       
/* 491 */       d5 = p_147734_8_.func_94206_g();
/* 492 */       d6 = p_147734_8_.func_94210_h();
/*     */     } 
/*     */     
/* 495 */     double d7 = d4;
/* 496 */     double d8 = d3;
/* 497 */     double d9 = d5;
/* 498 */     double d10 = d6;
/*     */     
/* 500 */     if (this.uvRotateWest == 1) {
/*     */       
/* 502 */       d3 = p_147734_8_.func_94214_a(this.renderMinY * 16.0D);
/* 503 */       d6 = p_147734_8_.func_94207_b(16.0D - this.renderMinX * 16.0D);
/* 504 */       d4 = p_147734_8_.func_94214_a(this.renderMaxY * 16.0D);
/* 505 */       d5 = p_147734_8_.func_94207_b(16.0D - this.renderMaxX * 16.0D);
/* 506 */       d9 = d5;
/* 507 */       d10 = d6;
/* 508 */       d7 = d3;
/* 509 */       d8 = d4;
/* 510 */       d5 = d6;
/* 511 */       d6 = d9;
/*     */     }
/* 513 */     else if (this.uvRotateWest == 2) {
/*     */       
/* 515 */       d3 = p_147734_8_.func_94214_a(16.0D - this.renderMaxY * 16.0D);
/* 516 */       d5 = p_147734_8_.func_94207_b(this.renderMinX * 16.0D);
/* 517 */       d4 = p_147734_8_.func_94214_a(16.0D - this.renderMinY * 16.0D);
/* 518 */       d6 = p_147734_8_.func_94207_b(this.renderMaxX * 16.0D);
/* 519 */       d7 = d4;
/* 520 */       d8 = d3;
/* 521 */       d3 = d4;
/* 522 */       d4 = d8;
/* 523 */       d9 = d6;
/* 524 */       d10 = d5;
/*     */     }
/* 526 */     else if (this.uvRotateWest == 3) {
/*     */       
/* 528 */       d3 = p_147734_8_.func_94214_a(16.0D - this.renderMinX * 16.0D);
/* 529 */       d4 = p_147734_8_.func_94214_a(16.0D - this.renderMaxX * 16.0D);
/* 530 */       d5 = p_147734_8_.func_94207_b(this.renderMaxY * 16.0D);
/* 531 */       d6 = p_147734_8_.func_94207_b(this.renderMinY * 16.0D);
/* 532 */       d7 = d4;
/* 533 */       d8 = d3;
/* 534 */       d9 = d5;
/* 535 */       d10 = d6;
/*     */     } 
/*     */     
/* 538 */     double d11 = p_147734_2_ + this.renderMinX;
/* 539 */     double d12 = p_147734_2_ + this.renderMaxX;
/* 540 */     double d13 = p_147734_4_ + this.renderMinY;
/* 541 */     double d14 = p_147734_4_ + this.renderMaxY;
/* 542 */     double d15 = p_147734_6_ + this.renderMaxZ;
/*     */     
/* 544 */     if (this.renderFromInside) {
/*     */       
/* 546 */       d11 = p_147734_2_ + this.renderMaxX;
/* 547 */       d12 = p_147734_2_ + this.renderMinX;
/*     */     } 
/* 549 */     int i = bright;
/* 550 */     int j = i >> 16 & 0xFFFF;
/* 551 */     int k = i & 0xFFFF;
/* 552 */     tessellator.func_178180_c().func_181662_b(d11, d14, d15).func_187315_a(d3, d5).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 553 */     tessellator.func_178180_c().func_181662_b(d11, d13, d15).func_187315_a(d8, d10).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 554 */     tessellator.func_178180_c().func_181662_b(d12, d13, d15).func_187315_a(d4, d6).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 555 */     tessellator.func_178180_c().func_181662_b(d12, d14, d15).func_187315_a(d7, d9).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFaceXNeg(Block p_147798_1_, double p_147798_2_, double p_147798_4_, double p_147798_6_, TextureAtlasSprite p_147798_8_, float red, float green, float blue, int bright) {
/* 563 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 565 */     double d3 = p_147798_8_.func_94214_a(this.renderMinZ * 16.0D);
/* 566 */     double d4 = p_147798_8_.func_94214_a(this.renderMaxZ * 16.0D);
/* 567 */     double d5 = p_147798_8_.func_94207_b(16.0D - this.renderMaxY * 16.0D);
/* 568 */     double d6 = p_147798_8_.func_94207_b(16.0D - this.renderMinY * 16.0D);
/*     */ 
/*     */     
/* 571 */     if (this.flipTexture) {
/*     */       
/* 573 */       double d7 = d3;
/* 574 */       d3 = d4;
/* 575 */       d4 = d7;
/*     */     } 
/*     */     
/* 578 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*     */       
/* 580 */       d3 = p_147798_8_.func_94209_e();
/* 581 */       d4 = p_147798_8_.func_94212_f();
/*     */     } 
/*     */     
/* 584 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*     */       
/* 586 */       d5 = p_147798_8_.func_94206_g();
/* 587 */       d6 = p_147798_8_.func_94210_h();
/*     */     } 
/*     */     
/* 590 */     double d7 = d4;
/* 591 */     double d8 = d3;
/* 592 */     double d9 = d5;
/* 593 */     double d10 = d6;
/*     */     
/* 595 */     if (this.uvRotateNorth == 1) {
/*     */       
/* 597 */       d3 = p_147798_8_.func_94214_a(this.renderMinY * 16.0D);
/* 598 */       d5 = p_147798_8_.func_94207_b(16.0D - this.renderMaxZ * 16.0D);
/* 599 */       d4 = p_147798_8_.func_94214_a(this.renderMaxY * 16.0D);
/* 600 */       d6 = p_147798_8_.func_94207_b(16.0D - this.renderMinZ * 16.0D);
/* 601 */       d9 = d5;
/* 602 */       d10 = d6;
/* 603 */       d7 = d3;
/* 604 */       d8 = d4;
/* 605 */       d5 = d6;
/* 606 */       d6 = d9;
/*     */     }
/* 608 */     else if (this.uvRotateNorth == 2) {
/*     */       
/* 610 */       d3 = p_147798_8_.func_94214_a(16.0D - this.renderMaxY * 16.0D);
/* 611 */       d5 = p_147798_8_.func_94207_b(this.renderMinZ * 16.0D);
/* 612 */       d4 = p_147798_8_.func_94214_a(16.0D - this.renderMinY * 16.0D);
/* 613 */       d6 = p_147798_8_.func_94207_b(this.renderMaxZ * 16.0D);
/* 614 */       d7 = d4;
/* 615 */       d8 = d3;
/* 616 */       d3 = d4;
/* 617 */       d4 = d8;
/* 618 */       d9 = d6;
/* 619 */       d10 = d5;
/*     */     }
/* 621 */     else if (this.uvRotateNorth == 3) {
/*     */       
/* 623 */       d3 = p_147798_8_.func_94214_a(16.0D - this.renderMinZ * 16.0D);
/* 624 */       d4 = p_147798_8_.func_94214_a(16.0D - this.renderMaxZ * 16.0D);
/* 625 */       d5 = p_147798_8_.func_94207_b(this.renderMaxY * 16.0D);
/* 626 */       d6 = p_147798_8_.func_94207_b(this.renderMinY * 16.0D);
/* 627 */       d7 = d4;
/* 628 */       d8 = d3;
/* 629 */       d9 = d5;
/* 630 */       d10 = d6;
/*     */     } 
/*     */     
/* 633 */     double d11 = p_147798_2_ + this.renderMinX;
/* 634 */     double d12 = p_147798_4_ + this.renderMinY;
/* 635 */     double d13 = p_147798_4_ + this.renderMaxY;
/* 636 */     double d14 = p_147798_6_ + this.renderMinZ;
/* 637 */     double d15 = p_147798_6_ + this.renderMaxZ;
/*     */     
/* 639 */     if (this.renderFromInside) {
/*     */       
/* 641 */       d14 = p_147798_6_ + this.renderMaxZ;
/* 642 */       d15 = p_147798_6_ + this.renderMinZ;
/*     */     } 
/* 644 */     int i = bright;
/* 645 */     int j = i >> 16 & 0xFFFF;
/* 646 */     int k = i & 0xFFFF;
/* 647 */     tessellator.func_178180_c().func_181662_b(d11, d13, d15).func_187315_a(d7, d9).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 648 */     tessellator.func_178180_c().func_181662_b(d11, d13, d14).func_187315_a(d3, d5).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 649 */     tessellator.func_178180_c().func_181662_b(d11, d12, d14).func_187315_a(d8, d10).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 650 */     tessellator.func_178180_c().func_181662_b(d11, d12, d15).func_187315_a(d4, d6).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFaceXPos(Block p_147764_1_, double p_147764_2_, double p_147764_4_, double p_147764_6_, TextureAtlasSprite p_147764_8_, float red, float green, float blue, int bright) {
/* 659 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */ 
/*     */     
/* 662 */     double d3 = p_147764_8_.func_94214_a(this.renderMinZ * 16.0D);
/* 663 */     double d4 = p_147764_8_.func_94214_a(this.renderMaxZ * 16.0D);
/*     */     
/* 665 */     if (this.field_152631_f) {
/*     */       
/* 667 */       d4 = p_147764_8_.func_94214_a((1.0D - this.renderMinZ) * 16.0D);
/* 668 */       d3 = p_147764_8_.func_94214_a((1.0D - this.renderMaxZ) * 16.0D);
/*     */     } 
/*     */     
/* 671 */     double d5 = p_147764_8_.func_94207_b(16.0D - this.renderMaxY * 16.0D);
/* 672 */     double d6 = p_147764_8_.func_94207_b(16.0D - this.renderMinY * 16.0D);
/*     */ 
/*     */     
/* 675 */     if (this.flipTexture) {
/*     */       
/* 677 */       double d7 = d3;
/* 678 */       d3 = d4;
/* 679 */       d4 = d7;
/*     */     } 
/*     */     
/* 682 */     if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
/*     */       
/* 684 */       d3 = p_147764_8_.func_94209_e();
/* 685 */       d4 = p_147764_8_.func_94212_f();
/*     */     } 
/*     */     
/* 688 */     if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
/*     */       
/* 690 */       d5 = p_147764_8_.func_94206_g();
/* 691 */       d6 = p_147764_8_.func_94210_h();
/*     */     } 
/*     */     
/* 694 */     double d7 = d4;
/* 695 */     double d8 = d3;
/* 696 */     double d9 = d5;
/* 697 */     double d10 = d6;
/*     */     
/* 699 */     if (this.uvRotateSouth == 2) {
/*     */       
/* 701 */       d3 = p_147764_8_.func_94214_a(this.renderMinY * 16.0D);
/* 702 */       d5 = p_147764_8_.func_94207_b(16.0D - this.renderMinZ * 16.0D);
/* 703 */       d4 = p_147764_8_.func_94214_a(this.renderMaxY * 16.0D);
/* 704 */       d6 = p_147764_8_.func_94207_b(16.0D - this.renderMaxZ * 16.0D);
/* 705 */       d9 = d5;
/* 706 */       d10 = d6;
/* 707 */       d7 = d3;
/* 708 */       d8 = d4;
/* 709 */       d5 = d6;
/* 710 */       d6 = d9;
/*     */     }
/* 712 */     else if (this.uvRotateSouth == 1) {
/*     */       
/* 714 */       d3 = p_147764_8_.func_94214_a(16.0D - this.renderMaxY * 16.0D);
/* 715 */       d5 = p_147764_8_.func_94207_b(this.renderMaxZ * 16.0D);
/* 716 */       d4 = p_147764_8_.func_94214_a(16.0D - this.renderMinY * 16.0D);
/* 717 */       d6 = p_147764_8_.func_94207_b(this.renderMinZ * 16.0D);
/* 718 */       d7 = d4;
/* 719 */       d8 = d3;
/* 720 */       d3 = d4;
/* 721 */       d4 = d8;
/* 722 */       d9 = d6;
/* 723 */       d10 = d5;
/*     */     }
/* 725 */     else if (this.uvRotateSouth == 3) {
/*     */       
/* 727 */       d3 = p_147764_8_.func_94214_a(16.0D - this.renderMinZ * 16.0D);
/* 728 */       d4 = p_147764_8_.func_94214_a(16.0D - this.renderMaxZ * 16.0D);
/* 729 */       d5 = p_147764_8_.func_94207_b(this.renderMaxY * 16.0D);
/* 730 */       d6 = p_147764_8_.func_94207_b(this.renderMinY * 16.0D);
/* 731 */       d7 = d4;
/* 732 */       d8 = d3;
/* 733 */       d9 = d5;
/* 734 */       d10 = d6;
/*     */     } 
/*     */     
/* 737 */     double d11 = p_147764_2_ + this.renderMaxX;
/* 738 */     double d12 = p_147764_4_ + this.renderMinY;
/* 739 */     double d13 = p_147764_4_ + this.renderMaxY;
/* 740 */     double d14 = p_147764_6_ + this.renderMinZ;
/* 741 */     double d15 = p_147764_6_ + this.renderMaxZ;
/*     */     
/* 743 */     if (this.renderFromInside) {
/*     */       
/* 745 */       d14 = p_147764_6_ + this.renderMaxZ;
/* 746 */       d15 = p_147764_6_ + this.renderMinZ;
/*     */     } 
/* 748 */     int i = bright;
/* 749 */     int j = i >> 16 & 0xFFFF;
/* 750 */     int k = i & 0xFFFF;
/* 751 */     tessellator.func_178180_c().func_181662_b(d11, d12, d15).func_187315_a(d8, d10).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 752 */     tessellator.func_178180_c().func_181662_b(d11, d12, d14).func_187315_a(d4, d6).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 753 */     tessellator.func_178180_c().func_181662_b(d11, d13, d14).func_187315_a(d7, d9).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/* 754 */     tessellator.func_178180_c().func_181662_b(d11, d13, d15).func_187315_a(d3, d5).func_187314_a(j, k).func_181666_a(red, green, blue, 1.0F).func_181675_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RenderCubes getInstance() {
/* 766 */     if (instance == null) instance = new RenderCubes(); 
/* 767 */     return instance;
/*     */   } }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\RenderCubes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */