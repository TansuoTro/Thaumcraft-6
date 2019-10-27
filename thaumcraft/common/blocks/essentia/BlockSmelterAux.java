/*     */ package thaumcraft.common.blocks.essentia;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class BlockSmelterAux
/*     */   extends BlockTC
/*     */   implements IBlockFacingHorizontal {
/*     */   public BlockSmelterAux() {
/*  24 */     super(Material.field_151573_f, "smelter_aux");
/*  25 */     func_149672_a(SoundType.field_185852_e);
/*  26 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH));
/*  27 */     func_149711_c(1.0F);
/*  28 */     func_149752_b(10.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) { return false; }
/*     */ 
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
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */ 
/*     */   
/*     */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) {
/*  58 */     return (super.func_176198_a(worldIn, pos, side) && side.func_176740_k().func_176722_c() && worldIn
/*  59 */       .func_180495_p(pos.func_177972_a(side.func_176734_d())).func_177230_c() instanceof BlockSmelter && 
/*  60 */       BlockStateUtils.getFacing(worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d()))) != side);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  66 */     bs = func_176223_P();
/*  67 */     if (!facing.func_176740_k().func_176722_c()) facing = EnumFacing.NORTH; 
/*  68 */     return bs.func_177226_a(IBlockFacingHorizontal.FACING, facing.func_176734_d());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/*  75 */     bs = func_176223_P();
/*  76 */     return bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.func_176731_b(BlockStateUtils.getFacing(meta).func_176736_b()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public int func_176201_c(IBlockState state) { return false | ((EnumFacing)state.func_177229_b(IBlockFacingHorizontal.FACING)).func_176745_a(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockStateContainer func_180661_e() {
/*  89 */     ArrayList<IProperty> ip = new ArrayList<IProperty>();
/*  90 */     ip.add(IBlockFacingHorizontal.FACING);
/*  91 */     return (ip.size() == 0) ? super.func_180661_e() : new BlockStateContainer(this, (IProperty[])ip.toArray(new IProperty[ip.size()]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockSmelterAux.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */