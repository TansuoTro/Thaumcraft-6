/*     */ package thaumcraft.common.lib.network;
/*     */ 
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
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
/*     */ public class PacketHandler
/*     */ {
/*  50 */   public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("thaumcraft".toLowerCase());
/*     */ 
/*     */   
/*     */   public static void preInit() {
/*  54 */     idx = 0;
/*     */ 
/*     */     
/*  57 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketBiomeChange.class, thaumcraft.common.lib.network.misc.PacketBiomeChange.class, idx++, Side.CLIENT);
/*  58 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketMiscEvent.class, thaumcraft.common.lib.network.misc.PacketMiscEvent.class, idx++, Side.CLIENT);
/*  59 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketKnowledgeGain.class, thaumcraft.common.lib.network.misc.PacketKnowledgeGain.class, idx++, Side.CLIENT);
/*  60 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketStartTheoryToServer.class, thaumcraft.common.lib.network.misc.PacketStartTheoryToServer.class, idx++, Side.SERVER);
/*  61 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketLogisticsRequestToServer.class, thaumcraft.common.lib.network.misc.PacketLogisticsRequestToServer.class, idx++, Side.SERVER);
/*  62 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketMiscStringToServer.class, thaumcraft.common.lib.network.misc.PacketMiscStringToServer.class, idx++, Side.SERVER);
/*  63 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketSelectThaumotoriumRecipeToServer.class, thaumcraft.common.lib.network.misc.PacketSelectThaumotoriumRecipeToServer.class, idx++, Side.SERVER);
/*  64 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketItemToClientContainer.class, thaumcraft.common.lib.network.misc.PacketItemToClientContainer.class, idx++, Side.CLIENT);
/*     */ 
/*     */     
/*  67 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.tiles.PacketTileToServer.class, thaumcraft.common.lib.network.tiles.PacketTileToServer.class, idx++, Side.SERVER);
/*  68 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.tiles.PacketTileToClient.class, thaumcraft.common.lib.network.tiles.PacketTileToClient.class, idx++, Side.CLIENT);
/*     */ 
/*     */     
/*  71 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketFocusNodesToServer.class, thaumcraft.common.lib.network.playerdata.PacketFocusNodesToServer.class, idx++, Side.SERVER);
/*  72 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketFocusNameToServer.class, thaumcraft.common.lib.network.playerdata.PacketFocusNameToServer.class, idx++, Side.SERVER);
/*     */ 
/*     */     
/*  75 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketAuraToClient.class, thaumcraft.common.lib.network.misc.PacketAuraToClient.class, idx++, Side.CLIENT);
/*  76 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketSealToClient.class, thaumcraft.common.lib.network.misc.PacketSealToClient.class, idx++, Side.CLIENT);
/*  77 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketSealFilterToClient.class, thaumcraft.common.lib.network.misc.PacketSealFilterToClient.class, idx++, Side.CLIENT);
/*  78 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketNote.class, thaumcraft.common.lib.network.misc.PacketNote.class, idx++, Side.CLIENT);
/*  79 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketSyncWarp.class, thaumcraft.common.lib.network.playerdata.PacketSyncWarp.class, idx++, Side.CLIENT);
/*  80 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketSyncKnowledge.class, thaumcraft.common.lib.network.playerdata.PacketSyncKnowledge.class, idx++, Side.CLIENT);
/*  81 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketWarpMessage.class, thaumcraft.common.lib.network.playerdata.PacketWarpMessage.class, idx++, Side.CLIENT);
/*  82 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketNote.class, thaumcraft.common.lib.network.misc.PacketNote.class, idx++, Side.SERVER);
/*  83 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketItemKeyToServer.class, thaumcraft.common.lib.network.misc.PacketItemKeyToServer.class, idx++, Side.SERVER);
/*  84 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.misc.PacketFocusChangeToServer.class, thaumcraft.common.lib.network.misc.PacketFocusChangeToServer.class, idx++, Side.SERVER);
/*  85 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketSyncProgressToServer.class, thaumcraft.common.lib.network.playerdata.PacketSyncProgressToServer.class, idx++, Side.SERVER);
/*  86 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketSyncResearchFlagsToServer.class, thaumcraft.common.lib.network.playerdata.PacketSyncResearchFlagsToServer.class, idx++, Side.SERVER);
/*  87 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.playerdata.PacketPlayerFlagToServer.class, thaumcraft.common.lib.network.playerdata.PacketPlayerFlagToServer.class, idx++, Side.SERVER);
/*     */ 
/*     */     
/*  90 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXPollute.class, thaumcraft.common.lib.network.fx.PacketFXPollute.class, idx++, Side.CLIENT);
/*  91 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXBlockBamf.class, thaumcraft.common.lib.network.fx.PacketFXBlockBamf.class, idx++, Side.CLIENT);
/*  92 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXFocusEffect.class, thaumcraft.common.lib.network.fx.PacketFXFocusEffect.class, idx++, Side.CLIENT);
/*  93 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact.class, thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact.class, idx++, Side.CLIENT);
/*  94 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXFocusPartImpactBurst.class, thaumcraft.common.lib.network.fx.PacketFXFocusPartImpactBurst.class, idx++, Side.CLIENT);
/*  95 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXBlockMist.class, thaumcraft.common.lib.network.fx.PacketFXBlockMist.class, idx++, Side.CLIENT);
/*  96 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXBlockArc.class, thaumcraft.common.lib.network.fx.PacketFXBlockArc.class, idx++, Side.CLIENT);
/*  97 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXEssentiaSource.class, thaumcraft.common.lib.network.fx.PacketFXEssentiaSource.class, idx++, Side.CLIENT);
/*  98 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXInfusionSource.class, thaumcraft.common.lib.network.fx.PacketFXInfusionSource.class, idx++, Side.CLIENT);
/*  99 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXShield.class, thaumcraft.common.lib.network.fx.PacketFXShield.class, idx++, Side.CLIENT);
/* 100 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXSonic.class, thaumcraft.common.lib.network.fx.PacketFXSonic.class, idx++, Side.CLIENT);
/* 101 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXWispZap.class, thaumcraft.common.lib.network.fx.PacketFXWispZap.class, idx++, Side.CLIENT);
/* 102 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXZap.class, thaumcraft.common.lib.network.fx.PacketFXZap.class, idx++, Side.CLIENT);
/* 103 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXSlash.class, thaumcraft.common.lib.network.fx.PacketFXSlash.class, idx++, Side.CLIENT);
/* 104 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXScanSource.class, thaumcraft.common.lib.network.fx.PacketFXScanSource.class, idx++, Side.CLIENT);
/* 105 */     INSTANCE.registerMessage(thaumcraft.common.lib.network.fx.PacketFXBoreDig.class, thaumcraft.common.lib.network.fx.PacketFXBoreDig.class, idx++, Side.CLIENT);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\PacketHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */