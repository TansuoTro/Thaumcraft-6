/*     */ package thaumcraft.common.world.objects;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.storage.loot.LootTableList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenGreatwoodTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*  29 */   static final byte[] otherCoordPairs = { 2, 0, 0, 1, 2, 1 };
/*     */ 
/*     */   
/*  32 */   Random rand = new Random();
/*     */   
/*     */   World world;
/*     */   
/*  36 */   int[] basePos = { 0, 0, 0 };
/*  37 */   int heightLimit = 0;
/*     */   int height;
/*  39 */   double heightAttenuation = 0.618D;
/*  40 */   double branchDensity = 1.0D;
/*  41 */   double branchSlope = 0.38D;
/*  42 */   double scaleWidth = 1.2D;
/*  43 */   double leafDensity = 0.9D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   int trunkSize = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   int heightLimitLimit = 11;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   int leafDistanceLimit = 4;
/*     */ 
/*     */   
/*     */   int[][] leafNodes;
/*     */   
/*     */   boolean spiders = false;
/*     */ 
/*     */   
/*     */   public WorldGenGreatwoodTrees(boolean par1, boolean spiders) {
/*  67 */     super(par1);
/*  68 */     this.spiders = spiders;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeList() {
/*  76 */     this.height = (int)(this.heightLimit * this.heightAttenuation);
/*     */     
/*  78 */     if (this.height >= this.heightLimit)
/*     */     {
/*  80 */       this.height = this.heightLimit - 1;
/*     */     }
/*     */     
/*  83 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  85 */     if (var1 < 1)
/*     */     {
/*  87 */       var1 = 1;
/*     */     }
/*     */     
/*  90 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/*  91 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  92 */     int var4 = 1;
/*  93 */     int var5 = this.basePos[1] + this.height;
/*  94 */     int var6 = var3 - this.basePos[1];
/*  95 */     var2[0][0] = this.basePos[0];
/*  96 */     var2[0][1] = var3;
/*  97 */     var2[0][2] = this.basePos[2];
/*  98 */     var2[0][3] = var5;
/*  99 */     var3--;
/*     */     
/* 101 */     while (var6 >= 0) {
/*     */       
/* 103 */       int var7 = 0;
/* 104 */       float var8 = layerSize(var6);
/*     */       
/* 106 */       if (var8 < 0.0F) {
/*     */         
/* 108 */         var3--;
/* 109 */         var6--;
/*     */         
/*     */         continue;
/*     */       } 
/* 113 */       for (double var9 = 0.5D; var7 < var1; var7++) {
/*     */         
/* 115 */         double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/* 116 */         double var13 = this.rand.nextFloat() * 2.0D * Math.PI;
/* 117 */         int var15 = MathHelper.func_76128_c(var11 * Math.sin(var13) + this.basePos[0] + var9);
/* 118 */         int var16 = MathHelper.func_76128_c(var11 * Math.cos(var13) + this.basePos[2] + var9);
/* 119 */         int[] var17 = { var15, var3, var16 };
/* 120 */         int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/*     */         
/* 122 */         if (checkBlockLine(var17, var18) == -1) {
/*     */           
/* 124 */           int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 125 */           double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 126 */           double var22 = var20 * this.branchSlope;
/*     */           
/* 128 */           if (var17[1] - var22 > var5) {
/*     */             
/* 130 */             var19[1] = var5;
/*     */           }
/*     */           else {
/*     */             
/* 134 */             var19[1] = (int)(var17[1] - var22);
/*     */           } 
/*     */           
/* 137 */           if (checkBlockLine(var19, var17) == -1) {
/*     */             
/* 139 */             var2[var4][0] = var15;
/* 140 */             var2[var4][1] = var3;
/* 141 */             var2[var4][2] = var16;
/* 142 */             var2[var4][3] = var19[1];
/* 143 */             var4++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       var3--;
/* 149 */       var6--;
/*     */     } 
/*     */ 
/*     */     
/* 153 */     this.leafNodes = new int[var4][4];
/* 154 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block par6) {
/* 159 */     int var7 = (int)(par4 + 0.618D);
/* 160 */     byte var8 = otherCoordPairs[par5];
/* 161 */     byte var9 = otherCoordPairs[par5 + 3];
/* 162 */     int[] var10 = { par1, par2, par3 };
/* 163 */     int[] var11 = { 0, 0, 0 };
/* 164 */     int var12 = -var7;
/* 165 */     int var13 = -var7;
/*     */     
/* 167 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++) {
/*     */       
/* 169 */       var11[var8] = var10[var8] + var12;
/* 170 */       var13 = -var7;
/*     */       
/* 172 */       while (var13 <= var7) {
/*     */         
/* 174 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/*     */         
/* 176 */         if (var15 > (par4 * par4)) {
/*     */           
/* 178 */           var13++;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         try {
/* 183 */           var11[var9] = var10[var9] + var13;
/* 184 */           IBlockState state = this.world.func_180495_p(new BlockPos(var11[0], var11[1], var11[2]));
/* 185 */           Block block = state.func_177230_c();
/* 186 */           if (block == Blocks.field_150350_a || block == BlocksTC.leafGreatwood)
/*     */           {
/*     */             
/* 189 */             if (block == null || block.canBeReplacedByLeaves(state, this.world, new BlockPos(var11[0], var11[1], var11[2])))
/*     */             {
/* 191 */               func_175903_a(this.world, new BlockPos(var11[0], var11[1], var11[2]), par6.func_176223_P()); } 
/*     */           }
/* 193 */         } catch (Exception exception) {}
/*     */         
/* 195 */         var13++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float layerSize(int par1) {
/* 206 */     if (par1 < this.heightLimit * 0.3D)
/*     */     {
/* 208 */       return -1.618F;
/*     */     }
/*     */ 
/*     */     
/* 212 */     float var2 = this.heightLimit / 2.0F;
/* 213 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */ 
/*     */     
/* 216 */     if (var3 == 0.0F) {
/*     */       
/* 218 */       var4 = var2;
/*     */     }
/* 220 */     else if (Math.abs(var3) >= var2) {
/*     */       
/* 222 */       var4 = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 226 */       var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */     } 
/*     */     
/* 229 */     return 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   float leafSize(int par1) { return (par1 >= 0 && par1 < this.leafDistanceLimit) ? ((par1 != 0 && par1 != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNode(int par1, int par2, int par3) {
/* 244 */     int var4 = par2;
/*     */     
/* 246 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++) {
/*     */       
/* 248 */       float var6 = leafSize(var4 - par2);
/* 249 */       genTreeLayer(par1, var4, par3, var6, (byte)1, BlocksTC.leafGreatwood);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3) {
/* 258 */     int[] var4 = { 0, 0, 0 };
/* 259 */     byte var5 = 0;
/*     */     
/*     */     byte var6;
/* 262 */     for (var6 = 0; var5 < 3; var5 = (byte)(var5 + 1)) {
/*     */       
/* 264 */       var4[var5] = par2ArrayOfInteger[var5] - par1ArrayOfInteger[var5];
/*     */       
/* 266 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
/*     */       {
/* 268 */         var6 = var5;
/*     */       }
/*     */     } 
/*     */     
/* 272 */     if (var4[var6] != 0) {
/*     */       
/* 274 */       byte var9, var7 = otherCoordPairs[var6];
/* 275 */       byte var8 = otherCoordPairs[var6 + 3];
/*     */ 
/*     */       
/* 278 */       if (var4[var6] > 0) {
/*     */         
/* 280 */         var9 = 1;
/*     */       }
/*     */       else {
/*     */         
/* 284 */         var9 = -1;
/*     */       } 
/*     */       
/* 287 */       double var10 = var4[var7] / var4[var6];
/* 288 */       double var12 = var4[var8] / var4[var6];
/* 289 */       int[] var14 = { 0, 0, 0 };
/* 290 */       int var15 = 0;
/*     */       
/* 292 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
/*     */         
/* 294 */         var14[var6] = MathHelper.func_76128_c((par1ArrayOfInteger[var6] + var15) + 0.5D);
/* 295 */         var14[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 296 */         var14[var8] = MathHelper.func_76128_c(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/* 297 */         byte var17 = 1;
/* 298 */         int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
/* 299 */         int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
/* 300 */         int var20 = Math.max(var18, var19);
/*     */         
/* 302 */         if (var20 > 0)
/*     */         {
/* 304 */           if (var18 == var20) {
/*     */             
/* 306 */             var17 = 0;
/*     */           }
/* 308 */           else if (var19 == var20) {
/*     */             
/* 310 */             var17 = 2;
/*     */           } 
/*     */         }
/* 313 */         if (isReplaceable(this.world, new BlockPos(var14[0], var14[1], var14[2]))) {
/* 314 */           func_175903_a(this.world, new BlockPos(var14[0], var14[1], var14[2]), par3.func_176203_a(var17));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeaves() {
/* 324 */     int var1 = 0;
/*     */     
/* 326 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++) {
/*     */       
/* 328 */       int var3 = this.leafNodes[var1][0];
/* 329 */       int var4 = this.leafNodes[var1][1];
/* 330 */       int var5 = this.leafNodes[var1][2];
/* 331 */       generateLeafNode(var3, var4, var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   boolean leafNodeNeedsBase(int par1) { return (par1 >= this.heightLimit * 0.2D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateTrunk() {
/* 349 */     int var1 = this.basePos[0];
/* 350 */     int var2 = this.basePos[1];
/* 351 */     int var3 = this.basePos[1] + this.height;
/* 352 */     int var4 = this.basePos[2];
/* 353 */     int[] var5 = { var1, var2, var4 };
/* 354 */     int[] var6 = { var1, var3, var4 };
/* 355 */     placeBlockLine(var5, var6, BlocksTC.logGreatwood);
/*     */     
/* 357 */     if (this.trunkSize == 2) {
/*     */       
/* 359 */       var5[0] = var5[0] + 1;
/* 360 */       var6[0] = var6[0] + 1;
/* 361 */       placeBlockLine(var5, var6, BlocksTC.logGreatwood);
/* 362 */       var5[2] = var5[2] + 1;
/* 363 */       var6[2] = var6[2] + 1;
/* 364 */       placeBlockLine(var5, var6, BlocksTC.logGreatwood);
/* 365 */       var5[0] = var5[0] + -1;
/* 366 */       var6[0] = var6[0] + -1;
/* 367 */       placeBlockLine(var5, var6, BlocksTC.logGreatwood);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeBases() {
/* 376 */     int var1 = 0;
/* 377 */     int var2 = this.leafNodes.length;
/*     */     
/* 379 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++) {
/*     */       
/* 381 */       int[] var4 = this.leafNodes[var1];
/* 382 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 383 */       var3[1] = var4[3];
/* 384 */       int var6 = var3[1] - this.basePos[1];
/*     */       
/* 386 */       if (leafNodeNeedsBase(var6))
/*     */       {
/* 388 */         placeBlockLine(var3, var5, BlocksTC.logGreatwood);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
/*     */     byte var8;
/* 399 */     int[] var3 = { 0, 0, 0 };
/* 400 */     byte var4 = 0;
/*     */     
/*     */     byte var5;
/* 403 */     for (var5 = 0; var4 < 3; var4 = (byte)(var4 + 1)) {
/*     */       
/* 405 */       var3[var4] = par2ArrayOfInteger[var4] - par1ArrayOfInteger[var4];
/*     */       
/* 407 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
/*     */       {
/* 409 */         var5 = var4;
/*     */       }
/*     */     } 
/*     */     
/* 413 */     if (var3[var5] == 0)
/*     */     {
/* 415 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 419 */     byte var6 = otherCoordPairs[var5];
/* 420 */     byte var7 = otherCoordPairs[var5 + 3];
/*     */ 
/*     */     
/* 423 */     if (var3[var5] > 0) {
/*     */       
/* 425 */       var8 = 1;
/*     */     }
/*     */     else {
/*     */       
/* 429 */       var8 = -1;
/*     */     } 
/*     */     
/* 432 */     double var9 = var3[var6] / var3[var5];
/* 433 */     double var11 = var3[var7] / var3[var5];
/* 434 */     int[] var13 = { 0, 0, 0 };
/* 435 */     int var14 = 0;
/*     */     
/*     */     int var15;
/* 438 */     for (var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
/*     */       
/* 440 */       var13[var5] = par1ArrayOfInteger[var5] + var14;
/* 441 */       var13[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var14 * var9);
/* 442 */       var13[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var14 * var11);
/*     */       try {
/* 444 */         Block var16 = this.world.func_180495_p(new BlockPos(var13[0], var13[1], var13[2])).func_177230_c();
/*     */         
/* 446 */         if (var16 != Blocks.field_150350_a && var16 != BlocksTC.leafGreatwood) {
/*     */           break;
/*     */         }
/*     */       }
/* 450 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 454 */     return (var14 == var15) ? -1 : Math.abs(var14);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean validTreeLocation(int x, int z) {
/* 464 */     int[] var1 = { this.basePos[0] + x, this.basePos[1], this.basePos[2] + z };
/* 465 */     int[] var2 = { this.basePos[0] + x, this.basePos[1] + this.heightLimit - 1, this.basePos[2] + z };
/*     */     try {
/* 467 */       IBlockState state = this.world.func_180495_p(new BlockPos(this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z));
/* 468 */       Block var3 = state.func_177230_c();
/* 469 */       boolean isSoil = var3.canSustainPlant(state, this.world, new BlockPos(this.basePos[0] + x, this.basePos[1] - 1, this.basePos[2] + z), EnumFacing.UP, (BlockSapling)Blocks.field_150345_g);
/* 470 */       if (!isSoil)
/*     */       {
/* 472 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 476 */       int var4 = checkBlockLine(var1, var2);
/*     */       
/* 478 */       if (var4 == -1)
/*     */       {
/* 480 */         return true;
/*     */       }
/* 482 */       if (var4 < 6)
/*     */       {
/* 484 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 488 */       this.heightLimit = var4;
/* 489 */       return true;
/*     */     
/*     */     }
/* 492 */     catch (Exception e) {
/* 493 */       return false;
/*     */     } 
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
/*     */   public void setScale(double par1, double par3, double par5) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180709_b(World par1World, Random par2Random, BlockPos pos) {
/* 516 */     this.world = par1World;
/* 517 */     long var6 = par2Random.nextLong();
/* 518 */     this.rand.setSeed(var6);
/* 519 */     this.basePos[0] = pos.func_177958_n();
/* 520 */     this.basePos[1] = pos.func_177956_o();
/* 521 */     this.basePos[2] = pos.func_177952_p();
/*     */     
/* 523 */     if (this.heightLimit == 0)
/*     */     {
/* 525 */       this.heightLimit = this.heightLimitLimit + this.rand.nextInt(this.heightLimitLimit);
/*     */     }
/*     */     
/* 528 */     int x = 0;
/* 529 */     int z = 0;
/*     */     
/* 531 */     for (x = 0; x < this.trunkSize; x++) {
/* 532 */       for (z = 0; z < this.trunkSize; z++) {
/* 533 */         if (!validTreeLocation(x, z)) {
/*     */           
/* 535 */           this.world = null;
/* 536 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 540 */     this.world.func_175698_g(pos);
/*     */     
/* 542 */     generateLeafNodeList();
/* 543 */     generateLeaves();
/* 544 */     generateLeafNodeBases();
/* 545 */     generateTrunk();
/*     */     
/* 547 */     this.scaleWidth = 1.66D;
/*     */     
/* 549 */     this.basePos[0] = pos.func_177958_n();
/* 550 */     this.basePos[1] = pos.func_177956_o() + this.height;
/* 551 */     this.basePos[2] = pos.func_177952_p();
/* 552 */     generateLeafNodeList();
/* 553 */     generateLeaves();
/* 554 */     generateLeafNodeBases();
/* 555 */     generateTrunk();
/*     */     
/* 557 */     if (this.spiders) {
/* 558 */       this.world.func_175656_a(pos.func_177977_b(), Blocks.field_150474_ac.func_176223_P());
/* 559 */       TileEntityMobSpawner var14 = (TileEntityMobSpawner)par1World.func_175625_s(pos.func_177977_b());
/* 560 */       if (var14 != null) {
/*     */         
/* 562 */         var14.func_145881_a().func_190894_a(EntityList.func_191306_a(net.minecraft.entity.monster.EntityCaveSpider.class));
/* 563 */         for (int a = 0; a < 50; a++) {
/* 564 */           int xx = pos.func_177958_n() - 7 + par2Random.nextInt(14);
/* 565 */           int yy = pos.func_177956_o() + par2Random.nextInt(10);
/* 566 */           int zz = pos.func_177952_p() - 7 + par2Random.nextInt(14);
/* 567 */           if (par1World.func_175623_d(new BlockPos(xx, yy, zz)) && (
/* 568 */             BlockUtils.isBlockTouching(par1World, new BlockPos(xx, yy, zz), BlocksTC.leafGreatwood) || 
/* 569 */             BlockUtils.isBlockTouching(par1World, new BlockPos(xx, yy, zz), BlocksTC.logGreatwood))) {
/* 570 */             this.world.func_175656_a(new BlockPos(xx, yy, zz), Blocks.field_150321_G.func_176223_P());
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 577 */         par1World.func_175656_a(pos.func_177979_c(2), Blocks.field_150486_ae.func_176223_P());
/* 578 */         TileEntityChest var16 = (TileEntityChest)par1World.func_175625_s(pos.func_177979_c(2));
/*     */         
/* 580 */         if (var16 != null)
/*     */         {
/* 582 */           var16.func_189404_a(LootTableList.field_186422_d, this.rand.nextLong());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 587 */     this.world = null;
/* 588 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\objects\WorldGenGreatwoodTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */