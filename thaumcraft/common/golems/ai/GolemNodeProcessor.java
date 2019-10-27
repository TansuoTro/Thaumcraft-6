/*     */ package thaumcraft.common.golems.ai;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.BlockFenceGate;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.NodeProcessor;
/*     */ import net.minecraft.pathfinding.PathNodeType;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GolemNodeProcessor
/*     */   extends NodeProcessor
/*     */ {
/*     */   private float avoidsWater;
/*     */   
/*     */   public void func_186315_a(IBlockAccess sourceIn, EntityLiving mob) {
/*  36 */     super.func_186315_a(sourceIn, mob);
/*  37 */     this.avoidsWater = mob.func_184643_a(PathNodeType.WATER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176163_a() {
/*  48 */     this.field_186326_b.func_184644_a(PathNodeType.WATER, this.avoidsWater);
/*  49 */     super.func_176163_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint func_186318_b() {
/*     */     int i;
/*  58 */     if (func_186322_e() && this.field_186326_b.func_70090_H()) {
/*     */       
/*  60 */       i = (int)(this.field_186326_b.func_174813_aQ()).field_72338_b;
/*  61 */       BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.func_76128_c(this.field_186326_b.field_70165_t), i, MathHelper.func_76128_c(this.field_186326_b.field_70161_v));
/*     */       
/*  63 */       for (Block block = this.field_176169_a.func_180495_p(blockpos$mutableblockpos).func_177230_c(); block == Blocks.field_150358_i || block == Blocks.field_150355_j; block = this.field_176169_a.func_180495_p(blockpos$mutableblockpos).func_177230_c())
/*     */       {
/*  65 */         i++;
/*  66 */         blockpos$mutableblockpos.func_181079_c(MathHelper.func_76128_c(this.field_186326_b.field_70165_t), i, MathHelper.func_76128_c(this.field_186326_b.field_70161_v));
/*     */       }
/*     */     
/*  69 */     } else if (this.field_186326_b.field_70122_E) {
/*     */       
/*  71 */       i = MathHelper.func_76128_c((this.field_186326_b.func_174813_aQ()).field_72338_b + 0.5D);
/*     */     } else {
/*     */       BlockPos blockpos;
/*     */ 
/*     */ 
/*     */       
/*  77 */       for (blockpos = new BlockPos(this.field_186326_b); (this.field_176169_a.func_180495_p(blockpos).func_185904_a() == Material.field_151579_a || this.field_176169_a.func_180495_p(blockpos).func_177230_c().func_176205_b(this.field_176169_a, blockpos)) && blockpos.func_177956_o() > 0; blockpos = blockpos.func_177977_b());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  82 */       i = blockpos.func_177984_a().func_177956_o();
/*     */     } 
/*     */     
/*  85 */     BlockPos blockpos2 = new BlockPos(this.field_186326_b);
/*  86 */     PathNodeType pathnodetype1 = getPathNodeType(this.field_186326_b, blockpos2.func_177958_n(), i, blockpos2.func_177952_p());
/*     */     
/*  88 */     if (this.field_186326_b.func_184643_a(pathnodetype1) < 0.0F) {
/*     */       
/*  90 */       Set<BlockPos> set = Sets.newHashSet();
/*  91 */       set.add(new BlockPos((this.field_186326_b.func_174813_aQ()).field_72340_a, i, (this.field_186326_b.func_174813_aQ()).field_72339_c));
/*  92 */       set.add(new BlockPos((this.field_186326_b.func_174813_aQ()).field_72340_a, i, (this.field_186326_b.func_174813_aQ()).field_72334_f));
/*  93 */       set.add(new BlockPos((this.field_186326_b.func_174813_aQ()).field_72336_d, i, (this.field_186326_b.func_174813_aQ()).field_72339_c));
/*  94 */       set.add(new BlockPos((this.field_186326_b.func_174813_aQ()).field_72336_d, i, (this.field_186326_b.func_174813_aQ()).field_72334_f));
/*     */       
/*  96 */       for (BlockPos blockpos1 : set) {
/*     */         
/*  98 */         PathNodeType pathnodetype = getPathNodeType(this.field_186326_b, blockpos1);
/*     */         
/* 100 */         if (this.field_186326_b.func_184643_a(pathnodetype) >= 0.0F)
/*     */         {
/* 102 */           return func_176159_a(blockpos1.func_177958_n(), blockpos1.func_177956_o(), blockpos1.func_177952_p());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return func_176159_a(blockpos2.func_177958_n(), i, blockpos2.func_177952_p());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public PathPoint func_186325_a(double x, double y, double z) { return func_176159_a(MathHelper.func_76128_c(x - (this.field_186326_b.field_70130_N / 2.0F)), MathHelper.func_76128_c(y), MathHelper.func_76128_c(z - (this.field_186326_b.field_70130_N / 2.0F))); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_186320_a(PathPoint[] pathOptions, PathPoint currentPoint, PathPoint targetPoint, float maxDistance) {
/* 122 */     int i = 0;
/* 123 */     int j = 0;
/* 124 */     PathNodeType pathnodetype = getPathNodeType(this.field_186326_b, currentPoint.field_75839_a, currentPoint.field_75837_b + 1, currentPoint.field_75838_c);
/*     */     
/* 126 */     if (this.field_186326_b.func_184643_a(pathnodetype) >= 0.0F)
/*     */     {
/* 128 */       j = MathHelper.func_76141_d(Math.max(1.0F, this.field_186326_b.field_70138_W));
/*     */     }
/*     */     
/* 131 */     BlockPos blockpos = (new BlockPos(currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c)).func_177977_b();
/* 132 */     double d0 = currentPoint.field_75837_b - 1.0D - (this.field_176169_a.func_180495_p(blockpos).func_185900_c(this.field_176169_a, blockpos)).field_72337_e;
/* 133 */     PathPoint pathpoint = getSafePoint(currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, j, d0, EnumFacing.SOUTH);
/* 134 */     PathPoint pathpoint1 = getSafePoint(currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c, j, d0, EnumFacing.WEST);
/* 135 */     PathPoint pathpoint2 = getSafePoint(currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c, j, d0, EnumFacing.EAST);
/* 136 */     PathPoint pathpoint3 = getSafePoint(currentPoint.field_75839_a, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, j, d0, EnumFacing.NORTH);
/*     */     
/* 138 */     if (pathpoint != null && !pathpoint.field_75842_i && pathpoint.func_75829_a(targetPoint) < maxDistance)
/*     */     {
/* 140 */       pathOptions[i++] = pathpoint;
/*     */     }
/*     */     
/* 143 */     if (pathpoint1 != null && !pathpoint1.field_75842_i && pathpoint1.func_75829_a(targetPoint) < maxDistance)
/*     */     {
/* 145 */       pathOptions[i++] = pathpoint1;
/*     */     }
/*     */     
/* 148 */     if (pathpoint2 != null && !pathpoint2.field_75842_i && pathpoint2.func_75829_a(targetPoint) < maxDistance)
/*     */     {
/* 150 */       pathOptions[i++] = pathpoint2;
/*     */     }
/*     */     
/* 153 */     if (pathpoint3 != null && !pathpoint3.field_75842_i && pathpoint3.func_75829_a(targetPoint) < maxDistance)
/*     */     {
/* 155 */       pathOptions[i++] = pathpoint3;
/*     */     }
/*     */     
/* 158 */     boolean flag = (pathpoint3 == null || pathpoint3.field_186287_m == PathNodeType.OPEN || pathpoint3.field_186286_l != 0.0F);
/* 159 */     boolean flag1 = (pathpoint == null || pathpoint.field_186287_m == PathNodeType.OPEN || pathpoint.field_186286_l != 0.0F);
/* 160 */     boolean flag2 = (pathpoint2 == null || pathpoint2.field_186287_m == PathNodeType.OPEN || pathpoint2.field_186286_l != 0.0F);
/* 161 */     boolean flag3 = (pathpoint1 == null || pathpoint1.field_186287_m == PathNodeType.OPEN || pathpoint1.field_186286_l != 0.0F);
/*     */     
/* 163 */     if (flag && flag3) {
/*     */       
/* 165 */       PathPoint pathpoint4 = getSafePoint(currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, j, d0, EnumFacing.NORTH);
/*     */       
/* 167 */       if (pathpoint4 != null && !pathpoint4.field_75842_i && pathpoint4.func_75829_a(targetPoint) < maxDistance)
/*     */       {
/* 169 */         pathOptions[i++] = pathpoint4;
/*     */       }
/*     */     } 
/*     */     
/* 173 */     if (flag && flag2) {
/*     */       
/* 175 */       PathPoint pathpoint5 = getSafePoint(currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c - 1, j, d0, EnumFacing.NORTH);
/*     */       
/* 177 */       if (pathpoint5 != null && !pathpoint5.field_75842_i && pathpoint5.func_75829_a(targetPoint) < maxDistance)
/*     */       {
/* 179 */         pathOptions[i++] = pathpoint5;
/*     */       }
/*     */     } 
/*     */     
/* 183 */     if (flag1 && flag3) {
/*     */       
/* 185 */       PathPoint pathpoint6 = getSafePoint(currentPoint.field_75839_a - 1, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, j, d0, EnumFacing.SOUTH);
/*     */       
/* 187 */       if (pathpoint6 != null && !pathpoint6.field_75842_i && pathpoint6.func_75829_a(targetPoint) < maxDistance)
/*     */       {
/* 189 */         pathOptions[i++] = pathpoint6;
/*     */       }
/*     */     } 
/*     */     
/* 193 */     if (flag1 && flag2) {
/*     */       
/* 195 */       PathPoint pathpoint7 = getSafePoint(currentPoint.field_75839_a + 1, currentPoint.field_75837_b, currentPoint.field_75838_c + 1, j, d0, EnumFacing.SOUTH);
/*     */       
/* 197 */       if (pathpoint7 != null && !pathpoint7.field_75842_i && pathpoint7.func_75829_a(targetPoint) < maxDistance)
/*     */       {
/* 199 */         pathOptions[i++] = pathpoint7;
/*     */       }
/*     */     } 
/*     */     
/* 203 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PathPoint getSafePoint(int x, int y, int z, int p_186332_4_, double p_186332_5_, EnumFacing facing) {
/* 212 */     PathPoint pathpoint = null;
/* 213 */     BlockPos blockpos = new BlockPos(x, y, z);
/* 214 */     BlockPos blockpos1 = blockpos.func_177977_b();
/* 215 */     double d0 = y - 1.0D - (this.field_176169_a.func_180495_p(blockpos1).func_185900_c(this.field_176169_a, blockpos1)).field_72337_e;
/*     */     
/* 217 */     if (d0 - p_186332_5_ > 1.125D)
/*     */     {
/* 219 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 223 */     PathNodeType pathnodetype = getPathNodeType(this.field_186326_b, x, y, z);
/* 224 */     float f = this.field_186326_b.func_184643_a(pathnodetype);
/* 225 */     double d1 = this.field_186326_b.field_70130_N / 2.0D;
/*     */     
/* 227 */     if (f >= 0.0F) {
/*     */       
/* 229 */       pathpoint = func_176159_a(x, y, z);
/* 230 */       pathpoint.field_186287_m = pathnodetype;
/* 231 */       pathpoint.field_186286_l = Math.max(pathpoint.field_186286_l, f);
/*     */     } 
/*     */     
/* 234 */     if (pathnodetype == PathNodeType.WALKABLE)
/*     */     {
/* 236 */       return pathpoint;
/*     */     }
/*     */ 
/*     */     
/* 240 */     if (pathpoint == null && p_186332_4_ > 0 && pathnodetype != PathNodeType.FENCE && pathnodetype != PathNodeType.TRAPDOOR) {
/*     */       
/* 242 */       pathpoint = getSafePoint(x, y + 1, z, p_186332_4_ - 1, p_186332_5_, facing);
/*     */       
/* 244 */       if (pathpoint != null && (pathpoint.field_186287_m == PathNodeType.OPEN || pathpoint.field_186287_m == PathNodeType.WALKABLE) && this.field_186326_b.field_70130_N < 1.0F) {
/*     */         
/* 246 */         double d2 = (x - facing.func_82601_c()) + 0.5D;
/* 247 */         double d3 = (z - facing.func_82599_e()) + 0.5D;
/* 248 */         AxisAlignedBB axisalignedbb = new AxisAlignedBB(d2 - d1, y + 0.001D, d3 - d1, d2 + d1, (y + this.field_186326_b.field_70131_O), d3 + d1);
/* 249 */         AxisAlignedBB axisalignedbb1 = this.field_176169_a.func_180495_p(blockpos).func_185900_c(this.field_176169_a, blockpos);
/* 250 */         AxisAlignedBB axisalignedbb2 = axisalignedbb.func_72321_a(0.0D, axisalignedbb1.field_72337_e - 0.002D, 0.0D);
/*     */         
/* 252 */         if (this.field_186326_b.field_70170_p.func_184143_b(axisalignedbb2))
/*     */         {
/* 254 */           pathpoint = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 259 */     if (pathnodetype == PathNodeType.OPEN) {
/*     */       
/* 261 */       AxisAlignedBB axisalignedbb3 = new AxisAlignedBB(x - d1 + 0.5D, y + 0.001D, z - d1 + 0.5D, x + d1 + 0.5D, (y + this.field_186326_b.field_70131_O), z + d1 + 0.5D);
/*     */       
/* 263 */       if (this.field_186326_b.field_70170_p.func_184143_b(axisalignedbb3))
/*     */       {
/* 265 */         return null;
/*     */       }
/*     */       
/* 268 */       if (this.field_186326_b.field_70130_N >= 1.0F) {
/*     */         
/* 270 */         PathNodeType pathnodetype1 = getPathNodeType(this.field_186326_b, x, y - 1, z);
/*     */         
/* 272 */         if (pathnodetype1 == PathNodeType.BLOCKED) {
/*     */           
/* 274 */           pathpoint = func_176159_a(x, y, z);
/* 275 */           pathpoint.field_186287_m = PathNodeType.WALKABLE;
/* 276 */           pathpoint.field_186286_l = Math.max(pathpoint.field_186286_l, f);
/* 277 */           return pathpoint;
/*     */         } 
/*     */       } 
/*     */       
/* 281 */       int i = 0;
/*     */       
/* 283 */       while (y > 0 && pathnodetype == PathNodeType.OPEN) {
/*     */         
/* 285 */         y--;
/*     */         
/* 287 */         if (i++ >= this.field_186326_b.func_82143_as())
/*     */         {
/* 289 */           return null;
/*     */         }
/*     */         
/* 292 */         pathnodetype = getPathNodeType(this.field_186326_b, x, y, z);
/* 293 */         f = this.field_186326_b.func_184643_a(pathnodetype);
/*     */         
/* 295 */         if (pathnodetype != PathNodeType.OPEN && f >= 0.0F) {
/*     */           
/* 297 */           pathpoint = func_176159_a(x, y, z);
/* 298 */           pathpoint.field_186287_m = pathnodetype;
/* 299 */           pathpoint.field_186286_l = Math.max(pathpoint.field_186286_l, f);
/*     */           
/*     */           break;
/*     */         } 
/* 303 */         if (f < 0.0F)
/*     */         {
/* 305 */           return null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 310 */     return pathpoint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathNodeType func_186319_a(IBlockAccess blockaccessIn, int x, int y, int z, EntityLiving entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
/* 318 */     EnumSet<PathNodeType> enumset = EnumSet.noneOf(PathNodeType.class);
/* 319 */     PathNodeType pathnodetype = PathNodeType.BLOCKED;
/* 320 */     double d0 = entitylivingIn.field_70130_N / 2.0D;
/* 321 */     BlockPos blockpos = new BlockPos(entitylivingIn);
/*     */     
/* 323 */     for (int i = 0; i < xSize; i++) {
/*     */       
/* 325 */       for (int j = 0; j < ySize; j++) {
/*     */         
/* 327 */         for (int k = 0; k < zSize; k++) {
/*     */           
/* 329 */           int l = i + x;
/* 330 */           int i1 = j + y;
/* 331 */           int j1 = k + z;
/* 332 */           PathNodeType pathnodetype1 = func_186330_a(blockaccessIn, l, i1, j1);
/*     */           
/* 334 */           if (pathnodetype1 == PathNodeType.DOOR_WOOD_CLOSED && canBreakDoorsIn && canEnterDoorsIn)
/*     */           {
/* 336 */             pathnodetype1 = PathNodeType.WALKABLE;
/*     */           }
/*     */           
/* 339 */           if (pathnodetype1 == PathNodeType.DOOR_OPEN && !canEnterDoorsIn)
/*     */           {
/* 341 */             pathnodetype1 = PathNodeType.BLOCKED;
/*     */           }
/*     */           
/* 344 */           if (pathnodetype1 == PathNodeType.RAIL && !(blockaccessIn.func_180495_p(blockpos).func_177230_c() instanceof net.minecraft.block.BlockRailBase) && !(blockaccessIn.func_180495_p(blockpos.func_177977_b()).func_177230_c() instanceof net.minecraft.block.BlockRailBase))
/*     */           {
/* 346 */             pathnodetype1 = PathNodeType.FENCE;
/*     */           }
/*     */           
/* 349 */           if (i == 0 && j == 0 && k == 0)
/*     */           {
/* 351 */             pathnodetype = pathnodetype1;
/*     */           }
/*     */           
/* 354 */           enumset.add(pathnodetype1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 359 */     if (enumset.contains(PathNodeType.FENCE))
/*     */     {
/* 361 */       return PathNodeType.FENCE;
/*     */     }
/*     */ 
/*     */     
/* 365 */     PathNodeType pathnodetype2 = PathNodeType.BLOCKED;
/*     */     
/* 367 */     for (PathNodeType pathnodetype3 : enumset) {
/*     */       
/* 369 */       if (entitylivingIn.func_184643_a(pathnodetype3) < 0.0F)
/*     */       {
/* 371 */         return pathnodetype3;
/*     */       }
/*     */       
/* 374 */       if (entitylivingIn.func_184643_a(pathnodetype3) >= entitylivingIn.func_184643_a(pathnodetype2))
/*     */       {
/* 376 */         pathnodetype2 = pathnodetype3;
/*     */       }
/*     */     } 
/*     */     
/* 380 */     if (pathnodetype == PathNodeType.OPEN && entitylivingIn.func_184643_a(pathnodetype2) == 0.0F)
/*     */     {
/* 382 */       return PathNodeType.OPEN;
/*     */     }
/*     */ 
/*     */     
/* 386 */     return pathnodetype2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   private PathNodeType getPathNodeType(EntityLiving entitylivingIn, BlockPos pos) { return getPathNodeType(entitylivingIn, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 398 */   private PathNodeType getPathNodeType(EntityLiving entitylivingIn, int x, int y, int z) { return func_186319_a(this.field_176169_a, x, y, z, entitylivingIn, this.field_176168_c, this.field_176165_d, this.field_176166_e, false, func_186323_c()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathNodeType func_186330_a(IBlockAccess blockaccessIn, int x, int y, int z) {
/* 404 */     PathNodeType pathnodetype = getPathNodeTypeRaw(blockaccessIn, x, y, z);
/*     */     
/* 406 */     if (pathnodetype == PathNodeType.OPEN && y >= 1) {
/*     */       
/* 408 */       Block block = blockaccessIn.func_180495_p(new BlockPos(x, y - 1, z)).func_177230_c();
/* 409 */       PathNodeType pathnodetype1 = getPathNodeTypeRaw(blockaccessIn, x, y - 1, z);
/* 410 */       pathnodetype = (pathnodetype1 != PathNodeType.WALKABLE && pathnodetype1 != PathNodeType.OPEN && pathnodetype1 != PathNodeType.WATER && pathnodetype1 != PathNodeType.LAVA) ? PathNodeType.WALKABLE : PathNodeType.OPEN;
/*     */       
/* 412 */       if (pathnodetype1 == PathNodeType.DAMAGE_FIRE || block == Blocks.field_189877_df)
/*     */       {
/* 414 */         pathnodetype = PathNodeType.DAMAGE_FIRE;
/*     */       }
/*     */       
/* 417 */       if (pathnodetype1 == PathNodeType.DAMAGE_CACTUS)
/*     */       {
/* 419 */         pathnodetype = PathNodeType.DAMAGE_CACTUS;
/*     */       }
/*     */     } 
/*     */     
/* 423 */     BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.func_185346_s();
/*     */     
/* 425 */     if (pathnodetype == PathNodeType.WALKABLE)
/*     */     {
/* 427 */       for (int j = -1; j <= 1; j++) {
/*     */         
/* 429 */         for (int i = -1; i <= 1; i++) {
/*     */           
/* 431 */           if (j != 0 || i != 0) {
/*     */             
/* 433 */             Block block1 = blockaccessIn.func_180495_p(blockpos$pooledmutableblockpos.func_181079_c(j + x, y, i + z)).func_177230_c();
/*     */             
/* 435 */             if (block1 == Blocks.field_150434_aF) {
/*     */               
/* 437 */               pathnodetype = PathNodeType.DANGER_CACTUS;
/*     */             }
/* 439 */             else if (block1 == Blocks.field_150480_ab) {
/*     */               
/* 441 */               pathnodetype = PathNodeType.DANGER_FIRE;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 448 */     blockpos$pooledmutableblockpos.func_185344_t();
/* 449 */     return pathnodetype;
/*     */   }
/*     */ 
/*     */   
/*     */   private PathNodeType getPathNodeTypeRaw(IBlockAccess p_189553_1_, int p_189553_2_, int p_189553_3_, int p_189553_4_) {
/* 454 */     BlockPos blockpos = new BlockPos(p_189553_2_, p_189553_3_, p_189553_4_);
/* 455 */     IBlockState iblockstate = p_189553_1_.func_180495_p(blockpos);
/* 456 */     Block block = iblockstate.func_177230_c();
/* 457 */     Material material = iblockstate.func_185904_a();
/* 458 */     return (material == Material.field_151579_a) ? PathNodeType.OPEN : ((block != Blocks.field_150415_aT && block != Blocks.field_180400_cw && block != Blocks.field_150392_bi) ? ((block == Blocks.field_150480_ab) ? PathNodeType.DAMAGE_FIRE : ((block == Blocks.field_150434_aF) ? PathNodeType.DAMAGE_CACTUS : ((block instanceof BlockDoor && material == Material.field_151575_d && 
/*     */ 
/*     */       
/* 461 */       !((Boolean)iblockstate.func_177229_b(BlockDoor.field_176519_b)).booleanValue()) ? PathNodeType.DOOR_WOOD_CLOSED : ((block instanceof BlockDoor && material == Material.field_151573_f && 
/* 462 */       !((Boolean)iblockstate.func_177229_b(BlockDoor.field_176519_b)).booleanValue()) ? PathNodeType.DOOR_IRON_CLOSED : ((block instanceof BlockDoor && ((Boolean)iblockstate
/* 463 */       .func_177229_b(BlockDoor.field_176519_b)).booleanValue()) ? PathNodeType.DOOR_OPEN : ((block instanceof net.minecraft.block.BlockRailBase) ? PathNodeType.RAIL : ((!(block instanceof net.minecraft.block.BlockFence) && !(block instanceof net.minecraft.block.BlockWall) && (!(block instanceof BlockFenceGate) || ((Boolean)iblockstate
/*     */ 
/*     */       
/* 466 */       .func_177229_b(BlockFenceGate.field_176466_a)).booleanValue())) ? ((material == Material.field_151586_h) ? PathNodeType.WATER : ((material == Material.field_151587_i) ? PathNodeType.LAVA : (
/* 467 */       block.func_176205_b(p_189553_1_, blockpos) ? PathNodeType.OPEN : PathNodeType.BLOCKED))) : PathNodeType.FENCE))))))) : PathNodeType.TRAPDOOR);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\GolemNodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */