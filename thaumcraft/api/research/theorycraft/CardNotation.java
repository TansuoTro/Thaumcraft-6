/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ public class CardNotation
/*    */   extends TheorycraftCard {
/*    */   private String cat1;
/*    */   private String cat2;
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 15 */     NBTTagCompound nbt = super.serialize();
/* 16 */     nbt.func_74778_a("cat1", this.cat1);
/* 17 */     nbt.func_74778_a("cat2", this.cat2);
/* 18 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 23 */     super.deserialize(nbt);
/* 24 */     this.cat1 = nbt.func_74779_i("cat1");
/* 25 */     this.cat2 = nbt.func_74779_i("cat2");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean isAidOnly() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getLocalizedName() { return (new TextComponentTranslation("card.notation.name", new Object[0])).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getLocalizedText() { return (new TextComponentTranslation("card.notation.text", new Object[] { TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat1, new Object[0]))
/* 46 */           .func_150254_d() + TextFormatting.RESET, TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat2, new Object[0]))
/* 47 */           .func_150254_d() + TextFormatting.RESET
/* 48 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 53 */     if (data.categoryTotals.size() < 2) return false; 
/* 54 */     int lVal = Integer.MAX_VALUE;
/* 55 */     String lKey = "";
/* 56 */     int hVal = 0;
/* 57 */     String hKey = "";
/* 58 */     for (String category : data.categoryTotals.keySet()) {
/* 59 */       int q = data.getTotal(category);
/* 60 */       if (q < lVal) {
/* 61 */         lVal = q;
/* 62 */         lKey = category;
/*    */       } 
/* 64 */       if (q > hVal) {
/* 65 */         hVal = q;
/* 66 */         hKey = category;
/*    */       } 
/*    */     } 
/* 69 */     if (hKey.equals(lKey) || lVal <= 0) return false; 
/* 70 */     this.cat1 = lKey;
/* 71 */     this.cat2 = hKey;
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 77 */     if (this.cat1 == null || this.cat2 == null) return false; 
/* 78 */     int lVal = data.getTotal(this.cat1);
/* 79 */     data.addTotal(this.cat1, -lVal);
/* 80 */     data.addTotal(this.cat2, lVal / 2 + MathHelper.func_76136_a(player.func_70681_au(), 0, lVal / 2));
/* 81 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardNotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */