/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class PacketPlayerFlagToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketPlayerFlagToServer, IMessage> {
/*    */   byte flag;
/*    */   
/*    */   public PacketPlayerFlagToServer() {}
/*    */   
/* 18 */   public PacketPlayerFlagToServer(EntityLivingBase player, int i) { this.flag = (byte)i; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void toBytes(ByteBuf buffer) { buffer.writeByte(this.flag); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void fromBytes(ByteBuf buffer) { this.flag = buffer.readByte(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketPlayerFlagToServer message, final MessageContext ctx) {
/* 33 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 34 */     worldServer.func_152344_a(new Runnable() {
/* 35 */           public void run() { if ((this.val$ctx.getServerHandler()).field_147369_b != null) {
/* 36 */               EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/* 37 */               switch (this.val$message.flag) {
/*    */                 case 1:
/* 39 */                   entityPlayerMP.field_70143_R = 0.0F;
/*    */                   break;
/*    */               } 
/*    */             }  }
/*    */         });
/* 44 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketPlayerFlagToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */