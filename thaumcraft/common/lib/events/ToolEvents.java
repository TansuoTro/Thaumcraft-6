/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.EntityFollowingItem;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXScanSource;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXSlash;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public class ToolEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void playerAttack(AttackEntityEvent event) {
/*  59 */     if (event.getEntityPlayer().func_184600_cs() == null)
/*  60 */       return;  ItemStack heldItem = event.getEntityPlayer().func_184586_b(event.getEntityPlayer().func_184600_cs());
/*  61 */     if (heldItem != null && !heldItem.func_190926_b()) {
/*  62 */       List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */ 
/*     */       
/*  65 */       if (list.contains(EnumInfusionEnchantment.ARCING) && event.getTarget().func_70089_S()) {
/*  66 */         int rank = EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.ARCING);
/*  67 */         List targets = (event.getEntityPlayer()).field_70170_p.func_72839_b(event.getEntityPlayer(), event
/*  68 */             .getTarget().func_174813_aQ().func_72314_b(1.5D + rank, (1.0F + rank / 2.0F), 1.5D + rank));
/*  69 */         int count = 0;
/*  70 */         if (targets.size() > 1) {
/*  71 */           for (int var9 = 0; var9 < targets.size(); var9++) {
/*     */             
/*  73 */             Entity var10 = (Entity)targets.get(var9);
/*  74 */             if (!var10.field_70128_L && !EntityUtils.isFriendly(event.getEntity(), var10)) {
/*  75 */               if (var10 instanceof net.minecraft.entity.EntityLiving && var10.func_145782_y() != event.getTarget().func_145782_y() && (
/*  76 */                 !(var10 instanceof EntityPlayer) || ((EntityPlayer)var10).func_70005_c_() != event.getEntityPlayer().func_70005_c_()))
/*     */               {
/*     */ 
/*     */                 
/*  80 */                 if (var10.func_70089_S()) {
/*     */                   
/*  82 */                   float f = (float)event.getEntityPlayer().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*  83 */                   event.getEntityPlayer().func_70652_k(var10);
/*  84 */                   if (var10.func_70097_a(DamageSource.func_76365_a(event.getEntityPlayer()), f * 0.5F)) {
/*     */                     
/*     */                     try {
/*  87 */                       if (var10 instanceof EntityLivingBase)
/*     */                       {
/*  89 */                         EnchantmentHelper.func_151384_a((EntityLivingBase)var10, event
/*     */                             
/*  91 */                             .getEntityPlayer());
/*     */                       }
/*  93 */                     } catch (Exception exception) {}
/*     */                     
/*  95 */                     var10.func_70024_g((-MathHelper.func_76126_a(
/*  96 */                           (event.getEntityPlayer()).field_70177_z * 3.1415927F / 180.0F) * 0.5F), 0.1D, (
/*  97 */                         MathHelper.func_76134_b((event.getEntityPlayer()).field_70177_z * 3.1415927F / 180.0F) * 0.5F));
/*     */                     
/*  99 */                     count++;
/* 100 */                     if (!(event.getEntityPlayer()).field_70170_p.field_72995_K) {
/* 101 */                       PacketHandler.INSTANCE.sendToAllAround(new PacketFXSlash(event.getTarget().func_145782_y(), var10.func_145782_y()), new NetworkRegistry.TargetPoint(
/* 102 */                             (event.getEntityPlayer()).field_70170_p.field_73011_w.getDimension(), 
/* 103 */                             (event.getTarget()).field_70165_t, (event.getTarget()).field_70163_u, (event.getTarget()).field_70161_v, 64.0D));
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               }
/* 108 */               if (count >= rank)
/*     */                 break; 
/*     */             } 
/* 111 */           }  if (count > 0 && !(event.getEntityPlayer()).field_70170_p.field_72995_K) {
/* 112 */             event.getEntityPlayer().func_184185_a(SoundsTC.wind, 1.0F, 0.9F + (event.getEntityPlayer()).field_70170_p.field_73012_v.nextFloat() * 0.2F);
/* 113 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXSlash(event.getEntityPlayer().func_145782_y(), event.getTarget().func_145782_y()), new NetworkRegistry.TargetPoint(
/* 114 */                   (event.getEntityPlayer()).field_70170_p.field_73011_w.getDimension(), 
/* 115 */                   (event.getEntityPlayer()).field_70165_t, (event.getEntityPlayer()).field_70163_u, (event.getEntityPlayer()).field_70161_v, 64.0D));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
/* 124 */     if (!(event.getWorld()).field_72995_K && event.getEntityPlayer() != null) {
/* 125 */       ItemStack heldItem = event.getEntityPlayer().func_184586_b(
/* 126 */           (event.getEntityPlayer().func_184600_cs() == null) ? EnumHand.MAIN_HAND : event.getEntityPlayer().func_184600_cs());
/* 127 */       if (heldItem != null && !heldItem.func_190926_b()) {
/* 128 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */ 
/*     */         
/* 131 */         if (list.contains(EnumInfusionEnchantment.SOUNDING) && event.getEntityPlayer().func_70093_af()) {
/* 132 */           heldItem.func_77972_a(5, event.getEntityPlayer());
/* 133 */           event.getWorld().func_184148_a(null, event.getPos().func_177958_n() + 0.5D, event.getPos().func_177956_o() + 0.5D, event.getPos().func_177952_p() + 0.5D, SoundsTC.wandfail, SoundCategory.BLOCKS, 0.2F, 0.2F + 
/* 134 */               (event.getWorld()).field_73012_v.nextFloat() * 0.2F);
/* 135 */           PacketHandler.INSTANCE.sendTo(new PacketFXScanSource(event
/* 136 */                 .getPos(), 
/* 137 */                 EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.SOUNDING)), (EntityPlayerMP)event
/* 138 */               .getEntityPlayer());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerInteract(PlayerInteractEvent.LeftClickBlock event) {
/* 146 */     if (event.getEntityPlayer() != null) {
/* 147 */       lastFaceClicked.put(Integer.valueOf(event.getEntityPlayer().func_145782_y()), event.getFace());
/*     */     }
/*     */   }
/*     */   
/* 151 */   static HashMap<Integer, EnumFacing> lastFaceClicked = new HashMap();
/*     */   
/* 153 */   public static HashMap<Integer, ArrayList<BlockPos>> blockedBlocks = new HashMap();
/*     */   
/*     */   public static void addBlockedBlock(World world, BlockPos pos) {
/* 156 */     if (!blockedBlocks.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/* 157 */       blockedBlocks.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList());
/*     */     }
/* 159 */     ArrayList<BlockPos> list = (ArrayList)blockedBlocks.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 160 */     if (!list.contains(pos)) list.add(pos); 
/*     */   }
/*     */   
/*     */   public static void clearBlockedBlock(World world, BlockPos pos) {
/* 164 */     if (!blockedBlocks.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/* 165 */       blockedBlocks.put(Integer.valueOf(world.field_73011_w.getDimension()), new ArrayList());
/*     */       return;
/*     */     } 
/* 168 */     ArrayList<BlockPos> list = (ArrayList)blockedBlocks.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 169 */     list.remove(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void breakBlockEvent(BlockEvent.BreakEvent event) {
/* 175 */     if (blockedBlocks.containsKey(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()))) {
/* 176 */       ArrayList<BlockPos> list = (ArrayList)blockedBlocks.get(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()));
/* 177 */       if (list == null) {
/* 178 */         list = new ArrayList<BlockPos>();
/* 179 */         blockedBlocks.put(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()), list);
/*     */       } 
/* 181 */       if (list.contains(event.getPos())) event.setCanceled(true);
/*     */     
/*     */     } 
/* 184 */     if (!(event.getWorld()).field_72995_K && event.getPlayer() != null) {
/* 185 */       ItemStack heldItem = event.getPlayer().func_184586_b(event.getPlayer().func_184600_cs());
/* 186 */       if (heldItem != null && !heldItem.func_190926_b()) {
/* 187 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/* 188 */         if (ForgeHooks.isToolEffective(event.getWorld(), event.getPos(), heldItem))
/*     */         {
/*     */           
/* 191 */           if (list.contains(EnumInfusionEnchantment.BURROWING) && !event.getPlayer().func_70093_af() && isValidBurrowBlock(event.getWorld(), event.getPos())) {
/* 192 */             event.setCanceled(true);
/*     */             
/* 194 */             if (!event.getPlayer().func_70005_c_().equals("FakeThaumcraftBore"))
/*     */             {
/*     */               
/* 197 */               heldItem.func_77972_a(1, event.getPlayer());
/*     */             }
/*     */             
/* 200 */             BlockUtils.breakFurthestBlock(event.getWorld(), event.getPos(), event.getState(), event.getPlayer());
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 209 */   private static boolean isValidBurrowBlock(World world, BlockPos pos) { return (Utils.isWoodLog(world, pos) || Utils.isOreBlock(world, pos)); }
/*     */ 
/*     */   
/*     */   static boolean blockDestructiveRecursion = false;
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void harvestBlockEvent(final BlockEvent.HarvestDropsEvent event) {
/* 217 */     if (!(event.getWorld()).field_72995_K && !event.isSilkTouching() && event.getState().func_177230_c() != null && ((
/* 218 */       event.getState().func_177230_c() == Blocks.field_150482_ag && (event.getWorld()).field_73012_v.nextFloat() < 0.05D) || (event
/* 219 */       .getState().func_177230_c() == Blocks.field_150412_bA && (event.getWorld()).field_73012_v.nextFloat() < 0.075D) || (event
/* 220 */       .getState().func_177230_c() == Blocks.field_150369_x && (event.getWorld()).field_73012_v.nextFloat() < 0.01D) || (event
/* 221 */       .getState().func_177230_c() == Blocks.field_150365_q && (event.getWorld()).field_73012_v.nextFloat() < 0.001D) || (event
/* 222 */       .getState().func_177230_c() == Blocks.field_150439_ay && (event.getWorld()).field_73012_v.nextFloat() < 0.01D) || (event
/* 223 */       .getState().func_177230_c() == Blocks.field_150450_ax && (event.getWorld()).field_73012_v.nextFloat() < 0.01D) || (event
/* 224 */       .getState().func_177230_c() == Blocks.field_150449_bY && (event.getWorld()).field_73012_v.nextFloat() < 0.01D) || (event
/* 225 */       .getState().func_177230_c() == BlocksTC.oreAmber && (event.getWorld()).field_73012_v.nextFloat() < 0.05D) || (event
/* 226 */       .getState().func_177230_c() == BlocksTC.oreQuartz && (event.getWorld()).field_73012_v.nextFloat() < 0.05D))) {
/* 227 */       event.getDrops().add(new ItemStack(ItemsTC.nuggets, 1, 10));
/*     */     }
/*     */ 
/*     */     
/* 231 */     if (!(event.getWorld()).field_72995_K && event.getHarvester() != null) {
/* 232 */       ItemStack heldItem = event.getHarvester().func_184586_b(event.getHarvester().func_184600_cs());
/*     */       
/* 234 */       if (heldItem != null && !heldItem.func_190926_b()) {
/* 235 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */         
/* 237 */         if (event.isSilkTouching() || ForgeHooks.isToolEffective(event.getWorld(), event.getPos(), heldItem) || (heldItem
/* 238 */           .func_77973_b() instanceof ItemTool && ((ItemTool)heldItem
/* 239 */           .func_77973_b()).func_150893_a(heldItem, event.getState()) > 1.0F)) {
/*     */ 
/*     */           
/* 242 */           if (list.contains(EnumInfusionEnchantment.REFINING)) {
/* 243 */             int fortune = 1 + EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.REFINING);
/* 244 */             float chance = fortune * 0.125F;
/* 245 */             boolean b = false;
/* 246 */             for (int a = 0; a < event.getDrops().size(); a++) {
/*     */               
/* 248 */               ItemStack is = (ItemStack)event.getDrops().get(a);
/* 249 */               ItemStack smr = Utils.findSpecialMiningResult(is, chance, (event.getWorld()).field_73012_v);
/* 250 */               if (!is.func_77969_a(smr)) {
/* 251 */                 event.getDrops().set(a, smr);
/* 252 */                 b = true;
/*     */               } 
/*     */             } 
/* 255 */             if (b) {
/* 256 */               event.getWorld().func_184133_a(null, event.getPos(), SoundEvents.field_187604_bf, SoundCategory.PLAYERS, 0.2F, 0.7F + 
/* 257 */                   (event.getWorld()).field_73012_v.nextFloat() * 0.2F);
/*     */             }
/*     */           } 
/*     */           
/* 261 */           if (!blockDestructiveRecursion && list.contains(EnumInfusionEnchantment.DESTRUCTIVE) && !event.getHarvester().func_70093_af()) {
/* 262 */             blockDestructiveRecursion = true;
/*     */             
/* 264 */             EnumFacing face = (EnumFacing)lastFaceClicked.get(Integer.valueOf(event.getHarvester().func_145782_y()));
/* 265 */             if (face == null) {
/* 266 */               face = EnumFacing.func_190914_a(event.getPos(), event.getHarvester());
/*     */             }
/* 268 */             for (int aa = -1; aa <= 1; aa++) {
/* 269 */               for (int bb = -1; bb <= 1; bb++) {
/*     */                 
/* 271 */                 if (aa != 0 || bb != 0) {
/*     */                   
/* 273 */                   int xx = 0;
/* 274 */                   int yy = 0;
/* 275 */                   int zz = 0;
/*     */                   
/* 277 */                   if (face.ordinal() <= 1) {
/* 278 */                     xx = aa;
/* 279 */                     zz = bb;
/* 280 */                   } else if (face.ordinal() <= 3) {
/* 281 */                     xx = aa;
/* 282 */                     yy = bb;
/*     */                   } else {
/* 284 */                     zz = aa;
/* 285 */                     yy = bb;
/*     */                   } 
/*     */                   
/* 288 */                   IBlockState bl = event.getWorld().func_180495_p(event.getPos().func_177982_a(xx, yy, zz));
/*     */                   
/* 290 */                   if (bl.func_185887_b(event.getWorld(), event.getPos().func_177982_a(xx, yy, zz)) >= 0.0F && (
/* 291 */                     ForgeHooks.isToolEffective(event.getWorld(), event.getPos().func_177982_a(xx, yy, zz), heldItem) || (heldItem
/* 292 */                     .func_77973_b() instanceof ItemTool && ((ItemTool)heldItem
/* 293 */                     .func_77973_b()).func_150893_a(heldItem, bl) > 1.0F)))
/*     */                   
/* 295 */                   { if (event.getHarvester().func_70005_c_().equals("FakeThaumcraftBore")) {
/* 296 */                       (event.getHarvester()).field_71090_bL++;
/*     */                     } else {
/* 298 */                       heldItem.func_77972_a(1, event.getHarvester());
/*     */                     } 
/*     */                     
/* 301 */                     BlockUtils.harvestBlock(event.getWorld(), event.getHarvester(), event.getPos().func_177982_a(xx, yy, zz)); } 
/*     */                 } 
/*     */               } 
/* 304 */             }  blockDestructiveRecursion = false;
/*     */           } 
/*     */ 
/*     */           
/* 308 */           if (list.contains(EnumInfusionEnchantment.COLLECTOR) && !event.getHarvester().func_70093_af()) {
/* 309 */             InventoryUtils.dropHarvestsAtPos(event.getWorld(), event.getPos(), event.getDrops(), true, 10, event.getHarvester());
/* 310 */             event.getDrops().clear();
/*     */           } 
/*     */ 
/*     */           
/* 314 */           if (list.contains(EnumInfusionEnchantment.LAMPLIGHT) && !event.getHarvester().func_70093_af() && event.getHarvester() instanceof EntityPlayerMP) {
/* 315 */             WorldServer worldServer = ((EntityPlayerMP)event.getHarvester()).func_71121_q();
/* 316 */             worldServer.func_152344_a(new Runnable() { public void run() {
/* 317 */                     if (event.getWorld().func_175623_d(event.getPos()) && event
/* 318 */                       .getWorld().func_180495_p(event.getPos()) != BlocksTC.effectGlimmer.func_176223_P() && event
/* 319 */                       .getWorld().func_175699_k(event.getPos()) < 10) {
/* 320 */                       event.getWorld().func_180501_a(event.getPos(), BlocksTC.effectGlimmer.func_176223_P(), 3);
/*     */                     }
/*     */                   } }
/*     */               );
/*     */           } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void livingDrops(LivingDropsEvent event) {
/* 359 */     if (event.getSource().func_76346_g() != null && event.getSource().func_76346_g() instanceof EntityPlayer) {
/* 360 */       ItemStack heldItem = ((EntityPlayer)event.getSource().func_76346_g()).func_184586_b(((EntityPlayer)event.getSource().func_76346_g()).func_184600_cs());
/*     */       
/* 362 */       if (heldItem != null && !heldItem.func_190926_b()) {
/* 363 */         List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
/*     */ 
/*     */         
/* 366 */         if (list.contains(EnumInfusionEnchantment.COLLECTOR)) {
/* 367 */           for (int a = 0; a < event.getDrops().size(); a++) {
/*     */             
/* 369 */             EntityItem ei = (EntityItem)event.getDrops().get(a);
/* 370 */             ItemStack is = ei.func_92059_d().func_77946_l();
/* 371 */             EntityFollowingItem entityFollowingItem = new EntityFollowingItem((event.getEntity()).field_70170_p, ei.field_70165_t, ei.field_70163_u, ei.field_70161_v, is, event.getSource().func_76346_g(), 10);
/* 372 */             entityFollowingItem.field_70159_w = ei.field_70159_w;
/* 373 */             entityFollowingItem.field_70181_x = ei.field_70181_x;
/* 374 */             entityFollowingItem.field_70179_y = ei.field_70179_y;
/* 375 */             entityFollowingItem.func_174869_p();
/* 376 */             ei.func_70106_y();
/* 377 */             event.getDrops().set(a, entityFollowingItem);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 382 */         if (list.contains(EnumInfusionEnchantment.ESSENCE)) {
/* 383 */           AspectList as = AspectHelper.getEntityAspects(event.getEntityLiving());
/* 384 */           if (as != null && as.size() > 0) {
/* 385 */             AspectList aspects = as.copy();
/* 386 */             int q = EnumInfusionEnchantment.getInfusionEnchantmentLevel(heldItem, EnumInfusionEnchantment.ESSENCE);
/* 387 */             Aspect[] al = aspects.getAspects();
/* 388 */             int b = ((event.getEntity()).field_70170_p.field_73012_v.nextInt(5) < q) ? 0 : 99;
/* 389 */             while (b < q && al != null && al.length > 0) {
/* 390 */               Aspect aspect = al[(event.getEntity()).field_70170_p.field_73012_v.nextInt(al.length)];
/* 391 */               if (aspects.getAmount(aspect) > 0) {
/* 392 */                 aspects.remove(aspect, 1);
/* 393 */                 ItemStack stack = ThaumcraftApiHelper.makeCrystal(aspect);
/* 394 */                 if (list.contains(EnumInfusionEnchantment.COLLECTOR)) {
/* 395 */                   event.getDrops().add(new EntityFollowingItem(
/* 396 */                         (event.getEntity()).field_70170_p, 
/* 397 */                         (event.getEntityLiving()).field_70165_t, 
/* 398 */                         (event.getEntityLiving()).field_70163_u + event.getEntityLiving().func_70047_e(), 
/* 399 */                         (event.getEntityLiving()).field_70161_v, stack, event.getSource().func_76346_g(), 10));
/*     */                 } else {
/* 401 */                   event.getDrops().add(new EntityItem(
/* 402 */                         (event.getEntity()).field_70170_p, 
/* 403 */                         (event.getEntityLiving()).field_70165_t, 
/* 404 */                         (event.getEntityLiving()).field_70163_u + event.getEntityLiving().func_70047_e(), 
/* 405 */                         (event.getEntityLiving()).field_70161_v, stack));
/*     */                 } 
/* 407 */                 b++;
/*     */               } 
/* 409 */               al = aspects.getAspects();
/* 410 */               if ((event.getEntity()).field_70170_p.field_73012_v.nextInt(q) == 0) b += 1 + (event.getEntity()).field_70170_p.field_73012_v.nextInt(2); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\ToolEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */