/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufInputStream;
/*     */ import io.netty.buffer.ByteBufOutputStream;
/*     */ import io.netty.handler.codec.EncoderException;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemDye;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTSizeTracker;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.internal.WeightedRandomLoot;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Rotation;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketBiomeChange;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Utils
/*     */ {
/*     */   public static boolean isChunkLoaded(World world, int x, int z) {
/*  59 */     Chunk chunk = world.func_72863_F().func_186026_b(x >> 4, z >> 4);
/*  60 */     return (chunk != null && !chunk.func_76621_g());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean useBonemealAtLoc(World world, EntityPlayer player, BlockPos pos) {
/*  65 */     ItemStack is = new ItemStack(Items.field_151100_aR, 1, 15);
/*  66 */     return ((ItemDye)Items.field_151100_aR).applyBonemeal(is, world, pos, player, EnumHand.MAIN_HAND);
/*     */   }
/*     */   
/*     */   public static boolean hasColor(byte[] colors) {
/*  70 */     for (byte col : colors) {
/*  71 */       if (col >= 0)
/*  72 */         return true; 
/*  73 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void copyFile(File sourceFile, File destFile) throws IOException {
/*  89 */     if (!destFile.exists()) {
/*  90 */       destFile.createNewFile();
/*     */     }
/*     */     
/*  93 */     source = null;
/*  94 */     destination = null;
/*     */     
/*     */     try {
/*  97 */       source = (new FileInputStream(sourceFile)).getChannel();
/*  98 */       destination = (new FileOutputStream(destFile)).getChannel();
/*  99 */       destination.transferFrom(source, 0L, source.size());
/*     */     } finally {
/* 101 */       if (source != null) {
/* 102 */         source.close();
/*     */       }
/* 104 */       if (destination != null) {
/* 105 */         destination.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/* 110 */   public static HashMap<List<Object>, ItemStack> specialMiningResult = new HashMap();
/* 111 */   public static HashMap<List<Object>, Float> specialMiningChance = new HashMap();
/*     */   
/*     */   public static void addSpecialMiningResult(ItemStack in, ItemStack out, float chance) {
/* 114 */     specialMiningResult.put(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77952_i()) }, ), out);
/* 115 */     specialMiningChance.put(Arrays.asList(new Object[] { in.func_77973_b(), Integer.valueOf(in.func_77952_i()) }, ), Float.valueOf(chance));
/*     */   }
/*     */   
/*     */   public static ItemStack findSpecialMiningResult(ItemStack is, float chance, Random rand) {
/* 119 */     ItemStack dropped = is.func_77946_l();
/*     */     
/* 121 */     float r = rand.nextFloat();
/* 122 */     List ik = Arrays.asList(new Object[] { is.func_77973_b(), Integer.valueOf(is.func_77952_i()) });
/* 123 */     if (specialMiningResult.containsKey(ik) && r <= chance * ((Float)specialMiningChance.get(ik)).floatValue()) {
/* 124 */       dropped = ((ItemStack)specialMiningResult.get(ik)).func_77946_l();
/* 125 */       dropped.func_190920_e(dropped.func_190916_E() * is.func_190916_E());
/*     */     } 
/* 127 */     return dropped;
/*     */   }
/*     */ 
/*     */   
/* 131 */   public static float clamp_float(float par0, float par1, float par2) { return (par0 < par1) ? par1 : ((par0 > par2) ? par2 : par0); }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static void setBiomeAt(World world, BlockPos pos, Biome biome) { setBiomeAt(world, pos, biome, true); }
/*     */ 
/*     */   
/*     */   public static void setBiomeAt(World world, BlockPos pos, Biome biome, boolean sync) {
/* 139 */     if (biome == null)
/*     */       return; 
/* 141 */     Chunk chunk = world.func_175726_f(pos);
/* 142 */     byte[] array = chunk.func_76605_m();
/* 143 */     array[(pos.func_177952_p() & 0xF) << 4 | pos.func_177958_n() & 0xF] = (byte)(Biome.func_185362_a(biome) & 0xFF);
/* 144 */     chunk.func_76616_a(array);
/* 145 */     if (sync && !world.field_72995_K) {
/* 146 */       PacketHandler.INSTANCE.sendToAllAround(new PacketBiomeChange(pos.func_177958_n(), pos.func_177952_p(), 
/* 147 */             (short)Biome.func_185362_a(biome)), new NetworkRegistry.TargetPoint(world.field_73011_w
/* 148 */             .getDimension(), pos.func_177958_n(), world.func_175645_m(pos).func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 153 */   public static boolean resetBiomeAt(World world, BlockPos pos) { return resetBiomeAt(world, pos, true); }
/*     */ 
/*     */   
/*     */   public static boolean resetBiomeAt(World world, BlockPos pos, boolean sync) {
/* 157 */     Biome[] biomesForGeneration = null;
/* 158 */     biomesForGeneration = world.func_72959_q().func_76937_a(biomesForGeneration, pos.func_177958_n(), pos.func_177952_p(), 1, 1);
/* 159 */     if (biomesForGeneration != null && biomesForGeneration[false] != null) {
/* 160 */       Biome biome = biomesForGeneration[0];
/* 161 */       if (biome != world.func_180494_b(pos)) {
/* 162 */         setBiomeAt(world, pos, biome, sync);
/* 163 */         return true;
/*     */       } 
/*     */     } 
/* 166 */     return false;
/*     */   }
/*     */   public static final String[] colorNames = { 
/* 169 */       "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
/*     */ 
/*     */   
/*     */   public static final int[] colors = { 
/* 173 */       15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 10526880, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 1973019 };
/*     */ 
/*     */ 
/*     */   
/* 177 */   public static ArrayList<List> oreDictLogs = new ArrayList();
/*     */   public static boolean isWoodLog(IBlockAccess world, BlockPos pos) {
/* 179 */     IBlockState bs = world.func_180495_p(pos);
/* 180 */     Block bi = bs.func_177230_c();
/* 181 */     if (bi.isWood(world, pos) || bi.canSustainLeaves(bs, world, pos)) return true; 
/* 182 */     if (oreDictLogs.contains(Arrays.asList(new Object[] { bi, Integer.valueOf(bi.func_176201_c(bs)) })))
/* 183 */       return true; 
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isOreBlock(World world, BlockPos pos) {
/* 189 */     IBlockState bi = world.func_180495_p(pos);
/* 190 */     if (bi.func_177230_c() != Blocks.field_150350_a && bi.func_177230_c() != Blocks.field_150357_h) {
/* 191 */       ItemStack is = BlockUtils.getSilkTouchDrop(bi);
/* 192 */       if (is == null || is.func_190926_b()) {
/* 193 */         int md = bi.func_177230_c().func_176201_c(bi);
/* 194 */         is = new ItemStack(bi.func_177230_c(), 1, md);
/*     */       } 
/* 196 */       if (is == null || is.func_190926_b() || is.func_77973_b() == null) return false; 
/* 197 */       int[] od = OreDictionary.getOreIDs(is);
/* 198 */       if (od != null && od.length > 0) {
/* 199 */         for (int id : od) {
/* 200 */           if (OreDictionary.getOreName(id) != null && 
/* 201 */             OreDictionary.getOreName(id).toUpperCase().contains("ORE")) {
/* 202 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int setNibble(int data, int nibble, int nibbleIndex) {
/* 213 */     int shift = nibbleIndex * 4;
/* 214 */     return data & (15 << shift ^ 0xFFFFFFFF) | nibble << shift;
/*     */   }
/*     */ 
/*     */   
/* 218 */   public static int getNibble(int data, int nibbleIndex) { return data >> nibbleIndex << 2 & 0xF; }
/*     */ 
/*     */ 
/*     */   
/* 222 */   public static boolean getBit(int value, int bit) { return ((value & 1 << bit) != 0); }
/*     */ 
/*     */ 
/*     */   
/* 226 */   public static int setBit(int value, int bit) { return value | 1 << bit; }
/*     */ 
/*     */ 
/*     */   
/* 230 */   public static int clearBit(int value, int bit) { return value & (1 << bit ^ 0xFFFFFFFF); }
/*     */ 
/*     */ 
/*     */   
/* 234 */   public static int toggleBit(int value, int bit) { return value ^ 1 << bit; }
/*     */ 
/*     */   
/*     */   public static byte pack(boolean... vals) {
/* 238 */     byte result = 0;
/* 239 */     for (boolean bit : vals)
/* 240 */       result = (byte)(result << 1 | (bit ? 1 : 0) & true); 
/* 241 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean[] unpack(byte val) {
/* 245 */     boolean[] result = new boolean[8];
/* 246 */     for (int i = 0; i < 8; i++) {
/* 247 */       result[i] = ((byte)(val >> 7 - i & true) == 1);
/*     */     }
/* 249 */     return result;
/*     */   }
/*     */ 
/*     */   
/* 253 */   public static final byte[] intToByteArray(int value) { return new byte[] { (byte)(value >>> 24), (byte)(value >>> 16), (byte)(value >>> 8), (byte)value }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public static int byteArraytoInt(byte[] bytes) { return bytes[0] << 24 | bytes[1] << 16 | bytes[2] << 8 | bytes[3]; }
/*     */ 
/*     */ 
/*     */   
/* 262 */   public static final byte[] shortToByteArray(short value) { return new byte[] { (byte)(value >>> 8), (byte)value }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public static short byteArraytoShort(byte[] bytes) { return (short)(bytes[0] << 8 | bytes[1]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLyingInCone(double[] x, double[] t, double[] b, float aperture) {
/* 284 */     double halfAperture = (aperture / 2.0F);
/*     */ 
/*     */     
/* 287 */     double[] apexToXVect = dif(t, x);
/*     */ 
/*     */     
/* 290 */     double[] axisVect = dif(t, b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     boolean isInInfiniteCone = (dotProd(apexToXVect, axisVect) / magn(apexToXVect) / magn(axisVect) > Math.cos(halfAperture));
/*     */     
/* 303 */     if (!isInInfiniteCone) {
/* 304 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 309 */     return 
/* 310 */       (dotProd(apexToXVect, axisVect) / magn(axisVect) < magn(axisVect));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 315 */   public static double dotProd(double[] a, double[] b) { return a[0] * b[0] + a[1] * b[1] + a[2] * b[2]; }
/*     */ 
/*     */ 
/*     */   
/* 319 */   public static double[] dif(double[] a, double[] b) { return new double[] { a[0] - b[0], a[1] - b[1], a[2] - b[2] }; }
/*     */ 
/*     */ 
/*     */   
/* 323 */   public static double magn(double[] a) { return Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3d calculateVelocity(Vec3d from, Vec3d to, double heightGain, double gravity) {
/* 330 */     double endGain = to.field_72448_b - from.field_72448_b;
/* 331 */     double horizDist = Math.sqrt(distanceSquared2d(from, to));
/*     */ 
/*     */     
/* 334 */     double gain = heightGain;
/* 335 */     double maxGain = (gain > endGain + gain) ? gain : (endGain + gain);
/*     */ 
/*     */     
/* 338 */     double a = -horizDist * horizDist / 4.0D * maxGain;
/* 339 */     double b = horizDist;
/* 340 */     double c = -endGain;
/*     */     
/* 342 */     double slope = -b / 2.0D * a - Math.sqrt(b * b - 4.0D * a * c) / 2.0D * a;
/*     */ 
/*     */     
/* 345 */     double vy = Math.sqrt(maxGain * gravity);
/*     */ 
/*     */     
/* 348 */     double vh = vy / slope;
/*     */ 
/*     */     
/* 351 */     double dx = to.field_72450_a - from.field_72450_a;
/* 352 */     double dz = to.field_72449_c - from.field_72449_c;
/* 353 */     double mag = Math.sqrt(dx * dx + dz * dz);
/* 354 */     double dirx = dx / mag;
/* 355 */     double dirz = dz / mag;
/*     */ 
/*     */     
/* 358 */     double vx = vh * dirx;
/* 359 */     double vz = vh * dirz;
/*     */     
/* 361 */     return new Vec3d(vx, vy, vz);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double distanceSquared2d(Vec3d from, Vec3d to) {
/* 366 */     double dx = to.field_72450_a - from.field_72450_a;
/* 367 */     double dz = to.field_72449_c - from.field_72449_c;
/* 368 */     return dx * dx + dz * dz;
/*     */   }
/*     */   
/*     */   public static double distanceSquared3d(Vec3d from, Vec3d to) {
/* 372 */     double dx = to.field_72450_a - from.field_72450_a;
/* 373 */     double dy = to.field_72448_b - from.field_72448_b;
/* 374 */     double dz = to.field_72449_c - from.field_72449_c;
/* 375 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */   
/*     */   public static ItemStack generateLoot(int rarity, Random rand) {
/* 379 */     ItemStack is = ItemStack.field_190927_a;
/* 380 */     if (rarity > 0 && rand.nextFloat() < 0.025F * rarity) {
/* 381 */       is = genGear(rarity, rand);
/* 382 */       if (is.func_190926_b()) is = generateLoot(rarity, rand); 
/*     */     } else {
/* 384 */       switch (rarity) { default:
/* 385 */           is = ((WeightedRandomLoot)WeightedRandom.func_76271_a(rand, WeightedRandomLoot.lootBagCommon)).item; break;
/* 386 */         case 1: is = ((WeightedRandomLoot)WeightedRandom.func_76271_a(rand, WeightedRandomLoot.lootBagUncommon)).item; break;
/* 387 */         case 2: is = ((WeightedRandomLoot)WeightedRandom.func_76271_a(rand, WeightedRandomLoot.lootBagRare)).item;
/*     */           break; }
/*     */     
/*     */     } 
/* 391 */     if (is.func_77973_b() == Items.field_151122_aG) {
/* 392 */       EnchantmentHelper.func_77504_a(rand, is, (int)(5.0F + rarity * 0.75F * rand.nextInt(18)), false);
/*     */     }
/*     */     
/* 395 */     return is.func_77946_l();
/*     */   }
/*     */   
/*     */   private static ItemStack genGear(int rarity, Random rand) {
/* 399 */     ItemStack is = ItemStack.field_190927_a;
/*     */     
/* 401 */     int quality = rand.nextInt(2);
/* 402 */     if (rand.nextFloat() < 0.2F) quality++; 
/* 403 */     if (rand.nextFloat() < 0.15F) quality++; 
/* 404 */     if (rand.nextFloat() < 0.1F) quality++; 
/* 405 */     if (rand.nextFloat() < 0.095F) quality++; 
/* 406 */     if (rand.nextFloat() < 0.095F) quality++;
/*     */     
/* 408 */     Item item = getGearItemForSlot(rand.nextInt(5), quality);
/* 409 */     if (item != null) {
/* 410 */       is = new ItemStack(item, 1, rand.nextInt(1 + item.func_77612_l() / 6));
/*     */     } else {
/* 412 */       return ItemStack.field_190927_a;
/*     */     } 
/*     */     
/* 415 */     if (rand.nextInt(4) < rarity) {
/* 416 */       EnchantmentHelper.func_77504_a(rand, is, (int)(5.0F + rarity * 0.75F * rand.nextInt(18)), false);
/*     */     }
/* 418 */     return is.func_77946_l();
/*     */   }
/*     */ 
/*     */   
/*     */   private static Item getGearItemForSlot(int slot, int quality) {
/* 423 */     switch (slot) {
/*     */       
/*     */       case 4:
/* 426 */         if (quality == 0) return Items.field_151024_Q; 
/* 427 */         if (quality == 1) return Items.field_151169_ag; 
/* 428 */         if (quality == 2) return Items.field_151020_U; 
/* 429 */         if (quality == 3) return Items.field_151028_Y; 
/* 430 */         if (quality == 4) return ItemsTC.thaumiumHelm; 
/* 431 */         if (quality == 5) return Items.field_151161_ac; 
/* 432 */         if (quality == 6) return ItemsTC.voidHelm; 
/*     */       case 3:
/* 434 */         if (quality == 0) return Items.field_151027_R; 
/* 435 */         if (quality == 1) return Items.field_151171_ah; 
/* 436 */         if (quality == 2) return Items.field_151023_V; 
/* 437 */         if (quality == 3) return Items.field_151030_Z; 
/* 438 */         if (quality == 4) return ItemsTC.thaumiumChest; 
/* 439 */         if (quality == 5) return Items.field_151163_ad; 
/* 440 */         if (quality == 6) return ItemsTC.voidChest; 
/*     */       case 2:
/* 442 */         if (quality == 0) return Items.field_151026_S; 
/* 443 */         if (quality == 1) return Items.field_151149_ai; 
/* 444 */         if (quality == 2) return Items.field_151022_W; 
/* 445 */         if (quality == 3) return Items.field_151165_aa; 
/* 446 */         if (quality == 4) return ItemsTC.thaumiumLegs; 
/* 447 */         if (quality == 5) return Items.field_151173_ae; 
/* 448 */         if (quality == 6) return ItemsTC.voidLegs; 
/*     */       case 1:
/* 450 */         if (quality == 0) return Items.field_151021_T; 
/* 451 */         if (quality == 1) return Items.field_151151_aj; 
/* 452 */         if (quality == 2) return Items.field_151029_X; 
/* 453 */         if (quality == 3) return Items.field_151167_ab; 
/* 454 */         if (quality == 4) return ItemsTC.thaumiumBoots; 
/* 455 */         if (quality == 5) return Items.field_151175_af; 
/* 456 */         if (quality == 6) return ItemsTC.voidBoots; 
/*     */       case 0:
/* 458 */         if (quality == 0) return Items.field_151036_c; 
/* 459 */         if (quality == 1) return Items.field_151040_l; 
/* 460 */         if (quality == 2) return Items.field_151006_E; 
/* 461 */         if (quality == 3) return Items.field_151010_B; 
/* 462 */         if (quality == 4) return ItemsTC.thaumiumSword; 
/* 463 */         if (quality == 5) return Items.field_151048_u; 
/* 464 */         if (quality == 6) return ItemsTC.voidSword;  break;
/*     */     } 
/* 466 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeItemStackToBuffer(ByteBuf bb, ItemStack stack) {
/* 474 */     if (stack == null || stack.func_190926_b()) {
/*     */       
/* 476 */       bb.writeShort(-1);
/*     */     }
/*     */     else {
/*     */       
/* 480 */       bb.writeShort(Item.func_150891_b(stack.func_77973_b()));
/* 481 */       bb.writeShort(stack.func_190916_E());
/* 482 */       bb.writeShort(stack.func_77960_j());
/* 483 */       NBTTagCompound nbttagcompound = null;
/*     */       
/* 485 */       if (stack.func_77973_b().func_77645_m() || stack.func_77973_b().func_77651_p())
/*     */       {
/* 487 */         nbttagcompound = stack.func_77978_p();
/*     */       }
/*     */       
/* 490 */       writeNBTTagCompoundToBuffer(bb, nbttagcompound);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack readItemStackFromBuffer(ByteBuf bb) {
/* 496 */     ItemStack itemstack = ItemStack.field_190927_a;
/* 497 */     short short1 = bb.readShort();
/*     */     
/* 499 */     if (short1 >= 0) {
/*     */       
/* 501 */       short b0 = bb.readShort();
/* 502 */       short short2 = bb.readShort();
/* 503 */       itemstack = new ItemStack(Item.func_150899_d(short1), b0, short2);
/* 504 */       itemstack.func_77982_d(readNBTTagCompoundFromBuffer(bb));
/*     */     } 
/*     */     
/* 507 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeNBTTagCompoundToBuffer(ByteBuf bb, NBTTagCompound nbt) {
/* 512 */     if (nbt == null) {
/*     */       
/* 514 */       bb.writeByte(0);
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 520 */         CompressedStreamTools.func_74800_a(nbt, new ByteBufOutputStream(bb));
/*     */       }
/* 522 */       catch (IOException ioexception) {
/*     */         
/* 524 */         throw new EncoderException(ioexception);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound readNBTTagCompoundFromBuffer(ByteBuf bb) {
/* 531 */     int i = bb.readerIndex();
/* 532 */     byte b0 = bb.readByte();
/*     */     
/* 534 */     if (b0 == 0)
/*     */     {
/* 536 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 540 */     bb.readerIndex(i);
/*     */     try {
/* 542 */       return CompressedStreamTools.func_152456_a(new ByteBufInputStream(bb), new NBTSizeTracker(2097152L));
/* 543 */     } catch (IOException iOException) {
/*     */ 
/*     */       
/* 546 */       return null;
/*     */     } 
/*     */   }
/*     */   
/* 550 */   public static Vec3d rotateAsBlock(Vec3d vec, EnumFacing side) { return rotate(vec.func_178786_a(0.5D, 0.5D, 0.5D), side).func_72441_c(0.5D, 0.5D, 0.5D); }
/*     */ 
/*     */ 
/*     */   
/* 554 */   public static Vec3d rotateAsBlockRev(Vec3d vec, EnumFacing side) { return revRotate(vec.func_178786_a(0.5D, 0.5D, 0.5D), side).func_72441_c(0.5D, 0.5D, 0.5D); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3d rotate(Vec3d vec, EnumFacing side) {
/* 559 */     switch (side) {
/*     */       case DOWN:
/* 561 */         return new Vec3d(vec.field_72450_a, -vec.field_72448_b, -vec.field_72449_c);
/* 562 */       case UP: return new Vec3d(vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
/* 563 */       case NORTH: return new Vec3d(vec.field_72450_a, vec.field_72449_c, -vec.field_72448_b);
/* 564 */       case SOUTH: return new Vec3d(vec.field_72450_a, -vec.field_72449_c, vec.field_72448_b);
/* 565 */       case WEST: return new Vec3d(-vec.field_72448_b, vec.field_72450_a, vec.field_72449_c);
/* 566 */       case EAST: return new Vec3d(vec.field_72448_b, -vec.field_72450_a, vec.field_72449_c);
/*     */     } 
/* 568 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3d revRotate(Vec3d vec, EnumFacing side) {
/* 573 */     switch (side) {
/*     */       case DOWN:
/* 575 */         return new Vec3d(vec.field_72450_a, -vec.field_72448_b, -vec.field_72449_c);
/* 576 */       case UP: return new Vec3d(vec.field_72450_a, vec.field_72448_b, vec.field_72449_c);
/* 577 */       case NORTH: return new Vec3d(vec.field_72450_a, -vec.field_72449_c, vec.field_72448_b);
/* 578 */       case SOUTH: return new Vec3d(vec.field_72450_a, vec.field_72449_c, -vec.field_72448_b);
/* 579 */       case WEST: return new Vec3d(vec.field_72448_b, -vec.field_72450_a, vec.field_72449_c);
/* 580 */       case EAST: return new Vec3d(-vec.field_72448_b, vec.field_72450_a, vec.field_72449_c);
/*     */     } 
/* 582 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3d rotateAroundX(Vec3d vec, float angle) {
/* 587 */     float var2 = MathHelper.func_76134_b(angle);
/* 588 */     float var3 = MathHelper.func_76126_a(angle);
/* 589 */     double var4 = vec.field_72450_a;
/* 590 */     double var6 = vec.field_72448_b * var2 + vec.field_72449_c * var3;
/* 591 */     double var8 = vec.field_72449_c * var2 - vec.field_72448_b * var3;
/* 592 */     return new Vec3d(var4, var6, var8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3d rotateAroundY(Vec3d vec, float angle) {
/* 600 */     float var2 = MathHelper.func_76134_b(angle);
/* 601 */     float var3 = MathHelper.func_76126_a(angle);
/* 602 */     double var4 = vec.field_72450_a * var2 + vec.field_72449_c * var3;
/* 603 */     double var6 = vec.field_72448_b;
/* 604 */     double var8 = vec.field_72449_c * var2 - vec.field_72450_a * var3;
/* 605 */     return new Vec3d(var4, var6, var8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3d rotateAroundZ(Vec3d vec, float angle) {
/* 614 */     float var2 = MathHelper.func_76134_b(angle);
/* 615 */     float var3 = MathHelper.func_76126_a(angle);
/* 616 */     double var4 = vec.field_72450_a * var2 + vec.field_72448_b * var3;
/* 617 */     double var6 = vec.field_72448_b * var2 - vec.field_72450_a * var3;
/* 618 */     double var8 = vec.field_72449_c;
/* 619 */     return new Vec3d(var4, var6, var8);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RayTraceResult rayTrace(World worldIn, Entity entityIn, boolean useLiquids) {
/* 624 */     double d3 = 5.0D;
/* 625 */     if (entityIn instanceof EntityPlayerMP)
/*     */     {
/* 627 */       d3 = ((EntityPlayerMP)entityIn).field_71134_c.getBlockReachDistance();
/*     */     }
/* 629 */     return rayTrace(worldIn, entityIn, useLiquids, d3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RayTraceResult rayTrace(World worldIn, Entity entityIn, boolean useLiquids, double range) {
/* 634 */     float f = entityIn.field_70125_A;
/* 635 */     float f1 = entityIn.field_70177_z;
/* 636 */     double d0 = entityIn.field_70165_t;
/* 637 */     double d1 = entityIn.field_70163_u + entityIn.func_70047_e();
/* 638 */     double d2 = entityIn.field_70161_v;
/* 639 */     Vec3d vec3d = new Vec3d(d0, d1, d2);
/* 640 */     float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
/* 641 */     float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
/* 642 */     float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
/* 643 */     float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
/* 644 */     float f6 = f3 * f4;
/* 645 */     float f7 = f2 * f4;
/* 646 */     Vec3d vec3d1 = vec3d.func_72441_c(f6 * range, f5 * range, f7 * range);
/* 647 */     return worldIn.func_147447_a(vec3d, vec3d1, useLiquids, !useLiquids, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RayTraceResult rayTrace(World worldIn, Entity entityIn, Vec3d lookvec, boolean useLiquids, double range) {
/* 654 */     double d0 = entityIn.field_70165_t;
/* 655 */     double d1 = entityIn.field_70163_u + entityIn.func_70047_e();
/* 656 */     double d2 = entityIn.field_70161_v;
/* 657 */     Vec3d vec3d = new Vec3d(d0, d1, d2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 664 */     Vec3d vec3d1 = vec3d.func_72441_c(lookvec.field_72450_a * range, lookvec.field_72448_b * range, lookvec.field_72449_c * range);
/* 665 */     return worldIn.func_147447_a(vec3d, vec3d1, useLiquids, !useLiquids, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
/*     */     try {
/* 673 */       return clazz.getDeclaredField(fieldName);
/* 674 */     } catch (NoSuchFieldException e) {
/* 675 */       Class superClass = clazz.getSuperclass();
/* 676 */       if (superClass == null) {
/* 677 */         throw e;
/*     */       }
/* 679 */       return getField(superClass, fieldName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB rotateBlockAABB(AxisAlignedBB aabb, EnumFacing facing) {
/* 691 */     Cuboid6 c = (new Cuboid6(aabb)).add(new Vector3(-0.5D, -0.5D, -0.5D)).apply(Rotation.sideRotations[facing.func_176745_a()]).add(new Vector3(0.5D, 0.5D, 0.5D));
/* 692 */     return c.aabb();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */