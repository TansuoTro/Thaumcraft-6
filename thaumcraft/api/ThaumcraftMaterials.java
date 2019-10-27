/*    */ package thaumcraft.api;
/*    */ 
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.common.util.EnumHelper;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ 
/*    */ public class ThaumcraftMaterials
/*    */ {
/* 14 */   public static Item.ToolMaterial TOOLMAT_THAUMIUM = EnumHelper.addToolMaterial("THAUMIUM", 3, 500, 7.0F, 2.5F, 22).setRepairItem(new ItemStack(ItemsTC.ingots));
/* 15 */   public static Item.ToolMaterial TOOLMAT_VOID = EnumHelper.addToolMaterial("VOID", 4, 150, 8.0F, 3.0F, 10).setRepairItem(new ItemStack(ItemsTC.ingots, 1, 1));
/* 16 */   public static Item.ToolMaterial TOOLMAT_ELEMENTAL = EnumHelper.addToolMaterial("THAUMIUM_ELEMENTAL", 3, 1500, 9.0F, 3.0F, 18).setRepairItem(new ItemStack(ItemsTC.ingots));
/* 17 */   public static ItemArmor.ArmorMaterial ARMORMAT_THAUMIUM = EnumHelper.addArmorMaterial("THAUMIUM", "THAUMIUM", 25, new int[] { 2, 5, 6, 2 }, 25, SoundEvents.field_187725_r, 1.0F);
/* 18 */   public static ItemArmor.ArmorMaterial ARMORMAT_SPECIAL = EnumHelper.addArmorMaterial("SPECIAL", "SPECIAL", 25, new int[] { 1, 2, 3, 1 }, 25, SoundEvents.field_187728_s, 1.0F);
/* 19 */   public static ItemArmor.ArmorMaterial ARMORMAT_VOID = EnumHelper.addArmorMaterial("VOID", "VOID", 10, new int[] { 3, 6, 8, 3 }, 10, SoundEvents.field_187713_n, 1.0F);
/* 20 */   public static ItemArmor.ArmorMaterial ARMORMAT_VOIDROBE = EnumHelper.addArmorMaterial("VOIDROBE", "VOIDROBE", 18, new int[] { 4, 7, 9, 4 }, 10, SoundEvents.field_187728_s, 2.0F);
/* 21 */   public static ItemArmor.ArmorMaterial ARMORMAT_FORTRESS = EnumHelper.addArmorMaterial("FORTRESS", "FORTRESS", 40, new int[] { 3, 6, 7, 3 }, 25, SoundEvents.field_187725_r, 3.0F);
/* 22 */   public static ItemArmor.ArmorMaterial ARMORMAT_CULTIST_PLATE = EnumHelper.addArmorMaterial("CULTIST_PLATE", "CULTIST_PLATE", 18, new int[] { 2, 5, 6, 2 }, 13, SoundEvents.field_187725_r, 0.0F);
/* 23 */   public static ItemArmor.ArmorMaterial ARMORMAT_CULTIST_ROBE = EnumHelper.addArmorMaterial("CULTIST_ROBE", "CULTIST_ROBE", 17, new int[] { 2, 4, 5, 2 }, 13, SoundEvents.field_187713_n, 0.0F);
/* 24 */   public static ItemArmor.ArmorMaterial ARMORMAT_CULTIST_LEADER = EnumHelper.addArmorMaterial("CULTIST_LEADER", "CULTIST_LEADER", 30, new int[] { 3, 6, 7, 3 }, 20, SoundEvents.field_187725_r, 1.0F);
/*    */ 
/*    */   
/* 27 */   public static final Material MATERIAL_TAINT = new MaterialTaint();
/*    */   
/*    */   public static class MaterialTaint
/*    */     extends Material
/*    */   {
/*    */     public MaterialTaint() {
/* 33 */       super(MapColor.field_151678_z);
/* 34 */       func_76219_n();
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     public boolean func_76230_c() { return true; }
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\ThaumcraftMaterials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */