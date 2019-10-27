/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemAxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ 
/*    */ public class ItemThaumiumAxe
/*    */   extends ItemAxe
/*    */   implements IThaumcraftItems
/*    */ {
/*    */   public ItemThaumiumAxe(Item.ToolMaterial enumtoolmaterial) {
/* 20 */     super(enumtoolmaterial, 8.0F, -3.0F);
/* 21 */     func_77637_a(ConfigItems.TABTC);
/* 22 */     setRegistryName("thaumium_axe");
/* 23 */     func_77655_b("thaumium_axe");
/* 24 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("axe"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemThaumiumAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */