/*    */ package thaumcraft.common.lib.research;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.aspects.AspectHelper;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ import thaumcraft.api.research.IScanThing;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchCategory;
/*    */ import thaumcraft.api.research.ScanningManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScanGeneric
/*    */   implements IScanThing
/*    */ {
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 24 */     if (obj == null) return false; 
/* 25 */     AspectList al = null;
/*    */     
/* 27 */     if (obj instanceof Entity && !(obj instanceof net.minecraft.entity.item.EntityItem)) {
/* 28 */       al = AspectHelper.getEntityAspects((Entity)obj);
/*    */     } else {
/* 30 */       ItemStack is = ScanningManager.getItemFromParms(player, obj);
/* 31 */       if (is != null && !is.func_190926_b()) {
/* 32 */         al = AspectHelper.getObjectAspects(is);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 37 */     return (al != null && al.size() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSuccess(EntityPlayer player, Object obj) {
/* 42 */     if (obj == null)
/* 43 */       return;  AspectList al = null;
/* 44 */     if (obj instanceof Entity && !(obj instanceof net.minecraft.entity.item.EntityItem)) {
/* 45 */       al = AspectHelper.getEntityAspects((Entity)obj);
/*    */     } else {
/* 47 */       ItemStack is = ScanningManager.getItemFromParms(player, obj);
/* 48 */       if (is != null && !is.func_190926_b()) {
/* 49 */         al = AspectHelper.getObjectAspects(is);
/*    */       }
/*    */     } 
/*    */     
/* 53 */     if (al != null) {
/* 54 */       for (ResearchCategory category : ResearchCategories.researchCategories.values()) {
/* 55 */         ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, category, category.applyFormula(al));
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getResearchKey(EntityPlayer player, Object obj) {
/* 63 */     if (obj instanceof Entity && !(obj instanceof net.minecraft.entity.item.EntityItem)) {
/* 64 */       String s = EntityList.func_75621_b((Entity)obj);
/* 65 */       return "!" + s;
/*    */     } 
/*    */     
/* 68 */     ItemStack is = ScanningManager.getItemFromParms(player, obj);
/* 69 */     if (is != null && !is.func_190926_b()) {
/* 70 */       String s = "!" + is.func_77973_b().getRegistryName();
/* 71 */       if (!is.func_77984_f()) s = s + is.func_77952_i(); 
/* 72 */       return s;
/*    */     } 
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\ScanGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */