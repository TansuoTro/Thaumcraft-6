/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockEnabled;
/*    */ 
/*    */ public class BlockCondenser
/*    */   extends BlockTCDevice
/*    */   implements IBlockEnabled
/*    */ {
/*    */   public BlockCondenser() {
/* 18 */     super(Material.field_151573_f, thaumcraft.common.tiles.devices.TileCondenser.class, "condenser");
/* 19 */     func_149672_a(SoundType.field_185852_e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockCondenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */