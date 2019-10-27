/*     */ package thaumcraft.common.blocks.world.plants;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBush;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import net.minecraftforge.event.terraingen.TerrainGen;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.world.objects.WorldGenGreatwoodTrees;
/*     */ import thaumcraft.common.world.objects.WorldGenSilverwoodTrees;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSaplingTC
/*     */   extends BlockBush
/*     */   implements IGrowable
/*     */ {
/*  32 */   public static final PropertyInteger STAGE = PropertyInteger.func_177719_a("stage", 0, 1);
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockSaplingTC(String name) {
/*  37 */     func_149663_c(name);
/*  38 */     setRegistryName("thaumcraft", name);
/*  39 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(STAGE, Integer.valueOf(0)));
/*  40 */     func_149647_a(ConfigItems.TABTC);
/*  41 */     func_149672_a(SoundType.field_185850_c);
/*     */   }
/*     */   
/*  44 */   protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return SAPLING_AABB; }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return 60; }
/*     */ 
/*     */   
/*  56 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 30; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  61 */     if (!worldIn.field_72995_K) {
/*     */       
/*  63 */       super.func_180650_b(worldIn, pos, state, rand);
/*     */       
/*  65 */       if (worldIn.func_175671_l(pos.func_177984_a()) >= 9 && rand.nextInt(7) == 0)
/*     */       {
/*  67 */         grow(worldIn, pos, state, rand);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  75 */     if (((Integer)state.func_177229_b(STAGE)).intValue() == 0) {
/*     */       
/*  77 */       worldIn.func_180501_a(pos, state.func_177231_a(STAGE), 4);
/*     */     }
/*     */     else {
/*     */       
/*  81 */       generateTree(worldIn, pos, state, rand);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  87 */     if (!TerrainGen.saplingGrowTree(worldIn, rand, pos))
/*  88 */       return;  Object object = null;
/*  89 */     int i = 0;
/*  90 */     int j = 0;
/*  91 */     boolean flag = false;
/*     */     
/*  93 */     if (state.func_177230_c() == BlocksTC.saplingGreatwood) {
/*     */       
/*  95 */       for (i = 0; i >= -1; i--) {
/*     */         
/*  97 */         for (j = 0; j >= -1; j--) {
/*     */           
/*  99 */           if (isTwoByTwoOfType(worldIn, pos, i, j, BlocksTC.saplingGreatwood)) {
/*     */             
/* 101 */             object = new WorldGenGreatwoodTrees(true, false);
/* 102 */             flag = true;
/*     */             // Byte code: goto -> 111
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 108 */       object = new WorldGenSilverwoodTrees(true, 7, 4);
/*     */     } 
/*     */     
/* 111 */     if (object == null)
/*     */       return; 
/* 113 */     IBlockState iblockstate1 = Blocks.field_150350_a.func_176223_P();
/*     */     
/* 115 */     if (flag) {
/* 116 */       worldIn.func_180501_a(pos.func_177982_a(i, 0, j), iblockstate1, 4);
/* 117 */       worldIn.func_180501_a(pos.func_177982_a(i + 1, 0, j), iblockstate1, 4);
/* 118 */       worldIn.func_180501_a(pos.func_177982_a(i, 0, j + 1), iblockstate1, 4);
/* 119 */       worldIn.func_180501_a(pos.func_177982_a(i + 1, 0, j + 1), iblockstate1, 4);
/*     */     } else {
/* 121 */       worldIn.func_180501_a(pos, iblockstate1, 4);
/*     */     } 
/*     */     
/* 124 */     if (!((WorldGenerator)object).func_180709_b(worldIn, rand, pos.func_177982_a(i, 0, j)))
/*     */     {
/* 126 */       if (flag) {
/* 127 */         worldIn.func_180501_a(pos.func_177982_a(i, 0, j), state, 4);
/* 128 */         worldIn.func_180501_a(pos.func_177982_a(i + 1, 0, j), state, 4);
/* 129 */         worldIn.func_180501_a(pos.func_177982_a(i, 0, j + 1), state, 4);
/* 130 */         worldIn.func_180501_a(pos.func_177982_a(i + 1, 0, j + 1), state, 4);
/*     */       } else {
/* 132 */         worldIn.func_180501_a(pos.func_177982_a(i, 0, j), state, 4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isTwoByTwoOfType(World worldIn, BlockPos pos, int p_181624_3_, int p_181624_4_, Block type) {
/* 139 */     return (isTypeAt(worldIn, pos.func_177982_a(p_181624_3_, 0, p_181624_4_), type) && 
/* 140 */       isTypeAt(worldIn, pos.func_177982_a(p_181624_3_ + 1, 0, p_181624_4_), type) && 
/* 141 */       isTypeAt(worldIn, pos.func_177982_a(p_181624_3_, 0, p_181624_4_ + 1), type) && 
/* 142 */       isTypeAt(worldIn, pos.func_177982_a(p_181624_3_ + 1, 0, p_181624_4_ + 1), type));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeAt(World worldIn, BlockPos pos, Block type) {
/* 147 */     IBlockState iblockstate = worldIn.func_180495_p(pos);
/* 148 */     return (iblockstate.func_177230_c() == type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public boolean func_176473_a(World worldIn, BlockPos pos, IBlockState state, boolean isClient) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public boolean func_180670_a(World worldIn, Random rand, BlockPos pos, IBlockState state) { return (worldIn.field_73012_v.nextFloat() < 0.25D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void func_176474_b(World worldIn, Random rand, BlockPos pos, IBlockState state) { grow(worldIn, pos, state, rand); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public IBlockState func_176203_a(int meta) { return func_176223_P().func_177226_a(STAGE, Integer.valueOf((meta & 0x8) >> 3)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 185 */     i = 0;
/* 186 */     return ((Integer)state.func_177229_b(STAGE)).intValue() << 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { STAGE }); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\plants\BlockSaplingTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */