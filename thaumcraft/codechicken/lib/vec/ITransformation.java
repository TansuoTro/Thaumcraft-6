/*    */ package thaumcraft.codechicken.lib.vec;
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
/*    */ public abstract class ITransformation<Vector, Transformation extends ITransformation>
/*    */   extends Object
/*    */ {
/*    */   public abstract void apply(Vector paramVector);
/*    */   
/*    */   public abstract Transformation at(Vector paramVector);
/*    */   
/*    */   public abstract Transformation with(Transformation paramTransformation);
/*    */   
/* 31 */   public Transformation merge(Transformation next) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public boolean isRedundant() { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Transformation inverse();
/*    */ 
/*    */ 
/*    */   
/* 47 */   public Transformation $plus$plus(Transformation t) { return (Transformation)with(t); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\codechicken\lib\vec\ITransformation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */