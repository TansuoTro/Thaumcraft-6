/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.EnumPushReaction;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockRenderLayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ public class BlockTranslucent
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockTranslucent(String name) {
/* 21 */     super(Material.field_151592_s, name);
/* 22 */     func_149711_c(0.5F);
/* 23 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public EnumPushReaction func_149656_h(IBlockState state) { return EnumPushReaction.NORMAL; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
/* 45 */     IBlockState iblockstate = blockAccess.func_180495_p(pos.func_177972_a(side));
/* 46 */     Block block = iblockstate.func_177230_c();
/* 47 */     return (block == this) ? false : super.func_176225_a(blockState, blockAccess, pos, side);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 54 */   public BlockRenderLayer func_180664_k() { return BlockRenderLayer.TRANSLUCENT; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public boolean func_149662_c(IBlockState iblockstate) { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockTranslucent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */