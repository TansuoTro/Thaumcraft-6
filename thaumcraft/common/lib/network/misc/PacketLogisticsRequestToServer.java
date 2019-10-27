/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.api.golems.GolemHelper;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketLogisticsRequestToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketLogisticsRequestToServer, IMessage> {
/*    */   private BlockPos pos;
/*    */   private ItemStack stack;
/*    */   private EnumFacing side;
/*    */   private int stacksize;
/*    */   
/*    */   public PacketLogisticsRequestToServer() {}
/*    */   
/*    */   public PacketLogisticsRequestToServer(BlockPos pos, EnumFacing side, ItemStack stack, int size) {
/* 26 */     this.pos = pos;
/* 27 */     this.stack = stack;
/* 28 */     this.side = side;
/* 29 */     this.stacksize = size;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 34 */     if (this.pos == null || this.side == null) {
/* 35 */       buffer.writeBoolean(false);
/*    */     } else {
/* 37 */       buffer.writeBoolean(true);
/* 38 */       buffer.writeLong(this.pos.func_177986_g());
/* 39 */       buffer.writeByte(this.side.func_176745_a());
/*    */     } 
/*    */     
/* 42 */     Utils.writeItemStackToBuffer(buffer, this.stack);
/* 43 */     buffer.writeInt(this.stacksize);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 48 */     if (buffer.readBoolean()) {
/* 49 */       this.pos = BlockPos.func_177969_a(buffer.readLong());
/* 50 */       this.side = EnumFacing.values()[buffer.readByte()];
/*    */     } 
/* 52 */     this.stack = Utils.readItemStackFromBuffer(buffer);
/* 53 */     this.stacksize = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketLogisticsRequestToServer message, final MessageContext ctx) {
/* 58 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 59 */     worldServer.func_152344_a(new Runnable() {
/*    */           public void run() {
/* 61 */             WorldServer worldServer = (this.val$ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 62 */             EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/* 63 */             int ui = 0;
/* 64 */             while (message.stacksize > 0) {
/* 65 */               ItemStack s = message.stack.func_77946_l();
/* 66 */               s.func_190920_e(Math.min(message.stacksize, s.func_77976_d())); PacketLogisticsRequestToServer packetLogisticsRequestToServer;
/* 67 */               packetLogisticsRequestToServer.stacksize = packetLogisticsRequestToServer.stacksize - s.func_190916_E();
/*    */               
/* 69 */               if (message.pos != null) {
/* 70 */                 GolemHelper.requestProvisioning(worldServer, message.pos, message.side, s, ui);
/*    */               } else {
/* 72 */                 GolemHelper.requestProvisioning(worldServer, (this.val$ctx.getServerHandler()).field_147369_b, s, ui);
/*    */               } 
/* 74 */               ui++;
/*    */             } 
/*    */           }
/*    */         });
/* 78 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketLogisticsRequestToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */