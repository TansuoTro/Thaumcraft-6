/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IVisDiscountGear;
/*     */ import thaumcraft.client.renderers.models.gear.ModelRobe;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ 
/*     */ public class ItemCultistRobeArmor extends ItemArmor implements IVisDiscountGear, IWarpingGear, IThaumcraftItems {
/*     */   ModelBiped model1;
/*     */   ModelBiped model2;
/*     */   ModelBiped model;
/*     */   
/*     */   public Item getItem() { return this; }
/*     */   
/*     */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */   
/*  27 */   public ItemCultistRobeArmor(String name, ItemArmor.ArmorMaterial enumarmormaterial, int j, EntityEquipmentSlot k) { super(enumarmormaterial, j, k);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.model1 = null;
/*  87 */     this.model2 = null;
/*  88 */     this.model = null;
/*     */     func_77637_a(ConfigItems.TABTC);
/*     */     setRegistryName(name);
/*     */     func_77655_b(name);
/*     */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this); } public int[] getVariantMeta() { return new int[] { 0 }; } @SideOnly(Side.CLIENT)
/*     */   public ItemMeshDefinition getCustomMesh() { return null; } @SideOnly(Side.CLIENT)
/*  94 */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) { if (this.model1 == null) {
/*  95 */       this.model1 = new ModelRobe(1.0F);
/*     */     }
/*  97 */     if (this.model2 == null) {
/*  98 */       this.model2 = new ModelRobe(0.5F);
/*     */     }
/* 100 */     this.model = CustomArmorHelper.getCustomArmorModel(entityLiving, itemStack, armorSlot, this.model, this.model1, this.model2);
/* 101 */     return this.model; }
/*     */    public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); } public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) { return "thaumcraft:textures/entity/armor/cultist_robe_armor.png"; }
/*     */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(Items.field_151042_j)) ? true : super.func_82789_a(stack1, stack2); }
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player) { return 1; }
/* 106 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 1; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemCultistRobeArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */