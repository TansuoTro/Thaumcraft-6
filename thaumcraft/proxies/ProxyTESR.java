/*    */ package thaumcraft.proxies;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*    */ import thaumcraft.client.renderers.tile.TileAlembicRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileBannerRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileBellowsRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileCentrifugeRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileCrucibleRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileDioptraRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileFocalManipulatorRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileGolemBuilderRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileHoleRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileHungryChestRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileInfusionMatrixRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileJarRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileMirrorRenderer;
/*    */ import thaumcraft.client.renderers.tile.TilePatternCrafterRenderer;
/*    */ import thaumcraft.client.renderers.tile.TilePedestalRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileRechargePedestalRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileResearchTableRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileThaumatoriumRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileTubeBufferRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileTubeOnewayRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileTubeValveRenderer;
/*    */ import thaumcraft.client.renderers.tile.TileVoidSiphonRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProxyTESR
/*    */ {
/*    */   public void setupTESR() {
/* 55 */     registerTESR(thaumcraft.common.tiles.crafting.TileCrucible.class, new TileCrucibleRenderer());
/* 56 */     registerTESR(thaumcraft.common.tiles.devices.TileDioptra.class, new TileDioptraRenderer());
/* 57 */     registerTESR(thaumcraft.common.tiles.crafting.TilePedestal.class, new TilePedestalRenderer());
/* 58 */     registerTESR(thaumcraft.common.tiles.devices.TileRechargePedestal.class, new TileRechargePedestalRenderer());
/* 59 */     registerTESR(thaumcraft.common.tiles.crafting.TileFocalManipulator.class, new TileFocalManipulatorRenderer());
/* 60 */     registerTESR(thaumcraft.common.tiles.devices.TileHungryChest.class, new TileHungryChestRenderer());
/* 61 */     registerTESR(thaumcraft.common.tiles.essentia.TileTubeOneway.class, new TileTubeOnewayRenderer());
/* 62 */     registerTESR(thaumcraft.common.tiles.essentia.TileTubeValve.class, new TileTubeValveRenderer());
/* 63 */     registerTESR(thaumcraft.common.tiles.essentia.TileTubeBuffer.class, new TileTubeBufferRenderer());
/* 64 */     registerTESR(thaumcraft.common.tiles.essentia.TileJar.class, new TileJarRenderer());
/* 65 */     registerTESR(thaumcraft.common.tiles.devices.TileBellows.class, new TileBellowsRenderer());
/* 66 */     registerTESR(thaumcraft.common.tiles.essentia.TileAlembic.class, new TileAlembicRenderer());
/* 67 */     registerTESR(thaumcraft.common.tiles.crafting.TileInfusionMatrix.class, new TileInfusionMatrixRenderer());
/* 68 */     registerTESR(thaumcraft.common.tiles.crafting.TileResearchTable.class, new TileResearchTableRenderer());
/* 69 */     registerTESR(thaumcraft.common.tiles.crafting.TileThaumatorium.class, new TileThaumatoriumRenderer());
/* 70 */     registerTESR(thaumcraft.common.tiles.essentia.TileCentrifuge.class, new TileCentrifugeRenderer());
/* 71 */     TileMirrorRenderer tmr = new TileMirrorRenderer();
/* 72 */     registerTESR(thaumcraft.common.tiles.devices.TileMirror.class, tmr);
/* 73 */     registerTESR(thaumcraft.common.tiles.devices.TileMirrorEssentia.class, tmr);
/* 74 */     registerTESR(thaumcraft.common.tiles.crafting.TileGolemBuilder.class, new TileGolemBuilderRenderer());
/* 75 */     registerTESR(thaumcraft.common.tiles.crafting.TilePatternCrafter.class, new TilePatternCrafterRenderer());
/* 76 */     registerTESR(thaumcraft.common.tiles.crafting.TileVoidSiphon.class, new TileVoidSiphonRenderer());
/*    */ 
/*    */     
/* 79 */     registerTESR(thaumcraft.common.tiles.misc.TileBanner.class, new TileBannerRenderer());
/* 80 */     registerTESR(thaumcraft.common.tiles.misc.TileHole.class, new TileHoleRenderer());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 91 */   private void registerTESR(Class tile, TileEntitySpecialRenderer renderer) { ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\proxies\ProxyTESR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */