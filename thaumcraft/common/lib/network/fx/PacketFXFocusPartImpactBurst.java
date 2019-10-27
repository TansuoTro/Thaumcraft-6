/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.casters.FocusEffect;
/*    */ import thaumcraft.api.casters.FocusEngine;
/*    */ import thaumcraft.api.casters.IFocusElement;
/*    */ 
/*    */ public class PacketFXFocusPartImpactBurst
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXFocusPartImpactBurst, IMessage>
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private String parts;
/*    */   
/*    */   public PacketFXFocusPartImpactBurst() {}
/*    */   
/*    */   public PacketFXFocusPartImpactBurst(double x, double y, double z, String[] parts) {
/* 28 */     this.x = x;
/* 29 */     this.y = y;
/* 30 */     this.z = z;
/* 31 */     this.parts = "";
/* 32 */     for (int a = 0; a < parts.length; a++) {
/* 33 */       if (a > 0) this.parts += "%"; 
/* 34 */       this.parts += parts[a];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 41 */     buffer.writeFloat((float)this.x);
/* 42 */     buffer.writeFloat((float)this.y);
/* 43 */     buffer.writeFloat((float)this.z);
/* 44 */     ByteBufUtils.writeUTF8String(buffer, this.parts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 49 */     this.x = buffer.readFloat();
/* 50 */     this.y = buffer.readFloat();
/* 51 */     this.z = buffer.readFloat();
/* 52 */     this.parts = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFXFocusPartImpactBurst message, MessageContext ctx) {
/* 57 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketFXFocusPartImpactBurst.this.processMessage(message); } });
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketFXFocusPartImpactBurst message) {
/* 63 */     String[] partKeys = message.parts.split("%");
/* 64 */     int amt = Math.max(1, 20 / partKeys.length);
/* 65 */     Random r = (Minecraft.func_71410_x()).field_71441_e.field_73012_v;
/* 66 */     for (String k : partKeys) {
/* 67 */       IFocusElement part = FocusEngine.getElement(k);
/* 68 */       if (part != null && part instanceof FocusEffect)
/* 69 */         for (int a = 0; a < amt; a++)
/* 70 */           ((FocusEffect)part).renderParticleFX((Minecraft.func_71410_x()).field_71441_e, message.x, message.y, message.z, r
/* 71 */               .nextGaussian() * 0.4D, r.nextGaussian() * 0.4D, r.nextGaussian() * 0.4D);  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXFocusPartImpactBurst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */