/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*    */ import net.minecraft.client.renderer.entity.RenderLivingBase;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumHandSide;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class LayerHeldItemPech
/*    */   extends LayerHeldItem
/*    */ {
/* 20 */   public LayerHeldItemPech(RenderLivingBase<?> livingEntityRendererIn) { super(livingEntityRendererIn); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_177141_a(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
/* 26 */     boolean flag = (entitylivingbaseIn.func_184591_cq() == EnumHandSide.RIGHT);
/* 27 */     ItemStack itemstack = flag ? entitylivingbaseIn.func_184592_cb() : entitylivingbaseIn.func_184614_ca();
/* 28 */     ItemStack itemstack1 = flag ? entitylivingbaseIn.func_184614_ca() : entitylivingbaseIn.func_184592_cb();
/*    */     
/* 30 */     if ((itemstack != null && !itemstack.func_190926_b()) || (itemstack1 != null && !itemstack1.func_190926_b())) {
/*    */       
/* 32 */       GlStateManager.func_179094_E();
/* 33 */       if ((this.field_177206_a.func_177087_b()).field_78091_s) {
/*    */         
/* 35 */         float f = 0.5F;
/* 36 */         GlStateManager.func_179109_b(0.0F, 0.625F, 0.0F);
/* 37 */         GlStateManager.func_179114_b(-20.0F, -1.0F, 0.0F, 0.0F);
/* 38 */         GlStateManager.func_179152_a(f, f, f);
/*    */       } 
/* 40 */       renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
/* 41 */       renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
/* 42 */       GlStateManager.func_179121_F();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType p_188358_3_, EnumHandSide p_188358_4_) {
/* 48 */     if (stack != null && !stack.func_190926_b()) {
/*    */       
/* 50 */       GlStateManager.func_179094_E();
/* 51 */       ((ModelBiped)this.field_177206_a.func_177087_b()).func_187073_a(0.0625F, p_188358_4_);
/* 52 */       GlStateManager.func_179109_b(0.0F, -0.1F, 0.0625F);
/*    */       
/* 54 */       if (stack.func_77973_b() instanceof net.minecraft.item.ItemBow) {
/* 55 */         GlStateManager.func_179137_b(-0.07500000298023224D, -0.1D, 0.0D);
/*    */       }
/*    */       
/* 58 */       if (entity.func_70093_af())
/*    */       {
/* 60 */         GlStateManager.func_179109_b(0.0F, 0.2F, 0.0F);
/*    */       }
/*    */       
/* 63 */       GlStateManager.func_179114_b(-90.0F, 1.0F, 0.0F, 0.0F);
/* 64 */       GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
/* 65 */       boolean flag = (p_188358_4_ == EnumHandSide.LEFT);
/* 66 */       GlStateManager.func_179109_b(flag ? -0.0625F : 0.0625F, 0.125F, -0.625F);
/* 67 */       Minecraft.func_71410_x().func_175597_ag().func_187462_a(entity, stack, p_188358_3_, flag);
/* 68 */       GlStateManager.func_179121_F();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 74 */   public boolean func_177142_b() { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\LayerHeldItemPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */