/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.research.theorycraft.ITheorycraftAid;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class AidGlyphedStone
/*    */   implements ITheorycraftAid
/*    */ {
/* 12 */   public Object getAidObject() { return new ItemStack(BlocksTC.stoneAncientGlyphed); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public Class<TheorycraftCard>[] getCards() { return new Class[] { CardGlyphs.class }; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\AidGlyphedStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */