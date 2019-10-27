/*    */ package thaumcraft.common.blocks.essentia;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockFacing;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ 
/*    */ public class BlockEssentiaTransport
/*    */   extends BlockTCDevice implements IBlockFacing {
/*    */   public BlockEssentiaTransport(Class te, String name) {
/* 21 */     super(Material.field_151573_f, te, name);
/* 22 */     func_149672_a(SoundType.field_185852_e);
/* 23 */     func_149711_c(1.0F);
/* 24 */     func_149752_b(10.0F);
/* 25 */     IBlockState bs = this.field_176227_L.func_177621_b();
/* 26 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/* 27 */     func_180632_j(bs);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 61 */     bs = func_176223_P();
/* 62 */     return bs.func_177226_a(IBlockFacing.FACING, facing);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 68 */     EnumFacing facing = BlockStateUtils.getFacing(state);
/* 69 */     switch (facing.ordinal()) { default:
/* 70 */         return new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 71 */       case 1: return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
/* 72 */       case 2: return new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
/* 73 */       case 3: return new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
/* 74 */       case 4: return new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
/* 75 */       case 5: break; }  return new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockEssentiaTransport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */