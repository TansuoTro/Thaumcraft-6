/*     */ package thaumcraft;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeManager;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.registry.EntityEntry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.api.potions.PotionVisExhaust;
/*     */ import thaumcraft.common.config.ConfigBlocks;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ConfigRecipes;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.potions.PotionBlurredVision;
/*     */ import thaumcraft.common.lib.potions.PotionDeathGaze;
/*     */ import thaumcraft.common.lib.potions.PotionInfectiousVisExhaust;
/*     */ import thaumcraft.common.lib.potions.PotionSunScorned;
/*     */ import thaumcraft.common.lib.potions.PotionThaumarhia;
/*     */ import thaumcraft.common.lib.potions.PotionUnnaturalHunger;
/*     */ import thaumcraft.common.lib.potions.PotionWarpWard;
/*     */ import thaumcraft.common.world.biomes.BiomeGenEerie;
/*     */ import thaumcraft.common.world.biomes.BiomeGenEldritch;
/*     */ import thaumcraft.common.world.biomes.BiomeGenMagicalForest;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ import thaumcraft.proxies.ProxyBlock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public final class Registrar
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void registerBlocks(RegistryEvent.Register<Block> event) {
/*  48 */     ConfigBlocks.initBlocks(event.getRegistry());
/*  49 */     ConfigBlocks.initTileEntities();
/*  50 */     ConfigBlocks.initMisc();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*  57 */   public static void registerBlocksClient(RegistryEvent.Register<Block> event) { ProxyBlock.setupBlocksClient(event.getRegistry()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerItems(RegistryEvent.Register<Item> event) {
/*  64 */     ConfigItems.preInitSeals();
/*  65 */     ConfigItems.initItems(event.getRegistry());
/*  66 */     ConfigItems.initMisc();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent(priority = EventPriority.LOWEST)
/*  73 */   public static void registerItemsClient(RegistryEvent.Register<Item> event) { ConfigItems.initModelsAndVariants(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*  79 */   public static void registerEntities(RegistryEvent.Register<EntityEntry> event) { ConfigEntities.initEntities(event.getRegistry()); }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerVanillaRecipes(RegistryEvent.Register<IRecipe> event) {
/*  85 */     ModConfig.modCompatibility();
/*     */     
/*  87 */     ConfigRecipes.initializeNormalRecipes(event.getRegistry());
/*  88 */     ConfigRecipes.initializeArcaneRecipes(event.getRegistry());
/*     */     
/*  90 */     ConfigRecipes.initializeInfusionRecipes();
/*  91 */     ConfigRecipes.initializeAlchemyRecipes();
/*  92 */     ConfigRecipes.initializeCompoundRecipes();
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
/*     */   @SubscribeEvent
/*     */   public static void registerPotions(RegistryEvent.Register<Potion> event) {
/* 106 */     PotionFluxTaint.instance = (Potion)(new PotionFluxTaint(true, 6697847)).setRegistryName("fluxTaint");
/* 107 */     PotionVisExhaust.instance = (Potion)(new PotionVisExhaust(true, 6702199)).setRegistryName("visExhaust");
/* 108 */     PotionInfectiousVisExhaust.instance = (Potion)(new PotionInfectiousVisExhaust(true, 6706551)).setRegistryName("infectiousVisExhaust");
/* 109 */     PotionUnnaturalHunger.instance = (Potion)(new PotionUnnaturalHunger(true, 4482611)).setRegistryName("unnaturalHunger");
/* 110 */     PotionWarpWard.instance = (Potion)(new PotionWarpWard(false, 14742263)).setRegistryName("warpWard");
/* 111 */     PotionDeathGaze.instance = (Potion)(new PotionDeathGaze(true, 6702131)).setRegistryName("deathGaze");
/* 112 */     PotionBlurredVision.instance = (Potion)(new PotionBlurredVision(true, 8421504)).setRegistryName("blurredVision");
/* 113 */     PotionSunScorned.instance = (Potion)(new PotionSunScorned(true, 16308330)).setRegistryName("sunScorned");
/* 114 */     PotionThaumarhia.instance = (Potion)(new PotionThaumarhia(true, 6702199)).setRegistryName("thaumarhia");
/*     */     
/* 116 */     event.getRegistry().register(PotionFluxTaint.instance);
/* 117 */     event.getRegistry().register(PotionVisExhaust.instance);
/* 118 */     event.getRegistry().register(PotionInfectiousVisExhaust.instance);
/* 119 */     event.getRegistry().register(PotionUnnaturalHunger.instance);
/* 120 */     event.getRegistry().register(PotionWarpWard.instance);
/* 121 */     event.getRegistry().register(PotionDeathGaze.instance);
/* 122 */     event.getRegistry().register(PotionBlurredVision.instance);
/* 123 */     event.getRegistry().register(PotionSunScorned.instance);
/* 124 */     event.getRegistry().register(PotionThaumarhia.instance);
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
/*     */   @SubscribeEvent
/*     */   public static void registerBiomes(RegistryEvent.Register<Biome> event) {
/* 148 */     BiomeHandler.MAGICAL_FOREST = new BiomeGenMagicalForest((new Biome.BiomeProperties("Magical Forest")).func_185398_c(0.2F).func_185400_d(0.3F).func_185410_a(0.8F).func_185395_b(0.4F));
/* 149 */     event.getRegistry().register(BiomeHandler.MAGICAL_FOREST);
/*     */     
/* 151 */     BiomeHandler.EERIE = new BiomeGenEerie((new Biome.BiomeProperties("Eerie")).func_185398_c(0.125F).func_185400_d(0.4F).func_185410_a(0.8F).func_185396_a());
/* 152 */     event.getRegistry().register(BiomeHandler.EERIE);
/*     */     
/* 154 */     BiomeHandler.ELDRITCH = new BiomeGenEldritch((new Biome.BiomeProperties("Outer Lands")).func_185398_c(0.125F).func_185400_d(0.15F).func_185410_a(0.8F).func_185395_b(0.2F));
/* 155 */     event.getRegistry().register(BiomeHandler.ELDRITCH);
/*     */     
/* 157 */     BiomeHandler.registerBiomes();
/*     */     
/* 159 */     if (ModConfig.CONFIG_WORLD.generateMagicForest) {
/* 160 */       BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BiomeHandler.MAGICAL_FOREST, ModConfig.CONFIG_WORLD.biomeMagicalForestWeight));
/* 161 */       BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BiomeHandler.MAGICAL_FOREST, ModConfig.CONFIG_WORLD.biomeMagicalForestWeight));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
/* 169 */     SoundsTC.registerSounds(event);
/* 170 */     SoundsTC.registerSoundTypes();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\Registrar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */