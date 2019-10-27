/*    */ package thaumcraft.common.golems.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel;
/*    */ 
/*    */ public class PartModelClaws
/*    */   extends PartModel {
/*    */   public PartModelClaws(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint) {
/* 11 */     super(objModel, objTexture, attachPoint);
/*    */     
/* 13 */     this.f = 0.0F;
/*    */   } float f;
/*    */   public void preRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side) {
/* 16 */     if (partName.startsWith("claw")) {
/* 17 */       this.f = 0.0F;
/* 18 */       this.f = golem.getGolemEntity().func_70678_g(partialTicks) * 4.1F;
/* 19 */       this.f *= this.f;
/* 20 */       GlStateManager.func_179137_b(0.0D, -0.2D, 0.0D);
/* 21 */       GlStateManager.func_179114_b(this.f, partName.endsWith("1") ? 1.0F : -1.0F, 0.0F, 0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\PartModelClaws.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */