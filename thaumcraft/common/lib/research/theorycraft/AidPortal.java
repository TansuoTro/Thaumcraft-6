/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ import thaumcraft.api.research.theorycraft.ITheorycraftAid;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class AidPortal
/*    */   implements ITheorycraftAid
/*    */ {
/*    */   Object portal;
/*    */   
/* 13 */   public AidPortal(Object o) { this.portal = o; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public Object getAidObject() { return this.portal; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public Class<TheorycraftCard>[] getCards() { return new Class[] { CardPortal.class }; }
/*    */   
/*    */   public static class AidPortalEnd
/*    */     extends AidPortal
/*    */   {
/* 28 */     public AidPortalEnd() { super(Blocks.field_150384_bq); }
/*    */   }
/*    */   
/*    */   public static class AidPortalNether
/*    */     extends AidPortal
/*    */   {
/* 34 */     public AidPortalNether() { super(Blocks.field_150427_aO); }
/*    */   }
/*    */   
/*    */   public static class AidPortalCrimson
/*    */     extends AidPortal
/*    */   {
/* 40 */     public AidPortalCrimson() { super(thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser.class); }
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\AidPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */