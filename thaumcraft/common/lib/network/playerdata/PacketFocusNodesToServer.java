/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ import thaumcraft.common.tiles.crafting.FocusElementNode;
/*    */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*    */ 
/*    */ public class PacketFocusNodesToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFocusNodesToServer, IMessage> {
/*    */   private long loc;
/* 20 */   private HashMap<Integer, FocusElementNode> data = new HashMap();
/*    */   
/*    */   private String name;
/*    */ 
/*    */   
/*    */   public PacketFocusNodesToServer(BlockPos pos, HashMap<Integer, FocusElementNode> data, String name) {
/* 26 */     this.loc = pos.func_177986_g();
/* 27 */     this.data = data;
/* 28 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 33 */     buffer.writeLong(this.loc);
/* 34 */     buffer.writeByte(this.data.size());
/* 35 */     for (FocusElementNode node : this.data.values())
/* 36 */       Utils.writeNBTTagCompoundToBuffer(buffer, node.serialize()); 
/* 37 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 42 */     this.loc = buffer.readLong();
/* 43 */     int m = buffer.readByte();
/* 44 */     for (int a = 0; a < m; a++) {
/* 45 */       FocusElementNode node = new FocusElementNode();
/* 46 */       node.deserialize(Utils.readNBTTagCompoundFromBuffer(buffer));
/* 47 */       this.data.put(Integer.valueOf(node.id), node);
/*    */     } 
/* 49 */     this.name = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFocusNodesToServer message, final MessageContext ctx) {
/* 54 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 55 */     worldServer.func_152344_a(new Runnable() {
/* 56 */           public void run() { if ((this.val$ctx.getServerHandler()).field_147369_b == null)
/* 57 */               return;  BlockPos pos = BlockPos.func_177969_a(message.loc);
/* 58 */             TileEntity rt = (this.val$ctx.getServerHandler()).field_147369_b.field_70170_p.func_175625_s(pos);
/* 59 */             if (rt != null && rt instanceof TileFocalManipulator) {
/* 60 */               ((TileFocalManipulator)rt).data.clear();
/* 61 */               ((TileFocalManipulator)rt).data = message.data;
/* 62 */               ((TileFocalManipulator)rt).focusName = message.name;
/* 63 */               ((TileFocalManipulator)rt).func_70296_d();
/*    */             }  }
/*    */         });
/* 66 */     return null;
/*    */   }
/*    */   
/*    */   public PacketFocusNodesToServer() {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketFocusNodesToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */