/*    */ package thaumcraft.common.world.aura;
/*    */ 
/*    */ import java.lang.ref.WeakReference;
/*    */ import net.minecraft.util.math.ChunkPos;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraChunk
/*    */ {
/*    */   ChunkPos loc;
/*    */   short base;
/*    */   float vis;
/*    */   float flux;
/*    */   WeakReference<Chunk> chunkRef;
/*    */   
/* 19 */   public AuraChunk(ChunkPos loc) { this.loc = loc; }
/*    */ 
/*    */   
/*    */   public AuraChunk(Chunk chunk, short base, float vis, float flux) {
/* 23 */     if (chunk != null) {
/* 24 */       this.loc = chunk.func_76632_l();
/* 25 */       this.chunkRef = new WeakReference(chunk);
/*    */     } 
/* 27 */     this.base = base;
/* 28 */     this.vis = vis;
/* 29 */     this.flux = flux;
/*    */   }
/*    */   
/*    */   public boolean isModified() {
/* 33 */     if (this.chunkRef != null && this.chunkRef.get() != null) {
/* 34 */       return ((Chunk)this.chunkRef.get()).func_76601_a(false);
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/* 40 */   public short getBase() { return this.base; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setBase(short base) { this.base = base; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public float getVis() { return this.vis; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void setVis(float vis) { this.vis = Math.min(32766.0F, Math.max(0.0F, vis)); }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public float getFlux() { return this.flux; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setFlux(float flux) { this.flux = Math.min(32766.0F, Math.max(0.0F, flux)); }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public ChunkPos getLoc() { return this.loc; }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void setLoc(ChunkPos loc) { this.loc = loc; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\aura\AuraChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */