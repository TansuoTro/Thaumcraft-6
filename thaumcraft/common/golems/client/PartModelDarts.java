/*    */ package thaumcraft.common.golems.client;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel;
/*    */ 
/*    */ public class PartModelDarts
/*    */   extends PartModel
/*    */ {
/* 10 */   public PartModelDarts(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint) { super(objModel, objTexture, attachPoint); }
/*    */ 
/*    */ 
/*    */   
/*    */   public float preRenderArmRotationX(IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side, float inputRot) {
/* 15 */     if (golem.isInCombat()) {
/* 16 */       inputRot = 90.0F - (golem.getGolemEntity()).field_70127_C + inputRot / 10.0F;
/*    */     }
/* 18 */     return inputRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public float preRenderArmRotationY(IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side, float inputRot) {
/* 23 */     if (golem.isInCombat()) {
/* 24 */       inputRot /= 10.0F;
/*    */     }
/* 26 */     return inputRot;
/*    */   }
/*    */ 
/*    */   
/*    */   public float preRenderArmRotationZ(IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side, float inputRot) {
/* 31 */     if (golem.isInCombat()) {
/* 32 */       inputRot /= 10.0F;
/*    */     }
/* 34 */     return inputRot;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\PartModelDarts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */