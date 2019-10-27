/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class PacketFXBlockMist
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXBlockMist, IMessage> {
/*    */   private long loc;
/*    */   private int color;
/*    */   
/*    */   public PacketFXBlockMist() {}
/*    */   
/*    */   public PacketFXBlockMist(BlockPos pos, int color) {
/* 19 */     this.loc = pos.func_177986_g();
/* 20 */     this.color = color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 26 */     buffer.writeLong(this.loc);
/* 27 */     buffer.writeInt(this.color);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 32 */     this.loc = buffer.readLong();
/* 33 */     this.color = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(PacketFXBlockMist message, MessageContext ctx) {
/* 38 */     FXDispatcher.INSTANCE.drawBlockMistParticles(BlockPos.func_177969_a(message.loc), message.color);
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXBlockMist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */