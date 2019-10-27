/*    */ package thaumcraft.common.golems.seals;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SealEmptyAdvanced
/*    */   extends SealEmpty
/*    */   implements ISealConfigToggles
/*    */ {
/* 17 */   public String getKey() { return "thaumcraft:empty_advanced"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public int getFilterSize() { return 9; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public ResourceLocation getSealIcon() { return this.icon; }
/*    */ 
/*    */   
/* 30 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_empty_advanced");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NonNullList<ItemStack> getInv(int c) {
/* 36 */     if ((getToggles()[4]).value && !isBlacklist()) {
/* 37 */       ArrayList<ItemStack> w = new ArrayList<ItemStack>();
/* 38 */       for (ItemStack s : super.getInv()) {
/* 39 */         if (s != null && !s.func_190926_b()) {
/* 40 */           w.add(s);
/*    */         }
/*    */       } 
/* 43 */       if (w.size() > 0) {
/* 44 */         int i = Math.abs(c % w.size());
/* 45 */         return NonNullList.func_191197_a(1, w.get(i));
/*    */       } 
/*    */     } 
/* 48 */     return super.getInv();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public NonNullList<ItemStack> getInv() { return super.getInv(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public int[] getGuiCategories() { return new int[] { 1, 3, 0, 4 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.SMART }; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealEmptyAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */