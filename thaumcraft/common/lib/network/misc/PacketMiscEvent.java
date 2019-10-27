/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.lib.events.RenderEventHandler;
/*    */ import thaumcraft.common.config.ModConfig;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ 
/*    */ public class PacketMiscEvent
/*    */   extends Object implements IMessage, IMessageHandler<PacketMiscEvent, IMessage> {
/*    */   private byte type;
/* 19 */   private int value = 0;
/*    */   
/*    */   public static final byte WARP_EVENT = 0;
/*    */   
/*    */   public static final byte MIST_EVENT = 1;
/*    */   public static final byte MIST_EVENT_SHORT = 2;
/*    */   
/* 26 */   public PacketMiscEvent(byte type) { this.type = type; }
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketMiscEvent(byte type, int value) {
/* 31 */     this.type = type;
/* 32 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 37 */     buffer.writeByte(this.type);
/* 38 */     if (this.value != 0) buffer.writeInt(this.value);
/*    */   
/*    */   }
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 43 */     this.type = buffer.readByte();
/* 44 */     if (buffer.isReadable()) this.value = buffer.readInt();
/*    */   
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketMiscEvent message, MessageContext ctx) {
/* 50 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketMiscEvent.this.processMessage(message); } });
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketMiscEvent message) {
/* 56 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 57 */     switch (message.type) {
/*    */ 
/*    */       
/*    */       case 0:
/* 61 */         if (!ModConfig.CONFIG_GRAPHICS.nostress) entityPlayerSP.field_70170_p.func_184134_a(entityPlayerSP.field_70165_t, entityPlayerSP.field_70163_u, entityPlayerSP.field_70161_v, SoundsTC.heartbeat, SoundCategory.AMBIENT, 1.0F, 1.0F, false);
/*    */         
/*    */         break;
/*    */       case 1:
/* 65 */         RenderEventHandler.fogFiddled = true;
/* 66 */         RenderEventHandler.fogDuration = 2400;
/*    */         break;
/*    */       
/*    */       case 2:
/* 70 */         RenderEventHandler.fogFiddled = true;
/* 71 */         if (RenderEventHandler.fogDuration < 200)
/* 72 */           RenderEventHandler.fogDuration = 200; 
/*    */         break;
/*    */     } 
/*    */   }
/*    */   
/*    */   public PacketMiscEvent() {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketMiscEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */