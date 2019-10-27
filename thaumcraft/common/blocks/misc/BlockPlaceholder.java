/*     */ package thaumcraft.common.blocks.misc;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.EnumPushReaction;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.blocks.crafting.BlockGolemBuilder;
/*     */ import thaumcraft.common.blocks.devices.BlockInfernalFurnace;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPlaceholder
/*     */   extends BlockTC
/*     */ {
/*  28 */   private final Random rand = new Random();
/*     */   
/*     */   public BlockPlaceholder(String name) {
/*  31 */     super(Material.field_151576_e, name);
/*     */     
/*  33 */     func_149711_c(2.5F);
/*  34 */     func_149672_a(SoundType.field_185851_d);
/*     */     
/*  36 */     func_149647_a(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public EnumPushReaction func_149656_h(IBlockState state) { return EnumPushReaction.BLOCK; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected boolean func_149700_E() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
/*  64 */     if (state.func_177230_c() == BlocksTC.placeholderCauldron) return 13; 
/*  65 */     return super.getLightValue(state, world, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.INVISIBLE; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
/*  75 */     if (state.func_177230_c() == BlocksTC.placeholderNetherbrick) return Item.func_150898_a(Blocks.field_150385_bj); 
/*  76 */     if (state.func_177230_c() == BlocksTC.placeholderObsidian) return Item.func_150898_a(Blocks.field_150343_Z); 
/*  77 */     if (state.func_177230_c() == BlocksTC.placeholderBars) return Item.func_150898_a(Blocks.field_150411_aY); 
/*  78 */     if (state.func_177230_c() == BlocksTC.placeholderAnvil) return Item.func_150898_a(Blocks.field_150467_bQ); 
/*  79 */     if (state.func_177230_c() == BlocksTC.placeholderCauldron) return Item.func_150898_a(Blocks.field_150383_bp); 
/*  80 */     if (state.func_177230_c() == BlocksTC.placeholderTable) return Item.func_150898_a(BlocksTC.tableStone); 
/*  81 */     return Item.func_150899_d(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  93 */     if (world.field_72995_K) return true; 
/*  94 */     if (state.func_177230_c() != BlocksTC.placeholderNetherbrick && state.func_177230_c() != BlocksTC.placeholderObsidian)
/*  95 */       for (int a = -1; a <= 1; a++) {
/*  96 */         for (int b = -1; b <= 1; b++) {
/*  97 */           for (int c = -1; c <= 1; c++) {
/*  98 */             IBlockState s = world.func_180495_p(pos.func_177982_a(a, b, c));
/*  99 */             if (s.func_177230_c() == BlocksTC.golemBuilder) {
/* 100 */               player.openGui(Thaumcraft.instance, 19, world, pos
/* 101 */                   .func_177982_a(a, b, c).func_177958_n(), pos.func_177982_a(a, b, c).func_177956_o(), pos.func_177982_a(a, b, c).func_177952_p());
/* 102 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/* 106 */       }   return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 111 */     if ((state.func_177230_c() == BlocksTC.placeholderNetherbrick || state.func_177230_c() == BlocksTC.placeholderObsidian) && !BlockInfernalFurnace.ignore && !worldIn.field_72995_K) {
/*     */ 
/*     */       
/* 114 */       for (int a = -1; a <= 1; a++) {
/* 115 */         for (int b = -1; b <= 1; b++) {
/* 116 */           for (int c = -1; c <= 1; c++) {
/* 117 */             IBlockState s = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/* 118 */             if (s.func_177230_c() == BlocksTC.infernalFurnace) {
/* 119 */               BlockInfernalFurnace.destroyFurnace(worldIn, pos.func_177982_a(a, b, c), s, pos);
/*     */               // Byte code: goto -> 265
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 125 */     } else if (state.func_177230_c() != BlocksTC.placeholderNetherbrick && state.func_177230_c() != BlocksTC.placeholderObsidian && !BlockGolemBuilder.ignore && !worldIn.field_72995_K) {
/*     */ 
/*     */       
/* 128 */       for (int a = -1; a <= 1; a++) {
/* 129 */         for (int b = -1; b <= 1; b++) {
/* 130 */           for (int c = -1; c <= 1; c++) {
/* 131 */             IBlockState s = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/* 132 */             if (s.func_177230_c() == BlocksTC.golemBuilder) {
/* 133 */               BlockGolemBuilder.destroy(worldIn, pos.func_177982_a(a, b, c), s, pos); // Byte code: goto -> 265
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 138 */     }  super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\misc\BlockPlaceholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */