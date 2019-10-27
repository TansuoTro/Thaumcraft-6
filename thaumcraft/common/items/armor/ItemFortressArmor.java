/*     */ package thaumcraft.common.items.armor;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ISpecialArmor;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IGoggles;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.models.gear.ModelFortressArmor;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ 
/*     */ public class ItemFortressArmor extends ItemArmor implements ISpecialArmor, IGoggles, IRevealer, IThaumcraftItems {
/*     */   ModelBiped model1;
/*     */   ModelBiped model2;
/*     */   ModelBiped model;
/*     */   
/*     */   public Item getItem() { return this; }
/*     */   
/*     */   public ItemFortressArmor(String name, ItemArmor.ArmorMaterial material, int renderIndex, EntityEquipmentSlot armorType) {
/*  35 */     super(material, renderIndex, armorType);
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
/*  69 */     this.model1 = null;
/*  70 */     this.model2 = null;
/*  71 */     this.model = null;
/*     */     setRegistryName(name);
/*     */     func_77655_b(name);
/*     */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*     */     func_77637_a(ConfigItems.TABTC);
/*     */   } public String[] getVariantNames() { return new String[] { "normal" }; } @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
/*  78 */     if (this.model1 == null) {
/*  79 */       this.model1 = new ModelFortressArmor(1.0F);
/*     */     }
/*  81 */     if (this.model2 == null) {
/*  82 */       this.model2 = new ModelFortressArmor(0.5F);
/*     */     }
/*  84 */     this.model = CustomArmorHelper.getCustomArmorModel(entityLiving, itemStack, armorSlot, this.model, this.model1, this.model2);
/*  85 */     return this.model;
/*     */   } public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*  90 */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) { return "thaumcraft:textures/entity/armor/fortress_armor.png"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 101 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("goggles")) {
/* 102 */       tooltip.add(TextFormatting.DARK_PURPLE + 
/* 103 */           I18n.func_74838_a("item.goggles.name"));
/*     */     }
/* 105 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("mask")) {
/* 106 */       tooltip.add(TextFormatting.GOLD + 
/* 107 */           I18n.func_74838_a("item.fortress_helm.mask." + stack
/* 108 */             .func_77978_p().func_74762_e("mask")));
/*     */     }
/* 110 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super
/* 116 */       .func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
/* 125 */     int priority = 0;
/* 126 */     double ratio = this.field_77879_b / 25.0D;
/*     */     
/* 128 */     if (source.func_82725_o() == true) {
/* 129 */       priority = 1;
/* 130 */       ratio = this.field_77879_b / 35.0D;
/*     */     }
/* 132 */     else if (source.func_76347_k() == true || source.func_94541_c()) {
/* 133 */       priority = 1;
/* 134 */       ratio = this.field_77879_b / 20.0D;
/* 135 */     } else if (source.func_76363_c()) {
/* 136 */       priority = 0;
/* 137 */       ratio = 0.0D;
/*     */     } 
/*     */     
/* 140 */     ISpecialArmor.ArmorProperties ap = new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77952_i());
/* 141 */     if (player instanceof EntityPlayer) {
/* 142 */       int q = 0;
/* 143 */       for (int a = 1; a < 4; a++) {
/* 144 */         ItemStack piece = (ItemStack)((EntityPlayer)player).field_71071_by.field_70460_b.get(a);
/* 145 */         if (piece != null && !piece.func_190926_b() && piece.func_77973_b() instanceof ItemFortressArmor) {
/* 146 */           if (piece.func_77942_o() && piece.func_77978_p().func_74764_b("mask")) {
/* 147 */             ap.Armor++;
/*     */           }
/* 149 */           q++;
/* 150 */           if (q <= 1) {
/* 151 */             ap.Armor++;
/* 152 */             ap.Toughness++;
/*     */           } 
/*     */         } 
/*     */       } 
/* 156 */     }  return ap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
/* 163 */     int q = 0;
/* 164 */     int ar = 0;
/* 165 */     for (int a = 1; a < 4; a++) {
/* 166 */       ItemStack piece = (ItemStack)player.field_71071_by.field_70460_b.get(a);
/* 167 */       if (piece != null && !piece.func_190926_b() && piece.func_77973_b() instanceof ItemFortressArmor) {
/* 168 */         if (piece.func_77942_o() && piece.func_77978_p().func_74764_b("mask")) {
/* 169 */           ar++;
/*     */         }
/* 171 */         q++;
/* 172 */         if (q <= 1) {
/* 173 */           ar++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     return ar;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
/* 183 */     if (source != DamageSource.field_76379_h) {
/* 184 */       stack.func_77972_a(damage, entity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public boolean showNodes(ItemStack itemstack, EntityLivingBase player) { return (itemstack.func_77942_o() && itemstack.func_77978_p().func_74764_b("goggles")); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) { return (itemstack.func_77942_o() && itemstack.func_77978_p().func_74764_b("goggles")); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemFortressArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */