/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*    */ 
/*    */ public class PacketStartTheoryToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketStartTheoryToServer, IMessage>
/*    */ {
/*    */   private long pos;
/* 22 */   private Set<String> aids = new HashSet();
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketStartTheoryToServer(BlockPos pos, Set<String> aids) {
/* 27 */     this.pos = pos.func_177986_g();
/* 28 */     this.aids = aids;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 33 */     buffer.writeLong(this.pos);
/* 34 */     buffer.writeByte(this.aids.size());
/* 35 */     for (String aid : this.aids) {
/* 36 */       ByteBufUtils.writeUTF8String(buffer, aid);
/*    */     }
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 41 */     this.pos = buffer.readLong();
/* 42 */     int s = buffer.readByte();
/* 43 */     for (int a = 0; a < s; a++) {
/* 44 */       this.aids.add(ByteBufUtils.readUTF8String(buffer));
/*    */     }
/*    */   }
/*    */   
/*    */   public IMessage onMessage(final PacketStartTheoryToServer message, final MessageContext ctx) {
/* 49 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 50 */     worldServer.func_152344_a(new Runnable() {
/*    */           public void run() {
/* 52 */             WorldServer worldServer = (this.val$ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 53 */             EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/* 54 */             BlockPos bp = BlockPos.func_177969_a(message.pos);
/*    */             
/* 56 */             if (worldServer != null && entityPlayerMP != null && entityPlayerMP instanceof EntityPlayer && bp != null) {
/* 57 */               TileEntity te = worldServer.func_175625_s(bp);
/* 58 */               if (te != null && te instanceof TileResearchTable) {
/* 59 */                 ((TileResearchTable)te).startNewTheory((EntityPlayer)entityPlayerMP, message.aids);
/*    */               }
/*    */             } 
/*    */           }
/*    */         });
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   public PacketStartTheoryToServer() {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketStartTheoryToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */