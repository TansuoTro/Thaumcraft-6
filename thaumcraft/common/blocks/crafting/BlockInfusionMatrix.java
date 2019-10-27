/*    */ package thaumcraft.common.blocks.crafting;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.EnumBlockRenderType;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ 
/*    */ public class BlockInfusionMatrix
/*    */   extends BlockTCDevice
/*    */ {
/*    */   public BlockInfusionMatrix() {
/* 17 */     super(Material.field_151576_e, thaumcraft.common.tiles.crafting.TileInfusionMatrix.class, "infusion_matrix");
/* 18 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockInfusionMatrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */