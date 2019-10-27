/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.items.casters.CasterManager;
/*    */ import thaumcraft.common.items.tools.ItemElementalShovel;
/*    */ 
/*    */ public class PacketItemKeyToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketItemKeyToServer, IMessage>
/*    */ {
/*    */   private byte key;
/*    */   private byte mod;
/*    */   
/*    */   public PacketItemKeyToServer() {}
/*    */   
/*    */   public PacketItemKeyToServer(int key) {
/* 23 */     this.key = (byte)key;
/* 24 */     this.mod = 0;
/*    */   }
/*    */   
/*    */   public PacketItemKeyToServer(int key, int mod) {
/* 28 */     this.key = (byte)key;
/* 29 */     this.mod = (byte)mod;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 34 */     buffer.writeByte(this.key);
/* 35 */     buffer.writeByte(this.mod);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 40 */     this.key = buffer.readByte();
/* 41 */     this.mod = buffer.readByte();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketItemKeyToServer message, final MessageContext ctx) {
/* 47 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 48 */     worldServer.func_152344_a(new Runnable() {
/*    */           public void run() {
/* 50 */             WorldServer worldServer = (this.val$ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 51 */             if (worldServer == null)
/*    */               return; 
/* 53 */             EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/*    */             
/* 55 */             if (entityPlayerMP != null && entityPlayerMP instanceof EntityPlayer) {
/* 56 */               boolean flag = false;
/* 57 */               if (((EntityPlayer)entityPlayerMP).func_184614_ca() != null) {
/* 58 */                 if (message.key == 1 && ((EntityPlayer)entityPlayerMP).func_184614_ca().func_77973_b() instanceof thaumcraft.api.casters.ICaster) {
/* 59 */                   CasterManager.toggleMisc(((EntityPlayer)entityPlayerMP).func_184614_ca(), worldServer, (EntityPlayer)entityPlayerMP, message.mod);
/* 60 */                   flag = true;
/*    */                 } 
/* 62 */                 if (!flag && message.key == 1 && ((EntityPlayer)entityPlayerMP).func_184592_cb().func_77973_b() instanceof thaumcraft.api.casters.ICaster) {
/* 63 */                   CasterManager.toggleMisc(((EntityPlayer)entityPlayerMP).func_184592_cb(), worldServer, (EntityPlayer)entityPlayerMP, message.mod);
/*    */                 }
/*    */                 
/* 66 */                 if (message.key == 1 && ((EntityPlayer)entityPlayerMP).func_184614_ca().func_77973_b() instanceof ItemElementalShovel) {
/* 67 */                   byte b = ((ItemElementalShovel)((EntityPlayer)entityPlayerMP).func_184614_ca().func_77973_b()).getOrientation(((EntityPlayer)entityPlayerMP).func_184614_ca());
/* 68 */                   ((ItemElementalShovel)((EntityPlayer)entityPlayerMP).func_184614_ca().func_77973_b()).setOrientation(((EntityPlayer)entityPlayerMP).func_184614_ca(), (byte)(b + 1));
/* 69 */                   flag = true;
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 77 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketItemKeyToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */