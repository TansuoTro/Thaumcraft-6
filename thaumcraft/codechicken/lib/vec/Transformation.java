/*    */ package thaumcraft.codechicken.lib.vec;
/*    */ 
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.codechicken.lib.render.CCRenderState;
/*    */ 
/*    */ 
/*    */ public abstract class Transformation
/*    */   extends ITransformation<Vector3, Transformation>
/*    */   implements CCRenderState.IVertexOperation
/*    */ {
/* 12 */   public static final int operationIndex = CCRenderState.registerOperation();
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
/* 27 */   public Transformation at(Vector3 point) { return new TransformationList(new Transformation[] { new Translation(-point.x, -point.y, -point.z), this, point.translation() }); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public TransformationList with(Transformation t) { return new TransformationList(new Transformation[] { this, t }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean load() {
/* 39 */     CCRenderState.pipeline.addRequirement(CCRenderState.normalAttrib.operationID());
/* 40 */     return !isRedundant();
/*    */   }
/*    */ 
/*    */   
/*    */   public void operate() {
/* 45 */     apply(CCRenderState.vert.vec);
/* 46 */     if (CCRenderState.normalAttrib.active) {
/* 47 */       applyN(CCRenderState.normal);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 52 */   public int operationID() { return operationIndex; }
/*    */   
/*    */   public abstract void applyN(Vector3 paramVector3);
/*    */   
/*    */   public abstract void apply(Matrix4 paramMatrix4);
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public abstract void glApply();
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\Transformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */