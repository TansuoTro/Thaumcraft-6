/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CropUtils
/*     */ {
/*  17 */   public static ArrayList<String> clickableCrops = new ArrayList();
/*  18 */   public static ArrayList<String> standardCrops = new ArrayList();
/*  19 */   public static ArrayList<String> stackedCrops = new ArrayList();
/*  20 */   public static ArrayList<String> lampBlacklist = new ArrayList();
/*     */   
/*     */   public static void addStandardCrop(ItemStack stack, int grownMeta) {
/*  23 */     Block block = Block.func_149634_a(stack.func_77973_b());
/*  24 */     if (block == null) {
/*     */       return;
/*     */     }
/*  27 */     addStandardCrop(block, grownMeta);
/*     */   }
/*     */   
/*     */   public static void addStandardCrop(Block block, int grownMeta) {
/*  31 */     if (grownMeta == 32767)
/*  32 */     { for (int a = 0; a < 16; ) { standardCrops.add(block.func_149739_a() + a); a++; }
/*     */        }
/*  34 */     else { standardCrops.add(block.func_149739_a() + grownMeta); }
/*     */     
/*  36 */     if (block instanceof net.minecraft.block.BlockCrops && grownMeta != 7)
/*  37 */       standardCrops.add(block.func_149739_a() + "7"); 
/*     */   }
/*     */   
/*     */   public static void addClickableCrop(ItemStack stack, int grownMeta) {
/*  41 */     if (Block.func_149634_a(stack.func_77973_b()) == null)
/*  42 */       return;  if (grownMeta == 32767)
/*  43 */     { for (int a = 0; a < 16; ) { clickableCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + a); a++; }
/*     */        }
/*  45 */     else { clickableCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + grownMeta); }
/*     */     
/*  47 */     if (Block.func_149634_a(stack.func_77973_b()) instanceof net.minecraft.block.BlockCrops && grownMeta != 7)
/*  48 */       clickableCrops.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + "7"); 
/*     */   }
/*     */   
/*     */   public static void addStackedCrop(ItemStack stack, int grownMeta) {
/*  52 */     if (Block.func_149634_a(stack.func_77973_b()) == null)
/*  53 */       return;  addStackedCrop(Block.func_149634_a(stack.func_77973_b()), grownMeta);
/*     */   }
/*     */   
/*     */   public static void addStackedCrop(Block block, int grownMeta) {
/*  57 */     if (grownMeta == 32767)
/*  58 */     { for (int a = 0; a < 16; ) { stackedCrops.add(block.func_149739_a() + a); a++; }
/*     */        }
/*  60 */     else { stackedCrops.add(block.func_149739_a() + grownMeta); }
/*     */     
/*  62 */     if (block instanceof net.minecraft.block.BlockCrops && grownMeta != 7)
/*  63 */       stackedCrops.add(block.func_149739_a() + "7"); 
/*     */   }
/*     */   
/*     */   public static boolean isGrownCrop(World world, BlockPos pos) {
/*  67 */     if (world.func_175623_d(pos)) return false; 
/*  68 */     boolean found = false;
/*  69 */     IBlockState bs = world.func_180495_p(pos);
/*  70 */     Block bi = bs.func_177230_c();
/*  71 */     int md = bi.func_176201_c(bs);
/*     */     
/*  73 */     if (standardCrops.contains(bi.func_149739_a() + md) || clickableCrops
/*  74 */       .contains(bi.func_149739_a() + md) || stackedCrops
/*  75 */       .contains(bi.func_149739_a() + md)) {
/*  76 */       found = true;
/*     */     }
/*     */     
/*  79 */     Block biB = world.func_180495_p(pos.func_177977_b()).func_177230_c();
/*     */     
/*  81 */     if ((bi instanceof IGrowable && 
/*     */       
/*  83 */       !((IGrowable)bi).func_176473_a(world, pos, world.func_180495_p(pos), world.field_72995_K) && !(bi instanceof net.minecraft.block.BlockStem)) || (bi instanceof net.minecraft.block.BlockCrops && md == 7 && !found) || standardCrops
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       .contains(bi.func_149739_a() + md) || clickableCrops
/*  90 */       .contains(bi.func_149739_a() + md) || (stackedCrops
/*  91 */       .contains(bi.func_149739_a() + md) && biB == bi))
/*     */     {
/*     */       
/*  94 */       return true;
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public static void blacklistLamp(ItemStack stack, int meta) {
/* 100 */     if (Block.func_149634_a(stack.func_77973_b()) == null)
/* 101 */       return;  if (meta == 32767)
/* 102 */     { for (int a = 0; a < 16; ) { lampBlacklist.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + a); a++; }
/*     */        }
/* 104 */     else { lampBlacklist.add(Block.func_149634_a(stack.func_77973_b()).func_149739_a() + meta); }
/*     */   
/*     */   }
/*     */   
/*     */   public static boolean doesLampGrow(World world, BlockPos pos) {
/* 109 */     Block bi = world.func_180495_p(pos).func_177230_c();
/* 110 */     int md = bi.func_176201_c(world.func_180495_p(pos));
/* 111 */     if (lampBlacklist.contains(bi.func_149739_a() + md)) {
/* 112 */       return false;
/*     */     }
/* 114 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\CropUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */