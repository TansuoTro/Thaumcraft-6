/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*    */ 
/*    */ public class PacketFocusNameToServer
/*    */   extends Object implements IMessage, IMessageHandler<PacketFocusNameToServer, IMessage> {
/*    */   private long loc;
/*    */   private String name;
/*    */   
/*    */   public PacketFocusNameToServer() {}
/*    */   
/*    */   public PacketFocusNameToServer(BlockPos pos, String name) {
/* 21 */     this.loc = pos.func_177986_g();
/* 22 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 27 */     buffer.writeLong(this.loc);
/* 28 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 33 */     this.loc = buffer.readLong();
/* 34 */     this.name = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFocusNameToServer message, final MessageContext ctx) {
/* 39 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 40 */     worldServer.func_152344_a(new Runnable() {
/* 41 */           public void run() { if ((this.val$ctx.getServerHandler()).field_147369_b == null)
/* 42 */               return;  BlockPos pos = BlockPos.func_177969_a(message.loc);
/* 43 */             TileEntity rt = (this.val$ctx.getServerHandler()).field_147369_b.field_70170_p.func_175625_s(pos);
/* 44 */             if (rt != null && rt instanceof TileFocalManipulator) {
/* 45 */               ((TileFocalManipulator)rt).focusName = message.name;
/* 46 */               ((TileFocalManipulator)rt).func_70296_d();
/*    */             }  }
/*    */         });
/* 49 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketFocusNameToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */