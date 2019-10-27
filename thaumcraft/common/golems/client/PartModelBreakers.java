/*    */ package thaumcraft.common.golems.client;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel;
/*    */ 
/*    */ public class PartModelBreakers extends PartModel {
/*    */   private HashMap<Integer, Float[]> ani;
/*    */   
/*    */   public PartModelBreakers(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint) {
/* 13 */     super(objModel, objTexture, attachPoint);
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
/* 34 */     this.ani = new HashMap();
/*    */   }
/*    */   
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side) {
/*    */     if (partName.equals("grinder")) {
/*    */       float lastSpeed = 0.0F;
/*    */       float lastRot = 0.0F;
/*    */       if (this.ani.containsKey(Integer.valueOf(golem.getGolemEntity().func_145782_y()))) {
/*    */         lastSpeed = (Float[])this.ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y()))[0].floatValue();
/*    */         lastRot = (Float[])this.ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y()))[1].floatValue();
/*    */       } 
/*    */       float f = Math.max(lastSpeed, golem.getGolemEntity().func_70678_g(partialTicks) * 20.0F);
/*    */       float rot = lastRot + f;
/*    */       lastSpeed = f * 0.99F;
/*    */       this.ani.put(Integer.valueOf(golem.getGolemEntity().func_145782_y()), new Float[] { null, (new Float[2][0] = Float.valueOf(lastSpeed)).valueOf(rot) });
/*    */       GlStateManager.func_179137_b(0.0D, -0.34D, 0.0D);
/*    */       GlStateManager.func_179114_b(((golem.getGolemEntity()).field_70173_aa + partialTicks) / 2.0F + rot + ((side == PartModel.EnumLimbSide.LEFT) ? 22 : 0), (side == PartModel.EnumLimbSide.LEFT) ? -1.0F : 1.0F, 0.0F, 0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\PartModelBreakers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */