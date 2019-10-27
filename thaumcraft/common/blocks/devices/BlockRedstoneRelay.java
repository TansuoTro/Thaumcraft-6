/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ @EventBusSubscriber({Side.CLIENT})
/*     */ public class BlockRedstoneRelay extends BlockTCDevice implements IBlockFacingHorizontal, IBlockEnabled {
/*     */   private RayTracer rayTracer;
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D); }
/*     */   
/*     */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */   
/*     */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */   
/*     */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */   
/*     */   public int func_180651_a(IBlockState state) { return 0; }
/*     */   
/*     */   public boolean func_176196_c(World worldIn, BlockPos pos) { return worldIn.func_180495_p(pos.func_177977_b()).func_185896_q() ? super.func_176196_c(worldIn, pos) : 0; }
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos pos) { return worldIn.func_180495_p(pos.func_177977_b()).func_185896_q(); }
/*     */   
/*     */   public void func_180645_a(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*     */     if (!player.field_71075_bZ.field_75099_e)
/*     */       return false; 
/*     */     RayTraceResult hit = RayTracer.retraceBlock(world, player, pos);
/*     */     if (hit == null)
/*     */       return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ); 
/*     */     TileEntity tile = world.func_175625_s(pos);
/*     */     if (tile != null && tile instanceof TileRedstoneRelay) {
/*     */       if (hit.subHit == 0) {
/*     */         ((TileRedstoneRelay)tile).increaseOut();
/*     */         world.func_184133_a(null, pos, SoundsTC.key, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*     */         updateState(world, pos, state);
/*     */         notifyNeighbors(world, pos, state);
/*     */       } 
/*     */       if (hit.subHit == 1) {
/*     */         ((TileRedstoneRelay)tile).increaseIn();
/*     */         world.func_184133_a(null, pos, SoundsTC.key, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*     */         updateState(world, pos, state);
/*     */         notifyNeighbors(world, pos, state);
/*     */       } 
/*     */       return true;
/*     */     } 
/*     */     return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*  52 */   public BlockRedstoneRelay() { super(Material.field_151594_q, TileRedstoneRelay.class, "redstone_relay");
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
/* 404 */     this.rayTracer = new RayTracer(); func_149711_c(0.0F); func_149752_b(0.0F); func_149672_a(SoundType.field_185848_a); func_149649_H(); }
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) { boolean flag = shouldBePowered(worldIn, pos, state); if (isPowered(state) && !flag) { worldIn.func_180501_a(pos, getUnpoweredState(state), 2); notifyNeighbors(worldIn, pos, state); } else if (!isPowered(state)) { worldIn.func_180501_a(pos, getPoweredState(state), 2); notifyNeighbors(worldIn, pos, state); if (!flag) worldIn.func_175654_a(pos, getPoweredState(state).func_177230_c(), getTickDelay(state), -1);  }  }
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) { super.func_180663_b(worldIn, pos, state); notifyNeighbors(worldIn, pos, state); }
/*     */   @SideOnly(Side.CLIENT) public boolean func_176225_a(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) { return (side.func_176740_k() != EnumFacing.Axis.Y); }
/*     */   protected boolean isPowered(IBlockState state) { return BlockStateUtils.isEnabled(state); }
/* 409 */   public int func_176211_b(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) { return func_180656_a(state, worldIn, pos, side); } public int func_180656_a(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) { return !isPowered(state) ? 0 : ((state.func_177229_b(FACING) == side) ? getActiveSignal(worldIn, pos, state) : 0); } public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) { if (canBlockStay(worldIn, pos)) { updateState(worldIn, pos, state); } else { func_176226_b(worldIn, pos, state, 0); worldIn.func_175698_g(pos); EnumFacing[] aenumfacing = EnumFacing.values(); int i = aenumfacing.length; for (int j = 0; j < i; j++) { EnumFacing enumfacing = aenumfacing[j]; worldIn.func_175685_c(pos.func_177972_a(enumfacing), this, false); }  }  } protected void updateState(World worldIn, BlockPos pos, IBlockState state) { boolean flag = shouldBePowered(worldIn, pos, state); if (((isPowered(state) && !flag) || (!isPowered(state) && flag)) && !worldIn.func_175691_a(pos, this)) { byte b0 = -1; if (isFacingTowardsRepeater(worldIn, pos, state)) { b0 = -3; } else if (isPowered(state)) { b0 = -2; }  worldIn.func_175654_a(pos, this, getTickDelay(state), b0); }  } protected boolean shouldBePowered(World worldIn, BlockPos pos, IBlockState state) { int pr = 1; TileEntity tile = worldIn.func_175625_s(pos); if (tile != null && tile instanceof TileRedstoneRelay) pr = ((TileRedstoneRelay)tile).getIn();  return (calculateInputStrength(worldIn, pos, state) >= pr); } @SideOnly(Side.CLIENT) public AxisAlignedBB func_180640_a(IBlockState state, World world, BlockPos pos) { TileEntity tile = world.func_175625_s(pos);
/* 410 */     if (tile != null && tile instanceof TileRedstoneRelay) {
/* 411 */       RayTraceResult hit = RayTracer.retraceBlock(world, (Minecraft.func_71410_x()).field_71439_g, pos);
/* 412 */       if (hit != null && hit.subHit == 0) {
/* 413 */         Cuboid6 cubeoid = ((TileRedstoneRelay)tile).getCuboid0(BlockStateUtils.getFacing(tile.func_145832_p()));
/* 414 */         Vector3 v = new Vector3(pos);
/* 415 */         Cuboid6 c = cubeoid.sub(v);
/* 416 */         return (new AxisAlignedBB((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z))
/*     */           
/* 418 */           .func_186670_a(pos);
/*     */       } 
/* 420 */       if (hit != null && hit.subHit == 1) {
/* 421 */         Cuboid6 cubeoid = ((TileRedstoneRelay)tile).getCuboid1(BlockStateUtils.getFacing(tile.func_145832_p()));
/* 422 */         Vector3 v = new Vector3(pos);
/* 423 */         Cuboid6 c = cubeoid.sub(v);
/* 424 */         return (new AxisAlignedBB((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z))
/*     */           
/* 426 */           .func_186670_a(pos);
/*     */       } 
/*     */     } 
/* 429 */     return super.func_180640_a(state, world, pos); } protected int calculateInputStrength(World worldIn, BlockPos pos, IBlockState state) { EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING); BlockPos blockpos1 = pos.func_177972_a(enumfacing); int i = worldIn.func_175651_c(blockpos1, enumfacing); if (i >= 15) return i;  IBlockState iblockstate1 = worldIn.func_180495_p(blockpos1); return Math.max(i, (iblockstate1.func_177230_c() == Blocks.field_150488_af) ? ((Integer)iblockstate1.func_177229_b(BlockRedstoneWire.field_176351_O)).intValue() : 0); } protected int getPowerOnSides(IBlockAccess worldIn, BlockPos pos, IBlockState state) { EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING); EnumFacing enumfacing1 = enumfacing.func_176746_e(); EnumFacing enumfacing2 = enumfacing.func_176735_f(); return Math.max(getPowerOnSide(worldIn, pos.func_177972_a(enumfacing1), enumfacing1), getPowerOnSide(worldIn, pos.func_177972_a(enumfacing2), enumfacing2)); } protected int getPowerOnSide(IBlockAccess worldIn, BlockPos pos, EnumFacing side) { IBlockState iblockstate = worldIn.func_180495_p(pos); Block block = iblockstate.func_177230_c(); return canPowerSide(block, iblockstate) ? ((block == Blocks.field_150488_af) ? ((Integer)iblockstate.func_177229_b(BlockRedstoneWire.field_176351_O)).intValue() : worldIn.func_175627_a(pos, side)) : 0; } public boolean func_149744_f(IBlockState state) { return true; } public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) { if (shouldBePowered(worldIn, pos, state)) worldIn.func_175684_a(pos, this, 1);  } public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) { bs = func_176223_P(); bs = bs.func_177226_a(FACING, placer.func_70093_af() ? placer.func_174811_aO() : placer.func_174811_aO().func_176734_d()); return bs.func_177226_a(ENABLED, Boolean.valueOf(false)); } public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) { notifyNeighbors(worldIn, pos, state); } protected void notifyNeighbors(World worldIn, BlockPos pos, IBlockState state) { EnumFacing enumfacing = (EnumFacing)state.func_177229_b(FACING); BlockPos blockpos1 = pos.func_177972_a(enumfacing.func_176734_d()); if (ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.func_180495_p(pos), EnumSet.of(enumfacing.func_176734_d()), false).isCanceled()) return;  worldIn.func_190524_a(blockpos1, this, pos); worldIn.func_175695_a(blockpos1, this, enumfacing); } public void func_176206_d(World worldIn, BlockPos pos, IBlockState state) { if (isPowered(state)) { EnumFacing[] aenumfacing = EnumFacing.values(); int i = aenumfacing.length; for (int j = 0; j < i; j++) { EnumFacing enumfacing = aenumfacing[j]; worldIn.func_175685_c(pos.func_177972_a(enumfacing), this, false); }  }  super.func_176206_d(worldIn, pos, state); } protected boolean canPowerSide(Block blockIn, IBlockState iblockstate) { return blockIn.func_149744_f(iblockstate); } protected int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state) { TileEntity tile = worldIn.func_175625_s(pos); if (tile != null && tile instanceof TileRedstoneRelay) return ((TileRedstoneRelay)tile).getOut();  return 0; } public static boolean isRedstoneRepeaterBlockID(Block blockIn) { return (Blocks.field_150413_aR.func_149667_c(blockIn) || Blocks.field_150441_bU.func_149667_c(blockIn)); } public boolean isAssociated(Block other) { return (other == getPoweredState(func_176223_P()).func_177230_c() || other == getUnpoweredState(func_176223_P()).func_177230_c()); }
/*     */   public boolean isFacingTowardsRepeater(World worldIn, BlockPos pos, IBlockState state) { EnumFacing enumfacing = ((EnumFacing)state.func_177229_b(FACING)).func_176734_d(); BlockPos blockpos1 = pos.func_177972_a(enumfacing); return isRedstoneRepeaterBlockID(worldIn.func_180495_p(blockpos1).func_177230_c()) ? ((worldIn.func_180495_p(blockpos1).func_177229_b(FACING) != enumfacing)) : false; }
/*     */   protected int getTickDelay(IBlockState state) { return 2; }
/*     */   protected IBlockState getPoweredState(IBlockState unpoweredState) { EnumFacing enumfacing = (EnumFacing)unpoweredState.func_177229_b(FACING); return func_176223_P().func_177226_a(FACING, enumfacing).func_177226_a(ENABLED, Boolean.valueOf(true)); }
/*     */   protected IBlockState getUnpoweredState(IBlockState poweredState) { EnumFacing enumfacing = (EnumFacing)poweredState.func_177229_b(FACING); return func_176223_P().func_177226_a(FACING, enumfacing).func_177226_a(ENABLED, Boolean.valueOf(false)); }
/*     */   public boolean func_149667_c(Block other) { return isAssociated(other); }
/*     */   @SideOnly(Side.CLIENT) public BlockRenderLayer func_180664_k() { return BlockRenderLayer.CUTOUT; }
/* 436 */   @SideOnly(Side.CLIENT) @SubscribeEvent public void onBlockHighlight(DrawBlockHighlightEvent event) { if ((event.getTarget()).field_72313_a == RayTraceResult.Type.BLOCK && 
/* 437 */       (event.getPlayer()).field_70170_p.func_180495_p(event.getTarget().func_178782_a()).func_177230_c() == this) {
/* 438 */       RayTracer.retraceBlock((event.getPlayer()).field_70170_p, event.getPlayer(), event.getTarget().func_178782_a());
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RayTraceResult func_180636_a(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end) {
/* 445 */     TileEntity tile = world.func_175625_s(pos);
/* 446 */     if (tile == null || !(tile instanceof TileRedstoneRelay)) {
/* 447 */       return super.func_180636_a(state, world, pos, start, end);
/*     */     }
/* 449 */     List<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();
/* 450 */     if (tile instanceof TileRedstoneRelay) {
/* 451 */       ((TileRedstoneRelay)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 453 */     ArrayList<ExtendedMOP> list = new ArrayList<ExtendedMOP>();
/* 454 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(pos), this, list);
/* 455 */     return (list.size() > 0) ? (RayTraceResult)list.get(0) : super.func_180636_a(state, world, pos, start, end);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockRedstoneRelay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */