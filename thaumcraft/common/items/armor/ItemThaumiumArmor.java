/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.inventory.EntityEquipmentSlot;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemThaumiumArmor
/*    */   extends ItemArmor
/*    */   implements IThaumcraftItems {
/*    */   public ItemThaumiumArmor(String name, ItemArmor.ArmorMaterial enumarmormaterial, int j, EntityEquipmentSlot k) {
/* 21 */     super(enumarmormaterial, j, k);
/* 22 */     setRegistryName(name);
/* 23 */     func_77655_b(name);
/* 24 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/* 25 */     func_77637_a(ConfigItems.TABTC);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 46 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
/* 56 */     if (stack.func_77973_b() == ItemsTC.thaumiumHelm || stack
/* 57 */       .func_77973_b() == ItemsTC.thaumiumChest || stack
/* 58 */       .func_77973_b() == ItemsTC.thaumiumBoots) {
/* 59 */       return "thaumcraft:textures/entity/armor/thaumium_1.png";
/*    */     }
/* 61 */     if (stack.func_77973_b() == ItemsTC.thaumiumLegs) {
/* 62 */       return "thaumcraft:textures/entity/armor/thaumium_2.png";
/*    */     }
/* 64 */     return "thaumcraft:textures/entity/armor/thaumium_1.png";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemThaumiumArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */