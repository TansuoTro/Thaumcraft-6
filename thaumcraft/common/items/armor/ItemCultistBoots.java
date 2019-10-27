/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.EntityEquipmentSlot;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.items.IVisDiscountGear;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemCultistBoots
/*    */   extends ItemArmor
/*    */   implements IWarpingGear, IVisDiscountGear, IThaumcraftItems {
/*    */   public ItemCultistBoots() {
/* 24 */     super(ItemArmor.ArmorMaterial.IRON, 2, EntityEquipmentSlot.FEET);
/* 25 */     func_77637_a(ConfigItems.TABTC);
/* 26 */     setRegistryName("crimson_boots");
/* 27 */     func_77655_b("crimson_boots");
/* 28 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 49 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 55 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/* 61 */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) { return "thaumcraft:textures/entity/armor/cultistboots.png"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super
/* 67 */       .func_82789_a(stack1, stack2); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public int getVisDiscount(ItemStack stack, EntityPlayer player) { return 1; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemCultistBoots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */