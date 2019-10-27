/*     */ package thaumcraft.proxies;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelBakery;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.client.renderer.block.statemap.StateMapperBase;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.ModelBakeEvent;
/*     */ import net.minecraftforge.client.model.ModelLoader;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.client.renderers.block.CrystalModel;
/*     */ import thaumcraft.common.blocks.world.ore.ShardType;
/*     */ 
/*     */ 
/*     */ public class ProxyBlock
/*     */ {
/*  27 */   static ModelResourceLocation[] crystals = new ModelResourceLocation[ShardType.values().length];
/*  28 */   static ModelResourceLocation[] jars = new ModelResourceLocation[4];
/*  29 */   static ModelResourceLocation[] jarsVoid = new ModelResourceLocation[4];
/*     */   static ModelResourceLocation fibres;
/*  31 */   private static ModelResourceLocation fluidGooLocation = new ModelResourceLocation("thaumcraft:flux_goo", "fluid");
/*     */   
/*  33 */   private static ModelResourceLocation fluidDeathLocation = new ModelResourceLocation("thaumcraft:liquid_death", "fluid");
/*  34 */   private static ModelResourceLocation fluidPureLocation = new ModelResourceLocation("thaumcraft:purifying_fluid", "fluid");
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setupBlocksClient(IForgeRegistry<Block> iForgeRegistry) {
/*  39 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.slabAncient), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:slab_ancient"), "half=bottom,variant=default"));
/*  40 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.slabArcaneStone), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:slab_arcane_stone"), "half=bottom,variant=default"));
/*  41 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.slabArcaneBrick), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:slab_arcane_brick"), "half=bottom,variant=default"));
/*  42 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.slabEldritch), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:slab_eldritch"), "half=bottom,variant=default"));
/*  43 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.slabGreatwood), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:slab_greatwood"), "half=bottom,variant=default"));
/*  44 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.slabSilverwood), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:slab_silverwood"), "half=bottom,variant=default"));
/*     */ 
/*     */ 
/*     */     
/*  48 */     for (int a = 0; a < ShardType.values().length; a++) {
/*  49 */       crystals[a] = new ModelResourceLocation(iForgeRegistry.getKey(ShardType.values()[a].getOre()), "normal");
/*  50 */       final ModelResourceLocation mrl = crystals[a];
/*  51 */       ModelLoader.setCustomStateMapper(ShardType.values()[a].getOre(), new StateMapperBase()
/*     */           {
/*     */             protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/*  54 */               return mrl;
/*     */             }
/*     */           });
/*     */     } 
/*     */ 
/*     */     
/*  60 */     for (Block b : BlocksTC.banners.values())
/*  61 */       ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(b), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:banner"), "inventory")); 
/*  62 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.bannerCrimsonCult), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:banner_crimson_cult"), "inventory"));
/*     */ 
/*     */     
/*  65 */     for (Block b : BlocksTC.nitor.values()) {
/*  66 */       ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(b), 0, new ModelResourceLocation(new ResourceLocation("thaumcraft:nitor"), "inventory"));
/*     */     }
/*     */     
/*  69 */     ModelBakery.registerItemVariants(Item.func_150898_a(BlocksTC.mirror), new ResourceLocation[] { new ResourceLocation("thaumcraft:mirror"), new ResourceLocation("thaumcraft:mirror_on") });
/*  70 */     ModelBakery.registerItemVariants(Item.func_150898_a(BlocksTC.mirrorEssentia), new ResourceLocation[] { new ResourceLocation("thaumcraft:mirror_essentia"), new ResourceLocation("thaumcraft:mirror_essentia_on") });
/*     */     
/*  72 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.mirror), 1, new ModelResourceLocation(new ResourceLocation("thaumcraft:mirror_on"), "inventory"));
/*  73 */     ModelLoader.setCustomModelResourceLocation(Item.func_150898_a(BlocksTC.mirrorEssentia), 1, new ModelResourceLocation(new ResourceLocation("thaumcraft:mirror_essentia_on"), "inventory"));
/*     */ 
/*     */ 
/*     */     
/*  77 */     Item fluxGooItem = Item.func_150898_a(BlocksTC.fluxGoo);
/*  78 */     ModelBakery.registerItemVariants(fluxGooItem, new ResourceLocation[0]);
/*  79 */     ModelLoader.setCustomMeshDefinition(fluxGooItem, new ItemMeshDefinition() {
/*     */           public ModelResourceLocation func_178113_a(ItemStack stack) {
/*  81 */             return fluidGooLocation;
/*     */           }
/*     */         });
/*  84 */     ModelLoader.setCustomStateMapper(BlocksTC.fluxGoo, new StateMapperBase() {
/*     */           protected ModelResourceLocation func_178132_a(IBlockState state) {
/*  86 */             return fluidGooLocation;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     Item liquidDeathItem = Item.func_150898_a(BlocksTC.liquidDeath);
/* 104 */     ModelBakery.registerItemVariants(liquidDeathItem, new ResourceLocation[0]);
/* 105 */     ModelLoader.setCustomMeshDefinition(liquidDeathItem, new ItemMeshDefinition() {
/*     */           public ModelResourceLocation func_178113_a(ItemStack stack) {
/* 107 */             return fluidDeathLocation;
/*     */           }
/*     */         });
/* 110 */     ModelLoader.setCustomStateMapper(BlocksTC.liquidDeath, new StateMapperBase() {
/*     */           protected ModelResourceLocation func_178132_a(IBlockState state) {
/* 112 */             return fluidDeathLocation;
/*     */           }
/*     */         });
/*     */     
/* 116 */     Item purifyingFluidItem = Item.func_150898_a(BlocksTC.purifyingFluid);
/* 117 */     ModelBakery.registerItemVariants(purifyingFluidItem, new ResourceLocation[0]);
/* 118 */     ModelLoader.setCustomMeshDefinition(purifyingFluidItem, new ItemMeshDefinition() {
/*     */           public ModelResourceLocation func_178113_a(ItemStack stack) {
/* 120 */             return fluidPureLocation;
/*     */           }
/*     */         });
/* 123 */     ModelLoader.setCustomStateMapper(BlocksTC.purifyingFluid, new StateMapperBase()
/*     */         {
/* 125 */           protected ModelResourceLocation func_178132_a(IBlockState state) { return fluidPureLocation; }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber({Side.CLIENT})
/*     */   public static class BakeBlockEventHandler
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onModelBakeEvent(ModelBakeEvent event) {
/* 140 */       TextureAtlasSprite crystalTexture = Minecraft.func_71410_x().func_147117_R().func_110572_b("thaumcraft:blocks/crystal");
/* 141 */       CrystalModel crystalModel = new CrystalModel(crystalTexture);
/* 142 */       for (int a = 0; a < ShardType.values().length; a++)
/* 143 */         event.getModelRegistry().func_82595_a(ProxyBlock.crystals[a], crystalModel); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\ProxyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */