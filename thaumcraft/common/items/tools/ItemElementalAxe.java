/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemAxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ 
/*     */ public class ItemElementalAxe
/*     */   extends ItemAxe
/*     */   implements IThaumcraftItems
/*     */ {
/*     */   public ItemElementalAxe(Item.ToolMaterial enumtoolmaterial) {
/*  36 */     super(enumtoolmaterial, 8.0F, -3.0F);
/*  37 */     func_77637_a(ConfigItems.TABTC);
/*  38 */     setRegistryName("elemental_axe");
/*  39 */     func_77655_b("elemental_axe");
/*  40 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  45 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("axe"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super
/*  84 */       .func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public EnumAction func_77661_b(ItemStack itemstack) { return EnumAction.BOW; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public int func_77626_a(ItemStack p_77626_1_) { return 72000; }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand hand) {
/* 101 */     playerIn.func_184598_c(hand);
/* 102 */     return new ActionResult(EnumActionResult.SUCCESS, playerIn.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
/* 108 */     List<EntityItem> stuff = EntityUtils.getEntitiesInRange(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, player, EntityItem.class, 10.0D);
/*     */ 
/*     */     
/* 111 */     if (stuff != null && stuff.size() > 0)
/*     */     {
/* 113 */       for (EntityItem e : stuff) {
/* 114 */         if (!e.field_70128_L) {
/*     */           
/* 116 */           double d6 = e.field_70165_t - player.field_70165_t;
/* 117 */           double d8 = e.field_70163_u - player.field_70163_u + (player.field_70131_O / 2.0F);
/* 118 */           double d10 = e.field_70161_v - player.field_70161_v;
/* 119 */           double d11 = MathHelper.func_76133_a(d6 * d6 + d8 * d8 + d10 * d10);
/* 120 */           d6 /= d11;
/* 121 */           d8 /= d11;
/* 122 */           d10 /= d11;
/* 123 */           double d13 = 0.3D;
/* 124 */           e.field_70159_w -= d6 * d13;
/* 125 */           e.field_70181_x -= d8 * d13 - 0.1D;
/* 126 */           e.field_70179_y -= d10 * d13;
/* 127 */           if (e.field_70159_w > 0.25D) e.field_70159_w = 0.25D; 
/* 128 */           if (e.field_70159_w < -0.25D) e.field_70159_w = -0.25D; 
/* 129 */           if (e.field_70181_x > 0.25D) e.field_70181_x = 0.25D; 
/* 130 */           if (e.field_70181_x < -0.25D) e.field_70181_x = -0.25D; 
/* 131 */           if (e.field_70179_y > 0.25D) e.field_70179_y = 0.25D; 
/* 132 */           if (e.field_70179_y < -0.25D) e.field_70179_y = -0.25D; 
/* 133 */           if (player.field_70170_p.field_72995_K) {
/* 134 */             FXDispatcher.INSTANCE.crucibleBubble((float)e.field_70165_t + (player.field_70170_p.field_73012_v
/* 135 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2F, (float)e.field_70163_u + e.field_70131_O + (player.field_70170_p.field_73012_v
/* 136 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2F, (float)e.field_70161_v + (player.field_70170_p.field_73012_v
/* 137 */                 .nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2F, 0.33F, 0.33F, 1.0F);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 146 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 147 */       ItemStack w1 = new ItemStack(this);
/* 148 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.BURROWING, 1);
/* 149 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.COLLECTOR, 1);
/* 150 */       items.add(w1);
/*     */     } else {
/* 152 */       super.func_150895_a(tab, items);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemElementalAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */