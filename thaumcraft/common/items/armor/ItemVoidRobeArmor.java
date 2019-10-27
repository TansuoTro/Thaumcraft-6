/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ISpecialArmor;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.renderers.models.gear.ModelRobe;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ public class ItemVoidRobeArmor extends ItemArmor implements IVisDiscountGear, IGoggles, IRevealer, ISpecialArmor, IWarpingGear, IThaumcraftItems {
/*     */   ModelBiped model1;
/*     */   ModelBiped model2;
/*     */   ModelBiped model;
/*     */   
/*     */   public Item getItem() { return this; }
/*     */   
/*     */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */   
/*     */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */   
/*  40 */   public ItemVoidRobeArmor(String name, ItemArmor.ArmorMaterial enumarmormaterial, int j, EntityEquipmentSlot k) { super(enumarmormaterial, j, k);
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
/* 131 */     this.model1 = null;
/* 132 */     this.model2 = null;
/* 133 */     this.model = null;
/*     */     func_77637_a(ConfigItems.TABTC);
/*     */     setRegistryName(name);
/*     */     func_77655_b(name);
/*     */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this); } @SideOnly(Side.CLIENT)
/*     */   public ItemMeshDefinition getCustomMesh() { return null; } public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); } public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) { return (type == null) ? "thaumcraft:textures/entity/armor/void_robe_armor_overlay.png" : "thaumcraft:textures/entity/armor/void_robe_armor.png"; } @SideOnly(Side.CLIENT)
/*     */   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
/* 140 */     if (this.model1 == null) {
/* 141 */       this.model1 = new ModelRobe(1.0F);
/*     */     }
/* 143 */     if (this.model2 == null) {
/* 144 */       this.model2 = new ModelRobe(0.5F);
/*     */     }
/* 146 */     this.model = CustomArmorHelper.getCustomArmorModel(entityLiving, itemStack, armorSlot, this.model, this.model1, this.model2);
/* 147 */     return this.model;
/*     */   }
/*     */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.EPIC; }
/*     */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 1)) ? true : super.func_82789_a(stack1, stack2); }
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/*     */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     if (!world.field_72995_K && stack.func_77951_h() && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase)
/*     */       stack.func_77972_a(-1, (EntityLivingBase)entity); 
/*     */   }
/* 156 */   public boolean func_82816_b_(ItemStack stack1) { return true; } public void onArmorTick(World world, EntityPlayer player, ItemStack armor) { super.onArmorTick(world, player, armor);
/*     */     if (!world.field_72995_K && armor.func_77952_i() > 0 && player.field_70173_aa % 20 == 0)
/*     */       armor.func_77972_a(-1, player);  } public boolean showNodes(ItemStack itemstack, EntityLivingBase player) { EntityEquipmentSlot type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
/*     */     return (type == EntityEquipmentSlot.HEAD); }
/*     */   public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) { EntityEquipmentSlot type = ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
/*     */     return (type == EntityEquipmentSlot.HEAD); }
/*     */   public int getVisDiscount(ItemStack stack, EntityPlayer player) { return 5; }
/*     */   public int func_82814_b(ItemStack stack1) {
/* 164 */     NBTTagCompound nbttagcompound = stack1.func_77978_p();
/*     */     
/* 166 */     if (nbttagcompound == null)
/*     */     {
/* 168 */       return 6961280;
/*     */     }
/*     */ 
/*     */     
/* 172 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/* 173 */     return (nbttagcompound1 == null) ? 6961280 : (nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : 6961280);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82815_c(ItemStack stack1) {
/* 180 */     NBTTagCompound nbttagcompound = stack1.func_77978_p();
/*     */     
/* 182 */     if (nbttagcompound != null) {
/*     */       
/* 184 */       NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */       
/* 186 */       if (nbttagcompound1.func_74764_b("color"))
/*     */       {
/* 188 */         nbttagcompound1.func_82580_o("color");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82813_b(ItemStack stack1, int par2) {
/* 197 */     NBTTagCompound nbttagcompound = stack1.func_77978_p();
/*     */     
/* 199 */     if (nbttagcompound == null) {
/*     */       
/* 201 */       nbttagcompound = new NBTTagCompound();
/* 202 */       stack1.func_77982_d(nbttagcompound);
/*     */     } 
/*     */     
/* 205 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */     
/* 207 */     if (!nbttagcompound.func_74764_b("display"))
/*     */     {
/* 209 */       nbttagcompound.func_74782_a("display", nbttagcompound1);
/*     */     }
/*     */     
/* 212 */     nbttagcompound1.func_74768_a("color", par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
/* 221 */     int priority = 0;
/* 222 */     double ratio = this.field_77879_b / 25.0D;
/*     */     
/* 224 */     if (source.func_82725_o()) {
/* 225 */       priority = 1;
/* 226 */       ratio = this.field_77879_b / 35.0D;
/* 227 */     } else if (source.func_76363_c()) {
/* 228 */       priority = 0;
/* 229 */       ratio = 0.0D;
/*     */     } 
/* 231 */     return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77952_i());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 236 */   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) { return this.field_77879_b; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) { if (source != DamageSource.field_76379_h) stack.func_77972_a(damage, entity);
/*     */      }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 248 */     IBlockState bs = world.func_180495_p(pos);
/* 249 */     if (bs.func_177230_c() == Blocks.field_150383_bp) {
/* 250 */       Blocks.field_150383_bp; int i = ((Integer)bs.func_177229_b(BlockCauldron.field_176591_a)).intValue();
/* 251 */       if (!world.field_72995_K && i > 0) {
/* 252 */         func_82815_c(player.func_184586_b(hand));
/* 253 */         Blocks.field_150383_bp.func_176590_a(world, pos, bs, i - 1);
/* 254 */         return EnumActionResult.SUCCESS;
/*     */       } 
/*     */     } 
/* 257 */     return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 3; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemVoidRobeArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */