/*     */ package thaumcraft.common.world.objects;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ 
/*     */ 
/*     */ public class WorldGenSilverwoodTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private final int minTreeHeight;
/*     */   private final int randomTreeHeight;
/*     */   boolean worldgen = false;
/*     */   
/*     */   public WorldGenSilverwoodTrees(boolean doBlockNotify, int minTreeHeight, int randomTreeHeight) {
/*  25 */     super(doBlockNotify);
/*     */     
/*  27 */     this.worldgen = !doBlockNotify;
/*  28 */     this.minTreeHeight = minTreeHeight;
/*  29 */     this.randomTreeHeight = randomTreeHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180709_b(World world, Random random, BlockPos pos) {
/*  37 */     int height = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
/*     */     
/*  39 */     boolean flag = true;
/*     */     
/*  41 */     int x = pos.func_177958_n();
/*  42 */     int y = pos.func_177956_o();
/*  43 */     int z = pos.func_177952_p();
/*     */     
/*  45 */     if (y >= 1 && y + height + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  50 */       for (int i1 = y; i1 <= y + 1 + height; i1++) {
/*     */         
/*  52 */         byte spread = 1;
/*     */         
/*  54 */         if (i1 == y)
/*     */         {
/*  56 */           spread = 0;
/*     */         }
/*     */         
/*  59 */         if (i1 >= y + 1 + height - 2)
/*     */         {
/*  61 */           spread = 3;
/*     */         }
/*     */         
/*  64 */         for (int j1 = x - spread; j1 <= x + spread && flag; j1++) {
/*     */           
/*  66 */           for (int k1 = z - spread; k1 <= z + spread && flag; k1++) {
/*     */             
/*  68 */             if (i1 >= 0 && i1 < 256) {
/*     */               
/*  70 */               IBlockState state = world.func_180495_p(new BlockPos(j1, i1, k1));
/*  71 */               Block block = state.func_177230_c();
/*     */               
/*  73 */               if (!block.isAir(state, world, new BlockPos(j1, i1, k1)) && !block.isLeaves(state, world, new BlockPos(j1, i1, k1)) && 
/*  74 */                 !block.func_176200_f(world, new BlockPos(j1, i1, k1)))
/*     */               {
/*  76 */                 if (i1 > y)
/*     */                 {
/*  78 */                   flag = false;
/*     */                 }
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  84 */               flag = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       if (!flag)
/*     */       {
/*  92 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  96 */       IBlockState state = world.func_180495_p(new BlockPos(x, y - 1, z));
/*  97 */       Block block1 = state.func_177230_c();
/*     */       
/*  99 */       boolean isSoil = block1.canSustainPlant(state, world, new BlockPos(x, y - 1, z), EnumFacing.UP, (BlockSapling)Blocks.field_150345_g);
/* 100 */       if (isSoil && y < 256 - height - 1) {
/*     */         
/* 102 */         block1.onPlantGrow(state, world, new BlockPos(x, y - 1, z), new BlockPos(x, y, z));
/* 103 */         int start = y + height - 5;
/* 104 */         int end = y + height + 3 + random.nextInt(3);
/*     */         
/*     */         int k2;
/* 107 */         for (k2 = start; k2 <= end; k2++) {
/*     */           
/* 109 */           int cty = MathHelper.func_76125_a(k2, y + height - 3, y + height);
/*     */           
/* 111 */           for (int xx = x - 5; xx <= x + 5; xx++) {
/*     */             
/* 113 */             for (int zz = z - 5; zz <= z + 5; zz++) {
/*     */               
/* 115 */               double d3 = (xx - x);
/* 116 */               double d4 = (k2 - cty);
/* 117 */               double d5 = (zz - z);
/* 118 */               double dist = d3 * d3 + d4 * d4 + d5 * d5;
/* 119 */               IBlockState s2 = world.func_180495_p(new BlockPos(xx, k2, zz));
/* 120 */               if (dist < (10 + random.nextInt(8)) && s2
/* 121 */                 .func_177230_c().canBeReplacedByLeaves(s2, world, new BlockPos(xx, k2, zz)))
/*     */               {
/* 123 */                 func_175903_a(world, new BlockPos(xx, k2, zz), BlocksTC.leafSilverwood.func_176203_a(1));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 132 */         for (k2 = 0; k2 < height; k2++) {
/*     */           
/* 134 */           IBlockState s3 = world.func_180495_p(new BlockPos(x, y + k2, z));
/* 135 */           Block block2 = s3.func_177230_c();
/* 136 */           if (block2.isAir(s3, world, new BlockPos(x, y + k2, z)) || block2.isLeaves(s3, world, new BlockPos(x, y + k2, z)) || block2
/* 137 */             .func_176200_f(world, new BlockPos(x, y + k2, z))) {
/*     */             
/* 139 */             func_175903_a(world, new BlockPos(x, y + k2, z), BlocksTC.logSilverwood.func_176203_a(1));
/* 140 */             func_175903_a(world, new BlockPos(x - 1, y + k2, z), BlocksTC.logSilverwood.func_176203_a(1));
/* 141 */             func_175903_a(world, new BlockPos(x + 1, y + k2, z), BlocksTC.logSilverwood.func_176203_a(1));
/* 142 */             func_175903_a(world, new BlockPos(x, y + k2, z - 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 143 */             func_175903_a(world, new BlockPos(x, y + k2, z + 1), BlocksTC.logSilverwood.func_176203_a(1));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 148 */         func_175903_a(world, new BlockPos(x, y + k2, z), BlocksTC.logSilverwood.func_176203_a(1));
/* 149 */         func_175903_a(world, new BlockPos(x - 1, y, z - 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 150 */         func_175903_a(world, new BlockPos(x + 1, y, z + 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 151 */         func_175903_a(world, new BlockPos(x - 1, y, z + 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 152 */         func_175903_a(world, new BlockPos(x + 1, y, z - 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 153 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x - 1, y + 1, z - 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 154 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x + 1, y + 1, z + 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 155 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x - 1, y + 1, z + 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 156 */         if (random.nextInt(3) != 0) func_175903_a(world, new BlockPos(x + 1, y + 1, z - 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 157 */         func_175903_a(world, new BlockPos(x - 2, y, z), BlocksTC.logSilverwood.func_176203_a(0));
/* 158 */         func_175903_a(world, new BlockPos(x + 2, y, z), BlocksTC.logSilverwood.func_176203_a(0));
/* 159 */         func_175903_a(world, new BlockPos(x, y, z - 2), BlocksTC.logSilverwood.func_176203_a(2));
/* 160 */         func_175903_a(world, new BlockPos(x, y, z + 2), BlocksTC.logSilverwood.func_176203_a(2));
/* 161 */         func_175903_a(world, new BlockPos(x - 2, y - 1, z), BlocksTC.logSilverwood.func_176203_a(1));
/* 162 */         func_175903_a(world, new BlockPos(x + 2, y - 1, z), BlocksTC.logSilverwood.func_176203_a(1));
/* 163 */         func_175903_a(world, new BlockPos(x, y - 1, z - 2), BlocksTC.logSilverwood.func_176203_a(1));
/* 164 */         func_175903_a(world, new BlockPos(x, y - 1, z + 2), BlocksTC.logSilverwood.func_176203_a(1));
/* 165 */         func_175903_a(world, new BlockPos(x - 1, y + height - 4, z - 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 166 */         func_175903_a(world, new BlockPos(x + 1, y + height - 4, z + 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 167 */         func_175903_a(world, new BlockPos(x - 1, y + height - 4, z + 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 168 */         func_175903_a(world, new BlockPos(x + 1, y + height - 4, z - 1), BlocksTC.logSilverwood.func_176203_a(1));
/* 169 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x - 1, y + height - 5, z - 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 170 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x + 1, y + height - 5, z + 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 171 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x - 1, y + height - 5, z + 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 172 */         if (random.nextInt(3) == 0) func_175903_a(world, new BlockPos(x + 1, y + height - 5, z - 1), BlocksTC.logSilverwood.func_176203_a(1)); 
/* 173 */         func_175903_a(world, new BlockPos(x - 2, y + height - 4, z), BlocksTC.logSilverwood.func_176203_a(0));
/* 174 */         func_175903_a(world, new BlockPos(x + 2, y + height - 4, z), BlocksTC.logSilverwood.func_176203_a(0));
/* 175 */         func_175903_a(world, new BlockPos(x, y + height - 4, z - 2), BlocksTC.logSilverwood.func_176203_a(2));
/* 176 */         func_175903_a(world, new BlockPos(x, y + height - 4, z + 2), BlocksTC.logSilverwood.func_176203_a(2));
/*     */         
/* 178 */         if (this.worldgen) {
/* 179 */           WorldGenerator flowers = new WorldGenCustomFlowers(BlocksTC.shimmerleaf, 0);
/* 180 */           flowers.func_180709_b(world, random, new BlockPos(x, y, z));
/*     */         } 
/*     */         
/* 183 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 189 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_175903_a(World worldIn, BlockPos pos, IBlockState state) {
/* 202 */     IBlockState bs = worldIn.func_180495_p(pos);
/* 203 */     if (worldIn.func_175623_d(pos) || bs.func_177230_c().isLeaves(bs, worldIn, pos) || bs.func_177230_c().func_176200_f(worldIn, pos))
/*     */     {
/* 205 */       super.func_175903_a(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\objects\WorldGenSilverwoodTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */