/*     */ package thaumcraft.common.blocks.crafting;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.tiles.crafting.TileThaumatorium;
/*     */ import thaumcraft.common.tiles.crafting.TileThaumatoriumTop;
/*     */ 
/*     */ public class BlockThaumatorium
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacingHorizontal
/*     */ {
/*     */   boolean top;
/*     */   
/*     */   public BlockThaumatorium(boolean top) {
/*  34 */     super(Material.field_151573_f, null, top ? "thaumatorium_top" : "thaumatorium");
/*  35 */     func_149672_a(SoundType.field_185852_e);
/*  36 */     func_149647_a(null);
/*  37 */     this.top = top;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata) {
/*  42 */     if (!this.top) return new TileThaumatorium(); 
/*  43 */     if (this.top) return new TileThaumatoriumTop(); 
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */ 
/*     */   
/*  52 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return this.top ? EnumBlockRenderType.INVISIBLE : EnumBlockRenderType.MODEL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  76 */     if (!world.field_72995_K && !player.func_70093_af()) {
/*  77 */       if (!this.top) {
/*  78 */         player.openGui(Thaumcraft.instance, 3, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*     */       } else {
/*  80 */         player.openGui(Thaumcraft.instance, 3, world, pos.func_177977_b().func_177958_n(), pos.func_177977_b().func_177956_o(), pos.func_177977_b().func_177952_p());
/*     */       } 
/*     */     }
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150898_a(BlocksTC.metalAlchemical); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 100 */     if (this.top && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.thaumatorium) {
/* 101 */       worldIn.func_175656_a(pos.func_177977_b(), BlocksTC.metalAlchemical.func_176223_P());
/*     */     }
/* 103 */     if (!this.top && worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == BlocksTC.thaumatoriumTop) {
/* 104 */       worldIn.func_175656_a(pos.func_177984_a(), BlocksTC.metalAlchemical.func_176223_P());
/*     */     }
/* 106 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/* 111 */     if (!this.top && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != BlocksTC.crucible) {
/* 112 */       worldIn.func_175656_a(pos, BlocksTC.metalAlchemical.func_176223_P());
/* 113 */       if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == BlocksTC.thaumatoriumTop) {
/* 114 */         worldIn.func_175656_a(pos.func_177984_a(), BlocksTC.metalAlchemical.func_176223_P());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 120 */   public boolean func_149740_M(IBlockState state) { return !this.top; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_180641_l(IBlockState state, World world, BlockPos pos) {
/* 125 */     TileEntity tile = world.func_175625_s(pos);
/* 126 */     if (tile != null && tile instanceof TileThaumatorium) {
/* 127 */       return Container.func_94526_b((IInventory)tile);
/*     */     }
/* 129 */     return super.func_180641_l(state, world, pos);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockThaumatorium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */