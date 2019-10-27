/*    */ package thaumcraft.api.research.theorycraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchCategory;
/*    */ 
/*    */ public class CardAnalyze
/*    */   extends TheorycraftCard
/*    */ {
/* 18 */   String cat = null;
/*    */ 
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 22 */     NBTTagCompound nbt = super.serialize();
/* 23 */     nbt.func_74778_a("cat", this.cat);
/* 24 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 29 */     super.deserialize(nbt);
/* 30 */     this.cat = nbt.func_74779_i("cat");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public String getResearchCategory() { return this.cat; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 40 */     Random r = new Random(getSeed());
/* 41 */     ArrayList<String> cats = new ArrayList<String>();
/* 42 */     for (ResearchCategory rc : ResearchCategories.researchCategories.values()) {
/* 43 */       if (rc.key != "BASICS" && 
/* 44 */         ThaumcraftCapabilities.getKnowledge(player).getKnowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, (ResearchCategory)ResearchCategories.researchCategories
/* 45 */           .get(this.cat)) > 0)
/* 46 */         cats.add(rc.key); 
/*    */     } 
/* 48 */     if (cats.size() > 0) {
/* 49 */       this.cat = (String)cats.get(r.nextInt(cats.size()));
/*    */     }
/* 51 */     return (this.cat != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public int getInspirationCost() { return 2; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String getLocalizedName() { return (new TextComponentTranslation("card.analyze.name", new Object[] { TextFormatting.DARK_BLUE + "" + TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat, new Object[0]))
/* 62 */           .func_150254_d() + TextFormatting.RESET
/* 63 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 68 */   public String getLocalizedText() { return (new TextComponentTranslation("card.analyze.text", new Object[] { TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category." + this.cat, new Object[0]))
/* 69 */           .func_150254_d() + TextFormatting.RESET, TextFormatting.BOLD + (new TextComponentTranslation("tc.research_category.BASICS", new Object[0]))
/* 70 */           .func_150254_d() + TextFormatting.RESET
/* 71 */         })).func_150260_c(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 76 */     ResearchCategory rc = ResearchCategories.getResearchCategory(this.cat);
/* 77 */     int k = ThaumcraftCapabilities.getKnowledge(player).getKnowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc);
/* 78 */     if (k >= 1) {
/* 79 */       data.addTotal("BASICS", 5);
/* 80 */       ThaumcraftCapabilities.getKnowledge(player).addKnowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc, 
/* 81 */           -IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression());
/* 82 */       data.addTotal(this.cat, MathHelper.func_76136_a(player.func_70681_au(), 25, 50));
/* 83 */       return true;
/*    */     } 
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\CardAnalyze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */