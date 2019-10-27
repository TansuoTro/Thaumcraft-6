/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.crafting.IInfusionStabiliserExt;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.tiles.devices.TileStabilizer;
/*     */ 
/*     */ 
/*     */ public class BlockInlay
/*     */   extends BlockTC
/*     */   implements IInfusionStabiliserExt
/*     */ {
/*  44 */   public static final PropertyEnum<EnumAttachPosition> NORTH = PropertyEnum.func_177709_a("north", EnumAttachPosition.class);
/*  45 */   public static final PropertyEnum<EnumAttachPosition> EAST = PropertyEnum.func_177709_a("east", EnumAttachPosition.class);
/*  46 */   public static final PropertyEnum<EnumAttachPosition> SOUTH = PropertyEnum.func_177709_a("south", EnumAttachPosition.class);
/*  47 */   public static final PropertyEnum<EnumAttachPosition> WEST = PropertyEnum.func_177709_a("west", EnumAttachPosition.class);
/*  48 */   public static final PropertyInteger CHARGE = PropertyInteger.func_177719_a("charge", 0, 15);
/*     */   protected static final AxisAlignedBB[] REDSTONE_WIRE_AABB = { 
/*  50 */       new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.8125D, 0.0625D, 1.0D), new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.1875D, 1.0D, 0.0625D, 1.0D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.1875D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 0.8125D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockInlay() {
/*  69 */     super(Material.field_151573_f, "inlay");
/*  70 */     func_149672_a(SoundType.field_185852_e);
/*  71 */     func_149711_c(0.5F);
/*  72 */     func_180632_j(this.field_176227_L.func_177621_b()
/*  73 */         .func_177226_a(NORTH, EnumAttachPosition.NONE)
/*  74 */         .func_177226_a(EAST, EnumAttachPosition.NONE)
/*  75 */         .func_177226_a(SOUTH, EnumAttachPosition.NONE)
/*  76 */         .func_177226_a(WEST, EnumAttachPosition.NONE)
/*  77 */         .func_177226_a(CHARGE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return REDSTONE_WIRE_AABB[getAABBIndex(state.func_185899_b(source, pos))]; }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getAABBIndex(IBlockState state) {
/*  88 */     int i = 0;
/*  89 */     boolean flag = (state.func_177229_b(NORTH) != EnumAttachPosition.NONE);
/*  90 */     boolean flag1 = (state.func_177229_b(EAST) != EnumAttachPosition.NONE);
/*  91 */     boolean flag2 = (state.func_177229_b(SOUTH) != EnumAttachPosition.NONE);
/*  92 */     boolean flag3 = (state.func_177229_b(WEST) != EnumAttachPosition.NONE);
/*     */     
/*  94 */     if (flag || (flag2 && !flag && !flag1 && !flag3))
/*     */     {
/*  96 */       i |= 1 << EnumFacing.NORTH.func_176736_b();
/*     */     }
/*     */     
/*  99 */     if (flag1 || (flag3 && !flag && !flag1 && !flag2))
/*     */     {
/* 101 */       i |= 1 << EnumFacing.EAST.func_176736_b();
/*     */     }
/*     */     
/* 104 */     if (flag2 || (flag && !flag1 && !flag2 && !flag3))
/*     */     {
/* 106 */       i |= 1 << EnumFacing.SOUTH.func_176736_b();
/*     */     }
/*     */     
/* 109 */     if (flag3 || (flag1 && !flag && !flag2 && !flag3))
/*     */     {
/* 111 */       i |= 1 << EnumFacing.WEST.func_176736_b();
/*     */     }
/*     */     
/* 114 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 120 */     state = state.func_177226_a(WEST, getAttachPosition(worldIn, pos, EnumFacing.WEST));
/* 121 */     state = state.func_177226_a(EAST, getAttachPosition(worldIn, pos, EnumFacing.EAST));
/* 122 */     state = state.func_177226_a(NORTH, getAttachPosition(worldIn, pos, EnumFacing.NORTH));
/* 123 */     return state.func_177226_a(SOUTH, getAttachPosition(worldIn, pos, EnumFacing.SOUTH));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private EnumAttachPosition getAttachPosition(IBlockAccess worldIn, BlockPos pos, EnumFacing direction) {
/* 129 */     BlockPos blockpos = pos.func_177972_a(direction);
/* 130 */     IBlockState iblockstate = worldIn.func_180495_p(pos.func_177972_a(direction));
/*     */     
/* 132 */     if (!canConnectTo(worldIn.func_180495_p(blockpos), direction, worldIn, blockpos)) {
/*     */       
/* 134 */       Block b = worldIn.func_180495_p(blockpos).func_177230_c();
/* 135 */       if (isSourceBlock(worldIn, blockpos)) {
/* 136 */         return EnumAttachPosition.EXT;
/*     */       }
/*     */       
/* 139 */       return EnumAttachPosition.NONE;
/*     */     } 
/*     */ 
/*     */     
/* 143 */     return EnumAttachPosition.SIDE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean canConnectTo(IBlockState blockState, @Nullable EnumFacing side, IBlockAccess world, BlockPos pos) {
/* 149 */     Block block = blockState.func_177230_c();
/* 150 */     return (block == BlocksTC.inlay || block instanceof BlockPedestal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public boolean func_176196_c(World worldIn, BlockPos pos) { return worldIn.func_180495_p(pos.func_177977_b()).func_185896_q(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/* 174 */   public AxisAlignedBB func_180646_a(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) { return field_185506_k; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) { return super.func_185473_a(worldIn, pos, state); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return super.func_180660_a(state, rand, fortune); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public IBlockState func_176203_a(int meta) { return func_176223_P().func_177226_a(CHARGE, Integer.valueOf(meta)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public int func_176201_c(IBlockState state) { return ((Integer)state.func_177229_b(CHARGE)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_185499_a(IBlockState state, Rotation rot) {
/* 209 */     switch (rot) {
/*     */       
/*     */       case LEFT_RIGHT:
/* 212 */         return state.func_177226_a(NORTH, state.func_177229_b(SOUTH)).func_177226_a(EAST, state.func_177229_b(WEST)).func_177226_a(SOUTH, state.func_177229_b(NORTH)).func_177226_a(WEST, state.func_177229_b(EAST));
/*     */       case FRONT_BACK:
/* 214 */         return state.func_177226_a(NORTH, state.func_177229_b(EAST)).func_177226_a(EAST, state.func_177229_b(SOUTH)).func_177226_a(SOUTH, state.func_177229_b(WEST)).func_177226_a(WEST, state.func_177229_b(NORTH));
/*     */       case null:
/* 216 */         return state.func_177226_a(NORTH, state.func_177229_b(WEST)).func_177226_a(EAST, state.func_177229_b(NORTH)).func_177226_a(SOUTH, state.func_177229_b(EAST)).func_177226_a(WEST, state.func_177229_b(SOUTH));
/*     */     } 
/* 218 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_185471_a(IBlockState state, Mirror mirrorIn) {
/* 225 */     switch (mirrorIn) {
/*     */       
/*     */       case LEFT_RIGHT:
/* 228 */         return state.func_177226_a(NORTH, state.func_177229_b(SOUTH)).func_177226_a(SOUTH, state.func_177229_b(NORTH));
/*     */       case FRONT_BACK:
/* 230 */         return state.func_177226_a(EAST, state.func_177229_b(WEST)).func_177226_a(WEST, state.func_177229_b(EAST));
/*     */     } 
/* 232 */     return super.func_185471_a(state, mirrorIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, CHARGE }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 246 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_180655_c(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
/* 254 */     int charge = ((Integer)stateIn.func_177229_b(CHARGE)).intValue();
/* 255 */     if (charge > 0 && rand.nextInt(20 - charge) == 0) {
/* 256 */       EnumFacing face = EnumFacing.field_176754_o[rand.nextInt(EnumFacing.field_176754_o.length)];
/* 257 */       if (getAttachPosition(worldIn, pos, face) != EnumAttachPosition.NONE) {
/* 258 */         double d0 = pos.func_177958_n() + 0.5D + rand.nextGaussian() * 0.08D;
/* 259 */         double d1 = (pos.func_177956_o() + 0.025F);
/* 260 */         double d2 = pos.func_177952_p() + 0.5D + rand.nextGaussian() * 0.08D;
/* 261 */         double f0 = face.func_82601_c() / 70.0D * (1.0D - rand.nextFloat() * 0.1D);
/* 262 */         double f1 = face.func_82599_e() / 70.0D * (1.0D - rand.nextFloat() * 0.1D);
/* 263 */         float r = MathHelper.func_76136_a(rand, 150, 200) / 255.0F;
/* 264 */         float g = MathHelper.func_76136_a(rand, 0, 200) / 255.0F;
/* 265 */         FXDispatcher.INSTANCE.drawLineSparkle(rand, d0, d1, d2, f0, 0.0D, f1, 0.33F, r, g, g / 2.0F, 0, 1.0F, 0.0F, 16);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 272 */   public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) { return (layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.TRANSLUCENT); }
/*     */   
/*     */   enum EnumAttachPosition
/*     */     implements IStringSerializable
/*     */   {
/* 277 */     SIDE("side"),
/* 278 */     NONE("none"),
/* 279 */     EXT("ext");
/*     */ 
/*     */     
/*     */     private final String name;
/*     */ 
/*     */     
/* 285 */     EnumAttachPosition(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     public String toString() { return func_176610_l(); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     public String func_176610_l() { return this.name; }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int colorMultiplier(int meta) {
/* 304 */     float f = meta / 15.0F;
/* 305 */     float f1 = f * 0.5F + 0.5F;
/* 306 */     if (meta == 0) f1 = 0.3F; 
/* 307 */     int i = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
/* 308 */     int j = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
/* 309 */     int k = MathHelper.func_76125_a((int)(f1 * 255.0F), 0, 255);
/* 310 */     return 0xFF000000 | i << 16 | j << 8 | k;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 315 */   public int func_149750_m(IBlockState state) { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public boolean func_189539_a(IBlockState state, World worldIn, BlockPos pos, int par5, int par6) { return super.func_189539_a(state, worldIn, pos, par5, par6); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
/* 336 */     if (!worldIn.field_72995_K) {
/*     */       
/* 338 */       updateSurroundingInlay(worldIn, pos, state);
/*     */       
/* 340 */       for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 342 */         notifyInlayNeighborsOfStateChange(worldIn, pos.func_177972_a(enumfacing1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void notifyInlayNeighborsOfStateChange(World worldIn, BlockPos pos) {
/* 349 */     IBlockState bs = worldIn.func_180495_p(pos);
/* 350 */     if (bs.func_177230_c() == BlocksTC.inlay || bs.func_177230_c() instanceof BlockPedestal) {
/*     */       
/* 352 */       worldIn.func_175685_c(pos, bs.func_177230_c(), false);
/*     */       
/* 354 */       for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 356 */         worldIn.func_175685_c(pos.func_177972_a(enumfacing), bs.func_177230_c(), false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IBlockState updateSurroundingInlay(World worldIn, BlockPos pos, IBlockState state) {
/* 364 */     Set<BlockPos> blocksNeedingUpdate = Sets.newHashSet();
/* 365 */     state = calculateChanges(worldIn, pos, pos, state, blocksNeedingUpdate);
/* 366 */     List<BlockPos> list = Lists.newArrayList(blocksNeedingUpdate);
/*     */     
/* 368 */     for (BlockPos blockpos : list)
/*     */     {
/* 370 */       worldIn.func_175685_c(blockpos, worldIn.func_180495_p(pos).func_177230_c(), false);
/*     */     }
/*     */     
/* 373 */     return state;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxStrength(World worldIn, BlockPos pos, int strength) {
/* 378 */     IBlockState bs = worldIn.func_180495_p(pos);
/* 379 */     if (bs.func_177230_c() != BlocksTC.inlay && !(bs.func_177230_c() instanceof BlockPedestal))
/*     */     {
/* 381 */       return strength;
/*     */     }
/*     */ 
/*     */     
/* 385 */     int i = ((Integer)bs.func_177229_b(CHARGE)).intValue();
/* 386 */     return (i > strength) ? i : strength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSourceStrength(IBlockAccess world, BlockPos pos) {
/* 392 */     for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
/*     */       
/* 394 */       int e = getSourceStrengthAt(world, pos.func_177972_a(enumfacing));
/* 395 */       if (e > 0) return e; 
/*     */     } 
/* 397 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSourceStrengthAt(IBlockAccess world, BlockPos pos) {
/* 402 */     if (isSourceBlock(world, pos)) {
/* 403 */       TileEntity te = world.func_175625_s(pos);
/* 404 */       if (te != null && te instanceof TileStabilizer) {
/* 405 */         return ((TileStabilizer)te).getEnergy();
/*     */       }
/*     */     } 
/* 408 */     return 0;
/*     */   }
/*     */ 
/*     */   
/* 412 */   public static boolean isSourceBlock(IBlockAccess world, BlockPos pos) { return (world.func_180495_p(pos).func_177230_c() == BlocksTC.stabilizer); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IBlockState calculateChanges(World worldIn, BlockPos pos1, BlockPos pos2, IBlockState state, Set<BlockPos> blocksNeedingUpdate) {
/* 417 */     IBlockState iblockstate = state;
/* 418 */     int current = ((Integer)state.func_177229_b(CHARGE)).intValue();
/* 419 */     int max = 0;
/* 420 */     max = getMaxStrength(worldIn, pos2, max);
/*     */     
/* 422 */     int source = getSourceStrength(worldIn, pos1);
/*     */     
/* 424 */     if (source > 0 && source > max - 1)
/*     */     {
/* 426 */       max = source;
/*     */     }
/*     */     
/* 429 */     int neighbour = 0;
/*     */     
/* 431 */     for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
/*     */       
/* 433 */       BlockPos blockpos = pos1.func_177972_a(enumfacing);
/* 434 */       boolean flag = (blockpos.func_177958_n() != pos2.func_177958_n() || blockpos.func_177952_p() != pos2.func_177952_p());
/*     */       
/* 436 */       if (flag)
/*     */       {
/* 438 */         neighbour = getMaxStrength(worldIn, blockpos, neighbour);
/*     */       }
/*     */     } 
/*     */     
/* 442 */     if (neighbour > max) {
/*     */       
/* 444 */       max = neighbour - 1;
/*     */     }
/* 446 */     else if (max > 0) {
/*     */       
/* 448 */       max--;
/*     */     }
/*     */     else {
/*     */       
/* 452 */       max = 0;
/*     */     } 
/*     */     
/* 455 */     if (source > max - 1)
/*     */     {
/* 457 */       max = source;
/*     */     }
/*     */     
/* 460 */     if (current != max) {
/*     */       
/* 462 */       state = state.func_177226_a(CHARGE, Integer.valueOf(max));
/*     */       
/* 464 */       if (worldIn.func_180495_p(pos1) == iblockstate)
/*     */       {
/* 466 */         worldIn.func_180501_a(pos1, state, 2);
/*     */       }
/*     */       
/* 469 */       blocksNeedingUpdate.add(pos1);
/*     */       
/* 471 */       for (EnumFacing enumfacing1 : EnumFacing.values())
/*     */       {
/* 473 */         blocksNeedingUpdate.add(pos1.func_177972_a(enumfacing1));
/*     */       }
/*     */     } 
/*     */     
/* 477 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 483 */     super.func_180663_b(worldIn, pos, state);
/*     */     
/* 485 */     if (!worldIn.field_72995_K) {
/*     */       
/* 487 */       for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 489 */         worldIn.func_175685_c(pos.func_177972_a(enumfacing), this, false);
/*     */       }
/*     */       
/* 492 */       updateSurroundingInlay(worldIn, pos, state);
/*     */       
/* 494 */       for (EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL)
/*     */       {
/* 496 */         notifyInlayNeighborsOfStateChange(worldIn, pos.func_177972_a(enumfacing1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
/* 504 */     if (!worldIn.field_72995_K)
/*     */     {
/* 506 */       if (func_176196_c(worldIn, pos)) {
/* 507 */         updateSurroundingInlay(worldIn, pos, state);
/*     */       } else {
/* 509 */         func_176226_b(worldIn, pos, state, 0);
/* 510 */         worldIn.func_175698_g(pos);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 517 */   public boolean canStabaliseInfusion(World world, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 522 */   public float getStabilizationAmount(World world, BlockPos pos) { return 0.025F; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockInlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */