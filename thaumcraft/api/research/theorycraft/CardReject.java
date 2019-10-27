/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ 
/*    */ public class CardReject
/*    */   extends TheorycraftCard
/*    */ {
/*    */   private String cat1;
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 17 */     NBTTagCompound nbt = super.serialize();
/* 18 */     nbt.func_74778_a("cat", this.cat1);
/* 19 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 24 */     super.deserialize(nbt);
/* 25 */     this.cat1 = nbt.func_74779_i("cat");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public int getInspirationCost() { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public String getLocalizedName() { return (new TextComponentTranslation("card.reject.name", new Object[] { TextFormatting.DARK_BLUE + "" + TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat1, new Object[0]))
/* 36 */           .func_150260_c() + TextFormatting.RESET + "" + TextFormatting.BOLD
/* 37 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public String getLocalizedText() { return (new TextComponentTranslation("card.reject.text", new Object[] { TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat1, new Object[0]))
/* 43 */           .func_150254_d() + TextFormatting.RESET
/* 44 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 49 */     ArrayList<String> s = new ArrayList<String>();
/* 50 */     for (String c : data.categoryTotals.keySet()) {
/* 51 */       if (!data.categoriesBlocked.contains(c))
/* 52 */         s.add(c); 
/*    */     } 
/* 54 */     if (s.size() < 1) return false; 
/* 55 */     Random r = new Random(getSeed());
/* 56 */     this.cat1 = (String)s.get(r.nextInt(s.size()));
/* 57 */     return (this.cat1 != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 62 */     if (this.cat1 == null) return false; 
/* 63 */     data.addTotal("BASICS", 5);
/* 64 */     data.categoriesBlocked.add(this.cat1);
/* 65 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardReject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */