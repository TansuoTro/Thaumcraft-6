/*     */ package thaumcraft.common.blocks.crafting;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ 
/*     */ 
/*     */ public class BlockGolemBuilder
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacingHorizontal
/*     */ {
/*     */   public BlockGolemBuilder() {
/*  27 */     super(Material.field_151576_e, thaumcraft.common.tiles.crafting.TileGolemBuilder.class, "golem_builder");
/*  28 */     func_149672_a(SoundType.field_185851_d);
/*  29 */     func_149647_a(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150898_a(Blocks.field_150331_J); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/*  68 */     destroy(worldIn, pos, state, pos);
/*  69 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */   public static boolean ignore = false;
/*     */   
/*     */   public static void destroy(World worldIn, BlockPos pos, IBlockState state, BlockPos startpos) {
/*  74 */     if (ignore || worldIn.field_72995_K)
/*  75 */       return;  ignore = true;
/*  76 */     for (int a = -1; a <= 1; a++) {
/*  77 */       for (int b = 0; b <= 1; b++) {
/*  78 */         for (int c = -1; c <= 1; c++) {
/*  79 */           if (pos.func_177982_a(a, b, c) != startpos)
/*  80 */           { IBlockState bs = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/*  81 */             if (bs.func_177230_c() == BlocksTC.placeholderBars) {
/*  82 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150411_aY.func_176223_P());
/*     */             }
/*  84 */             if (bs.func_177230_c() == BlocksTC.placeholderAnvil) {
/*  85 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150467_bQ.func_176223_P());
/*     */             }
/*  87 */             if (bs.func_177230_c() == BlocksTC.placeholderCauldron) {
/*  88 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150383_bp.func_176223_P());
/*     */             }
/*  90 */             if (bs.func_177230_c() == BlocksTC.placeholderTable)
/*  91 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), BlocksTC.tableStone.func_176223_P());  } 
/*     */         } 
/*     */       } 
/*  94 */     }  if (pos != startpos) worldIn.func_175656_a(pos, Blocks.field_150331_J.func_176223_P().func_177226_a(BlockPistonBase.field_176387_N, EnumFacing.UP)); 
/*  95 */     ignore = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 102 */     if (world.field_72995_K) return true; 
/* 103 */     player.openGui(Thaumcraft.instance, 19, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 104 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockGolemBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */