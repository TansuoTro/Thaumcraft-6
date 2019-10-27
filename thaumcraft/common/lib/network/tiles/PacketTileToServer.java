/*    */ package thaumcraft.common.lib.network.tiles;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ 
/*    */ public class PacketTileToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketTileToServer, IMessage> {
/*    */   private long pos;
/*    */   private NBTTagCompound nbt;
/*    */   
/*    */   public PacketTileToServer() {}
/*    */   
/*    */   public PacketTileToServer(BlockPos pos, NBTTagCompound nbt) {
/* 23 */     this.pos = pos.func_177986_g();
/* 24 */     this.nbt = nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 29 */     buffer.writeLong(this.pos);
/* 30 */     Utils.writeNBTTagCompoundToBuffer(buffer, this.nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 35 */     this.pos = buffer.readLong();
/* 36 */     this.nbt = Utils.readNBTTagCompoundFromBuffer(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketTileToServer message, final MessageContext ctx) {
/* 41 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 42 */     worldServer.func_152344_a(new Runnable() { public void run() {
/* 43 */             WorldServer worldServer = (this.val$ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 44 */             BlockPos bp = BlockPos.func_177969_a(message.pos);
/* 45 */             if (worldServer != null && bp != null) {
/* 46 */               TileEntity te = worldServer.func_175625_s(bp);
/* 47 */               if (te != null && te instanceof TileThaumcraft)
/* 48 */                 ((TileThaumcraft)te).messageFromClient((message.nbt == null) ? new NBTTagCompound() : message.nbt, (this.val$ctx.getServerHandler()).field_147369_b); 
/*    */             } 
/*    */           } }
/*    */       );
/* 52 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\tiles\PacketTileToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */