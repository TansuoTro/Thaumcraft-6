/*     */ package thaumcraft.api.capabilities;
/*     */ 
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraftforge.common.util.INBTSerializable;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IPlayerKnowledge
/*     */   extends INBTSerializable<NBTTagCompound>
/*     */ {
/*     */   void clear();
/*     */   
/*     */   EnumResearchStatus getResearchStatus(@Nonnull String paramString);
/*     */   
/*     */   boolean isResearchComplete(String paramString);
/*     */   
/*     */   boolean isResearchKnown(String paramString);
/*     */   
/*     */   int getResearchStage(@Nonnull String paramString);
/*     */   
/*     */   boolean addResearch(@Nonnull String paramString);
/*     */   
/*     */   boolean setResearchStage(@Nonnull String paramString, int paramInt);
/*     */   
/*     */   boolean removeResearch(@Nonnull String paramString);
/*     */   
/*     */   @Nonnull
/*     */   Set<String> getResearchList();
/*     */   
/*     */   boolean setResearchFlag(@Nonnull String paramString, @Nonnull EnumResearchFlag paramEnumResearchFlag);
/*     */   
/*     */   boolean clearResearchFlag(@Nonnull String paramString, @Nonnull EnumResearchFlag paramEnumResearchFlag);
/*     */   
/*     */   boolean hasResearchFlag(@Nonnull String paramString, @Nonnull EnumResearchFlag paramEnumResearchFlag);
/*     */   
/*     */   boolean addKnowledge(@Nonnull EnumKnowledgeType paramEnumKnowledgeType, ResearchCategory paramResearchCategory, int paramInt);
/*     */   
/*     */   int getKnowledge(@Nonnull EnumKnowledgeType paramEnumKnowledgeType, ResearchCategory paramResearchCategory);
/*     */   
/*     */   int getKnowledgeRaw(@Nonnull EnumKnowledgeType paramEnumKnowledgeType, ResearchCategory paramResearchCategory);
/*     */   
/*     */   void sync(EntityPlayerMP paramEntityPlayerMP);
/*     */   
/*     */   public enum EnumResearchStatus
/*     */   {
/*  51 */     UNKNOWN, COMPLETE, IN_PROGRESS;
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
/*     */   public enum EnumKnowledgeType
/*     */   {
/* 152 */     THEORY(32, true, "T"),
/* 153 */     OBSERVATION(16, true, "O");
/*     */     
/*     */     private short progression;
/*     */     private boolean hasFields;
/*     */     private String abbr;
/*     */     
/*     */     EnumKnowledgeType(int progression, boolean hasFields, String abbr) {
/* 160 */       this.progression = (short)progression;
/* 161 */       this.hasFields = hasFields;
/* 162 */       this.abbr = abbr;
/*     */     }
/*     */ 
/*     */     
/* 166 */     public int getProgression() { return this.progression; }
/*     */ 
/*     */ 
/*     */     
/* 170 */     public boolean hasFields() { return this.hasFields; }
/*     */ 
/*     */ 
/*     */     
/* 174 */     public String getAbbreviation() { return this.abbr; }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum EnumResearchFlag
/*     */   {
/* 181 */     PAGE,
/*     */     
/* 183 */     RESEARCH,
/*     */     
/* 185 */     POPUP;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\capabilities\IPlayerKnowledge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */