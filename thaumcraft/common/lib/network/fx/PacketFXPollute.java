/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class PacketFXPollute extends Object implements IMessage, IMessageHandler<PacketFXPollute, IMessage> {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private byte amount;
/*    */   
/*    */   public PacketFXPollute() {}
/*    */   
/*    */   public PacketFXPollute(BlockPos pos, float amt) {
/* 19 */     this.x = pos.func_177958_n();
/* 20 */     this.y = pos.func_177956_o();
/* 21 */     this.z = pos.func_177952_p();
/* 22 */     if (amt < 1.0F && amt > 0.0F) amt = 1.0F; 
/* 23 */     this.amount = (byte)(int)amt;
/*    */   }
/*    */ 
/*    */   
/* 27 */   public PacketFXPollute(BlockPos pos, float amt, boolean vary) { this(pos, amt); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 33 */     buffer.writeInt(this.x);
/* 34 */     buffer.writeInt(this.y);
/* 35 */     buffer.writeInt(this.z);
/* 36 */     buffer.writeByte(this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 41 */     this.x = buffer.readInt();
/* 42 */     this.y = buffer.readInt();
/* 43 */     this.z = buffer.readInt();
/* 44 */     this.amount = buffer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(PacketFXPollute message, MessageContext ctx) {
/* 49 */     for (int a = 0; a < Math.min(40, message.amount); a++)
/* 50 */       FXDispatcher.INSTANCE.drawPollutionParticles(new BlockPos(message.x, message.y, message.z)); 
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXPollute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */