/*    */ package thaumcraft.api.casters;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FocusEffect
/*    */   extends FocusNode
/*    */ {
/* 13 */   public IFocusElement.EnumUnitType getType() { return IFocusElement.EnumUnitType.EFFECT; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public final FocusNode.EnumSupplyType[] mustBeSupplied() { return new FocusNode.EnumSupplyType[] { FocusNode.EnumSupplyType.TARGET }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public FocusNode.EnumSupplyType[] willSupply() { return null; }
/*    */ 
/*    */   
/*    */   public abstract boolean execute(RayTraceResult paramRayTraceResult, @Nullable Trajectory paramTrajectory, float paramFloat, int paramInt);
/*    */ 
/*    */   
/* 29 */   public float getDamageForDisplay(float finalPower) { return 0.0F; }
/*    */   
/*    */   public abstract void renderParticleFX(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6);
/*    */   
/*    */   public void onCast(Entity caster) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */