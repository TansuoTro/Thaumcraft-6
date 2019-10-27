/*    */ package thaumcraft.common.lib.network.fx;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
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
/*    */ public class PacketFXFocusEffect
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFXFocusEffect, IMessage> {
/*    */   float x;
/*    */   float y;
/*    */   float z;
/*    */   float mx;
/*    */   float my;
/*    */   float mz;
/*    */   String parts;
/*    */   
/*    */   public PacketFXFocusEffect() {}
/*    */   
/*    */   public PacketFXFocusEffect(float x, float y, float z, float mx, float my, float mz, String[] parts) {
/* 29 */     this.x = x;
/* 30 */     this.y = y;
/* 31 */     this.z = z;
/* 32 */     this.mx = mx;
/* 33 */     this.my = my;
/* 34 */     this.mz = mz;
/* 35 */     this.parts = "";
/* 36 */     for (int a = 0; a < parts.length; a++) {
/* 37 */       if (a > 0) this.parts += "%"; 
/* 38 */       this.parts += parts[a];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 44 */     buffer.writeFloat(this.x);
/* 45 */     buffer.writeFloat(this.y);
/* 46 */     buffer.writeFloat(this.z);
/* 47 */     buffer.writeFloat(this.mx);
/* 48 */     buffer.writeFloat(this.my);
/* 49 */     buffer.writeFloat(this.mz);
/* 50 */     ByteBufUtils.writeUTF8String(buffer, this.parts);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 55 */     this.x = buffer.readFloat();
/* 56 */     this.y = buffer.readFloat();
/* 57 */     this.z = buffer.readFloat();
/* 58 */     this.mx = buffer.readFloat();
/* 59 */     this.my = buffer.readFloat();
/* 60 */     this.mz = buffer.readFloat();
/* 61 */     this.parts = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFXFocusEffect message, MessageContext ctx) {
/* 66 */     Minecraft.func_71410_x().func_152344_a(new Runnable() { public void run() { PacketFXFocusEffect.this.processMessage(message); } });
/* 67 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   void processMessage(PacketFXFocusEffect message) {
/* 72 */     String[] partKeys = message.parts.split("%");
/* 73 */     int amt = Math.max(1, 10 / partKeys.length);
/* 74 */     for (String k : partKeys) {
/* 75 */       IFocusElement part = FocusEngine.getElement(k);
/* 76 */       if (part != null && part instanceof FocusEffect)
/* 77 */         for (int a = 0; a < amt; a++)
/* 78 */           ((FocusEffect)part).renderParticleFX((Minecraft.func_71410_x()).field_71441_e, message.x, message.y, message.z, message.mx + 
/*    */               
/* 80 */               (Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextGaussian() / 20.0D, message.my + 
/* 81 */               (Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextGaussian() / 20.0D, message.mz + 
/* 82 */               (Minecraft.func_71410_x()).field_71441_e.field_73012_v.nextGaussian() / 20.0D);  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\fx\PacketFXFocusEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */