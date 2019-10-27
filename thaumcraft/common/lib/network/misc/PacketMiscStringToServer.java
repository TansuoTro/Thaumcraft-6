/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.container.ContainerLogistics;
/*    */ 
/*    */ public class PacketMiscStringToServer
/*    */   extends Object implements IMessage, IMessageHandler<PacketMiscStringToServer, IMessage> {
/*    */   private int id;
/*    */   private String text;
/*    */   
/*    */   public PacketMiscStringToServer() {}
/*    */   
/*    */   public PacketMiscStringToServer(int id, String text) {
/* 20 */     this.id = id;
/* 21 */     this.text = text;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 26 */     buffer.writeInt(this.id);
/* 27 */     ByteBufUtils.writeUTF8String(buffer, this.text);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 32 */     this.id = buffer.readInt();
/* 33 */     this.text = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketMiscStringToServer message, final MessageContext ctx) {
/* 38 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 39 */     worldServer.func_152344_a(new Runnable() { public void run() {
/* 40 */             EntityPlayerMP player = (this.val$ctx.getServerHandler()).field_147369_b;
/*    */             
/* 42 */             if (PacketMiscStringToServer.this.id == 0 && player.field_71070_bA instanceof ContainerLogistics) {
/*    */               
/* 44 */               ContainerLogistics container = (ContainerLogistics)player.field_71070_bA;
/* 45 */               container.searchText = message.text;
/* 46 */               container.refreshItemList(true);
/*    */             } 
/*    */           } }
/*    */       );
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketMiscStringToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */