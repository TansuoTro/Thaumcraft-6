/*     */ package thaumcraft.common.items.armor;
/*     */ 
/*     */ import net.minecraft.block.BlockCauldron;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IVisDiscountGear;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ 
/*     */ public class ItemRobeArmor
/*     */   extends ItemArmor implements IVisDiscountGear, IThaumcraftItems {
/*     */   public ItemRobeArmor(String name, ItemArmor.ArmorMaterial enumarmormaterial, int j, EntityEquipmentSlot k) {
/*  31 */     super(enumarmormaterial, j, k);
/*  32 */     setRegistryName(name);
/*  33 */     func_77655_b(name);
/*  34 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*  35 */     func_77637_a(ConfigItems.TABTC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  57 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public boolean func_82816_b_(ItemStack stack1) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_82814_b(ItemStack stack1) {
/*  78 */     NBTTagCompound nbttagcompound = stack1.func_77978_p();
/*     */     
/*  80 */     if (nbttagcompound == null)
/*     */     {
/*  82 */       return 6961280;
/*     */     }
/*     */ 
/*     */     
/*  86 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*  87 */     return (nbttagcompound1 == null) ? 6961280 : (nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : 6961280);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82815_c(ItemStack stack1) {
/*  94 */     NBTTagCompound nbttagcompound = stack1.func_77978_p();
/*     */     
/*  96 */     if (nbttagcompound != null) {
/*     */       
/*  98 */       NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */       
/* 100 */       if (nbttagcompound1.func_74764_b("color"))
/*     */       {
/* 102 */         nbttagcompound1.func_82580_o("color");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82813_b(ItemStack stack1, int par2) {
/* 111 */     NBTTagCompound nbttagcompound = stack1.func_77978_p();
/*     */     
/* 113 */     if (nbttagcompound == null) {
/*     */       
/* 115 */       nbttagcompound = new NBTTagCompound();
/* 116 */       stack1.func_77982_d(nbttagcompound);
/*     */     } 
/*     */     
/* 119 */     NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
/*     */     
/* 121 */     if (!nbttagcompound.func_74764_b("display"))
/*     */     {
/* 123 */       nbttagcompound.func_74782_a("display", nbttagcompound1);
/*     */     }
/*     */     
/* 126 */     nbttagcompound1.func_74768_a("color", par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
/* 133 */     if (stack.func_77973_b() == ItemsTC.clothChest || stack
/* 134 */       .func_77973_b() == ItemsTC.clothBoots) {
/* 135 */       return (type == null) ? "thaumcraft:textures/entity/armor/robes_1.png" : "thaumcraft:textures/entity/armor/robes_1_overlay.png";
/*     */     }
/* 137 */     if (stack.func_77973_b() == ItemsTC.clothLegs) {
/* 138 */       return (type == null) ? "thaumcraft:textures/entity/armor/robes_2.png" : "thaumcraft:textures/entity/armor/robes_2_overlay.png";
/*     */     }
/* 140 */     return (type == null) ? "thaumcraft:textures/entity/armor/robes_1.png" : "thaumcraft:textures/entity/armor/robes_1_overlay.png";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.fabric)) ? true : super
/* 154 */       .func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public int getVisDiscount(ItemStack stack, EntityPlayer player) { return (this.field_77881_a == EntityEquipmentSlot.FEET) ? 2 : 3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 168 */     IBlockState bs = world.func_180495_p(pos);
/* 169 */     if (bs.func_177230_c() == Blocks.field_150383_bp) {
/* 170 */       Blocks.field_150383_bp; int i = ((Integer)bs.func_177229_b(BlockCauldron.field_176591_a)).intValue();
/* 171 */       if (!world.field_72995_K && i > 0) {
/* 172 */         func_82815_c(player.func_184586_b(hand));
/* 173 */         Blocks.field_150383_bp.func_176590_a(world, pos, bs, i - 1);
/* 174 */         return EnumActionResult.SUCCESS;
/*     */       } 
/*     */     } 
/* 177 */     return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\armor\ItemRobeArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */