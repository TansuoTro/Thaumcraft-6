/*    */ package thaumcraft.common.golems.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ import thaumcraft.api.golems.parts.PartModel;
/*    */ 
/*    */ 
/*    */ public class PartModelHauler
/*    */   extends PartModel
/*    */ {
/* 16 */   public PartModelHauler(ResourceLocation objModel, ResourceLocation objTexture, PartModel.EnumAttachPoint attachPoint) { super(objModel, objTexture, attachPoint); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void postRenderObjectPart(String partName, IGolemAPI golem, float partialTicks, PartModel.EnumLimbSide side) {
/* 21 */     if (golem.getCarrying().size() > 1 && golem.getCarrying().get(true) != null) {
/* 22 */       ItemStack itemstack = (ItemStack)golem.getCarrying().get(1);
/* 23 */       if (itemstack != null && !itemstack.func_190926_b()) {
/*    */         
/* 25 */         GlStateManager.func_179094_E();
/* 26 */         Item item = itemstack.func_77973_b();
/* 27 */         Minecraft minecraft = Minecraft.func_71410_x();
/* 28 */         GlStateManager.func_179139_a(0.375D, 0.375D, 0.375D);
/* 29 */         GlStateManager.func_179109_b(0.0F, 0.33F, 0.825F);
/*    */         
/* 31 */         if (!(item instanceof net.minecraft.item.ItemBlock)) {
/* 32 */           GlStateManager.func_179109_b(0.0F, 0.0F, -0.25F);
/*    */         }
/*    */         
/* 35 */         minecraft.func_175597_ag().func_178099_a(golem.getGolemEntity(), itemstack, ItemCameraTransforms.TransformType.HEAD);
/* 36 */         GlStateManager.func_179121_F();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\client\PartModelHauler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */