/*    */ package thaumcraft.common.world.objects;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCustomFlowers
/*    */   extends WorldGenerator
/*    */ {
/*    */   private Block plantBlock;
/*    */   private int plantBlockMeta;
/*    */   
/*    */   public WorldGenCustomFlowers(Block bi, int md) {
/* 19 */     this.plantBlock = bi;
/* 20 */     this.plantBlockMeta = md;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180709_b(World world, Random par2Random, BlockPos pos) {
/* 26 */     for (int var6 = 0; var6 < 18; var6++) {
/*    */       
/* 28 */       int var7 = pos.func_177958_n() + par2Random.nextInt(8) - par2Random.nextInt(8);
/* 29 */       int var8 = pos.func_177956_o() + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 30 */       int var9 = pos.func_177952_p() + par2Random.nextInt(8) - par2Random.nextInt(8);
/* 31 */       BlockPos bp = new BlockPos(var7, var8, var9);
/* 32 */       if (world.func_175623_d(bp) && (world
/* 33 */         .func_180495_p(bp.func_177977_b()).func_177230_c() == Blocks.field_150349_c || world
/* 34 */         .func_180495_p(bp.func_177977_b()).func_177230_c() == Blocks.field_150354_m))
/*    */       {
/* 36 */         world.func_180501_a(bp, this.plantBlock.func_176203_a(this.plantBlockMeta), 3);
/*    */       }
/*    */     } 
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\objects\WorldGenCustomFlowers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */