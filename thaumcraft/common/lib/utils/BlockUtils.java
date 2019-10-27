/*     */ package thaumcraft.common.lib.utils;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLog;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.play.server.SPacketBlockBreakAnim;
/*     */ import net.minecraft.network.play.server.SPacketBlockChange;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.ReflectionHelper;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockUtils
/*     */ {
/*  58 */   private static boolean removeBlock(EntityPlayer player, BlockPos pos) { return removeBlock(player, pos, false); }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean removeBlock(EntityPlayer player, BlockPos pos, boolean canHarvest) {
/*  63 */     IBlockState iblockstate = player.field_70170_p.func_180495_p(pos);
/*  64 */     boolean flag = iblockstate.func_177230_c().removedByPlayer(iblockstate, player.field_70170_p, pos, player, canHarvest);
/*     */     
/*  66 */     if (flag) {
/*     */       
/*  68 */       try { iblockstate.func_177230_c().func_176206_d(player.field_70170_p, pos, iblockstate); } catch (Exception exception) {}
/*     */     }
/*     */     
/*  71 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*  75 */   public static boolean harvestBlockSkipCheck(World world, EntityPlayer player, BlockPos pos) { return harvestBlock(world, player, pos, false, false, 0, true); }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static boolean harvestBlock(World world, EntityPlayer player, BlockPos pos) { return harvestBlock(world, player, pos, false, false, 0, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean harvestBlock(World world, EntityPlayer p, BlockPos pos, boolean alwaysDrop, boolean silkOverride, int fortuneOverride, boolean skipEvent) {
/*  85 */     if (world.field_72995_K || !(p instanceof EntityPlayerMP)) return false; 
/*  86 */     EntityPlayerMP player = (EntityPlayerMP)p;
/*     */     
/*  88 */     int exp = skipEvent ? 0 : ForgeHooks.onBlockBreakEvent(world, player.field_71134_c.func_73081_b(), player, pos);
/*  89 */     if (exp == -1)
/*     */     {
/*  91 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  95 */     IBlockState iblockstate = world.func_180495_p(pos);
/*  96 */     TileEntity tileentity = world.func_175625_s(pos);
/*  97 */     Block block = iblockstate.func_177230_c();
/*     */     
/*  99 */     if ((block instanceof net.minecraft.block.BlockCommandBlock || block instanceof net.minecraft.block.BlockStructure) && !player.func_189808_dh()) {
/*     */       
/* 101 */       world.func_184138_a(pos, iblockstate, iblockstate, 3);
/* 102 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 106 */     world.func_180498_a(null, 2001, pos, Block.func_176210_f(iblockstate));
/* 107 */     boolean flag1 = false;
/*     */     
/* 109 */     if (player.field_71134_c.func_73083_d()) {
/*     */       
/* 111 */       flag1 = removeBlock(player, pos);
/* 112 */       player.field_71134_c.field_73090_b.field_71135_a.func_147359_a(new SPacketBlockChange(world, pos));
/*     */     }
/*     */     else {
/*     */       
/* 116 */       ItemStack itemstack1 = player.func_184614_ca();
/* 117 */       boolean flag = (alwaysDrop || iblockstate.func_177230_c().canHarvestBlock(world, pos, player));
/*     */       
/* 119 */       flag1 = removeBlock(player, pos, flag);
/* 120 */       if (flag1 && flag) {
/*     */         
/* 122 */         ItemStack fakeStack = itemstack1.func_77946_l();
/* 123 */         if (silkOverride || fortuneOverride > EnchantmentHelper.func_185284_a(Enchantments.field_185308_t, player)) {
/* 124 */           if (alwaysDrop || fakeStack.func_190926_b())
/* 125 */             fakeStack = new ItemStack(ItemsTC.enchantedPlaceholder); 
/* 126 */           Map<Enchantment, Integer> enchMap = EnchantmentHelper.func_82781_a(itemstack1);
/* 127 */           if (silkOverride) {
/* 128 */             enchMap.put(Enchantments.field_185306_r, Integer.valueOf(1));
/*     */           }
/* 130 */           int fort = Math.max(fortuneOverride, 
/* 131 */               (enchMap.get(Enchantments.field_185308_t) != null) ? ((Integer)enchMap.get(Enchantments.field_185308_t)).intValue() : 0);
/* 132 */           if (fort > 0)
/* 133 */             enchMap.put(Enchantments.field_185308_t, Integer.valueOf(fort)); 
/* 134 */           EnchantmentHelper.func_82782_a(enchMap, fakeStack);
/*     */         } 
/* 136 */         iblockstate.func_177230_c().func_180657_a(world, player, pos, iblockstate, tileentity, fakeStack);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 141 */     if (!player.field_71134_c.func_73083_d() && flag1 && exp > 0)
/*     */     {
/* 143 */       iblockstate.func_177230_c().func_180637_b(world, pos, exp);
/*     */     }
/* 145 */     return flag1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void destroyBlockPartially(World world, int par1, BlockPos pos, int par5) {
/* 263 */     Iterator iterator = world.field_73010_i.iterator();
/*     */     
/* 265 */     while (iterator.hasNext()) {
/* 266 */       EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();
/*     */       
/* 268 */       if (entityplayermp != null && entityplayermp.field_70170_p == 
/* 269 */         FMLCommonHandler.instance().getMinecraftServerInstance().func_130014_f_() && entityplayermp
/* 270 */         .func_145782_y() != par1) {
/* 271 */         double d0 = pos.func_177958_n() - entityplayermp.field_70165_t;
/* 272 */         double d1 = pos.func_177956_o() - entityplayermp.field_70163_u;
/* 273 */         double d2 = pos.func_177952_p() - entityplayermp.field_70161_v;
/*     */         
/* 275 */         if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
/* 276 */           entityplayermp.field_71135_a.func_147359_a(new SPacketBlockBreakAnim(par1, pos, par5));
/*     */         }
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void findBlocks(World world, BlockPos pos, IBlockState block, int reach) {
/* 298 */     for (int xx = -reach; xx <= reach; xx++) {
/* 299 */       for (int yy = reach; yy >= -reach; yy--) {
/* 300 */         for (int zz = -reach; zz <= reach; zz++) {
/* 301 */           if (Math.abs(lastPos.func_177958_n() + xx - pos.func_177958_n()) > 24)
/* 302 */             return;  if (Math.abs(lastPos.func_177956_o() + yy - pos.func_177956_o()) > 48)
/* 303 */             return;  if (Math.abs(lastPos.func_177952_p() + zz - pos.func_177952_p()) > 24)
/* 304 */             return;  IBlockState bs = world.func_180495_p(lastPos.func_177982_a(xx, yy, zz));
/*     */           
/* 306 */           boolean same = (bs.func_177230_c() == block.func_177230_c() && bs.func_177230_c().func_180651_a(bs) == block.func_177230_c().func_180651_a(block));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 314 */           if (same && bs.func_177230_c().func_176195_g(bs, world, lastPos.func_177982_a(xx, yy, zz)) >= 0.0F) {
/* 315 */             double xd = (lastPos.func_177958_n() + xx - pos.func_177958_n());
/* 316 */             double yd = (lastPos.func_177956_o() + yy - pos.func_177956_o());
/* 317 */             double zd = (lastPos.func_177952_p() + zz - pos.func_177952_p());
/* 318 */             double d = xd * xd + yd * yd + zd * zd;
/* 319 */             if (d > lastdistance) {
/* 320 */               lastdistance = d;
/* 321 */               lastPos = lastPos.func_177982_a(xx, yy, zz);
/* 322 */               findBlocks(world, pos, block, reach);
/*     */               return;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 329 */   } static BlockPos lastPos = BlockPos.field_177992_a;
/* 330 */   static int lasty = 0;
/* 331 */   static int lastz = 0;
/* 332 */   static double lastdistance = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean breakFurthestBlock(World world, BlockPos pos, IBlockState block, EntityPlayer player) {
/* 341 */     lastPos = new BlockPos(pos);
/* 342 */     lastdistance = 0.0D;
/*     */     
/* 344 */     int reach = Utils.isWoodLog(world, pos) ? 2 : 1;
/*     */     
/* 346 */     findBlocks(world, pos, block, reach);
/*     */     
/* 348 */     boolean worked = harvestBlockSkipCheck(world, player, lastPos);
/*     */     
/* 350 */     world.markAndNotifyBlock(pos, world.func_175726_f(pos), block, block, 3);
/*     */     
/* 352 */     if (worked && Utils.isWoodLog(world, pos)) {
/*     */       
/* 354 */       world.markAndNotifyBlock(pos, world.func_175726_f(pos), block, block, 3);
/* 355 */       for (int xx = -3; xx <= 3; xx++) {
/* 356 */         for (int yy = -3; yy <= 3; yy++) {
/* 357 */           for (int zz = -3; zz <= 3; zz++)
/* 358 */             world.func_175684_a(lastPos.func_177982_a(xx, yy, zz), world
/* 359 */                 .func_180495_p(lastPos.func_177982_a(xx, yy, zz)).func_177230_c(), 50 + world.field_73012_v
/* 360 */                 .nextInt(75)); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 364 */     return worked;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 384 */   public static RayTraceResult getTargetBlock(World world, Entity entity, boolean par3) { return getTargetBlock(world, entity, par3, par3, 10.0D); }
/*     */ 
/*     */   
/*     */   public static RayTraceResult getTargetBlock(World world, Entity entity, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, double range) {
/* 388 */     float var4 = 1.0F;
/* 389 */     float var5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * var4;
/* 390 */     float var6 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * var4;
/* 391 */     double var7 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * var4;
/* 392 */     double var9 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * var4 + entity.func_70047_e();
/* 393 */     double var11 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * var4;
/* 394 */     Vec3d var13 = new Vec3d(var7, var9, var11);
/* 395 */     float var14 = MathHelper.func_76134_b(-var6 * 0.017453292F - 3.1415927F);
/* 396 */     float var15 = MathHelper.func_76126_a(-var6 * 0.017453292F - 3.1415927F);
/* 397 */     float var16 = -MathHelper.func_76134_b(-var5 * 0.017453292F);
/* 398 */     float var17 = MathHelper.func_76126_a(-var5 * 0.017453292F);
/* 399 */     float var18 = var15 * var16;
/* 400 */     float var20 = var14 * var16;
/* 401 */     Vec3d var23 = var13.func_72441_c(var18 * range, var17 * range, var20 * range);
/* 402 */     return world.func_147447_a(var13, var23, stopOnLiquid, !ignoreBlockWithoutBoundingBox, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int countExposedSides(World world, BlockPos pos) {
/* 432 */     int count = 0;
/* 433 */     for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 434 */       if (world.func_175623_d(pos.func_177972_a(dir))) count++; 
/*     */     } 
/* 436 */     return count;
/*     */   }
/*     */   
/*     */   public static boolean isBlockExposed(World world, BlockPos pos) {
/* 440 */     for (EnumFacing face : EnumFacing.values()) {
/* 441 */       if (!world.func_180495_p(pos.func_177972_a(face)).func_185914_p()) {
/* 442 */         return true;
/*     */       }
/*     */     } 
/* 445 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isAdjacentToSolidBlock(World world, BlockPos pos) {
/* 449 */     for (EnumFacing face : EnumFacing.values()) {
/* 450 */       if (world.isSideSolid(pos.func_177972_a(face), face.func_176734_d())) return true; 
/*     */     } 
/* 452 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlockTouching(IBlockAccess world, BlockPos pos, IBlockState bs) {
/* 456 */     for (EnumFacing face : EnumFacing.values()) {
/* 457 */       if (world.func_180495_p(pos.func_177972_a(face)) == bs) return true; 
/*     */     } 
/* 459 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlockTouching(IBlockAccess world, BlockPos pos, Block bs) {
/* 463 */     for (EnumFacing face : EnumFacing.values()) {
/* 464 */       if (world.func_180495_p(pos.func_177972_a(face)).func_177230_c() == bs) return true; 
/*     */     } 
/* 466 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isBlockTouching(IBlockAccess world, BlockPos pos, Material mat, boolean solid) {
/* 470 */     for (EnumFacing face : EnumFacing.values()) {
/* 471 */       if (world.func_180495_p(pos.func_177972_a(face)).func_185904_a() == mat && (!solid || world
/* 472 */         .func_180495_p(pos.func_177972_a(face)).isSideSolid(world, pos.func_177972_a(face), face.func_176734_d())))
/* 473 */         return true; 
/*     */     } 
/* 475 */     return false;
/*     */   }
/*     */   
/*     */   public static EnumFacing getFaceBlockTouching(IBlockAccess world, BlockPos pos, Block bs) {
/* 479 */     for (EnumFacing face : EnumFacing.values()) {
/* 480 */       if (world.func_180495_p(pos.func_177972_a(face)).func_177230_c() == bs) return face; 
/*     */     } 
/* 482 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 487 */   public static ArrayList<String> portableHoleBlackList = new ArrayList();
/*     */ 
/*     */   
/* 490 */   public static boolean isPortableHoleBlackListed(IBlockState blockstate) { return isBlockListed(blockstate, portableHoleBlackList); }
/*     */ 
/*     */   
/*     */   public static boolean isBlockListed(IBlockState blockstate, List<String> list) {
/* 494 */     String stateString = blockstate.toString();
/* 495 */     for (String key : list) {
/* 496 */       String[] splitString = key.split(";");
/* 497 */       if (splitString[0].contains(":")) {
/* 498 */         if (((ResourceLocation)Block.field_149771_c.func_177774_c(blockstate.func_177230_c())).toString().equals(splitString[0])) {
/* 499 */           if (splitString.length > 1) {
/* 500 */             int matches = 0;
/* 501 */             for (int a = 1; a < splitString.length; a++) {
/* 502 */               if (stateString.contains(splitString[a])) matches++; 
/*     */             } 
/* 504 */             if (matches == splitString.length - 1)
/* 505 */               return true; 
/*     */             continue;
/*     */           } 
/* 508 */           return true;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 512 */       ItemStack bs = new ItemStack(Item.func_150898_a(blockstate.func_177230_c()), 1, blockstate.func_177230_c().func_176201_c(blockstate));
/* 513 */       for (ItemStack stack : OreDictionary.getOres(splitString[0], false)) {
/* 514 */         if (OreDictionary.itemMatches(stack, bs, false)) {
/* 515 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 520 */     return false;
/*     */   }
/*     */   
/*     */   public static double distance(BlockPos b1, BlockPos b2) {
/* 524 */     double d3 = (b1.func_177958_n() - b2.func_177958_n());
/* 525 */     double d4 = (b1.func_177956_o() - b2.func_177956_o());
/* 526 */     double d5 = (b1.func_177952_p() - b2.func_177952_p());
/* 527 */     return d3 * d3 + d4 * d4 + d5 * d5;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumFacing.Axis getBlockAxis(World world, BlockPos pos) {
/* 599 */     IBlockState state = world.func_180495_p(pos);
/* 600 */     EnumFacing.Axis ax = EnumFacing.Axis.Y;
/* 601 */     for (UnmodifiableIterator unmodifiableIterator = state.func_177228_b().keySet().iterator(); unmodifiableIterator.hasNext(); ) { IProperty prop = (IProperty)unmodifiableIterator.next();
/*     */       
/* 603 */       if (prop.func_177701_a().equals("axis")) {
/*     */         
/* 605 */         if (state.func_177229_b(prop) instanceof BlockLog.EnumAxis) {
/*     */ 
/*     */           
/* 608 */           ax = ((BlockLog.EnumAxis)state.func_177229_b(prop) == BlockLog.EnumAxis.X) ? EnumFacing.Axis.X : (((BlockLog.EnumAxis)state.func_177229_b(prop) == BlockLog.EnumAxis.Y) ? EnumFacing.Axis.Y : (((BlockLog.EnumAxis)state.func_177229_b(prop) == BlockLog.EnumAxis.Z) ? EnumFacing.Axis.Z : EnumFacing.Axis.Y));
/*     */           break;
/*     */         } 
/* 611 */         if (state.func_177229_b(prop) instanceof EnumFacing.Axis) {
/* 612 */           ax = (EnumFacing.Axis)state.func_177229_b(prop);
/*     */           break;
/*     */         } 
/*     */       }  }
/*     */     
/* 617 */     if (ax == null) ax = EnumFacing.Axis.Y; 
/* 618 */     return ax;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasLOS(World world, BlockPos source, BlockPos target) {
/* 623 */     RayTraceResult mop = ThaumcraftApiHelper.rayTraceIgnoringSource(world, new Vec3d(source
/* 624 */           .func_177958_n() + 0.5D, source.func_177956_o() + 0.5D, source.func_177952_p() + 0.5D), new Vec3d(target
/* 625 */           .func_177958_n() + 0.5D, target.func_177956_o() + 0.5D, target.func_177952_p() + 0.5D), false, true, false);
/*     */     
/* 627 */     return (mop == null || (mop.field_72313_a == RayTraceResult.Type.BLOCK && mop
/* 628 */       .func_178782_a().func_177958_n() == target.func_177958_n() && mop.func_178782_a().func_177956_o() == target.func_177956_o() && mop.func_178782_a().func_177952_p() == target.func_177952_p()));
/*     */   }
/*     */   
/*     */   public static ItemStack getSilkTouchDrop(IBlockState bs) {
/* 632 */     ItemStack dropped = ItemStack.field_190927_a;
/*     */     try {
/* 634 */       Method m = ReflectionHelper.findMethod(Block.class, "getSilkTouchDrop", "func_180643_i", new Class[] { IBlockState.class });
/* 635 */       dropped = (ItemStack)m.invoke(bs.func_177230_c(), new Object[] { bs });
/* 636 */     } catch (Exception e) {
/* 637 */       Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method getSilkTouchDrop");
/*     */     } 
/* 639 */     return dropped;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class BlockPosComparator
/*     */     extends Object
/*     */     implements Comparator<BlockPos>
/*     */   {
/*     */     private BlockPos source;
/*     */     
/* 649 */     public BlockPosComparator(BlockPos source) { this.source = source; }
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(BlockPos a, BlockPos b) {
/* 654 */       if (a.equals(b))
/*     */       {
/* 656 */         return 0;
/*     */       }
/* 658 */       double da = this.source.func_177951_i(a);
/* 659 */       double db = this.source.func_177951_i(b);
/* 660 */       return (da < db) ? -1 : ((da > db) ? 1 : 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\li\\utils\BlockUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */