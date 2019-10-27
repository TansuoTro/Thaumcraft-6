/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class BlockStairsTC
/*    */   extends BlockStairs
/*    */ {
/*    */   public BlockStairsTC(String name, IBlockState modelState) {
/* 15 */     super(modelState);
/* 16 */     func_149663_c(name);
/* 17 */     setRegistryName("thaumcraft", name);
/* 18 */     func_149647_a(ConfigItems.TABTC);
/* 19 */     func_149713_g(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 24 */     if (func_149688_o(func_176223_P()) == Material.field_151575_d) return 20; 
/* 25 */     return super.getFlammability(world, pos, face);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
/* 30 */     if (func_149688_o(func_176223_P()) == Material.field_151575_d) return 5; 
/* 31 */     return super.getFireSpreadSpeed(world, pos, face);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockStairsTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */