/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class BlockBrainBox
/*     */   extends BlockTC
/*     */   implements IBlockFacingHorizontal, IBlockEnabled {
/*     */   public BlockBrainBox() {
/*  28 */     super(Material.field_151573_f, "brain_box");
/*  29 */     func_149672_a(SoundType.field_185852_e);
/*  30 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  31 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  32 */     func_180632_j(bs);
/*  33 */     func_149711_c(1.0F);
/*  34 */     func_149752_b(10.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/*  66 */     if (worldIn.func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state))).func_177230_c() != BlocksTC.thaumatorium && worldIn
/*  67 */       .func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state))).func_177230_c() != BlocksTC.thaumatoriumTop) {
/*  68 */       func_176226_b(worldIn, pos, BlocksTC.brainBox.func_176223_P(), 0);
/*  69 */       worldIn.func_175698_g(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) {
/*  75 */     if (worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c() != BlocksTC.thaumatorium && worldIn
/*  76 */       .func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c() != BlocksTC.thaumatoriumTop) return false; 
/*  77 */     if (worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177229_b(FACING) == side) return false; 
/*  78 */     return super.func_176198_a(worldIn, pos, side);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  84 */     bs = func_176223_P();
/*  85 */     return bs.func_177226_a(IBlockFacing.FACING, facing.func_176734_d());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/*  92 */     bs = func_176223_P();
/*  93 */     return bs.func_177226_a(IBlockFacing.FACING, BlockStateUtils.getFacing(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 100 */     byte b0 = 0;
/* 101 */     return b0 | ((EnumFacing)state.func_177229_b(IBlockFacing.FACING)).func_176745_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { IBlockFacing.FACING }); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockBrainBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */