/*     */ package thaumcraft.common.world.aura;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.common.lib.events.ServerEvents;
/*     */ 
/*     */ public class AuraThread implements Runnable {
/*     */   public int dim;
/*     */   private final long INTERVAL = 1000L;
/*     */   private boolean stop;
/*     */   Random rand;
/*     */   private float phaseVis;
/*     */   
/*     */   public AuraThread(int dim2) {
/*  22 */     this.INTERVAL = 1000L;
/*     */ 
/*     */ 
/*     */     
/*  26 */     this.stop = false;
/*     */     
/*  28 */     this.rand = new Random(System.currentTimeMillis());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  34 */     this.phaseVis = 0.0F;
/*  35 */     this.phaseFlux = 0.0F;
/*  36 */     this.phaseMax = 0.0F;
/*  37 */     this.lastWorldTime = 0L;
/*     */     
/*  39 */     this.phaseTable = new float[] { 0.25F, 0.15F, 0.1F, 0.05F, 0.0F, 0.05F, 0.1F, 0.15F };
/*  40 */     this.maxTable = new float[] { 0.15F, 0.05F, 0.0F, -0.05F, -0.15F, -0.05F, 0.0F, 0.05F };
/*     */     this.dim = dim2;
/*     */   } private float phaseFlux; private float phaseMax; private long lastWorldTime; private float[] phaseTable; private float[] maxTable;
/*     */   public void run() {
/*  44 */     Thaumcraft.log.info("Starting aura thread for dim " + this.dim);
/*     */     
/*  46 */     while (!this.stop) {
/*     */       
/*  48 */       if (AuraHandler.auras.isEmpty()) {
/*  49 */         Thaumcraft.log.warn("No auras found!");
/*     */         
/*     */         break;
/*     */       } 
/*  53 */       long startTime = System.currentTimeMillis();
/*     */       
/*  55 */       AuraWorld auraWorld = AuraHandler.getAuraWorld(this.dim);
/*  56 */       if (auraWorld != null) {
/*  57 */         WorldServer worldServer = DimensionManager.getWorld(this.dim);
/*  58 */         if (this.lastWorldTime != worldServer.func_72820_D()) {
/*  59 */           this.lastWorldTime = worldServer.func_72820_D();
/*  60 */           if (worldServer != null) {
/*  61 */             this.phaseVis = this.phaseTable[worldServer.field_73011_w.func_76559_b(worldServer.func_72912_H().func_76073_f())];
/*  62 */             this.phaseMax = 1.0F + this.maxTable[worldServer.field_73011_w.func_76559_b(worldServer.func_72912_H().func_76073_f())];
/*  63 */             this.phaseFlux = 0.25F - this.phaseVis;
/*     */           } 
/*  65 */           for (AuraChunk auraChunk : auraWorld.auraChunks.values()) {
/*  66 */             processAuraChunk(auraWorld, auraChunk);
/*     */           }
/*     */         } 
/*     */       } else {
/*  70 */         stop();
/*     */       } 
/*     */       
/*  73 */       long executionTime = System.currentTimeMillis() - startTime;
/*     */       
/*     */       try {
/*  76 */         if (executionTime > 1000L) {
/*  77 */           Thaumcraft.log.warn("AURAS TAKING " + (executionTime - 1000L) + " ms LONGER THAN NORMAL IN DIM " + this.dim);
/*     */         }
/*  79 */         Thread.sleep(Math.max(1L, 1000L - executionTime));
/*     */       }
/*  81 */       catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */     
/*  84 */     Thaumcraft.log.info("Stopping aura thread for dim " + this.dim);
/*     */     try {
/*  86 */       ServerEvents.auraThreads.remove(Integer.valueOf(this.dim));
/*  87 */     } catch (Exception e) {
/*  88 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processAuraChunk(AuraWorld auraWorld, AuraChunk auraChunk) {
/*  93 */     List<Integer> directions = Arrays.asList(new Integer[] { null, null, null, (new Integer[4][2] = (new Integer[4][1] = (new Integer[4][0] = Integer.valueOf(0)).valueOf(1)).valueOf(2)).valueOf(3) });
/*  94 */     Collections.shuffle(directions, this.rand);
/*  95 */     int x = auraChunk.loc.field_77276_a;
/*  96 */     int y = auraChunk.loc.field_77275_b;
/*  97 */     float base = auraChunk.getBase() * this.phaseMax;
/*     */     
/*  99 */     boolean dirty = false;
/*     */     
/* 101 */     float currentVis = auraChunk.getVis();
/* 102 */     float currentFlux = auraChunk.getFlux();
/*     */     
/* 104 */     AuraChunk neighbourVisChunk = null;
/* 105 */     AuraChunk neighbourFluxChunk = null;
/* 106 */     float lowestVis = Float.MAX_VALUE;
/* 107 */     float lowestFlux = Float.MAX_VALUE;
/*     */     
/* 109 */     for (Integer a : directions) {
/* 110 */       EnumFacing dir = EnumFacing.func_176731_b(a.intValue());
/* 111 */       AuraChunk n = auraWorld.getAuraChunkAt(x + dir.func_82601_c(), y + dir.func_82599_e());
/* 112 */       if (n != null) {
/* 113 */         if ((neighbourVisChunk == null || lowestVis > n.getVis()) && n.getVis() + n.getFlux() < n.getBase() * this.phaseMax) {
/* 114 */           neighbourVisChunk = n;
/* 115 */           lowestVis = n.getVis();
/*     */         } 
/* 117 */         if (neighbourFluxChunk == null || lowestFlux > n.getFlux()) {
/* 118 */           neighbourFluxChunk = n;
/* 119 */           lowestFlux = n.getFlux();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 125 */     if (neighbourVisChunk != null && lowestVis < currentVis && (lowestVis / currentVis) < 0.75D) {
/* 126 */       float inc = Math.min(currentVis - lowestVis, 1.0F);
/* 127 */       currentVis -= inc;
/* 128 */       neighbourVisChunk.setVis(lowestVis + inc);
/* 129 */       dirty = true;
/* 130 */       markChunkAsDirty(neighbourVisChunk, auraWorld.dim);
/*     */     } 
/*     */ 
/*     */     
/* 134 */     if (neighbourFluxChunk != null && currentFlux > 
/* 135 */       Math.max(5.0F, auraChunk.getBase() / 10.0F) && lowestFlux < currentFlux / 1.75D) {
/*     */       
/* 137 */       float inc = Math.min(currentFlux - lowestFlux, 1.0F);
/* 138 */       currentFlux -= inc;
/* 139 */       neighbourFluxChunk.setFlux(lowestFlux + inc);
/* 140 */       dirty = true;
/* 141 */       markChunkAsDirty(neighbourFluxChunk, auraWorld.dim);
/*     */     } 
/*     */     
/* 144 */     if (currentVis + currentFlux < base) {
/* 145 */       float inc = Math.min(base - currentVis + currentFlux, this.phaseVis);
/* 146 */       currentVis += inc;
/* 147 */       dirty = true;
/*     */     }
/* 149 */     else if (currentVis > base * 1.25D && this.rand.nextFloat() < 0.1D) {
/* 150 */       currentFlux += this.phaseFlux;
/* 151 */       currentVis -= this.phaseFlux;
/* 152 */       dirty = true;
/*     */     }
/* 154 */     else if (currentVis <= base * 0.1D && currentVis >= currentFlux && this.rand.nextFloat() < 0.1D) {
/* 155 */       currentFlux += this.phaseFlux;
/* 156 */       dirty = true;
/*     */     } 
/*     */ 
/*     */     
/* 160 */     if (dirty) {
/* 161 */       auraChunk.setVis(currentVis);
/* 162 */       auraChunk.setFlux(currentFlux);
/* 163 */       markChunkAsDirty(auraChunk, auraWorld.dim);
/*     */     } 
/*     */     
/* 166 */     if (currentFlux > base * 0.75D && this.rand.nextFloat() < currentFlux / 500.0F / 10.0F) {
/* 167 */       AuraHandler.riftTrigger.put(Integer.valueOf(auraWorld.dim), new BlockPos(x * 16, 0, y * 16));
/*     */     }
/*     */   }
/*     */   
/*     */   private void markChunkAsDirty(AuraChunk chunk, int dim) {
/* 172 */     if (chunk.isModified())
/*     */       return; 
/* 174 */     ChunkPos pos = new ChunkPos(chunk.loc.field_77276_a, chunk.loc.field_77275_b);
/* 175 */     if (!AuraHandler.dirtyChunks.containsKey(Integer.valueOf(dim)))
/* 176 */       AuraHandler.dirtyChunks.put(Integer.valueOf(dim), new CopyOnWriteArrayList()); 
/* 177 */     CopyOnWriteArrayList<ChunkPos> dc = (CopyOnWriteArrayList)AuraHandler.dirtyChunks.get(Integer.valueOf(dim));
/* 178 */     if (!dc.contains(pos)) {
/* 179 */       dc.add(pos);
/*     */     }
/*     */   }
/*     */   
/* 183 */   public void stop() { this.stop = true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\aura\AuraThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */