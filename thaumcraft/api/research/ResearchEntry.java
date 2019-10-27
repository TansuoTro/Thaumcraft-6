/*     */ package thaumcraft.api.research;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResearchEntry
/*     */ {
/*     */   String key;
/*     */   String category;
/*     */   String name;
/*     */   String[] parents;
/*     */   String[] siblings;
/*     */   int displayColumn;
/*     */   int displayRow;
/*     */   Object[] icons;
/*     */   EnumResearchMeta[] meta;
/*     */   ItemStack[] rewardItem;
/*     */   ResearchStage.Knowledge[] rewardKnow;
/*     */   ResearchStage[] stages;
/*     */   ResearchAddendum[] addenda;
/*     */   
/*     */   public enum EnumResearchMeta
/*     */   {
/*  72 */     ROUND,
/*  73 */     SPIKY,
/*  74 */     REVERSE,
/*  75 */     HIDDEN,
/*  76 */     AUTOUNLOCK,
/*  77 */     HEX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public String getKey() { return this.key; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void setKey(String key) { this.key = key; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public String getCategory() { return this.category; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setCategory(String category) { this.category = category; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public String getLocalizedName() { return I18n.func_74838_a(getName()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public String[] getParents() { return this.parents; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getParentsClean() {
/* 150 */     String[] out = null;
/* 151 */     if (this.parents != null) {
/* 152 */       out = getParentsStripped();
/* 153 */       for (int q = 0; q < out.length; q++) {
/* 154 */         if (out[q].contains("@"))
/* 155 */           out[q] = out[q].substring(0, out[q].indexOf("@")); 
/*     */       } 
/*     */     } 
/* 158 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getParentsStripped() {
/* 166 */     String[] out = null;
/* 167 */     if (this.parents != null) {
/* 168 */       out = new String[this.parents.length];
/* 169 */       for (int q = 0; q < out.length; q++) {
/* 170 */         out[q] = "" + this.parents[q];
/* 171 */         if (out[q].startsWith("~"))
/* 172 */           out[q] = out[q].substring(1); 
/*     */       } 
/*     */     } 
/* 175 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public void setParents(String[] parents) { this.parents = parents; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public String[] getSiblings() { return this.siblings; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setSiblings(String[] siblings) { this.siblings = siblings; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public int getDisplayColumn() { return this.displayColumn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public void setDisplayColumn(int displayColumn) { this.displayColumn = displayColumn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public int getDisplayRow() { return this.displayRow; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public void setDisplayRow(int displayRow) { this.displayRow = displayRow; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public Object[] getIcons() { return this.icons; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   public void setIcons(Object[] icons) { this.icons = icons; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public EnumResearchMeta[] getMeta() { return this.meta; }
/*     */ 
/*     */ 
/*     */   
/* 249 */   public boolean hasMeta(EnumResearchMeta me) { return (this.meta == null) ? false : Arrays.asList(this.meta).contains(me); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public void setMeta(EnumResearchMeta[] meta) { this.meta = meta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public ResearchStage[] getStages() { return this.stages; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public void setStages(ResearchStage[] stages) { this.stages = stages; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public ItemStack[] getRewardItem() { return this.rewardItem; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setRewardItem(ItemStack[] rewardItem) { this.rewardItem = rewardItem; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public ResearchStage.Knowledge[] getRewardKnow() { return this.rewardKnow; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public void setRewardKnow(Knowledge[] rewardKnow) { this.rewardKnow = rewardKnow; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public ResearchAddendum[] getAddenda() { return this.addenda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public void setAddenda(ResearchAddendum[] addenda) { this.addenda = addenda; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ResearchEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */