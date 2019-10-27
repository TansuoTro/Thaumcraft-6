/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileLampArcane;
/*     */ import thaumcraft.common.tiles.devices.TileLampFertility;
/*     */ import thaumcraft.common.tiles.devices.TileLampGrowth;
/*     */ 
/*     */ public class BlockLamp
/*     */   extends BlockTCDevice implements IBlockFacing, IBlockEnabled {
/*     */   public BlockLamp(Class tc, String name) {
/*  26 */     super(Material.field_151573_f, tc, name);
/*  27 */     func_149672_a(SoundType.field_185852_e);
/*  28 */     func_149711_c(1.0F);
/*  29 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  30 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.DOWN);
/*  31 */     bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true));
/*  32 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return BlockStateUtils.isEnabled(world.func_180495_p(pos).func_177230_c().func_176201_c(world.func_180495_p(pos))) ? 15 : super.getLightValue(state, world, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  66 */     bs = func_176223_P();
/*  67 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d());
/*  68 */     return bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/*  74 */     TileEntity te = worldIn.func_175625_s(pos);
/*  75 */     if (te != null && te instanceof TileLampArcane) {
/*  76 */       ((TileLampArcane)te).removeLights();
/*     */     }
/*  78 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/*  84 */     if (worldIn.func_175623_d(pos.func_177972_a(BlockStateUtils.getFacing(state)))) {
/*  85 */       func_176226_b(worldIn, pos, func_176223_P(), 0);
/*  86 */       worldIn.func_175698_g(pos);
/*     */       
/*     */       return;
/*     */     } 
/*  90 */     TileEntity te = worldIn.func_175625_s(pos);
/*  91 */     if (te != null && te instanceof TileLampArcane && 
/*  92 */       BlockStateUtils.isEnabled(state) && worldIn.func_175640_z(pos)) {
/*  93 */       ((TileLampArcane)te).removeLights();
/*     */     }
/*     */ 
/*     */     
/*  97 */     boolean checkUpdate = true;
/*  98 */     if (te != null && te instanceof TileLampGrowth && ((TileLampGrowth)te).charges <= 0) checkUpdate = false; 
/*  99 */     if (te != null && te instanceof TileLampFertility && ((TileLampFertility)te).charges <= 0) checkUpdate = false;
/*     */     
/* 101 */     if (checkUpdate) super.func_189540_a(state, worldIn, pos, blockIn, pos2);
/*     */   
/*     */   }
/*     */ 
/*     */   
/* 106 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.25D, 0.125D, 0.25D, 0.75D, 0.875D, 0.75D); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */