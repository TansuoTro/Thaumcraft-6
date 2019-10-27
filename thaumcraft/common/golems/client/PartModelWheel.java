/*    */ package thaumcraft.common.golems.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel;
/*    */ import thaumcraft.common.golems.parts.GolemLegWheels;
/*    */ 
/*    */ public class PartModelWheel
/*    */   extends PartModel
/*    */ {
/* 12 */   public PartModelWheel(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint) { super(objModel, objTexture, attachPoint); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side) {
/* 17 */     if (partName.equals("wheel")) {
/* 18 */       float lastRot = 0.0F;
/* 19 */       if (GolemLegWheels.ani.containsKey(Integer.valueOf(golem.getGolemEntity().func_145782_y()))) {
/* 20 */         lastRot = ((Float)GolemLegWheels.ani.get(Integer.valueOf(golem.getGolemEntity().func_145782_y()))).floatValue();
/*    */       }
/* 22 */       GlStateManager.func_179137_b(0.0D, -0.375D, 0.0D);
/* 23 */       GlStateManager.func_179114_b(lastRot, -1.0F, 0.0F, 0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\PartModelWheel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */