/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.inventory.EntityEquipmentSlot;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumHandSide;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class CustomArmorHelper
/*    */ {
/*    */   protected static ModelBiped getCustomArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped model, ModelBiped model1, ModelBiped model2) {
/* 19 */     if (model == null) {
/* 20 */       EntityEquipmentSlot type = ((ItemArmor)itemStack.func_77973_b()).field_77881_a;
/* 21 */       if (type == EntityEquipmentSlot.CHEST || type == EntityEquipmentSlot.FEET) {
/* 22 */         model = model1;
/*    */       } else {
/* 24 */         model = model2;
/*    */       } 
/*    */     } 
/*    */     
/* 28 */     if (model != null) {
/* 29 */       model.field_78116_c.field_78806_j = (armorSlot == EntityEquipmentSlot.HEAD);
/* 30 */       model.field_178720_f.field_78806_j = (armorSlot == EntityEquipmentSlot.HEAD);
/* 31 */       model.field_78115_e.field_78806_j = (armorSlot == EntityEquipmentSlot.CHEST || armorSlot == EntityEquipmentSlot.LEGS);
/* 32 */       model.field_178723_h.field_78806_j = (armorSlot == EntityEquipmentSlot.CHEST);
/* 33 */       model.field_178724_i.field_78806_j = (armorSlot == EntityEquipmentSlot.CHEST);
/* 34 */       model.field_178721_j.field_78806_j = (armorSlot == EntityEquipmentSlot.LEGS);
/* 35 */       model.field_178722_k.field_78806_j = (armorSlot == EntityEquipmentSlot.LEGS);
/* 36 */       model.field_78117_n = entityLiving.func_70093_af();
/*    */       
/* 38 */       model.field_78093_q = entityLiving.func_184218_aH();
/* 39 */       model.field_78091_s = entityLiving.func_70631_g_();
/* 40 */       ItemStack itemstack = entityLiving.func_184614_ca();
/* 41 */       ItemStack itemstack1 = entityLiving.func_184592_cb();
/* 42 */       ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
/* 43 */       ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
/*    */       
/* 45 */       if (itemstack != null && !itemstack.func_190926_b()) {
/*    */         
/* 47 */         modelbiped$armpose = ModelBiped.ArmPose.ITEM;
/*    */         
/* 49 */         if (entityLiving.func_184605_cv() > 0) {
/*    */           
/* 51 */           EnumAction enumaction = itemstack.func_77975_n();
/*    */           
/* 53 */           if (enumaction == EnumAction.BLOCK) {
/*    */             
/* 55 */             modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
/*    */           }
/* 57 */           else if (enumaction == EnumAction.BOW) {
/*    */             
/* 59 */             modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 64 */       if (itemstack1 != null && !itemstack1.func_190926_b()) {
/*    */         
/* 66 */         modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;
/*    */         
/* 68 */         if (entityLiving.func_184605_cv() > 0) {
/*    */           
/* 70 */           EnumAction enumaction1 = itemstack1.func_77975_n();
/*    */           
/* 72 */           if (enumaction1 == EnumAction.BLOCK)
/*    */           {
/* 74 */             modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 79 */       if (entityLiving.func_184591_cq() == EnumHandSide.RIGHT) {
/*    */         
/* 81 */         model.field_187076_m = modelbiped$armpose;
/* 82 */         model.field_187075_l = modelbiped$armpose1;
/*    */       }
/*    */       else {
/*    */         
/* 86 */         model.field_187076_m = modelbiped$armpose1;
/* 87 */         model.field_187075_l = modelbiped$armpose;
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     return model;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\CustomArmorHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */