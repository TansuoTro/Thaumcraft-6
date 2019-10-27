/*     */ package thaumcraft.common.blocks.essentia;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.essentia.TileSmelter;
/*     */ 
/*     */ public class BlockSmelter
/*     */   extends BlockTCDevice
/*     */   implements IBlockEnabled, IBlockFacingHorizontal
/*     */ {
/*     */   public BlockSmelter(String name) {
/*  34 */     super(Material.field_151573_f, TileSmelter.class, name);
/*  35 */     func_149672_a(SoundType.field_185852_e);
/*     */     
/*  37 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  38 */     bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH);
/*  39 */     bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*  40 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  50 */     bs = func_176223_P();
/*  51 */     bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO().func_176734_d());
/*  52 */     return bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/*  60 */     TileEntity te = worldIn.func_175625_s(pos);
/*  61 */     if (te != null && te instanceof TileSmelter) {
/*  62 */       ((TileSmelter)te).checkNeighbours();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  70 */     if (!world.field_72995_K && !player.func_70093_af()) {
/*  71 */       player.openGui(Thaumcraft.instance, 9, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */     }
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return BlockStateUtils.isEnabled(world.func_180495_p(pos).func_177230_c().func_176201_c(world.func_180495_p(pos))) ? 13 : super.getLightValue(state, world, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public boolean func_149740_M(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
/*  94 */     TileEntity te = world.func_175625_s(pos);
/*  95 */     if (te != null && te instanceof IInventory) {
/*  96 */       return Container.func_94526_b((IInventory)te);
/*     */     }
/*  98 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setFurnaceState(World world, BlockPos pos, boolean state) {
/* 102 */     if (state == BlockStateUtils.isEnabled(world.func_180495_p(pos).func_177230_c().func_176201_c(world.func_180495_p(pos))))
/* 103 */       return;  TileEntity tileentity = world.func_175625_s(pos);
/* 104 */     keepInventory = true;
/* 105 */     world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(state)), 3);
/* 106 */     world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(state)), 3);
/* 107 */     if (tileentity != null) {
/*     */       
/* 109 */       tileentity.func_145829_t();
/* 110 */       world.func_175690_a(pos, tileentity);
/*     */     } 
/* 112 */     keepInventory = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 118 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/*     */     
/* 120 */     if (tileentity instanceof TileSmelter && !worldIn.field_72995_K && ((TileSmelter)tileentity).vis > 0) {
/*     */       
/* 122 */       int ess = ((TileSmelter)tileentity).vis;
/* 123 */       AuraHelper.polluteAura(worldIn, pos, ess, true);
/*     */     } 
/*     */     
/* 126 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState state, World w, BlockPos pos, Random r) {
/* 132 */     if (BlockStateUtils.isEnabled(state)) {
/* 133 */       float f = pos.func_177958_n() + 0.5F;
/* 134 */       float f1 = pos.func_177956_o() + 0.2F + r.nextFloat() * 5.0F / 16.0F;
/* 135 */       float f2 = pos.func_177952_p() + 0.5F;
/* 136 */       float f3 = 0.52F;
/* 137 */       float f4 = r.nextFloat() * 0.5F - 0.25F;
/*     */       
/* 139 */       if (BlockStateUtils.getFacing(state) == EnumFacing.WEST) {
/* 140 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, (f - f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D, new int[0]);
/* 141 */         w.func_175688_a(EnumParticleTypes.FLAME, (f - f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */       
/* 144 */       if (BlockStateUtils.getFacing(state) == EnumFacing.EAST) {
/* 145 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, (f + f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D, new int[0]);
/* 146 */         w.func_175688_a(EnumParticleTypes.FLAME, (f + f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */       
/* 149 */       if (BlockStateUtils.getFacing(state) == EnumFacing.NORTH) {
/* 150 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, (f + f4), f1, (f2 - f3), 0.0D, 0.0D, 0.0D, new int[0]);
/* 151 */         w.func_175688_a(EnumParticleTypes.FLAME, (f + f4), f1, (f2 - f3), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */       
/* 154 */       if (BlockStateUtils.getFacing(state) == EnumFacing.SOUTH) {
/* 155 */         w.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, (f + f4), f1, (f2 + f3), 0.0D, 0.0D, 0.0D, new int[0]);
/* 156 */         w.func_175688_a(EnumParticleTypes.FLAME, (f + f4), f1, (f2 + f3), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockSmelter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */