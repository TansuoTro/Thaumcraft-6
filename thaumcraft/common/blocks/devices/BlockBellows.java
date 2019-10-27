/*    */ package thaumcraft.common.blocks.devices;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.EnumBlockRenderType;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockEnabled;
/*    */ import thaumcraft.common.blocks.IBlockFacing;
/*    */ 
/*    */ public class BlockBellows
/*    */   extends BlockTCDevice
/*    */   implements IBlockFacing, IBlockEnabled {
/*    */   public BlockBellows() {
/* 21 */     super(Material.field_151575_d, thaumcraft.common.tiles.devices.TileBellows.class, "bellows");
/* 22 */     func_149672_a(SoundType.field_185848_a);
/* 23 */     func_149711_c(1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 57 */     IBlockState bs = func_176223_P();
/* 58 */     if (this instanceof IBlockFacing) bs = bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d()); 
/* 59 */     if (this instanceof IBlockEnabled) bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)); 
/* 60 */     return bs;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */