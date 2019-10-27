/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemThaumiumSword
/*    */   extends ItemSword
/*    */   implements IThaumcraftItems {
/*    */   public ItemThaumiumSword(Item.ToolMaterial enumtoolmaterial) {
/* 16 */     super(enumtoolmaterial);
/* 17 */     func_77637_a(ConfigItems.TABTC);
/* 18 */     setRegistryName("thaumium_sword");
/* 19 */     func_77655_b("thaumium_sword");
/* 20 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemThaumiumSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */