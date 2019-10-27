/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemEnchantedBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeManager;
/*     */ import net.minecraftforge.fml.common.event.FMLInterModComms;
/*     */ import net.minecraftforge.fml.common.registry.EntityEntry;
/*     */ import net.minecraftforge.fml.common.registry.EntityRegistry;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.monster.EntityPech;
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
/*     */ public class ConfigEntities
/*     */ {
/*     */   public static void initEntities(IForgeRegistry<EntityEntry> iForgeRegistry) {
/*  82 */     int id = 0;
/*     */     
/*  84 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "CultistPortalGreater"), thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater.class, "CultistPortalGreater", id++, Thaumcraft.instance, 64, 20, false, 6842578, 32896);
/*  85 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "CultistPortalLesser"), thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser.class, "CultistPortalLesser", id++, Thaumcraft.instance, 64, 20, false, 9438728, 6316242);
/*  86 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "FluxRift"), thaumcraft.common.entities.EntityFluxRift.class, "FluxRift", id++, Thaumcraft.instance, 64, 20, false);
/*     */ 
/*     */     
/*  89 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "SpecialItem"), thaumcraft.common.entities.EntitySpecialItem.class, "SpecialItem", id++, Thaumcraft.instance, 64, 20, true);
/*     */     
/*  91 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "FollowItem"), thaumcraft.common.entities.EntityFollowingItem.class, "FollowItem", id++, Thaumcraft.instance, 64, 20, false);
/*  92 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "FallingTaint"), thaumcraft.common.entities.EntityFallingTaint.class, "FallingTaint", id++, Thaumcraft.instance, 64, 3, true);
/*     */ 
/*     */     
/*  95 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Alumentum"), thaumcraft.common.entities.projectile.EntityAlumentum.class, "Alumentum", id++, Thaumcraft.instance, 64, 20, true);
/*  96 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "GolemDart"), thaumcraft.common.entities.projectile.EntityGolemDart.class, "GolemDart", id++, Thaumcraft.instance, 64, 20, false);
/*  97 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "EldritchOrb"), thaumcraft.common.entities.projectile.EntityEldritchOrb.class, "EldritchOrb", id++, Thaumcraft.instance, 64, 20, true);
/*  98 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "BottleTaint"), thaumcraft.common.entities.projectile.EntityBottleTaint.class, "BottleTaint", id++, Thaumcraft.instance, 64, 20, true);
/*  99 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "GolemOrb"), thaumcraft.common.entities.projectile.EntityGolemOrb.class, "GolemOrb", id++, Thaumcraft.instance, 64, 3, true);
/* 100 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Grapple"), thaumcraft.common.entities.projectile.EntityGrapple.class, "Grapple", id++, Thaumcraft.instance, 64, 20, true);
/* 101 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "CausalityCollapser"), thaumcraft.common.entities.projectile.EntityCausalityCollapser.class, "CausalityCollapser", id++, Thaumcraft.instance, 64, 20, true);
/*     */ 
/*     */     
/* 104 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "FocusProjectile"), thaumcraft.common.entities.projectile.EntityFocusProjectile.class, "FocusProjectile", id++, Thaumcraft.instance, 64, 20, true);
/* 105 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "FocusCloud"), thaumcraft.common.entities.projectile.EntityFocusCloud.class, "FocusCloud", id++, Thaumcraft.instance, 64, 20, true);
/* 106 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Focusmine"), thaumcraft.common.entities.projectile.EntityFocusMine.class, "Focusmine", id++, Thaumcraft.instance, 64, 20, true);
/*     */ 
/*     */     
/* 109 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TurretBasic"), thaumcraft.common.entities.construct.EntityTurretCrossbow.class, "TurretBasic", id++, Thaumcraft.instance, 64, 3, true);
/* 110 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TurretAdvanced"), thaumcraft.common.entities.construct.EntityTurretCrossbowAdvanced.class, "TurretAdvanced", id++, Thaumcraft.instance, 64, 3, true);
/* 111 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "ArcaneBore"), thaumcraft.common.entities.construct.EntityArcaneBore.class, "ArcaneBore", id++, Thaumcraft.instance, 64, 3, true);
/*     */     
/* 113 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Golem"), thaumcraft.common.golems.EntityThaumcraftGolem.class, "Golem", id++, Thaumcraft.instance, 64, 3, true);
/*     */ 
/*     */     
/* 116 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "EldritchWarden"), thaumcraft.common.entities.monster.boss.EntityEldritchWarden.class, "EldritchWarden", id++, Thaumcraft.instance, 64, 3, true, 6842578, 8421504);
/* 117 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "EldritchGolem"), thaumcraft.common.entities.monster.boss.EntityEldritchGolem.class, "EldritchGolem", id++, Thaumcraft.instance, 64, 3, true, 6842578, 8947848);
/* 118 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "CultistLeader"), thaumcraft.common.entities.monster.boss.EntityCultistLeader.class, "CultistLeader", id++, Thaumcraft.instance, 64, 3, true, 6842578, 9438728);
/* 119 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TaintacleGiant"), thaumcraft.common.entities.monster.boss.EntityTaintacleGiant.class, "TaintacleGiant", id++, Thaumcraft.instance, 96, 3, false, 6842578, 10618530);
/*     */ 
/*     */     
/* 122 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "BrainyZombie"), thaumcraft.common.entities.monster.EntityBrainyZombie.class, "BrainyZombie", id++, Thaumcraft.instance, 64, 3, true, -16129, -16744448);
/* 123 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "GiantBrainyZombie"), thaumcraft.common.entities.monster.EntityGiantBrainyZombie.class, "GiantBrainyZombie", id++, Thaumcraft.instance, 64, 3, true, -16129, -16760832);
/* 124 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Wisp"), thaumcraft.common.entities.monster.EntityWisp.class, "Wisp", id++, Thaumcraft.instance, 64, 3, false, -16129, -1);
/* 125 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Firebat"), thaumcraft.common.entities.monster.EntityFireBat.class, "Firebat", id++, Thaumcraft.instance, 64, 3, false, -16129, -806354944);
/* 126 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Spellbat"), thaumcraft.common.entities.monster.EntitySpellBat.class, "Spellbat", id++, Thaumcraft.instance, 64, 3, false, -16129, -806354944);
/* 127 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Pech"), EntityPech.class, "Pech", id++, Thaumcraft.instance, 64, 3, true, -16129, -12582848);
/* 128 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "MindSpider"), thaumcraft.common.entities.monster.EntityMindSpider.class, "MindSpider", id++, Thaumcraft.instance, 64, 3, true, 4996656, 4473924);
/* 129 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "EldritchGuardian"), thaumcraft.common.entities.monster.EntityEldritchGuardian.class, "EldritchGuardian", id++, Thaumcraft.instance, 64, 3, true, 8421504, 0);
/* 130 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "CultistKnight"), thaumcraft.common.entities.monster.cult.EntityCultistKnight.class, "CultistKnight", id++, Thaumcraft.instance, 64, 3, true, 9438728, 128);
/* 131 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "CultistCleric"), thaumcraft.common.entities.monster.cult.EntityCultistCleric.class, "CultistCleric", id++, Thaumcraft.instance, 64, 3, true, 9438728, 8388608);
/* 132 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "EldritchCrab"), thaumcraft.common.entities.monster.EntityEldritchCrab.class, "EldritchCrab", id++, Thaumcraft.instance, 64, 3, true, 8421504, 5570560);
/* 133 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "InhabitedZombie"), thaumcraft.common.entities.monster.EntityInhabitedZombie.class, "InhabitedZombie", id++, Thaumcraft.instance, 64, 3, true, 8421504, 5570560);
/*     */ 
/*     */     
/* 136 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "ThaumSlime"), thaumcraft.common.entities.monster.EntityThaumicSlime.class, "ThaumSlime", id++, Thaumcraft.instance, 64, 3, true, 10618530, -32513);
/* 137 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TaintCrawler"), thaumcraft.common.entities.monster.tainted.EntityTaintCrawler.class, "TaintCrawler", id++, Thaumcraft.instance, 64, 3, true, 10618530, 3158064);
/* 138 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "Taintacle"), thaumcraft.common.entities.monster.tainted.EntityTaintacle.class, "Taintacle", id++, Thaumcraft.instance, 64, 3, false, 10618530, 4469572);
/* 139 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TaintacleTiny"), thaumcraft.common.entities.monster.tainted.EntityTaintacleSmall.class, "TaintacleTiny", id++, Thaumcraft.instance, 64, 3, false);
/* 140 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TaintSwarm"), thaumcraft.common.entities.monster.tainted.EntityTaintSwarm.class, "TaintSwarm", id++, Thaumcraft.instance, 64, 3, false, 10618530, 16744576);
/* 141 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TaintSeed"), thaumcraft.common.entities.monster.tainted.EntityTaintSeed.class, "TaintSeed", id++, Thaumcraft.instance, 64, 20, false, 10618530, 4465237);
/* 142 */     EntityRegistry.registerModEntity(new ResourceLocation("thaumcraft", "TaintSeedPrime"), thaumcraft.common.entities.monster.tainted.EntityTaintSeedPrime.class, "TaintSeedPrime", id++, Thaumcraft.instance, 64, 20, false, 10618530, 5583718);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     EntityPech.valuedItems.put(Integer.valueOf(Item.func_150891_b(Items.field_151079_bi)), Integer.valueOf(15));
/*     */ 
/*     */     
/* 152 */     ArrayList<List> forInv = new ArrayList<List>();
/* 153 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 0) }));
/* 154 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 1) }));
/* 155 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 6) }));
/* 156 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 7) }));
/* 157 */     if (ModConfig.foundCopperIngot)
/* 158 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 2) })); 
/* 159 */     if (ModConfig.foundTinIngot)
/* 160 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 3) })); 
/* 161 */     if (ModConfig.foundSilverIngot)
/* 162 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 4) })); 
/* 163 */     if (ModConfig.foundLeadIngot)
/* 164 */       forInv.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack(ItemsTC.clusters, 1, 5) })); 
/* 165 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151072_bj) }));
/* 166 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(BlocksTC.saplingGreatwood) }));
/* 167 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_185157_bK) }));
/* 168 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 169 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 170 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 171 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.thaumiumPick) }));
/* 172 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.thaumiumAxe) }));
/* 173 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.thaumiumHoe) }));
/* 174 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 175 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(BlocksTC.saplingSilverwood) }));
/* 176 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.curio, 1, 4) }));
/*     */     
/* 178 */     EntityPech.tradeInventory.put(Integer.valueOf(0), forInv);
/*     */ 
/*     */     
/* 181 */     ArrayList<List> forMag = new ArrayList<List>();
/* 182 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), ThaumcraftApiHelper.makeCrystal(Aspect.AIR) }));
/* 183 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), ThaumcraftApiHelper.makeCrystal(Aspect.EARTH) }));
/* 184 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), ThaumcraftApiHelper.makeCrystal(Aspect.FIRE) }));
/* 185 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), ThaumcraftApiHelper.makeCrystal(Aspect.WATER) }));
/* 186 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), ThaumcraftApiHelper.makeCrystal(Aspect.ORDER) }));
/* 187 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(1), ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY) }));
/* 188 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8193) }));
/* 189 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151068_bn, 1, 8261) }));
/* 190 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(2), ThaumcraftApiHelper.makeCrystal(Aspect.FLUX) }));
/* 191 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 192 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 193 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), ThaumcraftApiHelper.makeCrystal(Aspect.AURA) }));
/* 194 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 195 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.clothBoots) }));
/* 196 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.clothChest) }));
/* 197 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.clothLegs) }));
/* 198 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 199 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.pechWand) }));
/* 200 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.curio, 1, 4) }));
/* 201 */     forMag.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.amuletVis, 1, 0) }));
/* 202 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(Items.field_190929_cY) }));
/* 203 */     EntityPech.tradeInventory.put(Integer.valueOf(1), forMag);
/*     */ 
/*     */     
/* 206 */     ArrayList<List> forArc = new ArrayList<List>();
/* 207 */     for (int a = 0; a < 15; a++) {
/* 208 */       forArc.add(Arrays.asList(new Object[] { Integer.valueOf(1), new ItemStack((Block)BlocksTC.candles.get(EnumDyeColor.func_176766_a(a))) }));
/* 209 */     }  forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151073_bk) }));
/* 210 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(2), new ItemStack(Items.field_151111_aL) }));
/* 211 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(2), ItemEnchantedBook.func_92111_a(new EnchantmentData(Enchantments.field_185309_u, 1)) }));
/* 212 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 213 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151062_by) }));
/* 214 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(3), new ItemStack(Items.field_151153_ao, 1, 0) }));
/* 215 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(ItemsTC.eldritchEye) }));
/* 216 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(Items.field_151153_ao, 1, 1) }));
/* 217 */     forInv.add(Arrays.asList(new Object[] { Integer.valueOf(4), new ItemStack(Items.field_185166_h) }));
/* 218 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.baubles, 1, 3) }));
/* 219 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), ItemEnchantedBook.func_92111_a(new EnchantmentData(Enchantments.field_185311_w, 1)) }));
/* 220 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), ItemEnchantedBook.func_92111_a(new EnchantmentData(Enchantments.field_185312_x, 1)) }));
/* 221 */     forArc.add(Arrays.asList(new Object[] { Integer.valueOf(5), new ItemStack(ItemsTC.curio, 1, 4) }));
/* 222 */     EntityPech.tradeInventory.put(Integer.valueOf(2), forArc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void postInitEntitySpawns() {
/* 229 */     biomes = new ArrayList(); UnmodifiableIterator unmodifiableIterator;
/* 230 */     for (unmodifiableIterator = BiomeManager.getBiomes(BiomeManager.BiomeType.WARM).iterator(); unmodifiableIterator.hasNext(); ) { BiomeManager.BiomeEntry be = (BiomeManager.BiomeEntry)unmodifiableIterator.next(); biomes.add(be.biome); }
/* 231 */      for (unmodifiableIterator = BiomeManager.getBiomes(BiomeManager.BiomeType.COOL).iterator(); unmodifiableIterator.hasNext(); ) { BiomeManager.BiomeEntry be = (BiomeManager.BiomeEntry)unmodifiableIterator.next(); biomes.add(be.biome); }
/* 232 */      for (unmodifiableIterator = BiomeManager.getBiomes(BiomeManager.BiomeType.ICY).iterator(); unmodifiableIterator.hasNext(); ) { BiomeManager.BiomeEntry be = (BiomeManager.BiomeEntry)unmodifiableIterator.next(); biomes.add(be.biome); }
/* 233 */      for (unmodifiableIterator = BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT).iterator(); unmodifiableIterator.hasNext(); ) { BiomeManager.BiomeEntry be = (BiomeManager.BiomeEntry)unmodifiableIterator.next(); biomes.add(be.biome); }
/*     */     
/* 235 */     Biome[] allBiomes = (Biome[])biomes.toArray(new Biome[] { null });
/*     */     
/* 237 */     if (ModConfig.CONFIG_WORLD.allowSpawnAngryZombie)
/* 238 */       for (Biome bgb : biomes) {
/* 239 */         if (bgb != null) if (((bgb.func_76747_a(EnumCreatureType.MONSTER) != null) ? 1 : 0) & (
/* 240 */             (bgb.func_76747_a(EnumCreatureType.MONSTER).size() > 0) ? 1 : 0)) {
/* 241 */             EntityRegistry.addSpawn(thaumcraft.common.entities.monster.EntityBrainyZombie.class, 10, 1, 1, EnumCreatureType.MONSTER, new Biome[] { bgb });
/*     */           }
/*     */       
/*     */       }  
/* 245 */     if (ModConfig.CONFIG_WORLD.allowSpawnPech)
/* 246 */       for (Biome bgb : BiomeDictionary.getBiomes(BiomeDictionary.Type.MAGICAL)) {
/* 247 */         if (bgb != null) if (((bgb.func_76747_a(EnumCreatureType.MONSTER) != null) ? 1 : 0) & (
/* 248 */             (bgb.func_76747_a(EnumCreatureType.MONSTER).size() > 0) ? 1 : 0)) {
/* 249 */             EntityRegistry.addSpawn(EntityPech.class, 10, 1, 1, EnumCreatureType.MONSTER, new Biome[] { bgb });
/*     */           }
/*     */       
/*     */       }  
/* 253 */     if (ModConfig.CONFIG_WORLD.allowSpawnFireBat) {
/* 254 */       EntityRegistry.addSpawn(thaumcraft.common.entities.monster.EntityFireBat.class, 10, 1, 2, EnumCreatureType.MONSTER, 
/* 255 */           (Biome[])BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
/*     */       
/* 257 */       Calendar calendar = Calendar.getInstance();
/* 258 */       calendar.setTimeInMillis(System.currentTimeMillis());
/* 259 */       if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
/* 260 */         EntityRegistry.addSpawn(thaumcraft.common.entities.monster.EntityFireBat.class, 5, 1, 2, EnumCreatureType.MONSTER, (Biome[])biomes
/* 261 */             .toArray(allBiomes));
/*     */       }
/*     */     } 
/* 264 */     if (ModConfig.CONFIG_WORLD.allowSpawnWisp) {
/* 265 */       EntityRegistry.addSpawn(thaumcraft.common.entities.monster.EntityWisp.class, 5, 1, 1, EnumCreatureType.MONSTER, 
/* 266 */           (Biome[])BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER).toArray(new Biome[0]));
/*     */     }
/*     */     
/* 269 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Zombie:0");
/* 270 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Spider:0");
/* 271 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Blaze:0");
/* 272 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Enderman:0");
/* 273 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Skeleton:0");
/* 274 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Witch:1");
/*     */     
/* 276 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.EldritchCrab:0");
/* 277 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.Taintacle:2");
/* 278 */     FMLInterModComms.sendMessage("Thaumcraft", "championWhiteList", "Thaumcraft.InhabitedZombie:3");
/*     */   }
/*     */ 
/*     */   
/* 282 */   public static HashMap<Class, Integer> championModWhitelist = new HashMap();
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ConfigEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */