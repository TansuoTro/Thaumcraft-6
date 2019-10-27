/*     */ package thaumcraft.common.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.ResearchEntry;
/*     */ import thaumcraft.api.research.ResearchStage;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandThaumcraft
/*     */   extends CommandBase
/*     */ {
/*     */   private List aliases;
/*     */   
/*     */   public CommandThaumcraft() {
/*  33 */     this.aliases = new ArrayList();
/*  34 */     this.aliases.add("thaumcraft");
/*  35 */     this.aliases.add("thaum");
/*  36 */     this.aliases.add("tc");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  41 */   public String func_71517_b() { return "thaumcraft"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public List<String> func_71514_a() { return this.aliases; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public String func_71518_a(ICommandSender icommandsender) { return "/thaumcraft <action> [<player> [<params>]]"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public int func_82362_a() { return 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public boolean func_82358_a(String[] astring, int i) { return (i == 1); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
/*  66 */     if (args.length == 0) {
/*  67 */       sender.func_145747_a(new TextComponentTranslation("§cInvalid arguments", new Object[0]));
/*  68 */       sender.func_145747_a(new TextComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */       return;
/*     */     } 
/*  71 */     if (args[0].equalsIgnoreCase("reload")) {
/*  72 */       for (ResearchCategory rc : ResearchCategories.researchCategories.values()) {
/*  73 */         rc.research.clear();
/*     */       }
/*  75 */       ResearchManager.parseAllResearch();
/*  76 */       sender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */     }
/*  78 */     else if (args[0].equalsIgnoreCase("help")) {
/*  79 */       sender.func_145747_a(new TextComponentTranslation("§3You can also use /thaum or /tc instead of /thaumcraft.", new Object[0]));
/*  80 */       sender.func_145747_a(new TextComponentTranslation("§3Use this to give research to a player.", new Object[0]));
/*  81 */       sender.func_145747_a(new TextComponentTranslation("  /thaumcraft research <list|player> <list|all|reset|<research>>", new Object[0]));
/*  82 */       sender.func_145747_a(new TextComponentTranslation("§3Use this to remove research from a player.", new Object[0]));
/*  83 */       sender.func_145747_a(new TextComponentTranslation("  /thaumcraft research <player> revoke <research>", new Object[0]));
/*  84 */       sender.func_145747_a(new TextComponentTranslation("§3Use this to give set a players warp level.", new Object[0]));
/*  85 */       sender.func_145747_a(new TextComponentTranslation("  /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
/*  86 */       sender.func_145747_a(new TextComponentTranslation("  not specifying perm or temp will just add normal warp", new Object[0]));
/*  87 */       sender.func_145747_a(new TextComponentTranslation("§3Use this to reload json research data", new Object[0]));
/*  88 */       sender.func_145747_a(new TextComponentTranslation("  /thaumcraft reload", new Object[0]));
/*     */     }
/*  90 */     else if (args.length >= 2) {
/*  91 */       if (args[0].equalsIgnoreCase("research") && args[1].equalsIgnoreCase("list")) {
/*  92 */         listResearch(sender);
/*     */       } else {
/*  94 */         EntityPlayerMP entityplayermp = func_184888_a(server, sender, args[1]);
/*     */         
/*  96 */         if (args[0].equalsIgnoreCase("research")) {
/*  97 */           if (args.length == 3) {
/*  98 */             if (args[2].equalsIgnoreCase("list")) {
/*  99 */               listAllResearch(sender, entityplayermp);
/*     */             }
/* 101 */             else if (args[2].equalsIgnoreCase("all")) {
/* 102 */               giveAllResearch(sender, entityplayermp);
/*     */             }
/* 104 */             else if (args[2].equalsIgnoreCase("reset")) {
/* 105 */               resetResearch(sender, entityplayermp);
/*     */             } else {
/* 107 */               giveResearch(sender, entityplayermp, args[2]);
/*     */             }
/*     */           
/* 110 */           } else if (args.length == 4) {
/* 111 */             if (args[2].equalsIgnoreCase("revoke")) {
/* 112 */               revokeResearch(sender, entityplayermp, args[3]);
/*     */             }
/*     */           } else {
/* 115 */             sender.func_145747_a(new TextComponentTranslation("§cInvalid arguments", new Object[0]));
/* 116 */             sender.func_145747_a(new TextComponentTranslation("§cUse /thaumcraft research <list|player> <list|all|reset|<research>>", new Object[0]));
/*     */           } 
/* 118 */         } else if (args[0].equalsIgnoreCase("warp")) {
/* 119 */           if (args.length >= 4 && args[2].equalsIgnoreCase("set")) {
/* 120 */             int i = func_180528_a(args[3], 0);
/* 121 */             setWarp(sender, entityplayermp, i, (args.length == 5) ? args[4] : "");
/*     */           }
/* 123 */           else if (args.length >= 4 && args[2].equalsIgnoreCase("add")) {
/* 124 */             int i = func_175764_a(args[3], -100, 100);
/* 125 */             addWarp(sender, entityplayermp, i, (args.length == 5) ? args[4] : "");
/*     */           } else {
/*     */             
/* 128 */             sender.func_145747_a(new TextComponentTranslation("§cInvalid arguments", new Object[0]));
/* 129 */             sender.func_145747_a(new TextComponentTranslation("§cUse /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
/*     */           } 
/*     */         } else {
/* 132 */           sender.func_145747_a(new TextComponentTranslation("§cInvalid arguments", new Object[0]));
/* 133 */           sender.func_145747_a(new TextComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 139 */       sender.func_145747_a(new TextComponentTranslation("§cInvalid arguments", new Object[0]));
/* 140 */       sender.func_145747_a(new TextComponentTranslation("§cUse /thaumcraft help to get help", new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type) {
/* 148 */     if (type.equalsIgnoreCase("PERM")) {
/* 149 */       ThaumcraftCapabilities.getWarp(player).set(IPlayerWarp.EnumWarpType.PERMANENT, i);
/*     */     }
/* 151 */     else if (type.equalsIgnoreCase("TEMP")) {
/* 152 */       ThaumcraftCapabilities.getWarp(player).set(IPlayerWarp.EnumWarpType.TEMPORARY, i);
/*     */     } else {
/* 154 */       ThaumcraftCapabilities.getWarp(player).set(IPlayerWarp.EnumWarpType.NORMAL, i);
/*     */     } 
/* 156 */     ThaumcraftCapabilities.getWarp(player).sync(player);
/* 157 */     player.func_145747_a(new TextComponentTranslation("§5" + icommandsender.func_70005_c_() + " set your warp to " + i, new Object[0]));
/* 158 */     icommandsender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   private void addWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type) {
/* 163 */     if (type.equalsIgnoreCase("PERM")) {
/* 164 */       ThaumcraftCapabilities.getWarp(player).add(IPlayerWarp.EnumWarpType.PERMANENT, i);
/*     */     }
/* 166 */     else if (type.equalsIgnoreCase("TEMP")) {
/* 167 */       ThaumcraftCapabilities.getWarp(player).add(IPlayerWarp.EnumWarpType.TEMPORARY, i);
/*     */     } else {
/* 169 */       ThaumcraftCapabilities.getWarp(player).add(IPlayerWarp.EnumWarpType.NORMAL, i);
/*     */     } 
/* 171 */     ThaumcraftCapabilities.getWarp(player).sync(player);
/* 172 */     PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)0, i), player);
/* 173 */     player.func_145747_a(new TextComponentTranslation("§5" + icommandsender.func_70005_c_() + " added " + i + " warp to your total.", new Object[0]));
/* 174 */     icommandsender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */   
/*     */   private void listResearch(ICommandSender icommandsender) {
/* 178 */     Collection<ResearchCategory> rc = ResearchCategories.researchCategories.values();
/* 179 */     for (ResearchCategory cat : rc) {
/* 180 */       Collection<ResearchEntry> rl = cat.research.values();
/* 181 */       for (ResearchEntry ri : rl) {
/* 182 */         icommandsender.func_145747_a(new TextComponentTranslation("§5" + ri.getKey(), new Object[0]));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void giveResearch(ICommandSender icommandsender, EntityPlayerMP player, String research) {
/* 188 */     if (ResearchCategories.getResearch(research) != null) {
/*     */       
/* 190 */       giveRecursiveResearch(player, research);
/* 191 */       ThaumcraftCapabilities.getKnowledge(player).sync(player);
/*     */       
/* 193 */       player.func_145747_a(new TextComponentTranslation("§5" + icommandsender.func_70005_c_() + " gave you " + research + " research and its requisites.", new Object[0]));
/* 194 */       icommandsender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */     } else {
/* 196 */       icommandsender.func_145747_a(new TextComponentTranslation("§cResearch does not exist.", new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void giveRecursiveResearch(EntityPlayer player, String research) {
/* 202 */     if (research.contains("@")) {
/* 203 */       int i = research.indexOf("@");
/* 204 */       research = research.substring(0, i);
/*     */     } 
/*     */     
/* 207 */     ResearchEntry res = ResearchCategories.getResearch(research);
/* 208 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*     */     
/* 210 */     if (!knowledge.isResearchComplete(research)) {
/*     */       
/* 212 */       if (res != null && res.getParents() != null) {
/* 213 */         for (String rsi : res.getParentsStripped()) {
/* 214 */           giveRecursiveResearch(player, rsi);
/*     */         }
/*     */       }
/* 217 */       if (res != null && res.getStages() != null) {
/* 218 */         for (ResearchStage page : res.getStages()) {
/* 219 */           if (page.getResearch() != null) {
/* 220 */             for (String gr : page.getResearch()) {
/* 221 */               ResearchManager.completeResearch(player, gr);
/*     */             }
/*     */           }
/*     */         } 
/*     */       }
/* 226 */       ResearchManager.completeResearch(player, research);
/*     */       
/* 228 */       for (String rc : ResearchCategories.researchCategories.keySet()) {
/* 229 */         for (ResearchEntry ri : (ResearchCategories.getResearchCategory(rc)).research.values()) {
/* 230 */           if (ri.getStages() != null) {
/* 231 */             for (ResearchStage stage : ri.getStages()) {
/* 232 */               if (stage.getResearch() != null && Arrays.asList(stage.getResearch()).contains(research)) {
/* 233 */                 ThaumcraftCapabilities.getKnowledge(player).setResearchFlag(ri.getKey(), IPlayerKnowledge.EnumResearchFlag.PAGE);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 242 */       if (res != null && res.getSiblings() != null)
/* 243 */         for (String rsi : res.getSiblings()) {
/* 244 */           giveRecursiveResearch(player, rsi);
/*     */         } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void revokeResearch(ICommandSender icommandsender, EntityPlayerMP player, String research) {
/* 250 */     if (ResearchCategories.getResearch(research) != null) {
/*     */       
/* 252 */       revokeRecursiveResearch(player, research);
/* 253 */       ThaumcraftCapabilities.getKnowledge(player).sync(player);
/*     */       
/* 255 */       player.func_145747_a(new TextComponentTranslation("§5" + icommandsender.func_70005_c_() + " removed " + research + " research and its children.", new Object[0]));
/* 256 */       icommandsender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */     } else {
/* 258 */       icommandsender.func_145747_a(new TextComponentTranslation("§cResearch does not exist.", new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void revokeRecursiveResearch(EntityPlayer player, String research) {
/* 265 */     if (research.contains("@")) {
/* 266 */       int i = research.indexOf("@");
/* 267 */       research = research.substring(0, i);
/*     */     } 
/*     */     
/* 270 */     ResearchEntry res = ResearchCategories.getResearch(research);
/* 271 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*     */     
/* 273 */     if (knowledge.isResearchComplete(research)) {
/* 274 */       for (String rc : ResearchCategories.researchCategories.keySet()) {
/* 275 */         for (ResearchEntry ri : (ResearchCategories.getResearchCategory(rc)).research.values()) {
/* 276 */           if (ri != null && ri.getParents() != null && knowledge.isResearchComplete(ri.getKey()))
/* 277 */             for (String rsi : ri.getParentsStripped()) {
/* 278 */               if (rsi.equals(research)) {
/* 279 */                 revokeRecursiveResearch(player, ri.getKey());
/*     */               }
/*     */             }  
/*     */         } 
/*     */       } 
/* 284 */       ThaumcraftCapabilities.getKnowledge(player).removeResearch(research);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void listAllResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 290 */     String ss = "";
/* 291 */     for (String key : ThaumcraftCapabilities.getKnowledge(player).getResearchList()) {
/* 292 */       if (ss.length() != 0) ss = ss + ", "; 
/* 293 */       ss = ss + key;
/*     */     } 
/* 295 */     icommandsender.func_145747_a(new TextComponentTranslation("§5Research for " + player.func_70005_c_(), new Object[0]));
/* 296 */     icommandsender.func_145747_a(new TextComponentTranslation("§5" + ss, new Object[0]));
/*     */   }
/*     */   
/*     */   void giveAllResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 300 */     Collection<ResearchCategory> rc = ResearchCategories.researchCategories.values();
/* 301 */     for (ResearchCategory cat : rc) {
/* 302 */       Collection<ResearchEntry> rl = cat.research.values();
/* 303 */       for (ResearchEntry ri : rl) {
/* 304 */         giveRecursiveResearch(player, ri.getKey());
/*     */       }
/*     */     } 
/*     */     
/* 308 */     player.func_145747_a(new TextComponentTranslation("§5" + icommandsender.func_70005_c_() + " has given you all research.", new Object[0]));
/* 309 */     icommandsender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void resetResearch(ICommandSender icommandsender, EntityPlayerMP player) {
/* 315 */     ThaumcraftCapabilities.getKnowledge(player).clear();
/*     */     
/* 317 */     Collection<ResearchCategory> rc = ResearchCategories.researchCategories.values();
/* 318 */     for (ResearchCategory cat : rc) {
/* 319 */       Collection<ResearchEntry> res = cat.research.values();
/* 320 */       for (ResearchEntry ri : res) {
/* 321 */         if (ri.hasMeta(ResearchEntry.EnumResearchMeta.AUTOUNLOCK)) {
/* 322 */           ResearchManager.completeResearch(player, ri.getKey(), false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 327 */     player.func_145747_a(new TextComponentTranslation("§5" + icommandsender.func_70005_c_() + " has reset all your research.", new Object[0]));
/* 328 */     icommandsender.func_145747_a(new TextComponentTranslation("§5Success!", new Object[0]));
/*     */     
/* 330 */     ThaumcraftCapabilities.getKnowledge(player).sync(player);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\CommandThaumcraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */