/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.advancements.CriteriaTriggers;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.items.IVisDiscountGear;
/*     */ import thaumcraft.api.items.IWarpingGear;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.items.RechargeHelper;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ConfigResearch;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.capabilities.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.capabilities.PlayerWarp;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ import thaumcraft.common.lib.potions.PotionWarpWard;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public class PlayerEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onFallDamage(LivingHurtEvent event) {
/*  75 */     if (event.getSource() == DamageSource.field_76379_h && event.getEntityLiving() instanceof EntityPlayer) {
/*  76 */       if (((ItemStack)((EntityPlayer)event.getEntityLiving()).field_71071_by.field_70460_b.get(false)).func_77973_b() == ItemsTC.travellerBoots) {
/*  77 */         float f = Math.max(0.0F, event.getAmount() / 2.0F - 1.0F);
/*  78 */         if (f < 1.0F)
/*  79 */         { event.setCanceled(true);
/*  80 */           event.setAmount(0.0F); }
/*  81 */         else { event.setAmount(f); }
/*     */       
/*     */       } 
/*  84 */       if (BaublesApi.isBaubleEquipped((EntityPlayer)event.getEntityLiving(), ItemsTC.ringCloud) >= 0) {
/*  85 */         float f = Math.max(0.0F, event.getAmount() / 3.0F - 2.0F);
/*  86 */         if (f < 1.0F)
/*  87 */         { event.setCanceled(true);
/*  88 */           event.setAmount(0.0F); }
/*  89 */         else if (f < event.getAmount()) { event.setAmount(f); }
/*  90 */          if (event.getAmount() < 1.0F) {
/*  91 */           event.setCanceled(true);
/*  92 */           event.setAmount(0.0F);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void livingTick(LivingEvent.LivingUpdateEvent event) {
/* 100 */     if (event.getEntity() instanceof EntityPlayer) {
/* 101 */       EntityPlayer player = (EntityPlayer)event.getEntity();
/*     */       
/* 103 */       handleMisc(player);
/*     */       
/* 105 */       handleSpeedMods(player);
/*     */       
/* 107 */       if (!player.field_70170_p.field_72995_K) {
/* 108 */         handleRunicArmor(player);
/* 109 */         handleWarp(player);
/*     */         
/* 111 */         if (player.field_70173_aa % 20 == 0)
/*     */         {
/* 113 */           if (ResearchManager.syncList.remove(player.func_70005_c_()) != null) {
/* 114 */             IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/* 115 */             knowledge.sync((EntityPlayerMP)player);
/*     */           } 
/*     */         }
/*     */         
/* 119 */         if (player.field_70173_aa % 200 == 0) {
/* 120 */           ConfigResearch.checkPeriodicStuff(player);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void pickupItem(EntityItemPickupEvent event) {
/* 129 */     if (event.getEntityPlayer() != null && !(event.getEntityPlayer()).field_70170_p.field_72995_K && event
/* 130 */       .getItem() != null && event.getItem().func_92059_d() != null) {
/*     */       
/* 132 */       IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(event.getEntityPlayer());
/*     */       
/* 134 */       if (event.getItem().func_92059_d().func_77973_b() instanceof thaumcraft.common.items.resources.ItemCrystalEssence && 
/* 135 */         !knowledge.isResearchKnown("!gotcrystals")) {
/* 136 */         knowledge.addResearch("!gotcrystals");
/* 137 */         knowledge.sync((EntityPlayerMP)event.getEntityPlayer());
/* 138 */         event.getEntityPlayer().func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.crystals")));
/* 139 */         if (ModConfig.CONFIG_MISC.noSleep && !knowledge.isResearchKnown("!gotdream")) {
/* 140 */           giveDreamJournal(event.getEntityPlayer());
/*     */         }
/*     */       } 
/*     */       
/* 144 */       if (event.getItem().func_92059_d().func_77973_b() instanceof thaumcraft.common.items.curios.ItemThaumonomicon && 
/* 145 */         !knowledge.isResearchKnown("!gotthaumonomicon")) {
/* 146 */         knowledge.addResearch("!gotthaumonomicon");
/* 147 */         knowledge.sync((EntityPlayerMP)event.getEntityPlayer());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void wakeUp(PlayerWakeUpEvent event) {
/* 154 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(event.getEntityPlayer());
/* 155 */     if (event.getEntityPlayer() != null && !(event.getEntityPlayer()).field_70170_p.field_72995_K && knowledge
/* 156 */       .isResearchKnown("!gotcrystals") && 
/* 157 */       !knowledge.isResearchKnown("!gotdream")) {
/* 158 */       giveDreamJournal(event.getEntityPlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   private static void giveDreamJournal(EntityPlayer player) {
/* 163 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/* 164 */     knowledge.addResearch("!gotdream");
/* 165 */     knowledge.sync((EntityPlayerMP)player);
/* 166 */     ItemStack book = ConfigItems.startBook.func_77946_l();
/* 167 */     book.func_77978_p().func_74778_a("author", player.func_70005_c_());
/* 168 */     if (!player.field_71071_by.func_70441_a(book)) {
/* 169 */       InventoryUtils.dropItemAtEntity(player.field_70170_p, book, player);
/*     */     }
/*     */     try {
/* 172 */       player.func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.dream")));
/* 173 */     } catch (Exception exception) {}
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
/*     */   private static void handleMisc(EntityPlayer player) {
/* 185 */     if (player.field_70170_p.field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.dimensionOuterId && player.field_70173_aa % 20 == 0 && 
/* 186 */       !player.func_175149_v() && !player.field_71075_bZ.field_75098_d && player.field_71075_bZ.field_75100_b) {
/*     */       
/* 188 */       player.field_71075_bZ.field_75100_b = false;
/*     */       
/* 190 */       player.func_146105_b(new TextComponentString(TextFormatting.ITALIC + "" + TextFormatting.GRAY + 
/*     */             
/* 192 */             I18n.func_74838_a("tc.break.fly")), true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void tooltipEvent(ItemTooltipEvent event) {
/*     */     try {
/* 201 */       int charge = getRunicCharge(event.getItemStack());
/* 202 */       if (charge > 0) {
/* 203 */         event.getToolTip().add(TextFormatting.GOLD + I18n.func_74838_a("item.runic.charge") + " +" + charge);
/*     */       }
/* 205 */       int warp = getFinalWarp(event.getItemStack(), event.getEntityPlayer());
/* 206 */       if (warp > 0) {
/* 207 */         event.getToolTip().add(TextFormatting.DARK_PURPLE + I18n.func_74838_a("item.warping") + " " + warp);
/*     */       }
/* 209 */       int al = getFinalDiscount(event.getItemStack(), event.getEntityPlayer());
/* 210 */       if (al > 0) {
/* 211 */         event.getToolTip().add(TextFormatting.DARK_PURPLE + I18n.func_74838_a("tc.visdiscount") + ": " + al + "%");
/*     */       }
/* 213 */       if (event.getItemStack() != null) {
/* 214 */         if (event.getItemStack().func_77973_b() instanceof thaumcraft.api.items.IRechargable) {
/* 215 */           int c = Math.round(RechargeHelper.getCharge(event.getItemStack()));
/* 216 */           if (c >= 0) {
/* 217 */             event.getToolTip().add(TextFormatting.YELLOW + I18n.func_74838_a("tc.charge") + " " + c);
/*     */           }
/*     */         } 
/* 220 */         if (event.getItemStack().func_77973_b() instanceof IEssentiaContainerItem) {
/* 221 */           AspectList aspects = ((IEssentiaContainerItem)event.getItemStack().func_77973_b()).getAspects(event.getItemStack());
/* 222 */           if (aspects != null && aspects.size() > 0) {
/* 223 */             for (Aspect tag : aspects.getAspectsSortedByName()) {
/* 224 */               event.getToolTip().add(tag.getName() + " x" + aspects.getAmount(tag));
/*     */             }
/*     */           }
/*     */         } 
/*     */         
/* 229 */         NBTTagList nbttaglist = EnumInfusionEnchantment.getInfusionEnchantmentTagList(event.getItemStack());
/* 230 */         if (nbttaglist != null)
/*     */         {
/* 232 */           for (int j = 0; j < nbttaglist.func_74745_c(); j++) {
/*     */             
/* 234 */             int k = nbttaglist.func_150305_b(j).func_74765_d("id");
/* 235 */             int l = nbttaglist.func_150305_b(j).func_74765_d("lvl");
/* 236 */             if (k >= 0 && k < EnumInfusionEnchantment.values().length) {
/* 237 */               String s = TextFormatting.GOLD + I18n.func_74838_a("enchantment.infusion." + EnumInfusionEnchantment.values()[k].toString());
/* 238 */               if ((EnumInfusionEnchantment.values()[k]).maxLevel > 1) {
/* 239 */                 s = s + " " + I18n.func_74838_a("enchantment.level." + l);
/*     */               }
/* 241 */               event.getToolTip().add(1, s);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/* 246 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   static HashMap<Integer, Long> nextCycle = new HashMap();
/* 253 */   static HashMap<Integer, Integer> lastCharge = new HashMap();
/* 254 */   static HashMap<Integer, Integer> lastMaxCharge = new HashMap();
/* 255 */   static HashMap<Integer, Integer> runicInfo = new HashMap();
/* 256 */   static HashMap<String, Long> upgradeCooldown = new HashMap();
/*     */ 
/*     */   
/*     */   private static void handleRunicArmor(EntityPlayer player) {
/* 260 */     if (player.field_70173_aa % 20 == 0) {
/* 261 */       int max = 0;
/*     */       
/* 263 */       for (int a = 0; a < 4; a++) {
/* 264 */         max += getRunicCharge((ItemStack)player.field_71071_by.field_70460_b.get(a));
/*     */       }
/*     */       
/* 267 */       IInventory baubles = BaublesApi.getBaubles(player);
/* 268 */       for (int a = 0; a < baubles.func_70302_i_(); a++) {
/* 269 */         max += getRunicCharge(baubles.func_70301_a(a));
/*     */       }
/*     */       
/* 272 */       if (lastMaxCharge.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 273 */         int charge = ((Integer)lastMaxCharge.get(Integer.valueOf(player.func_145782_y()))).intValue();
/* 274 */         if (charge > max) {
/* 275 */           player.func_110149_m(player.func_110139_bj() - (charge - max));
/*     */         }
/* 277 */         if (max <= 0) {
/* 278 */           lastMaxCharge.remove(Integer.valueOf(player.func_145782_y()));
/*     */         }
/*     */       } 
/*     */       
/* 282 */       if (max > 0) {
/* 283 */         runicInfo.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(max));
/* 284 */         lastMaxCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(max));
/*     */       } else {
/* 286 */         runicInfo.remove(Integer.valueOf(player.func_145782_y()));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 292 */     if (runicInfo.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 293 */       if (!nextCycle.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 294 */         nextCycle.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(0L));
/*     */       }
/* 296 */       long time = System.currentTimeMillis();
/*     */       
/* 298 */       int charge = (int)player.func_110139_bj();
/* 299 */       if (charge == 0 && lastCharge
/* 300 */         .containsKey(Integer.valueOf(player.func_145782_y())) && ((Integer)lastCharge
/* 301 */         .get(Integer.valueOf(player.func_145782_y()))).intValue() > 0) {
/* 302 */         nextCycle.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(time + ModConfig.CONFIG_MISC.shieldWait));
/* 303 */         lastCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(0));
/*     */       } 
/*     */       
/* 306 */       if (charge < ((Integer)runicInfo.get(Integer.valueOf(player.func_145782_y()))).intValue() && ((Long)nextCycle
/* 307 */         .get(Integer.valueOf(player.func_145782_y()))).longValue() < time && 
/* 308 */         !AuraHandler.shouldPreserveAura(player.field_70170_p, player, player.func_180425_c()) && 
/* 309 */         AuraHelper.getVis(player.field_70170_p, new BlockPos(player)) >= ModConfig.CONFIG_MISC.shieldCost) {
/*     */         
/* 311 */         AuraHandler.drainVis(player.field_70170_p, new BlockPos(player), ModConfig.CONFIG_MISC.shieldCost, false);
/*     */         
/* 313 */         nextCycle.put(Integer.valueOf(player.func_145782_y()), Long.valueOf(time + ModConfig.CONFIG_MISC.shieldRecharge));
/*     */         
/* 315 */         player.func_110149_m((charge + 1));
/* 316 */         lastCharge.put(Integer.valueOf(player.func_145782_y()), Integer.valueOf(charge + 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getRunicCharge(ItemStack stack) {
/* 323 */     int base = 0;
/* 324 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("TC.RUNIC")) {
/* 325 */       base += stack.func_77978_p().func_74771_c("TC.RUNIC");
/*     */     }
/* 327 */     return base;
/*     */   }
/*     */   
/*     */   public static int getFinalWarp(ItemStack stack, EntityPlayer player) {
/* 331 */     if (stack == null || stack.func_190926_b()) return 0; 
/* 332 */     int warp = 0;
/* 333 */     if (stack.func_77973_b() instanceof IWarpingGear) {
/* 334 */       IWarpingGear armor = (IWarpingGear)stack.func_77973_b();
/* 335 */       warp += armor.getWarp(stack, player);
/*     */     } 
/* 337 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("TC.WARP")) {
/* 338 */       warp += stack.func_77978_p().func_74771_c("TC.WARP");
/*     */     }
/* 340 */     return warp;
/*     */   }
/*     */   
/*     */   public static int getFinalDiscount(ItemStack stack, EntityPlayer player) {
/* 344 */     if (stack == null || stack.func_190926_b() || !(stack.func_77973_b() instanceof IVisDiscountGear)) return 0; 
/* 345 */     IVisDiscountGear gear = (IVisDiscountGear)stack.func_77973_b();
/* 346 */     return gear.getVisDiscount(stack, player);
/*     */   }
/*     */   
/* 349 */   public static HashMap<Integer, Float> prevStep = new HashMap();
/*     */   
/*     */   private static void handleSpeedMods(EntityPlayer player) {
/* 352 */     if (player.field_70170_p.field_72995_K && (player.func_70093_af() || ((ItemStack)player.field_71071_by.field_70460_b
/* 353 */       .get(false)).func_77973_b() != ItemsTC.travellerBoots) && prevStep
/* 354 */       .containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 355 */       player.field_70138_W = ((Float)prevStep.get(Integer.valueOf(player.func_145782_y()))).floatValue();
/* 356 */       prevStep.remove(Integer.valueOf(player.func_145782_y()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerJumps(LivingEvent.LivingJumpEvent event) {
/* 364 */     if (event.getEntity() instanceof EntityPlayer && (
/* 365 */       (ItemStack)((EntityPlayer)event.getEntity()).field_71071_by.field_70460_b.get(false)).func_77973_b() == ItemsTC.travellerBoots) {
/* 366 */       ItemStack is = (ItemStack)((EntityPlayer)event.getEntity()).field_71071_by.field_70460_b.get(0);
/* 367 */       if (RechargeHelper.getCharge(is) > 0) {
/* 368 */         (event.getEntityLiving()).field_70181_x += 0.2750000059604645D;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void handleWarp(EntityPlayer player) {
/* 375 */     if (!ModConfig.CONFIG_MISC.wussMode && player.field_70173_aa > 0 && player.field_70173_aa % 2000 == 0 && 
/* 376 */       !player.func_70644_a(PotionWarpWard.instance)) {
/* 377 */       WarpEvents.checkWarpEvent(player);
/*     */     }
/*     */ 
/*     */     
/* 381 */     if (player.field_70173_aa % 20 == 0 && player.func_70644_a(PotionDeathGaze.instance)) {
/* 382 */       WarpEvents.checkDeathGaze(player);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void droppedItem(ItemTossEvent event) {
/* 388 */     NBTTagCompound itemData = event.getEntityItem().getEntityData();
/* 389 */     itemData.func_74778_a("thrower", event.getPlayer().func_70005_c_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void finishedUsingItem(LivingEntityUseItemEvent.Finish event) {
/* 396 */     if (!(event.getEntity()).field_70170_p.field_72995_K && 
/* 397 */       event.getEntityLiving().func_70644_a(PotionUnnaturalHunger.instance)) {
/* 398 */       if (event.getItem().func_77969_a(new ItemStack(Items.field_151078_bh)) || event
/* 399 */         .getItem().func_77969_a(new ItemStack(ItemsTC.brain))) {
/*     */         
/* 401 */         PotionEffect pe = event.getEntityLiving().func_70660_b(PotionUnnaturalHunger.instance);
/* 402 */         int amp = pe.func_76458_c() - 1;
/* 403 */         int duration = pe.func_76459_b() - 600;
/*     */         
/* 405 */         event.getEntityLiving().func_184589_d(PotionUnnaturalHunger.instance);
/*     */         
/* 407 */         if (duration > 0 && amp >= 0) {
/* 408 */           pe = new PotionEffect(PotionUnnaturalHunger.instance, duration, amp, true, false);
/* 409 */           pe.getCurativeItems().clear();
/* 410 */           pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 411 */           event.getEntityLiving().func_70690_d(pe);
/*     */         } 
/*     */         
/* 414 */         event.getEntityLiving().func_145747_a(new TextComponentString("§2§o" + 
/*     */               
/* 416 */               I18n.func_74838_a("warp.text.hunger.2")));
/*     */       }
/* 418 */       else if (event.getItem().func_77973_b() instanceof net.minecraft.item.ItemFood) {
/* 419 */         event.getEntityLiving().func_145747_a(new TextComponentString("§4§o" + 
/*     */               
/* 421 */               I18n.func_74838_a("warp.text.hunger.1")));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
/* 429 */     if (event.getObject() instanceof EntityPlayer) {
/* 430 */       event.addCapability(PlayerKnowledge.Provider.NAME, new PlayerKnowledge.Provider());
/* 431 */       event.addCapability(PlayerWarp.Provider.NAME, new PlayerWarp.Provider());
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerJoin(EntityJoinWorldEvent event) {
/* 437 */     if (!(event.getWorld()).field_72995_K && event.getEntity() instanceof EntityPlayerMP) {
/* 438 */       EntityPlayerMP player = (EntityPlayerMP)event.getEntity();
/* 439 */       IPlayerKnowledge pk = ThaumcraftCapabilities.getKnowledge(player);
/* 440 */       IPlayerWarp pw = ThaumcraftCapabilities.getWarp(player);
/* 441 */       if (pk != null) pk.sync(player); 
/* 442 */       if (pw != null) pw.sync(player);
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void cloneCapabilitiesEvent(PlayerEvent.Clone event) {
/*     */     try {
/* 450 */       NBTTagCompound nbtKnowledge = (NBTTagCompound)ThaumcraftCapabilities.getKnowledge(event.getOriginal()).serializeNBT();
/* 451 */       ThaumcraftCapabilities.getKnowledge(event.getEntityPlayer()).deserializeNBT(nbtKnowledge);
/* 452 */       NBTTagCompound nbtWarp = (NBTTagCompound)ThaumcraftCapabilities.getWarp(event.getOriginal()).serializeNBT();
/* 453 */       ThaumcraftCapabilities.getWarp(event.getEntityPlayer()).deserializeNBT(nbtWarp);
/* 454 */     } catch (Exception e) {
/* 455 */       Thaumcraft.log.error("Could not clone player [" + event.getOriginal().func_70005_c_() + "] knowledge when changing dimensions");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void pickupXP(PlayerPickupXpEvent event) {
/* 462 */     if (event.getEntityPlayer() != null && !(event.getEntityPlayer()).field_70170_p.field_72995_K && 
/* 463 */       BaublesApi.isBaubleEquipped(event.getEntityPlayer(), ItemsTC.bandCuriosity) >= 0 && 
/* 464 */       event.getOrb().func_70526_d() > 1) {
/* 465 */       int d = (event.getOrb()).field_70530_e / 2;
/* 466 */       (event.getOrb()).field_70530_e -= d;
/* 467 */       float r = event.getEntityPlayer().func_70681_au().nextFloat();
/* 468 */       if (r < 0.05D * d) {
/* 469 */         String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/* 470 */         String cat = s[event.getEntityPlayer().func_70681_au().nextInt(s.length)];
/* 471 */         ThaumcraftApi.internalMethods.addKnowledge(event.getEntityPlayer(), IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory(cat), 1);
/* 472 */       } else if (r < 0.2D * d) {
/* 473 */         String[] s = (String[])ResearchCategories.researchCategories.keySet().toArray(new String[0]);
/* 474 */         String cat = s[event.getEntityPlayer().func_70681_au().nextInt(s.length)];
/* 475 */         ThaumcraftApi.internalMethods.addKnowledge(event.getEntityPlayer(), IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory(cat), 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDeath(LivingDeathEvent event) {
/* 484 */     if (event.getEntityLiving() instanceof EntityPlayer) {
/* 485 */       EntityPlayer player = (EntityPlayer)event.getEntityLiving();
/* 486 */       int slot = BaublesApi.isBaubleEquipped(player, ItemsTC.charmUndying);
/* 487 */       if (slot >= 0) {
/* 488 */         if (player instanceof EntityPlayerMP) {
/*     */           
/* 490 */           EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
/* 491 */           entityplayermp.func_71029_a(StatList.func_188057_b(Items.field_190929_cY));
/* 492 */           CriteriaTriggers.field_193130_A.func_193187_a(entityplayermp, BaublesApi.getBaubles(player).func_70301_a(slot));
/*     */         } 
/* 494 */         BaublesApi.getBaublesHandler(player).extractItem(slot, 1, false);
/* 495 */         player.func_70606_j(1.0F);
/* 496 */         player.func_70674_bp();
/* 497 */         player.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 900, 1));
/* 498 */         player.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 100, 1));
/* 499 */         player.field_70170_p.func_72960_a(player, (byte)35);
/* 500 */         event.setCanceled(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\PlayerEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */