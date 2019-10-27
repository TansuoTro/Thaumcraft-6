/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*     */ 
/*     */ public class BlockArcaneEar
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacing, IBlockEnabled
/*     */ {
/*     */   public BlockArcaneEar(String name) {
/*  33 */     super(Material.field_151575_d, TileArcaneEar.class, name);
/*  34 */     func_149672_a(SoundType.field_185848_a);
/*  35 */     func_149711_c(1.0F);
/*  36 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  37 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  38 */     bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  39 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  68 */     bs = func_176223_P();
/*  69 */     bs = bs.func_177226_a(IBlockFacing.FACING, facing);
/*  70 */     return bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
/*  76 */     TileArcaneEar tile = (TileArcaneEar)worldIn.func_175625_s(pos);
/*  77 */     if (tile != null)
/*     */     {
/*  79 */       tile.updateTone();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/*  85 */     TileArcaneEar tile = (TileArcaneEar)worldIn.func_175625_s(pos);
/*  86 */     if (tile != null)
/*     */     {
/*  88 */       tile.updateTone();
/*     */     }
/*     */     
/*  91 */     if (!worldIn.func_180495_p(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d())).isSideSolid(worldIn, pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d()), BlockStateUtils.getFacing(state))) {
/*  92 */       func_176226_b(worldIn, pos, func_176223_P(), 0);
/*  93 */       worldIn.func_175698_g(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 101 */     if (world.field_72995_K) return true; 
/* 102 */     TileArcaneEar tile = (TileArcaneEar)world.func_175625_s(pos);
/* 103 */     if (tile != null) {
/*     */       
/* 105 */       tile.changePitch();
/* 106 */       tile.triggerNote(world, pos, true);
/*     */     } 
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/* 112 */   public boolean func_149744_f(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public int func_180656_a(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) { return BlockStateUtils.isEnabled(state.func_177230_c().func_176201_c(state)) ? 15 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public int func_176211_b(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) { return BlockStateUtils.isEnabled(state.func_177230_c().func_176201_c(state)) ? 15 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) { return worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).isSideSolid(worldIn, pos.func_177972_a(side.func_176734_d()), side); }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 132 */     EnumFacing facing = BlockStateUtils.getFacing(func_176201_c(state));
/* 133 */     switch (facing.ordinal()) { case 0:
/* 134 */         return new AxisAlignedBB(0.125D, 0.625D, 0.125D, 0.875D, 1.0D, 0.875D);
/* 135 */       case 1: return new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.375D, 0.875D);
/* 136 */       case 2: return new AxisAlignedBB(0.125D, 0.125D, 0.625D, 0.875D, 0.875D, 1.0D);
/* 137 */       case 3: return new AxisAlignedBB(0.125D, 0.125D, 0.0D, 0.875D, 0.875D, 0.375D);
/* 138 */       case 4: return new AxisAlignedBB(0.625D, 0.125D, 0.125D, 1.0D, 0.875D, 0.875D); }
/* 139 */      return new AxisAlignedBB(0.0D, 0.125D, 0.125D, 0.375D, 0.875D, 0.875D);
/*     */   }
/*     */ 
/*     */   
/* 143 */   private static final List<SoundEvent> INSTRUMENTS = Lists.newArrayList(new SoundEvent[] { SoundEvents.field_187682_dG, SoundEvents.field_187676_dE, SoundEvents.field_187688_dI, SoundEvents.field_187685_dH, SoundEvents.field_187679_dF, SoundEvents.field_193809_ey, SoundEvents.field_193807_ew, SoundEvents.field_193810_ez, SoundEvents.field_193808_ex, SoundEvents.field_193785_eE });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getInstrument(int type) {
/* 149 */     if (type < 0 || type >= INSTRUMENTS.size())
/*     */     {
/* 151 */       type = 0;
/*     */     }
/*     */     
/* 154 */     return (SoundEvent)INSTRUMENTS.get(type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_189539_a(IBlockState state, World worldIn, BlockPos pos, int par5, int par6) {
/* 160 */     super.func_189539_a(state, worldIn, pos, par5, par6);
/*     */     
/* 162 */     float var7 = (float)Math.pow(2.0D, (par6 - 12) / 12.0D);
/* 163 */     worldIn.func_184133_a((EntityPlayer)null, pos, getInstrument(par5), SoundCategory.BLOCKS, 3.0F, var7);
/* 164 */     worldIn.func_175688_a(EnumParticleTypes.NOTE, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, par6 / 24.0D, 0.0D, 0.0D, new int[0]);
/* 165 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockArcaneEar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */