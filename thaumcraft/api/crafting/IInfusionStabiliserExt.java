/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
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
/*    */ public interface IInfusionStabiliserExt
/*    */   extends IInfusionStabiliser
/*    */ {
/*    */   float getStabilizationAmount(World paramWorld, BlockPos paramBlockPos);
/*    */   
/* 27 */   default boolean hasSymmetryPenalty(World world, BlockPos pos1, BlockPos pos2) { return false; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   default float getSymmetryPenalty(World world, BlockPos pos) { return 0.0F; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\IInfusionStabiliserExt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */