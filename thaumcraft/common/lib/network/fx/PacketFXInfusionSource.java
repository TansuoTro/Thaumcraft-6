/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.tiles.crafting.TileInfusionMatrix;
/*    */ 
/*    */ public class PacketFXInfusionSource
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXInfusionSource, IMessage>
/*    */ {
/*    */   private long p1;
/*    */   private long p2;
/*    */   private int color;
/*    */   
/*    */   public PacketFXInfusionSource() {}
/*    */   
/*    */   public PacketFXInfusionSource(BlockPos pos, BlockPos pos2, int color) {
/* 23 */     this.p1 = pos.func_177986_g();
/* 24 */     this.p2 = pos2.func_177986_g();
/* 25 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 30 */     buffer.writeLong(this.p1);
/* 31 */     buffer.writeLong(this.p2);
/* 32 */     buffer.writeInt(this.color);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 37 */     this.p1 = buffer.readLong();
/* 38 */     this.p2 = buffer.readLong();
/* 39 */     this.color = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(PacketFXInfusionSource message, MessageContext ctx) {
/* 44 */     BlockPos p1 = BlockPos.func_177969_a(message.p1);
/* 45 */     BlockPos p2 = BlockPos.func_177969_a(message.p2);
/* 46 */     String key = p2.func_177958_n() + ":" + p2.func_177956_o() + ":" + p2.func_177952_p() + ":" + message.color;
/* 47 */     TileEntity tile = Thaumcraft.proxy.getClientWorld().func_175625_s(p1);
/* 48 */     if (tile != null && tile instanceof TileInfusionMatrix) {
/* 49 */       int count = 15;
/* 50 */       if (Thaumcraft.proxy.getClientWorld().func_175625_s(p2) != null && Thaumcraft.proxy
/* 51 */         .getClientWorld().func_175625_s(p2) instanceof thaumcraft.common.tiles.crafting.TilePedestal)
/* 52 */         count = 60; 
/* 53 */       TileInfusionMatrix is = (TileInfusionMatrix)tile;
/*    */       
/* 55 */       if (is.sourceFX.containsKey(key)) {
/* 56 */         TileInfusionMatrix.SourceFX sf = (TileInfusionMatrix.SourceFX)is.sourceFX.get(key);
/* 57 */         sf.ticks = count;
/* 58 */         is.sourceFX.put(key, sf);
/*    */       } else {
/* 60 */         is.getClass(); is.sourceFX.put(key, new TileInfusionMatrix.SourceFX(is, p2, count, message.color));
/*    */       } 
/*    */     } 
/* 63 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXInfusionSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */