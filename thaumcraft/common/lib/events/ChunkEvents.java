/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent;
/*     */ import net.minecraftforge.event.world.ChunkWatchEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.golems.seals.SealEntity;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketSealToClient;
/*     */ import thaumcraft.common.world.aura.AuraChunk;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public class ChunkEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void chunkSave(ChunkDataEvent.Save event) {
/*  27 */     int dim = (event.getWorld()).field_73011_w.getDimension();
/*  28 */     ChunkPos loc = event.getChunk().func_76632_l();
/*     */     
/*  30 */     NBTTagCompound nbt = new NBTTagCompound();
/*  31 */     event.getData().func_74782_a("Thaumcraft", nbt);
/*  32 */     nbt.func_74757_a(ModConfig.CONFIG_WORLD.regenKey, true);
/*     */ 
/*     */     
/*  35 */     AuraChunk ac = AuraHandler.getAuraChunk(dim, loc.field_77276_a, loc.field_77275_b);
/*  36 */     if (ac != null) {
/*  37 */       nbt.func_74777_a("base", ac.getBase());
/*  38 */       nbt.func_74776_a("flux", ac.getFlux());
/*  39 */       nbt.func_74776_a("vis", ac.getVis());
/*  40 */       if (!event.getChunk().func_177410_o()) {
/*  41 */         AuraHandler.removeAuraChunk(dim, loc.field_77276_a, loc.field_77275_b);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  47 */     NBTTagList tagList = new NBTTagList();
/*  48 */     for (ISealEntity seal : SealHandler.getSealsInChunk(event.getWorld(), loc)) {
/*  49 */       NBTTagCompound sealnbt = seal.writeNBT();
/*  50 */       tagList.func_74742_a(sealnbt);
/*  51 */       if (!event.getChunk().func_177410_o()) {
/*  52 */         SealHandler.removeSealEntity(event.getWorld(), seal.getSealPos(), true);
/*     */       }
/*     */     } 
/*     */     
/*  56 */     nbt.func_74782_a("seals", tagList);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void chunkLoad(ChunkDataEvent.Load event) {
/*  62 */     int dim = (event.getWorld()).field_73011_w.getDimension();
/*  63 */     ChunkPos loc = event.getChunk().func_76632_l();
/*     */ 
/*     */     
/*  66 */     if (event.getData().func_74775_l("Thaumcraft").func_74764_b("base")) {
/*  67 */       NBTTagCompound nbt = event.getData().func_74775_l("Thaumcraft");
/*  68 */       short base = nbt.func_74765_d("base");
/*  69 */       float flux = nbt.func_74760_g("flux");
/*  70 */       float vis = nbt.func_74760_g("vis");
/*  71 */       AuraHandler.addAuraChunk(dim, event.getChunk(), base, vis, flux);
/*     */     } else {
/*  73 */       AuraHandler.generateAura(event.getChunk(), (event.getWorld()).field_73012_v);
/*     */     } 
/*     */ 
/*     */     
/*  77 */     if (event.getData().func_74775_l("Thaumcraft").func_74764_b("seals")) {
/*  78 */       NBTTagCompound nbt = event.getData().func_74775_l("Thaumcraft");
/*  79 */       NBTTagList tagList = nbt.func_150295_c("seals", 10);
/*  80 */       for (int a = 0; a < tagList.func_74745_c(); a++) {
/*  81 */         NBTTagCompound tasknbt = tagList.func_150305_b(a);
/*  82 */         SealEntity seal = new SealEntity();
/*  83 */         seal.readNBT(tasknbt);
/*  84 */         SealHandler.addSealEntity(event.getWorld(), seal);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  90 */     if (!event.getData().func_74775_l("Thaumcraft").func_74764_b(ModConfig.CONFIG_WORLD.regenKey) && (ModConfig.CONFIG_WORLD.regenAmber || ModConfig.CONFIG_WORLD.regenAura || ModConfig.CONFIG_WORLD.regenCinnabar || ModConfig.CONFIG_WORLD.regenCrystals || ModConfig.CONFIG_WORLD.regenStructure || ModConfig.CONFIG_WORLD.regenTrees)) {
/*     */       
/*  92 */       Thaumcraft.log.warn("World gen was never run for chunk at " + event.getChunk().func_76632_l() + ". Adding to queue for regeneration.");
/*  93 */       ArrayList<ChunkPos> chunks = (ArrayList)ServerEvents.chunksToGenerate.get(Integer.valueOf(dim));
/*  94 */       if (chunks == null) {
/*  95 */         ServerEvents.chunksToGenerate.put(Integer.valueOf(dim), new ArrayList());
/*  96 */         chunks = (ArrayList)ServerEvents.chunksToGenerate.get(Integer.valueOf(dim));
/*     */       } 
/*  98 */       if (chunks != null) {
/*  99 */         chunks.add(new ChunkPos(loc.field_77276_a, loc.field_77275_b));
/* 100 */         ServerEvents.chunksToGenerate.put(Integer.valueOf(dim), chunks);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void chunkWatch(ChunkWatchEvent.Watch event) {
/* 112 */     for (ISealEntity seal : SealHandler.getSealsInChunk((event.getPlayer()).field_70170_p, event.getChunk()))
/* 113 */       PacketHandler.INSTANCE.sendTo(new PacketSealToClient(seal), event.getPlayer()); 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\ChunkEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */