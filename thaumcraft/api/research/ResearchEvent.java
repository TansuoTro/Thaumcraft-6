/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResearchEvent
/*    */   extends Event
/*    */ {
/*    */   private final EntityPlayer player;
/*    */   
/* 17 */   public ResearchEvent(EntityPlayer player) { this.player = player; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   public EntityPlayer getPlayer() { return this.player; }
/*    */ 
/*    */   
/*    */   public static class Knowledge
/*    */     extends ResearchEvent
/*    */   {
/*    */     private final IPlayerKnowledge.EnumKnowledgeType type;
/*    */     
/*    */     private final ResearchCategory category;
/*    */     private final int amount;
/*    */     
/*    */     public Knowledge(EntityPlayer player, IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category, int amount) {
/* 34 */       super(player);
/* 35 */       this.type = type;
/* 36 */       this.category = category;
/* 37 */       this.amount = amount;
/*    */     }
/*    */ 
/*    */     
/* 41 */     public IPlayerKnowledge.EnumKnowledgeType getType() { return this.type; }
/*    */ 
/*    */ 
/*    */     
/* 45 */     public ResearchCategory getCategory() { return this.category; }
/*    */ 
/*    */ 
/*    */     
/* 49 */     public int getAmount() { return this.amount; }
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Research
/*    */     extends ResearchEvent
/*    */   {
/*    */     private final String researchKey;
/*    */ 
/*    */     
/*    */     public Research(EntityPlayer player, String researchKey) {
/* 60 */       super(player);
/* 61 */       this.researchKey = researchKey;
/*    */     }
/*    */ 
/*    */     
/* 65 */     public String getResearchKey() { return this.researchKey; }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public boolean isCancelable() { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ResearchEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */