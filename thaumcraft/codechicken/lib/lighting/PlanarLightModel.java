/*    */ package thaumcraft.codechicken.lib.lighting;
/*    */ 
/*    */ import thaumcraft.codechicken.lib.colour.ColourRGBA;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlanarLightModel
/*    */   implements CCRenderState.IVertexOperation
/*    */ {
/* 11 */   public static PlanarLightModel standardLightModel = LightModel.standardLightModel.reducePlanar();
/*    */   
/*    */   public int[] colours;
/*    */ 
/*    */   
/* 16 */   public PlanarLightModel(int[] colours) { this.colours = colours; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean load() {
/* 21 */     CCRenderState.pipeline.addDependency(CCRenderState.sideAttrib);
/* 22 */     CCRenderState.pipeline.addDependency(CCRenderState.colourAttrib);
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void operate() { CCRenderState.setColour(ColourRGBA.multiply(CCRenderState.colour, this.colours[CCRenderState.side])); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public int operationID() { return LightModel.operationIndex; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\lighting\PlanarLightModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */