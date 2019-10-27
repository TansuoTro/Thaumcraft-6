/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.tiles.devices.TileCondenser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCondenserLattice
/*     */   extends BlockTC
/*     */ {
/*  35 */   public static final PropertyBool NORTH = PropertyBool.func_177716_a("north");
/*  36 */   public static final PropertyBool EAST = PropertyBool.func_177716_a("east");
/*  37 */   public static final PropertyBool SOUTH = PropertyBool.func_177716_a("south");
/*  38 */   public static final PropertyBool WEST = PropertyBool.func_177716_a("west");
/*  39 */   public static final PropertyBool UP = PropertyBool.func_177716_a("up");
/*  40 */   public static final PropertyBool DOWN = PropertyBool.func_177716_a("down");
/*     */   private ArrayList<Long> history;
/*     */   
/*     */   public BlockCondenserLattice(boolean dirty) {
/*  44 */     super(Material.field_151573_f, dirty ? "condenser_lattice_dirty" : "condenser_lattice");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     this.history = new ArrayList(); func_149711_c(0.5F); func_149752_b(5.0F);
/*     */     func_149672_a(SoundType.field_185852_e);
/*     */     func_149715_a(dirty ? 0.0F : 0.33F);
/* 163 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(NORTH, Boolean.valueOf(false)).func_177226_a(EAST, Boolean.valueOf(false)).func_177226_a(SOUTH, Boolean.valueOf(false)).func_177226_a(WEST, Boolean.valueOf(false)).func_177226_a(UP, Boolean.valueOf(false)).func_177226_a(DOWN, Boolean.valueOf(false))); } public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; } protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, UP, DOWN }); } public boolean func_149662_c(IBlockState state) { return false; } public boolean func_149686_d(IBlockState state) { return false; } public int func_176201_c(IBlockState state) { return 0; } public void triggerUpdate(World world, BlockPos pos) { this.history.clear();
/* 164 */     BlockPos p = processUpdate(world, pos);
/* 165 */     if (p == null || p.func_177951_i(pos) > 74.0D) {
/* 166 */       func_176226_b(world, pos, func_176223_P(), 0);
/* 167 */       world.func_175698_g(pos);
/*     */     } 
/* 169 */     this.history.clear(); }
/*     */   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { Boolean[] cons = makeConnections(state, worldIn, pos); return state.func_177226_a(DOWN, cons[0]).func_177226_a(UP, cons[1]).func_177226_a(NORTH, cons[2]).func_177226_a(SOUTH, cons[3]).func_177226_a(WEST, cons[4]).func_177226_a(EAST, cons[5]); }
/*     */   private Boolean[] makeConnections(IBlockState state, IBlockAccess world, BlockPos pos) { Boolean[] cons = { null, null, null, null, null, (new Boolean[6][4] = (new Boolean[6][3] = (new Boolean[6][2] = (new Boolean[6][1] = (new Boolean[6][0] = Boolean.valueOf(false)).valueOf(false)).valueOf(false)).valueOf(false)).valueOf(false)).valueOf(false) }; int a = 0; for (EnumFacing face : EnumFacing.field_82609_l) { Block b = world.func_180495_p(pos.func_177972_a(face)).func_177230_c(); if (b instanceof BlockCondenserLattice || (face == EnumFacing.DOWN && b == BlocksTC.condenser)) cons[a] = Boolean.valueOf(true);  a++; }  return cons; }
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) { super.func_176213_c(worldIn, pos, state); triggerUpdate(worldIn, pos); }
/* 173 */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) { super.func_189540_a(state, worldIn, pos, blockIn, fromPos); if (blockIn == BlocksTC.condenserlattice || blockIn == BlocksTC.condenserlatticeDirty || blockIn == BlocksTC.condenser) triggerUpdate(worldIn, pos);  } public boolean func_180639_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) { if (worldIn.field_72995_K) return true;  if (state.func_177230_c() == BlocksTC.condenserlatticeDirty && playerIn.func_184586_b(hand).func_77973_b() == ItemsTC.filter) { playerIn.func_184586_b(hand).func_190918_g(1); if (worldIn.field_73012_v.nextBoolean()) worldIn.func_72838_d(new EntityItem(worldIn, (pos.func_177958_n() + 0.5F + facing.func_82601_c() / 3.0F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F + facing.func_82599_e() / 3.0F), ConfigItems.FLUX_CRYSTAL.func_77946_l()));  worldIn.func_184133_a(null, pos, SoundEvents.field_187638_cR, SoundCategory.BLOCKS, 0.2F, ((worldIn.field_73012_v.nextFloat() - worldIn.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 1.6F); worldIn.func_180501_a(pos, BlocksTC.condenserlattice.func_176223_P(), 3); IBlockState state2 = worldIn.func_180495_p(pos); if (state2.func_177230_c() instanceof BlockCondenserLattice) ((BlockCondenserLattice)state2.func_177230_c()).triggerUpdate(worldIn, pos);  }  return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ); } public void func_176208_a(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) { super.func_176208_a(worldIn, pos, state, player); triggerUpdate(worldIn, pos); } private BlockPos processUpdate(World world, BlockPos pos) { this.history.add(Long.valueOf(pos.func_177986_g()));
/* 174 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/* 175 */       BlockPos p2 = pos.func_177972_a(face);
/* 176 */       if (!this.history.contains(Long.valueOf(p2.func_177986_g()))) {
/* 177 */         Block b = world.func_180495_p(p2).func_177230_c();
/* 178 */         if (b instanceof BlockCondenserLattice) {
/* 179 */           BlockPos pp = processUpdate(world, p2);
/* 180 */           if (pp != null) return pp; 
/*     */         } 
/* 182 */         if (face == EnumFacing.DOWN && b == BlocksTC.condenser) {
/* 183 */           TileEntity te = world.func_175625_s(p2);
/* 184 */           if (te != null && te instanceof TileCondenser) {
/* 185 */             ((TileCondenser)te).latticeCount = -1.0F;
/*     */           }
/* 187 */           return p2;
/*     */         } 
/*     */       } 
/* 190 */     }  return null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 195 */     float minx = 0.3125F, maxx = 0.6875F;
/* 196 */     float miny = 0.3125F, maxy = 0.6875F;
/* 197 */     float minz = 0.3125F, maxz = 0.6875F;
/* 198 */     EnumFacing fd = null;
/* 199 */     for (int side = 0; side < 6; side++) {
/* 200 */       fd = EnumFacing.field_82609_l[side];
/* 201 */       Block b = source.func_180495_p(pos.func_177972_a(fd)).func_177230_c();
/* 202 */       if (b instanceof BlockCondenserLattice || (fd == EnumFacing.DOWN && b == BlocksTC.condenser))
/* 203 */         switch (side) { case 0:
/* 204 */             miny = 0.0F; break;
/* 205 */           case 1: maxy = 1.0F; break;
/* 206 */           case 2: minz = 0.0F; break;
/* 207 */           case 3: maxz = 1.0F; break;
/* 208 */           case 4: minx = 0.0F; break;
/* 209 */           case 5: maxx = 1.0F;
/*     */             break; }
/*     */          
/*     */     } 
/* 213 */     return new AxisAlignedBB(minx, miny, minz, maxx, maxy, maxz);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockCondenserLattice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */