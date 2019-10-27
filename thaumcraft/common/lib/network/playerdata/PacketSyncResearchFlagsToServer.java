/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketSyncResearchFlagsToServer extends Object implements IMessage, IMessageHandler<PacketSyncResearchFlagsToServer, IMessage> {
/*    */   String key;
/*    */   byte flags;
/*    */   
/*    */   public PacketSyncResearchFlagsToServer() {}
/*    */   
/*    */   public PacketSyncResearchFlagsToServer(EntityPlayer player, String key) {
/* 22 */     this.key = key;
/* 23 */     this.flags = Utils.pack(new boolean[] {
/* 24 */           ThaumcraftCapabilities.getKnowledge(player).hasResearchFlag(key, IPlayerKnowledge.EnumResearchFlag.PAGE), 
/* 25 */           ThaumcraftCapabilities.getKnowledge(player).hasResearchFlag(key, IPlayerKnowledge.EnumResearchFlag.POPUP), 
/* 26 */           ThaumcraftCapabilities.getKnowledge(player).hasResearchFlag(key, IPlayerKnowledge.EnumResearchFlag.RESEARCH)
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 32 */     ByteBufUtils.writeUTF8String(buffer, this.key);
/* 33 */     buffer.writeByte(this.flags);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 38 */     this.key = ByteBufUtils.readUTF8String(buffer);
/* 39 */     this.flags = buffer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketSyncResearchFlagsToServer message, final MessageContext ctx) {
/* 44 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 45 */     worldServer.func_152344_a(new Runnable() {
/*    */           public void run() {
/* 47 */             boolean[] b = Utils.unpack(this.val$message.flags);
/* 48 */             if ((this.val$ctx.getServerHandler()).field_147369_b != null) {
/* 49 */               EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/* 50 */               if (b[0]) {
/* 51 */                 ThaumcraftCapabilities.getKnowledge(entityPlayerMP).setResearchFlag(this.val$message.key, IPlayerKnowledge.EnumResearchFlag.PAGE);
/*    */               } else {
/* 53 */                 ThaumcraftCapabilities.getKnowledge(entityPlayerMP).clearResearchFlag(this.val$message.key, IPlayerKnowledge.EnumResearchFlag.PAGE);
/*    */               } 
/* 55 */               if (b[1]) {
/* 56 */                 ThaumcraftCapabilities.getKnowledge(entityPlayerMP).setResearchFlag(this.val$message.key, IPlayerKnowledge.EnumResearchFlag.POPUP);
/*    */               } else {
/* 58 */                 ThaumcraftCapabilities.getKnowledge(entityPlayerMP).clearResearchFlag(this.val$message.key, IPlayerKnowledge.EnumResearchFlag.POPUP);
/*    */               } 
/* 60 */               if (b[2]) {
/* 61 */                 ThaumcraftCapabilities.getKnowledge(entityPlayerMP).setResearchFlag(this.val$message.key, IPlayerKnowledge.EnumResearchFlag.RESEARCH);
/*    */               } else {
/* 63 */                 ThaumcraftCapabilities.getKnowledge(entityPlayerMP).clearResearchFlag(this.val$message.key, IPlayerKnowledge.EnumResearchFlag.RESEARCH);
/*    */               } 
/*    */             }  }
/*    */         });
/* 67 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketSyncResearchFlagsToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */