/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.research.theorycraft.ITheorycraftAid;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class AidBasicGolemancy
/*    */   implements ITheorycraftAid
/*    */ {
/* 11 */   public Object getAidObject() { return BlocksTC.golemBuilder; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public Class<TheorycraftCard>[] getCards() { return new Class[] { CardSculpting.class, CardScripting.class, CardSynergy.class }; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\AidBasicGolemancy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */