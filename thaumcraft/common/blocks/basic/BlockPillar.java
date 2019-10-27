/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.EnumPushReaction;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ public class BlockPillar
/*     */   extends BlockTC
/*     */ {
/*  27 */   public static final PropertyDirection FACING = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
/*     */   
/*  29 */   private final Random rand = new Random();
/*     */   
/*     */   public BlockPillar(String name) {
/*  32 */     super(Material.field_151576_e, name);
/*  33 */     func_149711_c(2.5F);
/*  34 */     func_149672_a(SoundType.field_185851_d);
/*  35 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  36 */     bs.func_177226_a(FACING, EnumFacing.NORTH);
/*  37 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  42 */   public EnumPushReaction func_149656_h(IBlockState state) { return EnumPushReaction.BLOCK; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  76 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  77 */     bs.func_177226_a(FACING, placer.func_174811_aO());
/*  78 */     return bs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/*  85 */     EnumFacing enumfacing = EnumFacing.func_176731_b(MathHelper.func_76128_c((placer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3).func_176734_d();
/*  86 */     state = state.func_177226_a(FACING, enumfacing);
/*  87 */     worldIn.func_180501_a(pos, state, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/*  99 */     if (state.func_177230_c() == BlocksTC.pillarArcane) func_180635_a(worldIn, pos, new ItemStack(BlocksTC.stoneArcane, 2)); 
/* 100 */     if (state.func_177230_c() == BlocksTC.pillarAncient) func_180635_a(worldIn, pos, new ItemStack(BlocksTC.stoneAncient, 2)); 
/* 101 */     if (state.func_177230_c() == BlocksTC.pillarEldritch) func_180635_a(worldIn, pos, new ItemStack(BlocksTC.stoneEldritchTile, 2)); 
/* 102 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/* 108 */     EnumFacing enumfacing = EnumFacing.func_176731_b(meta);
/* 109 */     return func_176194_O().func_177621_b().func_177226_a(FACING, enumfacing);
/*     */   }
/*     */   
/*     */   public static int calcMeta(EnumFacing enumfacing) {
/* 113 */     if (enumfacing.func_176740_k() == EnumFacing.Axis.Y)
/*     */     {
/* 115 */       enumfacing = EnumFacing.NORTH;
/*     */     }
/* 117 */     IBlockState state = BlocksTC.pillarArcane.func_176194_O().func_177621_b();
/* 118 */     return BlocksTC.pillarArcane.func_176201_c(state.func_177226_a(FACING, enumfacing));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public int func_176201_c(IBlockState state) { return ((EnumFacing)state.func_177229_b(FACING)).func_176736_b(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { FACING }); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockPillar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */