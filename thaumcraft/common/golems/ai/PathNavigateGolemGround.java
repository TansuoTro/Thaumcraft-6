/*     */ package thaumcraft.common.golems.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.pathfinding.PathFinder;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.pathfinding.PathNodeType;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
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
/*     */ public class PathNavigateGolemGround
/*     */   extends PathNavigateGround
/*     */ {
/* 248 */   public PathNavigateGolemGround(EntityLiving entitylivingIn, World worldIn) { super(entitylivingIn, worldIn); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected PathFinder func_179679_a() {
/* 253 */     this.field_179695_a = new GolemNodeProcessor();
/* 254 */     this.field_179695_a.func_186317_a(true);
/* 255 */     return new PathFinder(this.field_179695_a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   protected boolean func_75485_k() { return (this.field_75515_a.field_70122_E || (func_179684_h() && func_75506_l()) || this.field_75515_a.func_184218_aH()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   protected Vec3d func_75502_i() { return new Vec3d(this.field_75515_a.field_70165_t, getPathablePosY(), this.field_75515_a.field_70161_v); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Path func_179680_a(BlockPos pos) {
/* 276 */     if (this.field_75513_b.func_180495_p(pos).func_185904_a() == Material.field_151579_a) {
/*     */       BlockPos blockpos;
/*     */ 
/*     */       
/* 280 */       for (blockpos = pos.func_177977_b(); blockpos.func_177956_o() > 0 && this.field_75513_b.func_180495_p(blockpos).func_185904_a() == Material.field_151579_a; blockpos = blockpos.func_177977_b());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 285 */       if (blockpos.func_177956_o() > 0)
/*     */       {
/* 287 */         return super.func_179680_a(blockpos.func_177984_a());
/*     */       }
/*     */       
/* 290 */       while (blockpos.func_177956_o() < this.field_75513_b.func_72800_K() && this.field_75513_b.func_180495_p(blockpos).func_185904_a() == Material.field_151579_a)
/*     */       {
/* 292 */         blockpos = blockpos.func_177984_a();
/*     */       }
/*     */       
/* 295 */       pos = blockpos;
/*     */     } 
/*     */     
/* 298 */     if (!this.field_75513_b.func_180495_p(pos).func_185904_a().func_76220_a())
/*     */     {
/* 300 */       return super.func_179680_a(pos);
/*     */     }
/*     */ 
/*     */     
/*     */     BlockPos blockpos1;
/*     */     
/* 306 */     for (blockpos1 = pos.func_177984_a(); blockpos1.func_177956_o() < this.field_75513_b.func_72800_K() && this.field_75513_b.func_180495_p(blockpos1).func_185904_a().func_76220_a(); blockpos1 = blockpos1.func_177984_a());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     return super.func_179680_a(blockpos1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Path func_75494_a(Entity entityIn) {
/* 320 */     BlockPos blockpos = new BlockPos(entityIn);
/* 321 */     return func_179680_a(blockpos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getPathablePosY() {
/* 329 */     if (this.field_75515_a.func_70090_H() && func_179684_h()) {
/*     */       
/* 331 */       int i = (int)(this.field_75515_a.func_174813_aQ()).field_72338_b;
/* 332 */       Block block = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
/* 333 */       int j = 0;
/*     */       
/* 335 */       while (block == Blocks.field_150358_i || block == Blocks.field_150355_j) {
/*     */         
/* 337 */         i++;
/* 338 */         block = this.field_75513_b.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v))).func_177230_c();
/* 339 */         j++;
/*     */         
/* 341 */         if (j > 16)
/*     */         {
/* 343 */           return (int)(this.field_75515_a.func_174813_aQ()).field_72338_b;
/*     */         }
/*     */       } 
/*     */       
/* 347 */       return i;
/*     */     } 
/*     */ 
/*     */     
/* 351 */     return (int)((this.field_75515_a.func_174813_aQ()).field_72338_b + 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_75487_m() {
/* 360 */     super.func_75487_m();
/*     */     
/* 362 */     for (int i = 0; i < this.field_75514_c.func_75874_d(); i++) {
/*     */       
/* 364 */       PathPoint pathpoint = this.field_75514_c.func_75877_a(i);
/* 365 */       PathPoint pathpoint1 = (i + 1 < this.field_75514_c.func_75874_d()) ? this.field_75514_c.func_75877_a(i + 1) : null;
/* 366 */       IBlockState iblockstate = this.field_75513_b.func_180495_p(new BlockPos(pathpoint.field_75839_a, pathpoint.field_75837_b, pathpoint.field_75838_c));
/* 367 */       Block block = iblockstate.func_177230_c();
/*     */       
/* 369 */       if (block == Blocks.field_150383_bp) {
/*     */         
/* 371 */         this.field_75514_c.func_186309_a(i, pathpoint.func_186283_a(pathpoint.field_75839_a, pathpoint.field_75837_b + 1, pathpoint.field_75838_c));
/*     */         
/* 373 */         if (pathpoint1 != null && pathpoint.field_75837_b >= pathpoint1.field_75837_b)
/*     */         {
/* 375 */           this.field_75514_c.func_186309_a(i + 1, pathpoint1.func_186283_a(pathpoint1.field_75839_a, pathpoint.field_75837_b + 1, pathpoint1.field_75838_c));
/*     */         }
/*     */       } 
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
/*     */   protected boolean func_75493_a(Vec3d posVec31, Vec3d posVec32, int sizeX, int sizeY, int sizeZ) {
/* 405 */     int i = MathHelper.func_76128_c(posVec31.field_72450_a);
/* 406 */     int j = MathHelper.func_76128_c(posVec31.field_72449_c);
/* 407 */     double d0 = posVec32.field_72450_a - posVec31.field_72450_a;
/* 408 */     double d1 = posVec32.field_72449_c - posVec31.field_72449_c;
/* 409 */     double d2 = d0 * d0 + d1 * d1;
/*     */     
/* 411 */     if (d2 < 1.0E-8D)
/*     */     {
/* 413 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 417 */     double d3 = 1.0D / Math.sqrt(d2);
/* 418 */     d0 *= d3;
/* 419 */     d1 *= d3;
/* 420 */     sizeX += 2;
/* 421 */     sizeZ += 2;
/*     */     
/* 423 */     if (!isSafeToStandAt(i, (int)posVec31.field_72448_b, j, sizeX, sizeY, sizeZ, posVec31, d0, d1))
/*     */     {
/* 425 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 429 */     sizeX -= 2;
/* 430 */     sizeZ -= 2;
/* 431 */     double d4 = 1.0D / Math.abs(d0);
/* 432 */     double d5 = 1.0D / Math.abs(d1);
/* 433 */     double d6 = i - posVec31.field_72450_a;
/* 434 */     double d7 = j - posVec31.field_72449_c;
/*     */     
/* 436 */     if (d0 >= 0.0D)
/*     */     {
/* 438 */       d6++;
/*     */     }
/*     */     
/* 441 */     if (d1 >= 0.0D)
/*     */     {
/* 443 */       d7++;
/*     */     }
/*     */     
/* 446 */     d6 /= d0;
/* 447 */     d7 /= d1;
/* 448 */     int k = (d0 < 0.0D) ? -1 : 1;
/* 449 */     int l = (d1 < 0.0D) ? -1 : 1;
/* 450 */     int i1 = MathHelper.func_76128_c(posVec32.field_72450_a);
/* 451 */     int j1 = MathHelper.func_76128_c(posVec32.field_72449_c);
/* 452 */     int k1 = i1 - i;
/* 453 */     int l1 = j1 - j;
/*     */     
/* 455 */     while (k1 * k > 0 || l1 * l > 0) {
/*     */       
/* 457 */       if (d6 < d7) {
/*     */         
/* 459 */         d6 += d4;
/* 460 */         i += k;
/* 461 */         k1 = i1 - i;
/*     */       }
/*     */       else {
/*     */         
/* 465 */         d7 += d5;
/* 466 */         j += l;
/* 467 */         l1 = j1 - j;
/*     */       } 
/*     */       
/* 470 */       if (!isSafeToStandAt(i, (int)posVec31.field_72448_b, j, sizeX, sizeY, sizeZ, posVec31, d0, d1))
/*     */       {
/* 472 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 476 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSafeToStandAt(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vec3d vec31, double p_179683_8_, double p_179683_10_) {
/* 486 */     int i = x - sizeX / 2;
/* 487 */     int j = z - sizeZ / 2;
/*     */     
/* 489 */     if (!isPositionClear(i, y, j, sizeX, sizeY, sizeZ, vec31, p_179683_8_, p_179683_10_))
/*     */     {
/* 491 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 495 */     for (int k = i; k < i + sizeX; k++) {
/*     */       
/* 497 */       for (int l = j; l < j + sizeZ; l++) {
/*     */         
/* 499 */         double d0 = k + 0.5D - vec31.field_72450_a;
/* 500 */         double d1 = l + 0.5D - vec31.field_72449_c;
/*     */         
/* 502 */         if (d0 * p_179683_8_ + d1 * p_179683_10_ >= 0.0D) {
/*     */           
/* 504 */           PathNodeType pathnodetype = this.field_179695_a.func_186319_a(this.field_75513_b, k, y - 1, l, this.field_75515_a, sizeX, sizeY, sizeZ, true, true);
/*     */           
/* 506 */           if (pathnodetype == PathNodeType.WATER)
/*     */           {
/* 508 */             return false;
/*     */           }
/*     */           
/* 511 */           if (pathnodetype == PathNodeType.LAVA)
/*     */           {
/* 513 */             return false;
/*     */           }
/*     */           
/* 516 */           if (pathnodetype == PathNodeType.OPEN)
/*     */           {
/* 518 */             return false;
/*     */           }
/*     */           
/* 521 */           pathnodetype = this.field_179695_a.func_186319_a(this.field_75513_b, k, y, l, this.field_75515_a, sizeX, sizeY, sizeZ, true, true);
/* 522 */           float f = this.field_75515_a.func_184643_a(pathnodetype);
/*     */           
/* 524 */           if (f < 0.0F || f >= 8.0F)
/*     */           {
/* 526 */             return false;
/*     */           }
/*     */           
/* 529 */           if (pathnodetype == PathNodeType.DAMAGE_FIRE || pathnodetype == PathNodeType.DANGER_FIRE || pathnodetype == PathNodeType.DAMAGE_OTHER)
/*     */           {
/* 531 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 537 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPositionClear(int p_179692_1_, int p_179692_2_, int p_179692_3_, int p_179692_4_, int p_179692_5_, int p_179692_6_, Vec3d p_179692_7_, double p_179692_8_, double p_179692_10_) {
/* 546 */     for (BlockPos blockpos : BlockPos.func_177980_a(new BlockPos(p_179692_1_, p_179692_2_, p_179692_3_), new BlockPos(p_179692_1_ + p_179692_4_ - 1, p_179692_2_ + p_179692_5_ - 1, p_179692_3_ + p_179692_6_ - 1))) {
/*     */       
/* 548 */       double d0 = blockpos.func_177958_n() + 0.5D - p_179692_7_.field_72450_a;
/* 549 */       double d1 = blockpos.func_177952_p() + 0.5D - p_179692_7_.field_72449_c;
/*     */       
/* 551 */       if (d0 * p_179692_8_ + d1 * p_179692_10_ >= 0.0D) {
/*     */         
/* 553 */         Block block = this.field_75513_b.func_180495_p(blockpos).func_177230_c();
/*     */         
/* 555 */         if (!block.func_176205_b(this.field_75513_b, blockpos))
/*     */         {
/* 557 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 562 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 572 */   public void func_179691_c(boolean enterDoors) { this.field_179695_a.func_186317_a(enterDoors); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 577 */   public boolean func_179686_g() { return this.field_179695_a.func_186323_c(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 582 */   public void func_179693_d(boolean canSwim) { this.field_179695_a.func_186316_c(canSwim); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 587 */   public boolean func_179684_h() { return this.field_179695_a.func_186322_e(); }
/*     */   
/*     */   public void func_179685_e(boolean avoidSun) {}
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\PathNavigateGolemGround.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */