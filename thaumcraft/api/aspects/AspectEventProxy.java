/*    */ package thaumcraft.api.aspects;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.internal.CommonInternals;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AspectEventProxy
/*    */ {
/*    */   public void registerObjectTag(ItemStack item, AspectList aspects) {
/* 19 */     if (aspects == null) aspects = new AspectList(); 
/*    */     try {
/* 21 */       CommonInternals.objectTags.put(Integer.valueOf(CommonInternals.generateUniqueItemstackId(item)), aspects);
/* 22 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerObjectTag(String oreDict, AspectList aspects) {
/* 31 */     if (aspects == null) aspects = new AspectList(); 
/* 32 */     List<ItemStack> ores = ThaumcraftApiHelper.getOresWithWildCards(oreDict);
/* 33 */     if (ores != null && ores.size() > 0) {
/* 34 */       for (ItemStack ore : ores) {
/*    */         try {
/* 36 */           ItemStack oc = ore.func_77946_l();
/* 37 */           oc.func_190920_e(1);
/* 38 */           registerObjectTag(oc, aspects.copy());
/* 39 */         } catch (Exception exception) {}
/*    */       } 
/*    */     }
/*    */   }
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
/*    */   public void registerComplexObjectTag(ItemStack item, AspectList aspects) {
/* 54 */     if (!ThaumcraftApi.exists(item)) {
/* 55 */       AspectList tmp = AspectHelper.generateTags(item);
/* 56 */       if (tmp != null && tmp.size() > 0) {
/* 57 */         for (Aspect tag : tmp.getAspects()) {
/* 58 */           aspects.add(tag, tmp.getAmount(tag));
/*    */         }
/*    */       }
/* 61 */       registerObjectTag(item, aspects);
/*    */     } else {
/* 63 */       AspectList tmp = AspectHelper.getObjectAspects(item);
/* 64 */       for (Aspect tag : aspects.getAspects()) {
/* 65 */         tmp.merge(tag, tmp.getAmount(tag));
/*    */       }
/* 67 */       registerObjectTag(item, tmp);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerComplexObjectTag(String oreDict, AspectList aspects) {
/* 80 */     if (aspects == null) aspects = new AspectList(); 
/* 81 */     List<ItemStack> ores = ThaumcraftApiHelper.getOresWithWildCards(oreDict);
/* 82 */     if (ores != null && ores.size() > 0)
/* 83 */       for (ItemStack ore : ores) {
/*    */         try {
/* 85 */           ItemStack oc = ore.func_77946_l();
/* 86 */           oc.func_190920_e(1);
/* 87 */           registerComplexObjectTag(oc, aspects.copy());
/* 88 */         } catch (Exception exception) {}
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\AspectEventProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */