/*     */ package thaumcraft.common.blocks.misc;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBarrier
/*     */   extends Block
/*     */ {
/*  33 */   public static final Material barrierMat = new MaterialBarrier();
/*     */ 
/*     */   
/*     */   public BlockBarrier() {
/*  37 */     super(barrierMat);
/*  38 */     func_149647_a(null);
/*  39 */     func_149713_g(0);
/*  40 */     func_149663_c("barrier");
/*  41 */     setRegistryName("thaumcraft", "barrier");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> list) {}
/*     */ 
/*     */ 
/*     */   
/*  60 */   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) { return ItemStack.field_190927_a; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing o) { return false; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_185477_a(IBlockState state, World world, BlockPos pos, AxisAlignedBB mask, List list, Entity collidingEntity, boolean isActualState) {
/*  74 */     if (collidingEntity != null && collidingEntity instanceof net.minecraft.entity.EntityLivingBase && !(collidingEntity instanceof EntityPlayer))
/*     */     {
/*  76 */       if (collidingEntity.func_184180_b(EntityPlayer.class).isEmpty()) {
/*  77 */         int a = 1;
/*  78 */         if (world.func_180495_p(pos.func_177979_c(a)).func_177230_c() != BlocksTC.pavingStoneBarrier) a++; 
/*  79 */         if (world.func_175687_A(pos.func_177979_c(a)) == 0) {
/*  80 */           list.add(field_185505_j.func_186670_a(pos));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos pos2) {
/*  88 */     if (world.func_180495_p(pos.func_177979_c(true)) != BlocksTC.pavingStoneBarrier.func_176223_P() && world
/*  89 */       .func_180495_p(pos.func_177979_c(true)) != func_176223_P()) {
/*  90 */       world.func_175698_g(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos) {
/*  96 */     int a = 1;
/*  97 */     while (a < 3) {
/*  98 */       TileEntity te = worldIn.func_175625_s(pos.func_177979_c(a));
/*  99 */       if (te != null && te instanceof thaumcraft.common.tiles.misc.TileBarrierStone) {
/* 100 */         return (te.func_145831_w().func_175687_A(pos.func_177979_c(a)) > 0);
/*     */       }
/* 102 */       a++;
/*     */     } 
/*     */     
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */   
/* 117 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public boolean isAir(IBlockState state, IBlockAccess world, BlockPos pos) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class MaterialBarrier
/*     */     extends Material
/*     */   {
/* 133 */     public MaterialBarrier() { super(MapColor.field_151660_b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     public boolean func_76230_c() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     public boolean func_76220_a() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     public boolean func_76228_b() { return false; }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockBarrier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */