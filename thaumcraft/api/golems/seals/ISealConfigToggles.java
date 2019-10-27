/*    */ package thaumcraft.api.golems.seals;
/*    */ 
/*    */ 
/*    */ public interface ISealConfigToggles
/*    */ {
/*    */   SealToggle[] getToggles();
/*    */   
/*    */   void setToggle(int paramInt, boolean paramBoolean);
/*    */   
/*    */   public static class SealToggle
/*    */   {
/*    */     public boolean value;
/*    */     public String key;
/*    */     public String name;
/*    */     
/*    */     public SealToggle(boolean value, String key, String name) {
/* 17 */       this.value = value;
/* 18 */       this.key = key;
/* 19 */       this.name = name;
/*    */     }
/*    */ 
/*    */     
/* 23 */     public boolean getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */     
/* 27 */     public void setValue(boolean value) { this.value = value; }
/*    */ 
/*    */ 
/*    */     
/* 31 */     public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */     
/* 35 */     public String getName() { return this.name; }
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\seals\ISealConfigToggles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */