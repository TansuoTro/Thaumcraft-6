/*    */ package thaumcraft.common.items.armor;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.EntityEquipmentSlot;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.ThaumcraftMaterials;
/*    */ import thaumcraft.client.renderers.models.gear.ModelLeaderArmor;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemCultistLeaderArmor extends ItemArmor implements IThaumcraftItems {
/*    */   ModelBiped model1;
/*    */   ModelBiped model2;
/*    */   ModelBiped model;
/*    */   
/* 25 */   public ItemCultistLeaderArmor(String name, int j, EntityEquipmentSlot k) { super(ThaumcraftMaterials.ARMORMAT_CULTIST_LEADER, j, k);
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
/* 78 */     this.model1 = null;
/* 79 */     this.model2 = null;
/* 80 */     this.model = null;
/*    */     func_77637_a(ConfigItems.TABTC);
/*    */     setRegistryName(name);
/*    */     func_77655_b(name);
/*    */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this); } @SideOnly(Side.CLIENT)
/*    */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
/* 86 */     if (this.model1 == null) {
/* 87 */       this.model1 = new ModelLeaderArmor(1.0F);
/*    */     }
/* 89 */     if (this.model2 == null) {
/* 90 */       this.model2 = new ModelLeaderArmor(0.5F);
/*    */     }
/* 92 */     this.model = CustomArmorHelper.getCustomArmorModel(entityLiving, itemStack, armorSlot, this.model, this.model1, this.model2);
/* 93 */     return this.model;
/*    */   }
/*    */   
/*    */   public Item getItem() { return this; }
/*    */   
/*    */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */   
/*    */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */   
/*    */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */   
/*    */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) { return "thaumcraft:textures/entity/armor/cultist_leader_armor.png"; }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*    */   
/*    */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(stack1, stack2); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemCultistLeaderArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */