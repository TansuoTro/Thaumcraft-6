/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.text.TextComponentString;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ 
/*    */ public class PacketWarpMessage
/*    */   extends Object implements IMessage, IMessageHandler<PacketWarpMessage, IMessage> {
/* 17 */   protected int data = 0;
/* 18 */   protected byte type = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketWarpMessage(EntityPlayer player, byte type, int change) {
/* 24 */     this.data = change;
/* 25 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 30 */     buffer.writeInt(this.data);
/* 31 */     buffer.writeByte(this.type);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 36 */     this.data = buffer.readInt();
/* 37 */     this.type = buffer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketWarpMessage message, MessageContext ctx) {
/* 43 */     if (message.data != 0)
/* 44 */       Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketWarpMessage.this.processMessage(message); } }
/*    */         ); 
/* 46 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketWarpMessage message) {
/* 51 */     if (message.type == 0 && message.data > 0) {
/* 52 */       String text = I18n.func_74838_a("tc.addwarp");
/* 53 */       if (message.data < 0) {
/* 54 */         text = I18n.func_74838_a("tc.removewarp");
/*    */       } else {
/* 56 */         (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.whispers, 0.5F, 1.0F);
/*    */       }
/*    */     
/* 59 */     } else if (message.type == 1) {
/* 60 */       String text = I18n.func_74838_a("tc.addwarpsticky");
/* 61 */       if (message.data < 0) {
/* 62 */         text = I18n.func_74838_a("tc.removewarpsticky");
/*    */       } else {
/* 64 */         (Minecraft.func_71410_x()).field_71439_g.func_184185_a(SoundsTC.whispers, 0.5F, 1.0F);
/*    */       } 
/* 66 */       (Minecraft.func_71410_x()).field_71439_g.func_146105_b(new TextComponentString(text), true);
/*    */     }
/* 68 */     else if (message.data > 0) {
/* 69 */       String text = I18n.func_74838_a("tc.addwarptemp");
/* 70 */       if (message.data < 0) {
/* 71 */         text = I18n.func_74838_a("tc.removewarptemp");
/*    */       }
/* 73 */       (Minecraft.func_71410_x()).field_71439_g.func_146105_b(new TextComponentString(text), true);
/*    */     } 
/*    */   }
/*    */   
/*    */   public PacketWarpMessage() {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketWarpMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */