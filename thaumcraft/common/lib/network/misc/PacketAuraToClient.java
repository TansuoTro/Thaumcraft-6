/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.client.lib.events.HudHandler;
/*    */ import thaumcraft.common.world.aura.AuraChunk;
/*    */ 
/*    */ public class PacketAuraToClient
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketAuraToClient, IMessage> {
/*    */   short base;
/*    */   float vis;
/*    */   float flux;
/*    */   
/*    */   public PacketAuraToClient() {}
/*    */   
/*    */   public PacketAuraToClient(AuraChunk ac) {
/* 21 */     this.base = ac.getBase();
/* 22 */     this.vis = ac.getVis();
/* 23 */     this.flux = ac.getFlux();
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf dos) {
/* 28 */     dos.writeShort(this.base);
/* 29 */     dos.writeFloat(this.vis);
/* 30 */     dos.writeFloat(this.flux);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf dat) {
/* 35 */     this.base = dat.readShort();
/* 36 */     this.vis = dat.readFloat();
/* 37 */     this.flux = dat.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketAuraToClient message, MessageContext ctx) {
/* 42 */     Minecraft.func_71410_x().func_152344_a(new Runnable() {
/* 43 */           public void run() { HudHandler.currentAura = new AuraChunk(null, this.val$message.base, this.val$message.vis, this.val$message.flux); }
/*    */         });
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketAuraToClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */