/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileTubeOneway
/*    */   extends TileTube
/*    */ {
/* 12 */   void calculateSuction(Aspect filter, boolean restrict, boolean directional) { super.calculateSuction(filter, restrict, true); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   void equalizeWithNeighbours(boolean directional) { super.equalizeWithNeighbours(true); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileTubeOneway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */