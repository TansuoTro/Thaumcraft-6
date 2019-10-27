/*    */ package thaumcraft.api.golems.parts;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
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
/*    */ public class PartModel
/*    */ {
/*    */   private ResourceLocation objModel;
/*    */   private ResourceLocation texture;
/*    */   private EnumAttachPoint attachPoint;
/*    */   
/*    */   public enum EnumAttachPoint
/*    */   {
/* 23 */     ARMS, LEGS, BODY, HEAD; }
/*    */   
/*    */   public PartModel(ResourceLocation objModel, ResourceLocation objTexture, EnumAttachPoint attachPoint) {
/* 26 */     this.objModel = objModel;
/* 27 */     this.texture = objTexture;
/* 28 */     this.attachPoint = attachPoint;
/*    */   }
/*    */ 
/*    */   
/* 32 */   public ResourceLocation getObjModel() { return this.objModel; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public ResourceLocation getTexture() { return this.texture; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public EnumAttachPoint getAttachPoint() { return this.attachPoint; }
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
/* 51 */   public boolean useMaterialTextureForObjectPart(String partName) { return partName.startsWith("bm"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, EnumLimbSide side) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void postRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, EnumLimbSide side) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public float preRenderArmRotationX(IGolemAPI golem, float partialTicks, EnumLimbSide side, float inputRot) { return inputRot; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public float preRenderArmRotationY(IGolemAPI golem, float partialTicks, EnumLimbSide side, float inputRot) { return inputRot; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 94 */   public float preRenderArmRotationZ(IGolemAPI golem, float partialTicks, EnumLimbSide side, float inputRot) { return inputRot; }
/*    */   
/*    */   public enum EnumLimbSide
/*    */   {
/* 98 */     LEFT, RIGHT, MIDDLE;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\parts\PartModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */