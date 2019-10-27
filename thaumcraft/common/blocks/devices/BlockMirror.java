/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ public class BlockMirror
/*     */   extends BlockTCDevice implements IBlockFacing {
/*     */   public BlockMirror(Class cls, String name) {
/*  32 */     super(Material.field_151573_f, cls, name);
/*  33 */     func_149672_a(SoundsTC.JAR);
/*  34 */     func_149711_c(0.1F);
/*  35 */     setHarvestLevel(null, 0);
/*  36 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  37 */     bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP);
/*  38 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public SoundType func_185467_w() { return SoundsTC.JAR; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  82 */     bs = func_176223_P();
/*  83 */     return bs.func_177226_a(IBlockFacing.FACING, facing);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/*  92 */     EnumFacing d = BlockStateUtils.getFacing(state);
/*  93 */     if (!worldIn.func_180495_p(pos.func_177972_a(d.func_176734_d())).isSideSolid(worldIn, pos.func_177972_a(d.func_176734_d()), d)) {
/*  94 */       func_176226_b(worldIn, pos, func_176223_P(), 0);
/*  95 */       worldIn.func_175698_g(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 101 */     EnumFacing facing = BlockStateUtils.getFacing(state);
/* 102 */     switch (facing.ordinal()) { default:
/* 103 */         return new AxisAlignedBB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 104 */       case 1: return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
/* 105 */       case 2: return new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
/* 106 */       case 3: return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
/* 107 */       case 4: return new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 108 */       case 5: break; }  return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) { return worldIn.func_180495_p(pos.func_177972_a(side.func_176734_d())).isSideSolid(worldIn, pos.func_177972_a(side.func_176734_d()), side); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 121 */     if (world.field_72995_K) return true; 
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 129 */     TileEntity te = worldIn.func_175625_s(pos);
/* 130 */     if (te instanceof TileMirror || te instanceof TileMirrorEssentia) {
/*     */       
/* 132 */       dropMirror(worldIn, pos, state, te);
/*     */     }
/*     */     else {
/*     */       
/* 136 */       super.func_180653_a(worldIn, pos, state, chance, fortune);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
/* 144 */     if (te instanceof TileMirror || te instanceof TileMirrorEssentia) {
/*     */       
/* 146 */       dropMirror(worldIn, pos, state, te);
/*     */     }
/*     */     else {
/*     */       
/* 150 */       super.func_180657_a(worldIn, player, pos, state, (TileEntity)null, stack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropMirror(World world, BlockPos pos, IBlockState state, TileEntity te) {
/* 156 */     if (this.tileClass == TileMirror.class) {
/* 157 */       TileMirror tm = (TileMirror)te;
/* 158 */       ItemStack drop = new ItemStack(this, 1, 0);
/* 159 */       if (tm != null && tm instanceof TileMirror) {
/* 160 */         if (tm.linked) {
/* 161 */           drop.func_77964_b(1);
/* 162 */           drop.func_77983_a("linkX", new NBTTagInt(tm.linkX));
/* 163 */           drop.func_77983_a("linkY", new NBTTagInt(tm.linkY));
/* 164 */           drop.func_77983_a("linkZ", new NBTTagInt(tm.linkZ));
/* 165 */           drop.func_77983_a("linkDim", new NBTTagInt(tm.linkDim));
/* 166 */           tm.invalidateLink();
/*     */         } 
/* 168 */         func_180635_a(world, pos, drop);
/*     */       } 
/*     */     } else {
/* 171 */       TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 172 */       ItemStack drop = new ItemStack(this, 1, 0);
/* 173 */       if (tm != null && tm instanceof TileMirrorEssentia) {
/* 174 */         if (tm.linked) {
/* 175 */           drop.func_77964_b(1);
/* 176 */           drop.func_77983_a("linkX", new NBTTagInt(tm.linkX));
/* 177 */           drop.func_77983_a("linkY", new NBTTagInt(tm.linkY));
/* 178 */           drop.func_77983_a("linkZ", new NBTTagInt(tm.linkZ));
/* 179 */           drop.func_77983_a("linkDim", new NBTTagInt(tm.linkDim));
/* 180 */           tm.invalidateLink();
/*     */         } 
/* 182 */         func_180635_a(world, pos, drop);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/* 190 */     if (!world.field_72995_K && this.tileClass == TileMirror.class && entity instanceof EntityItem && !entity.field_70128_L && ((EntityItem)entity).field_71088_bW == 0) {
/*     */       
/* 192 */       TileMirror taf = (TileMirror)world.func_175625_s(pos);
/* 193 */       if (taf != null) {
/* 194 */         taf.transport((EntityItem)entity);
/*     */       }
/*     */     } 
/* 197 */     super.func_180634_a(world, pos, state, entity);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */