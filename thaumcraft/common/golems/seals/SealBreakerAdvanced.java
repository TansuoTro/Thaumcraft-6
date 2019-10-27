/*    */ package thaumcraft.common.golems.seals;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*    */ 
/*    */ 
/*    */ public class SealBreakerAdvanced
/*    */   extends SealBreaker
/*    */ {
/* 11 */   public String getKey() { return "thaumcraft:breaker_advanced"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public int getFilterSize() { return 9; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public ResourceLocation getSealIcon() { return this.icon; }
/*    */ 
/*    */   
/* 24 */   ResourceLocation icon = new ResourceLocation("thaumcraft", "items/seals/seal_breaker_advanced");
/*    */   
/* 26 */   protected ISealConfigToggles.SealToggle[] props = { new ISealConfigToggles.SealToggle(true, "pmeta", "golem.prop.meta"), new ISealConfigToggles.SealToggle(false, "psilk", "golem.prop.silk") };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public ISealConfigToggles.SealToggle[] getToggles() { return this.props; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setToggle(int indx, boolean value) { this.props[indx].setValue(value); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public EnumGolemTrait[] getRequiredTags() { return new EnumGolemTrait[] { EnumGolemTrait.BREAKER, EnumGolemTrait.SMART }; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealBreakerAdvanced.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */