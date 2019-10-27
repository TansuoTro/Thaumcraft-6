/*    */ package thaumcraft.common.world.aura;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import thaumcraft.common.lib.utils.PosXY;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraWorld
/*    */ {
/*    */   int dim;
/*    */   ConcurrentHashMap<PosXY, AuraChunk> auraChunks;
/*    */   
/*    */   public AuraWorld(int dim) {
/* 14 */     this.auraChunks = new ConcurrentHashMap();
/*    */ 
/*    */     
/* 17 */     this.dim = dim;
/*    */   }
/*    */ 
/*    */   
/* 21 */   public ConcurrentHashMap<PosXY, AuraChunk> getAuraChunks() { return this.auraChunks; }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void setAuraChunks(ConcurrentHashMap<PosXY, AuraChunk> auraChunks) { this.auraChunks = auraChunks; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public AuraChunk getAuraChunkAt(int x, int y) { return getAuraChunkAt(new PosXY(x, y)); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public AuraChunk getAuraChunkAt(PosXY loc) { return (AuraChunk)this.auraChunks.get(loc); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\aura\AuraWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */