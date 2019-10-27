/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemChunksEdible
/*    */   extends ItemFood
/*    */   implements IThaumcraftItems
/*    */ {
/*    */   public final int field_77855_a;
/*    */   private String[] variants;
/*    */   
/*    */   public ItemChunksEdible() {
/* 21 */     super(1, 0.3F, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     this.variants = new String[] { "beef", "chicken", "pork", "fish", "rabbit", "mutton" }; this.field_77855_a = 10; func_77625_d(64); func_77627_a(true);
/*    */     func_77656_e(0);
/*    */     setRegistryName("chunk");
/*    */     func_77655_b("chunk");
/*    */     func_77637_a(ConfigItems.TABTC);
/* 37 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this); } public int func_77626_a(ItemStack stack1) { return this.field_77855_a; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 42 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 43 */       for (int a = 0; a < this.variants.length; a++) {
/* 44 */         items.add(new ItemStack(this, 1, a));
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public String func_77667_c(ItemStack itemStack) {
/* 50 */     if (this.field_77787_bX && itemStack.func_77960_j() < this.variants.length && this.variants[itemStack.func_77960_j()] != "chunk") {
/* 51 */       return String.format(func_77658_a() + ".%s", new Object[] { this.variants[itemStack.func_77960_j()] });
/*    */     }
/* 53 */     return super.func_77667_c(itemStack);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public String[] getVariantNames() { return this.variants; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public int[] getVariantMeta() { return new int[] { 0, 1, 2, 3, 4, 5 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 73 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelResourceLocation getCustomModelResourceLocation(String variant) {
/* 79 */     if (variant.equals("chunk")) {
/* 80 */       return new ModelResourceLocation("thaumcraft:chunk");
/*    */     }
/* 82 */     return new ModelResourceLocation("thaumcraft:chunk", variant);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemChunksEdible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */