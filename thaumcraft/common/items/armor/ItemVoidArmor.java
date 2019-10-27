/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IWarpingGear;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ 
/*     */ public class ItemVoidArmor
/*     */   extends ItemArmor
/*     */   implements IWarpingGear, IThaumcraftItems {
/*     */   public ItemVoidArmor(String name, ItemArmor.ArmorMaterial enumarmormaterial, int j, EntityEquipmentSlot k) {
/*  25 */     super(enumarmormaterial, j, k);
/*  26 */     func_77637_a(ConfigItems.TABTC);
/*  27 */     setRegistryName(name);
/*  28 */     func_77655_b(name);
/*  29 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  34 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  50 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
/*  61 */     if (stack.func_77973_b() == ItemsTC.voidHelm || stack
/*  62 */       .func_77973_b() == ItemsTC.voidChest || stack
/*  63 */       .func_77973_b() == ItemsTC.voidBoots) {
/*  64 */       return "thaumcraft:textures/entity/armor/void_1.png";
/*     */     }
/*  66 */     if (stack.func_77973_b() == ItemsTC.voidLegs) {
/*  67 */       return "thaumcraft:textures/entity/armor/void_2.png";
/*     */     }
/*  69 */     return "thaumcraft:textures/entity/armor/void_1.png";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super
/*  83 */       .func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/*  88 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     
/*  90 */     if (!world.field_72995_K && stack.func_77951_h() && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
/*  91 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
/*  97 */     super.onArmorTick(world, player, armor);
/*  98 */     if (!world.field_72995_K && armor.func_77952_i() > 0 && player.field_70173_aa % 20 == 0) {
/*  99 */       armor.func_77972_a(-1, player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 1; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemVoidArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */