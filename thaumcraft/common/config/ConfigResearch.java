/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.stats.StatisticsManagerServer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ScanBlock;
/*     */ import thaumcraft.api.research.ScanEntity;
/*     */ import thaumcraft.api.research.ScanItem;
/*     */ import thaumcraft.api.research.ScanMaterial;
/*     */ import thaumcraft.api.research.ScanOreDictionary;
/*     */ import thaumcraft.api.research.ScanningManager;
/*     */ import thaumcraft.api.research.theorycraft.AidBookshelf;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftManager;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.research.ScanEnchantment;
/*     */ import thaumcraft.common.lib.research.ScanGeneric;
/*     */ import thaumcraft.common.lib.research.ScanPotion;
/*     */ import thaumcraft.common.lib.research.ScanSky;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBasicAlchemy;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBasicArtifice;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBasicAuromancy;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBasicEldritch;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBasicGolemancy;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBasicInfusion;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBeacon;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidBrainInAJar;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidEnchantmentTable;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidGlyphedStone;
/*     */ import thaumcraft.common.lib.research.theorycraft.AidPortal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigResearch
/*     */ {
/*     */   public static void init() {
/* 120 */     initCategories();
/* 121 */     initScannables();
/* 122 */     initTheorycraft();
/* 123 */     initWarp();
/*     */     
/* 125 */     for (String cat : TCCategories) {
/* 126 */       ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumcraft", "research/" + cat.toLowerCase()));
/*     */     }
/* 128 */     ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumcraft", "research/scans"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public static void postInit() { ResearchManager.parseAllResearch(); }
/*     */ 
/*     */   
/* 138 */   public static String[] TCCategories = { "BASICS", "ALCHEMY", "AUROMANCY", "ARTIFICE", "INFUSION", "GOLEMANCY", "ELDRITCH" };
/* 139 */   private static final ResourceLocation BACK_OVER = new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png");
/*     */ 
/*     */   
/*     */   private static void initCategories() {
/* 143 */     ResearchCategories.registerCategory("BASICS", null, (new AspectList())
/* 144 */         .add(Aspect.PLANT, 5).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5).add(Aspect.FIRE, 5).add(Aspect.EARTH, 3).add(Aspect.WATER, 5), new ResourceLocation("thaumcraft", "textures/items/thaumonomicon_cheat.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"), BACK_OVER);
/*     */ 
/*     */ 
/*     */     
/* 148 */     ResearchCategories.registerCategory("AUROMANCY", "UNLOCKAUROMANCY", (new AspectList())
/* 149 */         .add(Aspect.AURA, 20).add(Aspect.MAGIC, 20).add(Aspect.FLUX, 15)
/* 150 */         .add(Aspect.CRYSTAL, 5).add(Aspect.COLD, 5).add(Aspect.AIR, 5), new ResourceLocation("thaumcraft", "textures/research/cat_auromancy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_2.jpg"), BACK_OVER);
/*     */ 
/*     */ 
/*     */     
/* 154 */     ResearchCategories.registerCategory("ALCHEMY", "UNLOCKALCHEMY", (new AspectList())
/* 155 */         .add(Aspect.ALCHEMY, 30).add(Aspect.FLUX, 10).add(Aspect.MAGIC, 10)
/* 156 */         .add(Aspect.LIFE, 5).add(Aspect.AVERSION, 5).add(Aspect.DESIRE, 5).add(Aspect.WATER, 5), new ResourceLocation("thaumcraft", "textures/research/cat_alchemy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_3.jpg"), BACK_OVER);
/*     */ 
/*     */ 
/*     */     
/* 160 */     ResearchCategories.registerCategory("ARTIFICE", "UNLOCKARTIFICE", (new AspectList())
/* 161 */         .add(Aspect.MECHANISM, 10).add(Aspect.CRAFT, 10).add(Aspect.METAL, 10).add(Aspect.TOOL, 10).add(Aspect.ENERGY, 10)
/* 162 */         .add(Aspect.LIGHT, 5).add(Aspect.FLIGHT, 5).add(Aspect.TRAP, 5).add(Aspect.FIRE, 5), new ResourceLocation("thaumcraft", "textures/research/cat_artifice.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_4.jpg"), BACK_OVER);
/*     */ 
/*     */ 
/*     */     
/* 166 */     ResearchCategories.registerCategory("INFUSION", "UNLOCKINFUSION", (new AspectList())
/* 167 */         .add(Aspect.MAGIC, 30).add(Aspect.PROTECT, 10).add(Aspect.TOOL, 10)
/* 168 */         .add(Aspect.FLUX, 5).add(Aspect.CRAFT, 5).add(Aspect.SOUL, 5).add(Aspect.EARTH, 3), new ResourceLocation("thaumcraft", "textures/research/cat_infusion.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_7.jpg"), BACK_OVER);
/*     */ 
/*     */ 
/*     */     
/* 172 */     ResearchCategories.registerCategory("GOLEMANCY", "UNLOCKGOLEMANCY", (new AspectList())
/* 173 */         .add(Aspect.MAN, 20).add(Aspect.MOTION, 10).add(Aspect.MIND, 10).add(Aspect.MECHANISM, 10)
/* 174 */         .add(Aspect.EXCHANGE, 5).add(Aspect.SENSES, 5).add(Aspect.BEAST, 5).add(Aspect.ORDER, 5), new ResourceLocation("thaumcraft", "textures/research/cat_golemancy.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_5.jpg"), BACK_OVER);
/*     */ 
/*     */ 
/*     */     
/* 178 */     ResearchCategories.registerCategory("ELDRITCH", "UNLOCKELDRITCH", (new AspectList())
/* 179 */         .add(Aspect.ELDRITCH, 20).add(Aspect.DARKNESS, 10).add(Aspect.MAGIC, 5).add(Aspect.MIND, 5)
/* 180 */         .add(Aspect.VOID, 5).add(Aspect.DEATH, 5).add(Aspect.UNDEAD, 5).add(Aspect.ENTROPY, 5), new ResourceLocation("thaumcraft", "textures/research/cat_eldritch.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_6.jpg"), BACK_OVER);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initScannables() {
/* 186 */     ScanningManager.addScannableThing(new ScanGeneric());
/*     */     
/* 188 */     for (ResourceLocation loc : Enchantment.field_185264_b.func_148742_b()) {
/* 189 */       Enchantment ench = (Enchantment)Enchantment.field_185264_b.func_82594_a(loc);
/* 190 */       ScanningManager.addScannableThing(new ScanEnchantment(ench));
/*     */     } 
/*     */     
/* 193 */     for (ResourceLocation loc : Potion.field_188414_b.func_148742_b()) {
/* 194 */       Potion pot = (Potion)Potion.field_188414_b.func_82594_a(loc);
/* 195 */       ScanningManager.addScannableThing(new ScanPotion(pot));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 200 */     ScanningManager.addScannableThing(new ScanEntity("!Wisp", thaumcraft.common.entities.monster.EntityWisp.class, true));
/* 201 */     ScanningManager.addScannableThing(new ScanEntity("!ThaumSlime", thaumcraft.common.entities.monster.EntityThaumicSlime.class, true));
/* 202 */     ScanningManager.addScannableThing(new ScanEntity("!Firebat", thaumcraft.common.entities.monster.EntityFireBat.class, true));
/* 203 */     ScanningManager.addScannableThing(new ScanEntity("!Pech", thaumcraft.common.entities.monster.EntityPech.class, true));
/*     */ 
/*     */     
/* 206 */     ScanningManager.addScannableThing(new ScanEntity("!BrainyZombie", thaumcraft.common.entities.monster.EntityBrainyZombie.class, true));
/* 207 */     ScanningManager.addScannableThing(new ScanEntity("!EldritchCrab", thaumcraft.common.entities.monster.EntityEldritchCrab.class, true));
/* 208 */     ScanningManager.addScannableThing(new ScanEntity("!EldritchCrab", thaumcraft.common.entities.monster.EntityInhabitedZombie.class, true));
/* 209 */     ScanningManager.addScannableThing(new ScanEntity("!CrimsonCultist", thaumcraft.common.entities.monster.cult.EntityCultist.class, true));
/* 210 */     ScanningManager.addScannableThing(new ScanEntity("!EldritchGuardian", thaumcraft.common.entities.monster.EntityEldritchGuardian.class, true));
/*     */     
/* 212 */     ScanningManager.addScannableThing(new ScanEntity("!TaintCrawler", thaumcraft.common.entities.monster.tainted.EntityTaintCrawler.class, true));
/* 213 */     ScanningManager.addScannableThing(new ScanEntity("!Taintacle", thaumcraft.common.entities.monster.tainted.EntityTaintacle.class, true));
/* 214 */     ScanningManager.addScannableThing(new ScanEntity("!TaintSeed", thaumcraft.common.entities.monster.tainted.EntityTaintSeed.class, true));
/* 215 */     ScanningManager.addScannableThing(new ScanEntity("!TaintSwarm", thaumcraft.common.entities.monster.tainted.EntityTaintSwarm.class, true));
/*     */     
/* 217 */     ScanningManager.addScannableThing(new ScanEntity("f_toomuchflux", thaumcraft.common.entities.EntityFluxRift.class, true));
/* 218 */     ScanningManager.addScannableThing(new ScanEntity("!FluxRift", thaumcraft.common.entities.EntityFluxRift.class, true));
/*     */     
/* 220 */     ScanningManager.addScannableThing(new ScanEntity("f_golem", net.minecraft.entity.monster.EntityGolem.class, true));
/* 221 */     ScanningManager.addScannableThing(new ScanEntity("f_golem", thaumcraft.common.entities.construct.EntityOwnedConstruct.class, true));
/*     */     
/* 223 */     ScanningManager.addScannableThing(new ScanEntity("f_SPIDER", net.minecraft.entity.monster.EntitySpider.class, true));
/* 224 */     ScanningManager.addScannableThing(new ScanEntity("f_BAT", net.minecraft.entity.passive.EntityBat.class, true));
/* 225 */     ScanningManager.addScannableThing(new ScanEntity("f_BAT", thaumcraft.common.entities.monster.EntityFireBat.class, true));
/* 226 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", net.minecraft.entity.passive.EntityBat.class, true));
/* 227 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", net.minecraft.entity.passive.EntityParrot.class, true));
/* 228 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", thaumcraft.common.entities.monster.EntityFireBat.class, true));
/* 229 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", thaumcraft.common.entities.monster.tainted.EntityTaintSwarm.class, true));
/* 230 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", thaumcraft.common.entities.monster.EntityWisp.class, true));
/* 231 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", net.minecraft.entity.monster.EntityGhast.class, true));
/* 232 */     ScanningManager.addScannableThing(new ScanEntity("f_FLY", net.minecraft.entity.monster.EntityBlaze.class, true));
/*     */     
/* 234 */     ScanningManager.addScannableThing(new ScanEntity("!ORMOB", thaumcraft.api.entities.IEldritchMob.class, true));
/* 235 */     ScanningManager.addScannableThing(new ScanEntity("!ORBOSS", thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss.class, true));
/* 236 */     ScanningManager.addScannableThing(new ScanBlock("!ORBLOCK1", new Block[] { BlocksTC.stoneAncient, BlocksTC.stoneAncientTile }));
/* 237 */     ScanningManager.addScannableThing(new ScanBlock("!ORBLOCK2", new Block[] { BlocksTC.stoneEldritchTile }));
/* 238 */     ScanningManager.addScannableThing(new ScanBlock("!ORBLOCK3", new Block[] { BlocksTC.stoneAncientGlyphed }));
/*     */     
/* 240 */     ScanningManager.addScannableThing(new ScanBlock("ORE", new Block[] { BlocksTC.oreAmber, BlocksTC.oreCinnabar, BlocksTC.crystalAir, BlocksTC.crystalFire, BlocksTC.crystalWater, BlocksTC.crystalEarth, BlocksTC.crystalOrder, BlocksTC.crystalEntropy, BlocksTC.crystalTaint }));
/*     */     
/* 242 */     ScanningManager.addScannableThing(new ScanBlock("!OREAMBER", new Block[] { BlocksTC.oreAmber }));
/* 243 */     ScanningManager.addScannableThing(new ScanBlock("!ORECINNABAR", new Block[] { BlocksTC.oreCinnabar }));
/* 244 */     ScanningManager.addScannableThing(new ScanBlock("!ORECRYSTAL", new Block[] { BlocksTC.crystalAir, BlocksTC.crystalFire, BlocksTC.crystalWater, BlocksTC.crystalEarth, BlocksTC.crystalOrder, BlocksTC.crystalEntropy, BlocksTC.crystalTaint }));
/*     */ 
/*     */     
/* 247 */     ScanningManager.addScannableThing(new ScanBlock("PLANTS", new Block[] { BlocksTC.logGreatwood, BlocksTC.logSilverwood, BlocksTC.saplingGreatwood, BlocksTC.saplingSilverwood, BlocksTC.cinderpearl, BlocksTC.shimmerleaf, BlocksTC.vishroom }));
/*     */     
/* 249 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTWOOD", new Block[] { BlocksTC.logGreatwood }));
/* 250 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTWOOD", new Block[] { BlocksTC.logSilverwood }));
/* 251 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTWOOD", new Block[] { BlocksTC.saplingGreatwood }));
/* 252 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTWOOD", new Block[] { BlocksTC.saplingSilverwood }));
/* 253 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTCINDERPEARL", new Block[] { BlocksTC.cinderpearl }));
/* 254 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTSHIMMERLEAF", new Block[] { BlocksTC.shimmerleaf }));
/* 255 */     ScanningManager.addScannableThing(new ScanBlock("!PLANTVISHROOM", new Block[] { BlocksTC.vishroom }));
/*     */     
/* 257 */     ScanningManager.addScannableThing(new ScanItem("PRIMPEARL", new ItemStack(ItemsTC.primordialPearl, 1, 32767)));
/* 258 */     ScanningManager.addScannableThing(new ScanItem("!DRAGONBREATH", new ItemStack(Items.field_185157_bK)));
/* 259 */     ScanningManager.addScannableThing(new ScanItem("!TOTEMUNDYING", new ItemStack(Items.field_190929_cY)));
/*     */ 
/*     */     
/* 262 */     ScanningManager.addScannableThing(new ScanBlock("f_TELEPORT", new Block[] { Blocks.field_150427_aO, Blocks.field_150384_bq, Blocks.field_150378_br }));
/* 263 */     ScanningManager.addScannableThing(new ScanItem("f_TELEPORT", new ItemStack(Items.field_151079_bi)));
/* 264 */     ScanningManager.addScannableThing(new ScanEntity("f_TELEPORT", net.minecraft.entity.monster.EntityEnderman.class, true));
/*     */     
/* 266 */     ScanningManager.addScannableThing(new ScanEntity("f_BRAIN", thaumcraft.common.entities.monster.EntityBrainyZombie.class, true));
/* 267 */     ScanningManager.addScannableThing(new ScanItem("f_BRAIN", new ItemStack(ItemsTC.brain)));
/*     */     
/* 269 */     ScanningManager.addScannableThing(new ScanBlock("f_DISPENSER", new Block[] { Blocks.field_150367_z }));
/* 270 */     ScanningManager.addScannableThing(new ScanItem("f_DISPENSER", new ItemStack(Blocks.field_150367_z)));
/*     */     
/* 272 */     ScanningManager.addScannableThing(new ScanItem("f_MATCLAY", new ItemStack(Items.field_151119_aD)));
/* 273 */     ScanningManager.addScannableThing(new ScanBlock("f_MATCLAY", new Block[] { Blocks.field_150405_ch, Blocks.field_150406_ce }));
/* 274 */     ScanningManager.addScannableThing(new ScanMaterial("f_MATCLAY", new Material[] { Material.field_151571_B }));
/*     */     
/* 276 */     ScanningManager.addScannableThing(new ScanOreDictionary("f_MATIRON", new String[] { "oreIron", "ingotIron", "blockIron", "plateIron" }));
/* 277 */     ScanningManager.addScannableThing(new ScanOreDictionary("f_MATBRASS", new String[] { "ingotBrass", "blockBrass", "plateBrass" }));
/* 278 */     ScanningManager.addScannableThing(new ScanOreDictionary("f_MATTHAUMIUM", new String[] { "ingotThaumium", "blockThaumium", "plateThaumium" }));
/* 279 */     ScanningManager.addScannableThing(new ScanOreDictionary("f_MATVOID", new String[] { "ingotVoid", "blockVoid", "plateVoid" }));
/*     */     
/* 281 */     ScanningManager.addScannableThing(new ScanEntity("f_arrow", net.minecraft.entity.projectile.EntityArrow.class, true));
/* 282 */     ScanningManager.addScannableThing(new ScanItem("f_arrow", new ItemStack(Items.field_151032_g)));
/* 283 */     ScanningManager.addScannableThing(new ScanEntity("f_fireball", net.minecraft.entity.projectile.EntityFireball.class, true));
/* 284 */     ScanningManager.addScannableThing(new ScanEntity("f_spit", net.minecraft.entity.projectile.EntityLlamaSpit.class, true));
/*     */     
/* 286 */     ScanningManager.addScannableThing(new ScanItem("!Pechwand", new ItemStack(ItemsTC.pechWand)));
/*     */     
/* 288 */     ScanningManager.addScannableThing(new ScanItem("f_VOIDSEED", new ItemStack(ItemsTC.voidSeed)));
/*     */     
/* 290 */     ScanningManager.addScannableThing(new ScanSky());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initTheorycraft() {
/* 297 */     TheorycraftManager.registerAid(new AidBookshelf());
/* 298 */     TheorycraftManager.registerAid(new AidBrainInAJar());
/* 299 */     TheorycraftManager.registerAid(new AidGlyphedStone());
/* 300 */     TheorycraftManager.registerAid(new AidPortal.AidPortalEnd());
/* 301 */     TheorycraftManager.registerAid(new AidPortal.AidPortalNether());
/* 302 */     TheorycraftManager.registerAid(new AidPortal.AidPortalCrimson());
/* 303 */     TheorycraftManager.registerAid(new AidBasicAlchemy());
/* 304 */     TheorycraftManager.registerAid(new AidBasicArtifice());
/* 305 */     TheorycraftManager.registerAid(new AidBasicInfusion());
/* 306 */     TheorycraftManager.registerAid(new AidBasicAuromancy());
/* 307 */     TheorycraftManager.registerAid(new AidBasicGolemancy());
/* 308 */     TheorycraftManager.registerAid(new AidBasicEldritch());
/* 309 */     TheorycraftManager.registerAid(new AidEnchantmentTable());
/* 310 */     TheorycraftManager.registerAid(new AidBeacon());
/*     */ 
/*     */     
/* 313 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardStudy.class);
/* 314 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardAnalyze.class);
/* 315 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardBalance.class);
/* 316 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardNotation.class);
/* 317 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardPonder.class);
/* 318 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardRethink.class);
/* 319 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardReject.class);
/* 320 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardExperimentation.class);
/* 321 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardCurio.class);
/* 322 */     TheorycraftManager.registerCard(thaumcraft.api.research.theorycraft.CardInspired.class);
/* 323 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardEnchantment.class);
/* 324 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardBeacon.class);
/* 325 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardCelestial.class);
/*     */ 
/*     */     
/* 328 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardConcentrate.class);
/* 329 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardReactions.class);
/* 330 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardSynthesis.class);
/*     */ 
/*     */     
/* 333 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardCalibrate.class);
/* 334 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardMindOverMatter.class);
/* 335 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardTinker.class);
/*     */ 
/*     */     
/* 338 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardMeasure.class);
/* 339 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardChannel.class);
/* 340 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardInfuse.class);
/*     */ 
/*     */     
/* 343 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardFocus.class);
/* 344 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardAwareness.class);
/* 345 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardSpellbinding.class);
/*     */ 
/*     */     
/* 348 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardSculpting.class);
/* 349 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardScripting.class);
/* 350 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardSynergy.class);
/*     */ 
/*     */     
/* 353 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardDarkWhispers.class);
/* 354 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardGlyphs.class);
/* 355 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardPortal.class);
/* 356 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardRevelation.class);
/* 357 */     TheorycraftManager.registerCard(thaumcraft.common.lib.research.theorycraft.CardRealization.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   private static void initWarp() { ThaumcraftApi.addWarpToItem(new ItemStack(BlocksTC.jarBrain), 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initGolemancyResearch() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void initEldritchResearch() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkPeriodicStuff(EntityPlayer player) {
/* 444 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/* 445 */     Biome biome = player.field_70170_p.func_180494_b(player.func_180425_c());
/*     */ 
/*     */     
/* 448 */     if (!knowledge.isResearchKnown("m_hellandback") && 
/* 449 */       BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER)) {
/* 450 */       knowledge.addResearch("m_hellandback");
/* 451 */       knowledge.sync((EntityPlayerMP)player);
/* 452 */       player.func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.hellandback")), true);
/*     */     } 
/*     */     
/* 455 */     if (!knowledge.isResearchKnown("m_endoftheworld") && 
/* 456 */       BiomeDictionary.hasType(biome, BiomeDictionary.Type.END)) {
/* 457 */       knowledge.addResearch("m_endoftheworld");
/* 458 */       knowledge.sync((EntityPlayerMP)player);
/* 459 */       player.func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.endoftheworld")), true);
/*     */     } 
/*     */     
/* 462 */     if (knowledge.isResearchKnown("UNLOCKAUROMANCY@1") && !knowledge.isResearchKnown("UNLOCKAUROMANCY@2")) {
/* 463 */       if (player.field_70163_u < 10.0D && !knowledge.isResearchKnown("m_deepdown")) {
/* 464 */         knowledge.addResearch("m_deepdown");
/* 465 */         knowledge.sync((EntityPlayerMP)player);
/* 466 */         player.func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.deepdown")), true);
/*     */       } 
/*     */       
/* 469 */       if (player.field_70163_u > player.func_130014_f_().func_72940_L() * 0.4D && !knowledge.isResearchKnown("m_uphigh")) {
/* 470 */         knowledge.addResearch("m_uphigh");
/* 471 */         knowledge.sync((EntityPlayerMP)player);
/* 472 */         player.func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.uphigh")), true);
/*     */       } 
/*     */     } 
/*     */     
/* 476 */     StatisticsManagerServer sms = player.func_184102_h().func_184103_al().func_152602_a(player);
/* 477 */     if (sms != null) {
/* 478 */       if (!knowledge.isResearchKnown("m_walker") && sms.func_77444_a(StatList.field_188100_j) > 160000) {
/* 479 */         knowledge.addResearch("m_walker");
/* 480 */         knowledge.sync((EntityPlayerMP)player);
/*     */       } 
/* 482 */       if (!knowledge.isResearchKnown("m_runner") && sms.func_77444_a(StatList.field_188102_l) > 80000) {
/* 483 */         knowledge.addResearch("m_runner");
/* 484 */         knowledge.sync((EntityPlayerMP)player);
/*     */       } 
/* 486 */       if (!knowledge.isResearchKnown("m_jumper") && sms.func_77444_a(StatList.field_75953_u) > 500) {
/* 487 */         knowledge.addResearch("m_jumper");
/* 488 */         knowledge.sync((EntityPlayerMP)player);
/*     */       } 
/* 490 */       if (!knowledge.isResearchKnown("m_swimmer") && sms.func_77444_a(StatList.field_75946_m) > 8000) {
/* 491 */         knowledge.addResearch("m_swimmer");
/* 492 */         knowledge.sync((EntityPlayerMP)player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ConfigResearch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */