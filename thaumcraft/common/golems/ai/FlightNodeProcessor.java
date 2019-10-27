/*    */ package thaumcraft.common.golems.ai;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.pathfinding.NodeProcessor;
/*    */ import net.minecraft.pathfinding.PathNodeType;
/*    */ import net.minecraft.pathfinding.PathPoint;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlightNodeProcessor
/*    */   extends NodeProcessor
/*    */ {
/* 18 */   public PathPoint func_186325_a(double x, double y, double z) { return func_176159_a(MathHelper.func_76128_c(x - (this.field_186326_b.field_70130_N / 2.0F)), MathHelper.func_76128_c(y + 0.5D), MathHelper.func_76128_c(z - (this.field_186326_b.field_70130_N / 2.0F))); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public PathPoint func_186318_b() { return func_176159_a(MathHelper.func_76128_c((this.field_186326_b.func_174813_aQ()).field_72340_a), MathHelper.func_76128_c((this.field_186326_b.func_174813_aQ()).field_72338_b + 0.5D), MathHelper.func_76128_c((this.field_186326_b.func_174813_aQ()).field_72339_c)); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_186320_a(PathPoint[] p_186320_1_, PathPoint p_186320_2_, PathPoint p_186320_3_, float p_186320_4_) {
/* 31 */     int i = 0;
/*    */     
/* 33 */     for (EnumFacing enumfacing : EnumFacing.values()) {
/*    */       
/* 35 */       PathPoint pathpoint = func_186328_b(p_186320_2_.field_75839_a + enumfacing.func_82601_c(), p_186320_2_.field_75837_b + enumfacing.func_96559_d(), p_186320_2_.field_75838_c + enumfacing.func_82599_e());
/*    */       
/* 37 */       if (pathpoint != null && !pathpoint.field_75842_i && pathpoint.func_75829_a(p_186320_3_) < p_186320_4_)
/*    */       {
/* 39 */         p_186320_1_[i++] = pathpoint;
/*    */       }
/*    */     } 
/*    */     
/* 43 */     return i;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public PathNodeType func_186319_a(IBlockAccess p_186319_1_, int p_186319_2_, int p_186319_3_, int p_186319_4_, EntityLiving p_186319_5_, int p_186319_6_, int p_186319_7_, int p_186319_8_, boolean p_186319_9_, boolean p_186319_10_) { return PathNodeType.WATER; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public PathNodeType func_186330_a(IBlockAccess x, int y, int z, int p_186330_4_) { return PathNodeType.WATER; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private PathPoint func_186328_b(int p_186328_1_, int p_186328_2_, int p_186328_3_) {
/* 60 */     PathNodeType pathnodetype = func_186327_c(p_186328_1_, p_186328_2_, p_186328_3_);
/* 61 */     return (pathnodetype == PathNodeType.WALKABLE) ? func_176159_a(p_186328_1_, p_186328_2_, p_186328_3_) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   private PathNodeType func_186327_c(int p_186327_1_, int p_186327_2_, int p_186327_3_) {
/* 66 */     BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
/*    */     
/* 68 */     for (int i = p_186327_1_; i < p_186327_1_ + this.field_176168_c; i++) {
/*    */       
/* 70 */       for (int j = p_186327_2_; j < p_186327_2_ + this.field_176165_d; j++) {
/*    */         
/* 72 */         for (int k = p_186327_3_; k < p_186327_3_ + this.field_176166_e; k++) {
/*    */           
/* 74 */           IBlockState iblockstate = this.field_176169_a.func_180495_p(blockpos$mutableblockpos.func_181079_c(i, j, k));
/*    */           
/* 76 */           if (!this.field_176169_a.func_175623_d(blockpos$mutableblockpos.func_181079_c(i, j, k)) && 
/* 77 */             !iblockstate.func_177230_c().func_176205_b(this.field_176169_a, blockpos$mutableblockpos.func_181079_c(i, j, k)))
/*    */           {
/* 79 */             return PathNodeType.BLOCKED;
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 85 */     return PathNodeType.WALKABLE;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\FlightNodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */