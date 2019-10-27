/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ 
/*    */ 
/*    */ public class CardSculpting
/*    */   extends TheorycraftCard
/*    */ {
/* 14 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public String getResearchCategory() { return "GOLEMANCY"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public String getLocalizedName() { return (new TextComponentTranslation("card.sculpting.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public String getLocalizedText() { return (new TextComponentTranslation("card.sculpting.text", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { new ItemStack(Items.field_151119_aD) }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public boolean[] getRequiredItemsConsumed() { return new boolean[] { true }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 44 */     data.addTotal(getResearchCategory(), 20);
/* 45 */     data.bonusDraws++;
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardSculpting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */