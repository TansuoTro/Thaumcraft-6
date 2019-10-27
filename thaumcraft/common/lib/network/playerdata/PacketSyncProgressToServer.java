/*     */ package thaumcraft.common.lib.network.playerdata;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchEntry;
/*     */ import thaumcraft.api.research.ResearchStage;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ public class PacketSyncProgressToServer
/*     */   extends Object implements IMessage, IMessageHandler<PacketSyncProgressToServer, IMessage> {
/*     */   private String key;
/*     */   private boolean first;
/*     */   private boolean checks;
/*     */   private boolean noFlags;
/*     */   
/*     */   public PacketSyncProgressToServer() {}
/*     */   
/*     */   public PacketSyncProgressToServer(String key, boolean first, boolean checks, boolean noFlags) {
/*  31 */     this.key = key;
/*  32 */     this.first = first;
/*  33 */     this.checks = checks;
/*  34 */     this.noFlags = noFlags;
/*     */   }
/*     */ 
/*     */   
/*  38 */   public PacketSyncProgressToServer(String key, boolean first) { this(key, first, false, true); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toBytes(ByteBuf buffer) {
/*  43 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/*  44 */     buffer.writeBoolean(this.first);
/*  45 */     buffer.writeBoolean(this.checks);
/*  46 */     buffer.writeBoolean(this.noFlags);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromBytes(ByteBuf buffer) {
/*  51 */     this.key = ByteBufUtils.readUTF8String(buffer);
/*  52 */     this.first = buffer.readBoolean();
/*  53 */     this.checks = buffer.readBoolean();
/*  54 */     this.noFlags = buffer.readBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public IMessage onMessage(final PacketSyncProgressToServer message, final MessageContext ctx) {
/*  59 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/*  60 */     worldServer.func_152344_a(new Runnable() {
/*     */           public void run() {
/*  62 */             EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/*  63 */             if (entityPlayerMP != null && message.first != ThaumcraftCapabilities.knowsResearch(entityPlayerMP, new String[] { PacketSyncProgressToServer.access$100(message) })) {
/*  64 */               if (message.checks && !PacketSyncProgressToServer.this.checkRequisites(entityPlayerMP, message.key)) {
/*     */                 return;
/*     */               }
/*  67 */               if (message.noFlags) ResearchManager.noFlags = true; 
/*  68 */               ResearchManager.progressResearch(entityPlayerMP, message.key);
/*     */             } 
/*     */           }
/*     */         });
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkRequisites(EntityPlayer player, String key) {
/*  77 */     ResearchEntry research = ResearchCategories.getResearch(key);
/*  78 */     if (research.getStages() != null) {
/*  79 */       int currentStage = ThaumcraftCapabilities.getKnowledge(player).getResearchStage(key) - 1;
/*     */       
/*  81 */       if (currentStage < 0) return false; 
/*  82 */       if (currentStage >= research.getStages().length) return true; 
/*  83 */       ResearchStage stage = research.getStages()[currentStage];
/*     */ 
/*     */       
/*  86 */       Object[] o = stage.getObtain();
/*  87 */       if (o != null) {
/*  88 */         for (int a = 0; a < o.length; a++) {
/*  89 */           ItemStack ts = ItemStack.field_190927_a;
/*  90 */           boolean ore = false;
/*  91 */           if (o[a] instanceof ItemStack) {
/*  92 */             ts = (ItemStack)o[a];
/*     */           } else {
/*  94 */             NonNullList<ItemStack> nnl = OreDictionary.getOres((String)o[a]);
/*  95 */             ts = (ItemStack)nnl.get(0);
/*  96 */             ore = true;
/*     */           } 
/*  98 */           if (!InventoryUtils.isPlayerCarryingAmount(player, ts, ore)) {
/*  99 */             return false;
/*     */           }
/*     */         } 
/* 102 */         for (int a = 0; a < o.length; a++) {
/* 103 */           boolean ore = false;
/* 104 */           ItemStack ts = ItemStack.field_190927_a;
/* 105 */           if (o[a] instanceof ItemStack) {
/* 106 */             ts = (ItemStack)o[a];
/*     */           } else {
/* 108 */             NonNullList<ItemStack> nnl = OreDictionary.getOres((String)o[a]);
/* 109 */             ts = (ItemStack)nnl.get(0);
/* 110 */             ore = true;
/*     */           } 
/* 112 */           InventoryUtils.consumePlayerItem(player, ts, true, ore);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 117 */       Object[] c = stage.getCraft();
/* 118 */       if (c != null) {
/* 119 */         for (int a = 0; a < c.length; a++) {
/* 120 */           if (!ThaumcraftCapabilities.getKnowledge(player).isResearchKnown("[#]" + stage.getCraftReference()[a])) {
/* 121 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 127 */       String[] r = stage.getResearch();
/* 128 */       if (r != null) {
/* 129 */         for (int a = 0; a < r.length; a++) {
/* 130 */           if (!ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { r[a] })) {
/* 131 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 137 */       ResearchStage.Knowledge[] arrayOfKnowledge = stage.getKnow();
/* 138 */       if (arrayOfKnowledge != null) {
/* 139 */         for (int a = 0; a < arrayOfKnowledge.length; a++) {
/* 140 */           int pk = ThaumcraftCapabilities.getKnowledge(player).getKnowledge((arrayOfKnowledge[a]).type, (arrayOfKnowledge[a]).category);
/* 141 */           if (pk < (arrayOfKnowledge[a]).amount) {
/* 142 */             return false;
/*     */           }
/*     */         } 
/* 145 */         for (int a = 0; a < arrayOfKnowledge.length; a++) {
/* 146 */           ResearchManager.addKnowledge(player, (arrayOfKnowledge[a]).type, (arrayOfKnowledge[a]).category, -(arrayOfKnowledge[a]).amount * (arrayOfKnowledge[a]).type.getProgression());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketSyncProgressToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */