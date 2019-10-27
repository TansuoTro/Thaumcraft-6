/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ 
/*    */ public class AidBookshelf
/*    */   implements ITheorycraftAid
/*    */ {
/*  9 */   public Object getAidObject() { return Blocks.field_150342_X; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   public Class<TheorycraftCard>[] getCards() { return new Class[] { CardBalance.class, CardNotation.class, CardNotation.class, CardStudy.class, CardStudy.class, CardStudy.class }; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\AidBookshelf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */