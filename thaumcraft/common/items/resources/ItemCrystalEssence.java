/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.ItemTCEssentiaContainer;
/*    */ 
/*    */ 
/*    */ public class ItemCrystalEssence
/*    */   extends ItemTCEssentiaContainer
/*    */ {
/* 15 */   public ItemCrystalEssence() { super("crystal_essence", 1, new String[0]); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 21 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 22 */       for (Aspect tag : Aspect.aspects.values()) {
/* 23 */         ItemStack i = new ItemStack(this);
/* 24 */         setAspects(i, (new AspectList()).add(tag, this.base));
/* 25 */         items.add(i);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public String func_77653_i(ItemStack stack) {
/* 31 */     return (getAspects(stack) != null && !(getAspects(stack)).aspects.isEmpty()) ? 
/* 32 */       String.format(super.func_77653_i(stack), new Object[] { getAspects(stack).getAspects()[0].getName() }) : super
/* 33 */       .func_77653_i(stack);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\resources\ItemCrystalEssence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */