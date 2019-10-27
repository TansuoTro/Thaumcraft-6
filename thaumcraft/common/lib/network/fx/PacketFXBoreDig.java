/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.lib.events.ServerEvents;
/*    */ 
/*    */ public class PacketFXBoreDig
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXBoreDig, IMessage> {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private int bore;
/*    */   private int delay;
/*    */   
/*    */   public PacketFXBoreDig() {}
/*    */   
/*    */   public PacketFXBoreDig(BlockPos pos, Entity bore, int delay) {
/* 30 */     this.x = pos.func_177958_n();
/* 31 */     this.y = pos.func_177956_o();
/* 32 */     this.z = pos.func_177952_p();
/* 33 */     this.bore = bore.func_145782_y();
/* 34 */     this.delay = delay;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 39 */     buffer.writeInt(this.x);
/* 40 */     buffer.writeInt(this.y);
/* 41 */     buffer.writeInt(this.z);
/* 42 */     buffer.writeInt(this.bore);
/* 43 */     buffer.writeInt(this.delay);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 48 */     this.x = buffer.readInt();
/* 49 */     this.y = buffer.readInt();
/* 50 */     this.z = buffer.readInt();
/* 51 */     this.bore = buffer.readInt();
/* 52 */     this.delay = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFXBoreDig message, MessageContext ctx) {
/* 57 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketFXBoreDig.this.processMessage(message); } });
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(final PacketFXBoreDig message) {
/*    */     try {
/* 65 */       WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
/* 66 */       final BlockPos pos = new BlockPos(message.x, message.y, message.z);
/* 67 */       final Entity entity = worldClient.func_73045_a(message.bore);
/* 68 */       if (entity == null)
/* 69 */         return;  final IBlockState ts = worldClient.func_180495_p(pos);
/* 70 */       if (ts.func_177230_c() == Blocks.field_150350_a)
/*    */         return; 
/* 72 */       for (int a = 0; a < message.delay; a++) {
/* 73 */         ServerEvents.addRunnableClient(worldClient, new Runnable() {
/*    */               public void run() {
/* 75 */                 FXDispatcher.INSTANCE.boreDigFx(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), entity, ts, ts
/* 76 */                     .func_177230_c().func_176201_c(ts) >> 12 & 0xFF, message.delay);
/*    */               }
/*    */             }a);
/*    */       } 
/* 80 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXBoreDig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */