/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import baubles.api.BaublesApi;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.entities.monster.EntityEldritchGuardian;
/*     */ import thaumcraft.common.entities.monster.EntityMindSpider;
/*     */ import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketMiscEvent;
/*     */ import thaumcraft.common.lib.potions.PotionBlurredVision;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*     */ import thaumcraft.common.lib.potions.PotionThaumarhia;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WarpEvents
/*     */ {
/*     */   public static void checkWarpEvent(EntityPlayer player) {
/*  48 */     IPlayerWarp wc = ThaumcraftCapabilities.getWarp(player);
/*     */     
/*  50 */     ThaumcraftApi.internalMethods.addWarpToPlayer(player, -1, IPlayerWarp.EnumWarpType.TEMPORARY);
/*     */     
/*  52 */     int tw = wc.get(IPlayerWarp.EnumWarpType.TEMPORARY);
/*  53 */     int nw = wc.get(IPlayerWarp.EnumWarpType.NORMAL);
/*  54 */     int pw = wc.get(IPlayerWarp.EnumWarpType.PERMANENT);
/*     */     
/*  56 */     int warp = tw + nw + pw;
/*  57 */     int actualwarp = pw + nw;
/*     */     
/*  59 */     int gearWarp = getWarpFromGear(player);
/*     */     
/*  61 */     warp += gearWarp;
/*     */     
/*  63 */     int warpCounter = wc.getCounter();
/*     */     
/*  65 */     int r = player.field_70170_p.field_73012_v.nextInt(100);
/*     */     
/*  67 */     if (warpCounter > 0 && warp > 0 && r <= Math.sqrt(warpCounter)) {
/*     */       
/*  69 */       warp = Math.min(100, (warp + warp + warpCounter) / 3);
/*     */       
/*  71 */       warpCounter = (int)(warpCounter - Math.max(5.0D, Math.sqrt(warpCounter) * 2.0D - (gearWarp * 2)));
/*     */       
/*  73 */       wc.setCounter(warpCounter);
/*     */       
/*  75 */       int eff = player.field_70170_p.field_73012_v.nextInt(warp) + gearWarp;
/*     */ 
/*     */       
/*  78 */       ItemStack helm = (ItemStack)player.field_71071_by.field_70460_b.get(3);
/*  79 */       if (helm.func_77973_b() instanceof thaumcraft.common.items.armor.ItemFortressArmor && 
/*  80 */         helm.func_77942_o() && helm
/*  81 */         .func_77978_p().func_74764_b("mask") && helm
/*  82 */         .func_77978_p().func_74762_e("mask") == 0) {
/*  83 */         eff -= 2 + player.field_70170_p.field_73012_v.nextInt(4);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  88 */       PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((byte)0), (EntityPlayerMP)player);
/*     */ 
/*     */ 
/*     */       
/*  92 */       if (eff > 0)
/*     */       {
/*     */ 
/*     */         
/*  96 */         if (eff <= 4) {
/*  97 */           if (!ModConfig.CONFIG_GRAPHICS.nostress) {
/*  98 */             player.field_70170_p.func_184133_a(player, player.func_180425_c(), SoundEvents.field_187572_ar, SoundCategory.AMBIENT, 1.0F, 0.5F);
/*     */           
/*     */           }
/*     */         }
/* 102 */         else if (eff <= 8) {
/* 103 */           if (!ModConfig.CONFIG_GRAPHICS.nostress) player.field_70170_p.func_184148_a(player, player.field_70165_t + ((player.field_70170_p.field_73012_v
/* 104 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 10.0F), player.field_70163_u + ((player.field_70170_p.field_73012_v
/* 105 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 10.0F), player.field_70161_v + ((player.field_70170_p.field_73012_v
/* 106 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 10.0F), SoundEvents.field_187539_bB, SoundCategory.AMBIENT, 4.0F, (1.0F + (player.field_70170_p.field_73012_v
/*     */                 
/* 108 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
/*     */         
/*     */         }
/* 111 */         else if (eff <= 12) {
/* 112 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 114 */                 I18n.func_74838_a("warp.text.11")), true);
/*     */         
/*     */         }
/* 117 */         else if (eff <= 16) {
/* 118 */           PotionEffect pe = new PotionEffect(PotionVisExhaust.instance, 5000, Math.min(3, warp / 15), true, true);
/* 119 */           pe.getCurativeItems().clear();
/*     */           try {
/* 121 */             player.func_70690_d(pe);
/* 122 */           } catch (Exception e) {
/* 123 */             e.printStackTrace();
/*     */           } 
/* 125 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 127 */                 I18n.func_74838_a("warp.text.1")), true);
/*     */         
/*     */         }
/* 130 */         else if (eff <= 20) {
/* 131 */           PotionEffect pe = new PotionEffect(PotionThaumarhia.instance, Math.min(32000, 10 * warp), 0, true, true);
/* 132 */           pe.getCurativeItems().clear();
/*     */           try {
/* 134 */             player.func_70690_d(pe);
/* 135 */           } catch (Exception e) {
/* 136 */             e.printStackTrace();
/*     */           } 
/* 138 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 140 */                 I18n.func_74838_a("warp.text.15")), true);
/*     */         
/*     */         }
/* 143 */         else if (eff <= 24) {
/* 144 */           PotionEffect pe = new PotionEffect(PotionUnnaturalHunger.instance, 5000, Math.min(3, warp / 15), true, true);
/* 145 */           pe.getCurativeItems().clear();
/* 146 */           pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 147 */           pe.addCurativeItem(new ItemStack(ItemsTC.brain));
/*     */           try {
/* 149 */             player.func_70690_d(pe);
/* 150 */           } catch (Exception e) {
/* 151 */             e.printStackTrace();
/*     */           } 
/* 153 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 155 */                 I18n.func_74838_a("warp.text.2")), true);
/*     */         
/*     */         }
/* 158 */         else if (eff <= 28) {
/* 159 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 161 */                 I18n.func_74838_a("warp.text.12")), true);
/*     */         
/*     */         }
/* 164 */         else if (eff <= 32) {
/* 165 */           spawnMist(player, warp, 1);
/*     */         
/*     */         }
/* 168 */         else if (eff <= 36) {
/*     */           try {
/* 170 */             player.func_70690_d(new PotionEffect(PotionBlurredVision.instance, Math.min(32000, 10 * warp), 0, true, true));
/* 171 */           } catch (Exception e) {
/* 172 */             e.printStackTrace();
/*     */           }
/*     */         
/*     */         }
/* 176 */         else if (eff <= 40) {
/* 177 */           PotionEffect pe = new PotionEffect(PotionSunScorned.instance, 5000, Math.min(3, warp / 15), true, true);
/* 178 */           pe.getCurativeItems().clear();
/*     */           try {
/* 180 */             player.func_70690_d(pe);
/* 181 */           } catch (Exception e) {
/* 182 */             e.printStackTrace();
/*     */           } 
/* 184 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 186 */                 I18n.func_74838_a("warp.text.5")), true);
/*     */         
/*     */         }
/* 189 */         else if (eff <= 44) {
/*     */           try {
/* 191 */             player.func_70690_d(new PotionEffect(MobEffects.field_76419_f, 1200, Math.min(3, warp / 15), true, true));
/* 192 */           } catch (Exception e) {
/* 193 */             e.printStackTrace();
/*     */           } 
/* 195 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 197 */                 I18n.func_74838_a("warp.text.9")), true);
/*     */         
/*     */         }
/* 200 */         else if (eff <= 48) {
/* 201 */           PotionEffect pe = new PotionEffect(PotionInfectiousVisExhaust.instance, 6000, Math.min(3, warp / 15));
/* 202 */           pe.getCurativeItems().clear();
/*     */           try {
/* 204 */             player.func_70690_d(pe);
/* 205 */           } catch (Exception e) {
/* 206 */             e.printStackTrace();
/*     */           } 
/* 208 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 210 */                 I18n.func_74838_a("warp.text.1")), true);
/*     */         
/*     */         }
/* 213 */         else if (eff <= 52) {
/* 214 */           player.func_70690_d(new PotionEffect(MobEffects.field_76439_r, Math.min(40 * warp, 6000), 0, true, true));
/* 215 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 217 */                 I18n.func_74838_a("warp.text.10")), true);
/*     */         
/*     */         }
/* 220 */         else if (eff <= 56) {
/* 221 */           PotionEffect pe = new PotionEffect(PotionDeathGaze.instance, 6000, Math.min(3, warp / 15), true, true);
/* 222 */           pe.getCurativeItems().clear();
/*     */           try {
/* 224 */             player.func_70690_d(pe);
/* 225 */           } catch (Exception e) {
/*     */             
/* 227 */             e.printStackTrace();
/*     */           } 
/* 229 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 231 */                 I18n.func_74838_a("warp.text.4")), true);
/*     */         
/*     */         }
/* 234 */         else if (eff <= 60) {
/* 235 */           suddenlySpiders(player, warp, false);
/*     */         
/*     */         }
/* 238 */         else if (eff <= 64) {
/* 239 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 241 */                 I18n.func_74838_a("warp.text.13")), true);
/*     */         
/*     */         }
/* 244 */         else if (eff <= 68) {
/* 245 */           spawnMist(player, warp, warp / 30);
/*     */         
/*     */         }
/* 248 */         else if (eff <= 72) {
/*     */           try {
/* 250 */             player.func_70690_d(new PotionEffect(MobEffects.field_76440_q, Math.min(32000, 5 * warp), 0, true, true));
/* 251 */           } catch (Exception e) {
/* 252 */             e.printStackTrace();
/*     */           }
/*     */         
/*     */         }
/* 256 */         else if (eff == 76) {
/* 257 */           if (nw > 0) {
/* 258 */             ThaumcraftApi.internalMethods.addWarpToPlayer(player, -1, IPlayerWarp.EnumWarpType.NORMAL);
/*     */           }
/* 260 */           player.func_146105_b(new TextComponentString("§5§o" + 
/*     */                 
/* 262 */                 I18n.func_74838_a("warp.text.14")), true);
/*     */         
/*     */         }
/* 265 */         else if (eff <= 80) {
/* 266 */           PotionEffect pe = new PotionEffect(PotionUnnaturalHunger.instance, 6000, Math.min(3, warp / 15), true, true);
/* 267 */           pe.getCurativeItems().clear();
/* 268 */           pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
/* 269 */           pe.addCurativeItem(new ItemStack(ItemsTC.brain));
/*     */           try {
/* 271 */             player.func_70690_d(pe);
/* 272 */           } catch (Exception e) {
/* 273 */             e.printStackTrace();
/*     */           } 
/* 275 */           player.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("warp.text.2")), true);
/*     */         
/*     */         }
/* 278 */         else if (eff <= 88) {
/* 279 */           spawnPortal(player);
/*     */         
/*     */         }
/* 282 */         else if (eff <= 92) {
/* 283 */           suddenlySpiders(player, warp, true);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 288 */           spawnMist(player, warp, warp / 15);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 293 */       if (actualwarp > 10)
/*     */       {
/* 295 */         if (!ThaumcraftCapabilities.knowsResearch(player, new String[] { "BATHSALTS"
/* 296 */             }) && !ThaumcraftCapabilities.knowsResearch(player, new String[] { "!BATHSALTS" })) {
/* 297 */           player.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("warp.text.8")), true);
/* 298 */           ThaumcraftApi.internalMethods.completeResearch(player, "!BATHSALTS");
/*     */         } 
/*     */       }
/* 301 */       if (actualwarp > 25)
/*     */       {
/* 303 */         if (!ThaumcraftCapabilities.knowsResearch(player, new String[] { "ELDRITCHMINOR" })) {
/* 304 */           ThaumcraftApi.internalMethods.completeResearch(player, "ELDRITCHMINOR");
/*     */         }
/*     */       }
/*     */       
/* 308 */       if (actualwarp > 50)
/*     */       {
/* 310 */         if (!ThaumcraftCapabilities.knowsResearch(player, new String[] { "ELDRITCHMAJOR" })) {
/* 311 */           ThaumcraftApi.internalMethods.completeResearch(player, "ELDRITCHMAJOR");
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void spawnMist(EntityPlayer player, int warp, int guardian) {
/* 319 */     PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((byte)1), (EntityPlayerMP)player);
/*     */ 
/*     */ 
/*     */     
/* 323 */     if (guardian > 0) {
/* 324 */       guardian = Math.min(8, guardian);
/* 325 */       for (int a = 0; a < guardian; a++)
/* 326 */         spawnGuardian(player); 
/*     */     } 
/* 328 */     player.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("warp.text.6")), true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void spawnPortal(EntityPlayer player) {
/* 333 */     EntityCultistPortalLesser eg = new EntityCultistPortalLesser(player.field_70170_p);
/* 334 */     int i = MathHelper.func_76128_c(player.field_70165_t);
/* 335 */     int j = MathHelper.func_76128_c(player.field_70163_u);
/* 336 */     int k = MathHelper.func_76128_c(player.field_70161_v);
/* 337 */     for (int l = 0; l < 50; l++) {
/*     */       
/* 339 */       int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 340 */       int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 341 */       int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */       
/* 343 */       eg.func_70107_b(i1 + 0.5D, j1 + 1.0D, k1 + 0.5D);
/*     */       
/* 345 */       if (player.field_70170_p.func_180495_p(new BlockPos(i1, j1 - 1, k1)).func_185914_p())
/*     */       {
/* 347 */         if (player.field_70170_p.func_72855_b(eg.func_174813_aQ()) && player.field_70170_p
/* 348 */           .func_184144_a(eg, eg.func_174813_aQ()).isEmpty() && 
/* 349 */           !player.field_70170_p.func_72953_d(eg.func_174813_aQ())) {
/*     */           
/* 351 */           eg.func_180482_a(player.field_70170_p.func_175649_E(new BlockPos(eg)), (IEntityLivingData)null);
/* 352 */           player.field_70170_p.func_72838_d(eg);
/* 353 */           player.func_146105_b(new TextComponentString("§5§o" + I18n.func_74838_a("warp.text.16")), true);
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void spawnGuardian(EntityPlayer player) {
/* 361 */     EntityEldritchGuardian eg = new EntityEldritchGuardian(player.field_70170_p);
/* 362 */     int i = MathHelper.func_76128_c(player.field_70165_t);
/* 363 */     int j = MathHelper.func_76128_c(player.field_70163_u);
/* 364 */     int k = MathHelper.func_76128_c(player.field_70161_v);
/* 365 */     for (int l = 0; l < 50; l++) {
/*     */       
/* 367 */       int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 368 */       int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 369 */       int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */       
/* 371 */       if (player.field_70170_p.func_180495_p(new BlockPos(i1, j1 - 1, k1)).func_185917_h()) {
/*     */         
/* 373 */         eg.func_70107_b(i1, j1, k1);
/*     */         
/* 375 */         if (player.field_70170_p.func_72855_b(eg.func_174813_aQ()) && player.field_70170_p
/* 376 */           .func_184144_a(eg, eg.func_174813_aQ()).isEmpty() && 
/* 377 */           !player.field_70170_p.func_72953_d(eg.func_174813_aQ())) {
/*     */           
/* 379 */           eg.func_70624_b(player);
/* 380 */           player.field_70170_p.func_72838_d(eg);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void suddenlySpiders(EntityPlayer player, int warp, boolean real) {
/* 389 */     int spawns = Math.min(50, warp);
/*     */     
/* 391 */     for (int a = 0; a < spawns; a++) {
/*     */       
/* 393 */       EntityMindSpider spider = new EntityMindSpider(player.field_70170_p);
/* 394 */       int i = MathHelper.func_76128_c(player.field_70165_t);
/* 395 */       int j = MathHelper.func_76128_c(player.field_70163_u);
/* 396 */       int k = MathHelper.func_76128_c(player.field_70161_v);
/* 397 */       boolean success = false;
/* 398 */       for (int l = 0; l < 50; l++) {
/*     */         
/* 400 */         int i1 = i + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 401 */         int j1 = j + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/* 402 */         int k1 = k + MathHelper.func_76136_a(player.field_70170_p.field_73012_v, 7, 24) * MathHelper.func_76136_a(player.field_70170_p.field_73012_v, -1, 1);
/*     */         
/* 404 */         if (player.field_70170_p.func_180495_p(new BlockPos(i1, j1 - 1, k1)).func_185917_h()) {
/*     */           
/* 406 */           spider.func_70107_b(i1, j1, k1);
/*     */           
/* 408 */           if (player.field_70170_p.func_72855_b(spider.func_174813_aQ()) && player.field_70170_p
/* 409 */             .func_184144_a(spider, spider.func_174813_aQ()).isEmpty() && 
/* 410 */             !player.field_70170_p.func_72953_d(spider.func_174813_aQ())) {
/*     */             
/* 412 */             success = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 417 */       if (success) {
/*     */         
/* 419 */         spider.func_70624_b(player);
/* 420 */         if (!real) {
/* 421 */           spider.setViewer(player.func_70005_c_());
/* 422 */           spider.setHarmless(true);
/*     */         } 
/*     */         
/* 425 */         player.field_70170_p.func_72838_d(spider);
/*     */       } 
/*     */     } 
/* 428 */     player.func_146105_b(new TextComponentString("§5§o" + 
/*     */           
/* 430 */           I18n.func_74838_a("warp.text.7")), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void checkDeathGaze(EntityPlayer player) {
/* 435 */     PotionEffect pe = player.func_70660_b(PotionDeathGaze.instance);
/* 436 */     if (pe == null)
/* 437 */       return;  int level = pe.func_76458_c();
/* 438 */     int range = Math.min(8 + level * 3, 24);
/* 439 */     List list = player.field_70170_p.func_72839_b(player, player
/* 440 */         .func_174813_aQ().func_72314_b(range, range, range));
/*     */     
/* 442 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 444 */       Entity entity = (Entity)list.get(i);
/* 445 */       if (entity.func_70067_L() && entity instanceof EntityLivingBase && ((EntityLivingBase)entity)
/*     */         
/* 447 */         .func_70089_S())
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 452 */         if (EntityUtils.isVisibleTo(0.75F, player, entity, range))
/*     */         {
/* 454 */           if (entity != null && player.func_70685_l(entity) && (
/* 455 */             !(entity instanceof EntityPlayer) || FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W()) && 
/* 456 */             !((EntityLivingBase)entity).func_70644_a(MobEffects.field_82731_v)) {
/* 457 */             ((EntityLivingBase)entity).func_70604_c(player);
/* 458 */             ((EntityLivingBase)entity).func_130011_c(player);
/* 459 */             if (entity instanceof EntityCreature) {
/* 460 */               ((EntityCreature)entity).func_70624_b(player);
/*     */             }
/* 462 */             ((EntityLivingBase)entity).func_70690_d(new PotionEffect(MobEffects.field_82731_v, 80));
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getWarpFromGear(EntityPlayer player) {
/* 472 */     int w = PlayerEvents.getFinalWarp(player.func_184614_ca(), player);
/*     */     
/* 474 */     for (int a = 0; a < 4; a++) {
/* 475 */       w += PlayerEvents.getFinalWarp((ItemStack)player.field_71071_by.field_70460_b.get(a), player);
/*     */     }
/*     */     
/* 478 */     IInventory baubles = BaublesApi.getBaubles(player);
/* 479 */     for (int a = 0; a < baubles.func_70302_i_(); a++) {
/* 480 */       w += PlayerEvents.getFinalWarp(baubles.func_70301_a(a), player);
/*     */     }
/*     */     
/* 483 */     return w;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\WarpEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */