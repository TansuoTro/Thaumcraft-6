/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemTCBase
/*    */   extends Item
/*    */   implements IThaumcraftItems
/*    */ {
/*    */   protected final String BASE_NAME;
/*    */   protected String[] VARIANTS;
/*    */   protected int[] VARIANTS_META;
/*    */   
/*    */   public ItemTCBase(String name, String... variants) {
/* 22 */     setRegistryName(name);
/* 23 */     func_77655_b(name);
/* 24 */     func_77637_a(ConfigItems.TABTC);
/* 25 */     setNoRepair();
/*    */     
/* 27 */     func_77627_a((variants.length > 1));
/* 28 */     this.BASE_NAME = name;
/* 29 */     if (variants.length == 0) {
/* 30 */       this.VARIANTS = new String[] { name };
/*    */     } else {
/*    */       
/* 33 */       this.VARIANTS = variants;
/*    */     } 
/* 35 */     this.VARIANTS_META = new int[this.VARIANTS.length];
/* 36 */     for (int m = 0; m < this.VARIANTS.length; m++) {
/* 37 */       this.VARIANTS_META[m] = m;
/*    */     }
/* 39 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_77667_c(ItemStack itemStack) {
/* 44 */     if (this.field_77787_bX && itemStack.func_77960_j() < this.VARIANTS.length && this.VARIANTS[itemStack.func_77960_j()] != this.BASE_NAME) {
/* 45 */       return String.format(func_77658_a() + ".%s", new Object[] { this.VARIANTS[itemStack.func_77960_j()] });
/*    */     }
/* 47 */     return super.func_77667_c(itemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 52 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 53 */       if (!func_77614_k()) {
/* 54 */         super.func_150895_a(tab, items);
/*    */       } else {
/* 56 */         for (int meta = 0; meta < this.VARIANTS.length; meta++) {
/* 57 */           items.add(new ItemStack(this, 1, meta));
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 70 */   public String[] getVariantNames() { return this.VARIANTS; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public int[] getVariantMeta() { return this.VARIANTS_META; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelResourceLocation getCustomModelResourceLocation(String variant) {
/* 85 */     if (variant.equals(this.BASE_NAME)) {
/* 86 */       return new ModelResourceLocation("thaumcraft:" + this.BASE_NAME);
/*    */     }
/* 88 */     return new ModelResourceLocation("thaumcraft:" + this.BASE_NAME, variant);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\ItemTCBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */