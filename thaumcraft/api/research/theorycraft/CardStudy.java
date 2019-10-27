/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ public class CardStudy
/*    */   extends TheorycraftCard
/*    */ {
/* 14 */   String cat = "BASICS";
/*    */ 
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 18 */     NBTTagCompound nbt = super.serialize();
/* 19 */     nbt.func_74778_a("cat", this.cat);
/* 20 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 25 */     super.deserialize(nbt);
/* 26 */     this.cat = nbt.func_74779_i("cat");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getResearchCategory() { return this.cat; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 36 */     Random r = new Random(getSeed());
/* 37 */     ArrayList<String> list = data.getAvailableCategories(player);
/* 38 */     this.cat = (String)list.get(r.nextInt(list.size()));
/* 39 */     return (this.cat != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public boolean isAidOnly() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public String getLocalizedName() { return (new TextComponentTranslation("card.study.name", new Object[] { TextFormatting.DARK_BLUE + "" + TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat, new Object[0]))
/* 55 */           .func_150254_d() + TextFormatting.RESET
/* 56 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String getLocalizedText() { return (new TextComponentTranslation("card.study.text", new Object[] { TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat, new Object[0]))
/* 62 */           .func_150254_d() + TextFormatting.RESET
/* 63 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 68 */     data.addTotal(this.cat, MathHelper.func_76136_a(player.func_70681_au(), 15, 25));
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardStudy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */