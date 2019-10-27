/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*    */ 
/*    */ 
/*    */ public class ItemElementalPickaxe
/*    */   extends ItemPickaxe
/*    */   implements IThaumcraftItems
/*    */ {
/*    */   public ItemElementalPickaxe(Item.ToolMaterial enumtoolmaterial) {
/* 27 */     super(enumtoolmaterial);
/* 28 */     func_77637_a(ConfigItems.TABTC);
/* 29 */     setRegistryName("elemental_pick");
/* 30 */     func_77655_b("elemental_pick");
/* 31 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("pickaxe"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
/* 79 */     if (!player.field_70170_p.field_72995_K && (
/* 80 */       !(entity instanceof EntityPlayer) || FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W()))
/*    */     {
/*    */       
/* 83 */       entity.func_70015_d(2);
/*    */     }
/*    */     
/* 86 */     return super.onLeftClickEntity(stack, player, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 91 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 92 */       ItemStack w1 = new ItemStack(this);
/* 93 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.REFINING, 1);
/* 94 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.SOUNDING, 2);
/* 95 */       items.add(w1);
/*    */     } else {
/* 97 */       super.func_150895_a(tab, items);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemElementalPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */