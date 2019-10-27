/*     */ package thaumcraft.proxies;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.config.Config;
/*     */ import net.minecraftforge.common.config.ConfigManager;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLInterModComms;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*     */ import net.minecraftforge.fml.common.network.IGuiHandler;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigAspects;
/*     */ import thaumcraft.common.config.ConfigEntities;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ConfigRecipes;
/*     */ import thaumcraft.common.config.ConfigResearch;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.lib.BehaviorDispenseAlumetum;
/*     */ import thaumcraft.common.lib.InternalMethodHandler;
/*     */ import thaumcraft.common.lib.capabilities.PlayerKnowledge;
/*     */ import thaumcraft.common.lib.capabilities.PlayerWarp;
/*     */ import thaumcraft.common.lib.events.CraftingEvents;
/*     */ import thaumcraft.common.lib.events.WorldEvents;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ 
/*     */ public class CommonProxy
/*     */   implements IGuiHandler, IProxy
/*     */ {
/*  47 */   ProxyGUI proxyGUI = new ProxyGUI();
/*     */ 
/*     */   
/*     */   public void preInit(FMLPreInitializationEvent event) {
/*  51 */     (event.getModMetadata()).version = "6.1.BETA26";
/*  52 */     Thaumcraft.instance.modDir = event.getModConfigurationDirectory();
/*  53 */     ThaumcraftApi.internalMethods = new InternalMethodHandler();
/*  54 */     PlayerKnowledge.preInit();
/*  55 */     PlayerWarp.preInit();
/*  56 */     PacketHandler.preInit();
/*  57 */     MinecraftForge.TERRAIN_GEN_BUS.register(WorldEvents.INSTANCE);
/*  58 */     GameRegistry.registerFuelHandler(new CraftingEvents());
/*  59 */     GameRegistry.registerWorldGenerator(ThaumcraftWorldGenerator.INSTANCE, 0);
/*  60 */     MinecraftForge.EVENT_BUS.register(Thaumcraft.instance);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(FMLInitializationEvent event) {
/*  66 */     ConfigItems.init();
/*  67 */     BlockDispenser.field_149943_a.func_82595_a(ItemsTC.alumentum, new BehaviorDispenseAlumetum());
/*  68 */     NetworkRegistry.INSTANCE.registerGuiHandler(Thaumcraft.instance, this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     ConfigResearch.init();
/*  74 */     ConfigManager.sync("thaumcraft", Config.Type.INSTANCE);
/*     */     
/*  76 */     ConfigRecipes.initializeSmelting();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postInit(FMLPostInitializationEvent event) {
/*  81 */     ConfigEntities.postInitEntitySpawns();
/*  82 */     ConfigAspects.postInit();
/*  83 */     ConfigRecipes.postAspects();
/*  84 */     ModConfig.postInitLoot();
/*  85 */     ModConfig.postInitMisc();
/*  86 */     ConfigRecipes.compileGroups();
/*  87 */     ConfigResearch.postInit();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { return this.proxyGUI.getClientGuiElement(ID, player, world, x, y, z); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { return this.proxyGUI.getServerGuiElement(ID, player, world, x, y, z); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public boolean isShiftKeyDown() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public World getClientWorld() { return null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerModel(ItemBlock itemBlock) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkInterModComs(FMLInterModComms.IMCEvent event) {
/* 117 */     for (UnmodifiableIterator unmodifiableIterator = event.getMessages().iterator(); unmodifiableIterator.hasNext(); ) { FMLInterModComms.IMCMessage message = (FMLInterModComms.IMCMessage)unmodifiableIterator.next();
/* 118 */       if (message.key.equals("portableHoleBlacklist") && message.isStringMessage()) {
/* 119 */         BlockUtils.portableHoleBlackList.add(message.getStringValue());
/*     */       }
/* 121 */       if (message.key.equals("harvestStandardCrop") && message.isItemStackMessage()) {
/* 122 */         ItemStack crop = message.getItemStackValue();
/* 123 */         CropUtils.addStandardCrop(crop, crop.func_77952_i());
/*     */       } 
/* 125 */       if (message.key.equals("harvestClickableCrop") && message.isItemStackMessage()) {
/* 126 */         ItemStack crop = message.getItemStackValue();
/* 127 */         CropUtils.addClickableCrop(crop, crop.func_77952_i());
/*     */       } 
/* 129 */       if (message.key.equals("harvestStackedCrop") && message.isItemStackMessage()) {
/* 130 */         ItemStack crop = message.getItemStackValue();
/* 131 */         CropUtils.addStackedCrop(crop, crop.func_77952_i());
/*     */       } 
/*     */       
/* 134 */       if (message.key.equals("nativeCluster") && message.isStringMessage()) {
/* 135 */         String[] t = message.getStringValue().split(",");
/* 136 */         if (t != null && t.length == 5) {
/*     */           try {
/* 138 */             ItemStack ore = new ItemStack(Item.func_150899_d(Integer.parseInt(t[0])), 1, Integer.parseInt(t[1]));
/* 139 */             ItemStack cluster = new ItemStack(Item.func_150899_d(Integer.parseInt(t[2])), 1, Integer.parseInt(t[3]));
/* 140 */             Utils.addSpecialMiningResult(ore, cluster, Float.parseFloat(t[4]));
/*     */           }
/* 142 */           catch (Exception exception) {}
/*     */         }
/*     */       } 
/* 145 */       if (message.key.equals("lampBlacklist") && message.isItemStackMessage()) {
/* 146 */         ItemStack crop = message.getItemStackValue();
/* 147 */         CropUtils.blacklistLamp(crop, crop.func_77952_i());
/*     */       } 
/* 149 */       if (message.key.equals("dimensionBlacklist") && message.isStringMessage()) {
/* 150 */         String[] t = message.getStringValue().split(":");
/* 151 */         if (t != null && t.length == 2) {
/*     */           try {
/* 153 */             BiomeHandler.addDimBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
/* 154 */           } catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */       
/* 158 */       if (message.key.equals("biomeBlacklist") && message.isStringMessage()) {
/* 159 */         String[] t = message.getStringValue().split(":");
/* 160 */         if (t != null && t.length == 2 && Biome.func_150568_d(Integer.parseInt(t[false])) != null) {
/*     */           try {
/* 162 */             BiomeHandler.addBiomeBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
/* 163 */           } catch (Exception exception) {}
/*     */         }
/*     */       } 
/*     */       
/* 167 */       if (message.key.equals("championWhiteList") && message.isStringMessage()) {
/*     */         
/*     */         try {
/* 170 */           String[] t = message.getStringValue().split(":");
/* 171 */           Class oclass = EntityList.func_192839_a(t[0]);
/* 172 */           if (oclass != null)
/*     */           {
/*     */             
/* 175 */             ConfigEntities.championModWhitelist.put(oclass, Integer.valueOf(Integer.parseInt(t[1])));
/*     */           }
/* 177 */         } catch (Exception e) {
/* 178 */           Thaumcraft.log.error("Failed to Whitelist [" + message.getStringValue() + "] with [ championWhiteList ] message.");
/*     */         } 
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public World getWorld(int dim) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public boolean getSingleplayer() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */