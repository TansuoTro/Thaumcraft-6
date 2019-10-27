/*     */ package thaumcraft.api.capabilities;
/*     */ 
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityInject;
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
/*     */ public class ThaumcraftCapabilities
/*     */ {
/*     */   @CapabilityInject(IPlayerKnowledge.class)
/*  22 */   public static final Capability<IPlayerKnowledge> KNOWLEDGE = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  29 */   public static IPlayerKnowledge getKnowledge(@Nonnull EntityPlayer player) { return (IPlayerKnowledge)player.getCapability(KNOWLEDGE, null); }
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
/*     */   public static boolean knowsResearch(@Nonnull EntityPlayer player, @Nonnull String... research) {
/*  44 */     for (String r : research) {
/*  45 */       if (r.contains("&&"))
/*  46 */       { String[] rr = r.split("&&");
/*  47 */         if (!knowsResearch(player, rr)) return false;
/*     */          }
/*  49 */       else if (r.contains("||"))
/*  50 */       { String[] rr = r.split("||");
/*  51 */         for (String str : rr) {
/*  52 */           if (knowsResearch(player, new String[] { str })) return true; 
/*     */         }  }
/*  54 */       else if (!getKnowledge(player).isResearchKnown(r)) { return false; }
/*     */     
/*  56 */     }  return true;
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
/*     */   public static boolean knowsResearchStrict(@Nonnull EntityPlayer player, @Nonnull String... research) {
/*  71 */     for (String r : research) {
/*  72 */       if (r.contains("&&"))
/*  73 */       { String[] rr = r.split("&&");
/*  74 */         if (!knowsResearchStrict(player, rr)) return false;
/*     */          }
/*  76 */       else if (r.contains("||"))
/*  77 */       { String[] rr = r.split("||");
/*  78 */         for (String str : rr) {
/*  79 */           if (knowsResearchStrict(player, new String[] { str })) return true; 
/*     */         }  }
/*  81 */       else if (r.contains("@"))
/*  82 */       { if (!getKnowledge(player).isResearchKnown(r)) return false;
/*     */          }
/*  84 */       else if (!getKnowledge(player).isResearchComplete(r)) { return false; }
/*     */     
/*     */     } 
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @CapabilityInject(IPlayerWarp.class)
/*  97 */   public static final Capability<IPlayerWarp> WARP = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public static IPlayerWarp getWarp(@Nonnull EntityPlayer player) { return (IPlayerWarp)player.getCapability(WARP, null); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\capabilities\ThaumcraftCapabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */