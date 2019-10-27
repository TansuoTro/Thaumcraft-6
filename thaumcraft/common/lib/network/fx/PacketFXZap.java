/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ public class PacketFXZap
/*    */   extends Object implements IMessage, IMessageHandler<PacketFXZap, IMessage> {
/*    */   private Vec3d source;
/*    */   private Vec3d target;
/*    */   private int color;
/*    */   private float width;
/*    */   
/*    */   public PacketFXZap() {}
/*    */   
/*    */   public PacketFXZap(Vec3d source, Vec3d target, int color, float width) {
/* 23 */     this.source = source;
/* 24 */     this.target = target;
/* 25 */     this.color = color;
/* 26 */     this.width = width;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 31 */     buffer.writeDouble(this.source.field_72450_a);
/* 32 */     buffer.writeDouble(this.source.field_72448_b);
/* 33 */     buffer.writeDouble(this.source.field_72449_c);
/* 34 */     buffer.writeDouble(this.target.field_72450_a);
/* 35 */     buffer.writeDouble(this.target.field_72448_b);
/* 36 */     buffer.writeDouble(this.target.field_72449_c);
/* 37 */     buffer.writeInt(this.color);
/* 38 */     buffer.writeFloat(this.width);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 43 */     this.source = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
/* 44 */     this.target = new Vec3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
/* 45 */     this.color = buffer.readInt();
/* 46 */     this.width = buffer.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(PacketFXZap message, MessageContext ctx) {
/* 52 */     Color c = new Color(message.color);
/* 53 */     FXDispatcher.INSTANCE.arcBolt(message.source.field_72450_a, message.source.field_72448_b, message.source.field_72449_c, message.target.field_72450_a, message.target.field_72448_b, message.target.field_72449_c, c
/*    */ 
/*    */         
/* 56 */         .getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, message.width);
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXZap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */