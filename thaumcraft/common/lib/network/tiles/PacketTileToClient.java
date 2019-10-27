/*    */ package thaumcraft.common.lib.network.tiles;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ 
/*    */ public class PacketTileToClient
/*    */   extends Object implements IMessage, IMessageHandler<PacketTileToClient, IMessage> {
/*    */   private long pos;
/*    */   private NBTTagCompound nbt;
/*    */   
/*    */   public PacketTileToClient() {}
/*    */   
/*    */   public PacketTileToClient(BlockPos pos, NBTTagCompound nbt) {
/* 24 */     this.pos = pos.func_177986_g();
/* 25 */     this.nbt = nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 30 */     buffer.writeLong(this.pos);
/* 31 */     Utils.writeNBTTagCompoundToBuffer(buffer, this.nbt);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 36 */     this.pos = buffer.readLong();
/* 37 */     this.nbt = Utils.readNBTTagCompoundFromBuffer(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketTileToClient message, MessageContext ctx) {
/* 42 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() {
/* 43 */             World world = Thaumcraft.proxy.getClientWorld();
/* 44 */             BlockPos bp = BlockPos.func_177969_a(message.pos);
/* 45 */             if (world != null && bp != null) {
/* 46 */               TileEntity te = world.func_175625_s(bp);
/* 47 */               if (te != null && te instanceof TileThaumcraft)
/* 48 */                 ((TileThaumcraft)te).messageFromServer((message.nbt == null) ? new NBTTagCompound() : message.nbt); 
/*    */             } 
/*    */           } }
/*    */       );
/* 52 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\tiles\PacketTileToClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */