/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.api.items.RechargeHelper;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.lib.events.PlayerEvents;
/*     */ 
/*     */ public class ItemBootsTraveller
/*     */   extends ItemArmor
/*     */   implements IThaumcraftItems, IRechargable {
/*     */   public ItemBootsTraveller() {
/*  29 */     super(ThaumcraftMaterials.ARMORMAT_SPECIAL, 4, EntityEquipmentSlot.FEET);
/*  30 */     func_77656_e(350);
/*  31 */     setRegistryName("traveller_boots");
/*  32 */     func_77655_b("traveller_boots");
/*  33 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*  34 */     func_77637_a(ConfigItems.TABTC);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  55 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) { return "thaumcraft:textures/entity/armor/bootstraveler.png"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(Items.field_151116_aA)) ? true : super
/*  72 */       .func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
/*  84 */     boolean hasCharge = (RechargeHelper.getCharge(itemStack) > 0);
/*     */     
/*  86 */     if (!world.field_72995_K && player.field_70173_aa % 20 == 0) {
/*  87 */       int e = 0;
/*  88 */       if (itemStack.func_77942_o()) e = itemStack.func_77978_p().func_74762_e("energy"); 
/*  89 */       if (e > 0) {
/*  90 */         e--;
/*  91 */       } else if (e <= 0 && RechargeHelper.consumeCharge(itemStack, player, 1)) {
/*  92 */         e = 60;
/*     */       } 
/*  94 */       itemStack.func_77983_a("energy", new NBTTagInt(e));
/*     */     } 
/*     */     
/*  97 */     if (hasCharge && !player.field_71075_bZ.field_75100_b && player.field_191988_bg > 0.0F) {
/*     */       
/*  99 */       if (player.field_70170_p.field_72995_K && !player.func_70093_af()) {
/* 100 */         if (!PlayerEvents.prevStep.containsKey(Integer.valueOf(player.func_145782_y()))) {
/* 101 */           PlayerEvents.prevStep.put(Integer.valueOf(player.func_145782_y()), Float.valueOf(player.field_70138_W));
/*     */         }
/* 103 */         player.field_70138_W = 1.0F;
/*     */       } 
/*     */       
/* 106 */       if (player.field_70122_E) {
/* 107 */         float bonus = 0.05F;
/* 108 */         if (player.func_70090_H()) bonus /= 4.0F; 
/* 109 */         player.func_191958_b(0.0F, 0.0F, bonus, 1.0F);
/*     */       } else {
/* 111 */         if (player.func_70090_H()) {
/* 112 */           player.func_191958_b(0.0F, 0.0F, 0.025F, 1.0F);
/*     */         }
/* 114 */         player.field_70747_aH = 0.05F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int getMaxCharge(ItemStack stack, EntityLivingBase player) { return 240; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public IRechargable.EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) { return IRechargable.EnumChargeDisplay.PERIODIC; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemBootsTraveller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */