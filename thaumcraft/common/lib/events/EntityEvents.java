/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModTainted;
/*     */ import thaumcraft.common.entities.monster.mods.ChampionModifier;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXShield;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
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
/*     */ @EventBusSubscriber
/*     */ public class EntityEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void itemExpire(ItemExpireEvent event) {
/*  79 */     if (event.getEntityItem().func_92059_d() != null && !event.getEntityItem().func_92059_d().func_190926_b() && event.getEntityItem().func_92059_d().func_77973_b() != null && event
/*  80 */       .getEntityItem().func_92059_d().func_77973_b() instanceof thaumcraft.common.items.consumables.ItemBathSalts) {
/*  81 */       BlockPos bp = new BlockPos(event.getEntityItem());
/*  82 */       IBlockState bs = (event.getEntityItem()).field_70170_p.func_180495_p(bp);
/*  83 */       if (bs.func_177230_c() == Blocks.field_150355_j && bs.func_177230_c().func_176201_c(bs) == 0) {
/*  84 */         (event.getEntityItem()).field_70170_p.func_175656_a(bp, BlocksTC.purifyingFluid.func_176223_P());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void livingTick(LivingEvent.LivingUpdateEvent event) {
/*  93 */     if (event.getEntity() instanceof EntityCreature && !(event.getEntity()).field_70128_L) {
/*  94 */       EntityCreature mob = (EntityCreature)event.getEntity();
/*  95 */       if (mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD) != null) {
/*  96 */         int t = (int)mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e();
/*     */         try {
/*  98 */           if (t >= 0 && (ChampionModifier.mods[t]).type == 0) {
/*  99 */             (ChampionModifier.mods[t]).effect.performEffect(mob, null, null, 0.0F);
/*     */           }
/* 101 */         } catch (Exception e) {
/* 102 */           e.printStackTrace();
/* 103 */           if (t >= ChampionModifier.mods.length) {
/* 104 */             mob.func_70106_y();
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
/*     */   @SubscribeEvent
/*     */   public static void entityHurt(LivingHurtEvent event) {
/* 128 */     if (event.getSource().func_76347_k() && event.getEntity() instanceof EntityPlayer && 
/* 129 */       ThaumcraftCapabilities.knowsResearchStrict((EntityPlayer)event.getEntity(), new String[] { "BASEAUROMANCY@2"
/* 130 */         }) && !ThaumcraftCapabilities.knowsResearch((EntityPlayer)event.getEntity(), new String[] { "f_onfire" })) {
/* 131 */       IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge((EntityPlayer)event.getEntity());
/* 132 */       knowledge.addResearch("f_onfire");
/* 133 */       knowledge.sync((EntityPlayerMP)event.getEntity());
/* 134 */       ((EntityPlayer)event.getEntity()).func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.onfire")), true);
/*     */     } 
/*     */     
/* 137 */     if (event.getSource().func_76364_f() != null && event.getEntity() instanceof EntityPlayer && 
/* 138 */       ThaumcraftCapabilities.knowsResearchStrict((EntityPlayer)event.getEntity(), new String[] { "FOCUSPROJECTILE@2" })) {
/*     */       
/* 140 */       IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge((EntityPlayer)event.getEntity());
/*     */       
/* 142 */       if (!ThaumcraftCapabilities.knowsResearch((EntityPlayer)event.getEntity(), new String[] { "f_arrow" }) && event
/* 143 */         .getSource().func_76364_f() instanceof net.minecraft.entity.projectile.EntityArrow) {
/* 144 */         knowledge.addResearch("f_arrow");
/* 145 */         knowledge.sync((EntityPlayerMP)event.getEntity());
/* 146 */         ((EntityPlayer)event.getEntity()).func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.projectile")), true);
/*     */       } 
/*     */       
/* 149 */       if (!ThaumcraftCapabilities.knowsResearch((EntityPlayer)event.getEntity(), new String[] { "f_fireball" }) && event
/* 150 */         .getSource().func_76364_f() instanceof net.minecraft.entity.projectile.EntityFireball) {
/* 151 */         knowledge.addResearch("f_fireball");
/* 152 */         knowledge.sync((EntityPlayerMP)event.getEntity());
/* 153 */         ((EntityPlayer)event.getEntity()).func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.projectile")), true);
/*     */       } 
/*     */       
/* 156 */       if (!ThaumcraftCapabilities.knowsResearch((EntityPlayer)event.getEntity(), new String[] { "f_spit" }) && event
/* 157 */         .getSource().func_76364_f() instanceof net.minecraft.entity.projectile.EntityLlamaSpit) {
/* 158 */         knowledge.addResearch("f_spit");
/* 159 */         knowledge.sync((EntityPlayerMP)event.getEntity());
/* 160 */         ((EntityPlayer)event.getEntity()).func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.projectile")), true);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     if (event.getSource().func_76346_g() != null && event.getSource().func_76346_g() instanceof EntityPlayer) {
/* 168 */       EntityPlayer leecher = (EntityPlayer)event.getSource().func_76346_g();
/* 169 */       ItemStack helm = (ItemStack)leecher.field_71071_by.field_70460_b.get(3);
/* 170 */       if (helm != null && !helm.func_190926_b() && helm.func_77973_b() instanceof thaumcraft.common.items.armor.ItemFortressArmor && 
/* 171 */         helm.func_77942_o() && helm
/* 172 */         .func_77978_p().func_74764_b("mask") && helm
/* 173 */         .func_77978_p().func_74762_e("mask") == 2 && leecher.field_70170_p.field_73012_v
/* 174 */         .nextFloat() < event.getAmount() / 12.0F) {
/* 175 */         leecher.func_70691_i(1.0F);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 180 */     if (event.getEntity() instanceof EntityPlayer) {
/*     */       
/* 182 */       EntityPlayer player = (EntityPlayer)event.getEntity();
/*     */ 
/*     */       
/* 185 */       if (event.getSource().func_76346_g() != null && event.getSource().func_76346_g() instanceof EntityLivingBase) {
/* 186 */         EntityLivingBase attacker = (EntityLivingBase)event.getSource().func_76346_g();
/* 187 */         ItemStack helm = (ItemStack)player.field_71071_by.field_70460_b.get(3);
/* 188 */         if (helm != null && !helm.func_190926_b() && helm.func_77973_b() instanceof thaumcraft.common.items.armor.ItemFortressArmor && 
/* 189 */           helm.func_77942_o() && helm
/* 190 */           .func_77978_p().func_74764_b("mask") && helm
/* 191 */           .func_77978_p().func_74762_e("mask") == 1 && player.field_70170_p.field_73012_v
/* 192 */           .nextFloat() < event.getAmount() / 10.0F) {
/* 193 */           try { attacker.func_70690_d(new PotionEffect(MobEffects.field_82731_v, 80)); } catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 198 */       int charge = (int)player.func_110139_bj();
/*     */       
/* 200 */       if (charge > 0 && PlayerEvents.runicInfo
/* 201 */         .containsKey(Integer.valueOf(player.func_145782_y())) && PlayerEvents.lastMaxCharge
/* 202 */         .containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 203 */         long time = System.currentTimeMillis();
/*     */         
/* 205 */         int target = -1;
/* 206 */         if (event.getSource().func_76346_g() != null) target = event.getSource().func_76346_g().func_145782_y(); 
/* 207 */         if (event.getSource() == DamageSource.field_76379_h) target = -2; 
/* 208 */         if (event.getSource() == DamageSource.field_82729_p) target = -3;
/*     */         
/* 210 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(event
/* 211 */               .getEntity().func_145782_y(), target), new NetworkRegistry.TargetPoint(
/* 212 */               (event.getEntity()).field_70170_p.field_73011_w.getDimension(), (event.getEntity()).field_70165_t, (event.getEntity()).field_70163_u, (event.getEntity()).field_70161_v, 32.0D));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 218 */       if (!(event.getEntityLiving()).field_70170_p.field_72995_K && event.getEntityLiving().func_110143_aJ() < 2.0F && 
/* 219 */         !event.getEntityLiving().func_70662_br() && !(event.getEntityLiving()).field_70128_L && 
/* 220 */         !(event.getEntityLiving() instanceof thaumcraft.common.entities.construct.EntityOwnedConstruct) && 
/* 221 */         !(event.getEntityLiving() instanceof thaumcraft.api.entities.ITaintedMob) && event
/* 222 */         .getEntityLiving().func_70644_a(PotionFluxTaint.instance) && event
/* 223 */         .getEntityLiving().func_70681_au().nextBoolean()) {
/* 224 */         EntityUtils.makeTainted(event.getEntityLiving());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 229 */       if (event.getEntity() instanceof EntityMob) {
/* 230 */         IAttributeInstance cai = ((EntityMob)event.getEntity()).func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD);
/* 231 */         if ((cai != null && cai.func_111126_e() >= 0.0D) || event.getEntity() instanceof thaumcraft.api.entities.IEldritchMob) {
/*     */           
/* 233 */           EntityMob mob = (EntityMob)event.getEntity();
/* 234 */           int t = (int)cai.func_111126_e();
/*     */           
/* 236 */           if ((t == 5 || event.getEntity() instanceof thaumcraft.api.entities.IEldritchMob) && mob.func_110139_bj() > 0.0F) {
/* 237 */             int target = -1;
/* 238 */             if (event.getSource().func_76346_g() != null) {
/* 239 */               target = event.getSource().func_76346_g().func_145782_y();
/*     */             }
/* 241 */             if (event.getSource() == DamageSource.field_76379_h) target = -2; 
/* 242 */             if (event.getSource() == DamageSource.field_82729_p) target = -3; 
/* 243 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(mob
/* 244 */                   .func_145782_y(), target), new NetworkRegistry.TargetPoint(
/* 245 */                   (event.getEntity()).field_70170_p.field_73011_w.getDimension(), (event.getEntity()).field_70165_t, (event.getEntity()).field_70163_u, (event.getEntity()).field_70161_v, 32.0D));
/* 246 */             event.getEntity().func_184185_a(SoundsTC.runicShieldCharge, 0.66F, 1.1F + (event.getEntity()).field_70170_p.field_73012_v.nextFloat() * 0.1F);
/*     */           
/*     */           }
/* 249 */           else if (t >= 0 && (ChampionModifier.mods[t]).type == 2 && event
/* 250 */             .getSource().func_76346_g() != null && event.getSource().func_76346_g() instanceof EntityLivingBase) {
/* 251 */             EntityLivingBase attacker = (EntityLivingBase)event.getSource().func_76346_g();
/* 252 */             event.setAmount((ChampionModifier.mods[t]).effect.performEffect(mob, attacker, event.getSource(), event.getAmount()));
/*     */           } 
/*     */         } 
/*     */         
/* 256 */         if (event.getAmount() > 0.0F && event.getSource().func_76346_g() != null && event.getEntity() instanceof EntityLivingBase && event
/* 257 */           .getSource().func_76346_g() instanceof EntityMob && ((EntityMob)event
/* 258 */           .getSource().func_76346_g()).func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() >= 0.0D) {
/* 259 */           EntityMob mob = (EntityMob)event.getSource().func_76346_g();
/* 260 */           int t = (int)mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e();
/* 261 */           if ((ChampionModifier.mods[t]).type == 1) {
/* 262 */             event.setAmount((ChampionModifier.mods[t]).effect.performEffect(mob, (EntityLivingBase)event.getEntity(), event.getSource(), event.getAmount()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void itemPickup(EntityItemPickupEvent event) {
/* 273 */     if (event.getEntityPlayer().func_70005_c_().startsWith("FakeThaumcraft")) {
/* 274 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void entityConstuct(EntityEvent.EntityConstructing event) {
/* 280 */     if (event.getEntity() instanceof EntityCreature && !(event.getEntity() instanceof thaumcraft.common.entities.construct.EntityOwnedConstruct)) {
/* 281 */       EntityCreature mob = (EntityCreature)event.getEntity();
/* 282 */       mob.func_110140_aT().func_111150_b(ThaumcraftApiHelper.CHAMPION_MOD).func_111128_a(-2.0D);
/* 283 */       mob.func_110140_aT().func_111150_b(ChampionModTainted.TAINTED_MOD).func_111128_a(0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void livingDrops(LivingDropsEvent event) {
/* 289 */     boolean fakeplayer = (event.getSource().func_76346_g() != null && event.getSource().func_76346_g() instanceof net.minecraftforge.common.util.FakePlayer);
/*     */ 
/*     */     
/* 292 */     if (!(event.getEntity()).field_70170_p.field_72995_K && event.isRecentlyHit() && !fakeplayer && event
/* 293 */       .getEntity() instanceof EntityMob && !(event.getEntity() instanceof thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss) && ((EntityMob)event
/* 294 */       .getEntity()).func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() >= 0.0D && ((EntityMob)event
/* 295 */       .getEntity()).func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() != 13.0D) {
/*     */       
/* 297 */       int i = 5 + (event.getEntity()).field_70170_p.field_73012_v.nextInt(3);
/* 298 */       while (i > 0) {
/*     */         
/* 300 */         int j = EntityXPOrb.func_70527_a(i);
/* 301 */         i -= j;
/* 302 */         (event.getEntity()).field_70170_p.func_72838_d(new EntityXPOrb((event.getEntity()).field_70170_p, (event.getEntity()).field_70165_t, (event.getEntity()).field_70163_u, (event.getEntity()).field_70161_v, j));
/*     */       } 
/*     */       
/* 305 */       int lb = Math.min(2, MathHelper.func_76141_d(((event.getEntity()).field_70170_p.field_73012_v.nextInt(9) + event.getLootingLevel()) / 5.0F));
/* 306 */       event.getDrops().add(new EntityItem((event.getEntity()).field_70170_p, 
/* 307 */             (event.getEntityLiving()).field_70165_t, 
/* 308 */             (event.getEntityLiving()).field_70163_u + event.getEntityLiving().func_70047_e(), 
/* 309 */             (event.getEntityLiving()).field_70161_v, new ItemStack(ItemsTC.lootBag, 1, lb)));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     if (event.getEntityLiving() instanceof net.minecraft.entity.monster.EntityZombie && 
/* 316 */       !(event.getEntityLiving() instanceof thaumcraft.common.entities.monster.EntityBrainyZombie) && 
/* 317 */       event.isRecentlyHit() && (event.getEntity()).field_70170_p.field_73012_v.nextInt(10) - event.getLootingLevel() < 1) {
/* 318 */       event.getDrops().add(new EntityItem((event.getEntity()).field_70170_p, 
/* 319 */             (event.getEntityLiving()).field_70165_t, 
/* 320 */             (event.getEntityLiving()).field_70163_u + event.getEntityLiving().func_70047_e(), 
/* 321 */             (event.getEntityLiving()).field_70161_v, new ItemStack(ItemsTC.brain)));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 326 */     if (event.getEntityLiving() instanceof thaumcraft.common.entities.monster.cult.EntityCultist && !fakeplayer && event
/* 327 */       .getSource().func_76346_g() != null && event.getSource().func_76346_g() instanceof EntityPlayer) {
/*     */       
/* 329 */       EntityPlayer p = (EntityPlayer)event.getSource().func_76346_g();
/*     */       
/* 331 */       int c = !ThaumcraftCapabilities.getKnowledge(p).isResearchKnown("!CrimsonCultist@2") ? 4 : 20;
/*     */       
/* 333 */       if (InventoryUtils.getPlayerSlotFor(p, new ItemStack(ItemsTC.curio, 1, 6)) >= 0) c = 50;
/*     */       
/* 335 */       if ((event.getEntity()).field_70170_p.field_73012_v.nextInt(c) == 0) {
/* 336 */         event.getDrops().add(new EntityItem((event.getEntity()).field_70170_p, 
/* 337 */               (event.getEntityLiving()).field_70165_t, 
/* 338 */               (event.getEntityLiving()).field_70163_u + event.getEntityLiving().func_70047_e(), 
/* 339 */               (event.getEntityLiving()).field_70161_v, new ItemStack(ItemsTC.curio, 1, 6)));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 344 */     if (event.getSource() == DamageSourceThaumcraft.dissolve) {
/* 345 */       AspectList aspects = AspectHelper.getEntityAspects(event.getEntityLiving());
/* 346 */       if (aspects != null && aspects.size() > 0) {
/* 347 */         Aspect[] al = aspects.getAspects();
/* 348 */         int q = MathHelper.func_76136_a((event.getEntity().func_130014_f_()).field_73012_v, 1, 1 + aspects.visSize() / 10);
/* 349 */         for (int a = 0; a < q; a++) {
/* 350 */           Aspect aspect = al[(event.getEntity().func_130014_f_()).field_73012_v.nextInt(al.length)];
/* 351 */           ItemStack stack = ThaumcraftApiHelper.makeCrystal(aspect);
/* 352 */           event.getDrops().add(new EntityItem((event.getEntity()).field_70170_p, 
/* 353 */                 (event.getEntityLiving()).field_70165_t, 
/* 354 */                 (event.getEntityLiving()).field_70163_u + event.getEntityLiving().func_70047_e(), 
/* 355 */                 (event.getEntityLiving()).field_70161_v, stack));
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
/*     */   @SubscribeEvent
/*     */   public static void entitySpawns(EntityJoinWorldEvent event) {
/* 369 */     if (!(event.getWorld()).field_72995_K) {
/* 370 */       if (event.getEntity() instanceof EntityCreature && ((EntityCreature)event
/* 371 */         .getEntity()).func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD) != null && ((EntityCreature)event
/* 372 */         .getEntity()).func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() == 13.0D) {
/* 373 */         IAttributeInstance modai = ((EntityCreature)event.getEntity()).func_110148_a(ChampionModTainted.TAINTED_MOD);
/* 374 */         modai.func_111124_b(new AttributeModifier(UUID.fromString("2cb22137-a9d8-4417-ae06-de0e70f11b4c"), "istainted", 1.0D, 0));
/* 375 */         modai.func_111121_a(new AttributeModifier(UUID.fromString("2cb22137-a9d8-4417-ae06-de0e70f11b4c"), "istainted", 0.0D, 0));
/*     */       } 
/*     */       
/* 378 */       if (event.getEntity() instanceof EntityMob) {
/* 379 */         EntityMob mob = (EntityMob)event.getEntity();
/*     */         
/* 381 */         if (mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD).func_111126_e() < -1.0D) {
/*     */           
/* 383 */           int c = (event.getWorld()).field_73012_v.nextInt(100);
/*     */           
/* 385 */           if (event.getWorld().func_175659_aa() == EnumDifficulty.EASY || !ModConfig.CONFIG_WORLD.allowChampionMobs) c += 2; 
/* 386 */           if (event.getWorld().func_175659_aa() == EnumDifficulty.HARD) c -= (ModConfig.CONFIG_WORLD.allowChampionMobs ? 2 : 0); 
/* 387 */           if ((event.getWorld()).field_73011_w.getDimension() == ModConfig.CONFIG_WORLD.dimensionOuterId) c -= 3; 
/* 388 */           Biome bg = mob.field_70170_p.func_180494_b(new BlockPos(mob));
/* 389 */           if (BiomeDictionary.hasType(bg, BiomeDictionary.Type.SPOOKY) || 
/* 390 */             BiomeDictionary.hasType(bg, BiomeDictionary.Type.NETHER) || 
/* 391 */             BiomeDictionary.hasType(bg, BiomeDictionary.Type.END)) c -= (ModConfig.CONFIG_WORLD.allowChampionMobs ? 2 : 1);
/*     */           
/* 393 */           if (isDangerousLocation(mob.field_70170_p, 
/* 394 */               MathHelper.func_76143_f(mob.field_70165_t), 
/* 395 */               MathHelper.func_76143_f(mob.field_70163_u), 
/* 396 */               MathHelper.func_76143_f(mob.field_70161_v))) {
/* 397 */             c -= (ModConfig.CONFIG_WORLD.allowChampionMobs ? 10 : 3);
/*     */           }
/*     */           
/* 400 */           int cc = 0;
/* 401 */           boolean whitelisted = false;
/* 402 */           for (Class clazz : ConfigEntities.championModWhitelist.keySet()) {
/* 403 */             if (clazz.isAssignableFrom(event.getEntity().getClass())) {
/* 404 */               whitelisted = true;
/* 405 */               if (ModConfig.CONFIG_WORLD.allowChampionMobs || event.getEntity() instanceof thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss) {
/* 406 */                 cc = Math.max(cc, ((Integer)ConfigEntities.championModWhitelist.get(clazz)).intValue() - 1);
/*     */               }
/*     */             } 
/*     */           } 
/* 410 */           c -= cc;
/*     */           
/* 412 */           if (whitelisted && c <= 0 && mob.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() >= 10.0D) {
/* 413 */             EntityUtils.makeChampion(mob, false);
/*     */           } else {
/* 415 */             IAttributeInstance modai = mob.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD);
/* 416 */             modai.func_111124_b(ChampionModifier.ATTRIBUTE_MOD_NONE);
/* 417 */             modai.func_111121_a(ChampionModifier.ATTRIBUTE_MOD_NONE);
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
/* 433 */   private static boolean isDangerousLocation(World world, int x, int y, int z) { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\EntityEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */