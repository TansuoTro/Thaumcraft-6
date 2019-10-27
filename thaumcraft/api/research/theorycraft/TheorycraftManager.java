/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TheorycraftManager
/*    */ {
/*  9 */   public static HashMap<String, ITheorycraftAid> aids = new HashMap();
/*    */   
/*    */   public static void registerAid(ITheorycraftAid aid) {
/* 12 */     String key = aid.getClass().getName();
/* 13 */     if (!aids.containsKey(key)) {
/* 14 */       aids.put(key, aid);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 19 */   public static HashMap<String, Class<TheorycraftCard>> cards = new HashMap();
/*    */   
/*    */   public static void registerCard(Class cardClass) {
/* 22 */     String key = cardClass.getName();
/* 23 */     if (!cards.containsKey(key))
/* 24 */       cards.put(key, cardClass); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\TheorycraftManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */