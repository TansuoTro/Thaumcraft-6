/*    */ package thaumcraft.common.golems.seals;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SealPickupAdvanced
/*    */   extends SealPickup
/*    */   implements ISealConfigToggles
/*    */ {
/* 14 */   public String getKey() { return "thaumcraft:pickup_advanced"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public int getFilterSize() { return 9; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public ResourceLocation getSealIcon() { return this.icon; }
/*    */ 
/*    */   
/* 27 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_pickup_advanced");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public int[] getGuiCategories() { return new int[] { 2, 1, 3, 0, 4 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.SMART }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealPickupAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */