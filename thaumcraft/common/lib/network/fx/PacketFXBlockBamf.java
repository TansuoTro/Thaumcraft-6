/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketFXBlockBamf
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXBlockBamf, IMessage> {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private int color;
/*    */   private byte flags;
/*    */   private byte face;
/*    */   
/*    */   public PacketFXBlockBamf() {}
/*    */   
/*    */   public PacketFXBlockBamf(double x, double y, double z, int color, boolean sound, boolean flair, EnumFacing side) {
/* 28 */     this.x = x;
/* 29 */     this.y = y;
/* 30 */     this.z = z;
/* 31 */     this.color = color;
/* 32 */     int f = 0;
/* 33 */     if (sound) f = Utils.setBit(f, 0); 
/* 34 */     if (flair) f = Utils.setBit(f, 1); 
/* 35 */     if (side != null) { this.face = (byte)side.ordinal(); } else { this.face = -1; }
/* 36 */      this.flags = (byte)f;
/*    */   }
/*    */ 
/*    */   
/*    */   public PacketFXBlockBamf(BlockPos pos, int color, boolean sound, boolean flair, EnumFacing side) {
/* 41 */     this.x = pos.func_177958_n() + 0.5D;
/* 42 */     this.y = pos.func_177956_o() + 0.5D;
/* 43 */     this.z = pos.func_177952_p() + 0.5D;
/* 44 */     this.color = color;
/* 45 */     int f = 0;
/* 46 */     if (sound) f = Utils.setBit(f, 0); 
/* 47 */     if (flair) f = Utils.setBit(f, 1); 
/* 48 */     if (side != null) { this.face = (byte)side.ordinal(); } else { this.face = -1; }
/* 49 */      this.flags = (byte)f;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 55 */     buffer.writeDouble(this.x);
/* 56 */     buffer.writeDouble(this.y);
/* 57 */     buffer.writeDouble(this.z);
/* 58 */     buffer.writeInt(this.color);
/* 59 */     buffer.writeByte(this.flags);
/* 60 */     buffer.writeByte(this.face);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 65 */     this.x = buffer.readDouble();
/* 66 */     this.y = buffer.readDouble();
/* 67 */     this.z = buffer.readDouble();
/* 68 */     this.color = buffer.readInt();
/* 69 */     this.flags = buffer.readByte();
/* 70 */     this.face = buffer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFXBlockBamf message, MessageContext ctx) {
/* 75 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketFXBlockBamf.this.processMessage(message); } });
/* 76 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketFXBlockBamf message) {
/* 81 */     EnumFacing side = null;
/* 82 */     if (message.face >= 0) side = EnumFacing.func_82600_a(message.face); 
/* 83 */     if (message.color != -9999) {
/* 84 */       FXDispatcher.INSTANCE.drawBamf(message.x, message.y, message.z, message.color, 
/*    */           
/* 86 */           Utils.getBit(message.flags, 0), Utils.getBit(message.flags, 1), side);
/*    */     } else {
/*    */       
/* 89 */       FXDispatcher.INSTANCE.drawBamf(message.x, message.y, message.z, 
/*    */           
/* 91 */           Utils.getBit(message.flags, 0), Utils.getBit(message.flags, 1), side);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXBlockBamf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */