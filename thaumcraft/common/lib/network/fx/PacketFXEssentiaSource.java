/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.lib.events.EssentiaHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketFXEssentiaSource
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXEssentiaSource, IMessage>
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private byte dx;
/*    */   
/*    */   public PacketFXEssentiaSource(BlockPos p1, byte dx, byte dy, byte dz, int color, int e) {
/* 22 */     this.x = p1.func_177958_n();
/* 23 */     this.y = p1.func_177956_o();
/* 24 */     this.z = p1.func_177952_p();
/* 25 */     this.dx = dx;
/* 26 */     this.dy = dy;
/* 27 */     this.dz = dz;
/* 28 */     this.color = color;
/* 29 */     this.ext = e;
/*    */   }
/*    */   private byte dy; private byte dz; private int color; private int ext;
/*    */   public PacketFXEssentiaSource() {}
/*    */   public void toBytes(ByteBuf buffer) {
/* 34 */     buffer.writeInt(this.x);
/* 35 */     buffer.writeInt(this.y);
/* 36 */     buffer.writeInt(this.z);
/* 37 */     buffer.writeInt(this.color);
/* 38 */     buffer.writeByte(this.dx);
/* 39 */     buffer.writeByte(this.dy);
/* 40 */     buffer.writeByte(this.dz);
/* 41 */     buffer.writeShort(this.ext);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 46 */     this.x = buffer.readInt();
/* 47 */     this.y = buffer.readInt();
/* 48 */     this.z = buffer.readInt();
/* 49 */     this.color = buffer.readInt();
/* 50 */     this.dx = buffer.readByte();
/* 51 */     this.dy = buffer.readByte();
/* 52 */     this.dz = buffer.readByte();
/* 53 */     this.ext = buffer.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(PacketFXEssentiaSource message, MessageContext ctx) {
/* 58 */     int tx = message.x - message.dx;
/* 59 */     int ty = message.y - message.dy;
/* 60 */     int tz = message.z - message.dz;
/* 61 */     String key = message.x + ":" + message.y + ":" + message.z + ":" + tx + ":" + ty + ":" + tz + ":" + message.color;
/* 62 */     if (EssentiaHandler.sourceFX.containsKey(key)) {
/* 63 */       EssentiaHandler.EssentiaSourceFX sf = (EssentiaHandler.EssentiaSourceFX)EssentiaHandler.sourceFX.get(key);
/* 64 */       EssentiaHandler.sourceFX.remove(key);
/* 65 */       EssentiaHandler.sourceFX.put(key, sf);
/*    */     } else {
/* 67 */       EssentiaHandler.sourceFX.put(key, new EssentiaHandler.EssentiaSourceFX(new BlockPos(message.x, message.y, message.z), new BlockPos(tx, ty, tz), message.color, message.ext));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 72 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXEssentiaSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */