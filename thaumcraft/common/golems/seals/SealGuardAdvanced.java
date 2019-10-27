/*    */ package thaumcraft.common.golems.seals;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SealGuardAdvanced
/*    */   extends SealGuard
/*    */   implements ISealConfigToggles
/*    */ {
/* 14 */   public String getKey() { return "thaumcraft:guard_advanced"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public ResourceLocation getSealIcon() { return this.icon; }
/*    */ 
/*    */   
/* 22 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_guard_advanced");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public int[] getGuiCategories() { return new int[] { 2, 3, 0, 4 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.FIGHTER, EnumGolemTrait.SMART }; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealGuardAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */