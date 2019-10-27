/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTaintFeature
/*     */   extends BlockTC
/*     */   implements ITaintBlock
/*     */ {
/*     */   public BlockTaintFeature() {
/*  34 */     super(ThaumcraftMaterials.MATERIAL_TAINT, "taint_feature");
/*  35 */     func_149711_c(0.1F);
/*  36 */     func_149715_a(0.625F);
/*  37 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  38 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  39 */     func_180632_j(bs);
/*  40 */     func_149675_a(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  45 */   protected boolean func_149700_E() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/*  51 */     if (!worldIn.field_72995_K)
/*     */     {
/*  53 */       if (worldIn.field_73012_v.nextFloat() < 0.333F) {
/*  54 */         EntityTaintCrawler entityTaintCrawler = new EntityTaintCrawler(worldIn);
/*  55 */         entityTaintCrawler.func_70012_b((pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F), worldIn.field_73012_v.nextInt(360), 0.0F);
/*  56 */         worldIn.func_72838_d(entityTaintCrawler);
/*     */       } else {
/*  58 */         AuraHelper.polluteAura(worldIn, pos, 1.0F, true);
/*     */       } 
/*     */     }
/*  61 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void die(World world, BlockPos pos, IBlockState blockState) { world.func_175656_a(pos, BlocksTC.fluxGoo.func_176223_P()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random) {
/*  78 */     if (!world.field_72995_K) {
/*     */ 
/*     */       
/*  81 */       if (!TaintHelper.isNearTaintSeed(world, pos) && random.nextInt(10) == 0) {
/*  82 */         die(world, pos, state);
/*     */         return;
/*     */       } 
/*  85 */       TaintHelper.spreadFibres(world, pos);
/*     */       
/*  87 */       if (world.func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.taintLog && world
/*  88 */         .func_180495_p(pos.func_177977_b()).func_177229_b(BlockTaintLog.AXIS) == EnumFacing.Axis.Y && world.field_73012_v
/*  89 */         .nextInt(100) == 0) {
/*  90 */         world.func_175656_a(pos, BlocksTC.taintGeyser.func_176223_P());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public int func_185484_c(IBlockState state, IBlockAccess source, BlockPos pos) { return 200; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/* 118 */     if (!worldIn.field_72995_K && 
/* 119 */       !worldIn.func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d())).isSideSolid(worldIn, pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d()), BlockStateUtils.getFacing(state))) {
/* 120 */       worldIn.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 139 */     bs = func_176223_P();
/* 140 */     return bs.func_177226_a(IBlockFacing.FACING, facing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/* 148 */     bs = func_176223_P();
/* 149 */     return bs.func_177226_a(IBlockFacing.FACING, BlockStateUtils.getFacing(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 156 */     byte b0 = 0;
/* 157 */     return b0 | ((EnumFacing)state.func_177229_b(IBlockFacing.FACING)).func_176745_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockStateContainer func_180661_e() {
/* 164 */     ArrayList<IProperty> ip = new ArrayList<IProperty>();
/* 165 */     ip.add(IBlockFacing.FACING);
/* 166 */     return new BlockStateContainer(this, (IProperty[])ip.toArray(new IProperty[ip.size()]));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 171 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(state));
/* 172 */     switch (facing.ordinal()) { case 0:
/* 173 */         return new AxisAlignedBB(0.125D, 0.625D, 0.125D, 0.875D, 1.0D, 0.875D);
/* 174 */       case 1: return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.375D, 0.875D);
/* 175 */       case 2: return new AxisAlignedBB(0.125D, 0.125D, 0.625D, 0.875D, 0.875D, 1.0D);
/* 176 */       case 3: return new AxisAlignedBB(0.125D, 0.125D, 0.0D, 0.875D, 0.875D, 0.375D);
/* 177 */       case 4: return new AxisAlignedBB(0.625D, 0.125D, 0.125D, 1.0D, 0.875D, 0.875D);
/* 178 */       case 5: return new AxisAlignedBB(0.0D, 0.125D, 0.125D, 0.375D, 0.875D, 0.875D); }
/*     */     
/* 180 */     return super.func_185496_a(state, source, pos);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\taint\BlockTaintFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */