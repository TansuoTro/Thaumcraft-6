/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliserExt;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ public class BlockCandle
/*     */   extends BlockTC
/*     */   implements IInfusionStabiliserExt
/*     */ {
/*     */   public final EnumDyeColor dye;
/*     */   
/*     */   public BlockCandle(String name, EnumDyeColor dye) {
/*  27 */     super(Material.field_151594_q, name);
/*  28 */     func_149711_c(0.1F);
/*  29 */     func_149672_a(SoundType.field_185854_g);
/*  30 */     func_149715_a(0.9375F);
/*  31 */     this.dye = dye;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public MapColor func_180659_g(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return MapColor.func_193558_a(this.dye); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public boolean func_176196_c(World par1World, BlockPos pos) { return par1World.isSideSolid(pos, EnumFacing.UP); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/*  53 */     if (!func_176196_c(worldIn, pos.func_177977_b())) {
/*     */       
/*  55 */       func_176226_b(worldIn, pos, state, 0);
/*  56 */       worldIn.func_175698_g(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public boolean func_176198_a(World par1World, BlockPos pos, EnumFacing par5) { return func_176196_c(par1World, pos.func_177977_b()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180655_c(IBlockState state, World par1World, BlockPos pos, Random par5Random) {
/*  96 */     double var7 = (pos.func_177958_n() + 0.5F);
/*  97 */     double var9 = (pos.func_177956_o() + 0.7F);
/*  98 */     double var11 = (pos.func_177952_p() + 0.5F);
/*     */     
/* 100 */     par1World.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, var7, var9, var11, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     
/* 102 */     par1World.func_175688_a(EnumParticleTypes.FLAME, var7, var9, var11, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public boolean canStabaliseInfusion(World world, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public float getStabilizationAmount(World world, BlockPos pos) { return 0.1F; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public boolean hasSymmetryPenalty(World world, BlockPos pos1, BlockPos pos2) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public float getSymmetryPenalty(World world, BlockPos pos) { return 0.0F; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockCandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */