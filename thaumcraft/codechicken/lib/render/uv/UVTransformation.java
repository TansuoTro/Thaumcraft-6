/*    */ package thaumcraft.codechicken.lib.render.uv;
/*    */ 
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*    */ import thaumcraft.codechicken.lib.vec.ITransformation;
/*    */ 
/*    */ 
/*    */ public abstract class UVTransformation
/*    */   extends ITransformation<UV, UVTransformation>
/*    */   implements CCRenderState.IVertexOperation
/*    */ {
/* 11 */   public static final int operationIndex = CCRenderState.registerOperation();
/*    */ 
/*    */   
/* 14 */   public UVTransformation at(UV point) { return new UVTransformationList(new UVTransformation[] { new UVTranslation(-point.u, -point.v), this, new UVTranslation(point.u, point.v) }); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public UVTransformationList with(UVTransformation t) { return new UVTransformationList(new UVTransformation[] { this, t }); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public boolean load() { return !isRedundant(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void operate() { apply(CCRenderState.vert.uv); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public int operationID() { return operationIndex; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\rende\\uv\UVTransformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */