/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ public class CardInspired
/*    */   extends TheorycraftCard {
/* 10 */   String cat = null;
/*    */   
/*    */   int amt;
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 15 */     NBTTagCompound nbt = super.serialize();
/* 16 */     nbt.func_74778_a("cat", this.cat);
/* 17 */     nbt.func_74768_a("amt", this.amt);
/* 18 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 23 */     super.deserialize(nbt);
/* 24 */     this.cat = nbt.func_74779_i("cat");
/* 25 */     this.amt = nbt.func_74762_e("amt");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getResearchCategory() { return this.cat; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 35 */     if (data.categoryTotals.size() < 1) return false; 
/* 36 */     int hVal = 0;
/* 37 */     String hKey = "";
/* 38 */     for (String category : data.categoryTotals.keySet()) {
/* 39 */       int q = data.getTotal(category);
/* 40 */       if (q > hVal) {
/* 41 */         hVal = q;
/* 42 */         hKey = category;
/*    */       } 
/*    */     } 
/* 45 */     this.cat = hKey;
/* 46 */     this.amt = 10 + hVal / 2;
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public int getInspirationCost() { return 2; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public String getLocalizedName() { return (new TextComponentTranslation("card.inspired.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public String getLocalizedText() { return (new TextComponentTranslation("card.inspired.text", new Object[] {
/* 63 */           Integer.valueOf(this.amt), TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat, new Object[0])).func_150254_d() + TextFormatting.RESET })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 68 */     data.addTotal(this.cat, this.amt);
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardInspired.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */