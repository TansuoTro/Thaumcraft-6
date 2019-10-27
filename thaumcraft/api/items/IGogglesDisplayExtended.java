/*    */ package thaumcraft.api.items;
/*    */ 
/*    */ import net.minecraft.util.math.Vec3d;
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
/*    */ public interface IGogglesDisplayExtended
/*    */ {
/*    */   String[] getIGogglesText();
/*    */   
/* 24 */   default Vec3d getIGogglesTextOffset() { return new Vec3d(0.0D, 0.0D, 0.0D); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\items\IGogglesDisplayExtended.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */